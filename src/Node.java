public class Node {
    private int data;
    private char label;

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    private Node left;
    private Node right;

    public Node(char l, int n){
        data = n;
        left = null;
        right = null;
        label = l;
    }

    public Node(int n){
        data = n;
        left = null;
        right = null;
    }

    public char getLabel() {
        return label;
    }

    public int getData() {
        return data;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}