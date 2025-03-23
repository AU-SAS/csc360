import javax.swing.*;
import java.awt.*;


public class mandala_art extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        g2d.setColor(new Color(212, 104, 164));
        g2d.fillOval(187,87,60,60);
        g2d.fillOval(224,125,60,60);
        g2d.fillOval(228,175,60,60);
        g2d.fillOval(194,215,60,60);
        g2d.fillOval(138,219,60,60);
        g2d.fillOval(99,181,60,60);
        g2d.fillOval(95,128,60,60);
        g2d.fillOval(133,87,60,60);


        g2d.setColor(new Color(244,213,128));
        g2d.fillOval(centerX - 80, centerY - 80, 160, 160);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(centerX - 80, centerY - 80, 160, 160);

        g2d.setColor(Color.CYAN);
        g2d.fillOval(centerX - 60, centerY - 60, 120, 120);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(centerX - 60, centerY - 60, 120, 120);

        g2d.setColor(Color.PINK);
        g2d.fillOval(centerX -30 , centerY -30 , 60, 60);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(centerX -30 , centerY -30, 60, 60);


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mandala Art");
        mandala_art panel = new mandala_art();

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}