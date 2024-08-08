package chain.dto;

import chain.enums.ApplyType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
