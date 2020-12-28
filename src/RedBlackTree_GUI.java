import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RedBlackTree_GUI extends JFrame {
    RedBlackTree RBT = new RedBlackTree();
    private JPanel mainWindow;
    private JButton addButton;
    private JButton deleteButton;
    private JTextField insertionNumber;
    private JButton clearButton;
    private JPanel space;

    public RedBlackTree_GUI(String title) {
        super(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainWindow);
        this.setSize(500, 500);
        insertionNumber.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                insertionNumber.setText("");
            }
        });
        insertionNumber.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (insertionNumber.getText().equalsIgnoreCase("")) {
                    insertionNumber.setText("Insert a Number");
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(insertionNumber.getText());
                RBT.insert(Integer.parseInt(insertionNumber.getText()));
                RBT.printTree();
                drawGraph(RBT.buildGraph());
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RBT.Delete(Integer.parseInt(insertionNumber.getText()));
                RBT.printTree();
                drawGraph(RBT.buildGraph());
            }
        });
        insertionNumber.addKeyListener(new KeyAdapter() {
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RBT.clear();
                space.removeAll();
                space.revalidate();
                space.repaint();
            }
        });
    }

    private void drawGraph(Forest<Node, Integer> graph) {

        Transformer<Node, Paint> vertexColor = myNode -> myNode.color;
        Transformer<Node, String> vertexText = myNode -> String.valueOf(myNode.data);

        Layout<Node, Integer> layout = new TreeLayout<>(graph);
        VisualizationViewer<Node, Integer> vv = new VisualizationViewer<>(layout);

        vv.getRenderContext().setVertexLabelTransformer(vertexText);
        vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);


        space.removeAll();
        space.add(vv);
        space.revalidate();
    }


    public static void main(String args[]) {
        RedBlackTree_GUI frame = new RedBlackTree_GUI("Red Black Tree");
        frame.setVisible(true);

    }
}
