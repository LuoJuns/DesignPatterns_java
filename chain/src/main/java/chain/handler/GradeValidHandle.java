package chain.handler;

import chain.dto.StuEnrollDTO;
import chain.dto.StuEnrollDetail;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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
