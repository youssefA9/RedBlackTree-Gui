import java.awt.*;

class Node {
    String data;
    Node parent;
    Node left = null;
    Node right = null;
    Paint color;

    Node() {
    }

    Node(String n, Paint c) {
        data = n;
        color = c;
    }
}