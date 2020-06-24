package ProblemL;

import com.sun.source.tree.Tree;

import java.io.*;
import java.util.*;

public class ProblemL {

    public static void main(String[] args) throws IOException {

        String path = "/home/eike/Dokumente/Uni/6. Semester/Seminar/Git/seminarprogproblemc/src/ProblemL/Samples/";
        String file = "sample2.txt";



        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader br = new BufferedReader(new FileReader(path + file));
        String sample = Samples.createSample(50,50,10000,20);
        //System.out.println(sample);
        BufferedReader br = new BufferedReader(new StringReader(sample));

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
        }

        ArrayList<Tour> toursLeft = new ArrayList<>(Arrays.asList(tours));

        for (TreeNode node : sortedNodes) {
            if (node.x > 0) {
                nodes[node.y][node.x - 1].updateParent();
                TreeNode.merge(node.parent, nodes[node.y][node.x - 1].parent);
            }
            if (node.y > 0) {
                nodes[node.y - 1][node.x].updateParent();
                TreeNode.merge(node.parent, nodes[node.y - 1][node.x].parent);
            }
            if (node.x < rows - 1) {
                nodes[node.y][node.x + 1].updateParent();
                TreeNode.merge(node.parent, nodes[node.y][node.x + 1].parent);
            }
            if (node.y < cols - 1) {
                nodes[node.y + 1][node.x].updateParent();
                TreeNode.merge(node.parent, nodes[node.y + 1][node.x].parent);
            }
            Tour tour;
            for (int i = 0; i < toursLeft.size(); i++) {
                tour = toursLeft.get(i);
                tour.startNode.updateParent();
                tour.endNode.updateParent();
                if (tour.startNode.parent.equals(tour.endNode.parent)) {
                    tour.value = tour.endNode.parent.height;
                    toursLeft.remove(i--);
                    if (toursLeft.isEmpty()) {
                        break;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Tour t : tours) {
            sb.append(t.value + "\n");
        }
        sb.setLength(sb.length() - 1);

        System.out.print(sb.toString());


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
        LinkedList<TreeNode> childs = new LinkedList<>();
        int x, y, height;

        TreeNode(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        static void merge(TreeNode t1, TreeNode t2) {
            if (t1.height >= t2.height && !t2.childs.contains(t1)) {
                t2.parent = t1;
                t1.childs.add(t2);
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
