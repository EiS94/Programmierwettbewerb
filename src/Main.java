import Graph.*;

import java.util.HashSet;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        long startTime;
        long endTime;
        double endTime2;
        double endTime3;
        long totalTime;

        int width = 32;
        int heigth = 200;
        int paths = 50;
        startTime = System.nanoTime();
        String sample = Samples.createSample(width,heigth,paths);
        int pathlength = test(sample);
        endTime2 = (System.nanoTime() - startTime)/1000000000.0;

        startTime = System.nanoTime();
        pathlength = testGraphBFS(sample);
        endTime3 = (System.nanoTime() - startTime)/1000000000.0;

        System.out.println("Höhe: " + heigth + ", Breite: " + width + ", Anzahl Pfade: " + paths);
        System.out.println("Pfadlänge Array: " +  pathlength);
        System.out.println("Pfadlänge Graph: " + pathlength);
        System.out.println("Dauer Array: " + endTime2 + " s");
        System.out.println("Dauer Graph: " + endTime3 + " s");

        /*
        startTime = System.nanoTime();
        testNewBFS();
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("\n" + totalTime/1000000000.0 + "s");
        */


        /*long totalStartTime;
        long totalEndTime;
        long totalTotalTime;



        long total = 0;
        int trys = 1000;
        totalStartTime = System.nanoTime();
        for (int i = 0; i < trys; i++) {
            startTime = System.nanoTime();
            test(Samples.createSample5(10000));
            endTime = System.nanoTime();
            totalTime = endTime - startTime;
            total += totalTime;
            //System.out.println("\n" + totalTime / 1000000000.0 + "s");
            //System.out.println(i);
        }
        total = total;
        System.out.println("\naverage = " + (total / trys / 1000000000.0) + "s");

        totalEndTime   = System.nanoTime();
        totalTotalTime = totalEndTime - totalStartTime;
        System.out.println("\ntotal time = " + totalTotalTime/1000000000.0 + "s");


        total = 0;
        trys = 1000;
        totalStartTime = System.nanoTime();
        for (int i = 0; i < trys; i++) {
            startTime = System.nanoTime();
            testGraphBFS(Samples.createSample5(10000));
            endTime = System.nanoTime();
            totalTime = endTime - startTime;
            total += totalTime;
            //System.out.println("\n" + totalTime / 1000000000.0 + "s");
            //System.out.println(i);
        }
        total = total;
        System.out.println("\naverage = " + (total / trys / 1000000000.0) + "s");

        totalEndTime   = System.nanoTime();
        totalTotalTime = totalEndTime - totalStartTime;
        System.out.println("\ntotal time = " + totalTotalTime/1000000000.0 + "s");*/
    }

    public static int test(String input) {

        Tuple t = GraphTranslator.convertInputArray(input);
        int pathlength = 0;
        for (int i = 0; i < t.getPath().length - 1; i++) {
            pathlength += Breitensuche.findShortestPath(t.getWidth(), t.getGraph(), t.getPath()[i], t.getPath()[i+1]);
        }
        return pathlength;
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

    public static int testGraphBFS(String input){

        Graph graph = GraphTranslator.convertInput(input);
        Node[] path = graph.getPath();

        Long start = System.nanoTime();

        for (Node node:graph.getNodes()){
            node.intNeighbours(graph, graph.getEdges());
        }

        Double end = (System.nanoTime() - start)/1000000000.0;
        System.out.println("Knotennachbarn bestimmen: " + end + " s");

        int pathlength = 0;
        for (int i = 0; i < path.length-1; i++) {
            pathlength += Breitensuche.findShortestPath(graph, path[i], path[i+1]);
        }
        return pathlength;
    }

}
