import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;

public class ProblemYTest {

    public static final String sample = "3\n" +
            "2\n" +
            "10 2\n" +
            "6 1\n" +
            "6 1";

    public static final String sample1 = "3\n" +
            "3\n" +
            "15 3\n" +
            "12 2\n" +
            "12 2";

    public static final String sample2 = "1\n" +
            "3\n" +
            "15 2\n";

    public static final String samplePart =
            "40 20\n"+
            "2 1\n"+
            "2 1\n"+
            "10 30\n"+
            "4 6\n"+
            "15 24\n"+
            "60 50\n"+
            "12 7\n"+
            "21 42\n"+
            "39 45\n"+
            "9 4\n";

    public static String createSample(int elemente, int weight){
        String result = (elemente*10) + "\n" + weight + "\n";
        for (int i = 0; i < elemente; i++) {
            result += samplePart;
        }
        return result;
    }

    public static int[][] dynamicThing;
    public static int[] weights;
    public static int[] values;

    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime();
        BufferedReader reader = new BufferedReader(new StringReader(createSample(500, 30000)));
        int numberOfItems = Integer.parseInt(reader.readLine());
        int weightLimit = Integer.parseInt(reader.readLine());
        dynamicThing = new int[numberOfItems][weightLimit + 1];

        for (int i = 0; i < numberOfItems; i++) {
            Arrays.fill(dynamicThing[i], -1);
        }
/*
        for (int i = 0; i < numberOfItems; i++) {
            dynamicThing[i][0] = 0;
        }*/

        weights = new int[numberOfItems];
        values = new int[numberOfItems];
        for (int i = 0; i < numberOfItems; i++) {
            String line = reader.readLine();
            String[] splitLine = line.split(" ");
            values[i] = Integer.parseInt(splitLine[0]);
            weights[i] = Integer.parseInt(splitLine[1]);
        }
        System.out.print(getMaxValue(numberOfItems - 1, weightLimit));
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("\n" + totalTime/1000000000.0 + "s");
    }

    public static int getMaxValue(int numberOfItems, int weightLimit) {
        int max = 0;
        for (int i = 0; i < weightLimit + 1; i++) {
            int temp = f(numberOfItems, i);
            if (max < temp) {
                max = temp;
            }
        }
        return max;
    }

    public static int f(int i, int w) {
        //initialized?
        if (dynamicThing[i][w] != -1) {
            return dynamicThing[i][w];
        }
        int result = 0;
        //Basic
        if (i == 0) {
            if (w == weights[i]) {
                result = values[i];
            }
        }
        //Advanced
        else {
            result = f(i - 1, w);
            int tempweight = w - weights[i];
            if (tempweight >= 0) {
                int other = f(i - 1, w - weights[i]) + values[i];
                if (other > result) {
                    result = other;
                }
            }
        }
        //memorize
        dynamicThing[i][w] = result;
        return result;
    }


}
