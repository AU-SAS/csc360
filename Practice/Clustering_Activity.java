import javax.swing.*;
import java.awt.*;

public class Clustering_Activity extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);

        int x = 50; // same x coordinate
        int[] y = {50, 100, 150, 200, 250, 300}; // y coordinate


        for (int i = 0; i < 6; i++) {
            g2d.fillOval(x-5 , y[i]-5 , 10, 10);
        }

        g2d.setColor(Color.BLUE);

        // First cluster
        g2d.drawLine(x, y[0], x + 40, y[0]);  // 1stline
        g2d.drawLine(x + 40, y[0], x + 40, y[1]);  // 2ndline
        g2d.drawLine(x + 40, y[1], x, y[1]);  // 3rdline
        g2d.drawLine(x + 50, (y[0] + y[1]) / 2, x + 40, (y[0] + y[1]) / 2);  // Middle line on the right side

        // Second cluster
        g2d.drawLine(x, y[2], x + 40, y[2]);
        g2d.drawLine(x + 40, y[2], x + 40, y[3]);
        g2d.drawLine(x + 40, y[3], x, y[3]);
        g2d.drawLine(x + 50, (y[2] + y[3]) / 2, x + 40, (y[2] + y[3]) / 2);

        // Third cluster
        g2d.drawLine(x, y[4], x + 40, y[4]);
        g2d.drawLine(x + 40, y[4], x + 40, y[5]);
        g2d.drawLine(x + 40, y[5], x, y[5]);
        g2d.drawLine(x + 50, (y[4] + y[5]) / 2, x + 40, (y[4] + y[5]) / 2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clustering Activity");
        Clustering_Activity panel = new Clustering_Activity();
        frame.add(panel);
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centers the frame
        frame.setVisible(true);
    }
}
