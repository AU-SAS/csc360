import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ColourChanger extends JFrame {
    private JPanel panel;
    private JButton changeColorButton;
    private Random random;

    public ColourChanger() {
        setTitle("Random Color Changer");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setBackground(Color.WHITE);

        changeColorButton = new JButton("🎨 Change Color");
        changeColorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.setBackground(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            }
        });

        random = new Random();
        add(panel, BorderLayout.CENTER);
        add(changeColorButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ColourChanger();
    }
}
