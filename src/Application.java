import javax.swing.*;

public class Application {

    public static final int delay = 50;
    public static Timer timer = new Timer(delay, new TimerListener());
    public static TimerListener timerListener;
    public static VehicleController cc = new VehicleController();
    public static View frame;

    public static void main(String[] args) {

        cc.vehicles.add(new Volvo240());
        cc.vehicles.add(new Saab95());
        cc.vehicles.add(new Scania());

        frame = new View("CarSim 1.0", cc);

        timerListener = new TimerListener();
        timerListener.setViewAndViewList(frame, cc.vehicles);
        timer = new Timer(delay, timerListener);

        timer.start();
    }
}