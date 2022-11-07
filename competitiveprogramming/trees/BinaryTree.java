package competitiveprogramming.trees;

import java.util.*;


public class BinaryTree {

  static class Result {
    int index;
    boolean isRight;
    Node<Integer> node;

    Result(int index, boolean isRight){
      this.index = index;
      this.isRight = isRight;
    }
  }

  public static Node<Integer> parseTree(List<String> list) {
    System.out.println(list);
    return construct(list, list.iterator());
  }

  private static Node<Integer> construct(List<String> list, Iterator<String> iterator){
    if(!iterator.hasNext()){
      return null;
    }

    String element = iterator.next();

    if(element.equalsIgnoreCase("x")){
      return null;
    }

    Node<Integer> node = new Node<>(Integer.parseInt(element));

    Node<Integer> leftResult = construct(list, iterator);
    node.setLeft(leftResult);

    Node<Integer> rightResult = construct(list, iterator);
    node.setRight(rightResult);

    return node;
  }

  public static <T> void print(Node<T> root){
    Map<T, List<T>> levelOrderTraversal = levelOrderTraversal(root);
    System.out.print(levelOrderTraversal);
//    for (List<T> level : levelOrderTraversal) {
//      System.out.println();
//      System.out.print(level);
//    }
  }

  public static <T> Map<T, List<T>> levelOrderTraversal(Node<T> root) {
    if (root == null) {
      return null;
    }
    Map<T, List<T>> parentToChildrenMap = new LinkedHashMap<>();
    Queue<Node<T>> queue = new ArrayDeque<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      List<T> levelList = new ArrayList<>();
      parentToChildrenMap.put(queue.peek().getValue(), levelList);
      int size = queue.size();
      while (size > 0) {
        Node<T> poll = queue.poll();
        if (poll != null) {
          levelList.add(poll.getValue());
          if(poll.getLeft() != null)queue.offer(poll.getLeft());
          if(poll.getRight() != null)queue.offer(poll.getRight());
        }
        size--;
        //if(poll != null ) parentToChildrenMap.computeIfAbsent(poll.getValue(), k -> new ArrayList<>()).addAll(levelList);
      }

    }
    return parentToChildrenMap;
  }
}
