import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Test {

    private static final String snakeSamplePath = "C:\\Users\\Benedikt\\Desktop\\UNI\\Informatik\\6.Semester\\Seminar Prog\\Prog\\seminarprogproblemc\\samples\\generatedSnake.txt";


    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\Benedikt\\Desktop\\UNI\\Informatik\\6.Semester\\Seminar Prog\\Prog\\seminarprogproblemc\\Test\\";
        String pathlengthTestEOE = filePath + "pathlengthTestEOE.csv";
        String pathlengthTestET = filePath + "pathlengthTestET.csv";

        //schon gemacht
        /*
        whTest(filePath);
        System.out.println("whTest finished");
        */

        //schon gemacht
        /*
        snakewhTest(filePath);
        System.out.println("snakeWHTest finished");*/

        pathlengthTest(filePath);
        System.out.println("pathlengthTest finished");

        //schon gemacht
        /*
        snakepathlengthTest(filePath);
        System.out.println("snakepathlengthTest finished");*/
    }

    private static void snakepathlengthTest(String filePath) throws Exception {
        String widthHeightTestEOE = filePath + "snakePathlengthTestEOE.csv";
        String widthHeightTestET = filePath + "snakePathlengthTestET.csv";
        int counter = 500;
        BufferedWriter writerWHEOE = new BufferedWriter(new FileWriter(widthHeightTestEOE, true));
        BufferedWriter writerWHET = new BufferedWriter(new FileWriter(widthHeightTestET, true));
        writerWHEOE.append("pathlength;time\n");
        writerWHET.append("pathlength;time\n");
        while (counter < 10000) {
            final String labyrinth = Samples.createSnake(1000, 1000, counter);
            long tempEOE = 0;
            long tempET = 0;
            Exception e = new ArrayIndexOutOfBoundsException();
            while(e != null) {
                try {
                    e = null;
                    tempEOE = toolongtimeEulerOhneEuler(labyrinth);
                    System.out.println();
                    tempET = toolongtimeEulerTest(labyrinth);
                    System.out.println();
                } catch (ArrayIndexOutOfBoundsException z) {
                    e = z;
                    File file = new File(snakeSamplePath);
                    file.delete();
                    Samples.createSnake(1000, 1000, counter);
                }
            }
            writerWHEOE.append(counter + ";" + tempEOE + "\n");
            writerWHET.append(counter + ";" + tempET + "\n");
            writerWHEOE.flush();
            writerWHET.flush();
            counter += 500;
        }
        writerWHEOE.close();
        writerWHET.close();
    }

    private static void pathlengthTest(String filePath) throws Exception {
        String widthHeightTestEOE = filePath + "pathlengthTestEOE.csv";
        String widthHeightTestET = filePath + "pathlengthTestET.csv";
        int counter = 500;
        BufferedWriter writerWHEOE = new BufferedWriter(new FileWriter(widthHeightTestEOE));
        BufferedWriter writerWHET = new BufferedWriter(new FileWriter(widthHeightTestET));
        writerWHEOE.append("pathlength;time\n");
        writerWHET.append("pathlength;time\n");
        while (counter < 1000) {
            System.out.println("counter: " + counter);
            long meanEOE = 0;
            long meanET = 0;
            for (int i = 0; i < 100; i++) {
                final String labyrinth = Samples.createLabyrinth(1000, 1000, counter, false);
                long tempEOE = timeEulerOhneEuler(labyrinth);
                System.out.println();
                long tempET = timeEulerTest(labyrinth);
                System.out.println();
                meanEOE += tempEOE;
                meanET += tempET;
            }
            meanEOE /= 100;
            meanET /= 100;
            writerWHEOE.append(counter + ";" + meanEOE + "\n");
            writerWHET.append(counter + ";" + meanET + "\n");
            writerWHEOE.flush();
            writerWHET.flush();
            counter += 500;
        }
        writerWHEOE.close();
        writerWHET.close();
    }

    private static void whTest(String filePath) throws Exception {
        String widthHeightTestEOE = filePath + "widthHeightTestEOE.csv";
        String widthHeightTestET = filePath + "widthHeightTestET.csv";
        int counter = 50;
        BufferedWriter writerWHEOE = new BufferedWriter(new FileWriter(widthHeightTestEOE, true));
        BufferedWriter writerWHET = new BufferedWriter(new FileWriter(widthHeightTestET, true));
        writerWHEOE.append("size;time\n");
        writerWHET.append("size;time\n");
        while (counter < 1000) {
            long meanEOE = 0;
            long meanET = 0;
            for (int i = 0; i < 100; i++) {
                final String labyrinth = Samples.createLabyrinth(counter, counter, 10000, false);
                long tempEOE = timeEulerOhneEuler(labyrinth);
                System.out.println();
                long tempET = timeEulerTest(labyrinth);
                System.out.println();
                meanEOE += tempEOE;
                meanET += tempET;
            }
            meanEOE /= 100;
            meanET /= 100;
            writerWHEOE.append(counter + ";" + meanEOE + "\n");
            writerWHET.append(counter + ";" + meanET + "\n");
            writerWHEOE.flush();
            writerWHET.flush();
            counter += 50;
        }
        writerWHEOE.close();
        writerWHET.close();
    }

    private static void snakewhTest(String filePath) throws Exception {
        String widthHeightTestEOE = filePath + "snakeWidthHeightTestEOE.csv";
        String widthHeightTestET = filePath + "snakeWidthHeightTestET.csv";
        int counter = 50;
        BufferedWriter writerWHEOE = new BufferedWriter(new FileWriter(widthHeightTestEOE, true));
        BufferedWriter writerWHET = new BufferedWriter(new FileWriter(widthHeightTestET, true));
        writerWHEOE.append("size;time\n");
        writerWHET.append("size;time\n");
        while (counter < 1000) {
            final String labyrinth = Samples.createSnake(counter, counter, 10000);
            long tempEOE = timeEulerOhneEuler(labyrinth);
            System.out.println();
            long tempET = timeEulerTest(labyrinth);
            System.out.println();
            writerWHEOE.append(counter + ";" + tempEOE + "\n");
            writerWHET.append(counter + ";" + tempET + "\n");
            writerWHEOE.flush();
            writerWHET.flush();
            counter += 50;
        }
        writerWHEOE.close();
        writerWHET.close();
    }

    private static long timeEulerOhneEuler(String labyrinth) {
        long start;
        long end;
        long total;
        Thread eulerOhneEulerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EulerOhneEuler.main(new String[]{labyrinth});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        start = System.nanoTime();
        eulerOhneEulerThread.start();
        while ((System.nanoTime() - start <= 20000000000L) && eulerOhneEulerThread.isAlive()) {

        }
        end = System.nanoTime();
        eulerOhneEulerThread.interrupt();
        total = end - start;
        return total;
    }

    private static long timeEulerTest(String labyrinth) {
        long start;
        long end;
        long total;
        Thread eulerTestThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EulerTest.main(new String[]{labyrinth});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        start = System.nanoTime();
        eulerTestThread.start();
        while ((System.nanoTime() - start <= 20000000000L) && eulerTestThread.isAlive()) {

        }
        end = System.nanoTime();
        eulerTestThread.interrupt();
        total = end - start;
        return total;
    }


    private static long toolongtimeEulerTest(String labyrinth) throws Exception{
        long start;
        long end;
        long total;
        start = System.nanoTime();
        EulerTest.main(new String[]{labyrinth});
        end = System.nanoTime();
        total = end - start;
        return total;
    }

    private static long toolongtimeEulerOhneEuler(String labyrinth) throws Exception{
        long start;
        long end;
        long total;
        start = System.nanoTime();
        EulerOhneEuler.main(new String[]{labyrinth});
        end = System.nanoTime();
        total = end - start;
        return total;
    }
}
