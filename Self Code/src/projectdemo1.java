import javax.swing.*;
import java.awt.*;

import java.util.*;

public class projectdemo1 extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Random randomcolor = new Random();
        int startX = 20;
        int startY = 20;
        int dotsize = 12;

        // 9 dots column
        for (int i = 0; i < 9; i++) {
            g2d.setColor(new Color(randomcolor.nextInt(255), randomcolor.nextInt(255), randomcolor.nextInt(255)));
            g2d.fillOval(startX, startY, dotsize, dotsize);
            startY += 30;
        }

        // 3 Cluster of 3 dots each
        g2d.setColor(Color.pink);
        for (int i = 0; i < 3; i++) {
            int boxY = 20 + i * 90;
            int[] xPoints = {20, 50, 50, 20};
            int[] yPoints = {boxY, boxY, boxY + 72, boxY + 72};
            g2d.drawPolygon(xPoints, yPoints, 4);
        }

        // lines connecting the cluster
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < 3; i++) {
            int lineY = 20 + i * 90 + 36;  //
            g2d.drawLine(50, lineY, 100, lineY);
        }


        // Middle dots
        dotsize += 5;
        for (int i = 0; i < 3; i++) {
            g2d.setColor(new Color(randomcolor.nextInt(255), randomcolor.nextInt(255), randomcolor.nextInt(255)));
            g2d.fillOval(110, 50 + i * 65, dotsize, dotsize);
        }

        // 2nd level cluster
        g2d.setColor(Color.PINK);
        int[][] middleBoxes = {
                {30, 90},  // top box
                {110, 250} // merged second and third
        };
        for (int[] box : middleBoxes) {
            int[] xPoints = {100, 140, 140, 100};
            int[] yPoints = {box[0], box[0], box[1], box[1]};
            g2d.drawPolygon(xPoints, yPoints, 4);
        }

        // Lines to next-level nodes
        g2d.setColor(Color.BLACK);
        g2d.drawLine(140, 55, 190, 55);    // first
        g2d.drawLine(140, 180, 190, 180);  // merged (second + third)

        // Right-side dots
        dotsize += 5;
        for (int i = 0; i < 2; i++) {
            g2d.setColor(new Color(randomcolor.nextInt(255), randomcolor.nextInt(255), randomcolor.nextInt(255)));
            g2d.fillOval(195, 45 + i * 125, dotsize, dotsize);
        }

        // Box around second-level cluster
        g2d.setColor(Color.PINK);
        int[] Xpoint5 = {190, 230, 230, 190};
        int[] Ypoint5 = {35, 35, 220, 220};
        g2d.drawPolygon(Xpoint5, Ypoint5, 4);

        // Final merge to one node
        g2d.setColor(Color.BLACK);
        g2d.drawLine(230, 120, 280, 120);

        dotsize += 5;
        g2d.setColor(new Color(randomcolor.nextInt(255), randomcolor.nextInt(255), randomcolor.nextInt(255)));
        g2d.fillOval(280, 110, dotsize, dotsize);
    }





    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Demo");
        projectdemo1 panel = new projectdemo1();

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

}