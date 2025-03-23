package self_practice;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class main_project extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //defining paint component
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2)); // defining stroke width to be 2 -- for the whole project
        Random r_color = new Random(); // done to randomise color

        //using offset values for optimisation
        int x_offset = 30;
        int y_offset = 30;

        //first cluster
        int start_x = 40;
        int start_y = 20;
        int diameter = 10;
        int circles_per_cluster = 4;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < circles_per_cluster; j++) {
                Color random_colors = new Color(r_color.nextInt(255), r_color.nextInt(255), r_color.nextInt(255));
                g2d.setColor(random_colors);
                int y1 = start_y + (i*120) + (j*y_offset);
                g2d.fillOval(start_x, y1, diameter, diameter);
            }
        }

        // making boxes around each cluster using loops
        int [][] cluster_box = {
                {10,90}, // first box around 1st cluster
                {95, 245}, // second box -- middle 2nd cluster
                {250, 310}, //third box -- last 3rd cluster
                {315, 370} //fourth box -- last cluster
        };

        int[] link_y_values = new int[cluster_box.length]; //to store y values

        g2d.setColor(Color.BLACK);
        //drawing the boxes using loop
        for (int i = 0; i < cluster_box.length; i++) {
            int[] x_points = {20, 60, 60, 20};
            int[] y_points = {cluster_box[i][0], cluster_box[i][0], cluster_box[i][1], cluster_box[i][1]};
            g2d.drawPolygon(x_points, y_points, 4);

            //adding links between the clusters
            int link_y = cluster_box[i][0] + ((cluster_box[i][1] - cluster_box[i][0]) / 2); // middle of box
            g2d.drawLine(60, link_y, 90, link_y); // links
            link_y_values[i] = link_y; //storing values
        }

        //second layer of dots

        int link_x = 100;
        int diameter2 = 20;
        for (int i = 0; i < cluster_box.length; i++) {
            Color random_colors = new Color(r_color.nextInt(255), r_color.nextInt(255), r_color.nextInt(255));
            g2d.setColor(random_colors);
            int link_y = cluster_box[i][0] + ((cluster_box[i][1] - cluster_box[i][0]) / 2);
            g2d.fillOval(link_x, link_y-10, diameter2, diameter2); // making dots for the second line of clusters
        }

        //making boxes around each cluster

        int[][] cluster_box2 = {
                {link_y_values[0] - 15, link_y_values[1] + 15}, // first box -- 2 dots
                {link_y_values[2] - 15, link_y_values[3] + 15}  // decond box -- 2 dots
        };

        g2d.setColor(Color.BLACK);
        for (int i = 0; i < cluster_box2.length; i++) {
            int[] x_points = {link_x - 10, link_x + 30, link_x + 30, link_x - 10};
            int[] y_points = {cluster_box2[i][0], cluster_box2[i][0], cluster_box2[i][1], cluster_box2[i][1]};
            g2d.drawPolygon(x_points, y_points, 4);
        }





    }



    public static void main(String[] args) {
        //setting up the panel
        main_project panel = new main_project();
        JFrame frame = new JFrame("Project - Cluster Tree");
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
