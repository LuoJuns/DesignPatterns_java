# 适配器模式 ( Adapter )

## 别名

> Wrapper (包装器)

## 用途

> 将一个类的接口转换成客户希望的另外一个接口。 Adapter模式使得原本由于接口不兼容
  而不能一起工作的那些类可以一起工作。

## 实例

> 假设你的苹果手机只能使用Lightning接口的苹果充电器充电，华为手机只能使用TypeC接口的华为充电器充电，而新的需求来了，要求苹果手机充电要可以使用华为充电器，而华为手机充电也要可以使用苹果充电器，那么我们就需要创建两个两个充电适配器：
>
> 【苹果手机适配华为充电器 ——> 华为充电适配器】
>
> 【华为手机适配苹果充电器 ——> 苹果充电适配器】

## 模式分析

> 现在分别定义Lightning充电器接口 ( LightningCharger ) 和TypeC充电器接口 ( TypeCCharge)

```java
/**
 * Lightning充电器接口
 */
public interface LightningCharger {
    void useLightingCharge(String phoneName);
}

/**
 * TypeC充电器接口
 */
public interface TypeCCharge {
    void useTypeCCharge(String phoneName);
}
```
> 定义这两个接口的具体实现类，苹果充电器实现LightningCharger接口，华为充电器实现TypeCCharge接口

```java
/**
 * 苹果充电器
 */
@Slf4j
public class AppleCharger implements LightningCharger {
    @Override
    public void useLightingCharge(String phoneName) {
        log.info("  [正在使用苹果充电器给 {} 充电中...]", phoneName);
    }
}

/**
 * 华为充电器
 */
@Slf4j
public class HuaWeiCharger implements TypeCCharge {

    @Override
    public void useTypeCCharge(String phoneName) {
        log.info("[正在使用华为充电器给 {} 充电中...]", phoneName);
    }
}
```
> 定义具体手机类

```java
/**
 * 苹果手机
 */
public class IPhone {
    private final LightningCharger charger;
    private final String phoneName = "苹果手机";

    public IPhone(LightningCharger charger) {
        this.charger = charger;
    }

    public void charge(){
        charger.useLightingCharge(phoneName);
    }
}

/**
 * 华为手机
 */
public class HuaWei {
    private final TypeCCharge charger;
    private final String phoneName = "华为手机";

    public HuaWei(TypeCCharge charger) {
        this.charger = charger;
    }

    public void charge(){
        charger.useTypeCCharge(phoneName);
    }
}
```
> 现在有新的需求，需要创建两个适配器，来达到苹果手机用华为充电器充电，和华为手机使用苹果充电器充电的目的

```java
/**
 * 华为充电器适配器 使苹果手机可以用华为充电器进行充电
 * 适配器 adapter 要实现需求方原本的接口类方法（上承），并内置一个需要新适配的对象（下接）
 */
public class HuaWeiChargerAdapter implements LightningCharger {
    private final HuaWeiCharger huaWeiCharger;
    @Override
    public void useLightingCharge(String phoneName) {
        // 使用新适配的对象的具体方法
        huaWeiCharger.useTypeCCharge(phoneName);
    }
    public HuaWeiChargerAdapter() {
        this.huaWeiCharger = new HuaWeiCharger();
    }
}

/**
 * 苹果充电器适配器 使华为手机可以用苹果充电器进行充电
 */
public class AppleChargeAdapter implements TypeCCharge {
    private final AppleCharger appleCharger;
    public AppleChargeAdapter() {
        this.appleCharger = new AppleCharger();
    }
    @Override
    public void useTypeCCharge(String phoneName) {
        appleCharger.useLightingCharge(phoneName);
    }
}
```

> 程序入口

```java
@Slf4j
public class Application {
    public static void main(String[] args) {
        log.info("============旧的充电方式============]");
        IPhone phone = new IPhone(new AppleCharger());
        phone.charge();

        HuaWei huaWei = new HuaWei(new HuaWeiCharger());
        huaWei.charge();

        log.info("============新的充电方式============]");
        IPhone phone1 = new IPhone(new HuaWeiChargerAdapter());
        phone1.charge();

        HuaWei huaWei1 = new HuaWei(new AppleChargeAdapter());
        huaWei1.charge();
    }
}
```



## 适用场景

>* 需要使用的类的接口与需要的接口类型不匹配
>* 需要创建一个可重用的类，它可以与不相关的或不可预见的类进行协作，也就是说，
>  类不一定具有兼容的接口
>* 需要使用几个现有的子类，但是通过不断继承每个子类来调整它们的接口是不切实际的。
>  对象适配器可以调整其父类的接口，改变父类接口的具体实现。

## 类适配器示例

```java
// 已存在的、具有特殊功能、但不符合我们既有的标准接口的类
class Adaptee {
	public void specificRequest() {
		System.out.println("被适配类具有 特殊功能...");
	}
}

// 目标接口，或称为标准接口
interface Target {
	public void request();
}

// 具体目标类，只提供普通功能
class ConcreteTarget implements Target {
	public void request() {
		System.out.println("普通类 具有 普通功能...");
	}
}

// 适配器类，继承了被适配类，同时实现标准接口
class Adapter extends Adaptee implements Target{
	public void request() {
		super.specificRequest();
	}
}

// 测试类
public class Client {
	public static void main(String[] args) {
		// 使用普通功能类
		Target concreteTarget = new ConcreteTarget();
		concreteTarget.request();
		
		// 使用特殊功能类，即适配类
		Target adapter = new Adapter();
		adapter.request();
	}
}
```



## 类适配器和对象适配器的区别

>* 类适配器的适配功能是通过被适配类（接口）的一个具体子类对象间接完成的。因此，类适配器不能无法
对被适配类的所有子类进行适配
>* 类适配器重写了被适配类（接口）的方法，所以，类适配器可以看做被适配类（接口）的一个子类
>* 类适配器一般只需持有一个被适配类（接口）的对象，并不需要额外的对被适配类（接口）的引用
>* 对象适配器可以和多个被适配类（接口）的对象协同工作——即被适配类（接口）本身及其所有子类。适配器也可以一次为所有的被适配类（接口）添加自定义的功能。
>* 对象适配器使得重写被适配类（接口）的方法变成了不可能，这时需要的是被适配类（接口）的子类，而不是被适配类（接口）本身