import javax.swing.*;
import java.awt.*;


public class GroupDotsWithRectangles extends JPanel {

    private final Color[] dotColors = {
            Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE,
            Color.MAGENTA, Color.CYAN, Color.PINK, Color.YELLOW,
            Color.GRAY, new Color(128, 0, 128) // Purple
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int x = 100;
        int startY = 50;
        int dotSize = 20;
        int gap = 30;

        // Draw 10 colorful dots vertically
        for (int i = 0; i < 10; i++) {
            g2d.setColor(dotColors[i % dotColors.length]);
            int y = startY + i * gap;
            g2d.fillOval(x, y, dotSize, dotSize);
        }

        // Group definitions: {start index, count}
        int[][] groups = {
                {0, 3},  // Group of 3 dots
                {3, 5},  // Group of 5 dots
                {8, 2}   // Group of 2 dots
        };

        // Draw rectangles around the groups
        for (int i = 0; i < groups.length; i++) {
            int start = groups[i][0];
            int count = groups[i][1];

            int topY = startY + start * gap - 5;
            int height = gap * count;
            int rectX = x - 10;
            int rectWidth = dotSize + 20;

            // Draw bounding rectangle
            g2d.drawRect(rectX, topY, rectWidth, height);

            // Label the group size above the rectangle
            g2d.drawString(String.valueOf(count), rectX + rectWidth + 10, topY + height / 2);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Step 3 - Group Dots With Rectangles");
        GroupDotsWithRectangles panel = new GroupDotsWithRectangles();

        frame.add(panel);
        frame.setSize(300, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
