package ProblemE;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) throws Exception{
        String file = "/home/eike/Dokumente/Uni/6. Semester/Seminar/Git/seminarprogproblemc/src/ProblemE/Samples/452.txt";

        String sample = Files.readString(Paths.get(file));
        //sample = Samples.sample(10,10,30);
        arrayAnsatz.main(new String[]{sample});
    }
}
