import javax.swing.*;
import java.awt.*;

class DrawRectangleAndSquare extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set the line color to black
        g2d.setColor(Color.BLACK);

        // Draw a rectangle using 4 lines
        int rectX = 50, rectY = 50, rectWidth = 150, rectHeight = 100;
        g2d.drawLine(rectX, rectY, rectX + rectWidth, rectY);                   // Top line
        g2d.drawLine(rectX, rectY, rectX, rectY + rectHeight);                  // Left line
        g2d.drawLine(rectX + rectWidth, rectY, rectX + rectWidth, rectY + rectHeight); // Right line
        g2d.drawLine(rectX, rectY + rectHeight, rectX + rectWidth, rectY + rectHeight); // Bottom line

        // Draw a square using 4 lines
        int squareX = 250, squareY = 50, squareSize = 100;
        g2d.drawLine(squareX, squareY, squareX + squareSize, squareY);               // Top line
        g2d.drawLine(squareX, squareY, squareX, squareY + squareSize);               // Left line
        g2d.drawLine(squareX + squareSize, squareY, squareX + squareSize, squareY + squareSize); // Right line
        g2d.drawLine(squareX, squareY + squareSize, squareX + squareSize, squareY + squareSize); // Bottom line
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Draw Rectangle and Square Using Lines");
        DrawRectangleAndSquare panel = new DrawRectangleAndSquare();
        frame.add(panel);
        frame.setSize(450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
