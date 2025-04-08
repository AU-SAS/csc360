import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Venn1 extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set anti-aliasing for smoother edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Define the rectangle and circle
        Rectangle2D rectangle = new Rectangle2D.Double(50, 100, 200, 100);  // Rectangle at (50, 100) with width=200, height=100
        Ellipse2D circle = new Ellipse2D.Double(120, 80, 100, 100);  // Circle at (120, 80) with radius 50

        // First Operation: UNION
        g2d.setColor(new Color(0, 255, 0, 100));  // Light green with transparency
        Area rectArea = new Area(rectangle);
        Area circleArea = new Area(circle);
        Area unionArea = new Area(rectArea);
        unionArea.add(circleArea);  // Union of the two shapes

        g2d.fill(unionArea); // Fill the union area with light green

// Change color for the circle
        g2d.setColor(new Color(0, 0, 255, 100));  // Semi-transparent blue for the circle
        g2d.fill(circle);  // Fill only the circle separately

        g2d.setColor(Color.BLACK);
        g2d.draw(rectangle);
        g2d.draw(circle);
        g2d.drawString("Union", 50, 250);  // Label for the operation


        // Move to a different part for the next operation
        g2d.translate(0, 200);

        // Second Operation: INTERSECTION
        g2d.setColor(new Color(255, 0, 0, 100));  // Light red with transparency
        Area intersectionArea = new Area(rectArea);
        intersectionArea.intersect(circleArea);  // Intersection of the two shapes
        g2d.fill(intersectionArea);
        g2d.setColor(Color.BLACK);
        g2d.draw(rectangle);
        g2d.draw(circle);
        g2d.drawString("Intersection", 50, 250);  // Label for the operation

        // Move to a different part for the next operation
        g2d.translate(0, 200);

        // Third Operation: DIFFERENCE
        g2d.setColor(new Color(255, 255, 0, 100));  // Light yellow with transparency
        Area differenceArea = new Area(circleArea);
        differenceArea.subtract(rectArea);  // Difference (circle - rectangle)
        g2d.fill(differenceArea);
        g2d.setColor(Color.BLACK);
        g2d.draw(rectangle);
        g2d.draw(circle);
        g2d.drawString("Difference", 50, 250);  // Label for the operation

        // Move to a different part for the next operation
        g2d.translate(0, 200);

        // Fourth Operation: SYMMETRIC DIFFERENCE
        g2d.setColor(new Color(0, 0, 255, 100));  // Light blue with transparency
        Area symmetricDiffArea = new Area(rectArea);
        symmetricDiffArea.exclusiveOr(circleArea);  // Symmetric Difference of the two shapes
        g2d.fill(symmetricDiffArea);
        g2d.setColor(Color.BLACK);
        g2d.draw(rectangle);
        g2d.draw(circle);
        g2d.drawString("Symmetric Difference", 50, 250);  // Label for the operation



    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Venn Diagram Visualizer");
        Venn1 panel = new Venn1();
        frame.add(panel);
        frame.setSize(500, 1000);  // Increase height to accommodate all operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}