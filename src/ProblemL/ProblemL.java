package ProblemL;

import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class ProblemL {

    public static void main(String[] args) throws Exception{

        String path = "C:\\Users\\Benedikt\\Desktop\\UNI\\Informatik\\6.Semester\\Seminar Prog\\Prog\\seminarprogproblemc\\src\\ProblemL\\Sample\\";
        String file = "sample-L.1.in";
        BufferedReader br = new BufferedReader(new FileReader(path + file));

        String[] strings = br.readLine().split(" ");
        int rows = Integer.parseInt(strings[0]);
        int col = Integer.parseInt(strings[1]);
        int mountaineers = Integer.parseInt(strings[2]);

        TreeNode[][] nodes = new TreeNode[col][rows];
        TreeNode[] sortedNodes = new TreeNode[rows * col];

        HashMap<TreeNode, HashMap<TreeNode, Integer>> finishedTours = new HashMap<TreeNode, HashMap<TreeNode, Integer>>();

        for (int i = 0; i < nodes.length; i++) {
            strings = br.readLine().split(" ");
            for (int j = 0; j < nodes[i].length; j++) {
                nodes[i][j] = new TreeNode(i + 1, j + 1, Integer.parseInt(strings[j]));
                sortedNodes[i*col + j] = nodes[i][j];
            }
        }

        int x1;
        int y1;
        int x2;
        int y2;
        for (int i = 0; i < mountaineers; i++) {
            strings = br.readLine().split(" ");
            x1 = Integer.parseInt(strings[0]);
            y1 = Integer.parseInt(strings[1]);
            x2 = Integer.parseInt(strings[2]);
            y2 = Integer.parseInt(strings[3]);
            TreeNode.addTour(nodes[y1 - 1][x1 - 1], nodes[y2 - 1][x2 - 1]);
        }

        Arrays.sort(sortedNodes);
        for (TreeNode sortedNode : sortedNodes) {
            if (sortedNode.x > 1) {
                TreeNode.merge(sortedNode, nodes[sortedNode.x - 2][sortedNode.y - 1]);
            }
            if (sortedNode.x < rows) {
                TreeNode.merge(sortedNode, nodes[sortedNode.x][sortedNode.y - 1]);
            }
            if (sortedNode.y > 1) {
                TreeNode.merge(sortedNode, nodes[sortedNode.x - 1][sortedNode.y - 2]);
            }
            if (sortedNode.y < col) {
                TreeNode.merge(sortedNode, nodes[sortedNode.x - 1][sortedNode.y]);
            }
            for (TreeNode[] row:nodes) {
                for (TreeNode node:row){
                    HashMap<TreeNode, Integer> map = node.checkTours();
                    if (finishedTours.containsKey(node)){
                        HashMap<TreeNode, Integer> temp = finishedTours.get(node);
                        for (TreeNode key:map.keySet()) {
                            temp.put(key, map.get(key));
                        }
                    }
                    else{
                        finishedTours.put(node, map);
                    }

                }
            }
        }
        for (HashMap<TreeNode, Integer> map:finishedTours.values()) {
            for (Integer inte:map.values()){
                System.out.println(inte);
            }
        }
    }

    public static class TreeNode implements Comparable<TreeNode>{

        public int x;
        public int y;
        public TreeNode parent = this;
        public TreeNode[] children = new TreeNode[4];
        public int index = 0;
        public int value;
        public LinkedList<TreeNode> tours = new LinkedList<>();

        public TreeNode(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }

        public static TreeNode merge(TreeNode a, TreeNode b){
            if (a.value > b.value){
                a.add(b);
                b.parent = a;
                return a;
            }
            a.parent = b;
            b.add(a);
            return b;
        }

        private void add(TreeNode node){
            if (!Arrays.asList(children).contains(node)) {
                if (index < 4) {
                    children[index] = node;
                } else {
                    throw new IllegalArgumentException("More then 4 Children");
                }
                ++index;
            }
        }

        public static TreeNode addTour(TreeNode a, TreeNode b){
            if (a.value < b.value){
                a.tours.add(b);
                return a;
            }
            else{
                b.tours.add(a);
                return b;
            }
        }

        public HashMap<TreeNode, Integer> checkTours(){
            HashMap<TreeNode, Integer> result = new HashMap<TreeNode, Integer>();
            LinkedList<TreeNode> tempTours = new LinkedList<>();
            updateParent();
            for (TreeNode node:tours){
                node.updateParent();
                if (this.parent.equals(node.parent)){
                    result.put(node, parent.value);
                }
                else{
                    tempTours.add(node);
                }
            }
            tours = tempTours;
            return result;
        }

        public TreeNode updateParent(){
            if (parent.equals(this)){
                return this;
            }
            return parent = parent.updateParent();
        }


        @Override
        public int compareTo(TreeNode o) {
            return this.value - o.value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TreeNode treeNode = (TreeNode) o;
            return x == treeNode.x &&
                    y == treeNode.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static class Tupel{
        public final TreeNode treeNode;
        public final int value;

        public Tupel(TreeNode treeNode, int value){
            this.value = value;
            this.treeNode = treeNode;
        }
    }
}
