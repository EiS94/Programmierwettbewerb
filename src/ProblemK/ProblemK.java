package ProblemK;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class ProblemK {


    public static void main(String[] args) throws Exception{

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //int input = Integer.parseInt(br.readLine());

        long input = 100;

        double sqrt = Math.sqrt(input);
        long Intsqrt = (long) sqrt;
        int result = 1;

        int counter = 0;
        while (input % 2 == 0){
            input = input >> 1;
            ++counter;
        }
        if (counter != 0) {
            System.out.println("counter: " + counter + ", divider: 2");
            if (2 < sqrt) {
                ++counter;
            }
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
                System.out.println("counter: " + counter + ", divider: " + dividor);
                if (dividor < sqrt) {
                    ++counter;
                }
                result *= counter;
            }
            dividor += 2;
        }

        //Input ist Primzahl
        if (result == 1){
            ++result;
        }

        /*
        for (Integer count:list) {
            result *= (1 + count);
        }
        */

        /*
        int[] count = new int[sqrt];
        int index = 0;

        while (input % 2 == 0){
            input = input >> 1;
            ++count[index];
        }
        ++index;

        int dividor = 3;
        while(dividor <= sqrt){
          while(input % dividor == 0){
              input /= dividor;
              ++count[index];
          }
          ++index;
          dividor += 2;
        }

        for (int i = 0; i < sqrt; i++) {
            if (count[i] == 0){
                break;
            }
            result *= (1 + count[i]);
        }

        */
        System.out.print(result);
    }
}
