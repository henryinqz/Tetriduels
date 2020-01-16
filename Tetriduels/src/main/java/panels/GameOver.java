package panels;

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
    String strPlayer1 = "jeremy";
    String strPlayer2 = "selwin";
    JLabel winner = new JLabel(strPlayer1 + " won the game");

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

        this.butRestart.setSize(200,100);
        this.butRestart.setLocation(700,600);
        this.butRestart.addActionListener(this);

        this.butEnd.setSize(200,100);
        this.butEnd.setLocation(350,600);
        this.butEnd.addActionListener(this);

        this.winner.setSize(200,100);
        this.winner.setLocation(100,600);

        this.overPanel.add(butRestart);
        this.overPanel.add(butEnd);
        this.overPanel.add(winner);

        this.overPanel.repaint();
    }
} class GameOverPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Utility.loadImage(new File("assets/images/GameOver.jpg")),0,0,null); // Draw splash screen picture
    }
}
