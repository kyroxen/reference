package javastuff.ds.hashset.primitive.src.main.java;

import java.util.Arrays;

public class HashSet {

  private final Bucket[] buckets;
  private final HashFunction hashFunction;

  public HashSet(final int prime) {
    this.buckets = new Bucket[prime]; // idx from 0 => 996
    this.hashFunction = new HashFunctionMod(prime); // hash function's mod operation will use prime
    for (int i = 0; i < prime; i++) { // initialise the buckets
      buckets[i] = new BucketLinkedList();
    }
  }

  public void add(int key) {
    final int idx = hashFunction.process(key);
    final Bucket bucket = buckets[idx];
    bucket.add(key);
  }

  public boolean contains(int key) {
    final int idx = hashFunction.process(key);
    final Bucket bucket = buckets[idx];
    return bucket.contains(key);
  }

  public void remove(int key) {
    final int idx = hashFunction.process(key);
    final Bucket bucket = buckets[idx];
    bucket.remove(key);
  }

  @Override
  public String toString() {
    return "buckets: " + Arrays.toString(buckets);
  }
}
