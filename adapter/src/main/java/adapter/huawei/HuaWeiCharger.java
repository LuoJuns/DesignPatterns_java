package adapter.huawei;


import lombok.extern.slf4j.Slf4j;

/**
 * 华为充电器
 */
@Slf4j
public class HuaWeiCharger implements TypeCCharge {

    @Override
    public void useTypeCCharge(String phoneName) {
        log.info("[正在使用华为充电器给 {} 充电中...]", phoneName);
    }
}
