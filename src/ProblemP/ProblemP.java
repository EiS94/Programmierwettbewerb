package ProblemP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class ProblemP {

    public static void main(String[] args) throws IOException {

        //String input = Files.readString(Path.of("/home/eike/Dokumente/Uni/6. Semester/Seminar/Git/seminarprogproblemc/src/ProblemP/Samples/sample.txt"));

        //BufferedReader br = new BufferedReader(new StringReader(input));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String[] strings = br.readLine().split(" ");

        int k = Integer.parseInt(strings[0]);
        int n = Integer.parseInt(strings[1]);
        int m = Integer.parseInt(strings[2]);

        if (m >= 2 * n) {
            System.out.print(k);
            return;
        }

        Integer[] houses = new Integer[k];
        int[] greatBuys = new int[n];

        strings = br.readLine().split(" ");
        for (int i = 0; i < k; i++) {
            houses[i] = Integer.parseInt(strings[i]);
        }

        strings = br.readLine().split(" ");
        br.close();
        for (int i = 0; i < n; i++) {
            greatBuys[i] = Integer.parseInt(strings[i]);
        }

        Arrays.sort(houses);
        Arrays.sort(greatBuys);


        LinkedList<Integer> houseList = new LinkedList<>(Arrays.asList(houses));
        Intervall[] intervalls = new Intervall[n + 1];

        for (int i = 0; i < intervalls.length; i++) {
            intervalls[i] = new Intervall(i);
        }

        int counter = 0;
        for (Integer greatBuy : greatBuys) {
            while (!houseList.isEmpty() && houseList.getFirst() < greatBuy) {
                intervalls[counter].houses.add(houseList.removeFirst());
            }
            intervalls[counter].greatBuys.add(greatBuy);
            counter++;
            if (counter < intervalls.length) intervalls[counter].greatBuys.add(greatBuy);
        }

        if (!houseList.isEmpty()) intervalls[intervalls.length - 1].houses.addAll(houseList);

        /*LinkedList<Intervall> intervalls = new LinkedList<>();

        //determine all intervalls
        int counter = 0;
        int houseCounter = 0;
        Intervall intervall = new Intervall(counter);
        while (houseCounter < k) {
            if (counter < greatBuys.length) {
                while (houseCounter < k && houses[houseCounter] < greatBuys[counter]) {
                    intervall.houses.add(houses[houseCounter++]);
                }
            } else {
                while (houseCounter < k) {
                    intervall.houses.add(houses[houseCounter++]);
                }
            }
            counter++;
            if (counter - 1 < greatBuys.length) intervall.greatBuys.add(greatBuys[counter - 1]);
            intervalls.add(intervall);
            intervall = new Intervall(counter);
            if (counter - 1 < greatBuys.length) intervall.greatBuys.add(greatBuys[counter - 1]);
        }*/

        intervalls[0].openIntervall = true;
        intervalls[intervalls.length - 1].openIntervall = true;

        //determine all start and endpoints
        int nearestGreatBuy;
        for (Intervall intervall1 : intervalls) {
            if (!intervall1.openIntervall) {
                for (Integer house : intervall1.houses) {
                    nearestGreatBuy = intervall1.greatBuys.get(0);
                    if (intervall1.greatBuys.size() > 1 && Math.abs(house - nearestGreatBuy) > Math.abs(house - intervall1.greatBuys.get(1))) {
                        nearestGreatBuy = intervall1.greatBuys.get(1);
                    }
                    intervall1.startPoints.add((house - Math.abs(nearestGreatBuy - house)));
                    intervall1.endPoints.add((house + Math.abs(nearestGreatBuy - house)));
                }
                Collections.sort(intervall1.startPoints);
                Collections.sort(intervall1.endPoints);
            }
        }

        int[] maxPerIntervallB1 = new int[intervalls.length];
        int[] maxPerIntervallB2 = new int[intervalls.length];
        int temp;
        int max;
        counter = 0;
        int startIndex;
        int endIndex;
        //count maxHouses
        for (Intervall intervall1 : intervalls) {
            if (intervall1.openIntervall) {
                maxPerIntervallB1[counter] = intervall1.houses.size();
                maxPerIntervallB2[counter] = 0;
                counter++;
            } else {
                temp = 0;
                startIndex = 0;
                endIndex = 0;
                max = 0;
                while (startIndex < intervall1.startPoints.size()) {
                    if (intervall1.endPoints.get(endIndex) < intervall1.startPoints.get(startIndex)) {
                        endIndex++;
                        temp--;
                    } else if (intervall1.endPoints.get(endIndex) > intervall1.startPoints.get(startIndex)) {
                        startIndex++;
                        temp++;
                        if (temp > max) max = temp;
                    } else {
                        startIndex++;
                        endIndex++;
                    }
                }
                maxPerIntervallB1[counter] = max;
                maxPerIntervallB2[counter] = intervall1.houses.size() - max;
                counter++;
            }
        }

        int[] total = new int[intervalls.length * 2];
        for (int i = 0; i < maxPerIntervallB1.length; i++) {
            total[i] = maxPerIntervallB1[i];
        }

        for (int i = maxPerIntervallB1.length; i < total.length; i++) {
            total[i] = maxPerIntervallB2[i - maxPerIntervallB1.length];
        }

        Arrays.sort(total);

        int sum = 0;

        if (m > total.length) m = total.length;

        for (int i = 1; i <= m; i++) {
            sum += total[total.length - i];
        }

        System.out.print(sum);


    }

    private static class Intervall {

        int number;
        ArrayList<Integer> startPoints = new ArrayList<>();
        ArrayList<Integer> endPoints = new ArrayList<>();
        LinkedList<Integer> houses = new LinkedList<>();
        LinkedList<Integer> greatBuys = new LinkedList<>();
        boolean openIntervall = false;

        public Intervall(int number) {
            this.number = number;
        }
    }

}
