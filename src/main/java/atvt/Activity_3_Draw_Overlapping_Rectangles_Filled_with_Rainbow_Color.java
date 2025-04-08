package atvt;

import javax.swing.*;
import java.awt.*;

public class Activity_3_Draw_Overlapping_Rectangles_Filled_with_Rainbow_Color extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Colors for VIBGYOR
        Color[] vibgyor =
                {
                        Color.RED,
                        Color.ORANGE,
                        Color.YELLOW,
                        Color.GREEN,
                        Color.BLUE,
                        new Color(75, 0, 130), // Indigo (custom RGB value)
                        new Color(138, 43, 226) // Violet (custom RGB value)
                };
        // Starting coordinates and dimensions for the rectangles
        int startX = 50;
        int startY = 50;
        int width = 100;
        int height = 50;
        // Draw 7 overlapping rectangles
        for (int i = 0; i < 7; i++)
        {
            g2d.setColor(vibgyor[i]);
            g2d.fillRect(startX + (i * 20), startY + (i * 20), width, height);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(startX + (i * 20), startY + (i * 20), width, height);
        }
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Overlapping Rectangles");
        Activity_3_Draw_Overlapping_Rectangles_Filled_with_Rainbow_Color panel = new Activity_3_Draw_Overlapping_Rectangles_Filled_with_Rainbow_Color();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}