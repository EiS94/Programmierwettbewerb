package ProblemN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

        ArrayList<Integer> nodeWeight = new ArrayList<>();
        ArrayList<ArrayList<Edge>> edges = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            edges.add(new ArrayList<>());
        }
        boolean[] workNode = new boolean[N];

        strings = br.readLine().split(" ");
        for (int i = 0; i < K; i++) {
            workNode[Integer.parseInt(strings[i])] = true;
        }

        int a, b, c;
        for (int i = 0; i < M; i++) {
            strings = br.readLine().split(" ");
            //Node a
            a = Integer.parseInt(strings[0]);
            //Node b
            b = Integer.parseInt(strings[1]);
            //Weight of edge (a, b)
            c = Integer.parseInt(strings[2]);

            edges.get(a).add(new Edge(c, b));
            edges.get(b).add(new Edge(c, a));
        }

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
