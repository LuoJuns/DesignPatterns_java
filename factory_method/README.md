# 工厂方法 ( Factory Method )

## 用途

> 定义一个用于创建对象的接口， 让子类决定实例化哪一个类。 Factory Method使一个类的
  实例化延迟到其子类

## 别名

> 虚构造器 ( Virtual Constructor )

## 实例

> 假定现在过节日，好男人需要准备礼物，礼物分为鲜花礼品和珠宝礼品，现在有鲜花工厂和珠宝工厂，好男人根据不同的对象和预算来准备不同的礼品

## 模式分析

> 在基于类的设计中，工厂方法模式通常作为创建模式来使用。它使用工厂方法来处理创建对象的过程，无需指定创建对象的确切类型。客户端通过调用工厂方法来创建对象，这里的方法是在接口中指定的，或是由子类实现的，或是由基类实现，或者通过子类进行方法覆盖，从头至尾无需调用具体类的构造函数
>
> 首先定义工厂接口和礼品接口

```java
/**
 * @description 工厂接口
 */
public interface Factory {

    Gift createGift(GiftType giftType, Double cash);
}

/**
 * @description 礼物抽象类
 */
public interface Gift {
    
    GiftType getGiftType();
}
```

> 定义礼品的枚举类型
```java
/**
 * @description 礼物枚举
 */
public enum GiftType {
    CARNATION("康乃馨", 99.0),
    ROSE("玫瑰", 199.99),
    GOLD("金子", 6999.88),
    DIAMOND("钻石", 12888.88);

    private final String giftName;
    private final Double price;

    GiftType(String giftName, Double price) {
        this.giftName = giftName;
        this.price = price;
    }

    public String getGiftName() {
        return giftName;
    }

    public Double getPrice() {
        return price;
    }
}
```

> 定义两种工厂的实现类，分别负责准备鲜花礼品和珠宝礼品
```java
/**
 * @description 鲜花工厂
 */
public class FlowerFactory implements Factory {

    @Override
    public Gift createGift(GiftType giftType, Double cash) {

        Integer quantity = new Double(cash / giftType.getPrice()).intValue();
        return new FlowerGift(giftType, quantity);
    }
}

/**
 * @description 珠宝工厂
 */
public class JewelryFactory implements Factory {
    @Override
    public Gift createGift(GiftType giftType, Double cash) {
        Double weight = cash / giftType.getPrice();
        return new JewelryGift(giftType, weight);
    }
}
```
> 定义好男人类，在准备礼品的接口方法里调用工厂的创建方法
```java
/**
 * @description 好男人类
 */
@Slf4j
public class GoodMan {
    private final Factory factory;

    public GoodMan(Factory factory) {
        this.factory = factory;
    }

    public void prepareGift(GiftType giftType, Double cash) {
        Gift gift = factory.createGift(giftType, cash);
        log.info(gift.toString());
    }

}
```

> 程序入口

```java
public class Application {
    public static void main(String[] args) {
        GoodMan goodMan = new GoodMan(new FlowerFactory());
        // 创建对象只需调用工厂方法，不需要关注对象创建的具体细节
        goodMan.prepareGift(GiftType.CARNATION,500.00);

        GoodMan goodMan2 = new GoodMan(new JewelryFactory());
        goodMan2.prepareGift(GiftType.DIAMOND,10000.00);
    }
}
```



## 适用场景

>* 当一个类不知道它所必须创建的对象的类的时候
>* 当一个类希望由它的子类来指定它所创建的对象的时候
>* 当类将创建对象的职责委托给多个帮助子类中的某一个， 并且你希望将哪一个帮助子类是代理者这一信息局部化的时候