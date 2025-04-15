public class SimpleKMeans {
    static class Point {
        int x, y, cluster;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.cluster = -1;
        }

        double distanceTo(int cx, int cy) {
            int dx = x - cx;
            int dy = y - cy;
            return Math.sqrt(dx * dx + dy * dy); // basic Euclidean distance
        }
    }

    public static void main(String[] args) {
        Point[] points = {
                new Point(2, 3),
                new Point(3, 4),
                new Point(10, 11),
                new Point(11, 13)
        };

        int cx0 = points[0].x, cy0 = points[0].y;
        int cx1 = points[2].x, cy1 = points[2].y;

        boolean changed;
        do {
            changed = false;

            // Assign clusters
            for (Point p : points) {
                double d0 = p.distanceTo(cx0, cy0);
                double d1 = p.distanceTo(cx1, cy1);
                int newCluster = (d0 < d1) ? 0 : 1;

                if (p.cluster != newCluster) {
                    p.cluster = newCluster;
                    changed = true;
                }
            }

            // Recalculate centroids
            int sumX0 = 0, sumY0 = 0, count0 = 0;
            int sumX1 = 0, sumY1 = 0, count1 = 0;

            for (Point p : points) {
                if (p.cluster == 0) {
                    sumX0 += p.x;
                    sumY0 += p.y;
                    count0++;
                } else {
                    sumX1 += p.x;
                    sumY1 += p.y;
                    count1++;
                }
            }

            if (count0 > 0) {
                cx0 = sumX0 / count0;
                cy0 = sumY0 / count0;
            }
            if (count1 > 0) {
                cx1 = sumX1 / count1;
                cy1 = sumY1 / count1;
            }

        } while (changed);

        for (Point p : points) {
            System.out.println("(" + p.x + ", " + p.y + ") -> Cluster " + p.cluster);
        }
    }
}
