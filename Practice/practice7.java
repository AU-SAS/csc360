import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class practice7 {
    public static void main(String[] args) {
        String data = "Hello, Leisha!";
        int size = 250;

        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();


        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, size, size);


        generateQRPattern(graphics, size);


        try {
            ImageIO.write(image, "png", new File("qr_code.png"));
            System.out.println("QR Code Generated: qr_code.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void generateQRPattern(Graphics2D g, int size) {
        g.setColor(Color.BLACK);
        Random random = new Random();

        int cellSize = size / 25;
        for (int y = 0; y < 25; y++) {
            for (int x = 0; x < 25; x++) {
                if (random.nextBoolean()) {
                    g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
    }
}
