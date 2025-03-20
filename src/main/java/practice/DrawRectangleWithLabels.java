import javax.swing.*;
import java.awt.*;

public class DrawRectangleWithLabels extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set the color for the rectangle
        g2d.setColor(Color.BLUE);

        // Coordinates for the rectangle's top-left corner
        int x = 100, y = 100;

        // Dimensions of the rectangle
        int width = 50;
        int height = 30;

        // Draw the rectangle
        g2d.drawRect(x, y, width, height);

        // Set the font and color for the labels
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.setColor(Color.RED);

        // Label the sides
        g2d.drawString("50", x + width / 2 - 10, y - 5);               // Top side
        g2d.drawString("50", x + width / 2 - 10, y + height + 15);    // Bottom side
        g2d.drawString("30", x - 25, y + height / 2 + 5);             // Left side
        g2d.drawString("30", x + width + 10, y + height / 2 + 5);     // Right side
    }

    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Draw Rectangle with Labels");
        DrawRectangleWithLabels panel = new DrawRectangleWithLabels();

        frame.add(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}