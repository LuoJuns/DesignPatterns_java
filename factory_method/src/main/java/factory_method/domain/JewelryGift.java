package factory_method.domain;

/**
 * @author Jun
 * @description 珠宝礼物
 * @Date 2023/2/24 8:38
 * @modify
 */
public class JewelryGift implements Gift {

    private final GiftType giftType;

    private final Double weight;

    public JewelryGift(GiftType giftType, Double weight) {
        this.giftType = giftType;
        this.weight = weight;
    }

    @Override
    public GiftType getGiftType() {
        return this.giftType;
    }

    @Override
    public String toString() {
        return "准备了" + String.format("%.2f", weight) + "克的" + giftType.getGiftName();
    }
}
