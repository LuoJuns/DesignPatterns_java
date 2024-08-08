package adapter;

import adapter.adapters.AppleChargeAdapter;
import adapter.adapters.HuaWeiChargerAdapter;
import adapter.apple.AppleCharger;
import adapter.apple.IPhone;
import adapter.huawei.HuaWei;
import adapter.huawei.HuaWeiCharger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {
    public static void main(String[] args) {
        log.info("============旧的充电方式============]");
        IPhone phone = new IPhone(new AppleCharger());
        phone.charge();

        HuaWei huaWei = new HuaWei(new HuaWeiCharger());
        huaWei.charge();

        log.info("============新的充电方式============]");
        IPhone phone1 = new IPhone(new HuaWeiChargerAdapter());
        phone1.charge();

        HuaWei huaWei1 = new HuaWei(new AppleChargeAdapter());
        huaWei1.charge();
    }
}
