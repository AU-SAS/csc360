import javax.swing.*;
import java.awt.*;

public class CarSl extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw car body
        g2d.setColor(Color.BLUE);
        g2d.drawLine(50, 150, 250, 150);
        g2d.drawLine(50, 150, 50, 100);
        g2d.drawLine(250, 150, 250, 100);
        g2d.drawLine(50, 100, 100, 70);
        g2d.drawLine(100, 70, 200, 70);
        g2d.drawLine(200, 70, 250, 100);


        g2d.drawLine(50, 100, 250, 100);


        g2d.setColor(Color.BLACK);
        g2d.fillOval(70, 140, 30, 30);
        g2d.fillOval(180, 140, 30, 30);
    }

    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("2D-Car Drawing");
        CarSl panel = new CarSl();
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}
