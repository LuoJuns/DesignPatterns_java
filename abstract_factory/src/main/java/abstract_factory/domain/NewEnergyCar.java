package abstract_factory.domain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NewEnergyCar extends Car {
    /**
     * 电池
     */
    private Battery battery;

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }
}
