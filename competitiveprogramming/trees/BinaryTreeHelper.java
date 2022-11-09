package competitiveprogramming.trees;

import competitiveprogramming.trees.node.BTreeNode;

import java.util.*;


public class BinaryTreeHelper {

  private BinaryTreeHelper() {
    throw new IllegalStateException("Utility class");
  }

  public static <T> void printHorizontal(BTreeNode<T> bTreeNode) {
    BTreePrinter.printHorizontal(bTreeNode);
  }

    public static BTreeNode<Integer> parseTree(List<String> list) {
    System.out.println(list);
    return construct(list, list.iterator());
  }

  private static BTreeNode<Integer> construct(List<String> list, Iterator<String> iterator){
    if(!iterator.hasNext()){
      return null;
    }

    String element = iterator.next();

    if(element.equalsIgnoreCase("x")){
      return null;
    }

    BTreeNode<Integer> bTreeNode = new BTreeNode<>(Integer.parseInt(element));

    BTreeNode<Integer> leftResult = construct(list, iterator);
    bTreeNode.setLeft(leftResult);

    BTreeNode<Integer> rightResult = construct(list, iterator);
    bTreeNode.setRight(rightResult);

    return bTreeNode;
  }

  public static <T> Map<T, List<T>> levelOrderTraversal(BTreeNode<T> root) {
    if (root == null) {
      return Collections.emptyMap();
    }
    Map<T, List<T>> parentToChildrenMap = new LinkedHashMap<>();
    Queue<BTreeNode<T>> queue = new ArrayDeque<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      List<T> levelList = new ArrayList<>();
      parentToChildrenMap.put(queue.peek().getValue(), levelList);
      int size = queue.size();
      while (size > 0) {
        BTreeNode<T> poll = queue.poll();
        if (poll != null) {
          BTreeNode<T> left = poll.getLeft();
          BTreeNode<T> right = poll.getRight();

          if(left != null) {
            queue.offer(left);
            levelList.add(left.getValue());
          }
          if(right != null) {
            queue.offer(right);
            levelList.add(right.getValue());
          }
        }
        size--;
      }

    }
    return parentToChildrenMap;
  }
}
