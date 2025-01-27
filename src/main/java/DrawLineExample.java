import javax.swing.*;
import java.awt.*;

public class DrawLineExample extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set the color for the line
        g2d.setColor(Color.BLUE);

        // Draw a straight line
        int x1 = 50, y1 = 50; // Start point of the line
        int x2 = 150, y2 = 100; // End point of the line
        g2d.drawLine(x1, y1, x2, y2);
    }

    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Draw Line Example");
        DrawLineExample panel = new DrawLineExample();

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}