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
        int total_circles = 12;

        for (int i = 0; i < total_circles; i++) {
            Color random_colors = new Color(r_color.nextInt(255), r_color.nextInt(255), r_color.nextInt(255));
            g2d.setColor(random_colors);
            g2d.fillOval(start_x, start_y + (i*y_offset), diameter, diameter);
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
