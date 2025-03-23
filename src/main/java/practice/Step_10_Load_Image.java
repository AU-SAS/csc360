package practice;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Step_10_Load_Image extends JFrame
{
    private BufferedImage loadedImage;
    public Step_10_Load_Image()
    {
        setTitle("Image Loader & Texture Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Load Image Button
        JButton loadButton = new JButton("Load Image");
        loadButton.addActionListener(e -> loadImage());
        // Save Image Button
        JButton saveButton = new JButton("Save Image");
        saveButton.addActionListener(e -> saveImage());
        // Panel for Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        // Drawing Panel
        JPanel drawPanel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                if (loadedImage != null)
                {
                    int imgWidth = loadedImage.getWidth();
                    int imgHeight = loadedImage.getHeight();
                    // Maintain Aspect Ratio
                    int panelWidth = getWidth();
                    int panelHeight = getHeight();
                    int newWidth = panelWidth;
                    int newHeight = (imgHeight * panelWidth) / imgWidth;
                    if (newHeight > panelHeight)
                    {
                        newHeight = panelHeight;
                        newWidth = (imgWidth * panelHeight) / imgHeight;
                    }
                    // Draw the image at the center with aspect ratio maintained
                    int x = (panelWidth - newWidth) / 2;
                    int y = (panelHeight - newHeight) / 2;
                    g.drawImage(loadedImage, x, y, newWidth, newHeight, this);
                    // Example: Using Image as Texture
                    drawTexturedRectangle(g);
                }
            }
            private void drawTexturedRectangle(Graphics g)
            {
                if (loadedImage != null)
                {
                    Graphics2D g2d = (Graphics2D) g;
                    TexturePaint texturePaint = new TexturePaint(loadedImage,
                            new Rectangle(50, 50, loadedImage.getWidth() / 4, loadedImage.getHeight() / 4));
                    g2d.setPaint(texturePaint);
                    g2d.fillRect(100, 400, 200, 150); // Textured Rectangle
                }
            }
        };
        add(buttonPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    private void loadImage()
    {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            try
            {
                loadedImage = ImageIO.read(file);
                repaint();
            }
            catch (IOException e)
            {
                JOptionPane.showMessageDialog(this, "Error loading image!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void saveImage()
    {
        if (loadedImage != null)
        {
            try
            {
                File outputFile = new File("saved_image.png");
                ImageIO.write(loadedImage, "png", outputFile);
                JOptionPane.showMessageDialog(this, "Image saved as saved_image.png!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (IOException e)
            {
                JOptionPane.showMessageDialog(this, "Error saving image!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void main(String[] args)
    {
        new Step_10_Load_Image();
    }
}