import javax.swing.*;
import java.awt.*;

public class SimpleDashboard extends JFrame {
    public SimpleDashboard() {
        setTitle("Simple Dashboard UI");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel sidebar = new JPanel();
        sidebar.setBackground(Color.DARK_GRAY);
        sidebar.setPreferredSize(new Dimension(150, getHeight()));
        sidebar.setLayout(new GridLayout(5, 1));
        String[] buttons = {"🏠 Home", "📂 Files", "📊 Reports", "⚙ Settings", "🔓 Logout"};
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setBackground(Color.LIGHT_GRAY);
            btn.setForeground(Color.BLACK);
            sidebar.add(btn);
        }

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());
        JLabel title = new JLabel("Welcome to the Dashboard!", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(title, BorderLayout.CENTER);

        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleDashboard();
    }
}
