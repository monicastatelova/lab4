import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        VehicleController controller = new VehicleController();
        Model model = new Model();
        View view = new View(model, controller);

        model.addObserver(view);

        model.addVehicle(new Volvo240());
        model.addVehicle(new Saab95());
        model.addVehicle(new Scania());

        model.startTimer();
    }
}