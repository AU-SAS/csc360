import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class pixel {
    public static void main(String[] args) {
        try {
            File file = new File("C:/khushi/Admin/Desktop");
            BufferedImage image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = image.getRGB(x, y);

                    // Extract RGB components
                    int red   = (pixel >> 16) & 0xFF;
                    int green = (pixel >> 8)  & 0xFF;
                    int blue  = pixel & 0xFF;
                    System.out.println("Pixel at (" + x + ", " + y + "): R=" + red + ", G=" + green + ", B=" + blue);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}