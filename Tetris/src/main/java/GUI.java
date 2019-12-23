import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    // PROPERTIES
    JFrame theframe = new JFrame("Tetris");
    JPanel mainPanel = new JPanel();
    BoardPanel boardPanel = new BoardPanel();

    Timer thetimer = new Timer(1000/60, this); //60FPS

    // METHODS
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == thetimer) {
            boardPanel.repaint();
        }
    }

    // CONSTRUCTOR
    public GUI() {
        //mainPanel.setPreferredSize(new Dimension(1280, 720));
        boardPanel.setPreferredSize(new Dimension(1280, 720));

        theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theframe.setContentPane(boardPanel);
        theframe.pack();
        theframe.setResizable(false);
        theframe.setLocationRelativeTo(null);
        theframe.setVisible(true);

        thetimer.start();

    }

    // MAIN METHOD
    public static void main(String[] args) {
        new GUI();
    }
}
