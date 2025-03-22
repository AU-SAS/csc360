import javax.swing.*;
import java.awt.*;

public class Homework1 extends JPanel {
    private String currentShape = "Circle";
    private Color currentColor = Color.WHITE; //default color

    public Homework1() {
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(currentColor);


        switch (currentShape) {
            case "Circle":
                g2d.fillOval( 200,  100,  200, 200);
                g2d.setColor(Color.BLACK);
                g2d.drawOval( 200,  100,  200, 200);

                break;
            case "Rectangle":
                g2d.fillRect(200, 80, 200, 250);

                break;
            case "Triangle":
                int[] xPoints = { 300,  200, 400};
                int[] yPoints = { 100, 300, 300};
                g2d.fillPolygon(xPoints, yPoints, 3);

                break;
            case "Polygon":
                int[] StartX = { 100, 230, 400, 200 , 100};
                int[] StartY = { 100, 160, 300, 300, 250};
                g2d.fillPolygon(StartX, StartY, 5);
                g2d.setColor(Color.BLACK);
                g2d.drawPolygon(StartX, StartY, 5);

                break;
            case "Oval":
                g2d.fillOval( 200,  80,  200,  280);
                g2d.setColor(Color.BLACK);
                g2d.drawOval( 200,  80,  200,  280);

                break;
        }


    }


    public void setShape(String shape) {
        this.currentShape = shape;
        repaint();  //repaint the shape
    }

    public void setColor(Color color) {
        this.currentColor = color;
        repaint(); // repaint the color
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shapes and Colors Visualizer ");
        Homework1 visualizer = new Homework1();

        JPanel shapePanel = new JPanel(new GridLayout(5, 1)); //placing the shapes button
        String[] shapes = {"Circle", "Rectangle", "Triangle", "Polygon", "Oval"};
        for (String shape : shapes) {
            final String selectedShape = shape;
            JButton button = new JButton(selectedShape);
            button.addActionListener(e -> visualizer.setShape(selectedShape));
            shapePanel.add(button);
        }

        JPanel colorPanel = new JPanel(new GridLayout(7, 1)); //color button grid
        String[] colorNames = {"Violet", "Indigo", "Blue", "Green", "Yellow", "Orange", "Red"};
        Color[] vibgyor = {new Color(75, 0, 130),new Color(138, 43, 226), Color.BLUE,
                Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED}; //VIBGYOR
        for (int i = 0; i < vibgyor.length; i++) {
            final Color selectedColor = vibgyor[i];
            JButton button = new JButton(colorNames[i]);
            button.setOpaque(true);
            button.addActionListener(e -> visualizer.setColor(selectedColor));
            colorPanel.add(button);
        }

        frame.setLayout(new BorderLayout());
        frame.add(shapePanel, BorderLayout.WEST); //position of shape button
        frame.add(colorPanel, BorderLayout.EAST); //position of color button
        frame.add(visualizer, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
