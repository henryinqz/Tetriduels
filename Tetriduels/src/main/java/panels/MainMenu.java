package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainMenu implements ActionListener {
    // PROPERTIES
    JPanel mainMenuPanel = new JPanel();

    JButton butPlay = new JButton("Play");
    JButton butHelp = new JButton("Help");
    JButton butSettings = new JButton("Settings");
    JButton butExit = new JButton("Exit");

    // METHODS
    public JPanel getPanel() {
        return mainMenuPanel;
    }
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == butPlay) {
            Utility.setPanel(new ConnectMenu().getPanel());
            //panels.Utility.setPanel(new game.Game().getPanel());
        } else if (evt.getSource() == butHelp) {
        } else if (evt.getSource() == butSettings) {
            Utility.setPanel(new SettingsMenu().getPanel());

        } else if (evt.getSource() == butExit) {
            System.exit(1); // Exit game
        }
    }

    // CONSTRUCTOR
    public MainMenu() {
        this.mainMenuPanel.setPreferredSize(new Dimension(GUI.FRAME_WIDTH,GUI.FRAME_HEIGHT));
        this.mainMenuPanel.setLayout(null);

        // Play button
        this.butPlay.addActionListener(this);
        this.butPlay.setBounds(5,5,400,150);
        this.mainMenuPanel.add(butPlay);

        // Help button
        this.butHelp.addActionListener(this);
        this.butHelp.setBounds(5,(5*2)+150,400,150);
        this.mainMenuPanel.add(butHelp);

        // Settings button
        this.butSettings.addActionListener(this);
        this.butSettings.setBounds(5,(5*3)+(150*2),400,150);
        this.mainMenuPanel.add(butSettings);

        // Exit button
        this.butExit.addActionListener(this);
        this.butExit.setBounds(5,(5*4)+(150*3),400,150);
        this.mainMenuPanel.add(butExit);

    }
} class SplashMenu implements KeyListener {
    // PROPERTIES
    SplashMenuPanel splashMenuPanel = new SplashMenuPanel();
    JLabel labelContinue = new JLabel("Press any key to continue...");

    // METHODS
    public void keyTyped(KeyEvent evt) {
    }
    public void keyReleased(KeyEvent evt) {
    }
    public void keyPressed(KeyEvent evt) { // If any key is pressed, go to main menu
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Utility.setPanel(new MainMenu().getPanel()); // Go to main menu
    }
    public JPanel getPanel() {
        return splashMenuPanel;
    }

    // CONSTRUCTOR
    public SplashMenu() {
        this.splashMenuPanel.setPreferredSize(new Dimension(GUI.FRAME_WIDTH,GUI.FRAME_HEIGHT));
        this.splashMenuPanel.setLayout(null);

        this.labelContinue.setBounds(450,500,200,70);
        this.labelContinue.setForeground(Color.WHITE);
        this.splashMenuPanel.add(labelContinue);

        this.splashMenuPanel.setFocusable(true);
        this.splashMenuPanel.requestFocus();
        this.splashMenuPanel.addKeyListener(this);
        this.splashMenuPanel.repaint();
    }
} class SplashMenuPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Utility.loadImage(new File("assets/images/splashscreen.png")),0,0,null); // Draw splash screen picture
    }
}