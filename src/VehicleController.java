import javax.swing.*;
import java.awt.*;

public class VehicleController {

    private final Model model;
    private int gasAmount;
    private final JButton gasButton = new JButton("Gas");
    private final JButton brakeButton = new JButton("Brake");
    private final JButton turboOnButton = new JButton("Saab Turbo on");
    private final JButton turboOffButton = new JButton("Saab Turbo off");
    private final JButton liftBedButton = new JButton("Scania Lift Bed");
    private final JButton lowerBedButton = new JButton("Lower Lift Bed");
    private final JButton startButton = new JButton("Start all cars");
    private final JButton stopButton = new JButton("Stop all cars");

    private final JButton addCarButton = new JButton("Add car");
    private final JButton removeCarButton =new JButton("Remove Car");

    private final SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
    private JSpinner gasSpinner;

    public VehicleController(Model model) {
        this.model = model;
        this.gasSpinner = new JSpinner(this.spinnerModel);
        setupActionListeners();
    }

    public int getGasAmount() {
        return this.gasAmount;
    }

    private void setupActionListeners() {
        //Lambda för att lägga till actionlisteners för knapparna
        gasButton.addActionListener(e -> model.gas(getGasAmount()));
        brakeButton.addActionListener(e -> model.brake(getGasAmount()));
        turboOnButton.addActionListener(e -> model.turboOn());
        turboOffButton.addActionListener(e -> model.turboOff());
        startButton.addActionListener(e -> model.startAllCars());
        stopButton.addActionListener(e -> model.stopAllCars());
        liftBedButton.addActionListener(e -> model.liftBed());
        lowerBedButton.addActionListener(e -> model.lowerBed());
        addCarButton.addActionListener(e-> model.addCar()); //lägg till dessa
        removeCarButton.addActionListener(e-> model.removeCar()); // lägg till detta

        gasSpinner.addChangeListener(e -> {
            gasAmount = (int) gasSpinner.getValue();
        });
    }

    public JPanel createControlPanel() {
        //Skapa en ControlPanel med knappar för att styra fordonen
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.add(addCarButton, 6);
        controlPanel.add(removeCarButton, 7);
        controlPanel.setPreferredSize(new Dimension(WindowConfig.SCREEN_WIDTH / 2 + 5, WindowConfig.BUTTON_HEIGHT));
        return controlPanel;
    }

    public JPanel createGasPanel(JLabel gasLabel) {
        //Skapa en GasPanel för gasinställningar med spinner
        JPanel gasPanel = new JPanel();
        gasPanel.setLayout(new BorderLayout());
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(e -> {
            gasAmount = (int) gasSpinner.getValue();
        });
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);
        return gasPanel;
    }

    public JButton createStartButton() {
        //Skapa en startknapp
        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(WindowConfig.SCREEN_WIDTH / 5 - 15, WindowConfig.BUTTON_HEIGHT));
        return startButton;
    }

    public JButton createStopButton() {
        //Skapa en stoppknapp
        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(WindowConfig.SCREEN_WIDTH / 5 - 15, WindowConfig.BUTTON_HEIGHT));
        return stopButton;
    }
}