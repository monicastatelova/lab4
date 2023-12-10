package MainApplication.Model.Vehicles;

import java.awt.*;

public class Saab95 extends Car {
    private boolean turboOn;

    public Saab95(double x, double y) {
        super(2, 125, Color.red,  4.8, x, y);
        turboOn = false;
    }

    public void setTurboOn() {
        turboOn = true;
    }

    public void setTurboOff() {
        turboOn = false;
    }

    public boolean isTurboOn() {
        return turboOn;
    }

    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }

}