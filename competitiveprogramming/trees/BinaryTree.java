package competitiveprogramming.trees;

import java.util.*;


public class BinaryTree {

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
  }

  public static <T> Map<T, List<T>> levelOrderTraversal(Node<T> root) {
    if (root == null) {
      return Collections.emptyMap();
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
          Node<T> left = poll.getLeft();
          Node<T> right = poll.getRight();

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
