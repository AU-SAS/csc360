import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class PixelDataExtractor extends JPanel {
    private BufferedImage image;

    public PixelDataExtractor() {
        // Create an image
        int width = 100, height = 100;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Fill the image with colors
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Generate color gradient
                int red = (x * 255) / width;
                int green = (y * 255) / height;
                int blue = 128; // Fixed blue component

                int color = (red << 16) | (green << 8) | blue; // Combine RGB into one integer
                image.setRGB(x, y, color);
            }
        }

        // Print pixel data
        printPixelData();
    }

    // Method to print pixel data
    private void printPixelData() {
        int width = image.getWidth();
        int height = image.getHeight();

        System.out.println("Pixel Data (RGB Values):");
        for (int y = 0; y < height; y += 10) {  // Sampling every 10 rows for readability
            for (int x = 0; x < width; x += 10) {  // Sampling every 10 columns for readability
                int color = image.getRGB(x, y);

                // Extract RGB values
                int red = (color >> 16) & 0xFF;
                int green = (color >> 8) & 0xFF;
                int blue = color & 0xFF;

                System.out.printf("Pixel (%d, %d) -> R: %d, G: %d, B: %d%n", x, y, red, green, blue);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pixel Data Extractor");
        PixelDataExtractor panel = new PixelDataExtractor();
        frame.add(panel);
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
