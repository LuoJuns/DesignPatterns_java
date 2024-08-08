package bridge.student;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
