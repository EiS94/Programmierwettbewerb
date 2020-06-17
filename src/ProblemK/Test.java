package ProblemK;

import javax.xml.xpath.XPath;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Test {

    public static void main(String[] args) throws Exception {

        long start = 1;
        long end = 100;//(long) Math.pow(10,8);
        String path = "src\\ProblemK\\TestResults";
        writeWrongInFile(start, end, path);
        //test(9);
    }

    public static void writeWrongInFile(long start, long end, String path) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (long i = start; i <= end; i++) {
            if (!test(i)){
                writer.write(i + "\n");
                writer.flush();
            }
        }
    }

    public static boolean test(long input) throws Exception {
        int k = ProblemK.main(new String[]{String.valueOf(input)});
        int ansatz2 = Ansatz2.main(new String[]{String.valueOf(input)});
        if (k == ansatz2){
            System.out.println("Correct! Input: " + input + ", Output: " + k);
            return true;
        }
        else{
            System.out.println("False! Input: " + input + ", Output: " + k + ", expected: " + ansatz2);
            return false;
        }
    }
}
