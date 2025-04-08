package practice;

import javax.swing.*;
import java.awt.*;

public class Step_7_Implement_Naive_Line_Drawing_Algorithm extends JPanel
{
    private final int x1, y1, x2, y2;
    public Step_7_Implement_Naive_Line_Drawing_Algorithm(int num1, int num2, int num3, int num4)
    {
        this.x1 = num1;
        this.y1 = num2;
        this.x2 = num3;
        this.y2 = num4;
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.WHITE);
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        // Call the naive line drawing algorithm
        drawNaiveLine(g2d, x1, y1, x2, y2);
    }
    private void drawNaiveLine(Graphics2D g2d, int x1, int y1, int x2, int y2)
    {
        // Calculate the slope (m) of the line
        double m = (double) (y2 - y1) / (x2 - x1);
        double c = y1 - m * x1; // Calculate the intercept (c)

        // Draw points based on the slope
        if (Math.abs(x2 - x1) >= Math.abs(y2 - y1)) { // Loop along the x-axis
            for (int x = x1; x <= x2; x++) {
                int y = (int) Math.round(m * x + c); // Calculate y for each x
                g2d.fillRect(x, y, 1, 1); // Draw a pixel
            }
        } else { // Loop along the y-axis for steep lines
            for (int y = y1; y <= y2; y++) {
                int x = (int) Math.round((y - c) / m); // Calculate x for each y
                g2d.fillRect(x, y, 1, 1); // Draw a pixel
            }
        }
    }
    public static void main(String[] args)
    {
        // Line endpoints
        int x1 = 100, y1 = 100;
        int x2 = 500, y2 = 400;

        // Create a frame to display the line
        JFrame frame = new JFrame("Naïve Line Drawing Algorithm");
        Step_7_Implement_Naive_Line_Drawing_Algorithm linePanel = new Step_7_Implement_Naive_Line_Drawing_Algorithm(x1, y1, x2, y2);

        frame.add(linePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}