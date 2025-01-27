import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SimpleJava2DExample extends Frame {
    // Constructor
    SimpleJava2DExample() {
        // Add a window listener to handle window closing
        addWindowListener(new MyFinishWindow());
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("Hello world!", 30, 50);
    }

    public static void main(String[] argv) {
        // Create an instance of the Frame
        SimpleJava2DExample f = new SimpleJava2DExample();
        f.setTitle("The first Java 2D program");
        f.setSize(350, 80);
        f.setVisible(true);
    }
}

// Class to handle window close event
class MyFinishWindow extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0); // Close the application
    }
}