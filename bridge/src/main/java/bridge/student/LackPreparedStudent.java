package bridge.student;

import lombok.extern.slf4j.Slf4j;

/**
 * 缺乏准备的学生
 */
@Slf4j
public class LackPreparedStudent implements Student{

    @Override
    public void onExamBegin() {
        log.info("该学生缺乏准备，此刻非常慌张...");
    }

    @Override
    public void onExamInProgress() {
        log.info("该学生看不懂题目，非常沮丧...");
    }

    @Override
    public void onExamEnd() {
        log.info("该学生交了一张白卷，并且非常懊悔...");
    }
}
