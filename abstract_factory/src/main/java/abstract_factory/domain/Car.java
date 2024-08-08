package abstract_factory.domain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Car {
    /**
     * 轮胎
     */
    private Wheels wheels;

    /**
     * 车身
     */
    private Bodywork bodywork;

    public Wheels getWheels() {
        return wheels;
    }

    public void setWheels(Wheels wheels) {
        this.wheels = wheels;
    }

    public Bodywork getBodywork() {
        return bodywork;
    }

    public void setBodywork(Bodywork bodywork) {
        this.bodywork = bodywork;
    }
}
