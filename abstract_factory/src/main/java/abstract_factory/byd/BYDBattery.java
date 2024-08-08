package abstract_factory.byd;

import abstract_factory.domain.Battery;

public class BYDBattery implements Battery {

    private final String DESCRIPTION = "比亚迪的电池";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
