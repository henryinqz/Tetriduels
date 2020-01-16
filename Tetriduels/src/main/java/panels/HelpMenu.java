package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class HelpMenu implements ActionListener{
    // PROPERTIES
    HelpMenuPanel helpPanel = new HelpMenuPanel();
    JButton butBack = new JButton("Return to menu");

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

        // Back button
        this.butBack.setBounds(460,600,360,100);
        this.butBack.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.butBack,35);
        this.butBack.setBackground(Color.BLACK);
        this.butBack.setForeground(Color.WHITE);
        this.butBack.addActionListener(this);
        this.helpPanel.add(butBack);

        this.helpPanel.repaint();
    }
} class HelpMenuPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Utility.loadImage(new File("assets/images/helpscreen.png")),0,0,null); // Draw splash screen picture
    }
}
