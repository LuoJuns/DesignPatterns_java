package chain.handler;

import chain.dto.StuEnrollDTO;
import chain.dto.StuEnrollDetail;
import chain.enums.ApplyType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        validSchools = new ArrayList<>(Arrays.asList("天津私立大学", "天津特殊学院"));
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
