package ProblemL;

public class Test {

    public static void main(String[] args) throws Exception{
        String sample = Samples.createSample(200,200,100000,1000000);

        long p1 = ToursInNodes.main(new String[]{sample});
        long p2 = ProblemL.main(new String[]{sample});
        double divide = 1000000000.0;

        System.out.println("p1: " + p1/divide);
        System.out.println("p2: " + p2/divide);
    }
}
