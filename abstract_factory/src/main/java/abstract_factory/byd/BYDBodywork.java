package abstract_factory.byd;

import abstract_factory.domain.Bodywork;

public class BYDBodywork implements Bodywork {

    private final String DESCRIPTION = "比亚迪的车身";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
