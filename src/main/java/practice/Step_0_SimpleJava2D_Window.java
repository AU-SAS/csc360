import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Class to handle window close event
class Step_0_SimpleJava2D_Window extends WindowAdapter
{
    @Override
    public void windowClosing(WindowEvent e)
    {
        System.exit(0); // Close the application
    }
}