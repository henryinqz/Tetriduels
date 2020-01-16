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
    Thread threadBlockFall = new Thread(new BlockFallTimer());
    //JButton butMoveUp = new JButton("Move Up");

    Timer timerGame = new Timer(1000 / 60, this); //60FPS

    // Booleans to prevent holding keypresses
    boolean blnHardDropHeld = false;
    boolean blnHoldBlockHeld = false;
    boolean blnRotateLeftHeld = false;
    boolean blnRotateRightHeld = false;

    // Chat
    public static boolean blnChatOpen = false;

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

    public void actionPerformed(ActionEvent evt) {
        if (this.boardPanel.isFocusOwner() != true && blnChatOpen == false) { // Ensure user can move blocks
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
        if (intKeyCode == SettingsMenu.intKeyLeft) { // Left/right movement
            Controller.moveLeft(BoardPanel.blockCurrent); // Move block left
        } else if (intKeyCode == SettingsMenu.intKeyRight) {// Right arrow
            Controller.moveRight(BoardPanel.blockCurrent);
        } else if (intKeyCode == SettingsMenu.intKeyDown){
            Controller.moveDown(BoardPanel.blockCurrent);
        } else if (intKeyCode == KeyEvent.VK_T) {
           if (blnChatOpen == true) { // If chat is open, close chat
               this.boardPanel.requestFocus();
               blnChatOpen = false;
           } else if (blnChatOpen == false) { // If chat is closed, open chat
               ConnectMenu.fieldChatMessage.requestFocus();
               blnChatOpen = true;
           }
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
        this.boardPanel.setPreferredSize(new Dimension(GUI.FRAME_WIDTH,GUI.FRAME_HEIGHT));
        this.boardPanel.setLayout(null);

        /*this.butMoveUp.addActionListener(this);
        this.butMoveUp.setSize(110,30);
        this.butMoveUp.setLocation(BoardPanel.intXMax + 285, 210);
        this.boardPanel.add(butMoveUp);
        this.butMoveUp.setFocusable(false);*/

        this.boardPanel.add(ConnectMenu.scrollChat);
        this.boardPanel.add(ConnectMenu.fieldChatMessage);
        this.boardPanel.add(ConnectMenu.butChatMessageSend);


        // Allow for keypresses to move block
        this.boardPanel.setFocusable(true);
        this.boardPanel.requestFocus();
        this.boardPanel.addKeyListener(this);

        this.timerGame.start(); // 60FPS timer
        this.threadBlockFall.start(); // Auto fall timer

    }
}
