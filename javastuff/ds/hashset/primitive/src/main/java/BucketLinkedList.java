package javastuff.ds.hashset.primitive.src.main.java;

import java.util.LinkedList;

public class BucketLinkedList implements Bucket {

  private final LinkedList<Integer> list;

  public BucketLinkedList() {
    this.list = new LinkedList<>();
  }

  @Override
  public void add(Integer element) {
    if (!list.contains(element)) {
      list.add(element);
    }
  }

  @Override
  public boolean contains(Integer element) {
    return list.contains(element);
  }

  @Override
  public void remove(Integer element) {
    list.remove(element);
  }

  @Override
  public String toString() {
    return list.toString();
  }
}
