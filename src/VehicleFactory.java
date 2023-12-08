import java.util.Random;

public class VehicleFactory {
    private final Random random;

    public VehicleFactory() {
        this.random = new Random();
    }

    public Vehicle createRandomVehicle() {
        // Randomly choose a type of vehicle to create
        int choice = random.nextInt(3);

        switch (choice) {
            case 0:
                return new Volvo240();
            case 1:
                return new Saab95();
            case 2:
                return new Scania();
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }
}