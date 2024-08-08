package abstract_factory.tesla;

import abstract_factory.domain.Bodywork;

public class TeslaBodywork implements Bodywork {

    private final String DESCRIPTION = "特斯拉的车身";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
