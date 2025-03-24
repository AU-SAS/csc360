import javax.swing.*;
import java.awt.*;

public class DrawLine extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawLine(50, 50, 200, 200); // Draw a line from (50,50) to (200,200)
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Draw Line");
        DrawLine panel = new DrawLine();
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
