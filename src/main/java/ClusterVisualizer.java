package src;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClusterVisualizer extends JPanel {
    private static final int MARGIN = 50;
    private static final int INITIAL_DIAMETER = 10;
    private static final int LAYER_SPACING = 70;

    private final List<ClusterNode> nodes;
    private final List<ClusterLayer> layers = new ArrayList<>();
    private final Random random = new Random();

    public ClusterVisualizer(int pointCount) {
        this.nodes = createInitialNodes(pointCount);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));
        buildClusterHierarchy();
    }

    private List<ClusterNode> createInitialNodes(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> new ClusterNode(
                        MARGIN,
                        MARGIN + i * 40,
                        INITIAL_DIAMETER,
                        randomColor(),
                        Collections.singletonList(i)))
                .collect(Collectors.toList());
    }

    private void buildClusterHierarchy() {
        // First layer contains all individual points
        layers.add(new ClusterLayer(new ArrayList<>(nodes), 0));

        // Create hierarchical clusters
        List<ClusterNode> currentNodes = new ArrayList<>(nodes);
        int layerIndex = 1;

        while (currentNodes.size() > 1) {
            List<ClusterNode> nextLayerNodes = new ArrayList<>();

            // Cluster in pairs
            for (int i = 0; i < currentNodes.size(); i += 2) {
                if (i + 1 < currentNodes.size()) {
                    ClusterNode merged = mergeNodes(
                            currentNodes.get(i),
                            currentNodes.get(i + 1),
                            layerIndex);
                    nextLayerNodes.add(merged);
                } else {
                    // Odd number - carry forward the last node
                    nextLayerNodes.add(currentNodes.get(i));
                }
            }

            layers.add(new ClusterLayer(nextLayerNodes, layerIndex));
            currentNodes = nextLayerNodes;
            layerIndex++;
        }
    }

    private ClusterNode mergeNodes(ClusterNode a, ClusterNode b, int layerIndex) {
        int x = MARGIN + (layerIndex + 1) * LAYER_SPACING;
        int y = (a.y + b.y) / 2;
        int diameter = INITIAL_DIAMETER + layerIndex * 5;

        List<Integer> combinedPoints = new ArrayList<>();
        combinedPoints.addAll(a.containedPoints);
        combinedPoints.addAll(b.containedPoints);

        return new ClusterNode(x, y, diameter, randomColor(), combinedPoints);
    }

    private Color randomColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw all layers
        for (int i = 0; i < layers.size(); i++) {
            ClusterLayer layer = layers.get(i);
            layer.draw(g2d, i == layers.size() - 1);

            // Draw connections to next layer if not last layer
            if (i < layers.size() - 1) {
                drawConnections(g2d, layer, layers.get(i + 1));
            }
        }
    }

    private void drawConnections(Graphics2D g2d, ClusterLayer current, ClusterLayer next) {
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1.5f));

        Map<Integer, List<ClusterNode>> parentToChildren = new HashMap<>();
        for (ClusterNode child : current.nodes) {
            for (ClusterNode parent : next.nodes) {
                if (parent.containedPoints.containsAll(child.containedPoints)) {
                    parentToChildren.computeIfAbsent(next.nodes.indexOf(parent), k -> new ArrayList<>()).add(child);
                    break;
                }
            }
        }

        for (Map.Entry<Integer, List<ClusterNode>> entry : parentToChildren.entrySet()) {
            ClusterNode parent = next.nodes.get(entry.getKey());
            for (ClusterNode child : entry.getValue()) {
                g2d.drawLine(
                        child.x + child.diameter,
                        child.y + child.diameter/2,
                        parent.x,
                        parent.y + parent.diameter/2
                );
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String input = JOptionPane.showInputDialog("Enter number of points:");
            int k = Integer.parseInt(input);

            JFrame frame = new JFrame("Hierarchical Cluster Visualization");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new ClusterVisualizer(k));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    // Helper classes
    private static class ClusterNode {
        final int x, y, diameter;
        final Color color;
        final List<Integer> containedPoints;

        ClusterNode(int x, int y, int diameter, Color color, List<Integer> containedPoints) {
            this.x = x;
            this.y = y;
            this.diameter = diameter;
            this.color = color;
            this.containedPoints = containedPoints;
        }

        void draw(Graphics2D g2d) {
            g2d.setColor(color);
            g2d.fill(new Ellipse2D.Double(x, y, diameter, diameter));

            g2d.setColor(Color.BLACK);
            g2d.draw(new Ellipse2D.Double(x, y, diameter, diameter));
        }
    }

    private static class ClusterLayer {
        final List<ClusterNode> nodes;
        final int level;

        ClusterLayer(List<ClusterNode> nodes, int level) {
            this.nodes = nodes;
            this.level = level;
        }

        void draw(Graphics2D g2d, boolean isFinal) {
            // Draw all nodes in this layer
            nodes.forEach(node -> node.draw(g2d));

            // Draw bounding boxes for clusters
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(1));

            for (ClusterNode node : nodes) {
                int padding = isFinal ? 30 : 15;
                int boxX = node.x - padding/2;
                int boxY = node.y - padding/2;
                int boxWidth = node.diameter + padding;
                int boxHeight = node.diameter + padding;

                g2d.drawRect(boxX, boxY, boxWidth, boxHeight);
            }
        }
    }
}
