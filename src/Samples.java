import java.util.Random;

public class Samples {

    public static String sample = "2 6\n" +
            " _ _ _ _ _ _ \n" +
            "|  _ _ _ _ _|\n" +
            "|_ _ _ _ _ _|\n" +
            "2\n" +
            "1 1\n" +
            "2 1\n";

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
            while (x == lastX && y == lastY ) {
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
}
