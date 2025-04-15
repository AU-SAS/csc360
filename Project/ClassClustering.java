import javax.swing.*;
import java.awt.*;

public class ClassClustering extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set background color
        setBackground(Color.WHITE);

        // Draw central figure (professor)
        g.setColor(Color.CYAN);
        g.fillOval(160, 30, 25, 25);
        g.drawString("Instructor", 150, 25);

        // Define clusters' positions and sizes
        int[][] studentPositions = {
                {60, 80}, {100, 80}, {140, 80}, {180, 80}, {220, 80},  // Cluster 1 (Row 1)
                {60, 130}, {100, 130}, {140, 130}, {180, 130}, {220, 130} // Cluster 2 (Row 2)
        };

        // Draw students in Cluster 1 (Red)
        g.setColor(Color.RED);
        for (int i = 0; i < 5; i++) {
            g.fillOval(studentPositions[i][0], studentPositions[i][1], 15, 15);
        }

        // Draw students in Cluster 2 (Green)
        g.setColor(Color.GREEN);
        for (int i = 5; i < 10; i++) {
            g.fillOval(studentPositions[i][0], studentPositions[i][1], 15, 15);
        }

        // Cluster labels
        g.setColor(Color.BLACK);
        g.drawString("Cluster 1 (Top Row)", 60, 75);
        g.drawString("Cluster 2 (Bottom Row)", 60, 170);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Classroom Visualization");
        ClassClustering panel = new ClassClustering();
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
