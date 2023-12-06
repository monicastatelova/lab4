import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.HashMap;
// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // To keep track of a singel cars position
    public ArrayList<Point> vehiclePositions = new ArrayList<>();
    public ArrayList<BufferedImage> vehicleImages = new ArrayList<>();

    //public HashMap<Point, BufferedImage> imagePositions = new HashMap<>();


    public void moveit(int[] x, int[] y) {
        for (int i = 0; i < vehiclePositions.size(); i++) {
            vehiclePositions.get(i).x = x[i];
            vehiclePositions.get(i).y = y[i]+i*100;
        }
    }

    // Initializes the panel and reads the images

    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        try {

            vehicleImages.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            vehicleImages.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            vehicleImages.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));


        } catch (IOException ex)
        {
            ex.printStackTrace();
        }


        for(int i = 0; i < vehicleImages.size(); i++) {
            vehiclePositions.add(new Point(0, i * 100));
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < vehicleImages.size(); i++) {
            g.drawImage(vehicleImages.get(i), vehiclePositions.get(i).x, vehiclePositions.get(i).y, null);
        }

    }
}

