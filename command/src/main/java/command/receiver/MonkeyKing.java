package command.receiver;

import command.enums.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接受命令的对象-孙悟空
 */
public class MonkeyKing extends Monkey{

    private static final Logger LOGGER = LoggerFactory.getLogger(MonkeyKing.class);

    public MonkeyKing() {
        setPosition(Position.MONKEYKING);
    }

    @Override
    public void action(Position position) {
        switch (position) {
            case HORSESMANAGER:
                LOGGER.info("孙悟空心想：芝麻大官，还不如我山里称大王，我才不去！");
                break;
            case GREATSAGEEQUALLINGHEAVEN:
                LOGGER.info("孙悟空心想：这个称号听起来不错，去看看吧！"); setPosition(position);
                break;
        }

    }

    @Override
    public String toString() {
        return "孙悟空";
    }
}
