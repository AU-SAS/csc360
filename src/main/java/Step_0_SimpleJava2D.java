import java.awt.*;
public class Step_0_SimpleJava2D extends Frame
{
    // Constructor
    Step_0_SimpleJava2D()
    {
        // Add a window listener to handle window closing
        addWindowListener(new Step_0_SimpleJava2D_Window());
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
        Step_0_SimpleJava2D f = new Step_0_SimpleJava2D();
        f.setTitle("The first Java 2D program");
        f.setSize(350, 80);
        f.setVisible(true);
    }
}

