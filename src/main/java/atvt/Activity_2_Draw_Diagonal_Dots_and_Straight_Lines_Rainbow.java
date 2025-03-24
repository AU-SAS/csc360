package atvt;

import javax.swing.*;
import java.awt.*;
public class Activity_2_Draw_Diagonal_Dots_and_Straight_Lines_Rainbow extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Set the line weight
        g2d.setStroke(new BasicStroke(5));
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
        int lineLength = 200; // Length of the lines
        // Draw the dots and lines
        for (int i = 0; i < vibgyorColors.length; i++) {
            int centerX = startX + i * gap + dotSize / 2;
            int centerY = startY + i * gap + dotSize / 2;
            // Set the color for the current dot and line
            g2d.setColor(vibgyorColors[i]);
            // Draw the dot
            g2d.fillOval(startX + i * gap, startY + i * gap, dotSize, dotSize);
            // Draw the vertical line
            g2d.drawLine(centerX, centerY, centerX, centerY + lineLength);
            // Draw the horizontal line
            g2d.drawLine(centerX, centerY, centerX + lineLength, centerY);
        }
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("VIBGYOR Dots with Lines");
        Activity_2_Draw_Diagonal_Dots_and_Straight_Lines_Rainbow panel = new Activity_2_Draw_Diagonal_Dots_and_Straight_Lines_Rainbow();
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}