package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
}
