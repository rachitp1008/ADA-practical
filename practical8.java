import java.util.Arrays;

public class Kruskal {
    
    // A simple recursive function to find the root parent of a vertex
    static int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        return find(parent, parent[i]);
    }

    public static void main(String[] args) {
        int V = 5;
        
        // List of all edges in the format: {source, destination, weight}
        int[][] edges = {
            {0, 1, 2}, {0, 3, 6}, {1, 2, 3},
            {1, 3, 8}, {1, 4, 5}, {2, 4, 7}, {3, 4, 9}
        };

        // 1. Sort the 2D array by the 3rd column (the weight)
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        // 2. Set up the parent array to detect cycles
        int[] parent = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i; // Every vertex starts as its own parent
        }

        System.out.println("Edge \tWeight");
        int totalWeight = 0;
        int edgesInMST = 0;

        // 3. Loop through the sorted edges
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int weight = edges[i][2];

            // Find the root parents of both ends of the edge
            int rootU = find(parent, u);
            int rootV = find(parent, v);

            // 4. If roots are different, no cycle is formed. Add it to MST!
            if (rootU != rootV) {
                System.out.println(u + " - " + v + " \t" + weight);
                totalWeight += weight;
                
                // Merge the two components (Union)
                parent[rootU] = rootV; 
                edgesInMST++;
            }

            // Stop early if we have exactly V - 1 edges
            if (edgesInMST == V - 1) {
                break;
            }
        }

        System.out.println("Total MST Weight: " + totalWeight);
    }
}
