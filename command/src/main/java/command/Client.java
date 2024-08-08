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
package command;

import command.commands.Command;
import command.commands.CommandOne;
import command.commands.CommandTwo;
import command.invoker.CommandInvoker;
import command.receiver.MonkeyKing;


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
