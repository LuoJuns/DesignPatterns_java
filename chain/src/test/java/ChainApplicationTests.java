import chain.ChainApplication;
import chain.dto.StuEnrollDTO;
import chain.dto.StuEnrollDetail;
import chain.enums.ApplyType;
import chain.enums.HealthCondition;
import chain.handler.EnrollChainManager;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
