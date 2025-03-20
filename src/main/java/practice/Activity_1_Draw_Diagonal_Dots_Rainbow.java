package practice;

import javax.swing.*;
import java.awt.*;

public class Activity_1_Draw_Diagonal_Dots_Rainbow extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Array of VIBGYOR colors
        Color[] vibgyorColors =
                {
                Color.RED,
                Color.ORANGE,
                Color.YELLOW,
                Color.GREEN,
                Color.BLUE,
                new Color(75, 0, 130), // Indigo (custom RGB value)
                new Color(138, 43, 226) // Violet (custom RGB value)
                };

        // Starting coordinates for the first dot
        int startX = 50;
        int startY = 50;
        int dotSize = 20; // Diameter of each dot
        int gap = 30;     // Gap between the dots

        // Draw the dots in a diagonal line
        for (int i = 0; i < vibgyorColors.length; i++)
        {
            g2d.setColor(vibgyorColors[i]);
            g2d.fillOval(startX + i * gap, startY + i * gap, dotSize, dotSize);
        }
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("VIBGYOR Dots");
        Activity_1_Draw_Diagonal_Dots_Rainbow panel = new Activity_1_Draw_Diagonal_Dots_Rainbow();
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}