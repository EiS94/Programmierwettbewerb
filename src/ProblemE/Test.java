package ProblemE;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) throws Exception{
        String path = "src\\ProblemE\\Samples\\";
        String file = "1498.txt";
        String sample = Files.readString(Paths.get(path + file));
        Loesung2.main(new String[]{sample});
    }
}
