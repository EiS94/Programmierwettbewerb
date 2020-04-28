package Graph;

import java.util.Objects;

public class Edge {

    int weight;
    Node start;
    Node end;

    public Edge(Node start, Node end) {
        this.start = start;
        this.end = end;
        weight = 1;
    }

    public int getWeight() {
        return weight;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge kante = (Edge) o;
        return weight == kante.weight &&
                Objects.equals(start, kante.start) &&
                Objects.equals(end, kante.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, start, end);
    }

    @Override
    public String toString() {
        return start + " -> " + end;
    }
}
