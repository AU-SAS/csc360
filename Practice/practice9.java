import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class practice9 extends JFrame {
    public practice9() {
        setTitle("Netflix");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        add(new NetflixPanel());

        setFocusable(true);
        setVisible(true);
    }

    class NetflixPanel extends JPanel {
        Image[] profilePics = new Image[4];
        String[] imagePaths = {
                "C:\\Users\\leisha\\Downloads\\download (20).jpeg",
                "C:\\Users\\leisha\\Downloads\\2018 Netflix Penguin profile icon by Norbert-Sloth _ Redbubble.jpeg",
                "C:\\Users\\leisha\\Downloads\\Netflix smileu profile icon by Norbert-Sloth _ Redbubble.jpeg",
                "C:\\Users\\leisha\\Downloads\\Spongebob.jpeg"
        };

        String[] names = {"Leisha", "vyan", "vidhi", "tanush"};

        public NetflixPanel() {
            for (int i = 0; i < imagePaths.length; i++) {
                try {
                    profilePics[i] = ImageIO.read(new File(imagePaths[i])).getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                } catch (IOException e) {
                    System.out.println("Failed to load image: " + imagePaths[i]);
                    profilePics[i] = null;
                }
            }
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;


            g2.setColor(Color.black);
            g2.fillRect(0, 0, getWidth(), getHeight());


            String title = "Netflix";
            g2.setFont(new Font("SansSerif", Font.BOLD, 48));
            g2.setColor(Color.RED);
            FontMetrics fm = g2.getFontMetrics();
            int titleX = (getWidth() - fm.stringWidth(title)) / 2;
            g2.drawString(title, titleX, 60);




            g2.setFont(new Font("SansSerif", Font.PLAIN, 24));
            g2.setColor(Color.WHITE);
            String subtitle = "Who's watching?";
            int subX = (getWidth() - g2.getFontMetrics().stringWidth(subtitle)) / 2;
            g2.drawString(subtitle, subX, 120);
            int thumbSize = 120;
            int spacing = 40;
            int totalWidth = 4 * thumbSize + 3 * spacing;
            int startX = (getWidth() - totalWidth) / 2;
            int y = 150;


            for (int i = 0; i < 4; i++) {
                int x = startX + i * (thumbSize + spacing);


                if (profilePics[i] != null) {
                    g2.drawImage(profilePics[i], x, y, null);
                } else {

                    g2.setColor(Color.GRAY);
                    g2.fillRoundRect(x, y, thumbSize, thumbSize, 20, 20);
                }


                g2.setColor(Color.WHITE);
                g2.setFont(new Font("SansSerif", Font.PLAIN, 16));
                int nameX = x + (thumbSize - g2.getFontMetrics().stringWidth(names[i])) / 2;
                g2.drawString(names[i], nameX, y + thumbSize + 25);
            }


            g2.setFont(new Font("SansSerif", Font.PLAIN, 18));
            String btnText = "MANAGE PROFILES";
            int btnWidth = g2.getFontMetrics().stringWidth(btnText) + 40;
            int btnX = (getWidth() - btnWidth) / 2;
            int btnY = 450;

            g2.setColor(Color.DARK_GRAY);
            g2.fillRoundRect(btnX, btnY, btnWidth, 40, 10, 10);
            g2.setColor(Color.WHITE);
            g2.drawRoundRect(btnX, btnY, btnWidth, 40, 10, 10);

            int textX = btnX + (btnWidth - g2.getFontMetrics().stringWidth(btnText)) / 2;
            g2.drawString(btnText, textX, btnY + 25);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(practice9::new);
    }
}
