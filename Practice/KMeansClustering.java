import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;
import org.apache.commons.math3.ml.clustering.CentroidCluster;

import java.util.ArrayList;
import java.util.List;

public class KMeansClustering {
    public static void main(String[] args) {
        List<DoublePoint> points = new ArrayList<>();


        points.add(new DoublePoint(new double[]{1.0, 2.0}));
        points.add(new DoublePoint(new double[]{1.5, 1.8}));
        points.add(new DoublePoint(new double[]{5.0, 8.0}));
        points.add(new DoublePoint(new double[]{8.0, 8.0}));
        points.add(new DoublePoint(new double[]{1.0, 0.6}));
        points.add(new DoublePoint(new double[]{9.0, 11.0}));


        KMeansPlusPlusClusterer<DoublePoint> clusterer = new KMeansPlusPlusClusterer<>(2);


        List<CentroidCluster<DoublePoint>> clusters = clusterer.cluster(points);


        int clusterIndex = 1;
        for (CentroidCluster<DoublePoint> cluster : clusters) {
            System.out.println("Cluster " + clusterIndex++ + ":");
            for (DoublePoint p : cluster.getPoints()) {
                System.out.println("  " + java.util.Arrays.toString(p.getPoint()));
            }
        }
    }
}
