import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class practice6 {
    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\Leisha\\Downloads\\WhatsApp_Image_2024-12-13_at_19.01.49_fc64d9f0-removebg-preview.png");


            BufferedImage image = ImageIO.read(file);

            int width = image.getWidth();
            int height = image.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = image.getRGB(x, y);
                    System.out.printf("Pixel at (%d, %d): %08X\n", x, y, pixel);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
