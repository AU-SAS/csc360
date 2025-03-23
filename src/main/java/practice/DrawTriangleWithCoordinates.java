package practice;

import javax.swing.*;
import java.awt.*;

public class DrawTriangleWithCoordinates extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set the color for the triangle
        g2d.setColor(Color.GREEN);

        // Coordinates of the triangle's vertices
        int x1 = 100, y1 = 50;   // Vertex 1
        int x2 = 50, y2 = 150;   // Vertex 2
        int x3 = 150, y3 = 150;  // Vertex 3

        // Draw the triangle
        g2d.drawLine(x1, y1, x2, y2); // Line from Vertex 1 to Vertex 2
        g2d.drawLine(x2, y2, x3, y3); // Line from Vertex 2 to Vertex 3
        g2d.drawLine(x3, y3, x1, y1); // Line from Vertex 3 to Vertex 1

        // Set the color for the coordinate labels
        g2d.setColor(Color.BLUE);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));

        // Draw the coordinate labels next to each vertex
        g2d.drawString("(" + x1 + ", " + y1 + ")", x1 + 5, y1 - 5); // Label for Vertex 1
        g2d.drawString("(" + x2 + ", " + y2 + ")", x2 - 40, y2 + 15); // Label for Vertex 2
        g2d.drawString("(" + x3 + ", " + y3 + ")", x3 + 5, y3 + 15); // Label for Vertex 3
    }

    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Draw Triangle with Coordinates");
        DrawTriangleWithCoordinates panel = new DrawTriangleWithCoordinates();

        frame.add(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}