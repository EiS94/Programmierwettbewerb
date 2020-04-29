package Graph;

import java.util.HashSet;
import java.util.Objects;

public class Graph {

    Node[] path;
    HashSet<Node> nodes;
    HashSet<Edge> edges;

    public Graph(Node[] pfad, HashSet<Node> knoten, HashSet<Edge> kanten){
        this.path = pfad;
        this.nodes = knoten;
        this.edges = kanten;
    }

    public Node[] getPath() {
        return path;
    }

    public HashSet<Node> getNodes() {
        return nodes;
    }

    public HashSet<Edge> getEdges() {
        return edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return Objects.equals(path, graph.path) &&
                Objects.equals(nodes, graph.nodes) &&
                Objects.equals(edges, graph.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, nodes, edges);
    }
}
