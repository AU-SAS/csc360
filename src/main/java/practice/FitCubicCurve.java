import javax.swing.*;
import java.awt.*;

public class FitCubicCurve extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set antialiasing for smoother curves
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw a straight line (line to be fitted with a cubic curve)
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(50, 100, 250, 100); // Line from (50, 100) to (250, 100)
        g2d.drawString("Straight Line", 50, 90);

        // Draw cubic curve fitting the straight line smoothly
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(2)); // Make the cubic curve stroke thicker

        // Starting point of the curve
        int x0 = 50, y0 = 100;
        // Control points
        int x1 = 100, y1 = 80;
        int x2 = 200, y2 = 120;
        // End point of the curve
        int x3 = 250, y3 = 100;

        // Draw the cubic curve using curveTo (Cubic Bézier)
        g2d.drawLine(x0, y0, x1, y1); // Line from start to first control point
        g2d.drawLine(x3, y3, x2, y2); // Line from second control point to end point
        g2d.setColor(Color.BLUE);
        g2d.drawLine(x0, y0, x3, y3);  // Cubic Bézier curve

        // Label the control points for clarity
        g2d.setColor(Color.BLACK);
        g2d.fillOval(x1 - 3, y1 - 3, 6, 6);  // First control point
        g2d.fillOval(x2 - 3, y2 - 3, 6, 6);  // Second control point
        g2d.fillOval(x0 - 3, y0 - 3, 6, 6);  // Start point
        g2d.fillOval(x3 - 3, y3 - 3, 6, 6);  // End point

        // Displaying coordinates
        g2d.drawString("(" + x0 + "," + y0 + ")", x0 + 5, y0 - 5);
        g2d.drawString("(" + x1 + "," + y1 + ")", x1 + 5, y1 - 5);
        g2d.drawString("(" + x2 + "," + y2 + ")", x2 + 5, y2 - 5);
        g2d.drawString("(" + x3 + "," + y3 + ")", x3 + 5, y3 - 5);
    }

    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Fitting a Cubic Curve to a Line");
        FitCubicCurve panel = new FitCubicCurve();

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}