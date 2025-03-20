package project;

import util.Util_Cluster;

import javax.swing.*;
import java.awt.*;

/**
 * The initial version of the cluster drawing algorithm. The data points are hard-coded.
 * @version 1.0.0
 * @since 1.0.0
 */
public class Draw_Cluster extends JPanel {

    private final int[] yG1;
    private final int x;
    private final int[] yG2;
    private final int[] yG3;

    public Draw_Cluster() {
        x = 200;
        yG1 = new int[]{50, 80, 110};
        yG2 = new int[]{140, 170, 200};
        yG3 = new int[]{230, 260, 290};
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor(Color.RED);
        drawPoints(g, x, yG1);

        g.setColor(Color.BLUE);
        drawPoints(g, x, yG2);

        g.setColor(Color.GREEN);
        drawPoints(g, x, yG3);
    }

    private void drawPoints(Graphics g, int x, int[] yValues) {
        for (int y : yValues) {
            g.fillOval(x, y, 10,
                    10);
        }
    }

    public static void main(String[] args) {

        Util_Cluster.showFrame(new Draw_Cluster());
    }

}