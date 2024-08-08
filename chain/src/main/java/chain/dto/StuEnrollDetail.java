package chain.dto;

import chain.entity.Student;
import lombok.Data;
import lombok.experimental.Accessors;

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
