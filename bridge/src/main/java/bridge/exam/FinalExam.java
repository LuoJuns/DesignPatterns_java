package bridge.exam;

import bridge.student.Student;
import lombok.extern.slf4j.Slf4j;

/**
 * 期末考试
 */
@Slf4j
public class FinalExam implements Exam{

    private final Student student;

    @Override
    public void examBegin() {
        log.info("------------[期末考试即将开始]");
        student.onExamBegin();
    }

    @Override
    public void examInProgress() {
        log.info("------------[期末考试进行中]");
        student.onExamInProgress();
    }

    @Override
    public void examEnd() {
        log.info("------------[期末考试结束]");
        student.onExamEnd();
    }

    public FinalExam(Student student) {
        this.student = student;
    }
}
