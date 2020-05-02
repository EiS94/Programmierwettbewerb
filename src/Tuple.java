public class Tuple {

    private int[][] graph;
    private int[] path;
    private int width;

    public Tuple(int[][] graph, int[] path, int width) {
        this.graph = graph;
        this.path = path;
        this.width = width;
    }

    public int[][] getGraph() {
        return graph;
    }

    public int[] getPath() {
        return path;
    }

    public int getWidth() {
        return width;
    }
}
