package self_practice;

import javax.swing.*;
import java.awt.*;

public class vibgyor_stars extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // VIBGYOR Colors
        Color[] vibgyorColors = {
                Color.RED,
                Color.ORANGE,
                Color.YELLOW,
                Color.GREEN,
                Color.BLUE,
                new Color(75, 0, 130), // Indigo
                new Color(138, 43, 226) // Violet
        };

        // Define star coordinates (centered)
        int[] x1 = {150, 180, 250, 190, 210, 150, 90, 110, 50, 120};
        int[] y1 = {50, 120, 120, 160, 230, 180, 230, 160, 120, 120};
        int z = x1.length;

        for (int i = 0; i < vibgyorColors.length; i++) {
            g2d.setColor(vibgyorColors[i]);
            g2d.drawPolygon(x1, y1, z);

            for (int j = 0; j < x1.length; j++) {
                x1[j] += 5;
                y1[j] += 5;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VIBGYOR Star");
        vibgyor_stars panel = new vibgyor_stars();
        frame.add(panel);
        frame.setSize(400, 400);  // Adjusted size for better visualization
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
