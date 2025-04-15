import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class SleepTrackerApp extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton saveButton;

    public SleepTrackerApp() {
        setTitle("Weekly Sleep Tracker");
        setSize(1000, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 230, 250)); // Light purple background

        String[] columns = {"Day", "Time to Sleep", "Time Awake", "Hours of Sleep", "Sleep Notes"};
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        model = new DefaultTableModel(columns, 0);
        for (String day : days) {
            model.addRow(new Object[]{day, "", "", "", ""});
        }

        table = new JTable(model);
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);

        saveButton = new JButton("Save to CSV");
        saveButton.setBackground(new Color(180, 150, 255));
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.addActionListener(e -> saveToCSV());

        JLabel title = new JLabel("Sleep Tracker", SwingConstants.CENTER);
        title.setFont(new Font("Arial Black", Font.BOLD, 26));
        title.setForeground(new Color(100, 50, 200));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        setLayout(new BorderLayout(10, 10));
        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void saveToCSV() {
        try (FileWriter writer = new FileWriter("sleep_data.csv")) {
            for (int i = 0; i < model.getColumnCount(); i++) {
                writer.write(model.getColumnName(i) + ",");
            }
            writer.write("\n");

            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    writer.write(model.getValueAt(row, col).toString() + ",");
                }
                writer.write("\n");
            }

            JOptionPane.showMessageDialog(this, "Sleep data saved as 'sleep_data.csv'!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving CSV: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SleepTrackerApp::new);
    }
}
