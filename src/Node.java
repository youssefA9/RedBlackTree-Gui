import java.awt.*;

class Node {
    int data;
    Node parent;
    Node left;
    Node right;
    Paint color;

    Node() {
    }

    Node(int n, Paint c) {
        data = n;
        color = c;
    }
}