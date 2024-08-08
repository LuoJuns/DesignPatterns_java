package command.commands;

import command.enums.Position;
import command.receiver.Monkey;

/**
 * 命令2：任命猴子为齐天大圣
 */
public class CommandTwo extends Command{

    private final Monkey monkey;
    private final Position oriPosition;

    public CommandTwo(Monkey monkey) {
        this.monkey = monkey;
        this.oriPosition = monkey.getPosition();
    }

    @Override
    public void execute() {
        monkey.action(Position.GREATSAGEEQUALLINGHEAVEN);
    }

    @Override
    public void unExecute() {
        monkey.setPosition(oriPosition);
    }

    @Override
    public void reExecute() {
        execute();
    }

    @Override
    public String toString() {
        return "[任命猴子为齐天大圣]";
    }
}
