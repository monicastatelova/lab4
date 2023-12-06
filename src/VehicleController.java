import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VehicleController implements VehicleControllerListener {
    ArrayList<Vehicle> vehicles = new ArrayList<>();

    //knappar med actionlisteners



    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles
        ) {
            vehicle.gas(gas);
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles
        ) {
            vehicle.brake(brake);
        }
    }

    public void turboOn(){
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95) {
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }

    public void turboOff(){
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95) {
                ((Saab95) vehicle).setTurboOff();
            }
        }
    }

    public void startAllCars(){
        for (Vehicle vehicle : vehicles) {
            vehicle.startEngine();
        }
    }

    public void stopAllCars(){
        for (Vehicle vehicle : vehicles) {
            vehicle.stopEngine();
        }
    }

    public void liftBed(){
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Truck)
                ((Truck) vehicle).setIsLiftUp(true);
        }
    }

    public void lowerBed(){
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Truck)
                ((Truck) vehicle).setIsLiftUp(false);
        }
    }

}