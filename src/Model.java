import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.List;

public class Model {
    private final List<Vehicle> vehicles;
    private final List<SimulationObserver> observers;
    private Timer timer;

    public Model() {
        this.vehicles = new ArrayList<>();
        this.observers = new ArrayList<>(); //observatörer
        initializeTimer();
    }

    private void initializeTimer() {
        //Skapar och startar en timer för att regelbundet uppdatera fordonens positioner
        int delay = 50;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateVehiclePositions();
            }
        };
        timer = new Timer(delay, taskPerformer);
        timer.start();
    }

    public void addObserver(SimulationObserver observer) {
        //Lägger till observatör till listan
        observers.add(observer);
    }

    private void notifyObservers(double[] xCoordinates, double[] yCoordinates) {
        //Meddelar alla observatörer om uppdaterade koordinater
        for (SimulationObserver observer : observers) {
            observer.updateSimulation(xCoordinates, yCoordinates);
        }
    }


    public void addVehicle(Vehicle vehicle) {
        //Lägger till ett fordon till listan
        vehicles.add(vehicle);
    }

    public void updateVehiclePositions() {
        //Uppdaterar positionerna för alla fordon
        double[] xCoordinates = new double[vehicles.size()];
        double[] yCoordinates = new double[vehicles.size()];

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            double initialSpeed = vehicle.getCurrentSpeed();

            if (hitWall(vehicle)) {
                handleHitWall(vehicle, initialSpeed);
            }

            vehicle.move();
            xCoordinates[i] = Math.round(vehicle.getXPos());
            yCoordinates[i] = Math.round(vehicle.getYPos());
        }

        //Notify the view to update the simulation with new coordinates
        notifyObservers(xCoordinates, yCoordinates);
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    private void handleHitWall(Vehicle vehicle, double initialSpeed) {
        //Hanterar kollision med väggen
        vehicle.stopEngine();
        vehicle.turnRight();
        vehicle.turnRight();
        vehicle.startEngine();
        vehicle.setCurrentSpeed(initialSpeed);
    }

    private boolean hitWall(Vehicle vehicle) {
        //Kontrollerar om fordonet kolliderar med väggen
        int screenWidth = WindowConfig.SCREEN_WIDTH - WindowConfig.VEHICLE_WIDTH;
        int screenHeight = WindowConfig.SCREEN_HEIGHT;
        double vehicleXPos = vehicle.getXPos();
        double vehicleYPos = vehicle.getYPos();
        return (vehicleXPos < 0 || vehicleXPos > screenWidth || vehicleYPos < 0 || vehicleYPos > screenHeight);
    }

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

    public void addCar(){
        if (getNumberOfCars() <10){
            Vehicle newVehicle = vehicleFactory.createRandomVehicle();
            addVehicle(newVehicle);
        }

    }

    public void removeCar(){
        int lastIndex = vehicles.size() - 1;

        if (lastIndex >= 0) {
            vehicles.remove(lastIndex);
        }
    }
}