import Graph.*;

import java.util.HashSet;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        long startTime;
        long endTime;
        long totalTime;
        /*
        startTime = System.nanoTime();
        testNewBFS();
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("\n" + totalTime/1000000000.0 + "s");
        */
        long totalStartTime;
        long totalEndTime;
        long totalTotalTime;


        totalStartTime = System.nanoTime();
        long total = 0;
        int trys = 100000;
        for (int i = 0; i < trys; i++) {
            startTime = System.nanoTime();
            test(Samples.createSample5(10000));
            endTime = System.nanoTime();
            totalTime = endTime - startTime;
            total += totalTime;
            //System.out.println("\n" + totalTime / 1000000000.0 + "s");
            System.out.println(i);
        }
        total = total;
        System.out.println("\naverage = " + (total / trys / 1000000000.0) + "s");

        totalEndTime   = System.nanoTime();
        totalTotalTime = totalEndTime - totalStartTime;
        System.out.println("\ntotal time = " + totalTotalTime/1000000000.0 + "s");
    }

    public static void test(String input) {

        Tuple t = GraphTranslator.convertInputArray(input);
        int pathlength = 0;
        for (int i = 0; i < t.getPath().length - 1; i++) {
            pathlength += Breitensuche.findShortestPath(t.getWidth(), t.getGraph(), t.getPath()[i], t.getPath()[i+1]);
        }
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

    public static void testGraphBFS(String input){

        Graph graph = GraphTranslator.convertInput(input);
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
