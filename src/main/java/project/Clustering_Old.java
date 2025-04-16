package project;


import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Clustering_Old extends JPanel {

    int K = 3; // change this to modify number of clusters

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // General settings
        int circleSize = 15;
        int boxWidth = 50;
        int boxHeight = 80;
        int startX = 30;
        int spacingY = 30;
        int spacingX = 100;

        Random rand = new Random();

        // === Layer 1: Draw initial clusters ===
        int[] clusterCentersY = new int[K];

        for (int i = 0; i < K; i++) {
            int y = 40 + i * (boxHeight + spacingY);
            int x = startX;

            // Draw box around the cluster
            g2.setColor(Color.BLACK);
            g2.drawRect(x, y, boxWidth, boxHeight);

            // Draw small circles inside the cluster box
            for (int j = 0; j < 3; j++) {
                int cy = y + 10 + j * 20;
                Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
                g2.setColor(randomColor);
                g2.fillOval(x + 15, cy, circleSize, circleSize);
            }

            clusterCentersY[i] = y + boxHeight / 2;
        }

        // === Layer 2: Merge into parent clusters ===
        int mergeX1 = startX + spacingX;
        int midY1 = (clusterCentersY[0] + clusterCentersY[1]) / 2;
        int midY2 = clusterCentersY[2];

        // Draw lines from clusters to next layer
        g2.setColor(Color.BLUE);
        g2.drawLine(startX + boxWidth,
                clusterCentersY[0], mergeX1, clusterCentersY[0]);
        g2.drawLine(startX + boxWidth,
                clusterCentersY[1], mergeX1, clusterCentersY[1]);
        g2.drawLine(mergeX1,
                clusterCentersY[0], mergeX1, clusterCentersY[1]);

        g2.drawLine(startX + boxWidth,
                clusterCentersY[2], mergeX1, clusterCentersY[2]);

        // Draw parent boxes
        g2.setColor(Color.BLACK);
        g2.drawRect(mergeX1, midY1 - boxHeight / 2, boxWidth, boxHeight);
        g2.drawRect(mergeX1, midY2 - boxHeight / 2, boxWidth, boxHeight);

        // Draw circles inside parent clusters
        for (int i = 0; i < 2; i++) {
            int baseY = (i == 0) ? midY1 : midY2;
            for (int j = 0; j < 2; j++) {
                int cy = baseY - 20 + j * 25;
                g2.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
                g2.fillOval(mergeX1 + 15, cy, circleSize + 2, circleSize + 2);
            }
        }

        // === Final merge into one root ===
        int finalX = mergeX1 + spacingX;
        int finalY = (midY1 + midY2) / 2;

        g2.setColor(Color.BLUE);
        g2.drawLine(mergeX1 + boxWidth, midY1, finalX, midY1);
        g2.drawLine(mergeX1 + boxWidth, midY2, finalX, midY2);
        g2.drawLine(finalX, midY1, finalX, midY2);

        g2.setColor(Color.BLACK);
        g2.drawRect(finalX, finalY - boxHeight / 2, boxWidth, boxHeight);

        g2.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        g2.fillOval(finalX + 15, finalY - 10, circleSize + 4, circleSize + 4);
    }

    public static void main(String[] args) {
        // Set up the GUI frame
        JFrame frame = new JFrame("Simple Clustering Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new Clustering_Old());
        frame.setVisible(true);
    }
}
