import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RedBlackTree_GUI extends JFrame {
    private JPanel mainWindow;
    private JButton Add;
    private JButton Delete;
    private JTextField insertionNumber;

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
    }

    public static void main(String args[]) {
        JFrame frame = new RedBlackTree_GUI("Red Black Tree");
        frame.setVisible(true);
    }
}
