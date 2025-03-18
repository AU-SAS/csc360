package self_practice;

import javax.swing.*;
import java.awt.*;

public class project_cluster extends JFrame {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        //setting starting coordinates
        int start_x = 20;
        int start_y = 20;
        int diameter = 10;

        for (int i = 0 ; i < 10 ; i++) {
            g2d.setColor(Color.RED);
            g2d.fillOval(start_x, start_y, diameter, diameter);
            start_y += 30;
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
