package factory_method.factory;

import factory_method.domain.FlowerGift;
import factory_method.domain.Gift;
import factory_method.domain.GiftType;

/**
 * @author Jun
 * @description 鲜花工厂
 * @Date 2023/2/24 8:54
 * @modify
 */
public class FlowerFactory implements Factory {

    @Override
    public Gift createGift(GiftType giftType, Double cash) {

        Integer quantity = new Double(cash / giftType.getPrice()).intValue();
        return new FlowerGift(giftType, quantity);
    }
}
