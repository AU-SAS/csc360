package project;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Draw_Cluster extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        Random r_color = new Random();

        // Define dot size
        int dotSize = 6;

        // Layer 1: 3 boxes (BOX 1) - Now vertical
        // G1
        g2d.setColor(Color.BLACK);
        g2d.drawRect(50, 50, 80, 70);
        g2d.drawString("G1", 50, 45);
        drawDotsInBox(g2d, 50, 50, 80, 70, 4, dotSize, r_color);
        // G2
        g2d.drawRect(50, 170, 80, 70);
        g2d.drawString("G2", 50, 165);
        drawDotsInBox(g2d, 50, 170, 80, 70, 3, dotSize, r_color);
        // G3
        g2d.drawRect(50, 290, 80, 70);
        g2d.drawString("G3", 50, 285);
        drawDotsInBox(g2d, 50, 290, 80, 70, 3, dotSize, r_color);

        // Layer 2: 2 boxes (BOX 2)
        // G1
        g2d.drawRect(200, 110, 80, 70);
        g2d.drawString("G1", 200, 105);
        drawDotsInBox(g2d, 200, 110, 80, 70, 7, dotSize, r_color);
        // G2
        g2d.drawRect(200, 230, 80, 70);
        g2d.drawString("G2", 200, 225);
        drawDotsInBox(g2d, 200, 230, 80, 70, 3, dotSize, r_color);

        // Layer 3: 1 box (BOX 3)
        // G1
        g2d.drawRect(350, 170, 80, 70);
        g2d.drawString("G1", 350, 165);
        drawDotsInBox(g2d, 350, 170, 80, 70, 10, dotSize, r_color);


        // Draw connections
        g2d.setColor(Color.BLACK);
        // Layer 1 vertical connections
        g2d.drawLine(90, 120, 90, 170);  // G1 to G2
        g2d.drawLine(90, 240, 90, 290);  // G2 to G3
        // Layer 1 to Layer 2 connections
        g2d.drawLine(130, 85, 200, 145);   // Layer 1 G1 to Layer 2 G1
        g2d.drawLine(130, 205, 200, 145);  // Layer 1 G2 to Layer 2 G1
        g2d.drawLine(130, 325, 200, 265);  // Layer 1 G3 to Layer 2 G2
        // Layer 2 to Layer 3 connections
        g2d.drawLine(280, 145, 350, 205);  // Layer 2 G1 to Layer 3 G1
        g2d.drawLine(280, 265, 350, 205);  // Layer 2 G2 to Layer 3 G1

        // Title
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Cluster Tree (K = 3, Total dots = 10)", 100, 20);
    }

    // Helper method to draw dots in a box
    private void drawDotsInBox(Graphics2D g2d, int x, int y, int width, int height, int numDots, int dotSize, Random r_color) {
        int cols = 2;

        for (int i = 0; i < numDots; i++) {
            int row = i / cols;
            int col = i % cols;

            int dotX = x + (col + 1) * (width / (cols + 1)) - dotSize / 2;
            int dotY = y + (row + 1) * (height / ((int)Math.ceil((double)numDots/cols) + 1)) - dotSize / 2;

            Color randomColor = new Color(r_color.nextInt(255), r_color.nextInt(255), r_color.nextInt(255));
            g2d.setColor(randomColor);
            g2d.fillOval(dotX, dotY, dotSize, dotSize);
        }
    }

    public static void main(String[] args) {
        // Setting up the panel
        Draw_Cluster panel = new Draw_Cluster();
        JFrame frame = new JFrame("Project - Cluster Tree");
        frame.add(panel);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}