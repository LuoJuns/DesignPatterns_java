# 抽象工厂模式 ( Abstract Factory )

## 用途

> 工厂 ( Factory ) 和产品 ( Product ) 是 Abstract Factory 模式的主要参与者。 该模式描述了怎样在不直接实例化类的情况下创建一系列相关的产品对象。 它最适用于产品对象的数目和种类不变， 而具体产品系列之间存在不同的情况。 我们通过实例化一个特定的具体工厂对象来选择产品系列， 并且以后一直使用该工厂生产产品对象。 我们也能够通过用一个不同的具体工厂实例来替换原来的工厂对象以改变整个产品系列。 抽象工厂模式对产品系列的强调使它区别于其他只与一种产品对象有关的创建性模式。

## 实例

> 假定一个销售新能源的汽车商店、一辆新能源汽车由电池、车身、轮胎，三个组件组成，而新能源汽车工厂负责组件的生产和汽车的组装，具体的新能源汽车的工厂分别有亚迪工厂和特斯拉工厂，分别提供各自品牌的一系列汽车组件和组装功能

## 模式分析

> 首先定义汽车的组件的接口及其实现类

```java
/**
 * 汽车组件接口
 */
public interface Component {
    String getDescription();
}


/**
 * 电池
 */
public interface Battery extends Component{
}

/**
 * 车身外壳
 */
public interface Bodywork extends Component{

}

/**
 * 车轮
 */
public interface Wheels extends Component{
}
```

> 接下来定义工厂的接口以及两种不同主题工厂的实现类

```java
/**
 * 新能源汽车工厂
 */
public interface NewEnergyCarFactory {
    NewEnergyCar newEnergyCar = new NewEnergyCar();
    
    Wheels createWheels();

    Bodywork createBodyWork();

    Battery createBattery();

    NewEnergyCar assembleCar(Battery battery, Bodywork bodywork, Wheels wheels);
}
```
```java
/**
 * 比亚迪汽车工厂
 */
@Slf4j
public class BYDCarFactory implements NewEnergyCarFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewEnergyVehicleStore.class);

    @Override
    public Wheels createWheels() {
        BYDWheels wheels = new BYDWheels();
        LOGGER.info("正在制作-{}", wheels.getDescription());
        return wheels;

    }

    @Override
    public Bodywork createBodyWork() {
        BYDBodywork bodywork = new BYDBodywork();
        LOGGER.info("正在制造-{}", bodywork.getDescription());
        return bodywork;

    }

    @Override
    public Battery createBattery() {
        BYDBattery battery = new BYDBattery();
        LOGGER.info("正在制造-{}", battery.getDescription());
        return battery;
    }

    @Override
    public NewEnergyCar assembleCar(Battery battery, Bodywork bodywork, Wheels wheels) {
        this.newEnergyCar.setBattery(battery);
        log.info("正在组装 {}...", battery.getDescription());
        this.newEnergyCar.setBodywork(bodywork);
        log.info("正在组装 {}...", bodywork.getDescription());
        this.newEnergyCar.setWheels(wheels);
        log.info("正在组装 {}...", wheels.getDescription());
        return this.newEnergyCar;
    }
}
```
```java
/**
 * 特斯拉汽车工厂
 */
@Slf4j
public class TeslaVehicleFactory implements NewEnergyCarFactory {

    @Override
    public Wheels createWheels() {
        TeslaWheels wheels = new TeslaWheels();
        log.info("正在制造-{}", wheels.getDescription());
        return wheels;
    }

    @Override
    public Bodywork createBodyWork() {
        BYDBodywork bodywork = new BYDBodywork();
        log.info("正在制造-{}", bodywork.getDescription());
        return bodywork;
    }

    @Override
    public Battery createBattery() {
        TeslaBattery battery = new TeslaBattery();
        log.info("正在制造-{}", battery.getDescription());
        return battery;
    }

    @Override
    public NewEnergyCar assembleCar(Battery battery, Bodywork bodywork, Wheels wheels) {
        this.newEnergyCar.setBattery(battery);
        log.info("正在组装 {}...", battery.getDescription());
        this.newEnergyCar.setBodywork(bodywork);
        log.info("正在组装 {}...", bodywork.getDescription());
        this.newEnergyCar.setWheels(wheels);
        log.info("正在组装 {}...", wheels.getDescription());
        return this.newEnergyCar;
    }
}
```

> 现在，创建一个新能源汽车商店前需要首先创建具体的新能源汽车工厂对象，根据不同的品牌的新能源汽车工厂创建不同的汽车产品，并将其组装

```java
/**
 * 新能源汽车商店
 */
@Slf4j
public class NewEnergyVehicleStore {
    private NewEnergyCar car;

    /**
     * 组装汽车
     *
     * @param factory 具体的新能源汽车工厂
     */
    public void getCarFromFactory(final NewEnergyCarFactory factory) {
        log.info("====正在制造汽车组件====");
        Wheels wheels = factory.createWheels();
        Bodywork bodyWork = factory.createBodyWork();
        Battery battery = factory.createBattery();
        log.info("====正在组装汽车中====");
        setCar(factory.assembleCar(battery, bodyWork, wheels));
        log.info("[汽车已整装待发！]");
    }

    public NewEnergyCar getCar() {
        return car;
    }

    public void setCar(NewEnergyCar car) {
        this.car = car;
    }
}
```

> 程序入口

```java
public class Application {
    public static void main(String[] args) {
        NewEnergyVehicleStore factory = new NewEnergyVehicleStore();
        factory.getCarFromFactory(new BYDCarFactory());
        factory.getCarFromFactory(new TeslaVehicleFactory());
    }
}
```



## 适用场景

> * 一个系统应该独立于其产品是如何创建、组合和表示的
> * 一个系统需要配置多个系列中的一种产品
> * 一个相关产品对象的集合被设计用于一起使用，并且需对这个集合进行约束
> * 关于一个类的产品，使用者要特别关注它们的接口，而不是它们的实现
> * 依赖关系的生命周期理论上比使用者的生命周期短
> * 需要一个在运行时才能确定的参数来构造一个特定的依赖对象
> * 需要在运行时决定从一个集合中调用哪个系列的产品
> * 需要提供若干个只在运行时才知道的参数，然后才能解决依赖关系