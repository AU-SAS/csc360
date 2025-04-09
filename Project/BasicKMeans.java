public class BasicKMeans {
    static class Point {
        int x, y;
        int cluster; // 0 or 1


        Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.cluster = -1; // unassigned
        }


        double distanceTo(int cx, int cy) {
            return Math.sqrt((x - cx) * (x - cx) + (y - cy) * (y - cy));
        }
    }


    public static void main(String[] args) {
        // Step 1: Define points
        Point[] points = {
                new Point(2, 3),
                new Point(3, 4),
                new Point(10, 11),
                new Point(11, 13)
        };


        // Step 2: Initial centroids (pick first and third points)
        int c1x = points[0].x, c1y = points[0].y;
        int c2x = points[2].x, c2y = points[2].y;


        boolean changed;


        do {
            changed = false;


            // Step 3: Assign points to nearest centroid
            for (Point p : points) {
                double d1 = p.distanceTo(c1x, c1y);
                double d2 = p.distanceTo(c2x, c2y);


                int newCluster = (d1 < d2) ? 0 : 1;
                if (p.cluster != newCluster) {
                    p.cluster = newCluster;
                    changed = true;
                }
            }


            // Step 4: Recalculate centroids
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
                c1x = sumX0 / count0;
                c1y = sumY0 / count0;
            }


            if (count1 > 0) {
                c2x = sumX1 / count1;
                c2y = sumY1 / count1;
            }


        } while (changed); // Repeat until no changes in cluster assignment


        // Step 5: Output final clusters
        for (Point p : points) {
            System.out.println("(" + p.x + ", " + p.y + ") -> Cluster " + p.cluster);
        }


        System.out.println("Final Centroid 0: (" + c1x + ", " + c1y + ")");
        System.out.println("Final Centroid 1: (" + c2x + ", " + c2y + ")");
    }
}
