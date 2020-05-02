import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import Graph.Graph;
import Graph.Node;
import Graph.Edge;

public class GraphTranslator {


    public static Tuple convertInputArray(String input) {
        //Split input per Line
        String[] split = input.split("\n");

        //Split firstLine by whitespace to get width and heigth
        String[] firstLine = split[0].split(" ");
        int h = Integer.parseInt(firstLine[0]);
        int w = Integer.parseInt(firstLine[1]);

        int[][] graphArray = new int[w * h][4];

        //write mazeString to a seperate 2-D Array with h+1 and 2*w+1 fields
        char[][] mazeString = new char[2 * w + 1][h + 1];
        for (int y = 0; y < h + 1; y++) {
            for (int x = 0; x < 2 * w + 1; x++) {
                mazeString[x][y] = split[1 + y].charAt(x);
            }
        }

        for (int height = 1; height < h + 1; height++) {
            for (int width = 1; width < 2 * w; width++) {
                char module = mazeString[width][height];
                if (module == ' ') {
                    //no horizontal wall |
                    if (width % 2 == 0) {

                        //no right wall
                        //int nodeX = ((width - 1) / 2) -1;
                        //int nodeY = height - 1;
                        graphArray[(width - 2) / 2 + (height - 1) * w][1] = 1;

                        //no left wall
                        //nodeX = ((width + 1) / 2) - 1;
                        //nodeY = height - 1;
                        graphArray[width / 2 + (height - 1) * w][3] = 1;
                    } else {
                        //no lower wall _
                        graphArray[(width - 1) / 2 + (height - 1) * w][2] = 1;

                        //no upper wall _
                        graphArray[(width - 1) / 2 + height * w][0] = 1;
                    }
                }
            }
        }

        int[] path = new int[Integer.parseInt(split[h + 2])];

        int counter = 0;
        for (int i = h+3; i < split.length; i++) {
            String[] s = split[i].split(" ");
            int width = Integer.parseInt(s[1]) - 1;
            int height = Integer.parseInt(s[0]) -1;
            path[counter++] = width + (height * w);
        }

        return new Tuple(graphArray, path, w);
    }


    public static Graph convertInput(String input) {
        //Split input per Line
        String[] split = input.split("\n");

        //Split firstLine by whitespace to get width and heigth
        String[] firstLine = split[0].split(" ");
        int h = Integer.parseInt(firstLine[0]);
        int w = Integer.parseInt(firstLine[1]);

        //write mazeString to a seperate 2-D Array with h+1 and 2*w+1 fields
        char[][] maze = new char[2 * w + 1][h + 1];
        for (int y = 0; y < h + 1; y++) {
            for (int x = 0; x < 2 * w + 1; x++) {
                maze[x][y] = split[1 + y].charAt(x);
            }
        }

        //check every module for paths to his max. 4 possible neighbours modules
        //stores the modules to the nodes-list
        //and stores a edge between the modules if there is a path
        HashSet<Node> nodes = new HashSet<>();
        HashSet<Edge> edges = new HashSet<>();
        int yMaze = 1;
        for (int y = 1; y < h + 1; y++) {
            int xMaze = 1;
            for (int x = 1; x < 2 * w + 1; x += 2) {

                //add the checked Module to the Node-list if not already happened
                Node node = new Node(xMaze, yMaze);
                if (!nodes.contains(node)) nodes.add(node);

                //check neighbour in same line right
                try {
                    if (maze[x + 1][y] != '|') {
                        //add neighbour node to nodes-list if not already happened
                        Node neighbour = new Node(xMaze + 1, yMaze);

                        if (!nodes.contains(neighbour)) nodes.add(neighbour);
                        //add edge to edges-List
                        Edge edge = new Edge(node, neighbour);
                        if (!edges.contains(edge)) edges.add(edge);
                    }
                } catch (IndexOutOfBoundsException e) {
                    //don't call checkNeighbour if the neighbours char is not in array-bound
                }
                //check neighbour in same line left
                try {
                    if (maze[x - 1][y] != '|') {
                        //add neighbour node to nodes-list if not already happened
                        Node neighbour = new Node(xMaze - 1, yMaze);

                        if (!nodes.contains(neighbour)) nodes.add(neighbour);
                        //add edge to edges-List
                        Edge edge = new Edge(node, neighbour);
                        if (!edges.contains(edge)) edges.add(edge);
                    }
                } catch (IndexOutOfBoundsException e) {
                    //don't call checkNeighbour if the neighbours char is not in array-bound
                }

                //check neigbour in upper line
                try {
                    //add neighbour node to nodes-list if not already happened
                    Node neighbour = new Node(xMaze, yMaze - 1);

                    if (checkNeighbour(maze[x][y], maze[x][y - 1], Line.UPPER)) {
                        if (!nodes.contains(neighbour)) nodes.add(neighbour);
                        //add edge to edges-List
                        Edge edge = new Edge(node, neighbour);
                        if (!edges.contains(edge)) edges.add(edge);
                    }
                } catch (IndexOutOfBoundsException e) {
                    //don't call checkNeighbour if the neighbours char is not in array-bound
                }

                //check neighbour in downer line
                try {
                    //add neighbour node to nodes-list if not already happened
                    Node neighbour = new Node(xMaze, yMaze + 1);

                    if (checkNeighbour(maze[x][y], maze[x][y + 1], Line.DOWNER)) {
                        if (!nodes.contains(neighbour)) nodes.add(neighbour);
                        //add edge to edges-List
                        Edge edge = new Edge(node, neighbour);
                        if (!edges.contains(edge)) edges.add(edge);
                    }
                } catch (IndexOutOfBoundsException e) {
                    //don't call checkNeighbour if the neighbours char is not in array-bound
                }
                xMaze++;
            }
            yMaze++;
        }

        //create a Node-Array with the amount of fields of the Integer in the h+2-th line of split
        Node[] path = new Node[Integer.parseInt(split[h + 2])];

        //fill the path-array with the nodes
        int counter = 0;
        for (int i = h + 3; i < split.length; i++) {
            String[] s = split[i].split(" ");
            path[counter] = new Node(Integer.parseInt(s[1]), Integer.parseInt(s[0]));
            counter++;
        }

        return new Graph(path, nodes, edges);
    }

    //check if there is a path to the neighbour field
    //returns true, if there is a path
    static boolean checkNeighbour(char field, char neighbour, Line line) {
        switch (field) {
            case ' ':
                switch (neighbour) {
                    case ' ':
                        //case : "x "
                        return true;
                    case '_':
                        switch (line) {
                            case UPPER:
                                //case : "_"
                                //       "x"
                                return false;
                            case DOWNER:
                                //case: "x"
                                //      "_"
                                return true;
                        }
                }
            case '_':
                //no possible way to downer line
                if (line == Line.DOWNER) return false;

                switch (neighbour) {
                    case ' ':
                        //case: "_ " or " _" or " "
                        //                      "_"
                        return true;
                    case '_':
                        switch (line) {
                            case UPPER:
                                //case: "_"
                                //      "_"
                                return false;
                        }
                }
            default:
                return false;
        }
    }

    static void printCharArray(char[][] maze) {
        for (int y = 0; y < maze[0].length; y++) {
            for (int x = 0; x < maze.length; x++) {
                System.out.print(maze[x][y]);
            }
            System.out.print("\n");
        }
    }

    private enum Line {

        SAME, UPPER, DOWNER;

    }

}
