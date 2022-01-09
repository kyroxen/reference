package javastuff.ds.hashset.generic.src.main.java;

public interface HashFunction<T> {
  int process(T key);
}
