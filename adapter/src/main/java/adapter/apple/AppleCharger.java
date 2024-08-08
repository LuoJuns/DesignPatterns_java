package adapter.apple;

import lombok.extern.slf4j.Slf4j;

/**
 * 苹果充电器
 */
@Slf4j
public class AppleCharger implements LightningCharger {
    @Override
    public void useLightingCharge(String phoneName) {
        log.info("  [正在使用苹果充电器给 {} 充电中...]", phoneName);
    }
}
