package javastuff.ds.hashset.generic.src.main.java;

public interface Bucket<T> {
  void add(T element);

  boolean contains(T element);

  void remove(T element);
}
