package game;

import panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements ActionListener, KeyListener {
    // PROPERTIES
    BoardPanel boardPanel = new BoardPanel();
    GameOver gameOver = new GameOver();
    Thread threadBlockFall = new Thread(new BlockFallTimer());

    /*// Debug buttons
    JButton butRotateLeft = new JButton("Rotate Left");
    JButton butRotateRight = new JButton("Rotate Right");
    JButton butMoveLeft = new JButton("Move Left");
    JButton butMoveRight = new JButton("Move Right");
    JButton butMoveDown = new JButton("Move Down");
    JButton butMoveUp = new JButton("Move Up");*/

    Timer timerGame = new Timer(1000 / 60, this); //60FPS

    // Booleans to prevent holding keypresses
    boolean blnHardDropHeld = false;
    boolean blnHoldBlockHeld = false;
    boolean blnRotateLeftHeld = false;
    boolean blnRotateRightHeld = false;

    // METHODS
    public JPanel getPanel() {
        return boardPanel;
    }
    public static void endGame() {
        if (Tetriduels.blnGameLoop == false) {
            System.out.println("Game over!");
            Utility.setPanel(new GameOver().getPanel());
        }
    }

    public void killTimersThreads() {
        timerGame.stop();

    }

    public void actionPerformed(ActionEvent evt) {
        if (this.boardPanel.isFocusOwner() != true) {
            this.boardPanel.requestFocus();
        }

        if (evt.getSource() == timerGame) {
            this.boardPanel.repaint();
        }
        /*} else if (evt.getSource() == butMoveUp) {
            Controller.moveUp(BoardPanel.blockCurrent);
        }*/
    }

    // KeyListener
    public void keyTyped(KeyEvent evt) {
    }
    public void keyPressed(KeyEvent evt) {
        int intKeyCode = evt.getKeyCode();
        if(intKeyCode == SettingsMenu.intKeyLeft) { // Left/right movement
            Controller.moveLeft(BoardPanel.blockCurrent); // Move block left
        }else if(intKeyCode == SettingsMenu.intKeyRight) {// Right arrow
            Controller.moveRight(BoardPanel.blockCurrent);
        }else if(intKeyCode == SettingsMenu.intKeyDown){
            Controller.moveDown(BoardPanel.blockCurrent);
        }

        if (blnHardDropHeld == false) { // Only activate once (disable holding hard drop)
            if (evt.getKeyCode() == SettingsMenu.intKeyHardDrop) { // Spacebar
                Controller.hardDrop(BoardPanel.blockCurrent);
                blnHardDropHeld = true;
            }
        }

        if (blnHoldBlockHeld == false) { // Only activate once (disable holding down hold block key)
            if (evt.getKeyCode() == SettingsMenu.intKeyHold && BoardPanel.blockCurrent.blnHeldBefore == false) { // C key, and activate only if block has not been held before
                BoardPanel.blockCurrent = Controller.holdBlock(BoardPanel.blockCurrent);
                blnHoldBlockHeld = true;
            }
        }

        if (blnRotateLeftHeld == false) { // Only activate once (disable holding rotate button)
            if (evt.getKeyCode() == SettingsMenu.intKeyRotateLeft) { // Z key
                Controller.rotate(BoardPanel.blockCurrent, "left");
                blnRotateLeftHeld = true;
            }
        }
        if (blnRotateRightHeld == false) { // Only activate once (disable holding rotate button)
            if (evt.getKeyCode() == SettingsMenu.intKeyRotateRight) { // Up arrow
                Controller.rotate(BoardPanel.blockCurrent, "right");
                blnRotateRightHeld = true;
            }
        }
    }

    public void keyReleased(KeyEvent evt) {
        if (blnHardDropHeld == true && evt.getKeyCode() == SettingsMenu.intKeyHardDrop) { // Reenable hard drop button after release
            blnHardDropHeld = false;
        } else if (blnHoldBlockHeld == true && evt.getKeyCode() == SettingsMenu.intKeyHold) { // Reenable hold block button after relase
            blnHoldBlockHeld = false;
        } else if (blnRotateLeftHeld == true && evt.getKeyCode() == SettingsMenu.intKeyRotateLeft) { // Reenable left rotate button after release
            blnRotateLeftHeld = false;
        } else if (blnRotateRightHeld == true && evt.getKeyCode() == SettingsMenu.intKeyRotateRight) { // Reenable right rotate button after release
            blnRotateRightHeld = false;
        }
    }
    // CONSTRUCTOR

    public Game() {
        //mainPanel.setPreferredSize(new Dimension(1280, 720));
        //this.boardPanel.setPreferredSize(new Dimension(BoardPanel.intXMax + 400, BoardPanel.intYMax));
        this.boardPanel.setPreferredSize(new Dimension(GUI.FRAME_WIDTH,GUI.FRAME_HEIGHT));
        this.boardPanel.setLayout(null);

        /*//Debug buttons
        this.butRotateLeft.addActionListener(this);
        this.butRotateLeft.setSize(110,30);
        this.butRotateLeft.setLocation(BoardPanel.intXMax + 285, 10);
        this.boardPanel.add(butRotateLeft);

        this.butRotateRight.addActionListener(this);
        this.butRotateRight.setSize(110,30);
        this.butRotateRight.setLocation(BoardPanel.intXMax + 285, 50);
        this.boardPanel.add(butRotateRight);

        this.butMoveLeft.addActionListener(this);
        this.butMoveLeft.setSize(110,30);
        this.butMoveLeft.setLocation(BoardPanel.intXMax + 285, 90);
        this.boardPanel.add(butMoveLeft);

        this.butMoveRight.addActionListener(this);
        this.butMoveRight.setSize(110,30);
        this.butMoveRight.setLocation(BoardPanel.intXMax + 285, 130);
        this.boardPanel.add(butMoveRight);

        this.butMoveDown.addActionListener(this);
        this.butMoveDown.setSize(110,30);
        this.butMoveDown.setLocation(BoardPanel.intXMax + 285, 170);
        this.boardPanel.add(butMoveDown);

        this.butMoveUp.addActionListener(this);
        this.butMoveUp.setSize(110,30);
        this.butMoveUp.setLocation(BoardPanel.intXMax + 285, 210);
        this.boardPanel.add(butMoveUp);

        this.butMoveUp.setFocusable(false);
        this.butMoveDown.setFocusable(false);
        this.butMoveLeft.setFocusable(false);
        this.butMoveRight.setFocusable(false);
        this.butRotateLeft.setFocusable(false);
        this.butRotateRight.setFocusable(false);
        // end of debug buttons*/

        this.boardPanel.setFocusable(true);
        this.boardPanel.requestFocus();
        this.boardPanel.addKeyListener(this);

        /*this.theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.theframe.setContentPane(boardPanel);
        this.theframe.pack();
        this.theframe.setResizable(false);
        this.theframe.setLocationRelativeTo(null);
        this.theframe.setVisible(true);*/

        this.timerGame.start();
        this.threadBlockFall.start();

    }
}
