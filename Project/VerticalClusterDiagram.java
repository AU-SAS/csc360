import javax.swing.*;
import java.awt.*;

public class VerticalClusterDiagram extends JPanel {

    // Array of vibrant colors for the dots
    private static final Color[] DOT_COLORS = {
            Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE,
            new Color(75, 0, 130), // Indigo
            new Color(138, 43, 226), // Violet
            Color.MAGENTA, Color.CYAN, Color.PINK, Color.GRAY, Color.LIGHT_GRAY
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;

        int[][] clusterPositions = {{100}, {200}, {300}};  // X positions of the clusters (vertical alignment)
        int yStart = 80; // Initial Y position for the first dot
        int yGap = 40;   // Vertical gap between dots
        int dotRadius = 12; // Radius of the dots

        int colorIndex = 0; // Index to track the dot colors

        // Iterate through each cluster
        for (int clusterIndex = 0; clusterIndex < clusterPositions.length; clusterIndex++) {
            int clusterX = clusterPositions[clusterIndex][0];

            // Draw a vertical line for each cluster
            graphics.setColor(Color.DARK_GRAY);
            graphics.drawLine(clusterX, yStart - 20, clusterX, yStart + yGap * 3 + 20);

            // Draw 4 dots for each cluster, using the unique colors
            for (int i = 0; i < 4; i++) {
                int dotY = yStart + i * yGap;

                graphics.setColor(DOT_COLORS[colorIndex % DOT_COLORS.length]);
                graphics.fillOval(clusterX - dotRadius / 2, dotY - dotRadius / 2, dotRadius, dotRadius);
                colorIndex++;
            }
        }
    }

    public static void main(String[] args) {
        // Set up the JFrame for displaying the clustering diagram
        JFrame frame = new JFrame("Vertical Clusters with Colored Dots");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 300);
        frame.add(new VerticalClusterDiagram());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
