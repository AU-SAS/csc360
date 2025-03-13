package self_practice;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class practice_activity1 extends JPanel {

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
        int[] x1 = {150, 180, 250, 190, 210, 150, 90};
        int[] y1 = {50, 120, 120, 160, 230, 180, 230};
        int z = x1.length;
        GeneralPath gp = new GeneralPath();
        gp.moveTo(x1[0], y1[0]); // Start the path

        // Connect all points
        for (int i = 1; i < z; i++) {
            gp.lineTo(x1[i], y1[i]);
        }
        gp.closePath(); // Close the star shape

        // Draw multiple star outlines in rainbow colors
        for (int i = 0; i < vibgyorColors.length; i++) {
            g2d.setColor(vibgyorColors[i]);
            g2d.draw(gp);  // Draw the star outline
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VIBGYOR Star");
        practice_activity1 panel = new practice_activity1();
        frame.add(panel);
        frame.setSize(1000, 1000);  // Adjusted size for better visualization
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
