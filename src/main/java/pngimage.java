import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

class BresenhamLineDrawingWithPNG extends JPanel {

    private final int x1, y1, x2, y2;
    private BufferedImage image;
    private final int width = 800;
    private final int height = 800;

    public BresenhamLineDrawingWithPNG(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);

        // Create the image we'll be drawing to
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.BLACK);

        // Draw the line on the BufferedImage
        drawBresenhamLine(g2d, x1, y1, x2, y2);
        g2d.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the BufferedImage onto the panel
        g.drawImage(image, 0, 0, this);
    }

    private void drawBresenhamLine(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = x1 < x2 ? 1 : -1; // Step direction for x
        int sy = y1 < y2 ? 1 : -1; // Step direction for y

        int err = dx - dy; // Initial error term

        while (true) {
            // Draw the current pixel
            g2d.fillRect(x1, y1, 1, 1);

            // Check if we've reached the end point
            if (x1 == x2 && y1 == y2) break;

            // Calculate the next error term and coordinates
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }

    public void saveAsPNG(String filename) {
        try {
            File outputFile = new File(filename);
            ImageIO.write(image, "PNG", outputFile);
            System.out.println("Image saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving image: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Line endpoints
        int x1 = 100, y1 = 100;
        int x2 = 500, y2 = 300;

        // Create a frame to display the line
        JFrame frame = new JFrame("Bresenham's Line Drawing Algorithm");
        BresenhamLineDrawingWithPNG linePanel = new BresenhamLineDrawingWithPNG(x1, y1, x2, y2);

        frame.add(linePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Save the image as PNG
        linePanel.saveAsPNG("bresenham_line.png");
    }
}