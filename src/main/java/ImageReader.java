import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageReader {
    public static void main(String[] args) {
        try {
            // Create a File object pointing to the PNG image
            File file = new File("C:/Users/PARIDHI/Downloads/CSI Holi.png");
            // Read the PNG file into a BufferedImage
            BufferedImage image = ImageIO.read(file);
            // Now you can work with the image
        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
        }
    }
}