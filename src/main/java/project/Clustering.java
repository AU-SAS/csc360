package project;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Clustering extends JPanel {
    private static final int MARGIN_LEFT = 50;
    private static final int MARGIN_TOP = 40;
    private static final int DOT_SIZE = 8;
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;
    private static final Random RANDOM = new Random(42); // Fixed seed for reproducibility

    private List<DataPoint> dataPoints;
    private List<Centroid> centroids;
    private int k; // This will be determined automatically
    private Map<Centroid, List<DataPoint>> clusters;
    private double[] silhouetteScores;
    private int maxK = 10; // Maximum number of clusters to consider

    public Clustering() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBackground(Color.WHITE);
        initializeData();
        determineOptimalK();
        performClustering();
    }

    private void initializeData() {
        dataPoints = new ArrayList<>();

        // Group 1
        for (int i = 0; i < 20; i++) {
            dataPoints.add(new DataPoint("A" + i, 2 + RANDOM.nextDouble() * 2, 3 + RANDOM.nextDouble() * 2));
        }

        // Group 2
        for (int i = 0; i < 20; i++) {
            dataPoints.add(new DataPoint("B" + i, 10 + RANDOM.nextDouble() * 2, 12 + RANDOM.nextDouble() * 2));
        }

        // Group 3
        for (int i = 0; i < 15; i++) {
            dataPoints.add(new DataPoint("C" + i, 5 + RANDOM.nextDouble() * 2, 15 + RANDOM.nextDouble() * 2));
        }

        //noise points
        for (int i = 0; i < 5; i++) {
            dataPoints.add(new DataPoint("N" + i, RANDOM.nextDouble() * 15, RANDOM.nextDouble() * 20));
        }
    }

    private void determineOptimalK() {
        double[] wcss = new double[maxK + 1];
        silhouetteScores = new double[maxK + 1];
        for (int testK = 1; testK <= maxK; testK++) {
            List<Centroid> testCentroids = initializeCentroids(testK);
            Map<Centroid, List<DataPoint>> testClusters = new HashMap<>();
            boolean changed = true;
            int iterations = 0;
            final int MAX_ITERATIONS = 100;

            while (changed && iterations < MAX_ITERATIONS) {
                // Clear previous assignments
                for (Centroid centroid : testCentroids) {
                    testClusters.put(centroid, new ArrayList<>());
                }

                // Assign points to nearest centroid
                for (DataPoint point : dataPoints) {
                    Centroid nearest = null;
                    double minDistance = Double.MAX_VALUE;

                    for (Centroid centroid : testCentroids) {
                        double distance = calculateDistance(point, centroid);
                        if (distance < minDistance) {
                            minDistance = distance;
                            nearest = centroid;
                        }
                    }

                    if (nearest != null) {
                        testClusters.get(nearest).add(point);
                    }
                }

                // Recalculate centroids
                changed = false;
                for (Centroid centroid : testCentroids) {
                    List<DataPoint> clusterPoints = testClusters.get(centroid);
                    if (!clusterPoints.isEmpty()) {
                        double newX = 0, newY = 0;
                        for (DataPoint point : clusterPoints) {
                            newX += point.x;
                            newY += point.y;
                        }
                        newX /= clusterPoints.size();
                        newY /= clusterPoints.size();

                        if (Math.abs(newX - centroid.x) > 0.001 || Math.abs(newY - centroid.y) > 0.001) {
                            centroid.x = newX;
                            centroid.y = newY;
                            changed = true;
                        }
                    }
                }

                iterations++;
            }
            wcss[testK] = calculateWCSS(testClusters);
            if (testK > 1) {
                silhouetteScores[testK] = calculateSilhouetteScore(testClusters);
            }
        }

        // Find optimal k using the Elbow method and Silhouette score
        k = findOptimalK(wcss, silhouetteScores);
        System.out.println("Optimal K determined: " + k);
    }

    private List<Centroid> initializeCentroids(int k) {
        List<Centroid> centroids = new ArrayList<>();
        int firstIndex = RANDOM.nextInt(dataPoints.size());
        DataPoint firstPoint = dataPoints.get(firstIndex);
        centroids.add(new Centroid(firstPoint.x, firstPoint.y));
        for (int i = 1; i < k; i++) {
            double[] distances = new double[dataPoints.size()];
            double sumDistances = 0;

            for (int j = 0; j < dataPoints.size(); j++) {
                DataPoint point = dataPoints.get(j);
                double minDistance = Double.MAX_VALUE;

                for (Centroid centroid : centroids) {
                    double distance = calculateDistance(point, centroid);
                    minDistance = Math.min(minDistance, distance);
                }

                distances[j] = minDistance * minDistance;
                sumDistances += distances[j];
            }
            double random = RANDOM.nextDouble() * sumDistances;
            double cumulativeSum = 0;
            for (int j = 0; j < dataPoints.size(); j++) {
                cumulativeSum += distances[j];
                if (cumulativeSum >= random) {
                    DataPoint point = dataPoints.get(j);
                    centroids.add(new Centroid(point.x, point.y));
                    break;
                }
            }
        }

        return centroids;
    }

    private double calculateDistance(DataPoint point, Centroid centroid) {
        return Math.sqrt(Math.pow(point.x - centroid.x, 2) + Math.pow(point.y - centroid.y, 2));
    }

    private double calculateWCSS(Map<Centroid, List<DataPoint>> clusters) {
        double wcss = 0;

        for (Map.Entry<Centroid, List<DataPoint>> entry : clusters.entrySet()) {
            Centroid centroid = entry.getKey();
            List<DataPoint> clusterPoints = entry.getValue();

            for (DataPoint point : clusterPoints) {
                double distance = calculateDistance(point, centroid);
                wcss += distance * distance;
            }
        }

        return wcss;
    }

    private double calculateSilhouetteScore(Map<Centroid, List<DataPoint>> clusters) {
        // Calculate silhouette score for each point
        double totalSilhouette = 0;
        int validPoints = 0;

        for (Map.Entry<Centroid, List<DataPoint>> entry : clusters.entrySet()) {
            Centroid centroid = entry.getKey();
            List<DataPoint> clusterPoints = entry.getValue();

            if (clusterPoints.size() <= 1) continue; // Skip clusters with single point

            for (DataPoint point : clusterPoints) {
                // Calculate a(i) - average distance to points in same cluster
                double a = 0;
                for (DataPoint other : clusterPoints) {
                    if (point != other) {
                        a += Math.sqrt(Math.pow(point.x - other.x, 2) + Math.pow(point.y - other.y, 2));
                    }
                }
                a /= (clusterPoints.size() - 1);
                double minB = Double.MAX_VALUE;
                for (Map.Entry<Centroid, List<DataPoint>> otherEntry : clusters.entrySet()) {
                    if (otherEntry.getKey() != centroid && !otherEntry.getValue().isEmpty()) {
                        double b = 0;
                        List<DataPoint> otherClusterPoints = otherEntry.getValue();

                        for (DataPoint other : otherClusterPoints) {
                            b += Math.sqrt(Math.pow(point.x - other.x, 2) + Math.pow(point.y - other.y, 2));
                        }
                        b /= otherClusterPoints.size();

                        minB = Math.min(minB, b);
                    }
                }

                if (minB != Double.MAX_VALUE) {
                    double silhouette = (minB - a) / Math.max(a, minB);
                    totalSilhouette += silhouette;
                    validPoints++;
                }
            }
        }

        return validPoints > 0 ? totalSilhouette / validPoints : 0;
    }

    private int findOptimalK(double[] wcss, double[] silhouetteScores) {
        int bestK = 2;
        double bestScore = silhouetteScores[2];

        for (int i = 3; i <= maxK; i++) {
            if (silhouetteScores[i] > bestScore) {
                bestScore = silhouetteScores[i];
                bestK = i;
            }
        }
        if (bestScore < 0.5) {
            double[] elbowRatios = new double[maxK];
            for (int i = 2; i <= maxK; i++) {
                elbowRatios[i-1] = wcss[i-1] / wcss[i];
            }
            bestK = 2;
            double maxRatio = elbowRatios[1];
            for (int i = 3; i <= maxK-1; i++) {
                if (elbowRatios[i-1] > maxRatio) {
                    maxRatio = elbowRatios[i-1];
                    bestK = i;
                }
            }
        }

        return bestK;
    }

    private void performClustering() {
        // Initialize centroids
        centroids = initializeCentroids(k);
        clusters = new HashMap<>();

        // Run k-means algorithm
        boolean changed = true;
        int iterations = 0;
        final int MAX_ITERATIONS = 100;

        while (changed && iterations < MAX_ITERATIONS) {
            // Clear previous assignments
            for (Centroid centroid : centroids) {
                clusters.put(centroid, new ArrayList<>());
            }

            // Assign points to nearest centroid
            for (DataPoint point : dataPoints) {
                Centroid nearest = null;
                double minDistance = Double.MAX_VALUE;

                for (Centroid centroid : centroids) {
                    double distance = calculateDistance(point, centroid);
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearest = centroid;
                    }
                }

                if (nearest != null) {
                    clusters.get(nearest).add(point);
                    // Set the color of the point to the color of its cluster
                    point.color = nearest.color;
                }
            }

            // Recalculate centroids
            changed = false;
            for (Centroid centroid : centroids) {
                List<DataPoint> clusterPoints = clusters.get(centroid);
                if (!clusterPoints.isEmpty()) {
                    double newX = 0, newY = 0;
                    for (DataPoint point : clusterPoints) {
                        newX += point.x;
                        newY += point.y;
                    }
                    newX /= clusterPoints.size();
                    newY /= clusterPoints.size();

                    if (Math.abs(newX - centroid.x) > 0.001 || Math.abs(newY - centroid.y) > 0.001) {
                        centroid.x = newX;
                        centroid.y = newY;
                        changed = true;
                    }
                }
            }
        }
    }

    private double scaleX(double x) {
        double minX = dataPoints.stream().mapToDouble(p -> p.x).min().orElse(0);
        double maxX = dataPoints.stream().mapToDouble(p -> p.x).max().orElse(15);
        double range = maxX - minX;

        return MARGIN_LEFT + 50 + (x - minX) * (CANVAS_WIDTH - MARGIN_LEFT - 100) / range;
    }

    private double scaleY(double y) {
        double minY = dataPoints.stream().mapToDouble(p -> p.y).min().orElse(0);
        double maxY = dataPoints.stream().mapToDouble(p -> p.y).max().orElse(20);
        double range = maxY - minY;

        return MARGIN_TOP + (y - minY) * (CANVAS_HEIGHT - MARGIN_TOP - 100) / range;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(1.5f));

        // Draw data points
        for (DataPoint point : dataPoints) {
            int screenX = (int) scaleX(point.x);
            int screenY = (int) scaleY(point.y);

            g2d.setColor(point.color);
            g2d.fillOval(screenX - DOT_SIZE/2, screenY - DOT_SIZE/2, DOT_SIZE, DOT_SIZE);
        }

        // Draw centroids
        for (Centroid centroid : centroids) {
            int screenX = (int) scaleX(centroid.x);
            int screenY = (int) scaleY(centroid.y);

            g2d.setColor(centroid.color);
            g2d.fillOval(screenX - DOT_SIZE, screenY - DOT_SIZE, DOT_SIZE * 2, DOT_SIZE * 2);
            g2d.setColor(Color.BLACK);
            g2d.drawOval(screenX - DOT_SIZE, screenY - DOT_SIZE, DOT_SIZE * 2, DOT_SIZE * 2);
        }

        // Draw axes
        g2d.setColor(Color.BLACK);
        g2d.drawLine(MARGIN_LEFT, CANVAS_HEIGHT - 50, CANVAS_WIDTH - 50, CANVAS_HEIGHT - 50); // x-axis
        g2d.drawLine(MARGIN_LEFT, 30, MARGIN_LEFT, CANVAS_HEIGHT - 50); // y-axis
        g2d.drawString("X →", CANVAS_WIDTH - 70, CANVAS_HEIGHT - 30);
        g2d.drawString("Y ↑", MARGIN_LEFT - 30, 40);

        // Draw legend
        int legendX = CANVAS_WIDTH - 200;
        int legendY = 50;
        g2d.drawString("K = " + k , legendX, legendY);
        legendY += 20;

        for (int i = 0; i < centroids.size(); i++) {
            Centroid centroid = centroids.get(i);
            g2d.setColor(centroid.color);
            g2d.fillOval(legendX, legendY - 10, 10, 10);
            g2d.setColor(Color.BLACK);
            g2d.drawString("Cluster " + (i+1) + " (" + clusters.get(centroid).size() + " points)", legendX + 20, legendY);
            legendY += 20;
        }

    }

    private static class DataPoint {
        String name;
        double x, y;
        Color color;

        DataPoint(String name, double x, double y) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.color = Color.GRAY; // Default color before clustering
        }
    }

    private static class Centroid {
        double x, y;
        Color color;

        Centroid(double x, double y) {
            this.x = x;
            this.y = y;
            this.color = new Color(
                    RANDOM.nextInt(200),
                    RANDOM.nextInt(200),
                    RANDOM.nextInt(200)
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("K Means Clustering");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Clustering());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}