package bridge;

import bridge.exam.Exam;
import bridge.exam.FinalExam;
import bridge.exam.MidTermExam;
import bridge.student.LackPreparedStudent;
import bridge.student.WellPreparedStudent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class Application {

    public static void main(String[] args) {
        log.info("=================期中考试和缺乏准备的学生==================");
        Exam midTermExam = new MidTermExam(new LackPreparedStudent());
        midTermExam.examBegin();
        midTermExam.examInProgress();
        midTermExam.examEnd();
        log.info("\n");
        log.info("================期中考试和准备充分的学生==================");
        Exam midTermExam02 = new MidTermExam(new WellPreparedStudent());
        midTermExam02.examBegin();
        midTermExam02.examInProgress();
        midTermExam02.examEnd();
        log.info("\n");
        log.info("================期末考试和缺乏准备的学生=================");
        Exam finalExam = new FinalExam(new LackPreparedStudent());
        finalExam.examBegin();
        finalExam.examInProgress();
        finalExam.examEnd();
        log.info("\n");
        log.info("================期末考试和准备充分的学生==================");
        Exam finalExam02 = new FinalExam(new WellPreparedStudent());
        finalExam02.examBegin();
        finalExam02.examInProgress();
        finalExam02.examEnd();
    }
}