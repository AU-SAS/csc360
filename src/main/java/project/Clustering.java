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

    public Clustering() {
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);
        initializeData();
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set positions for data points on the screen
        int y = MARGIN_TOP;
        for (DataPoint point : dataPoints) {
            point.screenY = y;
            y += POINT_SPACING;
        }

        // Draw the data points and their labels
        for (DataPoint point : dataPoints) {
            g2d.setColor(point.color);
            g2d.fillOval(MARGIN_LEFT - DOT_SIZE/2, point.screenY - DOT_SIZE/2, DOT_SIZE, DOT_SIZE);
            g2d.drawString(point.name + " (" + point.x + ", " + point.y + ")",
                    MARGIN_LEFT + 10, point.screenY + 5);
        }

        // Draw the axis
        g2d.setColor(Color.BLACK);
        g2d.drawLine(MARGIN_LEFT, 20, MARGIN_LEFT, getHeight() - 30);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Data Points");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Clustering());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}