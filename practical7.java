public class Prim {
    public static void main(String[] args) {
        int V = 5;
        // 2D Array representing the graph (0 means no edge)
        // Rows and columns match the vertices 0 through 4
        int[][] graph = {
            { 0, 2, 0, 6, 0 },
            { 2, 0, 3, 8, 5 },
            { 0, 3, 0, 0, 7 },
            { 6, 8, 0, 0, 9 },
            { 0, 5, 7, 9, 0 }
        };

        int[] parent = new int[V];     // Stores the MST structure
        int[] key = new int[V];        // Stores minimum edge weights
        boolean[] inMST = new boolean[V]; // Tracks visited vertices

        // Initialize all keys to infinity
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
        }

        // Start at vertex 0
        key[0] = 0;
        parent[0] = -1; // Root has no parent

        // Loop to construct the MST (needs V-1 edges)
        for (int count = 0; count < V - 1; count++) {
            
            // 1. Find the unvisited vertex with the smallest key
            int minKey = Integer.MAX_VALUE;
            int u = -1;
            for (int i = 0; i < V; i++) {
                if (!inMST[i] && key[i] < minKey) {
                    minKey = key[i];
                    u = i;
                }
            }

            // 2. Add the picked vertex to the MST
            inMST[u] = true;

            // 3. Update the keys and parents of the adjacent unvisited vertices
            for (int v = 0; v < V; v++) {
                // If there is an edge (graph[u][v] != 0), v is unvisited, and weight is smaller than current key
                if (graph[u][v] != 0 && !inMST[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Print the result
        System.out.println("Edge \tWeight");
        int totalWeight = 0;
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + " \t" + graph[i][parent[i]]);
            totalWeight += graph[i][parent[i]];
        }
        System.out.println("Total MST Weight: " + totalWeight);
    }
}
