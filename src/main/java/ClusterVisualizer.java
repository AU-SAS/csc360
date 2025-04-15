import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ClusterVisualizer extends JPanel {

    private final double[][] dataPoints;
    private int[] labels;
    private int k;
    private final Color[] colors = {
            Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE,
            Color.MAGENTA, Color.CYAN, Color.PINK, Color.YELLOW
    };

    public ClusterVisualizer(double[][] dataPoints) {
        this.dataPoints = dataPoints;
        runClustering();
    }

    private void runClustering() {
        int maxK = Math.min(8, dataPoints.length);
        double minSSE = Double.MAX_VALUE;
        int bestK = 2;

        for (int i = 2; i <= maxK; i++) {
            KMeansResult result = kMeans(dataPoints, i);
            if (result.sse < minSSE) {
                minSSE = result.sse;
                bestK = i;
                this.labels = result.labels;
            }
        }
        this.k = bestK;
    }

    // Simple KMeans clustering without external libraries
    private KMeansResult kMeans(double[][] points, int k) {
        int n = points.length;
        int dim = points[0].length;
        Random rand = new Random();
        double[][] centroids = new double[k][dim];

        // Randomly initialize centroids
        for (int i = 0; i < k; i++) {
            centroids[i] = Arrays.copyOf(points[rand.nextInt(n)], dim);
        }

        int[] labels = new int[n];
        boolean changed;
        int iterations = 0;
        do {
            changed = false;

            // Assign points to closest centroid
            for (int i = 0; i < n; i++) {
                int bestCluster = -1;
                double bestDist = Double.MAX_VALUE;
                for (int j = 0; j < k; j++) {
                    double dist = distance(points[i], centroids[j]);
                    if (dist < bestDist) {
                        bestDist = dist;
                        bestCluster = j;
                    }
                }
                if (labels[i] != bestCluster) {
                    labels[i] = bestCluster;
                    changed = true;
                }
            }

            // Recompute centroids
            double[][] newCentroids = new double[k][dim];
            int[] counts = new int[k];
            for (int i = 0; i < n; i++) {
                int cluster = labels[i];
                for (int d = 0; d < dim; d++) {
                    newCentroids[cluster][d] += points[i][d];
                }
                counts[cluster]++;
            }

            for (int j = 0; j < k; j++) {
                if (counts[j] > 0) {
                    for (int d = 0; d < dim; d++) {
                        newCentroids[j][d] /= counts[j];
                    }
                }
            }

            centroids = newCentroids;
            iterations++;
        } while (changed && iterations < 100);

        // Calculate SSE
        double sse = 0;
        for (int i = 0; i < n; i++) {
            sse += Math.pow(distance(points[i], centroids[labels[i]]), 2);
        }

        return new KMeansResult(labels, sse);
    }

    private double distance(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(sum);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        double maxX = Arrays.stream(dataPoints).mapToDouble(p -> p[0]).max().orElse(1);
        double maxY = Arrays.stream(dataPoints).mapToDouble(p -> p[1]).max().orElse(1);

        for (int i = 0; i < dataPoints.length; i++) {
            int x = (int) (dataPoints[i][0] / maxX * (width - 100)) + 50;
            int y = (int) (dataPoints[i][1] / maxY * (height - 100)) + 50;
            g2d.setColor(colors[labels[i] % colors.length]);
            g2d.fillOval(x, y, 10, 10);
        }

        g2d.setColor(Color.BLACK);
        g2d.drawString("K = " + k + " clusters", 10, 20);
    }

    public static void main(String[] args) {
        double[][] data = {
                {1, 2}, {2, 3}, {3, 2}, {8, 9}, {9, 8}, {8, 8},
                {4, 5}, {5, 6}, {6, 5}, {7, 8}, {9, 9}, {3, 3}
        };

        JFrame frame = new JFrame("Cluster Diagram (No Library)");
        ClusterVisualizer panel = new ClusterVisualizer(data);
        frame.add(panel);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Helper class to store clustering result
    private static class KMeansResult {
        int[] labels;
        double sse;

        KMeansResult(int[] labels, double sse) {
            this.labels = labels;
            this.sse = sse;
        }
    }
}
