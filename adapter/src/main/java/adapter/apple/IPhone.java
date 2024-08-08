package adapter.apple;

/**
 * 苹果手机
 */
public class IPhone {
    private final LightningCharger charger;
    private final String phoneName = "苹果手机";

    public IPhone(LightningCharger charger) {
        this.charger = charger;
    }

    public void charge(){
        charger.useLightingCharge(phoneName);
    }
}
