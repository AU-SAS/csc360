import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageReader {
    public static void main(String[] args) {
        try {
            File file = new File("C:/Users/PARIDHI/Downloads/CSI Holi.png");
            BufferedImage image = ImageIO.read(file);

            if (image != null) {
                JFrame frame = new JFrame("Image Viewer");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(image.getWidth(), image.getHeight());
                ImageIcon icon = new ImageIcon(image);
                JLabel label = new JLabel(icon);
                frame.add(label);
                frame.pack();
                frame.setVisible(true);
            } else {
                System.out.println("Error: Could not load image.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
