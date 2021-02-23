package competitiveprogramming.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * [M]
 * Given a 2-dimensional array, return an ordered 1-D array
 * wherein the elements are picked from the input array in a
 * clockwise diminishing traversal to the center,
 * starting from the top-left corner.
 * <p>
 * input :
 * [
 * [1, 2, 3, 4],
 * [12, 13, 14, 5],
 * [11, 16, 15, 6],
 * [10, 9, 8, 7]
 * ]
 * <p>
 * output : [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]
 *
 * @See <a href = "https://www.algoexpert.io/questions/Spiral%20Traverse">AlgoExpert</a>
 */
public class P07SpiralTraverse {

    /**
     * @param array input array
     * @return List if traversed elements
     */
    public static List<Integer> spiralTraverse(int[][] array) {
        int rowStart = 0;
        int rowEnd = array.length - 1;
        int columnStart = 0;
        int columnEnd = array[0].length - 1;

        List<Integer> ans = new ArrayList<>();
        while (rowStart < rowEnd && columnStart < columnEnd) {
            for (int i = columnStart; i <= columnEnd; i++) {
                ans.add(array[rowStart][i]);
            }
            for (int i = rowStart + 1; i <= rowEnd; i++) {
                ans.add(array[i][columnEnd]);
            }
            for (int i = columnEnd - 1; i >= columnStart; i--) {
                ans.add(array[rowEnd][i]);
            }
            for (int i = rowEnd - 1; i > rowStart; i--) {
                ans.add(array[i][columnStart]);
            }
            rowStart++;
            rowEnd--;
            columnStart++;
            columnEnd--;
        }

        // Handling the edge cases:
        if (rowStart == rowEnd && columnStart < columnEnd) {
            int i = columnStart;
            while (i <= columnEnd) {
                ans.add(array[rowStart][i++]);
            }
        }
        if (columnStart == columnEnd && rowStart < rowEnd) {
            int i = rowStart;
            while (i <= rowEnd) {
                ans.add(array[i++][columnStart]);
            }
        }
        if (rowStart == rowEnd && columnStart == columnEnd) {
            ans.add(array[rowStart][columnStart]);
        }
        return ans;
    }
}

