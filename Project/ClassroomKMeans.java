public class ClassroomKMeans {
    static class Student {
        String name;
        int x, y;
        int cluster;


        Student(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.cluster = -1;
        }


        double distanceTo(int cx, int cy) {
            return Math.sqrt((x - cx) * (x - cx) + (y - cy) * (y - cy));
        }
    }


    public static void main(String[] args) {
        // Step 1: Define students and their positions (like sitting in the classroom)
        Student[] students = {
                new Student("Alice", 2, 3),
                new Student("Bob", 3, 4),
                new Student("Charlie", 10, 11),
                new Student("Daisy", 11, 13)
        };


        // Step 2: Initialize centroids (just pick two starting points)
        int c1x = students[0].x, c1y = students[0].y;
        int c2x = students[2].x, c2y = students[2].y;


        boolean changed;


        // Step 3: Run the K-Means loop
        do {
            changed = false;


            // Assign each student to the nearest centroid
            for (Student s : students) {
                double d1 = s.distanceTo(c1x, c1y);
                double d2 = s.distanceTo(c2x, c2y);


                int newCluster = (d1 < d2) ? 0 : 1;
                if (s.cluster != newCluster) {
                    s.cluster = newCluster;
                    changed = true;
                }
            }


            // Step 4: Recalculate centroids
            int sumX0 = 0, sumY0 = 0, count0 = 0;
            int sumX1 = 0, sumY1 = 0, count1 = 0;


            for (Student s : students) {
                if (s.cluster == 0) {
                    sumX0 += s.x;
                    sumY0 += s.y;
                    count0++;
                } else {
                    sumX1 += s.x;
                    sumY1 += s.y;
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


        } while (changed); // Repeat until stable


        // Step 5: Output final cluster results
        System.out.println("=== Final Student Clusters ===");
        for (Student s : students) {
            System.out.println(s.name + " (" + s.x + ", " + s.y + ") -> Cluster " + s.cluster);
        }


        System.out.println("\nFinal Centroid 0: (" + c1x + ", " + c1y + ")");
        System.out.println("Final Centroid 1: (" + c2x + ", " + c2y + ")");
    }
}
