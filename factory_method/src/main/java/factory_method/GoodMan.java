package factory_method;

import factory_method.domain.Gift;
import factory_method.domain.GiftType;
import factory_method.factory.Factory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jun
 * @description 好男人类
 * @Date 2023/2/24 9:08
 * @modify
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
