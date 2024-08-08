package factory_method.domain;

/**
 * @author Jun
 * @description 鲜花礼物
 * @Date 2023/2/24 8:45
 * @modify
 */
public class FlowerGift implements Gift {

    private final GiftType giftType;

    private final Integer quantity;

    public FlowerGift(GiftType giftType, Integer quantity) {
        this.giftType = giftType;
        this.quantity = quantity;
    }

    @Override
    public GiftType getGiftType() {
        return giftType;
    }

    @Override
    public String toString() {
        return "准备了" + quantity + "朵" + giftType.getGiftName();
    }
}
