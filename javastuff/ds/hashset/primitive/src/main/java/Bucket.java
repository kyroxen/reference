package javastuff.ds.hashset.primitive.src.main.java;

public interface Bucket {
  void add(Integer element);

  boolean contains(Integer element);

  void remove(Integer element);
}
