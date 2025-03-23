package practice;

import javax.swing.*;
import java.awt.*;
public class Homework_Activity_1 extends JFrame
{
    private String currentShape = "None";  // Default: No shape
    private Color currentColor = Color.WHITE; // Default: White color
    public Homework_Activity_1()
    {
        setTitle("Shape and Color Drawer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Left Panel for Shape Buttons
        JPanel shapePanel = new JPanel();
        shapePanel.setLayout(new GridLayout(5, 1));
        String[] shapes = {"Circle", "Rectangle", "Triangle", "Oval", "Convex Polygon"};
        for (String shape : shapes)
        {
            JButton btn = new JButton(shape);
            btn.addActionListener(e -> {currentShape = shape; repaint();});
            shapePanel.add(btn);
        }
        // Right Panel for Color Buttons
        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new GridLayout(7, 1));
        String [] colors = {"V", "I", "B", "G", "Y", "O", "R"};
        Color[] colorValues = {new Color(138, 43, 226), // Violet
                new Color(75, 0, 130),   // Indigo
                Color.BLUE,             // Blue
                Color.GREEN,            // Green
                Color.YELLOW,           // Yellow
                Color.ORANGE,           // Orange
                Color.RED};             // Red
        for (int i = 0; i < colors.length; i++)
        {
            JButton btn = new JButton(colors[i]);
            Color selectedColor = colorValues[i];
            btn.setBackground(selectedColor);
            btn.setForeground(Color.WHITE);
            btn.addActionListener(e -> {currentColor = selectedColor;repaint();});
            colorPanel.add(btn);
        }
        // Drawing Panel
        JPanel drawPanel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.setColor(currentColor);
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                switch (currentShape)
                {
                    case "Circle":
                        g.fillOval(centerX - 50, centerY - 50, 100, 100);
                        break;
                    case "Rectangle":
                        g.fillRect(centerX - 50, centerY - 30, 100, 60);
                        break;
                    case "Triangle":
                        int[] xPoints = {centerX, centerX - 50, centerX + 50};
                        int[] yPoints = {centerY - 50, centerY + 50, centerY + 50};
                        g.fillPolygon(xPoints, yPoints, 3);
                        break;
                    case "Oval":
                        g.fillOval(centerX - 60, centerY - 40, 120, 80);
                        break;
                    case "Convex Polygon":
                        int[] polyX = {centerX - 40, centerX, centerX + 40, centerX + 20, centerX - 20};
                        int[] polyY = {centerY, centerY - 40, centerY, centerY + 40, centerY + 40};
                        g.fillPolygon(polyX, polyY, 5);
                        break;
                }
            }
        };
        add(shapePanel, BorderLayout.WEST);
        add(colorPanel, BorderLayout.EAST);
        add(drawPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    public static void main(String[] args)
    {
        new Homework_Activity_1();
    }
}