# 命令模式 ( command)

## 用途

> 将请求方和接收方解耦，两者通过命令对象进行沟通，方便将命令对象进行储存、传递、调用、增加与管理 

## 实例

> 假设玉皇大帝（client）下旨（command）要任命孙悟空（receiver）为弼马温，或者齐天大圣，通过太白金星（invoker）进行命令的调用执行，撤回，重做等操作

## 模式分析

> 创建职位枚举

```java
/**
 * 职位
 */
public enum Position {
  MONKEYKING("美猴王"),
  HORSESMANAGER("弼马温"),
  GREATSAGEEQUALLINGHEAVEN("齐天大圣");

  private final String position;

  Position(String position) {
    this.position = position;
  }

  @Override
  public String toString() {
    return position;
  }
}

```

> 创建接收角色抽象类和具体实现类

```java
/**
 * 猴子抽象类
 */
public abstract class Monkey {

  private static final Logger LOGGER = LoggerFactory.getLogger(Monkey.class);

  private Position position;

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }
  // 行动方法
  public abstract void action(Position position);

  @Override
  public abstract String toString();

  /**
   * 打印当前职位
   */
  public void printPosition() {
    LOGGER.info("猴子当前的职位为：{}\n", getPosition());
  }
}



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
```
> 创建命令调用人

```java
/**
 * 命令调用人
 */
public final class CommandInvoker {

  private static final Logger LOGGER = LoggerFactory.getLogger(CommandInvoker.class);
  
  private final Deque<Command> reExecuteStack = new LinkedList<>();
  private final Deque<Command> unExecuteStack = new LinkedList<>();

  public void invokeCommand(Command command, Monkey monkey) {
    LOGGER.info("{}正在执行命令：{}，处理的对象为：{}", this, command, monkey);
    command.execute();
    unExecuteStack.offerLast(command);
  }

  public void undo() {
    if (!unExecuteStack.isEmpty()) {
      Command previousCommand = unExecuteStack.pollLast();
      reExecuteStack.offerLast(previousCommand);
      LOGGER.info("{}正在撤销命令：{}", this, previousCommand);
      previousCommand.unExecute();
    } else {
      LOGGER.info("没有可以撤销的命令了");
    }
  }

  public void redo() {
    if (!reExecuteStack.isEmpty()) {
      Command previousCommand = reExecuteStack.pollLast();
      unExecuteStack.offerLast(previousCommand);
      LOGGER.info("{}正在进行重做命令：{}", this, previousCommand);
      previousCommand.reExecute();
    } else {
      LOGGER.info("没有可以重做的操作了");
    }
  }

  @Override
  public String toString() {
    return "太白金星";
  }
}
```

> 创建命令抽象类Command和具体命令实现类
>

```java
/**
 * 命令抽象类
 */
public abstract class Command {

  public abstract void execute();

  public abstract void unExecute();

  public abstract void reExecute();

  @Override
  public abstract String toString();
}


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
```

> 创建客户端，创建具体命令指定命令调用人执行请求

```java
public class Client {

  public static void main(String[] args) {
    CommandInvoker invoker = new CommandInvoker();
    MonkeyKing monkey = new MonkeyKing();

    Command commandOne = new CommandOne(monkey);
    Command commandTwo = new CommandTwo(monkey);

    monkey.printPosition();

    // 玉皇大帝任命猴子为弼马温 --> 太白金星前去任命
    invoker.invokeCommand(commandOne, monkey);
    monkey.printPosition();


    // 玉皇大帝要给猴哥升职为齐天大圣 --> 太白金星前去任命
    invoker.invokeCommand(commandTwo, monkey);
    monkey.printPosition();

    // 玉皇大帝要撤了猴子的齐天大圣 --> 太白金星执行了撤回
    invoker.undo();
    monkey.printPosition();

    // 玉皇大帝要重新任命猴子为齐天大圣 --> 太白金星执行了重新任命
    invoker.redo();
    monkey.printPosition();
  }
}
```

## 适用场景

> * 系统需要将请求调用者和请求接收者解耦，使得调用者和接收者不直接交互。 
> * 系统需要支持命令的撤销(Undo)操作和恢复(Redo)操作。 