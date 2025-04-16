import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class TreeClusterDiagram extends JPanel {

    static class Node {
        int x, y;
        char label;
        Color color;

        Node(char label, int x, int y, Color color) {
            this.label = label;
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    private HashMap<Character, Node> nodes = new HashMap<>();
    private Color[] dotColors = {
            Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE,
            Color.MAGENTA, Color.CYAN, Color.PINK, Color.YELLOW,
            Color.GRAY, new Color(128, 0, 128)
    };

    public TreeClusterDiagram() {
        createNodes();
    }

    private void createNodes() {
        // Create and position nodes manually
        nodes.put('A', new Node('A', 400, 100, dotColors[0]));
        nodes.put('B', new Node('B', 250, 200, dotColors[1]));
        nodes.put('C', new Node('C', 550, 200, dotColors[2]));
        nodes.put('E', new Node('E', 320, 300, dotColors[4]));
        nodes.put('G', new Node('G', 620, 300, dotColors[6]));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawConnections(g);
        drawNodes(g);
    }

    private void drawConnections(Graphics g) {
        g.setColor(Color.BLACK);

        drawLineBetween(g, 'B', 'E');
        drawLineBetween(g, 'A', 'C');
        drawLineBetween(g, 'C', 'G');
    }

    private void drawLineBetween(Graphics g, char from, char to) {
        Node start = nodes.get(from);
        Node end = nodes.get(to);
        if (start != null && end != null) {
            g.drawLine(start.x, start.y, end.x, end.y);
        }
    }

    private void drawNodes(Graphics g) {
        int radius = 25;
        for (Node node : nodes.values()) {
            g.setColor(node.color);
            g.fillOval(node.x - radius, node.y - radius, radius * 2, radius * 2);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            FontMetrics fm = g.getFontMetrics();
            int labelWidth = fm.charWidth(node.label);
            int labelHeight = fm.getAscent();
            g.drawString(String.valueOf(node.label), node.x - labelWidth / 2, node.y + labelHeight / 4);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tree Cluster Diagram - Custom Connections");
        TreeClusterDiagram panel = new TreeClusterDiagram();
        frame.add(panel);
        frame.setSize(850, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
