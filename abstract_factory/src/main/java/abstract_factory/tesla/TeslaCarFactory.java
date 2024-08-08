package abstract_factory.tesla;

import abstract_factory.byd.BYDBodywork;
import abstract_factory.domain.Battery;
import abstract_factory.domain.Bodywork;
import abstract_factory.domain.NewEnergyCar;
import abstract_factory.domain.Wheels;
import abstract_factory.NewEnergyCarFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 特斯拉汽车工厂
 */
@Slf4j
public class TeslaCarFactory implements NewEnergyCarFactory {

    @Override
    public Wheels createWheels() {
        TeslaWheels wheels = new TeslaWheels();
        log.info("正在制造-{}", wheels.getDescription());
        return wheels;
    }

    @Override
    public Bodywork createBodyWork() {
        BYDBodywork bodywork = new BYDBodywork();
        log.info("正在制造-{}", bodywork.getDescription());
        return bodywork;
    }

    @Override
    public Battery createBattery() {
        TeslaBattery battery = new TeslaBattery();
        log.info("正在制造-{}", battery.getDescription());
        return battery;
    }

    @Override
    public NewEnergyCar assembleCar(Battery battery, Bodywork bodywork, Wheels wheels) {
        this.newEnergyCar.setBattery(battery);
        log.info("正在组装 {}...", battery.getDescription());
        this.newEnergyCar.setBodywork(bodywork);
        log.info("正在组装 {}...", bodywork.getDescription());
        this.newEnergyCar.setWheels(wheels);
        log.info("正在组装 {}...", wheels.getDescription());
        return this.newEnergyCar;
    }
}
