import javax.swing.*;
import java.awt.*;

class TwoTrianglesInLine extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Triangle 1 - Left
        int[] x1 = {50, 100, 0};
        int[] y1 = {50, 150, 150};

        // Triangle 2 - Right (shifted horizontally by 150 units)
        int[] x2 = {200, 250, 150};
        int[] y2 = {50, 150, 150};

        // Draw Triangle 1
        g2d.setColor(Color.ORANGE);
        g2d.fillPolygon(x1, y1, 3);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(x1, y1, 3);

        // Draw Triangle 2
        g2d.setColor(Color.CYAN);
        g2d.fillPolygon(x2, y2, 3);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(x2, y2, 3);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Two Triangles Side by Side");
        TwoTrianglesInLine panel = new TwoTrianglesInLine();
        frame.add(panel);
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
