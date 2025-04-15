import javax.swing.*;
import java.awt.*;

public class CarDrawing1 extends JPanel {

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
            CarDrawing1 car = new CarDrawing1();

            frame.add(car);
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
