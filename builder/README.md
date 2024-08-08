# 创建者模式 ( Builder )

## 用途

> 将一个复杂对象的构造与它分开，使得同样的构造过程可以产生不同的对象。

## 实例

> 假定需要创建一台笔记本电脑，笔记本的几个简单属性有：cpu处理器，显卡，内存，假如一个数据模型的拥有很多属性，如果单纯使用构造方法来实例化对象，势必会造成构造方法参数爆炸的问题，代码的可读性和可靠性大大降低。此时，可以为这个对象创建的过程指定一个创建者，我们只需要向创建者描述该对象的一些具体细节，接下来的构造过程就统统交给创建者完成了。

## 模式分析

> 先创建数据模型，笔记本实体

```java
/**
 * 笔记本类
 */
@Data
public class Computer {
    private String CPU;
    private String videoCard;
    private String memorySize;


    @Override
    public String toString() {
        return " 该电脑的配置为：{" +
                "CPU='" + CPU + '\'' +
                ", 显卡='" + videoCard + '\'' +
                ", 内存='" + memorySize + '\'' +
                '}';
    }
}
```
> 创建笔记本创建者的抽象类

```java
public abstract class ComputerBuilder extends Computer {
    Computer computer = new Computer();

    public abstract void buildCPU();

    public abstract void buildVideoCard();

    public abstract void buildMemorySize();

    public Computer getComputer() {
        return this.computer;
    }
}
```

> 抽象创建者的两个具体实现类

```java
/**
 * 游戏本创建者
 */
@Slf4j
public class GameBookBuilder extends ComputerBuilder {
    @Override
    public void buildCPU() {
        log.info("创建游戏本CPU...");
        this.computer.setCPU("Intel i7-13700 13代酷睿");
    }

    @Override
    public void buildVideoCard() {
        log.info("创建游戏本显卡...");
        this.computer.setVideoCard("七彩虹 iGame RTX 4090");
    }

    @Override
    public void buildMemorySize() {
        log.info("创建游戏本内存...");
        this.computer.setMemorySize("32GB");
    }
}

/**
 * 商务本创建者
 */
@Slf4j
public class BusinessBookBuilder extends ComputerBuilder {
    @Override
    public void buildCPU() {
        log.info("创建商务本CPU...");
        this.computer.setCPU("Intel i5-12400F 12代酷睿");
    }

    @Override
    public void buildVideoCard() {
        log.info("创建商务本显卡...");
        this.computer.setVideoCard("华硕 GTX 1060");
    }

    @Override
    public void buildMemorySize() {
        log.info("创建商务本内存...");
        this.computer.setMemorySize("8GB");
    }
}
```

> 创建Director类并通过construct方法实现computer对象的构建过程

```java
/**
 *  computer对象构建的指挥者(Director)，隔离了客户与对象的生产过程，并控制产品对象的生产过程
 */
@Slf4j
public class ComputerDirector {
    private ComputerBuilder builder;

    public ComputerDirector(ComputerBuilder builder) {
        this.builder = builder;
    }

    public Computer construct() {
        log.info("正在构建笔记本相关配件...");
        builder.buildCPU();
        builder.buildVideoCard();
        builder.buildMemorySize();
        return builder.getComputer();
    }
}
```

> 程序入口

```java
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
```



## 适用场景

> * 创建一个复杂对象的算法应该独立于组成对象的组成部分以及它们是如何组合的
> * 构建过程必须为所构造的对象提供不同的表示形式