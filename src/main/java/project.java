import javax.swing.*;
import java.awt.*;

public class project extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set color to black
        g2d.setColor(Color.BLACK);

        // Starting coordinates for the first dot
        int startX = 100; // Fixed X-coordinate for a vertical line
        int startY = 50;
        int dotSize = 20; // Diameter of each dot
        int gap = 30;     // Gap between the dots

        // Draw 7 dots in a straight vertical line
        for (int i = 0; i < 7; i++)
        {
            g2d.fillOval(startX, startY + i * gap, dotSize, dotSize);
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Black Dots in a Vertical Line");
        project panel = new project();
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}



