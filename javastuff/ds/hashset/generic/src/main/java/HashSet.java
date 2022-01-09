package javastuff.ds.hashset.generic.src.main.java;

import java.util.ArrayList;
import java.util.List;

public class HashSet<T> {

  private final List<Bucket<T>> buckets;
  private final HashFunction<T> hashFunction;

  public HashSet(final int capacity) {
    this.buckets = new ArrayList<>(); // idx from 0 => capacity
    this.hashFunction = new HashFunctionMod<>(capacity);
    for (int i = 0; i < capacity; i++) { // initialise the buckets
      buckets.add(new BucketLinkedList<>());
    }
  }

  public void add(T key) {
    final Bucket<T> bucket = getBucket(key);
    bucket.add(key);
  }

  public boolean contains(T key) {
    final Bucket<T> bucket = getBucket(key);
    return bucket.contains(key);
  }

  public void remove(T key) {
    final Bucket<T> bucket = getBucket(key);
    bucket.remove(key);
  }

  @Override
  public String toString() {
    return "buckets: " + buckets;
  }

  private Bucket<T> getBucket(T key) {
    final int idx = hashFunction.process(key);
    return buckets.get(idx);
  }
}
