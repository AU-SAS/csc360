import javax.swing.*;
import java.awt.*;

public class DrawCurves1 extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set antialiasing for smoother curves
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the quadratic curve
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2)); // Make the curve stroke thicker
        g2d.drawLine(50, 250, 150, 150); // Start and end points of the quadratic curve
        g2d.drawString("Quadratic Curve", 50, 240);

        // Control point for quadratic curve
        g2d.setColor(Color.BLACK);
        g2d.fillOval(100, 200, 5, 5); // Control point
        g2d.setColor(Color.RED);
        g2d.drawLine(50, 250, 100, 200); // Line from start point to control point
        g2d.drawLine(150, 150, 100, 200); // Line from end point to control point
        g2d.drawLine(50, 250, 150, 150); // Quadratic curve

        // Draw the cubic curve
        g2d.setColor(Color.BLUE);
        g2d.drawString("Cubic Curve", 50, 120);
        g2d.drawLine(50, 50, 150, 150); // First segment of cubic curve (start point)

        // Control points for cubic curve
        g2d.setColor(Color.BLACK);
        g2d.fillOval(100, 30, 5, 5);  // First control point
        g2d.fillOval(150, 70, 5, 5);  // Second control point
        g2d.fillOval(200, 150, 5, 5); // Third control point
        g2d.setColor(Color.BLUE);

        // Cubic curve with three control points
        g2d.drawLine(50, 50, 100, 30); // Start to first control point
        g2d.drawLine(150, 150, 200, 150); // Second control point to the end point
        g2d.drawLine(50, 50, 150, 150); // Cubic curve (a smooth transition)
    }

    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Draw Quadratic and Cubic Curves");
        DrawCurves1 panel = new DrawCurves1();

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}
