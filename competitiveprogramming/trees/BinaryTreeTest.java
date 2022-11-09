package competitiveprogramming.trees;

import competitiveprogramming.trees.node.BTreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BinaryTreeTest {
  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  /**
   * Sample test cases:
   * <p>
   *   1 2 4 x x 5 x x 3 x x
   * </p>
   * <p>
   *   5 4 3 x 8 x 1 x x x 6 x x
   * </p>
   * <p>
   *   1 2 4 10 x x x 5 x x 3 6 x x 7 8 x x 9 x x
   * </p>
   *
   * @param args
   */
  public static void main(String[] args) {
    List<String> arr = getUsingScanner();
    BTreeNode<Integer> root = BinaryTreeHelper.parseTree(arr);
    BinaryTreeHelper.printHorizontal(root);
  }

  private static List<String> getUsingScanner() {
    Scanner scanner = new Scanner(System.in);
    List<String> arr = new ArrayList<>(splitWords(scanner.nextLine()));
    scanner.close();
    return arr;
  }
}
