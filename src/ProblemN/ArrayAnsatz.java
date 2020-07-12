package src.ProblemN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ArrayAnsatz {

    public static void main(String[] args) throws IOException {

        String input = Files.readString(Paths.get("src\\ProblemN\\Samples\\min_multikante_lösung_ja_ca_5_sekunden.txt"));

        long start;
        long end;
        long total;
        long start2;
        start = System.nanoTime();

        BufferedReader br = new BufferedReader(new StringReader(input));

        String[] strings = br.readLine().split(" ");
        //#Nodes
        int N = Integer.parseInt(strings[0]);
        //#Edges
        int M = Integer.parseInt(strings[1]);
        //#WorkNodes
        int K = Integer.parseInt(strings[2]);

        ArrayList<Edge>[] nodes = new ArrayList[N];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new ArrayList<>();
        }

        start2 = System.nanoTime();
        int[] workTour = new int[K];
        int[] pred = new int[N];
        int[] nodeWeight = new int[N];
        boolean[] nodeWorkNode = new boolean[N];
        Arrays.fill(pred, -1);
        int lastNode = -1;
        int node;
        strings = br.readLine().split(" ");
        for (int i = 0; i < K; i++) {
            node = Integer.parseInt(strings[i]) - 1;
            nodeWorkNode[node] = true;
            workTour[i] = node;
            pred[node] = lastNode;
            lastNode = node;
        }
        end = System.nanoTime();
        total = end - start2;
        System.out.println("\nWorktour einlesen: " + total / 1000000000.0);

        start2 = System.nanoTime();
        for (int i = 0; i < M; i++) {
            strings = br.readLine().split(" ");
            int a = Integer.parseInt(strings[0]) - 1;
            int b = Integer.parseInt(strings[1]) - 1;
            int c = Integer.parseInt(strings[2]);
            Edge edge = new Edge(c,b);

            //update Edge, if allready in List
            if (nodes[a].contains(edge)) {
                for (Edge e : nodes[a]) {
                    if (e.equals(edge)) {
                        // if same weight, set multiEdge true
                        if (e.weight == edge.weight) e.multi = true;
                        // if edge has lower weight, update weight of e
                        if (e.weight > edge.weight) e.weight = edge.weight;
                    }
                }
            } else {
                //if Edge is not in List, add Edge
                nodes[a].add(edge);
            }

            //same with reverse direction
            edge = new Edge(c,a);
            if (nodes[b].contains(edge)) {
                for (Edge e : nodes[b]) {
                    if (e.equals(edge)) {
                        if (e.weight == edge.weight) e.multi = true;
                        if (e.weight > edge.weight) e.weight = edge.weight;
                    }
                }
            } else {
                nodes[b].add(edge);
            }
        }
        end = System.nanoTime();
        total = end - start2;
        System.out.println("\nKanten einlesen: " + total / 1000000000.0);

        br.close();

        start2 = System.nanoTime();
        for (int i = 1; i < nodeWeight.length; i++) {
            nodeWeight[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < workTour.length - 1; i++) {
            for (Edge edge : nodes[workTour[i]]) {
                if (edge.nextNode == workTour[i + 1]) {
                    nodeWeight[edge.nextNode] = nodeWeight[workTour[i]] + edge.weight;
                }
            }
        }

        Comparator<Integer> comp = Comparator.comparingInt(integer -> nodeWeight[integer]);

        PriorityQueue<Integer> Q = new PriorityQueue<Integer>(comp);
        for (int i = 0; i < N; i++) {
            Q.add(i);
        }

        end = System.nanoTime();
        total = end - start2;
        System.out.println("\nDijkstra Init: " + total / 1000000000.0);

        start2 = System.nanoTime();

        while (!Q.isEmpty()) {
            int u = Q.poll();
            for (Edge edge : nodes[u]) {
                int v = edge.nextNode;
                if (nodeWeight[v] >= nodeWeight[u] + edge.weight) {
                    if ((nodeWorkNode[v] && pred[v] != u) || (nodeWorkNode[v] && edge.multi)) {
                        end = System.nanoTime();
                        total = end - start2;
                        System.out.println("\nDijkstra Rest: " + total / 1000000000.0);

                        end = System.nanoTime();
                        total = end - start;
                        System.out.println("\ntotal: " + total / 1000000000.0);

                        System.out.print("yes");
                        return;
                    }
                    nodeWeight[edge.nextNode] = nodeWeight[u] + edge.weight;
                }
            }
        }

        end = System.nanoTime();
        total = end - start2;
        System.out.println("\nDijkstra Rest: " + total / 1000000000.0);

        end = System.nanoTime();
        total = end - start;
        System.out.println("\ntotal: " + total / 1000000000.0);

        System.out.print("no");

    }

    private static class Edge {

        int weight, nextNode;
        boolean multi;

        public Edge(int weight, int nextNode) {
            this.weight = weight;
            this.nextNode = nextNode;
        }

        @Override
        public boolean equals(Object obj) {
            return this.nextNode == ((Edge) obj).nextNode;
        }
    }

}
