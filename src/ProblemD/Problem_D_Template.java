package ProblemD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

public class Problem_D_Template {
    //    Debugging helper
/*
    public static void main(String[] args) throws Exception {
        int should = 10;
        for (int i = 0; i < 9; i++) {
            String input = "src\\ProblemD\\input\\generated\\input" + i + ".txt";
            test(i, should, problemD(input));
        }
    }

    public static void test(int index, int should, int is) {
        if (is == should) {
            System.out.println("Test " + index + " was successful with value: " + is + ".");
        } else {
            System.out.println("Test " + index + " was not successful was: " + is + " should be " + should + ".");
        }
    }
*/
    //    Change signature if you want to use the debugging helper methods
    //public static int problemD(String input) throws Exception {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        //BufferedReader br = new BufferedReader(new FileReader(input));
        //Reading in n and m
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // number of cities
        int m = Integer.parseInt(br.readLine()); // number of roads

        // In our flow-graph we have n + m + 2 vertices
        final int V = n + m + 2;
        int source = 0;
        int sink = V - 1;
        // Remember this is an adjacency-LIST implementation only the first index of the two-dimensional array
        // corresponds to the number of an actual edge
        // index 0 corresponds to the sink
        // index 1:n corresponds to the cities
        // index n+1:V-2 corresponds to the roads
        // index V-1 corresponds to the sink
        Edge[][] edges = new Edge[V][];
        edges[V - 1] = new Edge[0];


        // TODO Store which roads connect to which cities in a map (Key: index of city)
        HashMap<Integer, ArrayList<Integer>> citiesToRoads = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            citiesToRoads.put(i, new ArrayList<>());
        }

        // Our source connects to each "road"
        edges[source] = new Edge[m];
        // iterating through our edges
        for (int j = n + 1; j < V - 1; j++) {
            // TODO Add edges with capacity 1 from source to roads
            // e.g. edges[0][?] = new Edge(0,j, 1,false);
            edges[0][j - n - 1] = new Edge(0, j, 1, false);

            // TODO Read in all roads and create 2 edges for each (edges connect to each adjacent city)
            String[] destinations = br.readLine().split(" ");
            int firstCity = Integer.parseInt(destinations[0]);
            int secondCity = Integer.parseInt(destinations[1]);
            edges[j] = new Edge[]{new Edge(j, firstCity, 1, false), new Edge(j, secondCity, 1, false)};

            // TODO store which roads connect to a city in citiesToRoads (to create residual Edges from cities to roads)
            citiesToRoads.get(firstCity).add(j);
            citiesToRoads.get(secondCity).add(j);
        }
        br.close();

        //Iterating through all cities
        for (int i = 1; i < n + 1; i++) {
            ArrayList<Integer> roads = citiesToRoads.get(i);
            // Every "city" has one Edge to the sink
            // and roads.size() residual Edges for adjacent roads
            edges[i] = new Edge[1 + roads.size()];
            // TODO for each city create residual edges for each connecting road
            //sink
            edges[i][0] = new Edge(i, V - 1, 1, false);
            //res
            for (int j = 1; j < roads.size() + 1; j++) {
                edges[i][j] = new Edge(i, roads.get(j - 1), 1, true);
            }
        }

        Graph graph = new Graph(edges, n, m);
        // d can be zero for graph with 1 vertex.
        int low = 0, high = n;
        // Run binary search to solve for d.
        while (low < high) {
            // Try out d = mid.
            int mid = (low + high) / 2;

            // TODO update edges from cities to sink with capacity d (=mid)
            for (int i = 1; i < n + 1; i++) {
                edges[i][0].setCapacity(mid);
            }

            // find MaxFLow
            int maxFlow = edmondsKarp(graph);
            if (maxFlow == m) {
                high = mid;
                // Here d > mid.
            } else {
                low = mid + 1;
            }
            graph.resetFlow();
        }
        System.out.print(low);
        // Debugging help (your algorithm should be able to run in under ~2s)

        /*
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        double elapsedSeconds = (double) elapsedTime / 1000;
        System.out.println("The Algorithm took " + (elapsedSeconds) + (" seconds."));
        return low;*/
    }

    public static int edmondsKarp(Graph rGraph) {
        //long startTime = System.currentTimeMillis();
        int maxFlow = 0;
        int source = 0;
        int sink = rGraph.V - 1;

        // TODO find s,t-path
        //Bene: nicht sicher, ob die BFS richtig ist
        Optional<Edge[]> parentOptional = bfs(rGraph);
        Edge[] parent;
        while (parentOptional.isPresent()) {
            parent = parentOptional.get();
            // TODO find minFlow in s,t-path stored in the parent array

            // TODO Update edges and residualEdges with minFlow of the path
            int currentNode = sink;
            while (currentNode != source) {
                parent[currentNode].updateFlow();
                currentNode = parent[currentNode].getStart();
            }
            ++maxFlow;
            parentOptional = bfs(rGraph);
        }
        return maxFlow;
    }

    //Bene: Nicht sicher ob das fertig ist
    public static Optional<Edge[]> bfs(Graph graph) {
        //long startTime = System.currentTimeMillis();
        int V = graph.getV();
        Edge[] parent = new Edge[V];
        boolean[] visited = new boolean[V];
        // TODO implement BFS and return a source-sink-path given by an array of Edges if such a path is found
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int u = queue.removeFirst();
            for (Edge edge : graph.getEdges()[u]) {
                if (edge.getRemainingCapacity() > 0 && !visited[edge.dest]) {
                    visited[edge.dest] = true;
                    parent[edge.dest] = edge;
                    queue.add(edge.dest);
                }
            }

            if (u == V - 1) {
                break;
            }
        }
        return visited[V - 1] ? Optional.of(parent) : Optional.empty();
    }

    public static class Edge {
        int capacity;
        int flow;
        boolean res;
        int dest;
        int start;

        public Edge(int start, int dest, int capacity, boolean res) {
            this.capacity = capacity;
            this.start = start;
            this.dest = dest;
            this.res = res;
        }

        public void setFlow(int flow) {
            this.flow = flow;
        }

        public int getRemainingCapacity() {
            return capacity - flow;
        }

        public boolean isRes() {
            return res;
        }

        public int getDest() {
            return dest;
        }

        public int getStart() {
            return start;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public void updateFlow() {
            ++this.flow;
        }

    }

    public static class Graph {
        Edge[][] edges;
        int n;
        int m;
        int V;

        Graph(Edge[][] edges, int n, int m) {
            this.edges = edges;
            this.n = n;
            this.m = m;
            this.V = n + m + 2;
        }

        public int getV() {
            return edges.length;
        }

        public Edge[][] getEdges() {
            return edges;
        }

        public void resetFlow() {
            for (int i = 0; i < edges.length; i++) {
                if (edges[i] != null) {
                    for (int j = 0; j < edges[i].length; j++) {
                        edges[i][j].setFlow(0);
                    }
                }
            }
        }
    }
}

