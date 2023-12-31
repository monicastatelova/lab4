package MainApplication.Model;

import MainApplication.Model.Vehicles.Car;

import java.util.ArrayList;
import java.util.List;

public class CarWorkshop <T extends Car>{//T är av typen Vehicles.Car

    private final int maxCapacity;
    private final List<T> carsInWorkshop;

    public CarWorkshop(int maxCapacity){
        this.maxCapacity = maxCapacity;
        this.carsInWorkshop = new ArrayList<>();

    }

    public List<T> getCarsInWorkshop() {
        return carsInWorkshop;
    }

    public void load(T car){
        if (carsInWorkshop.size()<maxCapacity) {
            carsInWorkshop.add(car);
        }
    }

    public void unload(T car){
        if(!carsInWorkshop.isEmpty()){
            carsInWorkshop.remove(car);
        }
    }
}