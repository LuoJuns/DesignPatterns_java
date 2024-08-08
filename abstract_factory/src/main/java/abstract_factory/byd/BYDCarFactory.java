package abstract_factory.byd;

import abstract_factory.NewEnergyCarFactory;
import abstract_factory.CarStore;
import abstract_factory.domain.Battery;
import abstract_factory.domain.Bodywork;
import abstract_factory.domain.NewEnergyCar;
import abstract_factory.domain.Wheels;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 比亚迪汽车工厂
 */
@Slf4j
public class BYDCarFactory implements NewEnergyCarFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarStore.class);

    @Override
    public Wheels createWheels() {
        BYDWheels wheels = new BYDWheels();
        LOGGER.info("正在制作-{}", wheels.getDescription());
        return wheels;

    }

    @Override
    public Bodywork createBodyWork() {
        BYDBodywork bodywork = new BYDBodywork();
        LOGGER.info("正在制造-{}", bodywork.getDescription());
        return bodywork;

    }

    @Override
    public Battery createBattery() {
        BYDBattery battery = new BYDBattery();
        LOGGER.info("正在制造-{}", battery.getDescription());
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
