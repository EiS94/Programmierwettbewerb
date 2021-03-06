package ProblemC;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;

public class EulerSquarSol {


    public static void main(String[] args) throws Exception {
        long startTime;
        long endTime;
        double diffTime;
        //start get Input
        BufferedReader reader = new BufferedReader(new StringReader(Samples.createSnake(100,100,10000)));
        startTime = System.nanoTime();
        StringBuilder inputBuilder = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            inputBuilder.append(line + "\n");
            line = reader.readLine();
        }
        String input = inputBuilder.toString();
        //end get Input


        //get Translation
        String[] split = input.split("\n");

        //Split firstLine by whitespace to get width and heigth
        String[] firstLine = split[0].split(" ");
        int h = Integer.parseInt(firstLine[0]);
        int w = Integer.parseInt(firstLine[1]);

        int[][] graph = new int[w * h][4];

        //write mazeString to a seperate 2-D Array with h+1 and 2*w+1 fields
        char[][] mazeString = new char[2 * w + 1][h + 1];
        for (int y = 0; y < h + 1; y++) {
            for (int x = 0; x < 2 * w + 1; x++) {
                mazeString[x][y] = split[1 + y].charAt(x);
            }
        }

        for (int height = 1; height < h + 1; height++) {
            for (int width = 1; width < 2 * w; width++) {
                char module = mazeString[width][height];
                if (module == ' ') {
                    //no horizontal wall |
                    if (width % 2 == 0) {

                        //no right wall
                        //int nodeX = ((width - 1) / 2) -1;
                        //int nodeY = height - 1;
                        graph[(width - 2) / 2 + (height - 1) * w][1] = 1;

                        //no left wall
                        //nodeX = ((width + 1) / 2) - 1;
                        //nodeY = height - 1;
                        graph[width / 2 + (height - 1) * w][3] = 1;
                    } else {
                        //no lower wall _
                        graph[(width - 1) / 2 + (height - 1) * w][2] = 1;

                        //no upper wall _
                        graph[(width - 1) / 2 + height * w][0] = 1;
                    }
                }
            }
        }

        int[] path = new int[Integer.parseInt(split[h + 2])];

        int counter = 0;
        for (int i = h + 3; i < split.length; i++) {
            String[] s = split[i].split(" ");
            int width = Integer.parseInt(s[1]) - 1;
            int height = Integer.parseInt(s[0]) - 1;
            path[counter++] = width + (height * w);
        }

        //end Translation
        endTime = System.nanoTime();
        diffTime = (endTime - startTime) / 1000000000.0;
        System.out.println("Translator: " + diffTime);
        startTime = System.nanoTime();

        //start BFS

        int[] color = new int[graph.length];
        int[] parent = new int[graph.length];
        color[0] = 1;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        parent[0] = -1;
        int[][] children = new int[graph.length][3];
        int[] numberOfChildren = new int[graph.length];
        int[] graphToEuler = new int[graph.length];
        //int[] eulerToGraph = new int[graph.length];
        int newNameCounter = 1;
        while (!queue.isEmpty()) {
            int u = queue.remove(0);
            int up = u - w;
            int right = (u + 1);
            int left = (u - 1);
            int down = u + w;
            int childcounter = 0;
            int uTrans = graphToEuler[u];
            //check UP neighbour
            if (graph[u][0] == 1 && up >= 0) {
                if (color[up] == 0) {
                    //standard BFS
                    color[up] = 1;
                    queue.add(up);
                    //Euler Vorbereitung
                    graphToEuler[up] = newNameCounter;
                    //eulerToGraph[newNameCounter] = up;
                    parent[newNameCounter] = uTrans;
                    children[uTrans][childcounter] = newNameCounter;
                    ++childcounter;
                    ++newNameCounter;
                }
            }
            //check RIGHT neighbour
            if (graph[u][1] == 1 && (right % w) != 0) {
                if (color[right] == 0) {
                    //standard BFS
                    color[right] = 1;
                    queue.add(right);
                    //Euler Vorbereitung
                    graphToEuler[right] = newNameCounter;
                    //eulerToGraph[newNameCounter] = right;
                    parent[newNameCounter] = uTrans;
                    children[uTrans][childcounter] = newNameCounter;
                    ++childcounter;
                    ++newNameCounter;
                }
            }
            //check DOWN neighbour
            if (graph[u][2] == 1 && down <= graph.length) {
                if (color[down] == 0) {
                    //standard BFS
                    color[down] = 1;
                    queue.add(down);
                    //Euler Vorbereitung
                    graphToEuler[down] = newNameCounter;
                    //eulerToGraph[newNameCounter] = down;
                    parent[newNameCounter] = uTrans;
                    children[uTrans][childcounter] = newNameCounter;
                    ++childcounter;
                    ++newNameCounter;
                }
            }
            //check LEFT neighbour
            if (graph[u][3] == 1 && (left % w) != w - 1) {
                if (color[left] == 0) {
                    //standard BFS
                    color[left] = 1;
                    queue.add(left);
                    //Euler Vorbereitung
                    graphToEuler[left] = newNameCounter;
                    //eulerToGraph[newNameCounter] = left;
                    parent[newNameCounter] = uTrans;
                    children[uTrans][childcounter] = newNameCounter;
                    ++childcounter;
                    ++newNameCounter;
                }
            }
            numberOfChildren[graphToEuler[u]] = childcounter;
        }

