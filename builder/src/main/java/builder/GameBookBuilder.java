package builder;

import lombok.extern.slf4j.Slf4j;

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
