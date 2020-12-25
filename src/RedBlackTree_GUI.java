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
        /*insertionNumber.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                insertionNumber.setText("Insert a Number");
            }
        });*/
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(insertionNumber.getText());
                RBT.insert(Integer.parseInt(insertionNumber.getText()));
                RBT.printTree();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RBT.Delete(Integer.parseInt(insertionNumber.getText()));
                RBT.printTree();
            }
        });
        insertionNumber.addKeyListener(new KeyAdapter() {
        });
    }


    public static void main(String args[]) {
        RedBlackTree_GUI frame = new RedBlackTree_GUI("Red Black Tree");
        frame.setVisible(true);

    }
}
