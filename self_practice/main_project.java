package self_practice;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class main_project extends JPanel {
    private int k; //user will input here
    private Random r_color; //making it a global variable to randomise color
    private ArrayList<Point> points; // to store the positions of circles
    private ArrayList<ArrayList<Integer>> clusters; // will store index of points which will be grouped into clusters

    //constructor for initialising variables to generate initial points
    public main_project(int k) {
        this.k = k;
        this.r_color = new Random();
        this.points = new ArrayList<>();
        this.clusters = new ArrayList<>();

        //left side k circles alignment
        for (int i = 0; i < k; i++) { //i is the num of current circle
            int x =40; //all would be vertically in same line
            int y= 20 + (i*40)%400; // 400% added so it doesn't overflow from the screen (400 pixels)
            points.add(new Point(x, y)); //saves circle position
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2)); //keeps the stroke thick
    }



    public static void main(String[] args) {
        //user inputs num of circles
        String input = JOptionPane.showInputDialog("Enter the number of circles (k):"); //input as string
        int k = Integer.parseInt(input);  //converting it to int

        //setting up the panel
        main_project panel = new main_project(k);
        JFrame frame = new JFrame("Project - Cluster Tree");
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
