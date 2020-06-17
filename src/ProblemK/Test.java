package ProblemK;

public class Test {

    public static void main(String[] args) throws Exception {
        test(100000007700000049L);
    }

    public static void test(long input) throws Exception {
        int k = ProblemK.main(new String[]{String.valueOf(input)});
        int ansatz2 = Ansatz2.main(new String[]{String.valueOf(input)});
        if (k == ansatz2){
            System.out.println("Correct! Input: " + input + ", Output: " + k);
        }
        else{
            System.out.println("False! Input: " + input + ", Output: " + k + ", expected: " + ansatz2);
        }
    }
}