        //end BFS

        endTime = System.nanoTime();
        diffTime = (endTime - startTime) / 1000000000.0;
        System.out.println("BFS: " + diffTime);
        startTime = System.nanoTime();

        //start Eulertour

        LinkedList<Integer> euler = new LinkedList<>();
        LinkedList<Integer> rmqEuler = new LinkedList<>();
        int[] firstEncounter = new int[graph.length]; //Ein wilder Knoten taucht auf.......

        euler.add(0);
        rmqEuler.add(0);
        firstEncounter[0] = 0;
        int depth = 0;
        while (true) {
            int u = euler.getLast();
            if (numberOfChildren[u] == 0) {
                if (u == 0) {
                    break;
                }
                euler.add(parent[u]);
                --depth;
            } else {
                --numberOfChildren[u];
                int nextChild = children[u][numberOfChildren[u]];
                euler.add(nextChild);
                firstEncounter[nextChild] = euler.size() - 1;
                ++depth;
            }

            rmqEuler.add(depth);
        }

        //end Eulertour

        endTime = System.nanoTime();
        diffTime = (endTime - startTime) / 1000000000.0;
        System.out.println("Eulertour: " + diffTime);
        startTime = System.nanoTime();

        //start normal RMQ
        LinkedList<Integer> rmqParts = new LinkedList<>();
        int square = (int) Math.sqrt(euler.size());
        counter = 0;
        int rmqMin = 0;
        for (int i = 0; i < euler.size(); i++) {
            if (square == counter){
                rmqParts.add(rmqMin);
                counter = 0;
                rmqMin = euler.get(i);
            }
            else{
                int temp = euler.get(i);
                if (temp < rmqMin){
                    rmqMin = temp;
                }
            }
            ++counter;
        }
        if (euler.size() % square != 0){
            rmqParts.add(rmqMin);
        }

        //printRMQListArray(normalRMQ);
        //end normal RMQ

        endTime = System.nanoTime();
        diffTime = (endTime - startTime) / 1000000000.0;
        System.out.println("RMQ: " + diffTime);
        startTime = System.nanoTime();

        //start addingPaths

        int pathlength = 0;

        //System.out.println(euler);
        for (int i = 0; i < path.length - 1; i++) {
            //System.out.println("path " + i + "->" + (i + 1));
            int a = graphToEuler[path[i]];
            int b = graphToEuler[path[i + 1]];
            int start = firstEncounter[a];
            int end = firstEncounter[b];

            if (start > end) {
                int temp = end;
                end = start;
                start = temp;
            }


            //start RMQ Zugriff
            int diff = end - start;

            int startSqu = (start + square - 1) / square;
            int endSqu = end / square;

            int min = rmqParts.get(startSqu);
            for (int j = startSqu + 1; j < endSqu; j++) {
                if (rmqParts.get(j) < min){
                    min = rmqParts.get(j);
                }
            }

            int end1 = startSqu * square;
            for (int j = start; j < end1; j++) {
                if (euler.get(j) < min){
                    min = euler.get(j);
                }
            }

            int start2 = endSqu * square;
            for (int j = start2; j < end; j++) {
                if (euler.get(j) < min){
                    min = euler.get(j);
                }
            }

            int tempLength = 0;
            //min ist der lca
            int p = a;
            while (min != p) {
                p = parent[p];
                ++tempLength;
            }

            //min ist der lca
            p = b;
            while (min != p) {
                p = parent[p];
                ++tempLength;
            }
            //System.out.println("Pfad " + a + "->" + b + " lca(" + min + "): " + tempLength);
            pathlength += tempLength;
        }

        //end addingPaths

        endTime = System.nanoTime();
        diffTime = (endTime - startTime) / 1000000000.0;
        System.out.println("Pfad: " + diffTime);
        startTime = System.nanoTime();

        //output
        System.out.print(pathlength);

    }

    public static int getLog(int a) {
        int log = 0;
        while (a > 1) {
            a >>= 1;
            ++log;
        }
        return log;
    }

    public static void printRMQListList(LinkedList<LinkedList<Integer>> rmq) {
        for (int i = 0; i < rmq.size(); i++) {
            System.out.print(i + ":");
            for (int j = 0; j < i; j++) {
                System.out.print("  ");
            }
            System.out.print("[");
            for (int j = 0; j < rmq.get(i).size() - 1; j++) {
                if (rmq.get(i).get(j) < 10){
                    System.out.print(" ");
                }
                System.out.print(rmq.get(i).get(j) + ", ");
            }
            if (rmq.get(i).getLast() < 10){
                System.out.print(" ");
            }
            System.out.print(rmq.get(i).getLast());
            System.out.println("]");
        }
    }

    public static void printRMQListArray(LinkedList<int[]> rmq) {
        for (int i = 0; i < rmq.size(); i++) {
            System.out.print(i + ":");
            for (int j = 0; j < i; j++) {
                System.out.print("  ");
            }
            System.out.print("[");
            for (int j = 0; j < rmq.get(i).length - 1; j++) {
                if (rmq.get(i)[j] < 10){
                    System.out.print(" ");
                }
                System.out.print(rmq.get(i)[j] + ", ");
            }
            if (rmq.get(i)[rmq.get(i).length - 1] < 10){
                System.out.print(" ");
            }
            System.out.print(rmq.get(i)[rmq.get(i).length - 1]);
            System.out.println("]");
        }
    }
}
