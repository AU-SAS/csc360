import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RotateImage extends JFrame {
    private BufferedImage loadedImage;

    public RotateImage() {
        setTitle("Rotate Image");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Buttons
        JButton loadButton = new JButton("Load Image");
        JButton rotateButton = new JButton("Rotate 90°");
        JButton saveButton = new JButton("Save Image");

        loadButton.addActionListener(e -> loadImage());
        rotateButton.addActionListener(e -> rotateImage());
        saveButton.addActionListener(e -> saveImage());

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadButton);
        buttonPanel.add(rotateButton);
        buttonPanel.add(saveButton);

        // Drawing Panel
        JPanel drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (loadedImage != null) {
                    int x = (getWidth() - loadedImage.getWidth()) / 2;
                    int y = (getHeight() - loadedImage.getHeight()) / 2;
                    g.drawImage(loadedImage, x, y, this);
                }
            }
        };

        add(buttonPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void loadImage() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                loadedImage = ImageIO.read(file);
                repaint();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading image!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void rotateImage() {
        if (loadedImage != null) {
            int width = loadedImage.getWidth();
            int height = loadedImage.getHeight();
            BufferedImage rotatedImage = new BufferedImage(height, width, loadedImage.getType());

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    rotatedImage.setRGB(y, width - 1 - x, loadedImage.getRGB(x, y));
                }
            }
            loadedImage = rotatedImage;
            repaint();
        }
    }

    private void saveImage() {
        if (loadedImage != null) {
            try {
                File outputFile = new File("rotated_image.png");
                ImageIO.write(loadedImage, "png", outputFile);
                JOptionPane.showMessageDialog(this, "Image saved as rotated_image.png!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving image!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new RotateImage();
    }
}
