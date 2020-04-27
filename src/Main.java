import Graph.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Graph graph = GraphTranslator.convertInput(args);
        ArrayList<Node> pfad = graph.getPfad();
        int pathlength = 0;
        for (int i = 0; i < pfad.size()-1; i++) {
            pathlength += Dijkstra.findShortestPath(graph, pfad.get(i), pfad.get(i+1));
        }
        System.out.print(pathlength);
    }
}
