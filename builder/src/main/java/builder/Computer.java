package builder;

import lombok.Data;

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
