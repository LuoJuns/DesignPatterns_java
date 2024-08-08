# 桥接模式 ( Bridge )

## 用途

> 将抽象部分与它的实现部分分离， 使它们都可以独立地变化。
>
> 当一个抽象可能有多个实现时， 通常用继承来协调它们。  但是此方法有时不够灵活。 继承机制将抽象部分与它的实现部分固定在一起， 使得难以对抽象部分和实现部分独立地进行修改、 扩充和重用。

## 实例

> 假定两场考试--期中考试和期末考试，考试中学生分为准备充分的学生和缺乏准备的学生两种，这两种学生在两场考试中的表现不同，准备充分的学生信心满满，缺乏准备的学生交了白卷。。。

## 模式分析

> 定义考试接口

```java
public interface Exam {
 void examBegin();
 void examInProgress();
 void examEnd();
}
```
> 定义学生接口

```java
public interface Student {
    void onExamBegin();
    void onExamInProgress();
    void onExamEnd();
}
```

> 定义两种不同考试的实现类

```java
/**
 * 期末考试
 */
@Slf4j
public class FinalExam implements Exam{

    private final Student student;

    @Override
    public void examBegin() {
        log.info("------------[期末考试即将开始]");
        student.onExamBegin();
    }

    @Override
    public void examInProgress() {
        log.info("------------[期末考试进行中]");
        student.onExamInProgress();
    }

    @Override
    public void examEnd() {
        log.info("------------[期末考试结束]");
        student.onExamEnd();
    }

    public FinalExam(Student student) {
        this.student = student;
    }
}

/**
 * 期中考试
 */
@Slf4j
public class MidTermExam implements Exam {

    private final Student student;

    @Override
    public void examBegin() {
        log.info("------------[期中考试即将开始]");
        student.onExamBegin();
    }

    @Override
    public void examInProgress() {
        log.info("------------[期中考试进行中]");
        student.onExamInProgress();
    }

    @Override
    public void examEnd() {
        log.info("------------[期中考试已结束]");
        student.onExamEnd();
    }

    public MidTermExam(Student student) {
        this.student = student;
    }
}
```

> 两种学生根据两场不同考试做出不同的表现
```java
/**
 * 缺乏准备的学生
 */
@Slf4j
public class LackPreparedStudent implements Student{

    @Override
    public void onExamBegin() {
        log.info("该学生缺乏准备，此刻非常慌张...");
    }

    @Override
    public void onExamInProgress() {
        log.info("该学生看不懂题目，非常沮丧...");
    }

    @Override
    public void onExamEnd() {
        log.info("该学生交了一张白卷，并且非常懊悔...");
    }
}

/**
 * 准备充分的学生
 */
@Slf4j
public class WellPreparedStudent implements Student {

    @Override
    public void onExamBegin() {
        log.info("该学生考前准备充分，非常有信心...");
    }

    @Override
    public void onExamInProgress() {
        log.info("该学生答题速度非常快，并进行了重复检查...");
    }

    @Override
    public void onExamEnd() {
        log.info("该学生上交了试卷，并感到非常满意...");
    }
}

```

> 程序入口

```java
@Slf4j
public class Application {

    public static void main(String[] args) {
        log.info("=================期中考试和缺乏准备的学生=======================");
        Exam midTermExam = new MidTermExam(new LackPreparedStudent());
        midTermExam.examBegin();
        midTermExam.examInProgress();
        midTermExam.examEnd();
        log.info("\n");
        log.info("================期中考试和准备充分的学生======================");
        Exam midTermExam02 = new MidTermExam(new WellPreparedStudent());
        midTermExam02.examBegin();
        midTermExam02.examInProgress();
        midTermExam02.examEnd();
        log.info("\n");
        log.info("=================期末考试和缺乏准备的学生=====================");
        Exam finalExam = new FinalExam(new LackPreparedStudent());
        finalExam.examBegin();
        finalExam.examInProgress();
        finalExam.examEnd();
        log.info("\n");
        log.info("===================期末考试和准备充分的学生=====================");
        Exam finalExam02 = new FinalExam(new WellPreparedStudent());
        finalExam02.examBegin();
        finalExam02.examInProgress();
        finalExam02.examEnd();
    }
}
```

## 适用场景

>* 你不希望在抽象和它的实现部分之间有一个固定的绑定关系。例如这种情况可能是因为,在程序运行时刻实现部分应可以被选择或者切换
>
>* 类的抽象以及它的实现都应该可以通过生成子类的方法加以扩充。 这时 Bridge 模式使你可以对不同的抽象接口和实现部分进行组合， 并分别对它们进行扩充
>