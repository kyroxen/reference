package competitiveprogramming.trees.node;

public class BTreeNode<T>{

  private T value;
  private BTreeNode<T> left;
  private BTreeNode<T> right;

  public BTreeNode(T value, BTreeNode<T> left, BTreeNode<T> right){
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public BTreeNode() {}

  public BTreeNode(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public BTreeNode<T> getLeft() {
    return left;
  }

  public void setLeft(BTreeNode<T> left) {
    this.left = left;
  }

  public BTreeNode<T> getRight() {
    return right;
  }

  public void setRight(BTreeNode<T> right) {
    this.right = right;
  }

}
