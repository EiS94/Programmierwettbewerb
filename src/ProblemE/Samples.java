import java.util.Random;

public class Samples {

    public static String sample(int m, int n, int k) {
        Random rd = new Random();
        StringBuilder sb = new StringBuilder();

        sb.append(m).append(" ").append(n).append(" ").append(k).append("\n");

        for (int i = 0; i < k; i++) {
            sb.append(rd.nextInt(m) + 1).append(" ").append(rd.nextInt(n) + 1).append("\n");
        }
        return sb.toString();
    }

}
