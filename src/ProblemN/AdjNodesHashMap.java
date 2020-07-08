package ProblemN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class AdjNodesHashMap {

    public static void main(String[] args) throws Exception {

        String path = "src\\ProblemN\\Samples\\";
        String name = "langer_arbeitsweg_lösung_nein_ca_6_sekunden";
        String input = Files.readString(Paths.get(path + name + ".txt"));

        long start;
        long end;
        long total;
        start = System.nanoTime();

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
        //init weights
        for (int i = 1; i < nodeWeight.length; i++) {
            nodeWeight[i] = Integer.MAX_VALUE;
        }

        //init workWay weights
        Edge tempEdge;
        for (int i = 0; i < workWay.length - 1; i++) {
            tempEdge = edges.get(workWay[i]).get(workWay[i + 1]);
            if (tempEdge.more) {
                System.out.print("yes");
                end = System.nanoTime();
                total = end - start;
                System.out.println("\ntotal: " + total / 1000000000.0);
                return;
            }
            nodeWeight[i + 1] = nodeWeight[workWay[i]] + tempEdge.weight;
        }

        LinkedList<Integer> Q = new LinkedList();
        for (int i = 0; i < N; i++) {
            Q.add(i);
        }

        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return Integer.compare(nodeWeight[integer], nodeWeight[t1]);
            }
        };

        Q.sort(comp);

        for (int i = 0; i < workWay.length; i++) {
            System.out.print(" -> " + workWay[i]);
        }
        System.out.println();

        while (!Q.isEmpty()) {
            int u = Q.remove(0);
            for (Edge edge : edges.get(u).values()) {
                if (nodeWeight[edge.nextNode] >= nodeWeight[u] + edge.weight) {
                    if (workNode[edge.nextNode] && pred[edge.nextNode] != u) {
                        System.out.println("u: " + u);
                        System.out.println("pred of edge.nextNode " + edge.nextNode + ": " + pred[edge.nextNode]);
                        System.out.println("9708 neighbours: ");
                        for (Integer neigh:edges.get(9708).keySet()){
                            System.out.println(neigh);
                        }
                        System.out.println(workNode[9708]);
                        System.out.print("yes");
                        end = System.nanoTime();
                        total = end - start;
                        System.out.println("\ntotal: " + total / 1000000000.0);
                        return;
                    }
                    nodeWeight[edge.nextNode] = nodeWeight[u] + edge.weight;
                    Q.sort(comp);
                }
            }
        }

        System.out.print("no");
        end = System.nanoTime();
        total = end - start;
        System.out.println("\ntotal: " + total / 1000000000.0);

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


    public static final class Comparators {
        /**
         * Verify that a comparator is transitive.
         *
         * @param <T>        the type being compared
         * @param comparator the comparator to test
         * @param elements   the elements to test against
         * @throws AssertionError if the comparator is not transitive
         */
        public static <T> void verifyTransitivity(Comparator<T> comparator, Collection<T> elements) throws IllegalArgumentException {
            for (T first : elements) {
                for (T second : elements) {
                    int result1 = comparator.compare(first, second);
                    int result2 = comparator.compare(second, first);
                    if (result1 != -result2) {
                        // Uncomment the following line to step through the failed case
                        //comparator.compare(first, second);
                        throw new IllegalArgumentException("compare(" + first + ", " + second + ") == " + result1 +
                                " but swapping the parameters returns " + result2);
                    }
                }
            }
            for (T first : elements) {
                for (T second : elements) {
                    int firstGreaterThanSecond = comparator.compare(first, second);
                    if (firstGreaterThanSecond <= 0)
                        continue;
                    for (T third : elements) {
                        int secondGreaterThanThird = comparator.compare(second, third);
                        if (secondGreaterThanThird <= 0)
                            continue;
                        int firstGreaterThanThird = comparator.compare(first, third);
                        if (firstGreaterThanThird <= 0) {
                            // Uncomment the following line to step through the failed case
                            //comparator.compare(first, third);
                            throw new IllegalArgumentException("compare(" + first + ", " + second + ") > 0, " +
                                    "compare(" + second + ", " + third + ") > 0, but compare(" + first + ", " + third + ") == " +
                                    firstGreaterThanThird);
                        }
                    }
                }
            }
        }

        /**
         * Prevent construction.
         */
        private Comparators() {
        }
    }
}
