package adapter.adapters;

import adapter.apple.AppleCharger;
import adapter.huawei.TypeCCharge;

/**
 * 苹果适配器 使华为手机可以用苹果充电器进行充电
 */
public class AppleChargeAdapter implements TypeCCharge {
    private final AppleCharger appleCharger;
    public AppleChargeAdapter() {
        this.appleCharger = new AppleCharger();
    }
    @Override
    public void useTypeCCharge(String phoneName) {
        appleCharger.useLightingCharge(phoneName);
    }
}
