package project;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Clustering extends JPanel {
    private static final int MARGIN_LEFT = 50;
    private static final int MARGIN_TOP = 40;
    private static final int DOT_SIZE = 6;
    private static final int POINT_SPACING = 30;

    private List<DataPoint> dataPoints;
    private List<Cluster> clusters;

    public Clustering() {
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);
        initializeData();
        performClustering();
    }

    private void initializeData() {
        dataPoints = new ArrayList<>();

        // Group 1
        dataPoints.add(new DataPoint("A1", 2, 3, Color.RED));
        dataPoints.add(new DataPoint("A2", 3, 4, Color.RED));
        dataPoints.add(new DataPoint("A3", 2.5, 3.5, Color.RED));

        // Group 2
        dataPoints.add(new DataPoint("B1", 10, 12, Color.BLUE));
        dataPoints.add(new DataPoint("B2", 11, 13, Color.BLUE));
        dataPoints.add(new DataPoint("B3", 10.5, 11.5, Color.BLUE));

        // Group 3
        dataPoints.add(new DataPoint("C1", 5, 15, Color.GREEN));
        dataPoints.add(new DataPoint("C2", 6, 16, Color.GREEN));
    }

    private void performClustering() {
        // Initialize each data point as its own cluster
        clusters = new ArrayList<>();
        for (DataPoint point : dataPoints) {
            Cluster cluster = new Cluster();
            cluster.addPoint(point);
            clusters.add(cluster);
        }

        while (clusters.size() > 1) {
            int minI = 0, minJ = 1;
            double minDistance = Double.MAX_VALUE;

            for (int i = 0; i < clusters.size(); i++) {
                for (int j = i + 1; j < clusters.size(); j++) {
                    double distance = calculateDistance(clusters.get(i), clusters.get(j));
                    if (distance < minDistance) {
                        minDistance = distance;
                        minI = i;
                        minJ = j;
                    }
                }
            }


            Cluster merged = mergeClusters(clusters.get(minI), clusters.get(minJ), minDistance);
            Cluster cluster1 = clusters.remove(minJ);
            Cluster cluster2 = clusters.remove(minI);
            clusters.add(merged);
        }
    }

    private double calculateDistance(Cluster c1, Cluster c2) {
        // Use single linkage (minimum distance between any two points)
        double minDist = Double.MAX_VALUE;

        for (DataPoint p1 : c1.points) {
            for (DataPoint p2 : c2.points) {
                double dist = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }

        return minDist;
    }

    private Cluster mergeClusters(Cluster c1, Cluster c2, double distance) {
        Cluster merged = new Cluster();
        for (DataPoint p : c1.points) {
            merged.addPoint(p);
        }
        for (DataPoint p : c2.points) {
            merged.addPoint(p);
        }

        merged.leftChild = c1;
        merged.rightChild = c2;
        merged.distance = distance;

        return merged;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(1.5f));
        int y = MARGIN_TOP;
        for (DataPoint point : dataPoints) {
            point.screenY = y;
            y += POINT_SPACING;
        }
        for (DataPoint point : dataPoints) {
            g2d.setColor(point.color);
            g2d.fillOval(MARGIN_LEFT - DOT_SIZE/2, point.screenY - DOT_SIZE/2, DOT_SIZE, DOT_SIZE);
            g2d.drawString(point.name, MARGIN_LEFT - 30, point.screenY + 5);
        }

        if (!clusters.isEmpty()) {
            Cluster rootCluster = clusters.get(0);
            drawDendrogram(g2d, rootCluster, MARGIN_LEFT + 20);
        }

        g2d.setColor(Color.BLACK);
        g2d.drawLine(MARGIN_LEFT, 20, MARGIN_LEFT, getHeight() - 30);
        g2d.drawLine(MARGIN_LEFT, getHeight() - 30, getWidth() - 50, getHeight() - 30);
        g2d.drawString("Distance →", getWidth() - 100, getHeight() - 10);
    }

    private int drawDendrogram(Graphics2D g2d, Cluster cluster, int x) {
        if (cluster.isLeaf()) {
            DataPoint point = cluster.points.get(0);
            g2d.setColor(point.color);
            g2d.drawLine(MARGIN_LEFT, point.screenY, x, point.screenY);

            return point.screenY;
        } else {
            int leftY = drawDendrogram(g2d, cluster.leftChild, x);
            int rightY = drawDendrogram(g2d, cluster.rightChild, x);
            int newX = x + (int)(cluster.distance * 15);

            // Draw connecting lines
            g2d.setColor(cluster.getColor());
            g2d.drawLine(newX, leftY, newX, rightY);
            g2d.drawLine(x, leftY, newX, leftY);
            g2d.drawLine(x, rightY, newX, rightY);

            return (leftY + rightY) / 2;
        }
    }

    private static class DataPoint {
        String name;
        double x, y;
        Color color;
        int screenY;

        DataPoint(String name, double x, double y, Color color) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    private static class Cluster {
        List<DataPoint> points = new ArrayList<>();
        Cluster leftChild;
        Cluster rightChild;
        double distance;

        void addPoint(DataPoint point) {
            points.add(point);
        }

        boolean isLeaf() {
            return leftChild == null && rightChild == null;
        }

        Color getColor() {
            if (points.isEmpty()) return Color.BLACK;
            int r = 0, g = 0, b = 0;
            for (DataPoint p : points) {
                r += p.color.getRed();
                g += p.color.getGreen();
                b += p.color.getBlue();
            }
            return new Color(r / points.size(), g / points.size(), b / points.size());
        }
    }

    public static void main(String[] args) {
 //       SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Clustering");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Clustering());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        //});
    }
}