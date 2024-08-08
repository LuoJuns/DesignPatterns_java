package abstract_factory;

import abstract_factory.domain.Battery;
import abstract_factory.domain.Bodywork;
import abstract_factory.domain.NewEnergyCar;
import abstract_factory.domain.Wheels;

/**
 * 新能源汽车工厂
 */
public interface NewEnergyCarFactory {
    NewEnergyCar newEnergyCar = new NewEnergyCar();
    Wheels createWheels();

    Bodywork createBodyWork();

    Battery createBattery();

    NewEnergyCar assembleCar(Battery battery, Bodywork bodywork, Wheels wheels);
}
