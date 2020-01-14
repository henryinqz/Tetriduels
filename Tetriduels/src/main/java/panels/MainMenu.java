package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
        } else if (evt.getSource() == butHelp) {
            Utility.setPanel(new HelpMenu().getPanel());
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
        this.mainMenuPanel.setBackground(Color.DARK_GRAY);

        // Play button
        this.butPlay.addActionListener(this);
        this.butPlay.setBounds(56,170,400,150);
        this.butPlay.setFont(Utility.loadFont());
        Utility.setFontSize(this.butPlay,40);
        this.butPlay.setBackground(Color.BLACK);
        this.butPlay.setForeground(Color.WHITE);
        this.mainMenuPanel.add(butPlay);

        // Help button
        this.butHelp.addActionListener(this);
        this.butHelp.setBounds(105,200+(5*2)+120,300,120);
        this.butHelp.setFont(Utility.loadFont());
        Utility.setFontSize(this.butHelp,40);
        this.butHelp.setBackground(Color.BLACK);
        this.butHelp.setForeground(Color.WHITE);
        this.mainMenuPanel.add(butHelp);

        // Settings button
        this.butSettings.addActionListener(this);
        this.butSettings.setBounds(105,200+(5*3)+(120*2),300,120);
        this.butSettings.setFont(Utility.loadFont());
        Utility.setFontSize(this.butSettings,40);
        this.butSettings.setBackground(Color.BLACK);
        this.butSettings.setForeground(Color.WHITE);
        this.mainMenuPanel.add(butSettings);

        // Exit button
        this.butExit.addActionListener(this);
        this.butExit.setBounds(105,200+(5*4)+(120*3),300,120);
        this.butExit.setFont(Utility.loadFont());
        Utility.setFontSize(this.butExit,40);
        this.butExit.setBackground(Color.BLACK);
        this.butExit.setForeground(Color.WHITE);
        this.mainMenuPanel.add(butExit);

    }
} class SplashMenu implements KeyListener, MouseListener {
    // PROPERTIES
    SplashMenuPanel splashMenuPanel = new SplashMenuPanel();
    JLabel labelContinue = new JLabel("Press any key to continue...");

    // METHODS
    public void mousePressed(MouseEvent evt) {
    }
    public void mouseReleased(MouseEvent evt) {
    }
    public void mouseEntered(MouseEvent evt) {
    }
    public void mouseExited(MouseEvent evt) {
    }
    public void keyTyped(KeyEvent evt) {
    }
    public void keyReleased(KeyEvent evt) {
    }
    public void keyPressed(KeyEvent evt) { // If any key is pressed, go to main menu
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Utility.setPanel(new MainMenu().getPanel()); // Go to main menu
    }
    public void mouseClicked(MouseEvent evt) {
        try {
            Thread.sleep(1000);
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

        this.labelContinue.setBounds(400,530,470,70);
        this.labelContinue.setForeground(Color.WHITE);
        this.labelContinue.setFont(Utility.loadFont());
        Utility.setFontSize(this.labelContinue, 30);
        this.splashMenuPanel.add(this.labelContinue);

        this.splashMenuPanel.setFocusable(true);
        this.splashMenuPanel.requestFocus();
        this.splashMenuPanel.addKeyListener(this);
        this.splashMenuPanel.addMouseListener(this);
        this.splashMenuPanel.repaint();
    }
} class SplashMenuPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Utility.loadImage(new File("Tetriduels/assets/images/SplashScreen.png")),0,0,null); // Draw splash screen picture
    }
}