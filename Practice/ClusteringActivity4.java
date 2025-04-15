import javax.swing.*;
import java.awt.*;

public class ClusteringActivity4 extends JPanel {

    // Colors for all 12 dots (can be any vibrant set)
    private final Color[] dotColors = {
            Color.RED,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.BLUE,
            new Color(75, 0, 130), // Indigo
            new Color(138, 43, 226), // Violet
            Color.MAGENTA,
            Color.CYAN,
            Color.PINK,
            Color.GRAY,
            Color.LIGHT_GRAY
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int[][] clusterX = {{100}, {200}, {300}}; // X positions of each cluster (vertical lines)
        int yStart = 80;
        int yGap = 40;
        int radius = 12;

        int colorIndex = 0;

        for (int cluster = 0; cluster < clusterX.length; cluster++) {
            int x = clusterX[cluster][0];

            // Draw the vertical line
            g2d.setColor(Color.DARK_GRAY);
            g2d.drawLine(x, yStart - 20, x, yStart + yGap * 3 + 20);

            // Draw 4 dots per cluster with unique colors
            for (int i = 0; i < 4; i++) {
                int y = yStart + i * yGap;

                g2d.setColor(dotColors[colorIndex % dotColors.length]);
                g2d.fillOval(x - radius / 2, y - radius / 2, radius, radius);
                colorIndex++;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Vertical Clusters with Colored Dots");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 300);
        frame.add(new ClusteringActivity4());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
