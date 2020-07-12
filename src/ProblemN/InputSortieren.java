package src.ProblemN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class InputSortieren {


    public static void main(String[] args) throws IOException {

        //String input = Files.readString(Paths.get("src\\ProblemN\\Samples\\Yes.txt"));

        long start;
        long end;
        long total;

        start = System.nanoTime();

        //BufferedReader br = new BufferedReader(new StringReader(input));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");
        //#Nodes
        int N = Integer.parseInt(strings[0]);
        //#Edges
        int M = Integer.parseInt(strings[1]);
        //#WorkNodes
        int K = Integer.parseInt(strings[2]);

        Node[] nodes = new Node[N];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node();
        }
        int[] workTour = new int[K];

        Node lastNode = null;
        Node node;
        strings = br.readLine().split(" ");

        //save workNodes in workTour, set workNode in Node to true and store the previous Node of each Node in WorkTour
        for (int i = 0; i < K; i++) {
            node = nodes[Integer.parseInt(strings[i]) - 1];
            workTour[i] = Integer.parseInt(strings[i]) - 1;
            node.workNode = true;
            node.pred = lastNode;
            lastNode = node;
        }

        Tuple[] tuples = new Tuple[M];

        //store all M lines as Tuple (a,b,c=weight) in Array. If a > b -> swap(a,b) for better sorting
        for (int i = 0; i < M; i++) {
            strings = br.readLine().split(" ");
            int a = Integer.parseInt(strings[0]);
            int b = Integer.parseInt(strings[1]);
            int temp;
            if (a > b) {
                temp = a;
                a = b;
                b = temp;
            }
            int c = Integer.parseInt(strings[2]);
            tuples[i] = new Tuple(a, b, c);
        }

        //Sort Tuple Array 3 times
        //end-result: 1,1,1
        //            1,1,2
        //            1,2,1
        //            2,1,1 usw.
        Arrays.sort(tuples, Comparator.comparingInt(tuple -> tuple.a));

        Arrays.sort(tuples, (tuple, t1) -> {
            if (tuple.a == t1.a) {
                if (tuple.b < t1.b) return -1;
                if (tuple.b > t1.b) return 1;
            }
            return 0;
        });

        Arrays.sort(tuples, (tuple, t1) -> {
            if (tuple.a == t1.a && tuple.b == t1.b) {
                if (tuple.weight < t1.weight) return -1;
                if (tuple.weight > t1.weight) return 1;
            }
            return 0;
        });

        br.close();

        //Store Edge from a -> b to a and to b. If there are more than one Edge from a to b, only store the Edge with minimum weight.
        //If there are more than one Edge from a to b with minimum weight, set Edge.multi to true
        int lastA = Integer.MAX_VALUE;
        int lastB = Integer.MAX_VALUE;
        int lastWeight = Integer.MAX_VALUE;
        for (Tuple tuple : tuples) {
            int a = tuple.a - 1;
            int b = tuple.b - 1;
            int weight = tuple.weight;
            if (lastA == a && lastB == b && lastWeight == weight && nodes[a].edges.get(nodes[a].edges.size() - 1).weight == weight && nodes[a].edges.get(nodes[a].edges.size() - 1).nextNode == nodes[b]) {
                nodes[a].edges.get(nodes[a].edges.size() - 1).multi = true;
                if (a != b) {
                    nodes[b].edges.get(nodes[b].edges.size() - 1).multi = true;
                }
            } else if (lastA == a && lastB != b) {
                nodes[a].edges.add(new Edge(weight, nodes[b]));
                if (a != b) nodes[b].edges.add(new Edge(weight, nodes[a]));
            } else if (lastA != a) {
                nodes[a].edges.add(new Edge(weight, nodes[b]));
                if (a != b) nodes[b].edges.add(new Edge(weight, nodes[a]));
            }
            lastA = a;
            lastB = b;
            lastWeight = weight;
        }

        end = System.nanoTime();
        total = end - start;
        //System.out.println("\nKanten einlesen: " + total / 1000000000.0);

        //set weight of startNode to 0
        nodes[0].weight = 0;

        for (int i = 0; i < workTour.length - 1; i++) {
            for (Edge edge : nodes[workTour[i]].edges) {
                if (edge.nextNode == nodes[workTour[i + 1]]) {
                    edge.nextNode.weight = nodes[workTour[i]].weight + edge.weight;
                }
            }
        }

        //PriorityQueue autoSort, if something changed in the Queue
        //sort Nodes by Weight
        PriorityQueue<Node> Q = new PriorityQueue<>(Comparator.comparingInt(node2 -> node2.weight));

        //Dijkstra as in the presentation, but checks additional in the if-clause for YES-Result if the edge was a multi edge
        //-> then also return YES
        Q.addAll(Arrays.asList(nodes));

        while (!Q.isEmpty()) {
            Node u = Q.poll();
            for (Edge edge : u.edges) {
                Node v = edge.nextNode;
                if (v.weight >= u.weight + edge.weight) {
                    if ((v.workNode && v.pred != u) || (v.workNode && edge.multi)) {
                        System.out.print("yes");

                        end = System.nanoTime();
                        total = end - start;
                        //System.out.println("\ngesamt: " + total / 1000000000.0);

                        return;
                    }
                    v.weight = u.weight + edge.weight;
                    Q.remove(v);
                    Q.add(v);
                }
            }
        }
        System.out.print("no");

        end = System.nanoTime();
        total = end - start;
        //System.out.println("\ngesamt: " + total / 1000000000.0);
    }


    private static class Edge {

        int weight;
        Node nextNode;
        boolean multi;

        Edge(int weight, Node nextNode) {
            this.weight = weight;
            this.nextNode = nextNode;
        }

        @Override
        public String toString() {
            return nextNode.id + ", " + multi + ", " + weight;
        }
    }


    private static class Node {
        ArrayList<Edge> edges = new ArrayList<>();
        int weight = 10001;
        Node pred;
        boolean workNode;
        int id;
        static int idCounter = 1;

        Node() {
            this.id = idCounter++;
        }

        @Override
        public String toString() {
            StringBuilder e = new StringBuilder("[");
            for (Edge edge : edges) {
                e.append(", ").append(edge.nextNode.id);
            }
            return id + ", Edges: " + e;
        }
    }

    private static class Tuple {

        int a, b, weight;

        Tuple(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return a + ", " + b + ", " + weight;
        }
    }

}


