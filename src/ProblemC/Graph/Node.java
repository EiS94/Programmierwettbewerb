package ProblemC.Graph;

import java.util.HashSet;
import java.util.Objects;

public class Node {

    private Nodestatus status;
    private int x;
    private int y;
    private int d;
    private Node bfsParentNode;
    private HashSet<Node> neighbours;

    public Node(int x, int y) {
        status = Nodestatus.white;
        d=-1;
        bfsParentNode = null;
        this.x = x;
        this.y = y;
    }

    public Nodestatus getStatus() {
        return status;
    }

    public void setStatus(Nodestatus status) {
        this.status = status;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public Node getBfsParentNode() {
        return bfsParentNode;
    }

    public void setBfsParentNode(Node bfsParentNode) {
        this.bfsParentNode = bfsParentNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x &&
                y == node.y;
    }

    public void intNeighbours(Graph graph, HashSet<Edge> edges) {
        neighbours = new HashSet<Node>();
        for (Edge edge : edges) {
            if (edge.start.equals(this)) {
                for (Node node:graph.getNodes()){
                    if (node.equals(edge.end)){
                        neighbours.add(node);
                    }
                }
            }
        }
    }

    public HashSet<Node> getNeighbours() {
        return neighbours;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, x, y);
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }
}
