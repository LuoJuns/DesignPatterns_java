package chain.handler;

import chain.dto.StuEnrollDTO;
import chain.dto.StuEnrollDetail;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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
