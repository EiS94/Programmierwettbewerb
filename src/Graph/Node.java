package Graph;

import java.util.Objects;

public class Node {

    private Nodestatus status;
    private String id;
    private int labyrinthID;

    public Node(String id, int labyrinthID){
        status = Nodestatus.white;
        this.id = id;
        this.labyrinthID = labyrinthID;
    }

    public Nodestatus getStatus() {
        return status;
    }

    public void setStatus(Nodestatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node knoten = (Node) o;
        return status == knoten.status &&
                Objects.equals(id, knoten.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, id);
    }
}
