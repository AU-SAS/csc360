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
    private final int offset;
    private final int[] yG2;
    private final int[] yG3;
    private final Color[] colors;
    private final String[] labels;


    private int k = 3;
    private int[][] data;

    public Draw_Cluster() {
        offset = 50;
        data = new int[][]{
                {50, 80, 110},
                {140, 170, 200},
                {230, 260, 290}
        };
        colors = new Color[]{Color.RED, Color.BLUE, Color.GREEN};
        labels = new String[]{"G1", "G2", "G3"};

        yG1 = new int[]{50, 80, 110};
        yG2 = new int[]{140, 170, 200};
        yG3 = new int[]{230, 260, 290};
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        for (int i = 0; i < k; i++) {
            g.setColor(colors[i]);
            for (int y : data[i]) {
                g.fillOval(offset, y, 10, 10);
            }
        }

    }

    public static void main(String[] args) {
        Util_Cluster.showFrame(new Draw_Cluster());
    }

}