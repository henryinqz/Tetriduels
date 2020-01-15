package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class About implements ActionListener{
    // PROPERTIES
    AboutPanel aboutPanel = new AboutPanel();
    JButton butMenu = new JButton("Main Menu");

    //Include a method when connecting to the game that allows user to type in their name
    //Name will be put into JLabel showing who won, after game over.

    // METHODS
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == butMenu){
            Utility.setPanel(new MainMenu().getPanel());
        }
    }
    public JPanel getPanel() {
        return aboutPanel;
    }

    // CONSTRUCTOR
    public About(){
        this.aboutPanel.setPreferredSize(new Dimension(GUI.FRAME_WIDTH,GUI.FRAME_HEIGHT));
        this.aboutPanel.setLayout(null);

        this.butMenu.setBounds(460,600,360,100);
        this.butMenu.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.butMenu,35);
        this.butMenu.setBackground(Color.BLACK);
        this.butMenu.setForeground(Color.WHITE);
        this.butMenu.addActionListener(this);
        this.aboutPanel.add(butMenu);
        this.aboutPanel.add(butMenu);

        this.aboutPanel.repaint();
    }
} class AboutPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Utility.loadImage(new File("Tetriduels/assets/images/about.png")),0,0,null); // Draw splash screen picture
    }
}

