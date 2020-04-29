import Graph.*;

import java.util.LinkedList;

public class Breitensuche {

    public static int findShortestPath(Graph graph, Node a, Node b){
        for (Node node:graph.getNodes()){
            node.setStatus(Nodestatus.white);
            node.setBfsParentNode(null);
            node.setD(-1);
        }
        for (Node node:graph.getNodes()){
            if (node.equals(a)){
                a = node;
            }
        }
        a.setD(0);
        a.setStatus(Nodestatus.grey);
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(a);
        while(!queue.isEmpty()){
            Node u = queue.remove(0);
            for (Node neighbour:u.getNeighbours()) {
                if (neighbour.getStatus() == Nodestatus.white){
                    neighbour.setStatus(Nodestatus.grey);
                    neighbour.setD(u.getD() + 1);
                    neighbour.setBfsParentNode(u);
                    queue.add(neighbour);
                }
            }
            u.setStatus(Nodestatus.black);
        }
        for (Node node:graph.getNodes()){
            if (node.equals(b)){
                b = node;
            }
        }
        return b.getD();
    }
}
