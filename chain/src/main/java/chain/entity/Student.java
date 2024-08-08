package chain.entity;

import chain.enums.HealthCondition;
import lombok.Data;
import lombok.experimental.Accessors;

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
