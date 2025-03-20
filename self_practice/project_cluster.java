package self_practice;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class project_cluster extends JPanel  {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.BLACK);

        //setting starting coordinates
        int start_x = 40;
        int start_y = 20;
        int diameter = 10;
        Random r_color = new Random();


        for (int i = 0 ; i < 10 ; i++) {
            Color random_colors = new Color(r_color.nextInt(255), r_color.nextInt(255), r_color.nextInt(255));
            g2d.setColor(random_colors);
            g2d.fillOval(start_x, start_y, diameter, diameter);
            start_y += 30;
        }

        int distance_x = 100; // distance of the lines for the tree

        g2d.setColor(Color.BLACK);
        int[] xPoints = {20, 60, 60, 20}; // X coordinates
        int[] yPoints = {10, 10, 92, 92}; // Y coordinates
        g2d.drawPolygon(xPoints, yPoints, 4); // first box (to show a cluster or group)

        g2d.setColor(Color.BLACK);
        int[] xPoints1 = {20, 60, 60, 20}; // X coordinates
        int[] yPoints1 = {95, 95 , 245, 245}; // Y coordinates
        g2d.drawPolygon(xPoints1, yPoints1, 4); // second box (to show a cluster or group)


        g2d.setColor(Color.BLACK);
        int[] xPoints2 = {20, 60, 60, 20}; // X coordinates
        int[] yPoints2 = {250,250,  310, 310}; // Y coordinates
        g2d.drawPolygon(xPoints2, yPoints2, 4); // third box (to show a cluster or group)

        g2d.setColor(Color.BLUE); //setting color to blue for the links
        g2d.drawLine(60, (30*2), 120,(30*2)); // first link -- cluster 1
        g2d.drawLine(60, (30*6), 120,(30*6)); // second link -- cluster 2
        g2d.drawLine(60, 280, 120,280); //third link -- cluster 3

        //setting starting coordinates for 2nd cluster
        int start_x1 = 135;
        int start_y1 = 50;
        int diameter1 = 20;
        g2d.setColor(Color.BLACK);

        for (int i = 0 ; i < 3 ; i++) {
            Color random_colors = new Color(r_color.nextInt(255), r_color.nextInt(255), r_color.nextInt(255));
            g2d.setColor(random_colors);
            g2d.fillOval(start_x1, start_y1, diameter1, diameter1);
            start_y1 += 115;
        }




    }



    public static void main(String[] args){
        //setting up the panel
        project_cluster panel = new project_cluster();
        JFrame frame = new JFrame("Cluster Tree");
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);



    }
}
