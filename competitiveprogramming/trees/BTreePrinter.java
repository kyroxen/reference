package competitiveprogramming.trees;

import competitiveprogramming.trees.node.BTreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java
 */
public class BTreePrinter {

  private BTreePrinter() {
    throw new IllegalStateException("Utility class");
  }

  public static <T> void printHorizontal(BTreeNode<T> bTreeNode) {
    StringBuilder result = new StringBuilder();
    toString(bTreeNode, new StringBuilder(), true, result);
    System.out.println(result);
  }

  private static <T> void toString(BTreeNode<T> bTreeNode, StringBuilder prefix, boolean isLeft, StringBuilder result) {
    if (bTreeNode.getRight() != null) {
      toString(
          bTreeNode.getRight(),
          new StringBuilder().append(prefix).append(isLeft ? Constants.LINE_SEP : Constants.SPACE_SEP),
          false,
          result);
    }

    result.append(prefix)
        .append(isLeft ? Constants.LEFT : Constants.RIGHT)
        .append(bTreeNode.getValue().toString())
        .append(Constants.NEW_LINE);

    if (bTreeNode.getLeft() != null) {
      toString(
          bTreeNode.getLeft(),
          new StringBuilder().append(prefix).append(isLeft ? Constants.SPACE_SEP : Constants.LINE_SEP),
          true,
          result);
    }
  }


  public static <T extends Comparable<?>> void printVertical(BTreeNode<T> root) {
    int maxLevel = BTreePrinter.maxLevel(root);

    printNodeInternal(Collections.singletonList(root), 1, maxLevel);
  }

  private static <T extends Comparable<?>> void printNodeInternal(List<BTreeNode<T>> bTreeNodes, int level, int maxLevel) {
    if (bTreeNodes.isEmpty() || BTreePrinter.isAllElementsNull(bTreeNodes))
      return;

    int floor = maxLevel - level;
    int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
    int firstSpaces = (int) Math.pow(2, (floor)) - 1;
    int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

    BTreePrinter.printWhitespaces(firstSpaces);

    List<BTreeNode<T>> newBTreeNodes = new ArrayList<>();
    for (BTreeNode<T> BTreeNode : bTreeNodes) {
      if (BTreeNode != null) {
        System.out.print(BTreeNode.getValue());
        newBTreeNodes.add(BTreeNode.getLeft());
        newBTreeNodes.add(BTreeNode.getRight());
      } else {
        newBTreeNodes.add(null);
        newBTreeNodes.add(null);
        System.out.print(" ");
      }

      BTreePrinter.printWhitespaces(betweenSpaces);
    }
    System.out.println("");

    for (int i = 1; i <= edgeLines; i++) {
      for (BTreeNode<T> BTreeNode : bTreeNodes) {
        BTreePrinter.printWhitespaces(firstSpaces - i);
        if (BTreeNode == null) {
          BTreePrinter.printWhitespaces(edgeLines + edgeLines + i + 1);
          continue;
        }

        if (BTreeNode.getLeft() != null)
          System.out.print("/");
        else
          BTreePrinter.printWhitespaces(1);

        BTreePrinter.printWhitespaces(i + i - 1);

        if (BTreeNode.getRight() != null)
          System.out.print("\\");
        else
          BTreePrinter.printWhitespaces(1);

        BTreePrinter.printWhitespaces(edgeLines + edgeLines - i);
      }

      System.out.println("");
    }

    printNodeInternal(newBTreeNodes, level + 1, maxLevel);
  }

  private static void printWhitespaces(int count) {
    for (int i = 0; i < count; i++)
      System.out.print(" ");
  }

  private static <T extends Comparable<?>> int maxLevel(BTreeNode<T> bTreeNode) {
    if (bTreeNode == null)
      return 0;

    return Math.max(BTreePrinter.maxLevel(bTreeNode.getLeft()), BTreePrinter.maxLevel(bTreeNode.getRight())) + 1;
  }

  private static <T> boolean isAllElementsNull(List<T> list) {
    for (Object object : list) {
      if (object != null)
        return false;
    }

    return true;
  }

}
