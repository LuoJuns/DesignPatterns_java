package bridge.exam;

import bridge.student.Student;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 期中考试
 */
@Slf4j
public class MidTermExam implements Exam {

    private final Student student;

    @Override
    public void examBegin() {
        log.info("------------[期中考试即将开始]");
        student.onExamBegin();
    }

    @Override
    public void examInProgress() {
        log.info("------------[期中考试进行中]");
        student.onExamInProgress();
    }

    @Override
    public void examEnd() {
        log.info("------------[期中考试已结束]");
        student.onExamEnd();
    }

    public MidTermExam(Student student) {
        this.student = student;
    }
}
