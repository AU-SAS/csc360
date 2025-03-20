import javax.swing.*;
import java.awt.*;

class DrawMultipleVerticalLines extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set the line color to black
        g2d.setColor(Color.BLACK);

        // Define starting X position and spacing between lines
        int startX = 50;  // Starting X-coordinate
        int y1 = 50;      // Starting Y-coordinate
        int y2 = 250;     // Ending Y-coordinate
        int gap = 30;     // Gap between each line

        // Draw 5 vertical lines
        for (int i = 0; i < 5; i++)
        {
            int x = startX + (i * gap); // Calculate new X-coordinate for each line
            g2d.drawLine(x, y1, x, y2);
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Draw Multiple Vertical Lines");
        DrawMultipleVerticalLines panel = new DrawMultipleVerticalLines();
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
