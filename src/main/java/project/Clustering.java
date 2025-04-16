package project;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Clustering extends JPanel {
    private static final int MARGIN_LEFT = 50;
    private static final int MARGIN_TOP = 40;
    private static final int DOT_SIZE = 6;
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 400;

    private List<DataPoint> dataPoints;
    private List<Centroid> centroids;
    private int k = 3; // Fixed K value for now
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
        dataPoints.add(new DataPoint("A1", 2, 3));
        dataPoints.add(new DataPoint("A2", 3, 4));
        dataPoints.add(new DataPoint("A3", 2.5, 3.5));

        // Group 2
        dataPoints.add(new DataPoint("B1", 10, 12));
        dataPoints.add(new DataPoint("B2", 11, 13));
        dataPoints.add(new DataPoint("B3", 10.5, 11.5));

        // Group 3
        dataPoints.add(new DataPoint("C1", 5, 15));
        dataPoints.add(new DataPoint("C2", 6, 16));
    }

    private void initializeCentroids() {
        centroids = new ArrayList<>();
        Random random = new Random();

        // Randomly select k points as initial centroids
        Set<Integer> selectedIndices = new HashSet<>();
        while (selectedIndices.size() < k) {
            int index = random.nextInt(dataPoints.size());
            if (!selectedIndices.contains(index)) {
                selectedIndices.add(index);
                DataPoint point = dataPoints.get(index);
                centroids.add(new Centroid(point.x, point.y));
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
    }

    private double calculateDistance(DataPoint point, Centroid centroid) {
        return Math.sqrt(Math.pow(point.x - centroid.x, 2) + Math.pow(point.y - centroid.y, 2));
    }

    private double scaleX(double x) {
        double minX = dataPoints.stream().mapToDouble(p -> p.x).min().orElse(0);
        double maxX = dataPoints.stream().mapToDouble(p -> p.x).max().orElse(15);
        double range = maxX - minX;

        return MARGIN_LEFT + (x - minX) * (CANVAS_WIDTH - MARGIN_LEFT - 50) / range;
    }

    private double scaleY(double y) {
        double minY = dataPoints.stream().mapToDouble(p -> p.y).min().orElse(0);
        double maxY = dataPoints.stream().mapToDouble(p -> p.y).max().orElse(20);
        double range = maxY - minY;

        return MARGIN_TOP + (y - minY) * (CANVAS_HEIGHT - MARGIN_TOP - 50) / range;
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
        static final Random RANDOM = new Random();

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
            JFrame frame = new JFrame("K-Means Clustering");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Clustering());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}