package javastuff.concurrency.compf;

public class MyException extends RuntimeException {

  public MyException() {
    super();
  }

  /**
   * Constructs an <code>InterruptedException</code> with the specified detail message.
   *
   * @param s the detail message.
   */
  public MyException(String s) {
    super(s);
  }
}
