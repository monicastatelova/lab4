import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements SimulationObserver {
    private final VehicleController controller;
    private final DrawPanel drawPanel;
    private final JLabel gasLabel = new JLabel("Amount of gas");

    public View(Model model, VehicleController controller){
        this.controller = controller;
        //Initialiserar en panel med dimensioner för att rita fordonen
        this.drawPanel = new DrawPanel(WindowConfig.SCREEN_WIDTH, WindowConfig.SCREEN_HEIGHT - WindowConfig.BUTTON_HEIGHT);
        //Initialiserar komponenterna i gränssnittet
        initComponents("Vehicle Simulation");
    }

    @Override
    public void updateSimulation(double[] xCoordinates, double[] yCoordinates) {
        //Uppdatera ritpanelen med nya koordinater och uppdatera gränssnittet
        drawPanel.moveit(xCoordinates, yCoordinates);
        drawPanel.repaint();
    }

    private void initComponents(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(WindowConfig.SCREEN_WIDTH, WindowConfig.SCREEN_HEIGHT));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        //Lägger till ritpanelen till fönstret
        this.add(this.drawPanel);

        //Skapar och lägger till gaspanelen till fönstret
        JPanel gasPanel = controller.createGasPanel(gasLabel);
        this.add(gasPanel);

        //Skapar och lägger till kontrollpanelen till fönstret med knappar
        JPanel controlPanel = controller.createControlPanel();
        this.add(controlPanel);

        //Lägger till start- och stoppknappar till fönstret
        this.add(controller.createStartButton());
        this.add(controller.createStopButton());

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