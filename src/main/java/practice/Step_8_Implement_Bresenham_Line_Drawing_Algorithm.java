package practice;

import javax.swing.*;
import java.awt.*;

public class Step_8_Implement_Bresenham_Line_Drawing_Algorithm extends JPanel {

    private final int x1, y1, x2, y2;

    public Step_8_Implement_Bresenham_Line_Drawing_Algorithm(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        // Call the Bresenham's line drawing algorithm
        drawBresenhamLine(g2d, x1, y1, x2, y2);
    }

    private void drawBresenhamLine(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = x1 < x2 ? 1 : -1; // Step direction for x
        int sy = y1 < y2 ? 1 : -1; // Step direction for y

        int err = dx - dy; // Initial error term

        while (true) {
            // Draw the current pixel
            g2d.fillRect(x1, y1, 1, 1);

            // Check if we've reached the end point
            if (x1 == x2 && y1 == y2) break;

            // Calculate the next error term and coordinates
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }

    public static void main(String[] args) {
        // Line endpoints
        int x1 = 100, y1 = 100;
        int x2 = 500, y2 = 300;

        // Create a frame to display the line
        JFrame frame = new JFrame("Bresenham's Line Drawing Algorithm");
        Step_8_Implement_Bresenham_Line_Drawing_Algorithm linePanel = new Step_8_Implement_Bresenham_Line_Drawing_Algorithm(x1, y1, x2, y2);

        frame.add(linePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
