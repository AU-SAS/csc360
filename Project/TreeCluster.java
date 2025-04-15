import javax.swing.*;
import java.awt.*;

public class TreeCluster extends JPanel {

    private final int[][] nodes = {
            {300, 80},    // Root
            {200, 180},   // Level 1 - Left
            {400, 180},   // Level 1 - Right
            {150, 280},   // Level 2 - Left of Left
            {250, 280},   // Level 2 - Right of Left
            {350, 280},   // Level 2 - Left of Right
            {450, 280}    // Level 2 - Right of Right
    };

    private final int[][] edges = {
            {0, 1}, {0, 2},
            {1, 3}, {1, 4},
            {2, 5}, {2, 6}
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D draw = (Graphics2D) g;
        draw.setColor(new Color(0, 102, 204)); // Deep Blue

        // Draw connections
        for (int[] link : edges) {
            int[] a = nodes[link[0]];
            int[] b = nodes[link[1]];
            draw.drawLine(a[0], a[1], b[0], b[1]);
        }

        // Draw nodes
        for (int[] p : nodes) {
            draw.fillOval(p[0] - 7, p[1] - 7, 14, 14); // Slightly larger circles
        }
    }

    public static void main(String[] args) {
        JFrame app = new JFrame("Graph View");
        app.add(new TreeCluster());
        app.setSize(600, 500); // Bigger canvas
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
    }
}
