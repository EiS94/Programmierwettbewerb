package ProblemL;

import com.sun.source.tree.Tree;

import java.io.*;
import java.util.*;

public class OneDimensionalToursInNodes {

    public static long main(String[] args) throws IOException {

        String path = "src\\ProblemL\\Samples\\";
        String file = "sample2.txt";



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

        TreeNode[] nodes = new TreeNode[rows * cols];
        TreeNode[] sortedNodes = new TreeNode[rows * cols];
        Tour[] tours = new Tour[mountaineers];


        //read mountain-map
        for (int i = 0; i < rows; i++) {
            strings = br.readLine().split(" ");
            for (int j = 0; j < cols; j++) {
                TreeNode node = new TreeNode(i, j, Integer.parseInt(strings[j]));
                nodes[(j * rows) + i] = node;
                sortedNodes[(j * rows) + i] = node;
            }
        }
        //sort mountain-map for height
        Arrays.sort(sortedNodes, Comparator.comparingInt(treeNode -> treeNode.height));

        //read mountaineers-routes
        for (int i = 0; i < mountaineers; i++) {
            strings = br.readLine().split(" ");
            tours[i] = new Tour(nodes[((Integer.parseInt(strings[1]) - 1) * rows) + Integer.parseInt(strings[0]) - 1],
                    nodes[((Integer.parseInt(strings[3]) - 1) * rows) + Integer.parseInt(strings[2]) - 1]);
            if (tours[i].startNode.equals(tours[i].endNode)){
                tours[i].value = tours[i].startNode.height;
            }
            else {
                tours[i].startNode.tours.add(tours[i]);
                tours[i].endNode.tours.add(tours[i]);
            }
        }

        ArrayList<Tour> toursLeft = new ArrayList<>(Arrays.asList(tours));

        int index;
        for (TreeNode node : sortedNodes) {
            if (node.x > 0) {
                index = (node.y * rows) + node.x - 1;
                nodes[index].updateParent();
                node.updateParent();
                TreeNode.merge(node.parent, nodes[index].parent);
            }
            if (node.y > 0) {
                index = ((node.y - 1) * rows) + node.x;
                nodes[index].updateParent();
                node.updateParent();
                TreeNode.merge(node.parent, nodes[index].parent);
            }
            if (node.x < rows - 1) {
                index = (node.y * rows) + node.x + 1;
                nodes[index].updateParent();
                node.updateParent();
                TreeNode.merge(node.parent, nodes[index].parent);
            }
            if (node.y < cols - 1) {
                index = ((node.y + 1) * rows) + node.x;
                nodes[index].updateParent();
                node.updateParent();
                TreeNode.merge(node.parent, nodes[index].parent);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Tour t : tours) {
            sb.append(t.value + "\n");
        }
        sb.setLength(sb.length() - 1);

        System.out.print(sb.toString());
        end = System.nanoTime();
        total = end - start;
        System.out.println("total: " + total/1000000000.0);
        return total;

    }

    private static class Tour {

        TreeNode startNode, endNode;
        int value;

        public Tour(TreeNode startNode, TreeNode endNode) {
            this.startNode = startNode;
            this.endNode = endNode;
        }
    }

    private static class TreeNode {

        TreeNode parent = this;
        boolean merged = false;
        int x, y, height;
        ArrayList<Tour> tours = new ArrayList<>();

        TreeNode(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        static void merge(TreeNode t1, TreeNode t2) {
            if (t1.tours.isEmpty()){
                t1.merged = true;
            }
            if (t2.tours.isEmpty()){
                t2.merged = true;
            }
            if (!t2.merged && !t1.merged && !t1.equals(t2) && t1.height >= t2.height) {
                t2.parent = t1;
                t2.merged = true;
                updateTours(t1, t2);
            }
        }

        static void updateTours(TreeNode t1, TreeNode t2){
            LinkedList<Tour> temp = new LinkedList<>();
            try {
                for (Tour tour : t2.tours) {
                    if (t1.tours.contains(tour)) {
                        t1.tours.remove(tour);
                        tour.value = t1.height;
                    } else {
                        temp.add(tour);
                    }
                }
                t1.tours.addAll(temp);
            } catch (Exception e){
                System.out.println("Hä?");
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
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TreeNode treeNode = (TreeNode) o;
            return x == treeNode.x &&
                    y == treeNode.y &&
                    height == treeNode.height;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, height);
        }
    }
}
