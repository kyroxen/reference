package competitiveprogramming.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BinaryTreeTest {
  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  // 1 2 4 x x 5 x x 3 x x
  // 5 4 3 x 8 x 1 x x x 6 x x
  public static void main(String[] args) {
    List<String> arr = getUsingScanner();
    Node<Integer> root = BinaryTree.parseTree(arr);
    // BTreePrinter.printVertical(root);
    BTreePrinter.printHorizontal(root);

    BinaryTree.print(root);
  }

  private static List<String> getUsingScanner() {
    Scanner scanner = new Scanner(System.in);
    List<String> arr = new ArrayList<>(splitWords(scanner.nextLine()));
    scanner.close();
    return arr;
  }
}
