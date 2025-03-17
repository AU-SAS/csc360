import javax.swing.*;
import java.awt.*;

public class Activity4_drawcar  extends JPanel{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set antialiasing for smoother shapes
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.BLUE);
        int[] xstart ={50,50,80,110,190,210,190,180,170,110,100,90};
        int[] ystart ={230,200,200,180,180,230,230,250,230,230,250,230};
        g2d.drawPolygon(xstart, ystart, xstart.length);

        g2d.setColor(Color.BLACK);
        g2d.fillOval(175, 240, 15,15);
        g2d.fillOval(95, 240, 15,15);






    }

    public static void main(String[] args)
    {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("2D car");
        Activity4_drawcar panel = new Activity4_drawcar();
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }


}
