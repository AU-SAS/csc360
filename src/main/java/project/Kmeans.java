package project;

public class Kmeans {
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

        // Euclidean distance to a centroid
        double distanceTo(int centerX, int centerY) {
            return Math.sqrt(Math.pow(this.x - centerX, 2) + Math.pow(this.y - centerY, 2));
        }
    }

    public static void main(String[] args) {
        // Step 1: Students sitting at positions
        Student[] students = {
                new Student("David", 7, 4),
                new Student("George", 2, 4),
                new Student("Max", 7, 15)
        };

        // Step 2: Start with two centroids (just pick 2 students' positions)
        int[] centroidX = {students[0].x, students[2].x};
        int[] centroidY = {students[0].y, students[2].y};

        boolean changed;

        do {
            changed = false;

            // Step 3: Assign students to closest centroid
            for (Student s : students) {
                double distToC0 = s.distanceTo(centroidX[0], centroidY[0]);
                double distToC1 = s.distanceTo(centroidX[1], centroidY[1]);

                int nearestCluster = (distToC0 < distToC1) ? 0 : 1;

                if (s.cluster != nearestCluster) {
                    s.cluster = nearestCluster;
                    changed = true; // Clusters changed, so continue looping
                }
            }

            // Step 4: Recalculate centroid positions
            int[] sumX = {0, 0}, sumY = {0, 0}, count = {0, 0};

            for (Student s : students) {
                int cluster = s.cluster;
                sumX[cluster] += s.x;
                sumY[cluster] += s.y;
                count[cluster]++;
            }

            for (int i = 0; i < 2; i++) {
                if (count[i] > 0) {
                    centroidX[i] = sumX[i] / count[i];
                    centroidY[i] = sumY[i] / count[i];
                }
            }

        } while (changed); // Repeat until no changes in clusters

        // Step 5: Display final clusters
        System.out.println("=== Final Clusters ===");
        for (Student s : students) {
            System.out.println(s.name + " (" + s.x + ", " + s.y + ") -> Cluster " + s.cluster);
        }

        // Show final centroid locations
        System.out.println("\nFinal Centroids:");
        for (int i = 0; i < 2; i++) {
            System.out.println("Cluster " + i + ": (" + centroidX[i] + ", " + centroidY[i] + ")");
        }
    }
}


