import javax.swing.*;
import java.awt.*;

package self_practice;

public class outline_strokes extends JFrame {
    public outline_strokes() {
        setTitle("Dynamic Stroke Width");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 500);
    }

    class shapepanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.black);

            int x = 100;
            int y = 100;
            int w = 100; // width
            int h = 100;   //height

        }
    }






    public static void main(String[] args) {
        new outline_strokes();
    }
}
