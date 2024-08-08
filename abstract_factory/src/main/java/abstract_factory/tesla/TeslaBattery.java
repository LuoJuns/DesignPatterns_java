package abstract_factory.tesla;

import abstract_factory.domain.Battery;

public class TeslaBattery implements Battery {

    private final String DESCRIPTION = "特斯拉的电池";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
