package abstract_factory;

import abstract_factory.domain.Battery;
import abstract_factory.domain.Bodywork;
import abstract_factory.domain.NewEnergyCar;
import abstract_factory.domain.Wheels;
import lombok.extern.slf4j.Slf4j;

/**
 * 新能源汽车商店
 */
@Slf4j
public class CarStore {
    private NewEnergyCar car;

    /**
     * 组装汽车
     *
     * @param factory 具体的新能源汽车工厂
     */
    public void getCar(final NewEnergyCarFactory factory) {
        log.info("====正在制造汽车组件====");
        Wheels wheels = factory.createWheels();
        Bodywork bodyWork = factory.createBodyWork();
        Battery battery = factory.createBattery();
        log.info("====正在组装汽车中====");
        setCar(factory.assembleCar(battery, bodyWork, wheels));
        log.info("[汽车已整装待发！]");
    }

    public NewEnergyCar getCar() {
        return car;
    }

    public void setCar(NewEnergyCar car) {
        this.car = car;
    }
}
