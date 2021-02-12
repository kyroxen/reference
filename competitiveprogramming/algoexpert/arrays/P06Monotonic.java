package competitiveprogramming.algoexpert.arrays;

/**
 * Given an array of integers, determine if the array is monotonic
 *
 * Definition:
 *  Monotonically increasing:
 *          ______
 *         /
 *        /
 *    ----
 *   /
 *  /
 *
 * Monotonically decreasing:
 * \
 *  \
 *    ----
 *        \
 *        \
 *        \
 *
 * Input: [-1, -5, -10, -1100, -1100, -1101, -1102, -9001]
 * Output: true
 *
 * Input: []
 * Output: true
 *
 */
public class P06Monotonic {
    /**
     * O(n)
     *
     * @param array input
     * @return true if the function is monotonic
     */
    private static boolean isMonotonicBetterSolution(int[] array){
        boolean isMonotonicallyIncreasing = true;
        boolean isMonotonicallyDecreasing = true;
        for(int i = 0 ; i < array.length - 1 ; i++){
            if(array[i] > array[i+1]){
                isMonotonicallyIncreasing = false;
            }
            if(array[i] < array[i+1]){
                isMonotonicallyDecreasing = false;
            }
        }
        return isMonotonicallyDecreasing || isMonotonicallyIncreasing;
    }

    /**
     * O(2.n)
     *
     * @param array input
     * @return true if the function is monotonic
     */
    public static boolean isMonotonicNotThatPrettyButSimplerSolution(int[] array) {
        // Write your code here.
        if(array == null || array.length == 0)
            return true;
        return isMonotonicallyIncreasing(array) || isMonotonicallyDecreasing(array);
    }

    private static boolean isMonotonicallyIncreasing(int[] array){
        for(int i = 0 ; i < array.length - 1 ; i++){
            int currentNumber = array[i];
            int nextNumber = array[i+1];
            if(currentNumber <= nextNumber){
                continue;
            }
            else
                return false;
        }
        return true;
    }

    private static boolean isMonotonicallyDecreasing(int[] array){
        for(int i = 0 ; i < array.length - 1 ; i++){
            int currentNumber = array[i];
            int nextNumber = array[i+1];
            if(currentNumber >= nextNumber){
                continue;
            }
            else
                return false;
        }
        return true;
    }

}
