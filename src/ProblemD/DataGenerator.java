import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class DataGenerator {
    public static boolean writeDataFile() throws Exception{
        String inputFolder= Paths.get("input","generated").toString();
        //String outputFolder= Paths.get("output","generated").toString();

        BufferedWriter bw;
        int roadsPerCity = 19;
        int m;
        int[][] graph;
        int count = 0;
        Random random = new Random();
        for (int n = 50; n <= 250; n+= 25) {
            String inputFileName = Paths.get(inputFolder, "input"+count+".txt").toString();
            bw = new BufferedWriter(new FileWriter(inputFileName));
            bw.write(""+n+"\n");
            m = (n/2) * roadsPerCity;
            bw.write(""+m+"\n");
            bw.flush();

            for (int city = 1; city < n/2 + 1; city++) {
                for (int j = 0; j < roadsPerCity; j++) {
                    int connectingCity = n/2 + random.nextInt(n/2);
                    bw.write(""+city+" "+connectingCity+"\n");
                }
            }
            bw.flush();
            bw.close();
            count++;
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        writeDataFile();
    }
}
