package competitiveProgramming.algoExpert.arrays;

import java.util.Arrays;

public class P04SmallestDifference {
    public static int[] smallestDifference(int[] array1, int[] array2) {
        Arrays.sort(array1);
        Arrays.sort(array2);

        int firstArrayPointer = 0;
        int secondArrayPointer = 0;
        int globalDiff = Integer.MAX_VALUE;

        int[] answer = new int[2];

        while(firstArrayPointer < array1.length && secondArrayPointer < array2.length){
            int firstNumber = array1[firstArrayPointer];
            int secondNumber = array2[secondArrayPointer];
            int localDiff = Math.abs(firstNumber - secondNumber);
            if(localDiff <= globalDiff){
                answer[0] = firstNumber;
                answer[1] = secondNumber;
                globalDiff = localDiff;
            }
            if(firstNumber < secondNumber){
                firstArrayPointer++;
            }
            else{
                secondArrayPointer++;
            }
        }
        return answer;
    }
}
