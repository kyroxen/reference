package competitiveprogramming.algoexpert.arrays;

import java.util.Arrays;

/**
 * Given two arrays, find the pair from two with minimum distance between them.
 *
 * Input: firstArray = [-1, 5, 10, 20, 28, 3] secondArray: [26, 134, 135, 15, 17]
 * Output: [28, 26]
 *
 * @see
 * <a href = "https://www.algoexpert.io/questions/Smallest%20Difference">AE Link</a>
 */
public class P04SmallestDifference {

    /**
     * Time complexity : O(n.log(n) + m.log(m))
     *
     * @param array1
     * @param array2
     * @return
     */
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
