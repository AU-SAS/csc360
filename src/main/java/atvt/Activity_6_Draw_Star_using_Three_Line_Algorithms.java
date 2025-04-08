package atvt;

import javax.swing.*;
import java.awt.*;
public class Activity_6_Draw_Star_using_Three_Line_Algorithms extends JPanel
{
    public Activity_6_Draw_Star_using_Three_Line_Algorithms()
    {
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.WHITE);
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        // Define star vertices
        int[][] starPoints = {
                {400, 100}, {500, 300}, {700, 300}, {550, 450}, {600, 650},
                {400, 550}, {200, 650}, {250, 450}, {100, 300}, {300, 300}
        };

        // Draw lines using the three algorithms
        for (int i = 0; i < starPoints.length; i++)
        {
            int x1 = starPoints[i][0];
            int y1 = starPoints[i][1];
            int x2 = starPoints[(i + 2) % starPoints.length][0];
            int y2 = starPoints[(i + 2) % starPoints.length][1];

            if (i % 3 == 0) {
                drawNaiveLine(g2d, x1, y1, x2, y2);
            } else if (i % 3 == 1) {
                drawBresenhamLine(g2d, x1, y1, x2, y2);
            } else {
                drawMidpointLine(g2d, x1, y1, x2, y2);
            }
        }
    }
    // Naïve Line Algorithm
    private void drawNaiveLine(Graphics2D g2d, int x1, int y1, int x2, int y2)
    {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        double xInc = dx / (double) steps;
        double yInc = dy / (double) steps;

        double x = x1, y = y1;
        for (int i = 0; i <= steps; i++) {
            g2d.fillRect((int) x, (int) y, 1, 1);
            x += xInc;
            y += yInc;
        }
    }

    // Bresenham's Line Algorithm
    private void drawBresenhamLine(Graphics2D g2d, int x1, int y1, int x2, int y2)
    {
        int dx = Math.abs(x2 - x1), dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1, sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;
        while (true)
        {
            g2d.fillRect(x1, y1, 1, 1);
            if (x1 == x2 && y1 == y2) break;
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

    // Midpoint Line Algorithm
    private void drawMidpointLine(Graphics2D g2d, int x1, int y1, int x2, int y2)
    {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int d = 2 * dy - dx;
        int deltaE = 2 * dy;
        int deltaNE = 2 * (dy - dx);

        int x = x1, y = y1;
        g2d.fillRect(x, y, 1, 1);

        while (x < x2) {
            if (d <= 0) {
                d += deltaE;
                x++;
            } else {
                d += deltaNE;
                x++;
                y++;
            }
            g2d.fillRect(x, y, 1, 1);
        }
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Star Drawing Activity");
        Activity_6_Draw_Star_using_Three_Line_Algorithms starPanel = new Activity_6_Draw_Star_using_Three_Line_Algorithms();
        frame.add(starPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}