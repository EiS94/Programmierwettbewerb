package Graph;

import java.util.HashSet;
import java.util.Objects;

public class Node {

    private Nodestatus status;
    private int x;
    private int y;

    public Node(int x, int y) {
        status = Nodestatus.white;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x &&
                y == node.y;
    }

    public HashSet<Node> getNeighbours(HashSet<Edge> edges) {
        HashSet<Node> nodes = new HashSet<>();
        for (Edge edge : edges) {
            if (edge.start.equals(this)) nodes.add(edge.end);
        }
        return nodes;
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
