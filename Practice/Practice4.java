import javax.swing.*;
import java.awt.*;

public class Practice4 extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));


        int[][] positions = {
                {50, 100}, {200, 50}, {350, 150}, {500, 80},
                {650, 180}, {800, 100}, {950, 150}, {1100, 60}
        };

        int triSize = 80;


        for (int[] pos : positions) {
            int x = pos[0], y = pos[1];


            int[] xPoints = {x, x + triSize, x + triSize / 2};
            int[] yPoints = {y + triSize, y + triSize, y};


            g2d.setColor(Color.yellow);
            g2d.fillPolygon(xPoints, yPoints, 3);


            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(xPoints, yPoints, 3);


            g2d.setColor(Color.RED);
            int dotSize = 8;
            g2d.fillOval(x - dotSize / 2, y + triSize - dotSize / 2, dotSize, dotSize);
            g2d.fillOval(x + triSize - dotSize / 2, y + triSize - dotSize / 2, dotSize, dotSize);
            g2d.fillOval(x + triSize / 2 - dotSize / 2, y - dotSize / 2, dotSize, dotSize);
        }


        g2d.setColor(Color.BLUE);
        for (int i = 0; i < positions.length - 1; i++) {
            int x1 = positions[i][0] + triSize / 2, y1 = positions[i][1];
            int x2 = positions[i + 1][0] + triSize / 2, y2 = positions[i + 1][1];
            g2d.drawLine(x1, y1, x2, y2);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flowchart with Triangles and Dots");
        Practice4 panel = new Practice4();
        frame.add(panel);
        frame.setSize(1200, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
