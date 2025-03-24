package main.java;
import jdk.swing.interop.DragSourceContextWrapper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DrawTree extends JPanel{
    public static final int FRAME_WIDTH = 1280;
    public static final int FRAME_HEIGHT = 720;
    ArrayList<Integer> clusters = new ArrayList<>();
    Scanner in = new Scanner(System.in);
    int totalDots = 0;
    int numClusters = 0;
    int a = 0;

    public void drawDot(Graphics2D g2d, int y){
        g2d.setColor(Color.BLACK);
        g2d.fillOval(15, y - 2, 8, 8);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int spacing = (int)(FRAME_HEIGHT/(totalDots + 1));
        if(spacing <= 5){
            System.out.println("too many dots");
            System.exit(0);
        }
        for(int i = 0; i < totalDots; i ++){
            drawDot(g2d, (i+1)*spacing);
        }
    }

    public void execute(){
        do{
            System.out.println("Enter a positive number of elements in cluster " + (numClusters + 1) + ".");
            System.out.println("Or enter anything else except a number to confirm.");
            try {
                // checking valid integer using parseInt()
                // method
                a = Integer.parseInt(in.nextLine());
                numClusters ++;
                clusters.add(a);
                totalDots += a;
            }
            catch (NumberFormatException e) {
                break;
            }
        }
        while(a > 0);
        System.out.println(clusters);
    }

    public static void main(String[] args) {
        DrawTree tree = new DrawTree();
        tree.execute();
        JFrame frame = new JFrame("Project");
        frame.add(tree);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}