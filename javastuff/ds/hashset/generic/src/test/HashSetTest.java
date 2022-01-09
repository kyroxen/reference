package javastuff.ds.hashset.generic.src.test;

import javastuff.ds.hashset.generic.src.main.java.HashSet;

public class HashSetTest {
  public static void main(String[] args) {


//    print(Math.abs(Integer.MIN_VALUE));
    HashSet<String> hashSet = new HashSet<>(17);

    hashSet.add("996");
    hashSet.add("17");
    hashSet.add("18");
    hashSet.add("19");
    hashSet.add("20");
    hashSet.add("xbf21abcd");
    hashSet.add("999");
    print(hashSet);

    print(hashSet.contains("3"));
    print(hashSet.contains("996"));
    print(hashSet.contains("999"));

    hashSet.remove("996");
    print(hashSet);

    print(hashSet.contains("996"));

    hashSet.add("996");
    print(hashSet);
  }

  private static void print(Object m) {
    System.out.println(m);
  }
}
