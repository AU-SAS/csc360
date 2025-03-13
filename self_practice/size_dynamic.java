package self_practice;

import javax.swing.*;
import java.awt.*;

public class size_dynamic extends JFrame {

    private String shapes_main = "empty";
    private Color colors_main = Color.BLACK;
    private int size_height = 100;
    private int size_width = 100;

    public size_dynamic() {
        setTitle("Shapes, Colors, Size");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLayout(new BorderLayout());

        // left - shapes
        JPanel shapepanel = new JPanel();
        shapepanel.setLayout(new GridLayout(5, 1));
        String[] shapes = {"Circle", "Rectangle", "Triangle", "Diamond", "Star"};
        for (int i = 0; i < shapes.length; i++) {
            String selectshape = shapes[i];
            JButton shape = new JButton(shapes[i]);
            shape.addActionListener(e -> {
                shapes_main = selectshape;
                repaint();
            });
            shapepanel.add(shape);
        }

        // right - colors
        JPanel colorpanel = new JPanel();
        colorpanel.setLayout(new GridLayout(7, 1));
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA};
        String[] colorNames = {"Red", "Orange", "Yellow", "Green", "Blue", "Cyan", "Magenta"};

        for (int i = 0; i < colors.length; i++) {
            JButton colorbutton = new JButton(colorNames[i]);
            Color selectcolor = colors[i];
            colorbutton.addActionListener(e -> {
                colors_main = selectcolor;
                repaint();
            });
            colorbutton.setBackground(selectcolor);
            colorpanel.add(colorbutton);
        }

        // bottom - size
        JPanel sizepanel = new JPanel();
        JButton increaseSize = new JButton("Increase Size");
        JButton decreaseSize = new JButton("Decrease Size");

        increaseSize.addActionListener(e -> {
            size_width += 10;
            size_height += 10;
            repaint();
        });

        decreaseSize.addActionListener(e -> {
            if (size_width > 30 && size_height > 30) {
                size_width -= 10;
                size_height -= 10;
                repaint();
            }
        });

        // main panel
        JPanel mainpanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(colors_main);

                int x = (getWidth() - size_width) / 2;    // Center the shape
                int y = (getHeight() - size_height) / 2;  // Center the shape

                switch (shapes_main) {
                    case "Circle":
                        g2d.fillOval(x, y, size_width, size_height);
                        break;

                    case "Rectangle":
                        g2d.fillRect(x, y, size_width, size_height);
                        break;

                    case "Triangle":
                        int[] x_axis = {x + size_width / 2, x, x + size_width};
                        int[] y_axis = {y, y + size_height, y + size_height};
                        g2d.fillPolygon(x_axis, y_axis, 3);
                        break;

                    case "Diamond":
                        int[] x_diamond = {x + size_width / 2, x + size_width, x + size_width / 2, x};
                        int[] y_diamond = {y, y + size_height / 2, y + size_height, y + size_height / 2};
                        g2d.fillPolygon(x_diamond, y_diamond, 4);
                        break;

                    case "Star":
                        // Calculate points for a 5-pointed star
                        double radius = Math.min(size_width, size_height) / 2.0;
                        double innerRadius = radius * 0.381966; // Golden ratio conjugate for star points
                        int[] x_star = new int[10];
                        int[] y_star = new int[10];

                        for (int i = 0; i < 10; i++) {
                            double angle = Math.PI / 2 + (i * Math.PI / 5);
                            double r = (i % 2 == 0) ? radius : innerRadius;
                            x_star[i] = x + size_width / 2 + (int)(Math.cos(angle) * r);
                            y_star[i] = y + size_height / 2 - (int)(Math.sin(angle) * r);
                        }
                        g2d.fillPolygon(x_star, y_star, 10);
                        break;            }
            }
        };

        sizepanel.add(increaseSize);
        sizepanel.add(decreaseSize);

        add(shapepanel, BorderLayout.WEST);
        add(colorpanel, BorderLayout.EAST);
        add(sizepanel, BorderLayout.SOUTH);
        add(mainpanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
         new size_dynamic();
    }
}