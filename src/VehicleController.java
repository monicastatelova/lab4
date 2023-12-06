import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VehicleController {

    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    private TimerListener timerListener;
    public View frame;
    ArrayList<Vehicle> vehicles = new ArrayList<>();

    public static void main(String[] args) {
        // Instance of this class
        VehicleController cc = new VehicleController();

        cc.vehicles.add(new Volvo240()); //cc.vehicles.add(new Volvo240(0,0));
        cc.vehicles.add(new Saab95()); //cc.vehicles.add(new Saab95(0, 100));
        cc.vehicles.add(new Scania()); //cc.vehicles.add(new Scania(0, 200));

        // Start a new view and send a reference of self
        cc.frame = new View("CarSim 1.0", cc);

        cc.timerListener = new TimerListener();
        cc.timerListener.setViewAndViewList(cc.frame, cc.vehicles);
        cc.timer = new Timer(cc.delay, cc.timerListener);

        // Start the timer
        cc.timer.start();
    }

    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles
        ) {
            vehicle.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles
        ) {
            vehicle.brake(brake);
        }
    }

    void turboOn(){
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95) {
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }

    void turboOff(){
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95) {
                ((Saab95) vehicle).setTurboOff();
            }
        }
    }

    void startAllCars(){
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