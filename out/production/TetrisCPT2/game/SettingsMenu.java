package panels;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SettingsMenu implements ActionListener, KeyListener {
    //Static variables for all keybinds
    public static int intKeyHardDrop = KeyEvent.VK_SPACE;
    public static int intKeyLeft = KeyEvent.VK_LEFT;
    public static int intKeyRight = KeyEvent.VK_RIGHT;
    public static int intKeyRotateRight = KeyEvent.VK_UP;
    public static int intKeyRotateLeft = KeyEvent.VK_Z;
    public static int intKeySoftDrop = KeyEvent.VK_DOWN;
    public static int intKeyHold = KeyEvent.VK_C;

    JPanel settingsPanel = new JPanel();
    JButton butChangeHardDrop = new JButton(KeyEvent.getKeyText(intKeyHardDrop));
    JButton butChangeMoveLeft = new JButton(KeyEvent.getKeyText(intKeyLeft));
    JButton butChangeMoveRight = new JButton(KeyEvent.getKeyText(intKeyRight));
    JButton butChangeRotateRight = new JButton(KeyEvent.getKeyText(intKeyRotateRight));
    JButton butChangeRotateLeft = new JButton(KeyEvent.getKeyText(intKeyRotateLeft));
    JButton butChangeSoftDrop = new JButton(KeyEvent.getKeyText(intKeySoftDrop));
    JButton butChangeHold = new JButton(KeyEvent.getKeyText(intKeyHold));

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == butChangeHardDrop) {
            butChangeHardDrop.setText("Please type the key you would like to change to..");
        }

    }

    public JPanel getPanel() {
        return settingsPanel;
    }

    @Override
    public void keyTyped(KeyEvent evt) {

    }

    @Override
    public void keyPressed(KeyEvent evt) {
        if (evt.getSource() == butChangeHardDrop) {
            intKeyHardDrop = evt.getKeyCode();
            System.out.println(KeyEvent.getKeyText(evt.getKeyCode()));
            butChangeHardDrop.setText(KeyEvent.getKeyText(intKeyHardDrop));
            settingsPanel.setFocusable(false);

        }
    }

    @Override
    public void keyReleased(KeyEvent evt) {

    }

    public SettingsMenu() {
        this.settingsPanel.setPreferredSize(new Dimension(GUI.FRAME_WIDTH, GUI.FRAME_HEIGHT));
        this.settingsPanel.setLayout(null);
        settingsPanel.setFocusable(true);
        //Buttons for changing keybinds
        this.butChangeHardDrop.addActionListener(this);
        this.butChangeHardDrop.addKeyListener(this);
        this.butChangeHardDrop.setBounds(5, 5, 500, 200);
        this.settingsPanel.add(butChangeHardDrop);

    }
}
