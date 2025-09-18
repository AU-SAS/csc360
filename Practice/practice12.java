import javax.swing.*;
import java.awt.*;

public class practice12 extends JPanel {


    String[] users = {"Leisha", "Anvi", "Aryan", "Riya", "Jay"};
    double[] hours = {3.5, 2.0, 4.2, 1.8, 3.0};

    Color[] colors = {Color.PINK, Color.CYAN, Color.ORANGE, Color.MAGENTA, Color.GREEN};

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int barWidth = 80;
        int spacing = 40;
        int baseY = 350;
        int scale = 60;

        g2.setFont(new Font("times new roman", Font.BOLD, 16));
        g2.drawString("Instagram Screen Time (hrs/day)", 100, 40);

        for (int i = 0; i < users.length; i++) {
            int x = 50 + i * (barWidth + spacing);
            int barHeight = (int)(hours[i] * scale);
            int y = baseY - barHeight;

            g2.setColor(colors[i % colors.length]);
            g2.fillRect(x, y, barWidth, barHeight);


            g2.setColor(Color.BLACK);
            g2.drawString(hours[i] + "h", x + 15, y - 10);


            g2.drawString(users[i], x + 10, baseY + 20);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("practice12 - Insta Screen Time");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 450);
        frame.add(new practice12());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
