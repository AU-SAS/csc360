import javax.swing.*;
import java.awt.*;


public class mandala_visualizer extends JPanel
{
    private Color currentColor = Color.WHITE;

    public mandala_visualizer() {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(currentColor);


        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        g2d.fillOval(187,87,60,60);
        g2d.fillOval(224,125,60,60);
        g2d.fillOval(228,175,60,60);
        g2d.fillOval(194,215,60,60);
        g2d.fillOval(138,219,60,60);
        g2d.fillOval(99,181,60,60);
        g2d.fillOval(95,128,60,60);
        g2d.fillOval(133,87,60,60);


        g2d.setColor(new Color(244,213,128));
        g2d.fillOval(centerX - 80, centerY - 80, 160, 160);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(centerX - 80, centerY - 80, 160, 160);

        g2d.setColor(Color.CYAN);
        g2d.fillOval(centerX - 60, centerY - 60, 120, 120);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(centerX - 60, centerY - 60, 120, 120);

        g2d.setColor(Color.PINK);
        g2d.fillOval(centerX -30 , centerY -30 , 60, 60);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(centerX -30 , centerY -30, 60, 60);


    }

        public void setColor(Color color) {
            this.currentColor = color;
            repaint(); //  to repaint the color
        }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mandala Art");
        mandala_visualizer visualizer = new mandala_visualizer();


            JPanel colorPanel = new JPanel(new GridLayout(1, 3));
            String[] colorNames = {"Burgundy", "Olive", "Blue"};
            Color[] color1 = {new Color(109, 0, 22),new Color(75, 77, 57), new Color(33,56,91)}; //VIBGYOR
            for (int i = 0; i < color1.length; i++) {
                final Color selectedColor = color1[i];
                JButton button = new JButton(colorNames[i]);
                button.setOpaque(true);
                button.addActionListener(e -> visualizer.setColor(selectedColor));
                colorPanel.add(button);
            }
            frame.setLayout(new BorderLayout());
            frame.add(colorPanel, BorderLayout.SOUTH);
            frame.add(visualizer);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);    }
}