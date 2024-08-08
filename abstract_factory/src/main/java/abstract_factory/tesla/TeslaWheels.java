package abstract_factory.tesla;

import abstract_factory.domain.Wheels;

public class TeslaWheels implements Wheels {

    private final String DESCRIPTION = "特斯拉的车轮";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
