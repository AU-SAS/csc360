import javax.swing.*;
import java.awt.*;

public class Step_9_Implement_Midpoint_Algorithm_for_Lines extends JPanel
{
    private final int x1, y1, x2, y2;

    public Step_9_Implement_Midpoint_Algorithm_for_Lines(int x1, int y1, int x2, int y2)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);

        // Call the Midpoint line drawing algorithm
        drawMidpointLine(g2d, x1, y1, x2, y2);
    }

    private void drawMidpointLine(Graphics2D g2d, int x1, int y1, int x2, int y2)
    {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int d = 2 * dy - dx; // Initial decision parameter
        int deltaE = 2 * dy; // Increment if E (east pixel) is chosen
        int deltaNE = 2 * (dy - dx); // Increment if NE (northeast pixel) is chosen

        int x = x1;
        int y = y1;

        g2d.fillRect(x, y, 1, 1); // Plot the first point

        while (x < x2) {
            if (d <= 0) { // Choose E pixel
                d += deltaE;
                x++;
            } else { // Choose NE pixel
                d += deltaNE;
                x++;
                y++;
            }
            g2d.fillRect(x, y, 1, 1); // Plot the point
        }
    }

    public static void main(String[] args)
    {
        // Line endpoints
        int x1 = 100, y1 = 100;
        int x2 = 500, y2 = 300;

        // Create a frame to display the line
        JFrame frame = new JFrame("Midpoint Line Drawing Algorithm");
        Step_9_Implement_Midpoint_Algorithm_for_Lines linePanel = new Step_9_Implement_Midpoint_Algorithm_for_Lines(x1, y1, x2, y2);

        frame.add(linePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}