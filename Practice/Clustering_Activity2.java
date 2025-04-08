import javax.swing.*;
import java.awt.*;

public class Clustering_Activity2 extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLUE);

        // Cluster 1
        int x1 = 150, y1 = 80;
        int x2 = 140, y2 = 100;
        int x3 = 160, y3 = 100;

        // Cluster 2
        int x4 = 150, y4 = 180;
        int x5 = 140, y5 = 200;
        int x6 = 160, y6 = 200;

        // Cluster 3
        int x7 = 150, y7 = 280;
        int x8 = 140, y8 = 300;
        int x9 = 160, y9 = 300;

        g2d.fillOval(x1 - 5, y1 - 5, 10, 10);
        g2d.fillOval(x2 - 5, y2 - 5, 10, 10);
        g2d.fillOval(x3 - 5, y3 - 5, 10, 10);

        g2d.fillOval(x4 - 5, y4 - 5, 10, 10);
        g2d.fillOval(x5 - 5, y5 - 5, 10, 10);
        g2d.fillOval(x6 - 5, y6 - 5, 10, 10);

        g2d.fillOval(x7 - 5, y7 - 5, 10, 10);
        g2d.fillOval(x8 - 5, y8 - 5, 10, 10);
        g2d.fillOval(x9 - 5, y9 - 5, 10, 10);

        g2d.setColor(Color.DARK_GRAY); // Changed to DARK GRAY for a subtle but clear outline

        // Circles around clusters
        g2d.drawOval(125, 65, 50, 50); // First cluster circle
        g2d.drawOval(125, 165, 50, 50); // Second cluster circle
        g2d.drawOval(125, 265, 50, 50); // Third cluster circle
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clustering_Activity2");
        Clustering_Activity2 panel = new Clustering_Activity2();
        frame.add(panel);
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
