import javax.swing.*;
import java.awt.*;

public class Practice1 extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);

        int x = 150;
        int[] y = {50, 100, 150, 200, 250, 300};

        for (int i = 0; i < 6; i++) {
            g2d.fillOval(x - 5, y[i] - 5, 10, 10);
        }

        g2d.setColor(Color.BLUE);


        g2d.drawLine(x, y[0], x + 40, y[0]);
        g2d.drawLine(x + 40, y[0], x + 40, y[1]);
        g2d.drawLine(x + 40, y[1], x, y[1]);
        g2d.drawLine(x + 50, (y[0] + y[1]) / 2, x - 40, (y[0] + y[1]) / 2);


        g2d.drawLine(x, y[2], x - 40, y[2]);
        g2d.drawLine(x - 40, y[2], x - 40, y[3]);
        g2d.drawLine(x - 40, y[3], x, y[3]);
        g2d.drawLine(x - 50, (y[2] + y[3]) / 2, x - 40, (y[2] + y[3]) / 2);


        g2d.drawLine(x, y[4], x - 40, y[4]);
        g2d.drawLine(x - 40, y[4], x - 40, y[5]);
        g2d.drawLine(x - 40, y[5], x, y[5]);
        g2d.drawLine(x - 50, (y[4] + y[5]) / 2, x - 40, (y[4] + y[5]) / 2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clustering Activity");
        Practice1 panel = new Practice1();
        frame.add(panel);
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
