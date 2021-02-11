package competitiveprogramming.algoexpert.arrays;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Question: Given an input array, and a target sum, find two numbers in the array such that
 * they add up to the target sum.
 *
 * @see
 * <a href="https://www.algoexpert.io/questions/Two%20Number%20Sum">AE link</a>
 * <br>
 * <a href="https://leetcode.com/problems/two-sum/">Leetcode link</a>
 */
public class P01TwoSum {
    /**
     * O(n) : O(1) solution
     *
     * @param array input array
     * @param targetSum target sum
     * @return pair of integers that add up to the target sum
     */
    public static int[] usingHashSet(int[] array, int targetSum) {

        HashSet<Integer> set = new HashSet<>();
        for(int num : array){
            set.add(num);
            int diff = targetSum - num;
            if(set.contains(diff) && num != diff){
                return new int[]{diff, num};
            }
        }
        return new int[0];
    }

    /**
     * O(n.log(n)) : O(1) solution
     *
     * @param array input array
     * @param target target sum
     * @return pair of integers that add up to the target sum
     */
    public static int[] usingTwoPointers(int[] array, int target) {
        // Write your code here.
        Arrays.sort(array);
        int l = 0;
        int h = array.length - 1;
        while(l < h){
            int left = array[l];
            int right = array[h];
            int sum = array[l] + array[h];
            if(sum > target){
                h--;
            }
            else if(sum < target){
                l++;
            }
            else
                return new int[]{left, right};
        }
        return new int[0];
    }
}
