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
    
    public static String createSnake(int width, int height, int pathlength){
        StringBuilder builder = new StringBuilder(height + " " + width + "\n");
        int maxX = height % 2 == 0 ? 1 : width;
        //first line
        for (int i = 0; i < width; i++) {
            builder.append(" _");
        }
        builder.append(" \n");
        for (int i = 0; i < (height -1) / 2; i++) {
            builder.append(createLineRight(width));
            builder.append(createLineLeft(width));
        }
        if (height % 2 == 0){
            builder.append(createLineRight(width));
        }
        builder.append(createLine(width));
        builder.append(pathlength).append("\n");
        for (int i = 0; i < pathlength / 2; i++) {
            builder.append("1 1\n").append(height).append(" ").append(maxX).append("\n");
        }
        if (pathlength % 2 != 0){
            builder.append("1 1\n");
        }
        System.out.println(builder.toString());
        return builder.toString();
    }


    private static String createLineRight(int width){
        StringBuilder builder = new StringBuilder();
        builder.append("|_");
        for (int i = 0; i < width - 2; i++) {
            builder.append(" _");
        }
        builder.append("  |\n");
        return builder.toString();
    }
    private static String createLineLeft(int width){
        StringBuilder builder = new StringBuilder();
        builder.append("| ");
        for (int i = 0; i < width - 1; i++) {
            builder.append(" _");
        }
        builder.append("|\n");
        return builder.toString();
    }
    private static String createLine(int width){
        StringBuilder builder = new StringBuilder();
        builder.append("|_");
        for (int i = 0; i < width - 1; i++) {
            builder.append(" _");
        }
        builder.append("|\n");
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(createSnake(4,4,10));
    }
}
