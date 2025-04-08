import javax.swing.*;
import java.awt.*;

public class SimpleMandala extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Canvas background
        g2d.setColor(new Color(240, 240, 240)); // Light gray
        g2d.fillRect(0, 0, getWidth(), getHeight());

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Outer Yellow Circle
        g2d.setColor(new Color(244, 213, 128));
        g2d.fillOval(centerX - 80, centerY - 80, 160, 160);

        // Middle Cyan Circle
        g2d.setColor(Color.CYAN);
        g2d.fillOval(centerX - 60, centerY - 60, 120, 120);

        // Inner Pink Circle
        g2d.setColor(Color.PINK);
        g2d.fillOval(centerX - 20, centerY - 20, 40, 40);

        // 8 Petals Around Center
        g2d.setColor(new Color(212, 104, 164)); // Petal Color (Pinkish Purple)
        int petalSize = 60;
        g2d.fillOval(centerX - 30, centerY - 100, petalSize, petalSize); // Top
        g2d.fillOval(centerX + 40, centerY - 70, petalSize, petalSize); // Top-right
        g2d.fillOval(centerX + 70, centerY, petalSize, petalSize); // Right
        g2d.fillOval(centerX + 40, centerY + 40, petalSize, petalSize); // Bottom-right
        g2d.fillOval(centerX - 30, centerY + 70, petalSize, petalSize); // Bottom
        g2d.fillOval(centerX - 100, centerY + 40, petalSize, petalSize); // Bottom-left
        g2d.fillOval(centerX - 130, centerY, petalSize, petalSize); // Left
        g2d.fillOval(centerX - 100, centerY - 70, petalSize, petalSize); // Top-left
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("8-Petal Mandala");
        SimpleMandala panel = new SimpleMandala();

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
