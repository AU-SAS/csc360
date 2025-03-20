import javax.swing.*;
import java.awt.*;

public class Clustering_Activity3 extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);


        int rootX = 150, rootY = 50;
        g2d.fillOval(rootX - 5, rootY - 5, 10, 10);


        int x1 = 120, y1 = 100;
        int x2 = 180, y2 = 100;
        g2d.fillOval(x1 - 5, y1 - 5, 10, 10);
        g2d.fillOval(x2 - 5, y2 - 5, 10, 10);
        g2d.drawLine(rootX, rootY, x1, y1);
        g2d.drawLine(rootX, rootY, x2, y2);


        int x3 = 100, y3 = 150;
        int x4 = 140, y4 = 150;
        int x5 = 160, y5 = 150;
        int x6 = 200, y6 = 150;
        g2d.fillOval(x3 - 5, y3 - 5, 10, 10);
        g2d.fillOval(x4 - 5, y4 - 5, 10, 10);
        g2d.fillOval(x5 - 5, y5 - 5, 10, 10);
        g2d.fillOval(x6 - 5, y6 - 5, 10, 10);

        g2d.drawLine(x1, y1, x3, y3);
        g2d.drawLine(x1, y1, x4, y4);
        g2d.drawLine(x2, y2, x5, y5);
        g2d.drawLine(x2, y2, x6, y6);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clustering_Activity3");
        Clustering_Activity3 panel = new Clustering_Activity3();
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
