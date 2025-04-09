import javax.swing.*;
import java.awt.*;


public class ClassroomCluster extends JPanel {


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        // Background
        setBackground(Color.WHITE);


        // Draw professor
        g.setColor(Color.BLUE);
        g.fillOval(160, 30, 20, 20);
        g.drawString("Professor", 150, 25);


        // Draw students in Cluster 1 (Row 1)
        g.setColor(Color.RED);
        for (int i = 0; i < 5; i++) {
            g.fillOval(60 + i * 40, 80, 15, 15);
        }


        // Draw students in Cluster 2 (Row 2)
        g.setColor(Color.GREEN);
        for (int i = 0; i < 5; i++) {
            g.fillOval(60 + i * 40, 130, 15, 15);
        }


        // Add cluster labels
        g.setColor(Color.BLACK);
        g.drawString("Cluster 1 (Row 1)", 60, 75);
        g.drawString("Cluster 2 (Row 2)", 60, 170);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Virtual Classroom Clustering");
        ClassroomCluster panel = new ClassroomCluster();
        frame.add(panel);
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
