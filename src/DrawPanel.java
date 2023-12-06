import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class DrawPanel extends JPanel{
    // En HashMap som kopplar Point-objekt till BufferedImage-objekt
    public Map<Point, BufferedImage> imagePositions = new HashMap<>();

    // Metod för att flytta bildpositionerna baserat på givna x- och y-koordinater.
    public void moveit(int[] x, int[] y) {
        int i = 0;
        // Iterera över nycklarna (Point-objekten) i imagePositions.
        for (Point position : imagePositions.keySet()) {
            // Uppdatera x- och y-koordinaterna baserat på de givna arrayerna.
            position.x = x[i];
            position.y = y[i] + i * 100; // Justera y-koordinaten baserat på index.
            i++;
        }
    }

    // Konstruktor för DrawPanel, tar in bredd (x) och höjd (y) som argument.
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        try {
            // Lägg till tre bild- och positionsparen i imagePositions.
            imagePositions.put(new Point(0, 0), ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            imagePositions.put(new Point(0, 100), ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            imagePositions.put(new Point(0, 200), ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    // Metod för att rita komponenten.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Iterera över entrySet i imagePositions och rita varje bild på dess position.
        for (Map.Entry<Point, BufferedImage> entry : imagePositions.entrySet()) {
            Point position = entry.getKey();
            BufferedImage image = entry.getValue();
            g.drawImage(image, position.x, position.y, null);
        }

    }

}

