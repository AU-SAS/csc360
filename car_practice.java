import javax.swing.*;
import java.awt.*;

public class car_practice extends JPanel{

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set antialiasing for smoother shapes
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the car
        int[] xCoordinates = {30,100,200,400,550,550,410,390,370,170,150,130,30,30};
        int[] yCoordinates = {150,150,50,50,150,200,200,230,200,200,230,200,200,150};
        g2d.setColor(Color.yellow);
        g2d.fillPolygon(xCoordinates, yCoordinates, 13);
        g2d.setColor(Color.black);
        g2d.drawPolygon(xCoordinates, yCoordinates, xCoordinates.length);

        // Draw wheels (front and rear) as circles
        g2d.setColor(Color.BLACK);
        g2d.fillOval(120, 200, 60, 60); // Front wheel
        g2d.fillOval(360, 200, 60, 60); // Rear wheel

    }

    public static void main(String[] args)
    {
        // Create a JFrame to hold the JPanel
        JFrame frame = new JFrame("2D Car Drawing");
        car_practice panel = new car_practice();

        frame.add(panel);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}


