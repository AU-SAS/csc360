package clustering;

import javax.swing.*;
import java.awt.*;

/**
 * ClusterVisualization - Basic implementation for visualizing hierarchical clusters
 * Version 1.0: Initial setup with basic drawing capabilities
 */
public class ClusterVisualization extends JPanel {

    // Constants
    private static final int MARGIN = 50;
    private static final int DOT_SIZE = 10;
    private static final int VERTICAL_SPACING = 30;

    // Cluster data
    private final int[] clusterSizes = {50, 70, 15}; // Size of each cluster group G1, G2, G3
    private final String[] clusterLabels = {"G1", "G2", "G3"};
    private final Color[] clusterColors = {Color.RED, Color.BLUE, Color.GREEN};

    public ClusterVisualization() {
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the basic cluster points
        drawClusters(g2d);

        // Draw coordinate system
        drawCoordinateSystem(g2d);
    }

    private void drawClusters(Graphics2D g2d) {
        int startY = MARGIN;

        for (int i = 0; i < clusterLabels.length; i++) {
            g2d.setColor(clusterColors[i]);

            // Draw label
            g2d.drawString(clusterLabels[i], MARGIN - 30, startY + DOT_SIZE/2);

            // Draw cluster line
            g2d.drawLine(MARGIN - 10, startY + DOT_SIZE/2, MARGIN, startY + DOT_SIZE/2);

            // Draw cluster points
            for (int j = 0; j < 3; j++) { // Simplified: 3 points per cluster for now
                g2d.fillOval(MARGIN, startY, DOT_SIZE, DOT_SIZE);
                startY += VERTICAL_SPACING;
            }
        }
    }

    private void drawCoordinateSystem(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);

        // Draw X and Y axes
        int axisLength = 300;
        int xAxisY = getHeight() - MARGIN;
        int yAxisX = MARGIN;

        // Y-axis
        g2d.drawLine(yAxisX, MARGIN, yAxisX, xAxisY);
        g2d.drawString("Y", yAxisX - 15, MARGIN - 10);

        // X-axis
        g2d.drawLine(yAxisX, xAxisY, yAxisX + axisLength, xAxisY);
        g2d.drawString("X", yAxisX + axisLength + 10, xAxisY);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cluster Visualization v1.0");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new ClusterVisualization());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}