import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphDraw extends JFrame {
    int width;
    int height;

    ArrayList<Node> ns;
    ArrayList<edge> edges;


    public GraphDraw(String name) {
        JPanel mainWindow;
        this.setTitle(name);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ns = new ArrayList<Node>();
        edges = new ArrayList<edge>();
        width = 30;
        height = 30;
    }

    class Node {
        int x, y;
        int v;

        public Node(int value, int myX, int myY) {
            x = myX;
            y = myY;
            v = value;
        }
    }

    class edge {
        int i, j;

        public edge(int ii, int jj) {
            i = ii;
            j = jj;
        }
    }

    public void addNode(int value, int x, int y) {
        //add a node at pixel (x,y)
        ns.add(new Node(value, x, y));
        this.repaint();
    }

    public void addEdge(int i, int j) {
        //add an edge between nodes i and j
        edges.add(new edge(i, j));
        this.repaint();
    }

    public void paint(Graphics g) { // draw the nodes and edges
        FontMetrics f = g.getFontMetrics();
        int nodeHeight = Math.max(height, f.getHeight());

        g.setColor(Color.black);
        for (edge e : edges) {
            g.drawLine(ns.get(e.i).x, ns.get(e.i).y,
                    ns.get(e.j).x, ns.get(e.j).y);
        }

        for (Node n : ns) {
            int nodeWidth = Math.max(width, f.stringWidth(String.valueOf(n.v)) + width / 2);
            g.setColor(Color.white);
            g.fillOval(n.x - nodeWidth / 2, n.y - nodeHeight / 2,
                    nodeWidth, nodeHeight);
            g.setColor(Color.black);
            g.drawOval(n.x - nodeWidth / 2, n.y - nodeHeight / 2,
                    nodeWidth, nodeHeight);

            g.drawString(String.valueOf(n.v), n.x - f.stringWidth(String.valueOf(n.v)) / 2,
                    n.y + f.getHeight() / 2);
        }
    }
}
