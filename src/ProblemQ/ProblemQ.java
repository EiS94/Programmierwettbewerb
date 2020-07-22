package ProblemQ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ProblemQ {

    public static void main(String[] args) throws IOException {

        //String input = Files.readString(Path.of("/home/eike/Dokumente/Uni/6. Semester/Seminar/GitNeu/seminarprogproblemc/src/ProblemQ/Samples/sample-Q-slides.9.in"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader br = new BufferedReader(new StringReader(input));

        int x = Integer.parseInt(br.readLine());
        String[] strings = br.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        int t = Integer.parseInt(strings[2]);

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
        }

        for (int i = 0; i < m; i++) {
            strings = br.readLine().split(" ");
            nodes[Integer.parseInt(strings[0]) - 1].neighbours.add(nodes[Integer.parseInt(strings[1]) - 1]);
            nodes[Integer.parseInt(strings[1]) - 1].neighbours.add(nodes[Integer.parseInt(strings[0]) - 1]);
        }

        for (int i = 0; i < n; i++) {
            strings = br.readLine().split(" ");
            nodes[i].cost = Integer.parseInt(strings[1]);
            nodes[i].duration = Integer.parseInt(strings[0]);
        }

        br.close();

        int[][] price = new int[x][n];
        for (int[] ints : price) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }

        price[nodes[0].duration - 1][0] = nodes[0].cost;

        for (int e = nodes[0].duration; e <= x; e++) {
            for (int v = 0; v < n; v++) {
                int a = e - 1 - nodes[v].duration;
                int b = v;
                if (a >= 0) {
                    int priceAB = price[a][b];
                    if (priceAB != Integer.MAX_VALUE) priceAB = priceAB + nodes[v].cost;
                    price[e - 1][v] = priceAB;
                    for (Node i : nodes[v].neighbours) {
                        a = e - 1 - nodes[v].duration - t;
                        b = i.id - 1;
                        if (a >= 0 && b >= 0) {
                            priceAB = price[a][b];
                            if (priceAB != Integer.MAX_VALUE) priceAB = priceAB + nodes[v].cost;
                            price[e - 1][v] = Math.min(price[e - 1][v], priceAB);
                        }
                    }
                }
            }
        }

        if (price[x - 1][0] == Integer.MAX_VALUE) System.out.print("It is a trap.");
        else System.out.print(price[x - 1][0]);

    }

    static class Node {

        List<Node> neighbours = new LinkedList<>();
        int id;
        static int counter = 1;
        int cost, duration;

        Node() {
            this.id = counter++;
        }

        @Override
        public String toString() {
            return String.valueOf(id);
        }
    }

}
