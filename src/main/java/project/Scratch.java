package project;

import javax.swing.*;
import java.awt.*;

public class Scratch extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 200; // Fixed x value for all groups
        int[] yG1 = {50, 80, 110};
        int[] yG2 = {140, 170, 200};
        int[] yG3 = {230, 260, 290};

        g.setColor(Color.RED);
        drawPoints(g, x, yG1);

        g.setColor(Color.BLUE);
        drawPoints(g, x, yG2);

        g.setColor(Color.GREEN);
        drawPoints(g, x, yG3);
    }

    private void drawPoints(Graphics g, int x, int[] yValues) {
        for (int y : yValues) {
            g.fillOval(x, y, 10,
                    10);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cluster Plot");
        Scratch panel = new Scratch();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}