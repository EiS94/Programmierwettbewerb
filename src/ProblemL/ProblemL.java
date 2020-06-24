package ProblemL;

import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ProblemL {

    public static void main(String[] args) throws IOException {

        String file = "/home/eike/Dokumente/Uni/6. Semester/Seminar/Git/seminarprogproblemc/src/ProblemL/Samples/sample1.txt";

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader(file));

        String[] strings = br.readLine().split(" ");

        int rows = Integer.parseInt(strings[0]);
        int cols = Integer.parseInt(strings[1]);
        int mountaineers = Integer.parseInt(strings[2]);

        TreeNode[][] nodes = new TreeNode[rows][cols];
        TreeNode[] sortedNodes = new TreeNode[rows * cols];
        Tour[] tours = new Tour[mountaineers];


        //read mountain-map
        for (int i = 0; i < rows; i++) {
            strings = br.readLine().split(" ");
            for (int j = 0; j < cols; j++) {
                TreeNode node = new TreeNode(j, i, Integer.parseInt(strings[j]));
                nodes[i][j] = node;
                sortedNodes[(i * cols) + j] = node;
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
                TreeNode.merge(node, nodes[node.y][node.x - 1]);
            }
            if (node.y > 0) {
                TreeNode.merge(node, nodes[node.y - 1][node.x]);
            }
            if (node.x < cols - 1) {
                TreeNode.merge(node, nodes[node.y][node.x + 1]);
            }
            if (node.y < rows - 1) {
                TreeNode.merge(node, nodes[node.y + 1][node.x]);
            }
            Tour tour;
            for (int i = 0; i < toursLeft.size(); i++) {
                tour = toursLeft.get(i);
                try {
                    tour.startNode.updateParent();

                } catch (StackOverflowError e) {
                    System.out.println("Startnode x: " + tour.startNode.x + ", y: " + tour.startNode.y + " Node: " + node.x + ", " + node.y);
                }
                try {
                    tour.endNode.updateParent();
                } catch (StackOverflowError e) {
                    System.out.println("Endnode x: " + tour.endNode.x + ", y: " + tour.endNode.y);
                }
                if (tour.startNode.parent.equals(tour.endNode.parent)) {
                    tour.value = tour.endNode.parent.height;
                    toursLeft.remove(i--);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Tour tour : tours) {
            sb.append(tour.value + "\n");
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
            if (t1.height <= t2.height && !t1.childs.contains(t2)) {
                t1.parent = t2;
                t2.childs.add(t1);
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
