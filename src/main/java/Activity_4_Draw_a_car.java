import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class Activity_4_Draw_a_car extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing for smoother lines
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a GeneralPath to draw the car
        GeneralPath gp = new GeneralPath();

        // Start at the lower left corner of the car
        gp.moveTo(60, 120);
        gp.lineTo(80, 120); // Front underbody
        gp.quadTo(90, 140, 100, 120); // Front wheel
        gp.lineTo(160, 120); // Middle underbody
        gp.quadTo(170, 140, 180, 120); // Rear wheel
        gp.lineTo(200, 120); // Rear underbody
        gp.curveTo(195, 100, 200, 80, 160, 80); // Rear curve
        gp.lineTo(110, 80); // Roof
        gp.lineTo(90, 100); // Windscreen
        gp.lineTo(60, 100); // Bonnet
        gp.lineTo(60, 120); // Close the car shape

        // Draw the car outline
        g2d.setColor(Color.BLUE); // Set the car color
        g2d.draw(gp);

        // Fill the car shape with a color
        g2d.setColor(new Color(135, 206, 250)); // Light blue fill
        g2d.fill(gp);

        // Draw wheels (front and rear) as circles
        g2d.setColor(Color.BLACK);
        g2d.fillOval(85, 120, 20, 20); // Front wheel
        g2d.fillOval(165, 120, 20, 20); // Rear wheel
    }

    public static void main(String[] args)
    {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("2D Car Drawing");
        Activity_4_Draw_a_car panel = new Activity_4_Draw_a_car();

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}