import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Model model = new Model();
        VehicleController controller = new VehicleController(model);
        View view = new View(model, controller);

        model.addObserver(view);
        model.startTimer();
    }
}