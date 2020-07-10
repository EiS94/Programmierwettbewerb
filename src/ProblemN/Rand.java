package ProblemN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Rand {

    public static void main(String[] args) throws IOException {
        //String input = Files.readString(Paths.get("/home/eike/Dokumente/Uni/6. Semester/Seminar/Git/seminarprogproblemc/src/ProblemN/Samples/min_multikante_lösung_ja_ca_5_sekunden.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Random rd = new Random();
        int i = rd.nextInt(2);
        if (i == 0) System.out.print("yes");
        else if (i == 1) System.out.print("no");
    }

}
