package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HelpMenu implements ActionListener{
    JPanel HelpPanel = new JPanel();
    JButton HelpBackButton = new JButton("Click to return");

    Utility.loadImage()

    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == HelpBackButton){
            Utility.setPanel(new MainMenu().getPanel());
        }
    }

    public JPanel getPanel() {
        return HelpPanel;
    }

    public HelpMenu(){
        this.HelpPanel.setPreferredSize(new Dimension(GUI.FRAME_WIDTH,GUI.FRAME_HEIGHT));
        this.HelpPanel.setLayout(null);

        this.HelpBackButton.setSize(200,100);
        this.HelpBackButton.setLocation(540,600);
        this.HelpBackButton.addActionListener(this);

        this.HelpPanel.add(HelpBackButton);



    }

}
