import javax.swing.*;
import java.awt.*;

public class ClusteringActivity3 extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Define VIBGYOR colors
        Color[] vibgyorColors = {
                Color.RED,
                Color.ORANGE,
                Color.YELLOW,
                Color.GREEN,
                Color.BLUE,
                new Color(75, 0, 130), // Indigo
                new Color(138, 43, 226) // Violet
        };

        // Simulate 3 clusters with different positions and colors
        int[][] cluster1 = {{60, 60}, {70, 80}, {65, 100}};
        int[][] cluster2 = {{150, 150}, {160, 170}, {145, 190}};
        int[][] cluster3 = {{250, 70}, {260, 90}, {255, 110}};

        // Draw cluster 1 - Red
        g2d.setColor(vibgyorColors[0]);
        for (int[] point : cluster1) {
            g2d.fillOval(point[0], point[1], 15, 15);
        }

        // Draw cluster 2 - Green
        g2d.setColor(vibgyorColors[3]);
        for (int[] point : cluster2) {
            g2d.fillOval(point[0], point[1], 15, 15);
        }

        // Draw cluster 3 - Blue
        g2d.setColor(vibgyorColors[4]);
        for (int[] point : cluster3) {
            g2d.fillOval(point[0], point[1], 15, 15);
        }

        // Optional: Add cluster bounding boxes
        g2d.setColor(Color.GRAY);
        g2d.drawRect(55, 55, 30, 50);   // Around cluster 1
        g2d.drawRect(140, 145, 30, 50); // Around cluster 2
        g2d.drawRect(245, 65, 30, 50);  // Around cluster 3
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clustering Activity");
        ClusteringActivity panel = new ClusteringActivity();
        frame.add(panel);
        frame.setSize(350, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
