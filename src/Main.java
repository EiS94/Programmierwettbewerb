import Graph.*;

import java.util.HashSet;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        String sample = "2 6\n" +
                " _ _ _ _ _ _ \n" +
                "|  _ _ _ _ _|\n" +
                "|_ _ _ _ _ _|\n" +
                "5\n" +
                "1 5\n" +
                "1 1\n" +
                "1 6\n" +
                "1 1\n" +
                "1 5";

        String sample2 = "5 5\n" +
                " _ _ _ _ _ \n" +
                "|_ _  |_  |\n" +
                "|  _| |  _|\n" +
                "| |_   _| |\n" +
                "|    _ _  |\n" +
                "|_|_ _ _|_|\n" +
                "7\n" +
                "4 4\n" +
                "1 4\n" +
                "3 1\n" +
                "4 5\n" +
                "1 2\n" +
                "2 2\n" +
                "5 4\n";


        String sample3 = "2 6\n" +
                " _ _ _ _ _ _ \n" +
                "|  _ _ _ _ _|\n" +
                "|_ _ _ _ _ _|\n" +
                "2\n" +
                "1 5\n" +
                "1 1";

        String sample4 = "2 2\n" +
                " _ _ \n" +
                "| | |\n" +
                "|_ _|\n" +
                "2\n" +
                "1 1\n" +
                "2 2\n";


        Graph graph = GraphTranslator.convertInput(sample2);
        Node[] path = graph.getPath();
        for (Node node:graph.getNodes()){
            node.intNeighbours(graph, graph.getEdges());
        }

        int pathlength = 0;
        for (int i = 0; i < path.length-1; i++) {
            pathlength += Breitensuche.findShortestPath(graph, path[i], path[i+1]);
        }
        System.out.print(pathlength);
    }

}
