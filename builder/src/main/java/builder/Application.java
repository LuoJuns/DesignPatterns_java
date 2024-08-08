package builder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {
    public static void main(String[] args) {
        // 利用builder类的属性构造方法来构造具体对象的属性，也可以配合Director来隐藏具体构造细节，并将对象的创建过程隔离开来
        ComputerDirector director = new ComputerDirector(new GameBookBuilder());
        Computer gameBook = director.construct();
        log.info("游戏本的属性 {}", gameBook.toString());
        log.info("\n");
        director = new ComputerDirector(new BusinessBookBuilder());
        Computer businessBook = director.construct();
        log.info("商务本的属性 {}", businessBook.toString());
    }
}
