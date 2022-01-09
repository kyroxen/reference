package javastuff.ds.hashset.generic.src.main.java;

public class HashFunctionMod<T> implements HashFunction<T> {

  private final int mod;

  public HashFunctionMod(int mod) {
    this.mod = mod;
  }

  @Override
  public int process(T key) {
    final int javaHash = key.hashCode();
    final int value = javaHash == Integer.MIN_VALUE ? Math.abs(javaHash + 1) : Math.abs(javaHash);
    return value % mod;
  }
}
