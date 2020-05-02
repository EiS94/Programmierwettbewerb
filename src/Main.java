import Graph.*;

import java.util.HashSet;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        testNewBFS();
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("\n" + totalTime/1000000000.0 + "s");


        startTime = System.nanoTime();
        test();
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("\n" + totalTime/1000000000.0 + "s");
    }

    public static void test() {
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

        Tuple t = GraphTranslator.convertInputArray(sample);
        int pathlength = 0;
        for (int i = 0; i < t.getPath().length - 1; i++) {
            pathlength += Breitensuche.findShortestPath(t.getWidth(), t.getGraph(), t.getPath()[i], t.getPath()[i+1]);
        }
        System.out.println("test: " + pathlength);
    }

    public static void testNewBFS(){
        //Sample4
        int[][] graphSample4 = {{0,0,1,0},{0,0,1,0},
                                {1,1,0,0},{1,0,0,1}};
        int widthSample4 = 2;
        int[] pathSample4 = {0, 3};

        //Sample
        int[][] graphSample = {{0,1,1,0},{0,1,0,1},{0,1,0,1},{0,1,0,1},{0,1,0,1},{0,0,0,1},
                                {1,1,0,0},{0,1,0,1},{0,1,0,1},{0,1,0,1},{0,1,0,1},{0,0,0,1}};
        int widthSample = 6;
        int[] pathSample = {5, 1, 6, 1, 5};


        int[][] testGraph = graphSample;
        int testWidth = widthSample;
        int[] testPath = pathSample;
        for (int i = 0; i < testPath.length; i++) {
            --pathSample[i];
        }

        int pathlength = 0;
        for (int i = 0; i < testPath.length - 1; i++) {
            pathlength += Breitensuche.findShortestPath(testWidth, testGraph, testPath[i], testPath[i+1]);
        }
        System.out.println("testNewBFS: " + pathlength);
    }

    public static void testGraphBFS(){
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


        Graph graph = GraphTranslator.convertInput(sample);
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
