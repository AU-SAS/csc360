package project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This program draws a simple hierarchical clustering diagram.
 * It starts with K clusters entered by the user, then merges them
 * step by step into one final cluster.
 */
public class Draw_Cluster extends JPanel {

    // Number of starting clusters
    int totalClusters;

    // Random generator for colors
    Random random = new Random();

    // Constructor to receive K clusters from user
    public Draw_Cluster(int k) {
        this.totalClusters = k;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Use Graphics2D for better drawing
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2)); // set line thickness

        // Settings for drawing
        int initialX = 50;               // starting x position of first layer
        int ySpacing = 60;               // vertical spacing between clusters
        int clusterSize = 20;            // diameter of each cluster circle

        // This list stores all cluster points in the current layer
        ArrayList<Point> currentClusters = new ArrayList<>();

        // === Step 1: Draw initial K clusters ===
        for (int i = 0; i < totalClusters; i++) {
            int y = 50 + i * ySpacing;

            // Random color for each initial cluster
            Color clusterColor = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            g2.setColor(clusterColor);

            // Draw circle
            g2.fillOval(initialX, y, clusterSize, clusterSize);

            // Save center point of the circle
            int centerX = initialX + clusterSize / 2;
            int centerY = y + clusterSize / 2;
            currentClusters.add(new Point(centerX, centerY));
        }

        // === Step 2: Merge clusters until only one remains ===
        int layerX = initialX;

        while (currentClusters.size() > 1) {
            ArrayList<Point> nextClusters = new ArrayList<>();
            layerX += 100; // Move next layer to the right

            for (int i = 0; i < currentClusters.size(); i += 2) {
                // If we have at least two clusters to merge
                if (i + 1 < currentClusters.size()) {
                    Point clusterA = currentClusters.get(i);
                    Point clusterB = currentClusters.get(i + 1);

                    // Midpoint Y for new parent cluster
                    int mergedY = (clusterA.y + clusterB.y) / 2;
                    int mergedX = layerX;

                    // Draw lines from children to parent
                    g2.setColor(Color.BLUE);
                    g2.drawLine(clusterA.x, clusterA.y, mergedX, clusterA.y); // left child
                    g2.drawLine(clusterB.x, clusterB.y, mergedX, clusterB.y); // right child
                    g2.drawLine(mergedX, clusterA.y, mergedX, clusterB.y);    // vertical join

                    // Draw parent cluster as black circle
                    g2.setColor(Color.BLACK);
                    g2.fillOval(mergedX - clusterSize / 2, mergedY - clusterSize / 2, clusterSize, clusterSize);

                    // Save new cluster's center
                    nextClusters.add(new Point(mergedX, mergedY));
                } else {
                    // If one cluster is left unpaired, carry it to next layer
                    nextClusters.add(currentClusters.get(i));
                }
            }

            // Update the layer for the next round of merges
            currentClusters = nextClusters;
        }
    }

    public static void main(String[] args) {
        // === Get number of clusters from user ===
        String input = JOptionPane.showInputDialog("Enter number of clusters (K):");

        // Basic input validation
        int k;
        try {
            k = Integer.parseInt(input);
            if (k < 2) {
                JOptionPane.showMessageDialog(null, "Please enter a number greater than 1.");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
            return;
        }

        // === Create window and draw the tree ===
        JFrame frame = new JFrame("Hierarchical Clustering Diagram (K = " + k + ")");
        Draw_Cluster panel = new Draw_Cluster(k);
        frame.add(panel);
        frame.setSize(800, 600); // set window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
