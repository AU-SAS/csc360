import javax.swing.*;
import java.awt.*;

public class DrawDotsAndLineExample extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set the color for the dots
        g2d.setColor(Color.RED);

        // Draw two dots
        int x1 = 50, y1 = 50; // Coordinates of the first dot
        int x2 = 150, y2 = 100; // Coordinates of the second dot
        g2d.fillOval(x1 - 5, y1 - 5, 10, 10); // Dot 1
        g2d.fillOval(x2 - 5, y2 - 5, 10, 10); // Dot 2

        // Set the color for the line
        g2d.setColor(Color.BLUE);

        // Draw a line connecting the two dots
        g2d.drawLine(x1, y1, x2, y2);
    }

    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Draw Dots and Line Example");
        DrawDotsAndLineExample panel = new DrawDotsAndLineExample();

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}