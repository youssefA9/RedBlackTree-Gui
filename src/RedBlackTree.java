import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RedBlackTree {
    private Node root;
    private Node NEWN;
    ArrayList<Integer> nodes;

    // Search the tree.
    private Node searchTreeHelper(Node node, int key) {
        if (node == NEWN || key == node.data) {
            return node;
        }

        if (key < node.data) {
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
        while (A != root && A.color == 0) {
            if (A == A.parent.left) {
                B = A.parent.right;
                if (B.color == 1) {
                    B.color = 0;
                    A.parent.color = 1;
                    leftRotate(A.parent);
                    B = A.parent.right;
                }
                if (B.left.color == 0 && B.right.color == 0) {
                    B.color = 1;
                    A = A.parent;
                } else {
                    if (B.right.color == 0) {
                        B.left.color = 0;
                        B.color = 1;
                        rightRotate(B);
                        B = A.parent.right;
                    }

                    B.color = A.parent.color;
                    A.parent.color = 0;
                    B.right.color = 0;
                    leftRotate(A.parent);
                    A = root;
                }
            } else {
                B = A.parent.left;
                if (B.color == 1) {
                    B.color = 0;
                    A.parent.color = 1;
                    rightRotate(A.parent);
                    B = A.parent.left;
                }

                if (B.right.color == 0 && B.right.color == 0) {
                    B.color = 1;
                    A = A.parent;
                } else {
                    if (B.left.color == 0) {
                        B.right.color = 0;
                        B.color = 1;
                        leftRotate(B);
                        B = A.parent.left;
                    }

                    B.color = A.parent.color;
                    A.parent.color = 0;
                    B.left.color = 0;
                    rightRotate(A.parent);
                    A = root;
                }
            }
        }
        A.color = 0;
    }

    private void DeleteNode(Node node, int key) {
        Node z = NEWN;
        Node x, y;
        while (node != NEWN) {
            if (node.data == key) {
                z = node;
            }

            if (node.data <= key) {
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
        int yOriginalColor = y.color;
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
        if (yOriginalColor == 0) {
            fixDelete(x);
        }
    }

    // Balance the node after insertion
    private void fixInsert(Node N) {
        Node U;

        while (N.parent.color == 1) {
            if (N.parent == N.parent.parent.right) {
                U = N.parent.parent.left;
                if (U.color == 1) {
                    U.color = 0;
                    N.parent.color = 0;
                    N.parent.parent.color = 1;
                    N = N.parent.parent;
                } else {
                    if (N == N.parent.left) {
                        N = N.parent;
                        rightRotate(N);
                    }
                    N.parent.color = 0;
                    N.parent.parent.color = 1;
                    leftRotate(N.parent.parent);
                }
            } else {
                U = N.parent.parent.right;

                if (U.color == 1) {
                    U.color = 0;
                    N.parent.color = 0;
                    N.parent.parent.color = 1;
                    N = N.parent.parent;
                } else {
                    if (N == N.parent.right) {
                        N = N.parent;
                        leftRotate(N);
                    }
                    N.parent.color = 0;
                    N.parent.parent.color = 1;
                    rightRotate(N.parent.parent);
                }
            }
            if (N == root) {
                break;
            }
        }
        root.color = 0;
        this.printTree();
    }

    public void insert(int key) {
        Node node = new Node();
        node.parent = null;
        node.data = key;
        node.left = NEWN;
        node.right = NEWN;
        node.color = 1;
        nodes.add(node.data);
        Node y = null;
        Node x = this.root;

        while (x != NEWN) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = 0;
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

    // el print m4 3gbaneeeee ya jooooo.
    private void printHelper(Node root, String indent, boolean last, GraphDraw f) {
        if (root != NEWN) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sColor = root.color == 1 ? "RED" : "BLACK";
            System.out.println(root.data + "(" + sColor + ")");



            int x = 200; int y = 50;
            ArrayList<Integer> printing = nodes;

            for (int i : printing){

                if(last){
                    f.addNode(i, x, y);
                    x = x + 50;
                    y = y + 30;
                    //nodes.remove(printing.indexOf(i));
                }else {
                    f.addNode(i, x, y);
                    //nodes.remove(printing.indexOf(i));
                    x = x - 50;
                    y = y + 30;
                }

            }
            //f.addEdge(0, 1);

            printHelper(root.left, indent, false, f);
            printHelper(root.right, indent, true, f);
        }
    }

    public RedBlackTree() {
        NEWN = new Node();
        NEWN.color = 0;
        NEWN.left = null;
        NEWN.right = null;
        root = NEWN;
        nodes = new ArrayList<Integer>();
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
        GraphDraw f = new GraphDraw("Tree");
        f.setSize(400, 400);
        f.setVisible(true);
        
        printHelper(this.root, "", true, f);
    }

}