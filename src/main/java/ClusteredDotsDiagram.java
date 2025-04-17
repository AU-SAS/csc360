import javax.swing.*;
import java.awt.*;

public class ClusteredDotsDiagram extends JPanel {


    private int colorIndex = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Color[] dotColors = {
                Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN,
                Color.BLUE, Color.MAGENTA, Color.PINK, Color.GRAY, Color.BLACK,
                Color.DARK_GRAY, new Color(128, 0, 128), new Color(0, 128, 128),
                new Color(255, 105, 180), new Color(139, 69, 19)
        };

        int[][] groupSizes = {
                {3, 5, 2},  // Column 1
                {2, 2},     // Column 2
                {2},        // Column 3
                {1}         // Column 4
        };

        int startX = 100;
        int startY = 50;
        int dotSize = 20;
        int gap = 30;
        int rectPadding = 10;

        Point[][] groupCenters = new Point[groupSizes.length][];

        for (int col = 0; col < groupSizes.length; col++) {
            int currentY = startY;
            groupCenters[col] = new Point[groupSizes[col].length];
            for (int group = 0; group < groupSizes[col].length; group++) {
                int dotsInGroup = groupSizes[col][group];
                Point center = drawGroup(g2d, startX + col * 150, currentY, dotsInGroup, dotSize, gap, rectPadding, dotColors);
                groupCenters[col][group] = center;
                currentY += dotsInGroup * gap + 40;
            }
        }

        drawSingleAttachments(g2d, groupCenters);
    }

    private Point drawGroup(Graphics2D g2d, int startX, int startY, int dotsInGroup,
                            int dotSize, int gap, int rectPadding, Color[] dotColors) {

        int rectWidth = dotSize + 2 * rectPadding;
        int rectHeight = (dotsInGroup - 1) * gap + dotSize + 2 * rectPadding;
        int rectX = startX - rectWidth / 2;
        int rectY = startY - rectPadding;

        g2d.setColor(Color.BLACK);
        g2d.drawRect(rectX, rectY, rectWidth, rectHeight);

        for (int i = 0; i < dotsInGroup; i++) {
            g2d.setColor(dotColors[colorIndex % dotColors.length]);
            int dotX = rectX + rectPadding;
            int dotY = startY + i * gap;
            g2d.fillOval(dotX, dotY, dotSize, dotSize);
            colorIndex++;
        }

        return new Point(startX, rectY + rectHeight / 2); // Return the center point of the rectangle
    }

    // Adjust line drawing to ensure it only touches the edge of the rectangle
    private void drawSingleAttachments(Graphics2D g2d, Point[][] centers) {
        g2d.setColor(Color.DARK_GRAY);

        for (int col = 0; col < centers.length - 1; col++) {
            for (int i = 0; i < centers[col].length; i++) {
                Point from = centers[col][i];

                // Find the corresponding group in the next column (same index or closest)
                int nextIndex = Math.min(i, centers[col + 1].length - 1);
                Point to = centers[col + 1][nextIndex];

                // Calculate the edge points for the connection
                int fromX = from.x;
                int fromY = from.y;

                int toX = to.x;
                int toY = to.y;

                // Adjust positions so that the line just touches the edge
                // For now, we connect from center to center of next group, adjusting if necessary
                g2d.drawLine(fromX, fromY, toX, toY);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Single-Attachment Cluster Diagram");
        ClusteredDotsDiagram panel = new ClusteredDotsDiagram();
        frame.add(panel);
        frame.setSize(850, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
