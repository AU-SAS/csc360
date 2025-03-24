import javax.swing.*;
import java.awt.*;

public class Practice3 extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

        int[][] positions = {
                {50, 100}, {200, 50}, {350, 150}, {500, 80},
                {650, 180}, {800, 100}, {950, 150}, {1100, 60}
        };

        int rectWidth = 100, rectHeight = 50;


        g2d.setColor(Color.BLACK);
        for (int[] pos : positions) {
            int x = pos[0], y = pos[1];
            g2d.drawRect(x, y, rectWidth, rectHeight);


            g2d.setColor(Color.RED);
            int dotSize = 8;
            g2d.fillOval(x - dotSize / 2, y - dotSize / 2, dotSize, dotSize); // Top-left
            g2d.fillOval(x + rectWidth - dotSize / 2, y - dotSize / 2, dotSize, dotSize); // Top-right
            g2d.fillOval(x - dotSize / 2, y + rectHeight - dotSize / 2, dotSize, dotSize); // Bottom-left
            g2d.fillOval(x + rectWidth - dotSize / 2, y + rectHeight - dotSize / 2, dotSize, dotSize); // Bottom-right
            g2d.setColor(Color.BLACK); // Reset color for the next rectangle
        }


        g2d.setColor(Color.BLUE);
        for (int i = 0; i < positions.length - 1; i++) {
            int x1 = positions[i][0] + rectWidth, y1 = positions[i][1] + rectHeight / 2;
            int x2 = positions[i + 1][0], y2 = positions[i + 1][1] + rectHeight / 2;
            g2d.drawLine(x1, y1, x2, y2);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flowchart with Dots");
        Practice3 panel = new Practice3();
        frame.add(panel);
        frame.setSize(1200, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
