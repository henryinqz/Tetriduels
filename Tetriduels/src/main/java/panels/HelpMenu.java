package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class HelpMenu implements ActionListener{
    // PROPERTIES
    HelpMenuPanel helpPanel = new HelpMenuPanel();
    JButton butBack = new JButton("Click to return");

    // METHODS
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == butBack){
            Utility.setPanel(new MainMenu().getPanel()); // Return to main menu
        }
    }
    public JPanel getPanel() {
        return helpPanel;
    }

    // CONSTRUCTOR
    public HelpMenu(){
        this.helpPanel.setPreferredSize(new Dimension(GUI.FRAME_WIDTH,GUI.FRAME_HEIGHT));
        this.helpPanel.setLayout(null);

        this.butBack.setSize(200,100);
        this.butBack.setLocation(540,600);
        this.butBack.addActionListener(this);

        this.helpPanel.add(butBack);
        this.helpPanel.repaint();
    }
} class HelpMenuPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Utility.loadImage(new File("Tetriduels/assets/images/helpscreen.png")),0,0,null); // Draw splash screen picture
    }
}
