package ProblemC;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Samples {

    public static String sample = "2 6\n" +
            " _ _ _ _ _ _ \n" +
            "|  _ _ _ _ _|\n" +
            "|_ _ _ _ _ _|\n" +
            "2\n" +
            "1 1\n" +
            "2 1\n";

    public static String sample1 = "2 6\n" +
            " _ _ _ _ _ _ \n" +
            "|  _ _ _ _ _|\n" +
            "|_ _ _ _ _ _|\n" +
            "5\n" +
            "1 5\n" +
            "1 1\n" +
            "1 6\n" +
            "1 1\n" +
            "1 5";

    public static String sample2 = "5 5\n" +
            " _ _ _ _ _ \n" +
            "|_ _  |_  |\n" +
            "|  _| |  _|\n" +
            "| |_   _| |\n" +
            "|    _ _  |\n" +
            "|_|_ _ _|_|\n" +
            "7\n" +
            "4 4\n" +
            "1 4\n" +
            "3 1\n" +
            "4 5\n" +
            "1 2\n" +
            "2 2\n" +
            "5 4\n";


    public static String sample3 = "2 6\n" +
            " _ _ _ _ _ _ \n" +
            "|  _ _ _ _ _|\n" +
            "|_ _ _ _ _ _|\n" +
            "2\n" +
            "1 5\n" +
            "1 1";

    public static String sample4 = "2 2\n" +
            " _ _ \n" +
            "| | |\n" +
            "|_ _|\n" +
            "2\n" +
            "1 1\n" +
            "2 2\n";

    public static String createSample5(int pathlength) {
        StringBuilder result = new StringBuilder("5 13\n" +
                " _ _ _ _ _ _ _ _ _ _ _ _ _ \n" +
                "|_ _  |_   _ _ _   _ _ _  |\n" +
                "|  _| |  _ _|_ _ _|       |\n" +
                "| |_   _| |_ _ _ _ _|_|  _|\n" +
                "|    _ _    | |   |  _ _  |\n" +
                "|_|_ _ _|_|_ _ _|_ _ _ _ _|\n" +
                pathlength + "\n");
        for (int i = 0; i < pathlength; i++) {
            int x = (int) (Math.random() * 13) + 1;
            int y = (int) (Math.random() * 5) + 1;
            result.append(y).append(" ").append(x).append("\n");
        }
        return result.toString();
    }

    public static String sample6 = "2 2\n" +
            " _ _ \n" +
            "|  _|\n" +
            "|_ _|\n" +
            "2\n" +
            "1 1\n" +
            "2 2\n";

    public static String sample7 = "20 20\n" +
            " _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n" +
            "| |_  |_    |  _    |  _ _ _ _   _ _    |\n" +
            "|  _    |_| |  _|_|_  |   | |_ _|_ _ _|_|\n" +
            "| | | |  _  | |  _| |_ _|_  | |_  |_ _  |\n" +
            "|_|  _|_ _|_  |   |_ _ _  | |_  | |    _|\n" +
            "|   | |_  |_   _|_ _|_  |  _| |  _ _|_  |\n" +
            "|_| |  _|_  |_  |  _  |      _  |   |_ _|\n" +
            "| |_  | |_ _    |_|_  |_| | |_ _|_| |_  |\n" +
            "|  _|_ _ _|_  |_| |_  |  _| |_ _ _ _   _|\n" +
            "|  _ _| | | |_|  _ _ _ _|  _    | | |  _|\n" +
            "|      _  | | |_ _   _|_  |_ _|_  | | | |\n" +
            "|_|_|_|_ _ _     _|_ _ _|_    |     |   |\n" +
            "|_ _ _   _ _|_|  _|_   _|_ _|_| |_|   |_|\n" +
            "| |_   _|_  | |_ _  |  _|    _ _  |_|_  |\n" +
            "| |_  |_  |  _|_       _| |  _ _|_| |   |\n" +
            "| | |     |    _|_|_|_  | |_  |   |   | |\n" +
            "| | | |_|_ _|  _  | |_ _  |_ _| | | |_| |\n" +
            "|  _  | |_   _  |_  | | |_ _|_  | |_  |_|\n" +
            "|_  | |_  | |_ _| | | |    _    |_|_  | |\n" +
            "|  _|_|   |_    |_      |  _| |_  |_    |\n" +
            "|_ _ _|_|_ _ _|_ _|_|_|_|_|_ _ _|_ _ _|_|\n" +
            "5\n" +
            "6 1\n" +
            "19 9\n" +
            "15 16\n" +
            "11 1\n" +
            "13 9";


    public static String createSample(int width, int heigth, int paths) {
        //first line, height and width
        StringBuilder sb = new StringBuilder(heigth + " " + width + "\n");

        //fist line of maze, always append "_ "
        for (int i = 0; i < width; i++) {
            sb.append(" _");
        }
        sb.append(" ");

        //lines 2 to h-1 of maze, append "_ " or "  " or " |"
        for (int i = 0; i < heigth - 1; i++) {
            sb.append("\n|");
            for (int j = 0; j < width - 1; j++) {
                Double d = Math.random();
                if (d < 0.5) sb.append("  ");
                else if (d < 0.75) sb.append("_ ");
                else sb.append(" |");
            }
            sb.append(" |");
        }
        sb.append("\n|");

        //last line of maze, always append "_ "
        for (int i = 0; i < width - 1; i++) {
            sb.append("_ ");
        }
        sb.append("_|");

        //add number of paths
        sb.append("\n" + paths + "\n");

        //add Random paths
        Random rd = new Random();
        int lastX = -1;
        int lastY = -1;
        for (int i = 0; i < paths; i++) {
            int x = rd.nextInt(width) + 1;
            int y = rd.nextInt(heigth) + 1;
            while (x == lastX && y == lastY) {
                x = rd.nextInt(width) + 1;
                y = rd.nextInt(heigth) + 1;
            }
            sb.append(y + " " + x + "\n");
            lastX = x;
            lastY = y;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static String createSnake(int width, int height, int pathlength) throws Exception {
        String filepath = "C:\\Users\\Benedikt\\Desktop\\UNI\\Informatik\\6.Semester\\Seminar Prog\\Prog\\seminarprogproblemc\\samples\\generatedSnake.txt";
        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        writer.write(height + " " + width + "\n");
        int maxX = height % 2 == 0 ? 1 : width;
        //first line
        for (int i = 0; i < width; i++) {
            writer.append(" _");
        }
        writer.append(" \n");
        for (int i = 0; i < (height - 1) / 2; i++) {
            writer.append(createLineRight(width));
            writer.append(createLineLeft(width));
        }
        if (height % 2 == 0) {
            writer.append(createLineRight(width));
        }
        writer.append(createLine(width));
        writer.append(pathlength + "").append("\n");
        for (int i = 0; i < pathlength / 2; i++) {
            writer.append("1 1\n").append(height + "").append(" ").append(maxX + "").append("\n");
        }
        if (pathlength % 2 != 0) {
            writer.append("1 1\n");
        }
        //System.out.println(builder.toString());
        writer.close();
        return filepath;
    }


    private static String createLineRight(int width) {
        StringBuilder builder = new StringBuilder();
        builder.append("|_");
        for (int i = 0; i < width - 2; i++) {
            builder.append(" _");
        }
        builder.append("  |\n");
        return builder.toString();
    }

    private static String createLineLeft(int width) {
        StringBuilder builder = new StringBuilder();
        builder.append("| ");
        for (int i = 0; i < width - 1; i++) {
            builder.append(" _");
        }
        builder.append("|\n");
        return builder.toString();
    }

    private static String createLine(int width) {
        StringBuilder builder = new StringBuilder();
        builder.append("|_");
        for (int i = 0; i < width - 1; i++) {
            builder.append(" _");
        }
        builder.append("|\n");
        return builder.toString();
    }

    public static String createLabyrinth(int width, int height, int pathlength, boolean maxWay) throws Exception {
        int length = width * height;
        int[][] graph = new int[length][2]; //1==right 0==down 0==wall, 1==nowall
        //Black == 1 == has parent can't be picked as a child,
        //White == 0 == no parent == can be picked as a child
        int[] color = new int[length];
        int[] parent = new int[length];
        Arrays.fill(parent, -1);
        parent[0] = 0;
        color[0] = 1;
        LinkedList<Integer> queue = new LinkedList<>();
        while (true) {
            boolean allblack = true;
            for (int i = 0; i < color.length; i++) {
                if (color[i] == 0) {
                    allblack = false;
                } else {
                    queue.add(i);
                }
            }
            if (allblack) {
                break;
            }
            while (!queue.isEmpty()) {
                int u = queue.removeLast();
                if (color[u] == 1) {
                    int up = u - width;
                    int right = (u + 1);
                    int left = (u - 1);
                    int down = u + width;
                    int childcounter = 0;
                    //check UP neighbour
                    if (up >= 0) {
                        if (color[up] == 0) {
                            if ((int) (Math.random() * 2) == 1) {
                                color[up] = 1;
                                parent[up] = u;
                                queue.add(up);
                                graph[up][0] = 1;
                            }
                        }
                    }
                    //check RIGHT neighbour
                    if ((right % width) != 0 && right < length) {
                        if (color[right] == 0) {
                            if ((int) (Math.random() * 2) == 1) {
                                color[right] = 1;
                                parent[right] = u;
                                queue.add(right);
                                graph[u][1] = 1;
                            }
                        }
                    }
                    //check DOWN neighbour
                    if (down < length) {
                        if (color[down] == 0) {
                            if ((int) (Math.random() * 2) == 1) {
                                color[down] = 1;
                                parent[down] = u;
                                queue.add(down);
                                graph[u][0] = 1;
                            }
                        }
                    }
                    //check LEFT neighbour
                    if ((left % width) != width - 1 && left >= 0) {
                        if (color[left] == 0) {
                            if ((int) (Math.random() * 2) == 1) {
                                color[left] = 1;
                                parent[left] = u;
                                queue.add(left);
                                graph[left][1] = 1;
                            }
                        }
                    }
                }
            }
        }
        String filepath = "C:\\Users\\Benedikt\\Desktop\\UNI\\Informatik\\6.Semester\\Seminar Prog\\Prog\\seminarprogproblemc\\samples\\generatedSample.txt";
        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        writer.write(height + " " + width + "\n ");
        for (int i = 0; i < width; i++) {
            writer.append("_ ");
        }
        writer.append("\n");
        for (int i = 0; i < length; i++) {
            if (i % width == 0) {
                writer.append("|");
            }
            if (graph[i][0] == 1) {
                writer.append(" ");
            } else {
                writer.append("_");
            }
            if (graph[i][1] == 1) {
                writer.append(" ");
            } else {
                writer.append("|");
            }
            if (i % width == width - 1) {
                writer.append("\n");
            }
        }
        writer.append(pathlength + "");
        if (maxWay) {
            for (int i = 0; i < pathlength/2; i++) {
                writer.append("\n" + 1 + " " + 1);
                writer.append("\n" + (height) + " " + (width));
            }
            if (pathlength % 2 == 1){
                writer.append("\n" + 1 + " " + 1);
            }
        }
        else {
            for (int i = 0; i < pathlength; i++) {
                int y = (int) (Math.random() * height) + 1;
                int x = (int) (Math.random() * width) + 1;
                writer.append("\n" + y + " " + x);
            }
        }
        writer.close();
        return filepath;
    }
}
