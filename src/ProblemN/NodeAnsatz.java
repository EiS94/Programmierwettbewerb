package ProblemN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class NodeAnsatz {

    public static void main(String[] args) throws IOException {

        //String input = Files.readString(Paths.get("/home/eike/Dokumente/Uni/6. Semester/Seminar/Git/seminarprogproblemc/src/ProblemN/Samples/min_multikante_lösung_ja_ca_5_sekunden.txt"));

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

        for (int i = 0; i < K; i++) {
            node = nodes[Integer.parseInt(strings[i]) - 1];
            workTour[i] = Integer.parseInt(strings[i]) - 1;
            node.workNode = true;
            node.pred = lastNode;
            lastNode = node;
        }

        for (int i = 0; i < M; i++) {
            strings = br.readLine().split(" ");
            int a = Integer.parseInt(strings[0]) - 1;
            int b = Integer.parseInt(strings[1]) - 1;
            int c = Integer.parseInt(strings[2]);
            Edge edge = new Edge(c, nodes[b]);

            //update Edge, if allready in List
            if (nodes[a].edges.contains(edge)) {
                for (Edge e : nodes[a].edges) {
                    if (e.equals(edge)) {
                        // if same weight, set multiEdge true
                        if (e.weight == edge.weight) e.multi = true;
                        // if edge has lower weight, update weight of e
                        if (e.weight > edge.weight) e.weight = edge.weight;
                    }
                }
            } else {
                //if Edge is not in List, add Edge
                nodes[a].edges.add(edge);
            }

            //same with reverse direction
            edge = new Edge(c, nodes[a]);
            if (nodes[b].edges.contains(edge)) {
                for (Edge e : nodes[b].edges) {
                    if (e.equals(edge)) {
                        if (e.weight == edge.weight) e.multi = true;
                        if (e.weight > edge.weight) e.weight = edge.weight;
                    }
                }
            } else {
                nodes[b].edges.add(edge);
            }
        }

        br.close();

        nodes[0].weight = 0;

        for (int i = 0; i < workTour.length - 1; i++) {
            for (Edge edge : nodes[workTour[i]].edges) {
                if (edge.nextNode == nodes[workTour[i + 1]]) {
                    edge.nextNode.weight = nodes[workTour[i]].weight + edge.weight;
                }
            }
        }

        PriorityQueue<Node> Q = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                return Integer.compare(node.weight, t1.weight);
            }
        });

        Q.addAll(Arrays.asList(nodes));

        while (!Q.isEmpty()) {
            Node u = Q.poll();
            for (Edge edge : u.edges) {
                Node v = edge.nextNode;
                if (v.weight >= u.weight + edge.weight) {
                    if ((v.workNode && v.pred != u) || (v.workNode && edge.multi)) {
                        System.out.print("yes");
                        return;
                    }
                    v.weight = u.weight + edge.weight;
                }
            }
        }
        System.out.print("no");
    }


    private static class Edge {

        int weight;
        Node nextNode;
        boolean multi;

        public Edge(int weight, Node nextNode) {
            this.weight = weight;
            this.nextNode = nextNode;
        }

        @Override
        public boolean equals(Object obj) {
            return this.nextNode == ((Edge) obj).nextNode;
        }
    }


    private static class Node {

        ArrayList<Edge> edges = new ArrayList<>();
        int weight = 10001;
        Node pred;
        boolean workNode;
        int id;
        static int idCounter = 1;

        public Node() {
            this.id = idCounter++;
        }

        @Override
        public String toString() {
            return String.valueOf(id);
        }
    }

}
