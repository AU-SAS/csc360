import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Activity_5_Draw_Venn_Diagram_Visualizer extends JPanel
{
    private String operation = "Union"; // Default operation
    public Activity_5_Draw_Venn_Diagram_Visualizer()
    {
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Define two circles representing sets
        Ellipse2D circleA = new Ellipse2D.Double(150, 100, 200, 200);
        Ellipse2D circleB = new Ellipse2D.Double(250, 100, 200, 200);

        // Define areas for the circles
        Area areaA = new Area(circleA);
        Area areaB = new Area(circleB);

        // Apply the selected operation
        switch (operation)
        {
            case "Union":
                areaA.add(areaB);
                g2d.setColor(new Color(255, 0, 0, 150)); // Semi-transparent Red
                break;
            case "Intersection":
                areaA.intersect(areaB);
                g2d.setColor(new Color(0, 255, 0, 150)); // Semi-transparent Green
                break;
            case "Difference":
                areaA.subtract(areaB);
                g2d.setColor(new Color(0, 0, 255, 150)); // Semi-transparent Blue
                break;
        }

        // Draw the result
        g2d.fill(areaA);

        // Draw outlines for clarity
        g2d.setColor(Color.BLACK);
        g2d.draw(circleA);
        g2d.draw(circleB);

        // Label the sets
        g2d.setColor(Color.BLACK);
        g2d.drawString("Set A", 180, 90);
        g2d.drawString("Set B", 330, 90);
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
        repaint(); // Repaint the panel to show the selected operation
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Venn Diagram Visualizer");
        Activity_5_Draw_Venn_Diagram_Visualizer visualizer = new Activity_5_Draw_Venn_Diagram_Visualizer();

        // Create buttons for the operations
        JButton unionButton = new JButton("Union");
        JButton intersectionButton = new JButton("Intersection");
        JButton differenceButton = new JButton("Difference");

        // Add action listeners to the buttons
        unionButton.addActionListener(e -> visualizer.setOperation("Union"));
        intersectionButton.addActionListener(e -> visualizer.setOperation("Intersection"));
        differenceButton.addActionListener(e -> visualizer.setOperation("Difference"));

        // Add the visualizer and buttons to the frame
        frame.setLayout(new BorderLayout());
        frame.add(visualizer, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(unionButton);
        buttonPanel.add(intersectionButton);
        buttonPanel.add(differenceButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}