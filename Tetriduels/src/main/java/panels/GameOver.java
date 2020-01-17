package panels;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GameOver implements ActionListener{
    // PROPERTIES
    GameOverPanel overPanel = new GameOverPanel();
    JButton butRestart = new JButton("Play Again");
    JButton butBack = new JButton("Return to menu");

    // METHODS
    public void actionPerformed(ActionEvent evt){
        if (evt.getSource() == butRestart){
            ConnectMenu.blnReady = false;
            ConnectMenu.blnEnemyReady = false;
            Utility.setPanel(new ConnectMenu().getPanel());
        }
        if (evt.getSource() == butBack){ // Return to main menu
            Utility.setPanel(new MainMenu().getPanel());
        }
    }
    public JPanel getPanel() {
        return overPanel;
    }

    // CONSTRUCTOR
    public GameOver(){
        this.overPanel.setPreferredSize(new Dimension(GUI.FRAME_WIDTH,GUI.FRAME_HEIGHT));
        this.overPanel.setLayout(null);

        // Play again (go back to connectmenu)
        this.butRestart.setBounds(640-50-360,450,360,100);
        this.butRestart.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(butRestart,35);
        this.butRestart.setBackground(Color.DARK_GRAY);
        this.butRestart.setForeground(Color.WHITE);
        this.butRestart.addActionListener(this);
        this.overPanel.add(butRestart);

        // Return to main menu
        this.butBack.setBounds(640+50,450,360,100);
        this.butBack.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.butBack,35);
        this.butBack.setBackground(Color.DARK_GRAY);
        this.butBack.setForeground(Color.WHITE);
        this.butBack.addActionListener(this);
        this.overPanel.add(this.butBack);

        this.overPanel.repaint();
    }
} class GameOverPanel extends JPanel {
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g; // Use Graphics2D instead of regular Graphics
        super.paintComponent(g2); // Clear previous drawings (Windows only); super JPanel (original) paintComponent method

        g2.drawImage(Utility.loadImage(new File("Tetriduels/assets/images/blank.png")), 0, 0, null); // Draw splash screen picture
        g2.setColor(Color.WHITE);
        g2.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(g2,90);
        if (Game.intGameOverResult == Game.WINNER) { // Winner
            g.drawString("You won!",418,300);
        } else if (Game.intGameOverResult == Game.LOSER) { // Loser
            g.drawString("You lost!",418,300);
        }
    }
}
