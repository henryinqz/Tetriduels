package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class MainMenu implements ActionListener {
    // PROPERTIES
    MainMenuPanel mainMenuPanel = new MainMenuPanel(); // Create new mainMenuPanel JPanel object

    JLabel labelMainTitle = new JLabel("Tetriduels"); // Title bar

    // Buttons
    JButton butPlay = new JButton("Play");
    JButton butHelp = new JButton("Help");
    JButton butSettings = new JButton("Settings");
    JButton butExit = new JButton("Exit");
    JButton butAbout = new JButton("About");


    // METHODS
    public JPanel getPanel() { // Return current panel
        return mainMenuPanel;
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == butPlay) { // Play button pressed
            Utility.setPanel(new ConnectMenu().getPanel()); // Switch to connect menu panel
        } else if (evt.getSource() == butHelp) { // Help button pressed
            Utility.setPanel(new HelpMenu().getPanel()); // Switch to help menu panel
        } else if (evt.getSource() == butSettings) { // Settings button pressed
            Utility.setPanel(new SettingsMenu().getPanel()); // Switch to settings menu panel
        } else if (evt.getSource() == butAbout) { // About button pressed
            Utility.setPanel(new About().getPanel()); // Switch to about panel
        } else if (evt.getSource() == butExit) { // Exit button pressed
            System.exit(1); // Exit game
        }
    }


    // CONSTRUCTOR
    public MainMenu() {
        this.mainMenuPanel.setPreferredSize(new Dimension(GUI.FRAME_WIDTH, GUI.FRAME_HEIGHT));
        this.mainMenuPanel.setLayout(null);
        this.mainMenuPanel.setBackground(Color.DARK_GRAY);

        // Title
        this.labelMainTitle.setBounds(390, 60, 500, 150);
        this.labelMainTitle.setForeground(Color.WHITE);
        this.labelMainTitle.setFont(Utility.loadFont("fannabella"));
        Utility.setFontSize(this.labelMainTitle, 140);
        this.mainMenuPanel.add(this.labelMainTitle);

        // Play button
        this.butPlay.addActionListener(this);
        this.butPlay.setBounds(417, 205, 400, 140);
        this.butPlay.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.butPlay, 60);
        this.butPlay.setBackground(Color.BLACK);
        this.butPlay.setForeground(Color.WHITE);
        this.mainMenuPanel.add(butPlay);

        // Help button
        this.butHelp.addActionListener(this);
        this.butHelp.setBounds(417, 225 + (5 * 2) + 120, 195, 120);
        this.butHelp.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.butHelp, 30);
        this.butHelp.setBackground(Color.BLACK);
        this.butHelp.setForeground(Color.WHITE);
        this.mainMenuPanel.add(butHelp);

        // Settings button
        this.butSettings.addActionListener(this);
        this.butSettings.setBounds(622, 225 + (5 * 2) + 120, 195, 120);
        this.butSettings.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.butSettings, 30);
        this.butSettings.setBackground(Color.BLACK);
        this.butSettings.setForeground(Color.WHITE);
        this.mainMenuPanel.add(butSettings);

        //About button
        this.butAbout.addActionListener(this);
        this.butAbout.setBounds(417, 225 + (5 * 4) + (120 * 2), 195, 120);
        this.butAbout.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.butAbout, 30);
        this.butAbout.setBackground(Color.BLACK);
        this.butAbout.setForeground(Color.WHITE);
        this.mainMenuPanel.add(butAbout);

        // Exit button
        this.butExit.addActionListener(this);
        this.butExit.setBounds(622 , 225 + (5 * 4) + (120 * 2), 195, 120);
        this.butExit.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.butExit, 30);
        this.butExit.setBackground(Color.BLACK);
        this.butExit.setForeground(Color.WHITE);
        this.mainMenuPanel.add(butExit);
    }


} class MainMenuPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Utility.loadImage(new File("assets/images/MainMenuImage2.png")),40,80,null); // Draw left side board on menu
        g.drawImage(Utility.loadImage(new File("assets/images/MainMenuImage.png")),910,80,null); // Draw right side board on menu

    }

} class SplashMenu implements KeyListener, MouseListener {
    // PROPERTIES
    SplashMenuPanel splashMenuPanel = new SplashMenuPanel(); // Create new splashMenuPanel JPanel object
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
        loadMainMenu();
    }
    public void mouseClicked(MouseEvent evt) { // If mouse is pressed, go to main menu
        loadMainMenu();
    }

    public void loadMainMenu() {
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Utility.setPanel(new MainMenu().getPanel()); // Go to main menu
    }

    public JPanel getPanel() { // Return current panel
        return splashMenuPanel;
    }

    // CONSTRUCTOR
    public SplashMenu() {
        this.splashMenuPanel.setPreferredSize(new Dimension(GUI.FRAME_WIDTH,GUI.FRAME_HEIGHT));
        this.splashMenuPanel.setLayout(null);

        // Continue label
        this.labelContinue.setBounds(400,530,470,70);
        this.labelContinue.setForeground(Color.WHITE);
        this.labelContinue.setFont(Utility.loadFont("zorque"));
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
        g.drawImage(Utility.loadImage(new File("Tetriduels/assets/images/splash.png")),0,0,null); // Draw splash screen picture
    }
}