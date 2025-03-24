import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class practice2 extends JPanel {
    private static final int RECT_WIDTH = 100;
    private static final int RECT_HEIGHT = 60;
    private static final int NUM_RECTANGLES = 5;

    private Random random = new Random();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

        int startX = 50;
        int startY = 50;

        int[][] points = new int[NUM_RECTANGLES][2];

        for (int i = 0; i < NUM_RECTANGLES; i++) {
            int x = startX + (i * 30);
            int y = startY + (i * 20);


            points[i][0] = x + RECT_WIDTH;
            points[i][1] = y + RECT_HEIGHT / 2;


            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, RECT_WIDTH, RECT_HEIGHT);


            g2d.setColor(Color.RED);
            g2d.fillOval(points[i][0] - 4, points[i][1] - 4, 8, 8);
        }


        for (int i = 0; i < NUM_RECTANGLES - 1; i++) {
            g2d.setColor(getRandomColor());
            g2d.drawLine(points[i][0], points[i][1], points[i + 1][0] - RECT_WIDTH, points[i + 1][1]);
        }
    }

    private Color getRandomColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Overlapping Rectangles with Edge Connections");
        practice2 panel = new practice2();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
