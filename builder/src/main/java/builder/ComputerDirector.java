package builder;

import lombok.extern.slf4j.Slf4j;

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
