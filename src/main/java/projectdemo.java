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
        int[] Ypoint2 = {160,160,(31*9),(31*9)};
        g2d.drawPolygon(Xpoint2, Ypoint2, 4);

        g2d.setColor(Color.BLACK);
        g2d.drawLine(50,55,100,55);
        g2d.drawLine(50,120,100,120);
        g2d.drawLine(50,220,100,220);

        Color color0 = new Color(randomcolor.nextInt(255),randomcolor.nextInt(255),randomcolor.nextInt(255));
        g2d.setColor(color0);
        dotsize = dotsize += 5;
        g2d.fillOval(110,50, dotsize, dotsize);
        g2d.fillOval(110,115, dotsize, dotsize);
        g2d.fillOval(110,215,dotsize,dotsize);

        g2d.setColor(Color.PINK);
        int[] Xpoint3 = {100,140,140,100};
        int[] Ypoint3 = {30,30,90,90};
        g2d.drawPolygon(Xpoint3, Ypoint3, 4);

        int[] Xpoint4 = {100,140,140,100};
        int[] Ypoint4 = {110,110,250,250};
        g2d.drawPolygon(Xpoint4, Ypoint4, 4);

        g2d.setColor(Color.BLACK);
        g2d.drawLine(140,55, 190,55);
        g2d.drawLine(140, 180, 190, 180);

        int start_y = 45;

        for(int i = 0; i < 2 ; i++) {
            Color color1 = new Color(randomcolor.nextInt(255), randomcolor.nextInt(255), randomcolor.nextInt(255));
            g2d.setColor(color1);
            dotsize = dotsize += 5;
            g2d.fillOval(195, start_y, dotsize, dotsize);
            start_y += 125;
        }


        g2d.setColor(Color.PINK);
        int[] Xpoint5 = {190,230,230,190};
        int[] Ypoint5 = {35,35,220,220};
        g2d.drawPolygon(Xpoint5, Ypoint5, 4);

        g2d.setColor(Color.BLACK);
        g2d.drawLine(230,120,280,120);

        Color color2 = new Color(randomcolor.nextInt(255),randomcolor.nextInt(255),randomcolor.nextInt(255));
        g2d.setColor(color2);
        dotsize = dotsize += 5;
        g2d.fillOval(280,110, dotsize, dotsize);


    }

    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Demo");
        projectdemo panel = new projectdemo();

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}