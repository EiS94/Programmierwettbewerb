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
}
