import javax.swing.*;
import java.awt.*;

public class ClusteringActivity5 extends JPanel {

    private final Color[] nodeColors = {
            Color.RED,
            Color.ORANGE,
            Color.GREEN,
            Color.BLUE,
            Color.MAGENTA,
            Color.CYAN,
            Color.PINK
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int radius = 14;

        // Define coordinates for each level
        int rootX = 250, rootY = 50;
        int[] middleX = {150, 350};
        int middleY = 130;
        int[][] leafXY = {
                {100, 210}, {200, 210},
                {300, 210}, {400, 210}
        };

        // Draw root node
        g2d.setColor(nodeColors[0]);
        g2d.fillOval(rootX - radius / 2, rootY - radius / 2, radius, radius);

        // Draw branches to middle level
        for (int i = 0; i < middleX.length; i++) {
            g2d.setColor(Color.BLACK);
            g2d.drawLine(rootX, rootY, middleX[i], middleY);

            g2d.setColor(nodeColors[i + 1]);
            g2d.fillOval(middleX[i] - radius / 2, middleY - radius / 2, radius, radius);
        }

        // Draw branches to leaf level
        int colorIndex = 3;
        for (int i = 0; i < leafXY.length; i++) {
            int parentX = (i < 2) ? middleX[0] : middleX[1]; // Left or right branch
            int parentY = middleY;
            int leafX = leafXY[i][0];
            int leafY = leafXY[i][1];

            g2d.setColor(Color.BLACK);
            g2d.drawLine(parentX, parentY, leafX, leafY);

            g2d.setColor(nodeColors[colorIndex++ % nodeColors.length]);
            g2d.fillOval(leafX - radius / 2, leafY - radius / 2, radius, radius);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tree Cluster");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.add(new ClusteringActivity5());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
