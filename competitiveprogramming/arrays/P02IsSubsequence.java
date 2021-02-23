package competitiveprogramming.arrays;

import java.util.List;

/**
 * [E]
 * Determine if the given sequence is a sub-sequence of a larger entity.
 *
 * Definition: A sub sequence means it has the characters in order from the larger sequence
 * but it is not necessary to have all the characters. Some might be missing in between
 * Example: For the string a,b,c,d,e,f : a,c,f is a valid sub-sequence
 *
 * Input: array = [5, 1, 22, 25, 6, -1, 8, 10]
 *        sequence = [1, 6, -1, 10]
 * Output: true
 *
 * @see <a href = "https://www.algoexpert.io/questions/Validate%20Subsequence">AE Link</a>
 * <br>
 * @see <a href = "https://leetcode.com/problems/is-subsequence/">LeetCode Link</a>
 */
public class P02IsSubsequence {

    /**
     * This function is for leetcode.
     *
     * @param smallerString input sequence
     * @param biggerString possible sub-sequence
     * @return is valid?
     */
    public boolean isSubsequence(String smallerString, String biggerString) {
        int prevIdx = -1;
        for(int i = 0 ; i < smallerString.length(); i++){
            char c = smallerString.charAt(i);
            int pos = getIndexInBiggerString(biggerString, prevIdx, c);
            if(pos == -1 || pos < prevIdx)
                return false;
            else
                prevIdx = pos;
        }
        return true;
    }

    private int getIndexInBiggerString(String possibleSubSequence, int prevIdx, char c){
        for(int i = prevIdx + 1 ; i < possibleSubSequence.length() ; i++){
            if(possibleSubSequence.charAt(i) == c)
                return i;
        }
        return -1;
    }

    /**
     * This code is for AlgoExpert
     *
     * @param array
     * @param sequence
     * @return
     */
    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        int prevPos = -1;
        boolean ans = false;
        for(int i = 0 ; i < sequence.size(); i++){
            Integer num = sequence.get(i);
            int pos = getIndexInArray(array, num, prevPos);
            if(pos == -1 || pos < prevPos)
                return false;
            else
                prevPos = pos;
        }
        return true;
    }

    private static int getIndexInArray(List<Integer> array, int num, int prevPos){
        for(int i = prevPos + 1 ; i < array.size() ; i++){
            if(array.get(i) == num)
                return i;
        }
        return -1;
    }

}
