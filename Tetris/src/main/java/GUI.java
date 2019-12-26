import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI implements ActionListener, KeyListener {
    // PROPERTIES
    JFrame theframe = new JFrame("Tetris");
    JPanel mainPanel = new JPanel();
    BoardPanel boardPanel = new BoardPanel();

    // Debug buttons
    JButton butRotateLeft = new JButton("Rotate Left");
    JButton butRotateRight = new JButton("Rotate Right");
    JButton butMoveLeft = new JButton("Move Left");
    JButton butMoveRight = new JButton("Move Right");
    JButton butMoveDown = new JButton("Move Down");
    JButton butMoveUp = new JButton("Move Up");

    Timer thetimer = new Timer(1000 / 60, this); //60FPS

    boolean blnRotateLeftHeld = false;
    boolean blnRotateRightHeld = false;

    // METHODS
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == thetimer) {
            this.boardPanel.repaint();
        } else if (evt.getSource() == butRotateLeft) {
            Controller.rotate(BoardPanel.blockCurrent, "left");
        } else if (evt.getSource() == butRotateRight) {
            Controller.rotate(BoardPanel.blockCurrent, "right");
        } else if (evt.getSource() == butMoveLeft) {
            Controller.moveLeft(BoardPanel.blockCurrent);
        } else if (evt.getSource() == butMoveRight) {
            Controller.moveRight(BoardPanel.blockCurrent);
        } else if (evt.getSource() == butMoveDown) {
            Controller.moveDown(BoardPanel.blockCurrent);
        } else if (evt.getSource() == butMoveUp) {
            Controller.moveUp(BoardPanel.blockCurrent);
        }
    }

    // KeyListener
    public void keyTyped(KeyEvent evt) {
    }
    public void keyPressed(KeyEvent evt) {
        int intKeyCode = evt.getKeyCode();
        switch (intKeyCode) { // Left/right movement
            case KeyEvent.VK_LEFT: // Left arrow
                Controller.moveLeft(BoardPanel.blockCurrent); // Move block left
                break;
            case KeyEvent.VK_RIGHT: // Right arrow
                Controller.moveRight(BoardPanel.blockCurrent);
                break;
            case KeyEvent.VK_DOWN: // Down arrow
                Controller.moveDown(BoardPanel.blockCurrent);
                break;
        }

        if (blnRotateLeftHeld == false) { // Only activate once (disable holding rotate button)
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                Controller.rotate(BoardPanel.blockCurrent, "left");
                blnRotateLeftHeld = true;
            }
        }
        if (blnRotateRightHeld == false) { // Only activate once (disable holding rotate button)
            if (evt.getKeyCode() == KeyEvent.VK_Z) {
                Controller.rotate(BoardPanel.blockCurrent, "right");
                blnRotateRightHeld = true;
            }
        }
    }

    public void keyReleased(KeyEvent evt) {
        if (blnRotateLeftHeld == true && evt.getKeyCode() == KeyEvent.VK_UP) { // Reenable rotate button after release
            blnRotateLeftHeld = false;
        } else if (blnRotateRightHeld == true && evt.getKeyCode() == KeyEvent.VK_Z) {
            blnRotateRightHeld = false;
        }
    }
    // CONSTRUCTOR

    public GUI() {
        //mainPanel.setPreferredSize(new Dimension(1280, 720));
        this.boardPanel.setPreferredSize(new Dimension(BoardPanel.intXMax + 400, BoardPanel.intYMax));
        this.boardPanel.setLayout(null);
        //Block JBlock = new Block(Block.JBlock);

        //Debug buttons
        this.butRotateLeft.addActionListener(this);
        this.butRotateLeft.setSize(110,30);
        this.butRotateLeft.setLocation(BoardPanel.intXMax + 50, 50);
        this.boardPanel.add(butRotateLeft);

        this.butRotateRight.addActionListener(this);
        this.butRotateRight.setSize(110,30);
        this.butRotateRight.setLocation(BoardPanel.intXMax + 50, 90);
        this.boardPanel.add(butRotateRight);

        this.butMoveLeft.addActionListener(this);
        this.butMoveLeft.setSize(110,30);
        this.butMoveLeft.setLocation(BoardPanel.intXMax + 170, 50);
        this.boardPanel.add(butMoveLeft);

        this.butMoveRight.addActionListener(this);
        this.butMoveRight.setSize(110,30);
        this.butMoveRight.setLocation(BoardPanel.intXMax + 170, 90);
        this.boardPanel.add(butMoveRight);

        this.butMoveDown.addActionListener(this);
        this.butMoveDown.setSize(110,30);
        this.butMoveDown.setLocation(BoardPanel.intXMax + 170, 130);
        this.boardPanel.add(butMoveDown);

        this.butMoveUp.addActionListener(this);
        this.butMoveUp.setSize(110,30);
        this.butMoveUp.setLocation(BoardPanel.intXMax + 170, 170);
        this.boardPanel.add(butMoveUp);

        this.butMoveUp.setFocusable(false);
        this.butMoveDown.setFocusable(false);
        this.butMoveLeft.setFocusable(false);
        this.butMoveRight.setFocusable(false);
        this.butRotateLeft.setFocusable(false);
        this.butRotateRight.setFocusable(false);


        this.boardPanel.setFocusable(true);
        this.boardPanel.addKeyListener(this);

        this.theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.theframe.setContentPane(boardPanel);
        this.theframe.pack();
        this.theframe.setResizable(false);
        this.theframe.setLocationRelativeTo(null);
        this.theframe.setVisible(true);

        this.thetimer.start();

    }
}
