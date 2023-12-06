import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements SimulationListener{

    private final VehicleControllerListener controllerListener;

    private static int gasAmount;

    DrawPanel drawPanel = new DrawPanel(WindowConfig.SCREEN_WIDTH, WindowConfig.SCREEN_HEIGHT - WindowConfig.BUTTON_HEIGHT);

    JPanel controlPanel = new JPanel(); //**

    JPanel gasPanel = new JPanel(); //**
    JSpinner gasSpinner = new JSpinner(); //**

    JLabel gasLabel = new JLabel("Amount of gas"); //**

    JButton gasButton = new JButton("Gas"); //**
    JButton brakeButton = new JButton("Brake"); //**
    JButton turboOnButton = new JButton("Saab Turbo on"); //**
    JButton turboOffButton = new JButton("Saab Turbo off"); //**
    JButton liftBedButton = new JButton("Scania Lift Bed"); //**
    JButton lowerBedButton = new JButton("Lower Lift Bed"); //**

    JButton startButton = new JButton("Start all cars"); //**
    JButton stopButton = new JButton("Stop all cars"); //**

    // Constructor
    public View(String framename, VehicleControllerListener controllerListener){
        this.controllerListener = controllerListener;
        initComponents(framename);
    }

    static int getGasAmount() {
        return gasAmount;
    }

    @Override
    public void updateSimulation(int[] xCoordinates, int[] yCoordinates) {
        drawPanel.moveit(xCoordinates, yCoordinates);
        drawPanel.repaint();
    }


    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(WindowConfig.SCREEN_WIDTH, WindowConfig.SCREEN_HEIGHT));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);



        SpinnerModel spinnerModel = //**
                new SpinnerNumberModel(0, //initial value //**
                        0, //min //**
                        100, //max //**
                        1);//step //**
        gasSpinner = new JSpinner(spinnerModel); //**
        gasSpinner.addChangeListener(new ChangeListener() { //**
            public void stateChanged(ChangeEvent e) { //**
                gasAmount = (int) ((JSpinner)e.getSource()).getValue(); //**

            }
        });


        gasPanel.setLayout(new BorderLayout()); //**
        gasPanel.add(gasLabel, BorderLayout.PAGE_START); //**
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END); //**

        this.add(gasPanel); //**

        controlPanel.setLayout(new GridLayout(2,4));  //**

        controlPanel.add(gasButton, 0); //**
        controlPanel.add(turboOnButton, 1);//**
        controlPanel.add(liftBedButton, 2);//**
        controlPanel.add(brakeButton, 3);//**
        controlPanel.add(turboOffButton, 4);//**
        controlPanel.add(lowerBedButton, 5);//**
        controlPanel.setPreferredSize(new Dimension((WindowConfig.SCREEN_WIDTH/2)+4, 200));//**
        this.add(controlPanel);//**
        controlPanel.setBackground(Color.CYAN); //**


        startButton.setBackground(Color.blue); //**
        startButton.setForeground(Color.green); //**
        startButton.setPreferredSize(new Dimension(WindowConfig.SCREEN_WIDTH/5-15,200)); //**
        this.add(startButton); //**


        stopButton.setBackground(Color.red); //**
        stopButton.setForeground(Color.black); //**
        stopButton.setPreferredSize(new Dimension(WindowConfig.SCREEN_WIDTH/5-15,200)); //**
        this.add(stopButton); //**

        gasButton.addActionListener(e -> controllerListener.gas(getGasAmount()));
        brakeButton.addActionListener(e -> controllerListener.brake(getGasAmount()));
        turboOnButton.addActionListener(e -> controllerListener.turboOn());
        turboOffButton.addActionListener(e -> controllerListener.turboOff());
        startButton.addActionListener(e -> controllerListener.startAllCars());
        stopButton.addActionListener(e -> controllerListener.stopAllCars());
        liftBedButton.addActionListener(e -> controllerListener.liftBed());
        lowerBedButton.addActionListener(e -> controllerListener.lowerBed());

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}