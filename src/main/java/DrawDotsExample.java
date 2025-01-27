import javax.swing.*;
import java.awt.*;

public class DrawDotsExample extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set dot color
        g2d.setColor(Color.RED);

        // Draw some dots
        g2d.fillOval(50, 50, 10, 10); // Dot 1
        g2d.fillOval(100, 100, 10, 10); // Dot 2
        g2d.fillOval(150, 75, 10, 10); // Dot 3
        g2d.fillOval(200, 125, 10, 10); // Dot 4
        g2d.fillOval(250, 50, 10, 10); // Dot 5
    }

    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Draw Dots Example");
        DrawDotsExample panel = new DrawDotsExample();

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}