package project;

import javax.swing.*;
import java.awt.*;

public class Draw_Cluster extends JPanel {

    int totalClusters;

    public Draw_Cluster(int k) {
        this.totalClusters = k;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int x = 50;
        int ySpacing = 60;
        int size = 20;

        // Store the center points of current clusters
        int[] centerX = new int[totalClusters];
        int[] centerY = new int[totalClusters];

        // Step 1: Draw initial clusters (colored red/green/blue repeating)
        for (int i = 0; i < totalClusters; i++) {
            int y = 50 + i * ySpacing;

            // Set basic color pattern
            if (i % 3 == 0) g2.setColor(Color.RED);
            else if (i % 3 == 1) g2.setColor(Color.GREEN);
            else g2.setColor(Color.BLUE);

            g2.fillOval(x, y, size, size);

            // Save centers
            centerX[i] = x + size / 2;
            centerY[i] = y + size / 2;
        }

        // Step 2: Merge clusters step by step
        int count = totalClusters;
        int layer = 0;

        while (count > 1) {
            int[] nextX = new int[(count + 1) / 2];
            int[] nextY = new int[(count + 1) / 2];

            int newX = x + 100;

            int index = 0;
            for (int i = 0; i < count; i += 2) {
                if (i + 1 < count) {
                    int y1 = centerY[i];
                    int y2 = centerY[i + 1];
                    int midY = (y1 + y2) / 2;

                    // Draw merge lines
                    g2.setColor(Color.BLUE);
                    g2.drawLine(centerX[i], y1, newX, y1);
                    g2.drawLine(centerX[i + 1], y2, newX, y2);
                    g2.drawLine(newX, y1, newX, y2);

                    // Draw new merged cluster
                    g2.setColor(Color.BLACK);
                    g2.fillOval(newX - size / 2, midY - size / 2, size, size);

                    nextX[index] = newX;
                    nextY[index] = midY;
                } else {
                    // Carry forward unpaired cluster
                    nextX[index] = centerX[i];
                    nextY[index] = centerY[i];
                }
                index++;
            }

            // Update current clusters
            count = index;
            centerX = nextX;
            centerY = nextY;
            x = newX;
            layer++;
        }
    }

    public static void main(String[] args) {
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

        JFrame frame = new JFrame("Simple Hierarchical Clustering (K = " + k + ")");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new Draw_Cluster(k));
        frame.setVisible(true);
    }
}
