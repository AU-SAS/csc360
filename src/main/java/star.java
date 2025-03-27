import javax.swing.*;
import java.awt.*;

class star extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Array of VIBGYOR colors
        Color[] vibgyorColors = {
                Color.RED,            // V - Violet
                new Color(75, 0, 130), // I - Indigo
                Color.BLUE,           // B - Blue
                Color.GREEN,          // G - Green
                Color.YELLOW,         // Y - Yellow
                Color.ORANGE,         // O - Orange
                Color.RED             // R - Red (repeated for pattern)
        };

        // Start position and size configuration
        int startX = 50;  // X-coordinate for the first star
        int startY = 50;  // Y-coordinate for the first star
        int starSize = 40; // Size of the stars
        int gap = 60;     // Gap between stars

        // Draw 7 stars in VIBGYOR colors
        for (int i = 0; i < vibgyorColors.length; i++)
        {
            g2d.setColor(vibgyorColors[i]);
            drawStar(g2d, startX + i * gap, startY, starSize);
        }
    }

    // Method to draw a star at (x, y) with given size
    private void drawStar(Graphics2D g2d, int x, int y, int size)
    {
        int[] xPoints = new int[10];
        int[] yPoints = new int[10];

        double angle = Math.PI / 5; // 36 degrees between star points
        for (int i = 0; i < 10; i++)
        {
            double r = (i % 2 == 0) ? size / 2.0 : size / 4.0;
            xPoints[i] = (int) (x + r * Math.cos(i * 2 * angle - Math.PI / 2));
            yPoints[i] = (int) (y + r * Math.sin(i * 2 * angle - Math.PI / 2));
        }

        g2d.fillPolygon(xPoints, yPoints, 10);
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("VIBGYOR Stars");
        star panel = new star();
        frame.add(panel);
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
