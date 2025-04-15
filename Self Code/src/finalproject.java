import javax.swing.*;
import java.awt.*;
import java.util.*;

public class finalproject extends JPanel {
    int K; // number of clusters

    public finalproject(int K) {
        this.K = K;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Random randomcolor = new Random();
        int startX = 20;
        int startY = 20;
        int dotSize = 12;

        // First level: K clusters of 3 dots
        int[] centerYs = new int[K]; // to store midpoints for connection

        for (int i = 0; i < K; i++) {
            int clusterY = startY + i * 90;

            for (int j = 0; j < 3; j++) {
                g2d.setColor(new Color(randomcolor.nextInt(255), randomcolor.nextInt(255), randomcolor.nextInt(255))); //different color for each dots
                g2d.fillOval(startX, clusterY + j * 24, dotSize, dotSize);
            }

            // Pink box around cluster
            g2d.setColor(Color.PINK);
            int[] xPoints = {startX, startX + 30, startX + 30, startX};
            int[] yPoints = {clusterY, clusterY, clusterY + 72, clusterY + 72};
            g2d.drawPolygon(xPoints, yPoints, 4);

            // Connecting line to middle dot
            int centerY = clusterY + 36;
            centerYs[i] = centerY;
            g2d.setColor(Color.BLACK);
            g2d.drawLine(startX + 30, centerY, startX + 80, centerY);
        }

        // Middle dots
        dotSize += 5;
        for (int i = 0; i < K; i++) {
            g2d.setColor(new Color(randomcolor.nextInt(255), randomcolor.nextInt(255), randomcolor.nextInt(255)));
            g2d.fillOval(startX + 90, centerYs[i] - dotSize / 2, dotSize, dotSize);
        }

        // Box around middle-level nodes
        g2d.setColor(Color.PINK);
        int midBoxY = centerYs[0] - 36;
        int midBoxHeight = (centerYs[K - 1] + 36) - midBoxY;
        g2d.drawPolygon(
                new int[]{startX + 80, startX + 120, startX + 120, startX + 80},
                new int[]{midBoxY, midBoxY, midBoxY + midBoxHeight, midBoxY + midBoxHeight},
                4
        );

        // Connecting lines from middle-level to final merge node
        int mergeX = startX + 160;
        int mergeY = centerYs[0] + (centerYs[K - 1] - centerYs[0]) / 2;

        for (int i = 0; i < K; i++) {
            g2d.setColor(Color.BLACK);
            g2d.drawLine(startX + 120, centerYs[i], mergeX, mergeY);
        }

        // Final big dot
        dotSize += 5;
        g2d.setColor(new Color(randomcolor.nextInt(255), randomcolor.nextInt(255), randomcolor.nextInt(255)));
        g2d.fillOval(mergeX, mergeY - dotSize / 2, dotSize, dotSize);
    }

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null, "Enter number of clusters (K):", "Input K", JOptionPane.QUESTION_MESSAGE);
        int K = 3; // default
        try {
            K = Integer.parseInt(input);
            if (K < 1) K = 3;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Using default K = 3.");
        }

        JFrame frame = new JFrame("K Cluster Tree ");
        finalproject panel = new finalproject(K);

        frame.add(panel);
        frame.setSize(400, 150 + K * 90); //adjust height through K
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
