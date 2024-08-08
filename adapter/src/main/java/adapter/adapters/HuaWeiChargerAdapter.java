package adapter.adapters;

import adapter.apple.LightningCharger;
import adapter.huawei.HuaWeiCharger;

/**
 * 华为适配器 使苹果手机可以用华为充电器进行充电
 * 适配器 adapter 要实现需求方原本的接口类方法（上承），并内置一个需要新适配的对象（下接）
 */
public class HuaWeiChargerAdapter implements LightningCharger {
    private final HuaWeiCharger huaWeiCharger;
    @Override
    public void useLightingCharge(String phoneName) {
        // 使用新适配的对象的具体方法
        huaWeiCharger.useTypeCCharge(phoneName);
    }
    public HuaWeiChargerAdapter() {
        this.huaWeiCharger = new HuaWeiCharger();
    }
}
