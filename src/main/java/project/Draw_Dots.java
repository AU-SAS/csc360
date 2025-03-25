package project;

import javax.swing.*;
import java.awt.*;

public class Draw_Dots extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // Explicitly cast Graphics to Graphics2D

        // Array of VIBGYOR colors
        Color[] vibgyorColors = {
                Color.RED,
                Color.ORANGE,
                Color.YELLOW,
                Color.GREEN,
                Color.BLUE,
                new Color(75, 0, 130), // Indigo
                new Color(138, 43, 226) // Violet
        };

        // Starting coordinates for the first dot
        int startX = 50;
        int startY = 50;
        int dotSize = 20; // Diameter of each dot
        int gap = 40;     // Gap between the dots
        int lineLength = 200; // Length of the lines

        // Draw the dots and lines
        for (int i = 0; i < vibgyorColors.length; i++) {
            int x = startX + i * gap;
            int y = startY + i * gap;
            int centerX = x + dotSize / 2;
            int centerY = y + dotSize / 2;

            // Set the color for the current dot and line
            g2d.setColor(vibgyorColors[i]);

            // Draw the dot
            g2d.fillOval(x, y, dotSize, dotSize);

            // Draw the vertical line
            g2d.drawLine(centerX, centerY, centerX, centerY + lineLength);

            // Draw the horizontal line
            g2d.drawLine(centerX, centerY, centerX + lineLength, centerY);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VIBGYOR Dots with Lines");
        Draw_Dots panel = new Draw_Dots();

        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
