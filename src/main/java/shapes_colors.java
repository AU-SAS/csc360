import javax.swing.*;
import java.awt.*;

public class shapes_colors extends JPanel {

    @Override


    public static void main(String[] args) {
        JFrame frame = new JFrame("VIBGYOR Star");
        practice_activity panel = new practice_activity();
        frame.add(panel);
        frame.setSize(400, 400);  // Adjusted size for better visualization
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
