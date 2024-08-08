package chain.handler;

import chain.dto.StuEnrollDTO;
import chain.dto.StuEnrollDetail;
import org.springframework.stereotype.Component;

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
