import javax.swing.*;
import java.awt.*;

public class mandala extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.PINK);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        g2d.fillOval(centerX -25 , centerY -25 , 50, 50);

        g2d.setColor(Color.orange);
        int [] startX ={ centerX - 50 , centerX + 100, centerX + 150, centerX + 100, centerX - 50 };
        int[] startY = { centerY};

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mandala Art");
        mandala panel = new mandala();

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}