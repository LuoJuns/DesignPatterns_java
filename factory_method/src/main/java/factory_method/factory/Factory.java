package factory_method.factory;

import factory_method.domain.Gift;
import factory_method.domain.GiftType;

/**
 * @author Jun
 * @description 工厂接口
 * @Date 2023/2/24 8:21
 * @modify
 */
public interface Factory {

    Gift createGift(GiftType giftType, Double cash);
}
