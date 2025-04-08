package misc;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class ShapeOperations extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set anti-aliasing for smoother edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Define the rectangle and circle
        Rectangle2D rectangle = new Rectangle2D.Double(100, 100, 200, 100);  // Rectangle at (100, 100) with width=200, height=100
        Ellipse2D circle = new Ellipse2D.Double(150, 80, 100, 100);  // Circle at (150, 80) with radius 50

        // Draw the rectangle
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fill(rectangle);

        // Draw the circle
        g2d.setColor(new Color(173, 216, 230)); // Light Blue using RGB values
        g2d.fill(circle);

        // Create Area objects for the operations
        Area rectArea = new Area(rectangle);
        Area circleArea = new Area(circle);

        // UNION: Both rectangle and circle area
        g2d.setColor(new Color(0, 255, 0, 100));  // Light green with transparency
        Area unionArea = new Area(rectArea);
        unionArea.add(circleArea);  // Union of the two shapes
        g2d.fill(unionArea);

        // INTERSECTION: Overlapping area of rectangle and circle
        g2d.setColor(new Color(255, 0, 0, 100));  // Light red with transparency
        Area intersectionArea = new Area(rectArea);
        intersectionArea.intersect(circleArea);  // Intersection of the two shapes
        g2d.fill(intersectionArea);

        // DIFFERENCE: Area of circle - rectangle
        g2d.setColor(new Color(255, 255, 0, 100));  // Light yellow with transparency
        Area differenceArea = new Area(circleArea);
        differenceArea.subtract(rectArea);  // Difference (circle - rectangle)
        g2d.fill(differenceArea);

        // SYMMETRIC DIFFERENCE: Area covered by either circle or rectangle, but not both
        g2d.setColor(new Color(0, 0, 255, 100));  // Light blue with transparency
        Area symmetricDiffArea = new Area(rectArea);
        symmetricDiffArea.exclusiveOr(circleArea);  // Symmetric Difference of the two shapes
        g2d.fill(symmetricDiffArea);

        // Draw boundary of shapes for clarity
        g2d.setColor(Color.BLACK);
        g2d.draw(rectangle);
        g2d.draw(circle);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Set Operations: Circle and Rectangle");
        ShapeOperations panel = new ShapeOperations();
        frame.add(panel);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}