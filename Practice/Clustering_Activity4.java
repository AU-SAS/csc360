import javax.swing.*;
import java.awt.*;

public class Clustering_Activity4 extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        Color[] vibgyorColors = {
                Color.RED,
                Color.ORANGE,
                Color.YELLOW,
                Color.GREEN,
                Color.BLUE,
                new Color(75, 0, 130),  // Indigo (custom RGB value)
                new Color(138, 43, 226) // Violet (custom RGB value)
        };

        // Cluster 1
        g2d.setColor(vibgyorColors[0]);
        g2d.fillOval(100, 50, 10, 10);
        g2d.fillOval(120, 70, 10, 10);
        g2d.fillOval(80, 70, 10, 10);
        g2d.drawLine(105, 55, 125, 75);
        g2d.drawLine(105, 55, 85, 75);
        g2d.drawLine(85, 75, 125, 75);

        // Cluster 2
        g2d.setColor(vibgyorColors[1]);
        g2d.fillOval(200, 50, 10, 10);
        g2d.fillOval(220, 70, 10, 10);
        g2d.fillOval(180, 70, 10, 10);
        g2d.drawLine(205, 55, 225, 75);
        g2d.drawLine(205, 55, 185, 75);
        g2d.drawLine(185, 75, 225, 75);

        // Cluster 3
        g2d.setColor(vibgyorColors[2]);
        g2d.fillOval(150, 150, 10, 10);
        g2d.fillOval(170, 170, 10, 10);
        g2d.fillOval(130, 170, 10, 10);
        g2d.drawLine(155, 155, 175, 175);
        g2d.drawLine(155, 155, 135, 175);
        g2d.drawLine(135, 175, 175, 175);

        // Cluster 4
        g2d.setColor(vibgyorColors[3]);
        g2d.fillOval(250, 150, 10, 10);
        g2d.fillOval(270, 170, 10, 10);
        g2d.fillOval(230, 170, 10, 10);
        g2d.drawLine(255, 155, 275, 175);
        g2d.drawLine(255, 155, 235, 175);
        g2d.drawLine(235, 175, 275, 175);

        // Cluster 5
        g2d.setColor(vibgyorColors[4]);
        g2d.fillOval(100, 250, 10, 10);
        g2d.fillOval(120, 270, 10, 10);
        g2d.fillOval(80, 270, 10, 10);
        g2d.drawLine(105, 255, 125, 275);
        g2d.drawLine(105, 255, 85, 275);
        g2d.drawLine(85, 275, 125, 275);

        // Cluster 6
        g2d.setColor(vibgyorColors[5]);
        g2d.fillOval(200, 250, 10, 10);
        g2d.fillOval(220, 270, 10, 10);
        g2d.fillOval(180, 270, 10, 10);
        g2d.drawLine(205, 255, 225, 275);
        g2d.drawLine(205, 255, 185, 275);
        g2d.drawLine(185, 275, 225, 275);

        // Cluster 7
        g2d.setColor(vibgyorColors[6]);
        g2d.fillOval(300, 250, 10, 10);
        g2d.fillOval(320, 270, 10, 10);
        g2d.fillOval(280, 270, 10, 10);
        g2d.drawLine(305, 255, 325, 275);
        g2d.drawLine(305, 255, 285, 275);
        g2d.drawLine(285, 275, 325, 275);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clustering_Activity4");
        Clustering_Activity4 panel = new Clustering_Activity4();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
