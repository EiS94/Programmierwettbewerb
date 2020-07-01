package ProblemL;

import com.sun.source.tree.Tree;

import java.io.*;
import java.util.*;

public class IDHashSet {

    public static long main(String[] args) throws IOException {

        /*
        String path = "src\\ProblemL\\Samples\\";
        String file = "sample2.txt";

         */


        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader br = new BufferedReader(new FileReader(path + file));
        //String sample = Samples.createSample(200,200,100000,1000000);
        //System.out.println(sample);


        long start;
        long end;
        long total;
        start = System.nanoTime();


        BufferedReader br = new BufferedReader(new StringReader(args[0]));

        String[] strings = br.readLine().split(" ");

        int rows = Integer.parseInt(strings[0]);
        int cols = Integer.parseInt(strings[1]);
        int mountaineers = Integer.parseInt(strings[2]);

        TreeNode[][] nodes = new TreeNode[cols][rows];
        TreeNode[] sortedNodes = new TreeNode[rows * cols];
        Tour[] tours = new Tour[mountaineers];


        //read mountain-map
        for (int i = 0; i < rows; i++) {
            strings = br.readLine().split(" ");
            for (int j = 0; j < cols; j++) {
                TreeNode node = new TreeNode(i, j, Integer.parseInt(strings[j]));
                nodes[j][i] = node;
                sortedNodes[(j * rows) + i] = node;
            }
        }
        //sort mountain-map for height
        Arrays.sort(sortedNodes, Comparator.comparingInt(treeNode -> treeNode.height));

        //read mountaineers-routes
        for (int i = 0; i < mountaineers; i++) {
            strings = br.readLine().split(" ");
            tours[i] = new Tour(nodes[Integer.parseInt(strings[1]) - 1][Integer.parseInt(strings[0]) - 1],
                    nodes[Integer.parseInt(strings[3]) - 1][Integer.parseInt(strings[2]) - 1]);
            if (tours[i].startNode.equals(tours[i].endNode)){
                tours[i].value = tours[i].startNode.height;
            }
            else {
                tours[i].startNode.tours.add(tours[i]);
                tours[i].endNode.tours.add(tours[i]);
            }
        }

        for (TreeNode node : sortedNodes) {
            if (node.x > 0) {
                nodes[node.y][node.x - 1].updateParent();
                node.updateParent();
                TreeNode.merge(node.parent, nodes[node.y][node.x - 1].parent);
            }
            if (node.y > 0) {
                nodes[node.y - 1][node.x].updateParent();
                node.updateParent();
                TreeNode.merge(node.parent, nodes[node.y - 1][node.x].parent);
            }
            if (node.x < rows - 1) {
                nodes[node.y][node.x + 1].updateParent();
                node.updateParent();
                TreeNode.merge(node.parent, nodes[node.y][node.x + 1].parent);
            }
            if (node.y < cols - 1) {
                nodes[node.y + 1][node.x].updateParent();
                node.updateParent();
                TreeNode.merge(node.parent, nodes[node.y + 1][node.x].parent);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Tour t : tours) {
            sb.append(t.value + "\n");
        }
        sb.setLength(sb.length() - 1);

        //System.out.print(sb.toString());


        end = System.nanoTime();
        total = end - start;
        System.out.println("total: " + total/1000000000.0);
        return total;
    }

    private static class Tour {

        TreeNode startNode, endNode;
        int value;
        static int nextID = 0;
        int ID;

        public Tour(TreeNode startNode, TreeNode endNode) {
            this.startNode = startNode;
            this.endNode = endNode;
            ID = nextID++;
        }

        @Override
        public boolean equals(Object o) {
            return ID == ((Tour) o).ID;
        }

        @Override
        public int hashCode() {
            return ID;
        }
    }

    private static class TreeNode {

        TreeNode parent = this;
        boolean merged = false;
        int x, y, height;
        HashSet<Tour> tours = new HashSet<>();
        static int nextID = 0;
        int ID;

        TreeNode(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
            ID = nextID++;
        }

        static void merge(TreeNode t1, TreeNode t2) {
            if (!t1.merged && !t1.equals(t2) && t1.height >= t2.height) {
                t2.parent = t1;
                t2.merged = true;
                updateTours(t1, t2);
            }
        }

        static void updateTours(TreeNode t1, TreeNode t2){
            if (t1.tours.size() < t2.tours.size()){
                HashSet<Tour> temp = new HashSet<>();
                for (Tour tour : t1.tours) {
                    if (t2.tours.contains(tour)) {
                        t2.tours.remove(tour);
                        tour.value = t1.height;
                    } else {
                        temp.add(tour);
                    }
                }
                t1.tours = t2.tours;
                t1.tours.addAll(temp);
            }
            else {
                HashSet<Tour> temp = new HashSet<>();
                for (Tour tour : t2.tours) {
                    if (t1.tours.contains(tour)) {
                        t1.tours.remove(tour);
                        tour.value = t1.height;
                    } else {
                        temp.add(tour);
                    }
                }
                t1.tours.addAll(temp);
            }
        }

        TreeNode updateParent() {
            if (!parent.equals(this)) {
                parent = parent.updateParent();
                return parent;
            } else {
                return this;
            }
        }

        @Override
        public boolean equals(Object o) {
            return ID == ((TreeNode) o).ID;
        }

        @Override
        public int hashCode() {
            return ID;
        }
    }
}
