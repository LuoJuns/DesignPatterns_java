/**
 * MIT License
 * <p>
 * Copyright (c) 2017 James
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package command.invoker;

import command.commands.Command;
import command.receiver.Monkey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;

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
