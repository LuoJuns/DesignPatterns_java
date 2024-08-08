package builder;

import lombok.extern.slf4j.Slf4j;
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
