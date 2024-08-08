package abstract_factory.byd;

import abstract_factory.domain.Wheels;

public class BYDWheels implements Wheels {

    private final String DESCRIPTION = "比亚迪的车轮";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
