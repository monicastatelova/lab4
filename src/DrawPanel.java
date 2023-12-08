import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class DrawPanel extends JPanel {

    //kolla vilken typ av bil och sedan lägga till bild
    //position i konstruktor
    //koppla biltyp till bild

    public Map<Position, BufferedImage> imagePositions = new HashMap<>(); //Lagra positioner och bilder för varje fordon

    public void moveit(double[] x, double[] y) {
        //Flyttar fordonens positioner baserat på nya x- och y-koordinater
        int i = 0;
        for (Position position : imagePositions.keySet()) {
            position.setXPos(x[i]);
            position.setYPos(y[i] + i * 100);
            i++;
        }
    }

    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        try {
            //Lägger till bilder och startposition för varje fordon
            imagePositions.put(new Position(0, 0, ""), ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            imagePositions.put(new Position(0, 100, ""), ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            imagePositions.put(new Position(0, 200, ""), ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addPosition(){

    }

    public void addImage(){

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Ritar ut fordonen
        for (Position position : imagePositions.keySet()) {
            BufferedImage image = imagePositions.get(position);
            g.drawImage(image, (int) position.getXPos(), (int) position.getYPos(), null);
        }
    }
}