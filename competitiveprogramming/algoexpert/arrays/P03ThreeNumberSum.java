package competitiveprogramming.algoexpert.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find all sets of three integers in sorted order such that their sum equals the target sum.
 *
 * Input: array = [12, 3, 1, 2, -6, 5, -8, 6] ; targetSum = 0
 * Output: [[-8, 2, 6], [-8, 3, 5], [-6, 1, 5]]
 *
 * @see
 * <a href = "https://www.algoexpert.io/questions/Three%20Number%20Sum">AE Link</a>
 */
public class P03ThreeNumberSum {

    /**
     * O(n.n) solution
     *
     * @param array array of integers
     * @param targetSum target sum
     * @return list of sorted integers
     */
    public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
        Arrays.sort(array);
        ArrayList<Integer[]> ans = new ArrayList<>();
        for(int i = 0 ; i <= array.length - 3 ; i++){
            int l = i + 1;
            int h = array.length - 1;
            int twoSum = targetSum - array[i];
            while(l < h){
                int left = array[l];
                int right = array[h];
                int sum = left + right;
                if(sum < twoSum){
                    l++;
                }
                else if(sum > twoSum){
                    h--;
                }
                else{
                    ans.add(new Integer[]{array[i], left, right});
                    l++;
                    h--;
                }
            }
        }
        return ans;
    }
}
