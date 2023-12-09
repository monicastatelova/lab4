import java.util.Random;

public class VehicleFactory {
    private final Random random;

    public VehicleFactory() {
        this.random = new Random();
    }

    public Vehicle createRandomVehicle(double x, double y) {
        int choice = random.nextInt(3);

        switch (choice) {
            case 0:
                return new Volvo240(x, y);
            case 1:
                return new Saab95(x, y);
            case 2:
                return new Scania(x, y);
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }
}