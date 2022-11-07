package competitiveprogramming.trees;

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

  public static <T> void printHorizontal(Node<T> node) {
    StringBuilder stringBuilder = toString(node, new StringBuilder(), true, new StringBuilder());
    System.out.println(stringBuilder.toString());
  }

  private static <T> StringBuilder toString(Node<T> node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
    if (node.getRight() != null) {
      toString(
          node.getRight(),
          new StringBuilder().append(prefix).append(isTail ? Constants.LINE_SEP : Constants.SPACE_SEP),
          false,
          sb);
    }

    sb.append(prefix)
        .append(isTail ? Constants.LEFT : Constants.RIGHT)
        .append(node.getValue().toString())
        .append(Constants.NEW_LINE);

    if (node.getLeft() != null) {
      toString(
          node.getLeft(),
          new StringBuilder().append(prefix).append(isTail ? Constants.SPACE_SEP : Constants.LINE_SEP),
          true,
          sb);
    }
    return sb;
  }


  public static <T extends Comparable<?>> void printVertical(Node<T> root) {
    int maxLevel = BTreePrinter.maxLevel(root);

    printNodeInternal(Collections.singletonList(root), 1, maxLevel);
  }

  private static <T extends Comparable<?>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
    if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
      return;

    int floor = maxLevel - level;
    int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
    int firstSpaces = (int) Math.pow(2, (floor)) - 1;
    int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

    BTreePrinter.printWhitespaces(firstSpaces);

    List<Node<T>> newNodes = new ArrayList<>();
    for (Node<T> node : nodes) {
      if (node != null) {
        System.out.print(node.getValue());
        newNodes.add(node.getLeft());
        newNodes.add(node.getRight());
      } else {
        newNodes.add(null);
        newNodes.add(null);
        System.out.print(" ");
      }

      BTreePrinter.printWhitespaces(betweenSpaces);
    }
    System.out.println("");

    for (int i = 1; i <= edgeLines; i++) {
      for (Node<T> node : nodes) {
        BTreePrinter.printWhitespaces(firstSpaces - i);
        if (node == null) {
          BTreePrinter.printWhitespaces(edgeLines + edgeLines + i + 1);
          continue;
        }

        if (node.getLeft() != null)
          System.out.print("/");
        else
          BTreePrinter.printWhitespaces(1);

        BTreePrinter.printWhitespaces(i + i - 1);

        if (node.getRight() != null)
          System.out.print("\\");
        else
          BTreePrinter.printWhitespaces(1);

        BTreePrinter.printWhitespaces(edgeLines + edgeLines - i);
      }

      System.out.println("");
    }

    printNodeInternal(newNodes, level + 1, maxLevel);
  }

  private static void printWhitespaces(int count) {
    for (int i = 0; i < count; i++)
      System.out.print(" ");
  }

  private static <T extends Comparable<?>> int maxLevel(Node<T> node) {
    if (node == null)
      return 0;

    return Math.max(BTreePrinter.maxLevel(node.getLeft()), BTreePrinter.maxLevel(node.getRight())) + 1;
  }

  private static <T> boolean isAllElementsNull(List<T> list) {
    for (Object object : list) {
      if (object != null)
        return false;
    }

    return true;
  }

}
