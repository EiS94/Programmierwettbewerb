import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class DomJudgeVersion {

    public static void main(String[] args) throws Exception{
        //start get Input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder inputBuilder = new StringBuilder();
        String line = reader.readLine();
        while(line != null){
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
        for (int i = h+3; i < split.length; i++) {
            String[] s = split[i].split(" ");
            int width = Integer.parseInt(s[1]) - 1;
            int height = Integer.parseInt(s[0]) -1;
            path[counter++] = width + (height * w);
        }

        //end Translation

        //start BFS

        int[] color = new int[graph.length];
        int[] parent = new int[graph.length];
        color[0] = 1;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(0);
        parent[0] = -1;
        while(!queue.isEmpty()){
            int u = queue.remove(0);
            int up = u - w;
            int right = (u + 1);
            int left = (u - 1);
            int down = u + w;
            //check UP neighbour
            if (graph[u][0] == 1 && up >= 0){
                if (color[up] == 0){
                    color[up] = 1;
                    parent[up] = u;
                    queue.add(up);
                }
            }
            //check RIGHT neighbour
            if (graph[u][1] == 1 && (right % w) != 0){
                if (color[right] == 0){
                    color[right] = 1;
                    parent[right] = u;
                    queue.add(right);
                }
            }
            //check DOWN neighbour
            if (graph[u][2] == 1 && down <= graph.length){
                if (color[down] == 0){
                    color[down] = 1;
                    parent[down] = u;
                    queue.add(down);
                }
            }
            //check LEFT neighbour
            if (graph[u][3] == 1 && (left % w) != w - 1){
                if (color[left] == 0){
                    color[left] = 1;
                    parent[left] = u;
                    queue.add(left);
                }
            }
        }

        //end BFS

        //start addingPaths

        int pathlength = 0;

        for (int i = 0; i < path.length - 1; i++) {
            LinkedList<Integer> parentsA = new LinkedList<>();
            LinkedList<Integer> parentsB = new LinkedList<>();
            int a = path[i];
            int b = path[i + 1];
            parentsA.add(a);
            parentsB.add(b);
            while (a != 0){
                a = parent[a];
                parentsA.add(a);
            }
            while (b != 0){
                b = parent[b];
                parentsB.add(b);
            }
            while(!parentsA.isEmpty() && !parentsB.isEmpty() && parentsA.getLast().equals(parentsB.getLast())){
                parentsA.removeLast();
                parentsB.removeLast();
            }
            pathlength += parentsA.size() + parentsB.size();
        }

        //end addingPaths

        //output
        System.out.print(pathlength);

    }
}
