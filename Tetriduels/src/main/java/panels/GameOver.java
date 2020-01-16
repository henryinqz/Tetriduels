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
    JButton butEnd = new JButton("Quit");

    //Include a method when connecting to the game that allows user to type in their name
    //Name will be put into JLabel showing who won, after game over.

    // METHODS
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == butRestart){
            ConnectMenu.blnReady = false;
            ConnectMenu.blnEnemyReady = false;
            Utility.setPanel(new ConnectMenu().getPanel());
        }
        if(evt.getSource() == butEnd){
            System.exit(0);
        }
    }
    public JPanel getPanel() {
        return overPanel;
    }

    // CONSTRUCTOR
    public GameOver(){
        this.overPanel.setPreferredSize(new Dimension(GUI.FRAME_WIDTH,GUI.FRAME_HEIGHT));
        this.overPanel.setLayout(null);

        this.butRestart.setBounds(390,600,250,100);
        //this.butRestart.setSize(250,100);
        //this.butRestart.setLocation(615,600);
        this.butRestart.addActionListener(this);
        this.butRestart.setFont(Utility.loadFont("zorque"));
        this.butRestart.setBackground(Color.BLACK);
        this.butRestart.setForeground(Color.WHITE);
        Utility.setFontSize(butRestart,30);
        this.overPanel.add(butRestart);

        this.butEnd.setBounds(615,600,250,100);
        //this.butEnd.setSize(250,100);
        //this.butEnd.setLocation(390,600);
        this.butEnd.addActionListener(this);
        this.butEnd.setFont(Utility.loadFont("zorque"));
        this.butEnd.setBackground(Color.BLACK);
        this.butEnd.setForeground(Color.WHITE);
        Utility.setFontSize(butEnd,30);
        this.overPanel.add(butEnd);

        this.overPanel.repaint();
    }
} class GameOverPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (Game.intGameOverResult == Game.WINNER) { // Winner
            g.drawString("You won!",300,300);
        } else if (Game.intGameOverResult == Game.LOSER) { // Loser
            g.drawString("You lost!",300,300);
            //g.drawImage(Utility.loadImage(new File("assets/images/GameOver.jpg")), 0, 0, null); // Draw splash screen picture
        }
    }
}
