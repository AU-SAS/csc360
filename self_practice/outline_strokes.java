package self_practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class outline_strokes extends JFrame {

    private shapepanel panel;
    private float stroke_width = 2.0f; //default value

    public outline_strokes() {
        setTitle("Dynamic Stroke Width");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        panel = new shapepanel();
        add(panel, BorderLayout.CENTER);

        JPanel controller = new JPanel();
        JButton increase_width = new JButton("Increase Stroke Width");
        JButton decrease_width = new JButton("Decrease Stroke Width");

        // increase stroke width button
        increase_width.addActionListener(e -> {
            stroke_width += 2.0f;
            panel.repaint();
        });

        // decrease stroke width button
        decrease_width.addActionListener(e -> {
            if (stroke_width>1.0f) {
                stroke_width -= 2.0f;
                panel.repaint();
            }
        });

        controller.add(increase_width);
        controller.add(decrease_width);
        add(controller, BorderLayout.SOUTH);


        setVisible(true);
    }

    class shapepanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(stroke_width));
            int x = 100;
            int y = 100;
            int w = 100; //width
            int h = 100; // height

            //drawing rectangle
            g2d.drawRect(x, y, w, h);


        }
    }

    public static void main(String[] args) {
        new outline_strokes();
    }
}
