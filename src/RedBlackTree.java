import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.Forest;

import java.awt.*;

public class RedBlackTree {
    private Node root;
    private Node NEWN;

    public void clear() {
        NEWN = new Node();
        NEWN.color = Color.BLACK;
        NEWN.left = null;
        NEWN.right = null;
        root = NEWN;
    }

    // Search the tree.
    private Node searchTreeHelper(Node node, int key) {
        if (node == NEWN || key == Integer.valueOf(node.data)) {
            return node;
        }

        if (key < Integer.valueOf(node.data)) {
            return searchTreeHelper(node.left, key);
        }
        return searchTreeHelper(node.right, key);
    }

    private void newRBT(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    // Balance the tree after Action of a node
    private void fixDelete(Node A) {
        Node B;
        while (A != root && A.color == Color.BLACK) {
            if (A == A.parent.left) {
                B = A.parent.right;
                if (B.color == Color.RED) {
                    B.color = Color.BLACK;
                    A.parent.color = Color.RED;
                    leftRotate(A.parent);
                    B = A.parent.right;
                }
                if (B.left.color == Color.BLACK && B.right.color == Color.BLACK) {
                    B.color = Color.RED;
                    A = A.parent;
                } else {
                    if (B.right.color == Color.BLACK) {
                        B.left.color = Color.BLACK;
                        B.color = Color.RED;
                        rightRotate(B);
                        B = A.parent.right;
                    }

                    B.color = A.parent.color;
                    A.parent.color = Color.BLACK;
                    B.right.color = Color.BLACK;
                    leftRotate(A.parent);
                    A = root;
                }
            } else {
                B = A.parent.left;
                if (B.color == Color.RED) {
                    B.color = Color.BLACK;
                    A.parent.color = Color.RED;
                    rightRotate(A.parent);
                    B = A.parent.left;
                }

                if (B.right.color == Color.BLACK && B.right.color == Color.BLACK) {
                    B.color = Color.RED;
                    A = A.parent;
                } else {
                    if (B.left.color == Color.BLACK) {
                        B.right.color = Color.BLACK;
                        B.color = Color.RED;
                        leftRotate(B);
                        B = A.parent.left;
                    }

                    B.color = A.parent.color;
                    A.parent.color = Color.BLACK;
                    B.left.color = Color.BLACK;
                    rightRotate(A.parent);
                    A = root;
                }
            }
        }
        A.color = Color.BLACK;
    }

    private void DeleteNode(Node node, int key) {
        Node z = NEWN;
        Node x, y;
        while (node != NEWN) {
            if (Integer.valueOf(node.data) == key) {
                z = node;
            }

            if (Integer.valueOf(node.data) <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == NEWN) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        y = z;
        Paint yOriginalColor = y.color;
        if (z.left == NEWN) {
            x = z.right;
            newRBT(z, z.right);
        } else if (z.right == NEWN) {
            x = z.left;
            newRBT(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                newRBT(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            newRBT(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == Color.BLACK) {
            fixDelete(x);
        }
    }

    // Balance the node after insertion
    private void fixInsert(Node N) {
        Node U;
        while (N.parent.color == Color.RED) {
            if (N.parent == N.parent.parent.right) {
                U = N.parent.parent.left;
                if (U.color == Color.RED) {
                    U.color = Color.BLACK;
                    N.parent.color = Color.BLACK;
                    N.parent.parent.color = Color.RED;
                    N = N.parent.parent;
                } else {
                    if (N == N.parent.left) {
                        N = N.parent;
                        rightRotate(N);
                    }
                    N.parent.color = Color.BLACK;
                    N.parent.parent.color = Color.RED;
                    leftRotate(N.parent.parent);
                }
            } else {
                U = N.parent.parent.right;

                if (U.color == Color.RED) {
                    U.color = Color.BLACK;
                    N.parent.color = Color.BLACK;
                    N.parent.parent.color = Color.RED;
                    N = N.parent.parent;
                } else {
                    if (N == N.parent.right) {
                        N = N.parent;
                        leftRotate(N);
                    }
                    N.parent.color = Color.BLACK;
                    N.parent.parent.color = Color.RED;
                    rightRotate(N.parent.parent);
                }
            }
            if (N == root) {
                break;
            }
        }
        root.color = Color.BLACK;
    }

    public void insert(String key) {
        Node node = new Node();
        node.parent = null;
        node.data = key;
        node.left = NEWN;
        node.right = NEWN;
        node.color = Color.RED;

        Node y = null;
        Node x = this.root;

        while (x != NEWN) {
            y = x;
            if (Integer.valueOf(node.data) < Integer.valueOf(y.data)) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (Integer.valueOf(node.data) < Integer.valueOf(y.data)) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = Color.BLACK;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    public Node minimum(Node node) {
        while (node.left != NEWN) {
            node = node.left;
        }
        return node;
    }

    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != NEWN) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != NEWN) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    private void printHelper(Node root, String indent, boolean last) {
        if (root != NEWN) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            String sColor = root.color == Color.RED ? "RED" : "BLACK";
            System.out.println(root.data + "(" + sColor + ")");
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }

    public RedBlackTree() {
        NEWN = new Node();
        NEWN.color = Color.BLACK;
        NEWN.left = null;
        NEWN.right = null;
        root = NEWN;
    }

    public Node searchTree(int k) {
        return searchTreeHelper(this.root, k);
    }

    public Node getRoot() {
        return this.root;
    }

    public void Delete(int data) {
        DeleteNode(this.root, data);
    }

    public void printTree() {
        printHelper(this.root, "", true);
    }

    Forest<Node, Integer> buildGraph() {

        Forest<Node, Integer> graph = new DelegateForest<>();
        int i = 0;
        Node temp = new Node();
        Node t;
        temp = root;
        //while (root.right != null) {
        graph.addVertex(temp);
        if (root.left.data != null) {
            System.out.println("Left null");
            graph.addVertex(temp.left);
            graph.addEdge(i, temp, temp.left);
        } else {
            t = new Node(null, Color.BLACK);
            graph.addVertex(t);
            graph.addEdge(i, temp, t);
        }
        if (root.right.data != null) {
            System.out.println("right null");
            graph.addVertex(temp.right);
            graph.addEdge(i + 1, temp, temp.right);
        } else {
            t = new Node(null, Color.BLACK);
            graph.addVertex(t);
            graph.addEdge(i + 1, temp, t);
        }

        temp = temp.right;

        // }
        return graph.addVertex();
    }

}