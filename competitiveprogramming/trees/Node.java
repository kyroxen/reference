package competitiveprogramming.trees;

public class Node <T>{

    private T value;
    private Node<T> left;
    private Node<T> right;

    private boolean isRoot;

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }


    public Node(T value, Node<T> left, Node<T> right){
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node() {}

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

}
