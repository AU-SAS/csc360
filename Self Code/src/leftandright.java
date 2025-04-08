
import javax.swing.*;
import java.awt.*;

public class leftandright extends JFrame {
    private String currentvehicle = "None";
    private int vehicleXOffset = 0; // for the movement

    public leftandright() {
        setTitle("Vehicle Moving System");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for vehicle buttons
        JPanel vehiclePanel = new JPanel(new GridLayout(1, 2));
        String[] vehicle = {"Car", "Truck"};
        for (String i : vehicle) {
            JButton btn = new JButton(i);
            btn.addActionListener(e -> {
                currentvehicle = i;
                repaint();
            });
            vehiclePanel.add(btn);
        }

        // Panel for movement buttons (Left and Right)
        JPanel movePanel = new JPanel(new GridLayout(1, 2));
        JButton frontBtn = new JButton("Front");
        JButton reverseBtn = new JButton("Reverse");

        frontBtn.addActionListener(e -> {
            vehicleXOffset -= 25;
            repaint();
        });

        reverseBtn.addActionListener(e -> {
            vehicleXOffset += 25;
            repaint();
        });

        movePanel.add(frontBtn);
        movePanel.add(reverseBtn);

        // Drawing panel
        JPanel drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (currentvehicle.equals("Car")) {
                    g.setColor(Color.BLUE);
                    int[] xstart = {50, 50, 80, 110, 190, 210, 190, 180, 170, 110, 100, 90};
                    int[] ystart = {230, 200, 200, 180, 180, 230, 230, 250, 230, 230, 250, 230};
                    for (int i = 0; i < xstart.length; i++) {
                        xstart[i] += vehicleXOffset;
                    }
                    g.drawPolygon(xstart, ystart, xstart.length);
                    g.setColor(Color.BLACK);
                    g.fillOval(175 + vehicleXOffset, 240, 15, 15);
                    g.fillOval(95 + vehicleXOffset, 240, 15, 15);
                }

                else if (currentvehicle.equals("Truck")) {
                    g.setColor(Color.RED);
                    int[] xstart1 = {200, 400, 400, 120, 120, 200};
                    int[] ystart1 = {150, 150, 300, 300, 230, 150};
                    for (int i = 0; i < xstart1.length; i++) {
                        xstart1[i] += vehicleXOffset;
                    }
                    g.drawPolygon(xstart1, ystart1, xstart1.length);
                    int[] xstart2 = {200, 380, 380, 200};
                    int[] ystart2 = {170, 170, 250, 250};
                    for (int i = 0; i < xstart2.length; i++) {
                        xstart2[i] += vehicleXOffset;
                    }
                    g.drawPolygon(xstart2, ystart2, xstart2.length);
                    g.setColor(Color.BLACK);
                    g.fillOval(150 + vehicleXOffset, 280, 30, 30);
                    g.fillOval(350 + vehicleXOffset, 280, 30, 30);
                }
            }
        };

        // Add panels to the frame
        add(vehiclePanel, BorderLayout.NORTH); //Vehicle buttons at the top
        add(drawPanel, BorderLayout.CENTER);// object at center
        add(movePanel, BorderLayout.SOUTH); // Movement buttons at bottom
        setVisible(true);
    }

    public static void main(String[] args) {
        new leftandright();
    }
}
