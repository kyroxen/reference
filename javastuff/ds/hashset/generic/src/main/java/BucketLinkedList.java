package javastuff.ds.hashset.generic.src.main.java;

import java.util.LinkedList;

public class BucketLinkedList<T> implements Bucket<T> {

  private final LinkedList<T> list;

  public BucketLinkedList() {
    this.list = new LinkedList<>();
  }

  @Override
  public void add(T element) {
    if (!list.contains(element)) {
      list.add(element);
    }
  }

  @Override
  public boolean contains(T element) {
    return list.contains(element);
  }

  @Override
  public void remove(T element) {
    list.remove(element);
  }

  @Override
  public String toString() {
    return list.toString();
  }
}
