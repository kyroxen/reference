package javastuff.ds.hashset.primitive.src.test;

import javastuff.ds.hashset.primitive.src.main.java.HashSet;

public class HashSetTest {
  public static void main(String[] args) {
    HashSet hashSet = new HashSet(11);

    hashSet.add(996);
    print(hashSet);

    hashSet.add(17);
    print(hashSet);

    hashSet.add(999);
    print(hashSet);

    print(hashSet.contains(3));
    print(hashSet.contains(996));
    print(hashSet.contains(999));

    hashSet.remove(996);
    print(hashSet);

    print(hashSet.contains(996));

    hashSet.add(996);
    print(hashSet);
  }

  private static void print(Object m) {
    System.out.println(m);
  }
}
