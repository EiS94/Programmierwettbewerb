import Graph.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        long start;
        long end;
        double diff;
        start = System.nanoTime();
        System.out.println(tree(Samples.sample2));
        end = System.nanoTime();
        diff = (end - start) / 1000000000.0;
        System.out.println(diff + "s");

        start = System.nanoTime();
        System.out.println(test(Samples.sample2));
        end = System.nanoTime();
        diff = (end - start) / 1000000000.0;
        System.out.println(diff + "s");
    }

    public static int tree(String input){
        Tuple t = GraphTranslator.convertInputArray(input);
        int pathlength = 0;
        int[] tree = Breitensuche.returnTree(t.getWidth(), t.getGraph());

        for (int i = 0; i < t.getPath().length - 1; i++) {
            LinkedList<Integer> parentsA = new LinkedList<>();
            LinkedList<Integer> parentsB = new LinkedList<>();
            int a = t.getPath()[i];
            int b = t.getPath()[i + 1];
            parentsA.add(a);
            parentsB.add(b);
            while (a != 0){
                a = tree[a];
                parentsA.add(a);
            }
            while (b != 0){
                b = tree[b];
                parentsB.add(b);
            }
            while(!parentsA.isEmpty() && !parentsB.isEmpty() && parentsA.getLast() == parentsB.getLast()){
                parentsA.removeLast();
                parentsB.removeLast();
            }
            pathlength += parentsA.size() + parentsB.size();
        }


        return pathlength;
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


    public static void printArray(int[] array){
        System.out.print("[" + array[0]);
        for (int i = 1; i < array.length; i++) {
            System.out.print(", " + array[i]);
        }
        System.out.println("]");
    }

}
