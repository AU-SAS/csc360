import javax.swing.*;
import java.awt.*;

public class ClusteringActivity extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Color[] vibgyorColors =
                {
                        Color.RED,
                        Color.ORANGE,
                        Color.YELLOW,
                        Color.GREEN,
                        Color.BLUE,
                        new Color(75, 0, 130), // Indigo (custom RGB value)
                        new Color(138, 43, 226) // Violet (custom RGB value)
                };
        int x = 50;
        int[] y = {50, 100,100, 150, 200, 250,270,300,350};

//        for(int i=0; i<vibgyorColors.length; i++) {
//            g2d.setColor(vibgyorColors[i]);
//        }
        for(int j=0; j<vibgyorColors.length; j++) {
            int i = 0;
            g2d.setColor(vibgyorColors[i]);}
            for(int i=0; i<y.length; i++){

            g2d.fillOval(x - 5, y[i] - 5, 15, 15);

        }
//        for(int i=0; i<vibgyorColors.length; i++) {
//            g2d.setColor(vibgyorColors[i]);
//        }



        for (int i = 0; i < y.length; i += 2) {
            g2d.drawLine(x, y[i], x - 40, y[i]);
            if (i + 1 < y.length) {
                g2d.drawLine(x - 40, y[i], x- 40, y[i + 1]);
                g2d.drawLine(x - 40, y[i + 1], x, y[i + 1]); // Horizont bk
                g2d.drawLine(x - 40, y[i], x- 40, y[i + 1]);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clustering Activity");
        ClusteringActivity panel = new ClusteringActivity();
        frame.add(panel);
        frame.setSize(300, 400); // Adjusted size to fit all elements
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}
