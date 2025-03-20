import javax.swing.*;
import java.awt.*;

import java.util.*;

public class projectdemo extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Set dot color

        // Draw some dots
        int startX= 20;
        int startY= 20;
        int dotsize=12;

        Random randomcolor = new Random();

        for(int i = 0; i < 9 ; i++)
        {
            Color color = new Color(randomcolor.nextInt(255),randomcolor.nextInt(255),randomcolor.nextInt(255));
            g2d.setColor(color);
            g2d.fillOval(startX, startY , dotsize, dotsize);
            startY += 30;
    }
        g2d.setColor(Color.pink);
        int[] Xpoint0 = {20,50,50,20};
        int[] Ypoint0 = {20,20,92,92};
        g2d.drawPolygon(Xpoint0, Ypoint0, 4);

        int[] Xpoint1 = {20,50,50,20};
        int[] Ypoint1 = {95,95,(31*5),(31*5)};
        g2d.drawPolygon(Xpoint1, Ypoint1, 4);

        int[] Xpoint2 = {20,50,50,20};
        int[] Ypoint2 = {155,155,(31*9),(31*9)};
        g2d.drawPolygon(Xpoint2, Ypoint2, 4);

        g2d.setColor(Color.BLACK);
        g2d.drawLine(50,55,100,55);
        g2d.drawLine(50,120,100,120);
        g2d.drawLine(50,220,100,220);

        Color color = new Color(randomcolor.nextInt(255),randomcolor.nextInt(255),randomcolor.nextInt(255));
        g2d.setColor(color);
        g2d.fillOval(97,50, dotsize, dotsize);
        g2d.fillOval(97,115, dotsize, dotsize);
        g2d.fillOval(97,215,dotsize,dotsize);

    }

    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Demo");
        projectdemo panel = new projectdemo();

        frame.add(panel);
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}