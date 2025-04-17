import javax.swing.*;
import java.awt.*;

public class connections extends JPanel {

    private final Color[] dotColors = {
            new Color(102, 204, 255), new Color(153, 102, 255), new Color(0, 204, 153),
            new Color(153, 0, 204), new Color(102, 0, 153), new Color(255, 153, 102),
            new Color(51, 0, 204), new Color(255, 0, 0), new Color(153, 255, 102),
            new Color(0, 255, 204)
    };

    private final int dotSize = 20;
    private final int x1 = 50;   // Column 1
    private final int x2 = 180;  // Column 2
    private final int x3 = 310;  // Column 3
    private final int yBase = 40;
    private final int gap = 25;
    private final int groupBoxPadding = 10;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int[] yPositions = new int[10];

        // Column 1: 10 vertical dots
        for (int i = 0; i < 10; i++) {
            int y = yBase + i * gap;
            yPositions[i] = y;
            g2d.setColor(dotColors[i]);
            g2d.fillOval(x1, y, dotSize, dotSize);
        }

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));

        // Draw Group Rectangles (Clusters)
        int boxWidth = dotSize + 20;
        drawGroupBox(g2d, x1 - groupBoxPadding, yPositions[0], yPositions[2] + dotSize); // Group of 3
        drawGroupBox(g2d, x1 - groupBoxPadding, yPositions[3], yPositions[7] + dotSize); // Group of 5
        drawGroupBox(g2d, x1 - groupBoxPadding, yPositions[8], yPositions[9] + dotSize); // Group of 2

        // COLUMN 2
        // Merge Cluster 3+5
        int y3 = getMid(yPositions[0], yPositions[2]);
        int y5 = getMid(yPositions[3], yPositions[7]);
        int yNode1 = (y3 + y5) / 2;

        drawConnection(g2d, x1 + boxWidth, y3 + dotSize / 2, x2, yNode1 + dotSize / 2);
        drawConnection(g2d, x1 + boxWidth, y5 + dotSize / 2, x2, yNode1 + dotSize / 2);

        g2d.setColor(new Color(204, 153, 255)); // Light purple
        g2d.fillOval(x2, yNode1, dotSize, dotSize);

        // Merge Cluster 5+2
        int y2 = getMid(yPositions[8], yPositions[9]);
        int yNode2 = (y5 + y2) / 2;

        drawConnection(g2d, x1 + boxWidth, y5 + dotSize / 2, x2, yNode2 + dotSize / 2);
        drawConnection(g2d, x1 + boxWidth, y2 + dotSize / 2, x2, yNode2 + dotSize / 2);

        g2d.setColor(new Color(153, 255, 255)); // Light blue
        g2d.fillOval(x2, yNode2, dotSize, dotSize);

        // COLUMN 3 - Final Merge
        int yFinal = (yNode1 + yNode2) / 2;
        drawConnection(g2d, x2 + dotSize, yNode1 + dotSize / 2, x3, yFinal + dotSize / 2);
        drawConnection(g2d, x2 + dotSize, yNode2 + dotSize / 2, x3, yFinal + dotSize / 2);

        g2d.setColor(new Color(255, 51, 153)); // Final magenta cluster
        g2d.fillOval(x3, yFinal, dotSize + 10, dotSize + 10);
        g2d.drawRect(x3 - 5, yFinal - 5, dotSize + 20, dotSize + 20); // Final boxed node
    }

    private void drawGroupBox(Graphics2D g, int x, int yTop, int yBottom) {
        g.drawRect(x, yTop, dotSize + 20, yBottom - yTop);
    }

    private void drawConnection(Graphics2D g, int x1, int y1, int x2, int y2) {
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
    }

    private int getMid(int y1, int y2) {
        return (y1 + y2) / 2;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("connections");
        frame.add(new connections());
        frame.setSize(450, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
