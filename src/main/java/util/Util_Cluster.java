package util;

import project.Draw_Cluster;

import javax.swing.*;

public class Util_Cluster {

    public static void showFrame(Draw_Cluster panel) {
        JFrame frame = new JFrame("Cluster Plot");
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
