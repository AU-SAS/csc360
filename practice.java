import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class practice extends JPanel {
    private String selectedShape = "Circle"; // Default shape
    private Color selectedColor = Color.WHITE; // Default color

    public practice() {
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);
    }

    public void setSelectedShape(String shape) {
        this.selectedShape = shape;
        repaint();
    }

    public void setSelectedColor(Color color) {
        this.selectedColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(selectedColor);

        // Center position
        int x = getWidth() / 2 - 50;
        int y = getHeight() / 2 - 50;

        switch (selectedShape) {
            case "Circle":
                g2d.fill(new Ellipse2D.Double(x, y, 100, 100));
                break;
            case "Rectangle":
                g2d.fill(new Rectangle2D.Double(x, y, 120, 80));
                break;
            case "Triangle":
                Path2D triangle = new Path2D.Double();
                triangle.moveTo(x + 60, y);
                triangle.lineTo(x, y + 100);
                triangle.lineTo(x + 120, y + 100);
                triangle.closePath();
                g2d.fill(triangle);
                break;
            case "Polygon":
                int[] px = {x + 50, x + 20, x + 80, x + 10, x + 90};
                int[] py = {y, y + 80, y + 80, y + 40, y + 40};
                g2d.fillPolygon(px, py, 5);
                break;
            case "Oval":
                g2d.fill(new Ellipse2D.Double(x, y, 120, 80));
                break;
        }
    }

    private static JPanel createRightPanel(practice visualizer) {
        JPanel rightPanel = new JPanel(new GridLayout(7, 1));
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(75, 0, 130), new Color(148, 0, 211)};
        String[] colorNames = {"Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet"};
        for (int i = 0; i < colors.length; i++) {
            JButton button = new JButton(colorNames[i]);
            button.setBackground(colors[i]);
            button.setOpaque(true);
            button.setBorderPainted(false);
            int index = i;
            button.addActionListener(e -> visualizer.setSelectedColor(colors[index]));
            rightPanel.add(button);
        }
        return rightPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shape & Color Visualizer");
        practice visualizer = new practice();

        JPanel leftPanel = new JPanel(new GridLayout(5, 1));
        String[] shapes = {"Circle", "Rectangle", "Triangle", "Polygon", "Oval"};
        for (String shape : shapes) {
            JButton button = new JButton(shape);
            button.addActionListener(e -> visualizer.setSelectedShape(shape));
            leftPanel.add(button);
        }

        JPanel rightPanel = createRightPanel(visualizer);

        frame.setLayout(new BorderLayout());
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(visualizer, BorderLayout.CENTER);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
