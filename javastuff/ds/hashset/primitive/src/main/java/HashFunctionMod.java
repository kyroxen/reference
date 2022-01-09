package javastuff.ds.hashset.primitive.src.main.java;

public class HashFunctionMod implements HashFunction {

  private final int mod;

  public HashFunctionMod(int mod) {
    this.mod = mod;
  }

  @Override
  public int process(int key) {
    return key % mod;
  }
}
