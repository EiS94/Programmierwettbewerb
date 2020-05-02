import Graph.*;

import java.util.LinkedList;

public class Breitensuche {

    public static int findShortestPath(Graph graph, Node a, Node b){
        for (Node node:graph.getNodes()){
            node.setStatus(Nodestatus.white);
            node.setBfsParentNode(null);
            node.setD(-1);
        }
        for (Node node:graph.getNodes()){
            if (node.equals(a)){
                a = node;
            }
        }
        a.setD(0);
        a.setStatus(Nodestatus.grey);
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(a);
        while(!queue.isEmpty()){
            Node u = queue.remove(0);
            for (Node neighbour:u.getNeighbours()) {
                if (neighbour.getStatus() == Nodestatus.white){
                    neighbour.setStatus(Nodestatus.grey);
                    neighbour.setD(u.getD() + 1);
                    neighbour.setBfsParentNode(u);
                    queue.add(neighbour);
                }
            }
            u.setStatus(Nodestatus.black);
        }
        for (Node node:graph.getNodes()){
            if (node.equals(b)){
                b = node;
            }
        }
        return b.getD();
    }

    public static int findShortestPath(int[][] graph, int a, int b){
        //graph = new int[|V|][|V|]
        //Kante (x,y) => graph[x][y]=1 und graph[y][x]=1
        //0 white 1 grey 2 black
        int[] color = new int[graph.length];
        int[] d = new int[graph.length];
        color[a] = 1;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(a);
        while(!queue.isEmpty()){
            int u = queue.remove(0);
            for (int i = 0; i < graph[u].length; i++) {
                //is i a neighbour of u?
                if (graph[u][i] == 1){
                    if (color[i] == 0){
                        color[i] = 1;
                        d[i] = d[u] + 1;
                        queue.add(i);
                        if (i == b){
                            return d[b];
                        }
                    }
                }
            }
            //color[u] = 2;
        }
        return d[b];
    }


    public static int findShortestPath(int width, int[][] graph, int a, int b){
        //graph = new int[|V|][4]
        //0 Up, 1 Right, 2 Down, 3 Left
        //Kante (x,y) y is over x => graph[x][0]=1 und graph[y][2]=1
        //x UP -> y = x - width
        //x RIGHT -> y = x + 1
        //x LEFT -> y = x - 1
        //x DOWN -> y = x + width

        //0 white 1 grey 2 black
        int[] color = new int[graph.length];
        int[] d = new int[graph.length];
        color[a] = 1;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(a);
        while(!queue.isEmpty()){
            int u = queue.remove(0);
            int up = u - width;
            int right = (u + 1);
            int left = (u - 1);
            int down = u + width;
            //check UP neighbour
            if (graph[u][0] == 1 && up >= 0){
                if (color[up] == 0){
                    color[up] = 1;
                    d[up] = d[u] + 1;
                    queue.add(up);
                    if (up == b){
                        return d[b];
                    }
                }
            }
            //check RIGHT neighbour
            if (graph[u][1] == 1 && (right % width) != 0){
                if (color[right] == 0){
                    color[right] = 1;
                    d[right] = d[u] + 1;
                    queue.add(right);
                    if (right == b){
                        return d[b];
                    }
                }
            }
            //check DOWN neighbour
            if (graph[u][2] == 1 && down <= graph.length){
                if (color[down] == 0){
                    color[down] = 1;
                    d[down] = d[u] + 1;
                    queue.add(down);
                    if (down == b){
                        return d[b];
                    }
                }
            }
            //check LEFT neighbour
            if (graph[u][3] == 1 && (left % width) != width - 1){
                if (color[left] == 0){
                    color[left] = 1;
                    d[left] = d[u] + 1;
                    queue.add(left);
                    if (left == b){
                        return d[b];
                    }
                }
            }
        }
        return d[b];
    }
}
