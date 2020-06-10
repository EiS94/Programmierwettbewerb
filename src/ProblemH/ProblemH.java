package ProblemH;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class ProblemH {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /*String path = "src\\ProblemH\\Samples\\";
        path += "Sample5 934.txt";
        BufferedReader br = new BufferedReader(new FileReader(path));
*/
        //Einlesen
        int numberKeys = Integer.parseInt(br.readLine());
        int[] soll = new int[numberKeys];
        String[] keysString = br.readLine().split(" ");
        for (int i = 0; i < numberKeys; i++) {
            soll[i] = Integer.parseInt(keysString[i]) - 1;
        }

        int[] ist = new int[numberKeys];
        keysString = br.readLine().split(" ");
        for (int i = 0; i < numberKeys; i++) {
            ist[i] = Integer.parseInt(keysString[i]) - 1;
        }

        //Translation
        int[] map = new int[numberKeys];
        for (int i = 0; i < numberKeys; i++) {
            map[soll[i]] = i;
        }
        int[] keys = new int[numberKeys];
        for (int i = 0; i < numberKeys; i++) {
            keys[i] = map[ist[i]];
        }

        //keys final array input
        int sub = lengthOfLongestSubsequence(keys);
        System.out.print(numberKeys - sub);
    }

    public static int lengthOfLongestSubsequence(int[] array){
        int up = lengthOfLongestUpSub(array);
        int down = lengthOfLongestDownSub(array);
        return Math.max(up, down);
    }

    public static int lengthOfLongestDownSub(int[] array){
        int max = 0;
        int temp;
        for (int i = 0; i < array.length; i++) {
            temp = lengthOfDownSub(array, i);
            if (temp > max){
                max = temp;
            }
        }
        return max;
    }

    public static int lengthOfDownSub(int[] array, int start){
        //length of longest Subsequence from start
        ArrayList<Integer> helpList = new ArrayList<>();
        helpList.add(array[start]);
        int actual;
        for (int i = start + 1; i < array.length + start + 1; i++) {
            actual = i % array.length;
            //next int larger then last in helpList?
            if (array[actual] < helpList.get(helpList.size() - 1)){
                helpList.add(array[actual]);
            }
            //next int smaller then last in helpList
            else if (array[actual] < helpList.get(0)){
                //go through helpList back to front, until next int in helpList smaller then array int
                for (int j = helpList.size() - 2; j >= 0; j--) {
                    if (array[actual] < helpList.get(j)){
                        helpList.set(j + 1, array[actual]);
                        break;
                    }
                }
            }
        }
        return helpList.size();
    }

    public static int lengthOfLongestUpSub(int[] array){
        int max = 0;
        int temp;
        for (int i = 0; i < array.length; i++) {
            temp = lengthOfUpSub(array, i);
            if (temp > max){
                max = temp;
            }
        }
        return max;
    }

    public static int lengthOfUpSub(int[] array, int start){
        //length of longest Subsequence from start
        ArrayList<Integer> helpList = new ArrayList<>();
        helpList.add(array[start]);
        int actual;
        for (int i = start + 1; i < array.length + start + 1; i++) {
            actual = i % array.length;

            //next int larger then last in helpList?
            if (array[actual] > helpList.get(helpList.size() - 1)){
                helpList.add(array[actual]);
            }
            //next int smaller then last in helpList
            else if (array[actual] > helpList.get(0)){
                //go through helpList back to front, until next int in helpList smaller then array int
                for (int j = helpList.size() - 2; j >= 0; j--) {
                    if (array[actual] > helpList.get(j)){
                        helpList.set(j + 1, array[actual]);
                        break;
                    }
                }
            }
        }
        return helpList.size();
    }
}
