package self_practice;

import javax.swing.*;
import java.awt.*;

public class project_cluster extends JFrame {

    //number of clusters we need

    public project_cluster() {
        //setting up the panel
        setTitle("Project Cluster");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);


    }



    public static void main(String[] args) {

        new project_cluster();
    }
}
