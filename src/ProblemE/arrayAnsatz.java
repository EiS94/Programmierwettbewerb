package ProblemE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class arrayAnsatz {

    static int[] match;
    static ArrayList<Integer>[] socketsBig;
    static boolean[] seen;

    public static void main(String[] args) throws Exception {

        //BufferedReader br = new BufferedReader(new FileReader("/home/eike/Dokumente/Uni/6. Semester/Seminar/Git/seminarprogproblemc/src/ProblemE/Samples/sample5.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = br.readLine().split(" ");
        int m = Integer.parseInt(strings[0]);
        int n = Integer.parseInt(strings[1]);
        int k = Integer.parseInt(strings[2]);

        int numWithSockets = 0;
        int numMatches = 0;

        socketsBig = new ArrayList[m + 2];

        for (int i = 0; i < m; i++) {
            socketsBig[i] = new ArrayList<>();
        }

        for (int i = 0; i < k; i++) {
            strings = br.readLine().split(" ");
            socketsBig[Integer.parseInt(strings[0]) - 1].add(Integer.parseInt(strings[1]) - 1);
        }

        //bipartiteMatching
        match = new int[n];
        Arrays.fill(match, -1);
        for (int i = 0; i < m; i++) {
            seen = new boolean[n];
            if (findMatches(i)) numMatches++;
        }

        if (numMatches == n) System.out.print(numMatches);

            //matching with 2 extra sockets for each socket
        else {
            int bestResult = numMatches;

            int[] matchCopy = Arrays.copyOf(match, match.length);

            for (int i = 0; i < m; i++) {
                numWithSockets = numMatches;

                socketsBig[m] = socketsBig[i];
                socketsBig[m + 1] = socketsBig[i];

                match = Arrays.copyOf(matchCopy, matchCopy.length);

                seen = new boolean[n];
                if (findMatches(m)) numWithSockets++;

                seen = new boolean[n];
                if (findMatches(m + 1)) numWithSockets++;

                bestResult = Math.max(bestResult, numWithSockets);

                if (numWithSockets == n || numWithSockets == m + 2 || numWithSockets == numMatches + 2) {
                    break;
                }
            }
            System.out.print(bestResult);
        }
    }

    static boolean findMatches(int socket) {
        for (Integer neighbour : socketsBig[socket]) {
            if (!seen[neighbour]) {
                seen[neighbour] = true;
                if (match[neighbour] < 0 || findMatches(match[neighbour])) {
                    match[neighbour] = socket;
                    return true;
                }
            }
        }
        return false;
    }

}
