package ProblemK;

import java.util.LinkedList;

public class Ansatz2 {


    public static int main(String[] args) throws Exception {

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //int input = Integer.parseInt(br.readLine());

        long input = Long.parseLong(args[0]);;

        double sqrt = Math.sqrt(input);
        long Intsqrt = (long) sqrt;
        int result = 2;

        long dividor = 2;
        LinkedList<Long> list = new LinkedList<>();
        while (dividor <= Intsqrt) {
            if (input % dividor == 0) {
                ++result;
                list.add(dividor);
                if (dividor < sqrt){
                    ++result;
                }
            }
            ++dividor;
        }

        //System.out.print(result);

        return result;
    }
}
