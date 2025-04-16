package project;

import javax.swing.*;
import java.awt.*;

// This class handles drawing the clusters
public class Draw_Cluster extends JPanel {

    int totalClusters;

    // Main method
    public static void main(String[] args) {
        // Ask the user how many clusters they want to start with
        String input = JOptionPane.showInputDialog("Enter number of clusters (K):");
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

        // Set up the window to show the clustering visualization
        JFrame frame = new JFrame("Simple Hierarchical Clustering (K = " + k + ")");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new Draw_Cluster(k)); // Add the drawing panel
        frame.setVisible(true); // Display the window
    }

    // Constructor to store the number of clusters
    public Draw_Cluster(int k) {
        this.totalClusters = k;
    }

    // This method handles all the drawing logic
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int x = 50; // Starting horizontal position for clusters
        int ySpacing = 60; // Space between clusters vertically
        int size = 20; // Size of each cluster circle

        // Store the center points of the current clusters
        int[] centerX = new int[totalClusters];
        int[] centerY = new int[totalClusters];

        // Draw the initial set of clusters using red, green, and blue
        for (int i = 0; i < totalClusters; i++) {
            int y = 50 + i * ySpacing;

            // Pick a color based on cluster index
            if (i % 3 == 0) g2.setColor(Color.RED);
            else if (i % 3 == 1) g2.setColor(Color.GREEN);
            else g2.setColor(Color.BLUE);

            g2.fillOval(x, y, size, size);

            // Save the center position of each cluster
            centerX[i] = x + size / 2;
            centerY[i] = y + size / 2;
        }

        int count = totalClusters; // Track how many clusters exist at the current step
        int layer = 0; // Keep track of how many layers of merging have happened

        // Keep merging clusters until only one remains
        while (count > 1) {
            int[] nextX = new int[(count + 1) / 2]; // Store centers for the next layer
            int[] nextY = new int[(count + 1) / 2];

            int newX = x + 100; // Shift right for the new layer

            int index = 0; // Index for the next set of merged clusters
            for (int i = 0; i < count; i += 2) {
                if (i + 1 < count) {
                    // Merge two clusters together and draw connection lines
                    int y1 = centerY[i];
                    int y2 = centerY[i + 1];
                    int midY = (y1 + y2) / 2;

                    // Draw horizontal lines from each cluster to the new merge point
                    g2.setColor(Color.BLUE);
                    g2.drawLine(centerX[i], y1, newX, y1);
                    g2.drawLine(centerX[i + 1], y2, newX, y2);

                    // Connect the horizontal lines with a vertical line
                    g2.drawLine(newX, y1, newX, y2);

                    // Draw the new merged cluster as a black circle
                    g2.setColor(Color.BLACK);
                    g2.fillOval(newX - size / 2, midY - size / 2, size, size);

                    // Save the new center
                    nextX[index] = newX;
                    nextY[index] = midY;
                } else {
                    // If there's an odd cluster left, carry it to the next round unchanged
                    nextX[index] = centerX[i];
                    nextY[index] = centerY[i];
                }
                index++;
            }

            // Move to the next round of merging
            count = index;
            centerX = nextX;
            centerY = nextY;
            x = newX;
            layer++;
        }
    }
}
