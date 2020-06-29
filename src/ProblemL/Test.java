package ProblemL;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) throws Exception{
        String sample = Samples.createSample(500,500,100000,1000000);
        sample = Files.readString(Paths.get("src\\ProblemL\\Samples\\sample3.txt"));

        long p1Total = 0;
        long p2Total = 0;
        int end = 1;
        for (int i = 0; i < end; i++) {
            //p1Total += ToursInNodes.main(new String[]{sample});
            //p2Total += IDHashSet.main(new String[]{sample});
        }
        p1Total /= end;
        p2Total /= end;
        double divide = 1000000000.0;

        System.out.println("p1: " + p1Total/divide);
        System.out.println("p2: " + p2Total/divide);
    }
}
