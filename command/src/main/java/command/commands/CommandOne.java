package command.commands;

import command.enums.Position;
import command.receiver.Monkey;

/**
 * 命令1：任命猴子为弼马温
 */
public class CommandOne extends Command{

    private final Monkey monkey;
    private final Position oriPosition;

    public CommandOne(Monkey monkey) {
        this.monkey = monkey;
        this.oriPosition = monkey.getPosition();
    }

    @Override
    public void execute() {
        monkey.action(Position.HORSESMANAGER);
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
        return "[任命猴子为弼马温]";
    }
}
