package practice;

import javax.swing.*;
import java.awt.*;
public class Step_3_Draw_Triangle_Area_Filled_With_Color extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Coordinates of the triangle vertices
        int[] xPoints = {100, 150, 50};
        int[] yPoints = {50, 150, 150};
        int nPoints = 3;
        // Fill the triangle with yellow color
        g2d.setColor(Color.YELLOW);
        g2d.fillPolygon(xPoints, yPoints, nPoints);
        // Draw the outline of the triangle
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(xPoints, yPoints, nPoints);
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Triangle Drawing");
        Step_3_Draw_Triangle_Area_Filled_With_Color panel = new Step_3_Draw_Triangle_Area_Filled_With_Color();
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}