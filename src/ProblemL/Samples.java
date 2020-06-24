package ProblemL;

import java.util.Random;

public class Samples {

    static String createSample(int width, int heigth, int mountaineers, int maxHeigth) {
        StringBuilder sb = new StringBuilder();
        Random rd = new Random();
        sb.append(heigth + " " + width + " " + mountaineers + "\n");
        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(rd.nextInt(maxHeigth) + 1);
                sb.append(" ");
            }
            sb.append("\n");
        }

        for (int i = 0; i < mountaineers; i++) {
            int x1 = rd.nextInt(width) + 1;
            int y1 = rd.nextInt(heigth) + 1;
            int x2 = rd.nextInt(width) + 1;
            while (x2 == x1) x2 = rd.nextInt(width) + 1;
            int y2 = rd.nextInt(heigth) + 1;
            while (y2 == y1) y2 = rd.nextInt(heigth) + 1;
            sb.append(x1 + " " + y1 + " " + x2 + " " + y2 + "\n");
        }
        return sb.toString();
    }

}
