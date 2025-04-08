import javax.swing.*;
import java.awt.*;

public class DrawHouse extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        g2d.setColor(Color.ORANGE);
        g2d.fillRect(100, 150, 200, 150);


        g2d.setColor(Color.RED);
        int[] xPoints = {100, 200, 300};
        int[] yPoints = {150, 50, 150};
        g2d.fillPolygon(xPoints, yPoints, 3);


        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(170, 220, 60, 80);


        g2d.setColor(Color.CYAN);
        g2d.fillRect(120, 170, 50, 50);
        g2d.fillRect(230, 170, 50, 50);
    }

    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Draw House");
        DrawHouse panel = new DrawHouse();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}
