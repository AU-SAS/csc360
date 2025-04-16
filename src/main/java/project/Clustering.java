package project;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Clustering extends JPanel {
    private static final int MARGIN_LEFT = 50;
    private static final int MARGIN_TOP = 40;
    private static final int DOT_SIZE = 8;
    private static final int CANVAS_WIDTH = 700;
    private static final int CANVAS_HEIGHT = 500;
    private static final Random RANDOM = new Random();

    private List<DataPoint> dataPoints;
    private List<Centroid> centroids;
    private int k = 3;
    private Map<Centroid, List<DataPoint>> clusters;

    public Clustering() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBackground(Color.WHITE);
        initializeData();
        performClustering();
    }

    private void initializeData() {
        dataPoints = new ArrayList<>();

        // Group 1
        for (int i = 0; i < 15; i++) {
            dataPoints.add(new DataPoint("A" + i, 2 + RANDOM.nextDouble() * 2, 3 + RANDOM.nextDouble() * 2));
        }

        // Group 2
        for (int i = 0; i < 15; i++) {
            dataPoints.add(new DataPoint("B" + i, 10 + RANDOM.nextDouble() * 2, 12 + RANDOM.nextDouble() * 2));
        }

        // Group 3
        for (int i = 0; i < 10; i++) {
            dataPoints.add(new DataPoint("C" + i, 5 + RANDOM.nextDouble() * 2, 15 + RANDOM.nextDouble() * 2));
        }
    }

    private void initializeCentroids() {
        centroids = new ArrayList<>();
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
    }

    private void performClustering() {
        initializeCentroids();
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

            iterations++;
        }

        System.out.println("K-means clustering completed in " + iterations + " iterations");

        // Calculate and print the WCSS (Within-Cluster Sum of Squares)
        double wcss = calculateWCSS();
        System.out.println("WCSS for k=" + k + ": " + wcss);
    }

    private double calculateDistance(DataPoint point, Centroid centroid) {
        return Math.sqrt(Math.pow(point.x - centroid.x, 2) + Math.pow(point.y - centroid.y, 2));
    }

    private double calculateWCSS() {
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
        int legendX = CANVAS_WIDTH - 150;
        int legendY = 50;
        g2d.setColor(Color.BLACK);
        g2d.drawString("K = " + k, legendX, legendY);
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