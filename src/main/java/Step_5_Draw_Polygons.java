import javax.swing.*;
import java.awt.*;

public class Step_5_Draw_Polygons extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set antialiasing for smoother shapes
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the self-overlapping polygon
        g2d.setColor(Color.RED);
        int[] xSelfOverlap = {50, 100, 75, 125, 100};
        int[] ySelfOverlap = {50, 50, 100, 100, 75};
        g2d.drawPolygon(xSelfOverlap, ySelfOverlap, xSelfOverlap.length);
        g2d.drawString("Self-Overlapping", 50, 40);

        // Draw the nonconvex polygon
        g2d.setColor(Color.BLUE);
        int[] xNonConvex = {200, 250, 225, 275, 225};
        int[] yNonConvex = {50, 50, 100, 100, 75};
        g2d.drawPolygon(xNonConvex, yNonConvex, xNonConvex.length);
        g2d.drawString("Nonconvex", 200, 40);

        // Draw the convex polygon
        g2d.setColor(Color.GREEN);
        int[] xConvex = {50, 100, 75, 50};
        int[] yConvex = {150, 150, 200, 200};
        g2d.drawPolygon(xConvex, yConvex, xConvex.length);
        g2d.drawString("Convex", 50, 140);
    }
    public static void main(String[] args)
    {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Draw Polygons");
        Step_5_Draw_Polygons panel = new Step_5_Draw_Polygons();
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}