package adapter.huawei;

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
