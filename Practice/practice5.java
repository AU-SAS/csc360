import javax.swing.*;
import java.awt.*;

public class practice5 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("PNG Image Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Leisha\\Downloads\\Realism and Naturalism theatre with description in the image in the ratio 4_5.png");

        JLabel label = new JLabel(imageIcon);

        frame.add(label);
        frame.setVisible(true);
    }
}
