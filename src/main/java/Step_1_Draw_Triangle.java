import javax.swing.*;
import java.awt.*;

public class Step_1_Draw_Triangle extends JPanel {

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
    }

    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Draw Triangle Example");
        Step_1_Draw_Triangle panel = new Step_1_Draw_Triangle();

        frame.add(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}