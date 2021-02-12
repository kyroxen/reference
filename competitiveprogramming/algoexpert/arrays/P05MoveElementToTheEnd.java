package competitiveprogramming.algoexpert.arrays;

import java.util.List;

/**
 * Given an array of integers and an input integer, move all instances of the integer to the end of the array in-place.
 *
 * Input: array = [2, 1, 2, 2, 2, 3, 4, 2] toMove = 2
 * Output: [1,3,4,2,2,2,2,2]
 *
 * @see
 * <a href = "https://www.algoexpert.io/questions/Move%20Element%20To%20End">AE Link</a>
 *
 */
public class P05MoveElementToTheEnd {

    /**
     * O(n)
     *
     * @param array
     * @param toMove
     * @return
     */
    private static List<Integer> moveToTheEnd(List<Integer> array, Integer toMove){
        int low = 0;
        int high = array.size() - 1;
        while(low < high){
            if(array.get(low).equals(toMove)){
                Integer swapMem = array.get(high);
                array.set(high, array.get(low));
                array.set(low, swapMem);
                high--;
            }else {
                low++;
            }
        }
        return array;
    }
}
