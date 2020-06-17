package ProblemK;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class ProblemK {


    public static void main(String[] args) throws Exception{


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long input = Long.parseLong(br.readLine());

        //long input = Long.parseLong(args[0]);

        double sqrt = Math.sqrt(input);
        long Intsqrt = (long) sqrt;
        int result = 1;

        int counter = 0;
        while (input % 2 == 0){
            input = input >> 1;
            ++counter;
        }
        if (counter != 0) {
            //System.out.println("counter: " + counter + ", divider: 2");
            /*
            if (2 < sqrt) {
                ++counter;
            }*/
            ++counter;
            result *= counter;
        }

        long dividor = 3;
        while(dividor <= Intsqrt){
            counter = 0;
            while(input % dividor == 0){
                input /= dividor;
                ++counter;
            }
            if (counter != 0) {
                //System.out.println("counter: " + counter + ", divider: " + dividor);
                //if (dividor < sqrt) {
                    ++counter;
                //}
                result *= counter;
            }
            dividor += 2;
        }

        if (input > 1){
            result *= 2;
        }

        System.out.print(result);
        //return result;
    }
}
