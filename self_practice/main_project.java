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
