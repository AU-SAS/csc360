package misc;

import javax.swing.*;
import java.awt.*;

// Builds a car out of plain fillRect/fillOval primitives.
// Contrast with atvt.Activity_4_Draw_a_car, which draws the same subject
// as a single GeneralPath using quadratic and cubic segments.
public class DrawCarWithPrimitives extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set background color
        this.setBackground(Color.WHITE);

        // Draw car body
        g.setColor(Color.BLUE);
        g.fillRect(100, 150, 200, 50); // Main body
        g.fillRect(140, 120, 120, 40); // Roof

        // Draw windows
        g.setColor(Color.CYAN);
        g.fillRect(150, 125, 40, 30); // Left window
        g.fillRect(210, 125, 40, 30); // Right window

        // Draw wheels
        g.setColor(Color.BLACK);
        g.fillOval(120, 190, 40, 40); // Left wheel
        g.fillOval(240, 190, 40, 40); // Right wheel

        // Draw wheel centers
        g.setColor(Color.GRAY);
        g.fillOval(132, 202, 16, 16); // Left hubcap
        g.fillOval(252, 202, 16, 16); // Right hubcap
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Simple Car Drawing");
            DrawCarWithPrimitives car = new DrawCarWithPrimitives();

            frame.add(car);
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
        });
    }
}
