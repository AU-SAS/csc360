package practice;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;

public class DrawCurves extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable antialiasing for smooth curves
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set the stroke for better visibility
        g2d.setStroke(new BasicStroke(2));

        // Draw a quadratic curve
        g2d.setColor(Color.BLUE);
        QuadCurve2D.Double quadraticCurve = new QuadCurve2D.Double(50, 100, 150, 50, 250, 100);
        g2d.draw(quadraticCurve);
        g2d.drawString("Quadratic Curve", 50, 90);

        // Draw a cubic curve
        g2d.setColor(Color.RED);
        CubicCurve2D.Double cubicCurve = new CubicCurve2D.Double(50, 200, 100, 150, 200, 250, 300, 200);
        g2d.draw(cubicCurve);
        g2d.drawString("Cubic Curve", 50, 190);
    }

    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Draw Quadratic and Cubic Curves");
        DrawCurves panel = new DrawCurves();

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}