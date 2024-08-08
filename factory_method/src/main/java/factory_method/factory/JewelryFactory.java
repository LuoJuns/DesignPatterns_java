package factory_method.factory;

import factory_method.domain.Gift;
import factory_method.domain.GiftType;
import factory_method.domain.JewelryGift;

/**
 * @author Jun
 * @description 珠宝工厂
 * @Date 2023/2/24 9:04
 * @modify
 */
public class JewelryFactory implements Factory {
    @Override
    public Gift createGift(GiftType giftType, Double cash) {
        Double weight = cash / giftType.getPrice();
        return new JewelryGift(giftType, weight);
    }
}
