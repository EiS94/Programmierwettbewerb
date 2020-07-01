package ProblemE;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) throws Exception{
        String path = "src\\ProblemE\\Samples\\";
        String file = "452.txt";

        String sample = Files.readString(Paths.get(path + file));
        sample = Samples.sample(10,10,30);
        Loesung2.main(new String[]{sample});
    }
}
