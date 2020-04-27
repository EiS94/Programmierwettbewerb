package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Graph {

    ArrayList<Node> pfad;
    HashSet<Node> knoten;
    HashSet<Edge> kanten;

    public Graph(ArrayList<Node> pfad, HashSet<Node> knoten, HashSet<Edge> kanten){
        this.pfad = pfad;
        this.knoten = knoten;
        this.kanten = kanten;
    }

    public ArrayList<Node> getPfad() {
        return pfad;
    }

    public HashSet<Node> getKnoten() {
        return knoten;
    }

    public HashSet<Edge> getKanten() {
        return kanten;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return Objects.equals(pfad, graph.pfad) &&
                Objects.equals(knoten, graph.knoten) &&
                Objects.equals(kanten, graph.kanten);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pfad, knoten, kanten);
    }
}
