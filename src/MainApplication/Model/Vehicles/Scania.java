package MainApplication.Model.Vehicles;

import java.awt.*;

public class Scania extends Truck {
    private double angleLift;

    public Scania(double x, double y){
        super(2, 100, Color.blue, 25, x, y, false);
        this.angleLift = angleLift;
    }

    public double getAngleLift(){
        return this.angleLift;
    }

    public void setAnglePlatform(double angle) {
        if (!isMoving() && angle > 0 && angle <= 70) {
            this.angleLift = angle;
            setIsLiftUp(true);

        }else if (angle == 0 && getCurrentSpeed() == 0){
            setIsLiftUp(false);

        }else
            this.angleLift = 0;

    }

}