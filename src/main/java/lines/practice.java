import javax.swing.*;
import java.awt.*;

class DrawVerticalLine extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set the line color to black
        g2d.setColor(Color.BLACK);

        // Define the coordinates of the vertical line
        int x = 150;  // X-coordinate (fixed for vertical line)
        int y1 = 50;  // Starting Y-coordinate
        int y2 = 250; // Ending Y-coordinate

        // Draw the vertical line
        g2d.drawLine(x, y1, x, y2);
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Draw Vertical Line");
        DrawVerticalLine panel = new DrawVerticalLine();
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
