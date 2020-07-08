package ProblemN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ProblemN {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");
        //#Nodes
        int N = Integer.parseInt(strings[0]);
        //#Edges
        int M = Integer.parseInt(strings[1]);
        //#WorkNodes
        int K = Integer.parseInt(strings[2]);

        int[] nodeWeight = new int[N];
        ArrayList<ArrayList<Edge>> edges = new ArrayList<>();
        ArrayList<HashMap<Integer, Integer>> lowestEdge = new ArrayList<>();
        int[] workWay = new int[K];

        for (int i = 0; i < N; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            lowestEdge.add(new HashMap<>());
        }

        boolean[] workNode = new boolean[N];

        strings = br.readLine().split(" ");
        int tempWorkNode;
        for (int i = 0; i < K; i++) {
            tempWorkNode = Integer.parseInt(strings[i]);
            workNode[tempWorkNode] = true;
            workWay[i] = tempWorkNode;
        }


        //start adding edges
        int a, b, c;
        Integer weightA; //Lowest weight for edge (a, b) / (b, a)
        for (int i = 0; i < M; i++) {
            strings = br.readLine().split(" ");
            //Node a
            a = Integer.parseInt(strings[0]);
            //Node b
            b = Integer.parseInt(strings[1]);
            //Weight of edge (a, b)
            c = Integer.parseInt(strings[2]);

            weightA = lowestEdge.get(a).get(b);
            //No edges added yet for edge (a, b) / (b, a)
            if (weightA == null){
                //for (a, b)
                edges.get(a).add(new Edge(c, b));
                lowestEdge.get(a).put(b, c);

                //for (b, a)
                edges.get(b).add(new Edge(c, a));
                lowestEdge.get(b).put(a, c);
            }
            else{
                //Weights are equaly min -> just add
                if (weightA == c){
                    //for (a, b)
                    edges.get(a).add(new Edge(c, b));

                    //for (b, a)
                    edges.get(b).add(new Edge(c, a));
                }
                //Previously added edges have a higer weight -> delet edges && add new edge && update minEdge
                else if (weightA > c){
                    //for (a, b)
                    edges.set(a, new ArrayList<>());
                    edges.get(a).add(new Edge(c, b));
                    lowestEdge.get(a).put(b, c);

                    //for (b, a)
                    edges.set(b, new ArrayList<>());
                    edges.get(b).add(new Edge(c, a));
                    lowestEdge.get(b).put(a, c);
                }
                //Previously added edges have a lower weight -> do nothing
            }
        }
        //end adding edges


        //start Dijkstra
        for (int i = 1; i < nodeWeight.length; i++) {
            nodeWeight[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < workWay.length - 1; i++) {
            for (Edge edge:edges.get(workWay[i])) {
                if (edge.nextNode == workWay[i + 1]){
                    nodeWeight[edge.nextNode] = nodeWeight[workWay[i]] + edge.weight;
                }
            }
        }

        //end Dijkstra

    }

    private static class Edge{

        public int weight;
        public int nextNode;

        public Edge(int weight, int nextNode){
            this.weight = weight;
            this.nextNode = nextNode;
        }
    }
}
