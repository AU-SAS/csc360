import javax.swing.*;
import java.awt.*;

public class Clusteringactivity2 extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Define rainbow colors
        Color[] rainbowColors = {
                Color.RED, Color.ORANGE, Color.YELLOW,
                Color.GREEN, Color.BLUE, new Color(75, 0, 130), new Color(138, 43, 226)
        };

        // Define five clusters with two points each (kept vertical)
        int[][] clusters = {
                {50, 50, 50, 80},
                {150, 100, 150, 130},
                {250, 150, 250, 180},
                {100, 250, 100, 280},
                {200, 300, 200, 330}
        };

        // Draw clusters with two dots each in different colors
        for (int i = 0; i < clusters.length; i++) {
            g2d.setColor(rainbowColors[(i * 2) % rainbowColors.length]);
            g2d.fillOval(clusters[i][0] - 5, clusters[i][1] - 5, 10, 10);

            g2d.setColor(rainbowColors[(i * 2 + 1) % rainbowColors.length]);
            g2d.fillOval(clusters[i][2] - 5, clusters[i][3] - 5, 10, 10);
        }

        // Draw vertical rectangles around each cluster
        g2d.setColor(Color.BLUE);
        for (int[] cluster : clusters) {
            int rectX = cluster[0] - 10;
            int rectY = Math.min(cluster[1], cluster[3]) - 10;
            int rectWidth = 20;
            int rectHeight = Math.abs(cluster[3] - cluster[1]) + 20;
            g2d.drawRect(rectX, rectY, rectWidth, rectHeight);
        }

        // Connect clusters with lines
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < clusters.length - 1; i++) {
            int x1 = clusters[i][0];
            int y1 = (clusters[i][1] + clusters[i][3]) / 2;
            int x2 = clusters[i + 1][0];
            int y2 = (clusters[i + 1][1] + clusters[i + 1][3]) / 2;
            g2d.drawLine(x1, y1, x2, y2);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clustering Activity");
        ClusteringActivity panel = new ClusteringActivity();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}