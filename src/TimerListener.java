import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TimerListener implements ActionListener {

    View frame;
    ArrayList<Vehicle> vehicles = new ArrayList<>();

    public void setViewAndViewList(View frame, ArrayList<Vehicle> vehicles) {
        this.frame = frame;
        this.vehicles = vehicles;
    }

    public void actionPerformed(ActionEvent e) {
        if (frame != null && vehicles != null) {
            int[] xCoordinates = new int[vehicles.size()];
            int[] yCoordinates = new int[vehicles.size()];

            for (int i = 0; i < vehicles.size(); i++) {
                Vehicle vehicle = vehicles.get(i);
                double initialSpeed = vehicle.getCurrentSpeed();
                if (hitWall(vehicle)) {
                    vehicle.stopEngine();
                    vehicle.turnRight();
                    vehicle.turnRight();
                    vehicle.startEngine();
                    vehicle.setCurrentSpeed(initialSpeed);
                }
                vehicle.move();
                xCoordinates[i] = (int) Math.round(vehicle.getXPos());
                yCoordinates[i] = (int) Math.round(vehicle.getYPos());
                frame.drawPanel.moveit(xCoordinates, yCoordinates);
                frame.drawPanel.repaint();
            }
        }
    }

    boolean hitWall(Vehicle vehicle){
        int screenWidth = WindowConfig.SCREEN_WIDTH - WindowConfig.VEHICLE_WIDTH;
        int screenHeight = WindowConfig.SCREEN_HEIGHT; //för gröna boxen
        double vehicleXPos = vehicle.getXPos();
        double vehicleYPos = vehicle.getYPos();
        return (vehicleXPos < 0 || vehicleXPos > screenWidth || vehicleYPos < 0 || vehicleYPos > screenHeight);
    }
}
