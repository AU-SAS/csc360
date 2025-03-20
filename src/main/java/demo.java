
import javax.swing.*;
import java.awt.*;

class DrawTwoCircles extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set color for the first circle
        g2d.setColor(Color.BLUE);
        int x1 = 50, y1 = 50, diameter1 = 100;
        g2d.fillOval(x1, y1, diameter1, diameter1);

        // Set color for the second circle
        g2d.setColor(Color.RED);
        int x2 = 200, y2 = 50, diameter2 = 100;
        g2d.fillOval(x2, y2, diameter2, diameter2);
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Draw Two Circles");
        DrawTwoCircles panel = new DrawTwoCircles();
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
