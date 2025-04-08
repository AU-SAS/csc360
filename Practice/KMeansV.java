import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeansV extends JPanel {
    private List<CentroidCluster<DoublePoint>> clusters;

    public KMeansV() {
        // Generate random data points
        List<DoublePoint> points = generateRandomPoints(30, 400, 300);

        // Apply KMeans clustering with k = 3
        int k = 3;
        KMeansPlusPlusClusterer<DoublePoint> kMeans = new KMeansPlusPlusClusterer<>(k);
        clusters = kMeans.cluster(points);
    }

    private List<DoublePoint> generateRandomPoints(int num, int width, int height) {
        List<DoublePoint> points = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < num; i++) {
            double[] coords = new double[]{
                    50 + rand.nextDouble() * (width - 100),
                    50 + rand.nextDouble() * (height - 100)
            };
            points.add(new DoublePoint(coords));
        }

        return points;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Use different colors for each cluster
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA};
        Graphics2D g2d = (Graphics2D) g;

        int clusterIndex = 0;
        for (Cluster<DoublePoint> cluster : clusters) {
            g2d.setColor(colors[clusterIndex % colors.length]);
            for (DoublePoint point : cluster.getPoints()) {
                int x = (int) point.getPoint()[0];
                int y = (int) point.getPoint()[1];
                g2d.fillOval(x - 5, y - 5, 10, 10);
            }
            clusterIndex++;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("K-Means Clustering Visualization");
        KMeansV panel = new KMeansV();
        frame.add(panel);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}
