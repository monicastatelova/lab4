package MainApplication.View;

import MainApplication.Model.Vehicles.Saab95;
import MainApplication.Model.Vehicles.Scania;
import MainApplication.Model.Vehicles.Vehicle;
import MainApplication.Model.Vehicles.Volvo240;
import MainApplication.Model.Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.List;


public class DrawPanel extends JPanel {

    private BufferedImage volvo240Image;
    private BufferedImage saab95Image;
    private BufferedImage scaniaImage;
    private Model model;

    public DrawPanel(int x, int y, Model model) {
        this.model = model;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        try {
            volvo240Image = ImageIO.read(DrawPanel.class.getResourceAsStream("/MainApplication/View/pics/Volvo240.jpg"));
            saab95Image = ImageIO.read(DrawPanel.class.getResourceAsStream("/MainApplication/View/pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/MainApplication/View/pics/Scania.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void moveit(double[] xCoordinates, double[] yCoordinates) {
        List<Vehicle> vehicles = this.model.getVehicleList();

        for (int i = 0; i < this.model.getVehicleList().size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            vehicle.setXPos(xCoordinates[i]);
            vehicle.setYPos(yCoordinates[i]);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Vehicle vehicle : this.model.getVehicleList()) {
            BufferedImage image = null;

            if (vehicle instanceof Saab95) {
                image = getSaab95Image();
            } else if (vehicle instanceof Volvo240) {
                image = getVolvo240Image();
            } else if (vehicle instanceof Scania) {
                image = getScaniaImage();
            }

            if (image != null) {
                g.drawImage(image, (int) vehicle.getXPos(), (int) vehicle.getYPos(), null);
            } else {
                System.err.println("Image not found for vehicle: " + vehicle.getClass().getSimpleName());
            }
        }
    }

    private BufferedImage getSaab95Image() {
        return saab95Image;
    }

    private BufferedImage getVolvo240Image() {
        return volvo240Image;
    }

    private BufferedImage getScaniaImage() {
        return scaniaImage;
    }

}