import java.util.*;

public class practice11 {

    static int k = 2; // Number of clusters


    static double[][] data = {
            {2, 6, 8},  // student 1
            {3, 7, 6},  // student 2
            {8, 5, 3},  // student 3
            {7, 6, 2},  // student 4
            {1, 8, 9},  // student 5
            {9, 4, 1}   // student 6
    };

    static double[][] centroids = new double[k][3];
    static int[] labels = new int[data.length];

    public static void main(String[] args) {
        initCentroids();
        boolean changed = true;

        while (changed) {
            changed = assignClusters();
            updateCentroids();
        }

        printClusters();
    }

    static void initCentroids() {

        for (int i = 0; i < k; i++) {
            centroids[i] = Arrays.copyOf(data[i], data[i].length);
        }
    }

    static boolean assignClusters() {
        boolean changed = false;

        for (int i = 0; i < data.length; i++) {
            double minDist = Double.MAX_VALUE;
            int bestCluster = -1;

            for (int j = 0; j < k; j++) {
                double dist = euclidean(data[i], centroids[j]);
                if (dist < minDist) {
                    minDist = dist;
                    bestCluster = j;
                }
            }

            if (labels[i] != bestCluster) {
                labels[i] = bestCluster;
                changed = true;
            }
        }

        return changed;
    }

    static void updateCentroids() {
        double[][] newCentroids = new double[k][3];
        int[] counts = new int[k];

        for (int i = 0; i < data.length; i++) {
            int cluster = labels[i];
            for (int j = 0; j < 3; j++) {
                newCentroids[cluster][j] += data[i][j];
            }
            counts[cluster]++;
        }

        for (int i = 0; i < k; i++) {
            if (counts[i] == 0) continue;
            for (int j = 0; j < 3; j++) {
                centroids[i][j] = newCentroids[i][j] / counts[i];
            }
        }
    }

    static double euclidean(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(sum);
    }

    static void printClusters() {
        System.out.println("Student\tStudy\tSleep\tScreen\tCluster");
        for (int i = 0; i < data.length; i++) {
            System.out.printf("S%d\t%.1f\t%.1f\t%.1f\t%d\n", i+1, data[i][0], data[i][1], data[i][2], labels[i]);
        }
    }
}
