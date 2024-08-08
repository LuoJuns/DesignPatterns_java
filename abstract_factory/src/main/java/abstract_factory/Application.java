package abstract_factory;

import abstract_factory.byd.BYDCarFactory;
import abstract_factory.tesla.TeslaCarFactory;

public class Application {
    public static void main(String[] args) {
        CarStore store = new CarStore();
        store.getCar(new BYDCarFactory());
        store.getCar(new TeslaCarFactory());
    }
}
