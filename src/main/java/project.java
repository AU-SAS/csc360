import javax.swing.*;
import java.awt.*;

public class project extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Array of colors for the dots
        Color[] dotColors = {
                Color.RED, Color.ORANGE, Color.YELLOW,
                Color.GREEN, Color.CYAN, Color.BLUE,
                Color.MAGENTA, Color.PINK, Color.GRAY,
                Color.BLACK, Color.DARK_GRAY
        };

        // Group configuration based on the diagram
        int[] leftGroupSizes = {3, 3, 3};  // Left side groups
        int[] rightGroupSizes = {2, 2, 2}; // Right side groups
        int finalGroupSize = 2;            // Final group with 2 dots

        // Starting positions and sizes
        int startXLeft = 100;   // X for left groups
        int startXRight = 350;  // X for right groups
        int startY = 50;        // Y start position
        int dotSize = 20;       // Diameter of each dot
        int gap = 30;           // Gap between dots in a group
        int rectPadding = 10;   // Space between dots and rectangle boundary
        int colorIndex = 0;     // Track colors for the dots

        // Draw left side groups
        int currentY = startY;
        for (int group = 0; group < leftGroupSizes.length; group++)
        {
            int dotsInGroup = leftGroupSizes[group];
            drawGroup(g2d, startXLeft, currentY, dotsInGroup, dotSize, gap, rectPadding, dotColors, colorIndex);
            colorIndex += dotsInGroup;
            currentY += dotsInGroup * gap + 40; // Add space after each group
        }

        // Draw right side groups
        currentY = startY + 10; // Slight offset for right groups
        for (int group = 0; group < rightGroupSizes.length; group++)
        {
            int dotsInGroup = rightGroupSizes[group];
            drawGroup(g2d, startXRight, currentY, dotsInGroup, dotSize, gap, rectPadding, dotColors, colorIndex);
            colorIndex += dotsInGroup;
            currentY += dotsInGroup * gap + 40;
        }

        // Draw vertical connections between left and right groups
        drawVerticalConnections(g2d, startXLeft, startXRight, startY, leftGroupSizes, rightGroupSizes, gap, rectPadding, dotSize);

        // Draw final rectangle with 2 dots and connect to the last three rectangles
        int finalGroupX = 500;  // X-coordinate for the final group
        int finalGroupY = (startY + 2 * (gap * 3 + 40)) + 10; // Align to the middle of last three groups
        drawGroup(g2d, finalGroupX, finalGroupY, finalGroupSize, dotSize, gap, rectPadding, dotColors, colorIndex);

        // Connect last 3 right-side groups to the final rectangle with vertical lines
        connectLastGroupsToFinal(g2d, startXRight, finalGroupX, finalGroupY, rightGroupSizes, startY, gap, rectPadding, dotSize);
    }

    // Method to draw a group of dots with a rectangle
    private void drawGroup(Graphics2D g2d, int startX, int startY, int dotsInGroup, int dotSize, int gap, int rectPadding, Color[] dotColors, int colorIndex)
    {
        // Rectangle dimensions
        int rectWidth = dotSize + 2 * rectPadding;
        int rectHeight = (dotsInGroup - 1) * gap + dotSize + 2 * rectPadding;
        int rectX = startX - rectWidth / 2;
        int rectY = startY - rectPadding;

        // Draw the rectangle
        g2d.setColor(Color.BLACK);
        g2d.drawRect(rectX, rectY, rectWidth, rectHeight);

        // Draw the dots inside the rectangle
        for (int i = 0; i < dotsInGroup; i++)
        {
            g2d.setColor(dotColors[colorIndex % dotColors.length]);
            int dotX = rectX + rectPadding;
            int dotY = startY + i * gap;
            g2d.fillOval(dotX, dotY, dotSize, dotSize);
            colorIndex++;
        }
    }

    // Method to draw vertical connections between groups
    private void drawVerticalConnections(Graphics2D g2d, int startXLeft, int startXRight, int startY, int[] leftGroupSizes, int[] rightGroupSizes, int gap, int rectPadding, int dotSize)
    {
        int currentYLeft = startY + dotSize / 2;
        int currentYRight = startY + dotSize / 2 + 10; // Slight offset for right groups

        for (int i = 0; i < leftGroupSizes.length; i++)
        {
            int leftDotsInGroup = leftGroupSizes[i];
            int rightDotsInGroup = rightGroupSizes[i];

            // Midpoints of the rectangles for connection
            int midLeftY = currentYLeft + (leftDotsInGroup - 1) * gap / 2;
            int midRightY = currentYRight + (rightDotsInGroup - 1) * gap / 2;

            // Draw vertical line directly between groups
            g2d.setColor(Color.BLACK);
            g2d.drawLine(startXLeft + dotSize / 2 + rectPadding, midLeftY, startXRight - dotSize / 2 - rectPadding, midRightY);

            // Update Y position for next group
            currentYLeft += leftDotsInGroup * gap + 40;
            currentYRight += rightDotsInGroup * gap + 40;
        }
    }

    // Method to connect last 3 right-side groups to the final rectangle with vertical lines
    private void connectLastGroupsToFinal(Graphics2D g2d, int startXRight, int finalGroupX, int finalGroupY, int[] rightGroupSizes, int startY, int gap, int rectPadding, int dotSize)
    {
        int currentYRight = startY + dotSize / 2 + 10; // Y for right groups

        for (int i = 0; i < rightGroupSizes.length; i++)
        {
            int rightDotsInGroup = rightGroupSizes[i];
            int midRightY = currentYRight + (rightDotsInGroup - 1) * gap / 2;

            // Midpoint of the final group for connection
            int midFinalY = finalGroupY + dotSize + gap / 2;

            // Draw vertical line directly to final group
            g2d.setColor(Color.BLACK);
            g2d.drawLine(startXRight + dotSize / 2 + rectPadding, midRightY, finalGroupX - dotSize / 2 - rectPadding, midFinalY);

            // Update Y position for next group
            currentYRight += rightDotsInGroup * gap + 40;
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Final Diagram with Vertical Connections and Colorful Dots");
        project panel = new project();
        frame.add(panel);
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}