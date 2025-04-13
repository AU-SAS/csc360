import javax.swing.*;
import java.awt.*;

import static java.awt.Color.*;

public class HouseDrawing extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the house body
        g.setColor(YELLOW);
        g.fillRect(100, 150, 200, 150); // House body

        // Draw the roof
        g.setColor(RED);
        int[] xPoints = {80, 200, 320}; // X coordinates of the roof
        int[] yPoints = {150, 50, 150};  // Y coordinates of the roof
        g.fillPolygon(xPoints, yPoints, 3); // Draw the roof

        // Draw the door
        g.setColor(GREEN);
        g.fillRect(180, 220, 40, 80); // Door

        // Draw windows
        g.setColor(BLUE);
        g.fillRect(120, 180, 40, 40); // Left window
        g.fillRect(240, 180, 40, 40); // Right window

        // Draw window panes
        g.setColor(WHITE);
        g.drawLine(120, 200, 160, 200); // Horizontal line for left window
        g.drawLine(140, 180, 140, 220); // Vertical line for left window
        g.drawLine(240, 200, 280, 200); // Horizontal line for right window
        g.drawLine(260, 180, 260, 220); // Vertical line for right window
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("House Drawing");
        HouseDrawing houseDrawing = new HouseDrawing();

        frame.add(houseDrawing);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}