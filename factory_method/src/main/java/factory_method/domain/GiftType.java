package factory_method.domain;

/**
 * @author Jun
 * @description 礼物枚举
 * @Date 2023/2/24 8:24
 * @modify
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

