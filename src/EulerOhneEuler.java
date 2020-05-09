
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;

public class EulerOhneEuler {

    public static int[][] children;


    public static void main(String[] args) throws Exception {
        //start get Input
        BufferedReader reader = new BufferedReader(new StringReader(Samples.createSnake(11,11,10001)));
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

        //start BFS

        int[] color = new int[graph.length];
        int[] parent = new int[graph.length];
        color[0] = 1;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        parent[0] = -1;
        children = new int[graph.length][3];
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
        }

        //end BFS

        //start addingPaths

        long pathlength = 0;

        //System.out.println(euler);
        int i = 0;
        while (i < path.length - 1) {
            int a = graphToEuler[path[i]];
            int b = graphToEuler[path[i + 1]];
            int reach = 1;

            for (int j = i + 2; j < path.length; j++) {
                if (graphToEuler[path[j - 2]] == graphToEuler[path[j]]) {
                    ++reach;
                }
                else{
                    break;
                }
            }

            int aP = a;
            int bP = b;
            long aBPath = 0;
            while (aP != bP) {
                if (aP < bP) {
                    bP = parent[bP];
                } else {
                    aP = parent[aP];
                }
                ++aBPath;
            }
            pathlength += (aBPath * reach);
            i += reach;
        }

        //end addingPaths

        //output
        System.out.print(pathlength);

    }
}
