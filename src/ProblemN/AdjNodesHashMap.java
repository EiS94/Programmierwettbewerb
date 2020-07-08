package ProblemN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class AdjNodesHashMap {

    public static void main(String[] args) throws Exception {

        String path = "src\\ProblemN\\Samples\\";
        String name = "min_multikante_lösung_ja_ca_5_sekunden";
        String input = Files.readString(Paths.get(path + name + ".txt"));

        BufferedReader br = new BufferedReader(new StringReader(input));

        String[] strings = br.readLine().split(" ");
        //#Nodes
        int N = Integer.parseInt(strings[0]);
        //#Edges
        int M = Integer.parseInt(strings[1]);
        //#WorkNodes
        int K = Integer.parseInt(strings[2]);

        int[] nodeWeight = new int[N];
        ArrayList<HashMap<Integer, Edge>> edges = new ArrayList<>();
        ArrayList<HashMap<Integer, Integer>> lowestEdge = new ArrayList<>();
        int[] workWay = new int[K];
        int[] pred = new int[N];

        for (int i = 0; i < N; i++) {
            edges.add(new HashMap<>());
        }

        for (int i = 0; i < N; i++) {
            lowestEdge.add(new HashMap<>());
        }

        boolean[] workNode = new boolean[N];

        strings = br.readLine().split(" ");
        int tempWorkNode;
        for (int i = 0; i < K; i++) {
            tempWorkNode = Integer.parseInt(strings[i]) - 1;
            workNode[tempWorkNode] = true;
            workWay[i] = tempWorkNode;
        }


        //start adding edges
        int a, b, c;
        Integer weightA; //Lowest weight for edge (a, b) / (b, a)
        for (int i = 0; i < M; i++) {
            strings = br.readLine().split(" ");
            //Node a
            a = Integer.parseInt(strings[0]) - 1;
            //Node b
            b = Integer.parseInt(strings[1]) - 1;
            //Weight of edge (a, b)
            c = Integer.parseInt(strings[2]);

            weightA = lowestEdge.get(a).get(b);
            //No edges added yet for edge (a, b) / (b, a)
            if (weightA == null) {
                //for (a, b)
                edges.get(a).put(b, new Edge(c, b));
                lowestEdge.get(a).put(b, c);

                //for (b, a)
                edges.get(b).put(a, new Edge(c, a));
                lowestEdge.get(b).put(a, c);
            } else {
                //Weights are equaly min -> just add
                if (weightA == c) {
                    //for (a, b)
                    edges.get(a).get(b).more = true;

                    //for (b, a)
                    edges.get(b).get(a).more = true;
                }
                //Previously added edges have a higer weight -> delet edges && add new edge && update minEdge
                else if (weightA > c) {
                    //for (a, b)
                    edges.get(a).put(b, new Edge(c, b));
                    lowestEdge.get(a).put(b, c);

                    //for (b, a)
                    edges.get(b).put(a, new Edge(c, a));
                    lowestEdge.get(b).put(a, c);
                }
                //Previously added edges have a lower weight -> do nothing
            }
        }
        //end adding edges


        for (int i = 1; i < workWay.length; i++) {
            pred[workWay[i]] = workWay[i - 1];
        }

        //start Dijkstra
        for (int i = 1; i < nodeWeight.length; i++) {
            nodeWeight[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < workWay.length - 1; i++) {
            for (Edge edge : edges.get(workWay[i]).values()) {
                if (edge.nextNode == workWay[i + 1]) {
                    nodeWeight[edge.nextNode] = nodeWeight[workWay[i]] + edge.weight;
                }
            }
        }

        LinkedList<Integer> Q = new LinkedList();
        for (int i = 0; i < N; i++) {
            Q.add(i);
        }

        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return nodeWeight[integer] - nodeWeight[t1];
            }
        };

        Q.sort(comp);

        while (!Q.isEmpty()) {
            int u = Q.remove(0);
            for (Edge edge : edges.get(u).values()) {
                if (nodeWeight[edge.nextNode] >= nodeWeight[u] + edge.weight) {
                    if (workNode[edge.nextNode] && (pred[edge.nextNode] != u || edge.more)) {
                        System.out.print("yes");
                        return;
                    }
                    nodeWeight[edge.nextNode] = nodeWeight[u] + edge.weight;
                    Q.sort(comp);
                }
            }
        }

        System.out.print("no");

        //end Dijkstra

    }

    private static class Edge {

        public int weight;
        public int nextNode;
        public boolean more = false;

        public Edge(int weight, int nextNode) {
            this.weight = weight;
            this.nextNode = nextNode;
        }
    }
}
