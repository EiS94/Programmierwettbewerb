package ProblemM.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class ProblemM {

    public static HashMap<Integer, Node> nodes;

    public static class Node {

        public int color = 0;
        public long d = Long.MAX_VALUE;
        public LinkedList<Edge> edges = new LinkedList<>();
        public boolean visited = false;
    }

    public static class Edge {

        public int nextNode;
        public int weight;

        public Edge(int nextNode, int weight) {
            this.nextNode = nextNode;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "next Node: " + nextNode;
        }
    }

    //public static String path = "D:\\SeminarAlgorithmenFürProgrammierwettbewerbe\\seminarprogproblemc\\src\\ProblemM\\samples\\helpMe.txt";

    public static void main(String[] args) throws Exception {
        nodes = new HashMap<>();


        //start reading Input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = reader.readLine();
        String[] firstLineString = firstLine.split(" ");
        int numberOfNodes = Integer.parseInt(firstLineString[0]) + 1;
        int numberOfEdges = Integer.parseInt(firstLineString[1]);

        //+ is nose at 1
        //- is nose at 2
        for (int i = 1; i < numberOfNodes; i++) {
            nodes.put(i, new Node());
            nodes.put(-i, new Node());
        }

        for (int i = 0; i < numberOfEdges; i++) {
            String line = reader.readLine();
            String[] lineString = line.split(" ");
            int[] edge = new int[5];
            for (int j = 0; j < 5; j++) {
                edge[j] = Integer.parseInt(lineString[j]);
            }
            int nodeA = edge[0];
            int noseA = edge[1];
            int nodeB = edge[2];
            int noseB = edge[3];
            int weight = edge[4];

            //right Nodes
            if (noseA == 2) {
                nodeA *= -1;
            }
            if (noseB == 2) {
                nodeB *= -1;
            }

            nodes.get(nodeA).edges.add(new Edge(nodeB * -1, weight));
            nodes.get(nodeB * -1).edges.add(new Edge(nodeA, weight));
            nodes.get(nodeB).edges.add(new Edge(nodeA * -1, weight));
            nodes.get(nodeA * -1).edges.add(new Edge(nodeB, weight));

        }

        int startNode = Integer.parseInt(reader.readLine());
        Node endNode = nodes.get(-startNode);
        reader.close();

        //end reading Input

        //Dijkstra
        nodes.get(startNode).color = 1;
        nodes.get(startNode).d = 0;

        Comparator<Node> c = new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                if (node.d > t1.d) return 1;
                else if (node.d == t1.d) return 0;
                else return -1;
            }
        };

        PriorityQueue<Node> queue = new PriorityQueue<>(c);

        queue.add(nodes.get(startNode));
        /*
        for (int i = 1; i < numberOfNodes; i++) {
            if (i != startNode) {
                queue.add(nodes.get(i));
            }
            queue.add(nodes.get(-i));
        }
         */
        //Queue sorted

        while (!queue.isEmpty()) {
            Node u = queue.poll();
            if (u.visited)
                continue;
            u.visited = true;
            for (Edge edge : u.edges) {
                Node neighbour = nodes.get(edge.nextNode);

                if (neighbour.d != Long.MAX_VALUE && neighbour.color == 1 && neighbour.equals(endNode)) {
                    System.out.print(neighbour.d);
                    return;
                }


                //Relax
                if (neighbour.color != 1 && neighbour.d > u.d + edge.weight && u.d + edge.weight > 0) {
                    neighbour.d = u.d + edge.weight;
                    //u.color = 1;

                    //DecreaseKey
                    queue.add(neighbour);

                }
                u.color = 1;
            }
        }
        long total = nodes.get(-startNode).d;
        if (total == Long.MAX_VALUE) {
            System.out.print("impossible");
        } else {
            System.out.print(total);
        }
    }


}
