package MainApplication;

import MainApplication.Controller.VehicleController;
import MainApplication.Model.Model;
import MainApplication.View.View;

public class Application {

    public static void main(String[] args) {
        Model model = new Model();
        VehicleController controller = new VehicleController(model);
        View view = new View(model, controller);

        model.addObserver(view);
        model.startTimer();
    }
}