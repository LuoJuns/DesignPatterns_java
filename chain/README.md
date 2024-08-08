# 责任链模式 ( chain)

## 用途

> 消除if else，优化校验流程

## 实例

> 假定某市的高考结束并且考生已在线上完成志愿填报，现在需要根据考生的具体情况和学校的具体要求来判定的一系列录取流程
>
> 录取规则（优先级从1~4）：
>
> 1.户籍校验：如果该批次的考生报考的类型为“高校”，则无需户籍验证，报考类型为“成人”，则部分学校需要进行本地户籍验证
>
> 2.申请日期校验：每个学校的截至申请日期不一致，学生的申请日期不得晚于学校的截至日期，否则录取失败
>
> 3.成绩校验：学生成绩需要大于学校的最低录取线
>
> 4.健康状态校验：学生的身体状态需要为健康，如果为糟糕则不予录取

## 模式分析

> 构建数据模型，Student(学生信息），StuEnrollDetail（学生录取信息详情），StuEnrollDTO（学生录取信息DTO）

```java
/**
 * 学生实体
 */
@Data
@Accessors(chain = true)
public class Student {
    private String name;
    private Integer age;
    private String nativePlace;
    private Double grade;
    private HealthCondition healthCondition;
}

/**
 * 学生录取信息详情
 */
@Data
@Accessors(chain = true)
public class StuEnrollDetail extends Student {
    private String applySchool;
    private String applyDate;
    private boolean applyResult;
    private String message;

    @Override
    public String toString() {
        return "StuEnrollDetail{" +
                "applySchool='" + applySchool + '\'' +
                "name='" + getName() + '\'' +
                "age='" + getAge() + '\'' +
                "nativePlace='" + getNativePlace() + '\'' +
                "grade='" + getGrade() + '\'' +
                "healthCondition='" + getHealthCondition() + '\'' +
                ", applyDate=" + applyDate +
                ", applyResult=" + applyResult +
                ", message='" + message + '\'' +
                '}'+"\n";
    }
}

/**
 * 学生录取信息DTO
 */
@Data
public class StuEnrollDTO {
    /**
     * 学生录取信息详情列表
     */
    private List<StuEnrollDetail> details = new ArrayList<>();

    /**
     * 该批次学生的申请类型
     */
    private ApplyType applyType;

    @Override
    public String toString() {
        return "StuEnrollDTO{" +
                "details=" + details +
                ", applyType=" + applyType +
                '}';
    }
}


```



> 创建步骤接口Step

```java
public interface Step<T, U> {
    /**
     * 处理步骤，ture继续下一步，false结束当前步骤
     *
     */
    boolean process(T detail);

    /**
     * 是否启用该步骤
     *
     */
    boolean isEnable(U info);
}
```
> 创建录取处理抽象类CollageEnrollHandle，集成步骤接口和Ordered接口

```java
public interface CollageEnrollHandle extends Step<StuEnrollDetail, StuEnrollDTO>, Ordered {
    /**
     * 录取相关操作前的预处理
     *
     */
    default void preHandle(StuEnrollDTO dto) {
    }
}
```

> 创建录取处理的几个具体实现类
>
> 1.户籍规则校验

```java
/**
 * 户籍校验处理器
 */
@Component
public class NativePlaceValidHandle implements CollageEnrollHandle {
    private List<String> validSchools;

    @Override
    public boolean process(StuEnrollDetail detail) {
        for (String validSchool : validSchools) {
            if (detail.getApplySchool().equals(validSchool)
                    && "天津".equals(detail.getNativePlace())) {
                detail.setApplyResult(false).setMessage("报考学院需要天津本地户籍，报考条件不符！");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEnable(StuEnrollDTO dto) {
        // 申请类型为高校，则无需本地户籍验证
        return !ApplyType.SENIOR.equals(dto.getApplyType());
    }

    @Override
    public void preHandle(StuEnrollDTO dto) {
        // 需要进行户籍校验的学校
        validSchools = new ArrayList<>(Arrays.asList("天津私立大学", "天津特殊学院"));
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
```

> 2.申请日期校验

```java
/**
 * 申请时间校验处理器
 */
@Component
public class ApplyDateValidHandle implements CollageEnrollHandle {
    private Map<String, String> dateMap;
    @Override
    public boolean process(StuEnrollDetail detail) {
        String applySchool = detail.getApplySchool();
        String endDate = dateMap.get(applySchool);
        if (detail.getApplyDate().compareTo(endDate) > 0){
            detail.setApplyResult(false).setMessage(applySchool+"的申请时间已过，录取通道已关闭!");
            return false;
        }
        return true;
    }

    @Override
    public boolean isEnable(StuEnrollDTO info) {
        return true;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void preHandle(StuEnrollDTO dto) {
        dateMap = new HashMap<>();
        dateMap.put("天津大学","20230701");
        dateMap.put("天津私立大学","20230601");
        dateMap.put("天津特殊学院","20230501");
        dateMap.put("天津工业大学","20230801");
        dateMap.put("天津师范大学","20230901");
    }
}
```


>3.成绩校验
>
 ```java
 /**
  * 成绩校验处理器
  */
 @Component
 public class GradeValidHandle implements CollageEnrollHandle {
     private Map<String, Double> gradeMap = new HashMap<>();
 
     @Override
     public boolean process(StuEnrollDetail detail) {
         String applySchool = detail.getApplySchool();
         if (detail.getGrade() < gradeMap.get(applySchool)) {
             detail.setApplyResult(false).setMessage("该生的成绩未达到" + applySchool + "的最低录取分数要求！");
             return false;
         }
         return true;
     }
 
     @Override
     public boolean isEnable(StuEnrollDTO info) {
         return true;
     }
 
     @Override
     public int getOrder() {
         return 10;
     }
 
     @Override
     public void preHandle(StuEnrollDTO dto) {
         // 加载大学的最低录取线
         gradeMap.put("天津大学", 613.0);
         gradeMap.put("天津私立大学", 512.0);
         gradeMap.put("天津特殊学院", 412.0);
         gradeMap.put("天津工业大学", 552.0);
         gradeMap.put("天津师范大学", 562.0);
     }
 }
 ```
>
> 4.健康状态校验
>
 ```java
 /**
  * 健康校验处理器
  */
 @Component
 public class HealthValidHandle implements CollageEnrollHandle {
     @Override
     public boolean process(StuEnrollDetail detail) {
         HealthCondition healthCondition = detail.getHealthCondition();
         if (!HealthCondition.HEALTH.equals(healthCondition)){
             detail.setApplyResult(false).setMessage("该生健康标准为达标！不予录取");
             return false;
         }
         return true;
     }
 
     @Override
     public boolean isEnable(StuEnrollDTO info) {
         return true;
     }
 
     @Override
     public int getOrder() {
         return 20;
     }
 }
 ```
>
> 5.将数据落库
>
 ```java
/**
  * 落库处理器
  */
 @Component
 public class FinalEnrollHandle implements CollageEnrollHandle {
     @Override
     public boolean process(StuEnrollDetail detail) {
         // 符合条件后的最终录取操作
         //TODO 可直接batchSave落库
         detail.setApplyResult(true).setMessage(String.format("已被%s成功录取！", detail.getApplySchool()));
         return true;
     }
 
     @Override
     public boolean isEnable(StuEnrollDTO info) {
         return true;
     }
 
     @Override
     public int getOrder() {
         return LOWEST_PRECEDENCE;
     }
 }
 ```

> 创建录取处理链的统一管理器EnrollChainManager

```java
/**
 * 录取处理链管理器
 */
@Component
@Slf4j
@AllArgsConstructor
public class EnrollChainManager {

    private final List<CollageEnrollHandle> handlers;

    @PostConstruct
    public void init() {
        Collections.sort(handlers, AnnotationAwareOrderComparator.INSTANCE);
        log.info("[录取执行顺序：{}]", handlers);
    }

    public void process(StuEnrollDTO dto) {
        log.info("[录取信息：{}]", dto);
        // 获取需开启的执行链路
        List<CollageEnrollHandle> handlerChain = handlers.stream().filter(
                (item) -> item.isEnable(dto)
        ).collect(Collectors.toList());
        log.info("[本次录取处理执行链路：{}]", handlerChain);

        // 预处理
        for (CollageEnrollHandle handler : handlerChain) {
            handler.preHandle(dto);
        }

        List<StuEnrollDetail> details = dto.getDetails();
        // 正式处理
        details.parallelStream().forEach(
                (item) -> {
                    for (CollageEnrollHandle handler : handlerChain) {
                        if (!handler.process(item)) {
                            return;
                        }
                    }
                }
        );

        Map<Boolean, List<StuEnrollDetail>> resultMap = details.stream().collect(Collectors.partitioningBy(StuEnrollDetail::isApplyResult));
        List<StuEnrollDetail> enrolledList = resultMap.get(true);
        List<StuEnrollDetail> failedList = resultMap.get(false);
        log.info("[本次处理总共录取成功{}人,录取人员信息{}]", enrolledList.size(), enrolledList);
        log.info("[本次处理录取失败人数{}人,录取失败人员信息{}]", failedList.size(), failedList);

    }
}
```

> 测试类

```java
@SpringBootTest(classes = ChainApplication.class)
@RunWith(SpringRunner.class)
class ChainApplicationTests {

    @Resource
    private EnrollChainManager manager;

    @Test
    void test() {
        StuEnrollDTO dto = buildDTO();
        manager.process(dto);
    }

    private StuEnrollDTO buildDTO() {
        StuEnrollDTO dto = new StuEnrollDTO();
        List<StuEnrollDetail> details = new ArrayList<>();
        StuEnrollDetail stu1 = new StuEnrollDetail();
        stu1.setApplyDate("20230413").setApplySchool("天津大学")
                .setName("LuoJun1")
                .setAge(18)
                .setGrade(570.00)
                .setHealthCondition(HealthCondition.HEALTH)
                .setNativePlace("天津");
        details.add(stu1);

        StuEnrollDetail stu2 = new StuEnrollDetail();
        stu2.setApplyDate("20230413").setApplySchool("天津工业大学")
                .setName("LuoJun2")
                .setAge(18)
                .setGrade(570.00)
                .setHealthCondition(HealthCondition.BAD)
                .setNativePlace("天津");
        details.add(stu2);

        StuEnrollDetail stu3 = new StuEnrollDetail();
        stu3.setApplyDate("20230413").setApplySchool("天津特殊学院")
                .setName("LuoJun3")
                .setAge(18)
                .setGrade(470.00)
                .setHealthCondition(HealthCondition.HEALTH)
                .setNativePlace("北京");
        details.add(stu3);

        StuEnrollDetail stu4 = new StuEnrollDetail();
        stu4.setApplyDate("20230902").setApplySchool("天津师范大学")
                .setName("LuoJun4")
                .setAge(18)
                .setGrade(470.00)
                .setHealthCondition(HealthCondition.HEALTH)
                .setNativePlace("北京");
        details.add(stu4);
        dto.setDetails(details);
        dto.setApplyType((ApplyType.SENIOR));

        return dto;
    }
}
```



## 适用场景

> * 校验规则复杂，并且校验规则依赖第三方配置信息