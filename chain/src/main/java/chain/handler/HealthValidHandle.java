package chain.handler;

import chain.dto.StuEnrollDTO;
import chain.dto.StuEnrollDetail;
import chain.enums.HealthCondition;
import org.springframework.stereotype.Component;

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
