import javax.swing.*;
import java.awt.*;

public class projectdemo extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Set dot color

        // Draw some dots
        int startX= 20;
        int startY= 20;
        int dotsize=10;

        for(int i = 0; i < 10 ; i++)
        {
            g2d.setColor(Color.BLUE);
            g2d.fillOval(startX, startY , dotsize, dotsize);
            startY += 30;
        }
    }

    public static void main(String[] args) {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Dots");
        projectdemo panel = new projectdemo();

        frame.add(panel);
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}