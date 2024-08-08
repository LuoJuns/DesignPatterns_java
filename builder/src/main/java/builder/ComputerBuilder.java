package builder;

public abstract class ComputerBuilder extends Computer {
    Computer computer = new Computer();

    public abstract void buildCPU();

    public abstract void buildVideoCard();

    public abstract void buildMemorySize();

    public Computer getComputer() {
        return this.computer;
    }
}
