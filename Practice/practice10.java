import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class practice10 extends JPanel {
    int[][] points = new int[100][2];
    int[] clusters = new int[100];
    int k = 3;

    public practice10() {
        generateRandomPoints();
        assignClusters();
    }

    void generateRandomPoints() {
        Random rand = new Random();
        for (int i = 0; i < points.length; i++) {
            points[i][0] = rand.nextInt(600);
            points[i][1] = rand.nextInt(400);
        }
    }

    void assignClusters() {
        Random rand = new Random();
        for (int i = 0; i < clusters.length; i++) {
            clusters[i] = rand.nextInt(k);
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN};
        for (int i = 0; i < points.length; i++) {
            g2.setColor(colors[clusters[i]]);
            g2.fillOval(points[i][0], points[i][1], 10, 10);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Cluster Diagram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 450);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new practice10());
        frame.setVisible(true);
    }
}
