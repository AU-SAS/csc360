package self_practice;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class main_project extends JPanel {
    private int k; //user will input here
    private Random r_color; //making it a global variable to randomise color
    private ArrayList<Point> points; // to store the positions of circles
    private ArrayList<ArrayList<Integer>> clusters; // will store index of points which will be grouped into clusters

    //constructor for initialising variables to generate initial points
    public main_project(int k) {
        this.k = k;
        this.r_color = new Random();
        this.points = new ArrayList<>();
        this.clusters = new ArrayList<>();

        //left side k circles alignment
        for (int i = 0; i < k; i++) { //i is the num of current circle
            int x =40; //all would be vertically in same line
            int y= 20 + (i*40)%400; // 400% added so it doesn't overflow from the screen (400 pixels)
            points.add(new Point(x, y)); //saves circle position
        }

        generateClusters();
    }
    private void generateClusters() { //k means algorithm
        int number_of_clusters = Math.max(1, (int) Math.sqrt(k)); //decides how many clusters we want
        if (number_of_clusters > k) number_of_clusters = k;

        ArrayList<Point> centroids = new ArrayList<>();
        ArrayList<Integer> centroid_indices = new ArrayList<>();
        for (int i = 0; i < k; i++) centroid_indices.add(i);
        Collections.shuffle(centroid_indices, r_color); //shuffles randomly
        for (int i = 0; i < number_of_clusters; i++) {
            centroids.add(new Point(points.get(centroid_indices.get(i))));
        }

        int[] assignments = new int[k]; //stores which cluster reach circle is in
        int[] counts = new int[number_of_clusters]; //counts how many in each cluster
        boolean changed;

        for (int iter = 0; iter < 100; iter++) { //  100 times max
            changed = false;

            // assigning each circle to nearest center
            for (int i = 0; i < k; i++) {
                int closest = 0;
                double min_dist = distance(points.get(i), centroids.get(0));
                for (int j = 1; j < number_of_clusters; j++) {
                    double dist = distance(points.get(i), centroids.get(j));
                    if (dist < min_dist) {
                        min_dist = dist;
                        closest = j;
                    }
                }
                // If changed cluster, mark as changed
                if (assignments[i] != closest) {
                    assignments[i] = closest;
                    changed = true;
                }
            }

            // moving each center to the average position of its group
            Arrays.fill(counts, 0);
            double[] sum_x = new double[number_of_clusters];
            double[] sum_y = new double[number_of_clusters];

            for (int i = 0; i < k; i++) {
                int c = assignments[i];
                sum_x[c] += points.get(i).x;
                sum_y[c] += points.get(i).y;
                counts[c]++;
            }

            for (int j = 0; j < number_of_clusters; j++) {
                if (counts[j] > 0) {
                    centroids.get(j).x = (int) (sum_x[j] / counts[j]);
                    centroids.get(j).y = (int) (sum_y[j] / counts[j]);
                }
            }


            if (!changed) break;
        }

        for (int j = 0; j < number_of_clusters; j++) clusters.add(new ArrayList<>());
        for (int i = 0; i < k; i++) {
            if (counts[assignments[i]] > 0) {
                clusters.get(assignments[i]).add(i);
            }
        }

        clusters.removeIf(ArrayList::isEmpty);
    }
    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2)); //keeps the stroke thick

        int diameter = 10;

        //first layer of clusters
        ArrayList<int[]> cluster_box = new ArrayList<>(); // array of num of cluster boxes
        ArrayList<Integer> y_values = new ArrayList<>();

        int current_y=20;
        for (ArrayList<Integer> cluster : clusters) {
            if (cluster.isEmpty()) continue;
            int box_top = current_y;

            for (int idx : cluster) {
                Color random_colors = new Color(r_color.nextInt(255), r_color.nextInt(255), r_color.nextInt(255));
                g2d.setColor(random_colors);
                Point p = points.get(idx);
                g2d.fillOval(p.x, p.y, diameter, diameter);
                current_y += 30;
            }

            int box_bottom = current_y + 10;
            cluster_box.add(new int[]{box_top, box_bottom});
            current_y += 20; // Space between clusters
        }

        g2d.setColor(Color.BLACK);
        for (int[] box : cluster_box) {
            int[] x_points = {20, 60, 60, 20};
            int[] y_points = {box[0], box[0], box[1], box[1]};
            g2d.drawPolygon(x_points, y_points, 4);

            int link_y = box[0] + (box[1] - box[0]) / 2;
            g2d.drawLine(60, link_y, 90, link_y);
            y_values.add(link_y);
        }

        int layer_x = 100;
        int layer_diameter = 20;
        ArrayList<ArrayList<Integer>> current_layer = new ArrayList<>();
        current_layer.add(y_values);

        while (current_layer.get(current_layer.size() - 1).size() > 1) {
            ArrayList<Integer> prev_layer = current_layer.get(current_layer.size() - 1);
            ArrayList<Integer> next_layer_y = new ArrayList<>();
            ArrayList<int[]> next_boxes = new ArrayList<>();

            for (int i = 0; i < prev_layer.size(); i += 2) {
                Color random_colors = new Color(r_color.nextInt(255), r_color.nextInt(255), r_color.nextInt(255));
                g2d.setColor(random_colors);

                int y_top = prev_layer.get(i);
                int y_bottom = (i + 1 < prev_layer.size()) ? prev_layer.get(i + 1) : y_top;
                int link_y = (y_top + y_bottom) / 2;

                g2d.fillOval(layer_x, link_y - layer_diameter / 2, layer_diameter, layer_diameter);

                int box_top = Math.min(y_top, y_bottom) - 15;
                int box_bottom = Math.max(y_top, y_bottom) + 15;
                next_boxes.add(new int[]{box_top, box_bottom});
                next_layer_y.add(link_y);
            }

            g2d.setColor(Color.BLACK);
            for (int[] box : next_boxes) {
                int[] x_points = {layer_x - 10, layer_x + 30, layer_x + 30, layer_x - 10};
                int[] y_points = {box[0], box[0], box[1], box[1]};
                g2d.drawPolygon(x_points, y_points, 4);

                int link_y = box[0] + (box[1] - box[0]) / 2;
                g2d.drawLine(layer_x + 30, link_y, layer_x + 60, link_y);
            }

            current_layer.add(next_layer_y);
            layer_x += 70;
            layer_diameter += 10;
        }

        if (current_layer.get(current_layer.size() - 1).size() == 1) {
            Color random_colors = new Color(r_color.nextInt(255), r_color.nextInt(255), r_color.nextInt(255));
            g2d.setColor(random_colors);
            int link_y = current_layer.get(current_layer.size() - 1).get(0);
            g2d.fillOval(layer_x, link_y - layer_diameter / 2, layer_diameter, layer_diameter);

            g2d.setColor(Color.BLACK);
            int[] x_points = {layer_x - 10, layer_x + 50, layer_x + 50, layer_x - 10};
            int[] y_points = {link_y - 30, link_y - 30, link_y + 30, link_y + 30};
            g2d.drawPolygon(x_points, y_points, 4);
        }




    }



    public static void main(String[] args) {
        //user inputs num of circles
        String input = JOptionPane.showInputDialog("Enter the number of circles (k):"); //input as string
        int k = Integer.parseInt(input);  //converting it to int

        //setting up the panel
        main_project panel = new main_project(k);
        JFrame frame = new JFrame("Project - Cluster Tree");
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
