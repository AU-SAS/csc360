package practice;

import java.awt.*;
public class SimpleJava2D extends Frame
{
    // Constructor
    SimpleJava2D()
    {
        // Add a window listener to handle window closing
        addWindowListener(new SimpleJava2D_Window());
    }
    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("Hello world!", 30, 50);
    }
    public static void main(String[] argv)
    {
        // Create an instance of the Frame
        SimpleJava2D f = new SimpleJava2D();
        f.setTitle("The first Java 2D program");
        f.setSize(350, 80);
        f.setVisible(true);
    }
}

