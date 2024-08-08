package factory_method;

import factory_method.domain.GiftType;
import factory_method.factory.FlowerFactory;
import factory_method.factory.JewelryFactory;

public class Application {
    public static void main(String[] args) {
        GoodMan goodMan = new GoodMan(new FlowerFactory());
        goodMan.prepareGift(GiftType.CARNATION,500.00);//创建对象只需调用工厂方法，不需要关注对象创建的具体细节

        GoodMan goodMan2 = new GoodMan(new JewelryFactory());
        goodMan2.prepareGift(GiftType.DIAMOND,10000.00);
    }
}
