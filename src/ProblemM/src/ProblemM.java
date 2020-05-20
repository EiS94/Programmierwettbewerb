package ProblemM.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;

public class ProblemM {

    public static HashMap<Integer, Node> nodes;

    public static class Node{

        public int color = 0;
        public int d = Integer.MAX_VALUE;
        public LinkedList<Edge> edges = new LinkedList<>();
    }

    public static class Edge{

        public int nextNode;
        public int weight;

        public Edge(int nextNode, int weight){
            this.nextNode = nextNode;
            this.weight = weight;
        }
    }

    public static String path = "C:\\Users\\Benedikt\\Desktop\\UNI\\Informatik\\6.Semester\\Seminar Prog\\Prog\\seminarprogproblemc\\src\\ProblemM\\samples\\sample-M.";

    public static void main(String[] args) throws Exception{
        nodes = new HashMap<>();


        //start reading Input
        BufferedReader reader = new BufferedReader(new FileReader(path + "1.in"));

        String firstLine = reader.readLine();
        String[] firstLineString = firstLine.split(" ");
        int numberOfNodes = Integer.parseInt(firstLineString[0]) + 1;
        int numberOfEdges = Integer.parseInt(firstLineString[1]);

        //+ is nose at 1
        //- is nose at 2
        for (int i = 1; i < numberOfNodes; i++) {
            nodes.put(i, new Node());
            nodes.put(-i, new Node());
        }

        for (int i = 0; i < numberOfEdges; i++) {
            String line = reader.readLine();
            String[] lineString = line.split(" ");
            int[] edge = new int[5];
            for (int j = 0; j < 5; j++) {
                edge[j] = Integer.parseInt(lineString[j]);
            }
            int nodeA = edge[0];
            int noseA = edge[1];
            int nodeB = edge[2];
            int noseB = edge[3];
            int weight = edge[4];

            //right Nodes
            if (noseA == 2){
                nodeA *= -1;
            }
            if (noseB == 2){
                nodeB *= -1;
            }

            nodes.get(nodeA).edges.add(new Edge(nodeB * -1, weight));
            nodes.get(nodeB).edges.add(new Edge(nodeA * -1, weight));

        }

        int startNode = Integer.parseInt(reader.readLine());
        reader.close();

        //end reading Input

        //Dijkstra
        nodes.get(startNode).color = 1;
        nodes.get(startNode).d = 0;

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(nodes.get(startNode));
        for (int i = 1; i < numberOfNodes; i++) {
            if (i != startNode) {
                queue.add(nodes.get(i));
            }
            queue.add(nodes.get(-i));
        }
        //Queue sorted

        while(!queue.isEmpty()){
            Node u = queue.get(0);
            for (Edge edge:u.edges){
                Node neighbour = nodes.get(edge.nextNode);

                //Relax
                if (neighbour.color != 1 && neighbour.d > u.d + edge.weight){
                    neighbour.d = u.d + edge.weight;

                    //DecreaseKey
                    int indexNeighbour = queue.indexOf(neighbour);
                    for (int i = indexNeighbour; i >= 0; i--) {
                        if (queue.get(i).d < neighbour.d){
                            queue.remove(indexNeighbour);
                            queue.add(i + 1,neighbour);
                            break;
                        }
                    }
                }
            }
            u.color = 1;
            queue.remove(u);
        }

        int total = nodes.get(-startNode).d;
        if (total == Integer.MAX_VALUE){
            System.out.print("impossible");
        }
        else{
            System.out.print(total);
        }
    }
}
