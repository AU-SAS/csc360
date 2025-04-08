import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {
    private int rows;
    private int cols;


    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / cols;
        int cellHeight = height / rows;

        g2d.setColor(Color.BLACK);

        for (int i = 0; i <= cols; i++) {
            int x = i * cellWidth;
            g2d.drawLine(x, 0, x, height);
        }


        for (int i = 0; i <= rows; i++) {
            int y = i * cellHeight;
            g2d.drawLine(0, y, width, y);
        }
    }

    public static void main(String[] args) {
        int rows = 5;
        int cols = 5;

        JFrame frame = new JFrame("Grid");
        Grid panel = new Grid(rows, cols);

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
