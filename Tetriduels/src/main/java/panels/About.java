package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class About implements ActionListener, MouseListener {
    // PROPERTIES
    AboutPanel aboutPanel = new AboutPanel();
    JButton butMenu = new JButton("Return to Menu");
    JLabel labelGitHubLink  = new JLabel("Click Here");

    int intPurpleCheck = 0;
    Color hyperlinkBlue = new Color(51,102,187);
    Color hyperlinkPurple = new Color(70, 0, 128);
    //Include a method when connecting to the game that allows user to type in their name
    //Name will be put into JLabel showing who won, after game over.

    // METHODS
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == butMenu){
            Utility.setPanel(new MainMenu().getPanel());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent evt) {
        if(evt.getSource() == labelGitHubLink) {
            intPurpleCheck = 1;
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI("http://www.github.com/henryinqz/Tetriduels"));
                } catch (IOException e) {
                } catch (URISyntaxException e) {
                }

            }
            if(intPurpleCheck == 1) {
                this.labelGitHubLink.setForeground(hyperlinkPurple);
            }

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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

        this.labelGitHubLink.setBounds(100,275,200,50);
        Color hyperlinkBlue = new Color(51,102,187);
        this.labelGitHubLink.setForeground(hyperlinkBlue);
        Utility.setFontSize(this.labelGitHubLink,30);
        this.labelGitHubLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.aboutPanel.add(this.labelGitHubLink);
        this.labelGitHubLink.addMouseListener(this);

        this.aboutPanel.repaint();
    }

} class AboutPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Utility.loadImage(new File("assets/images/about.jpg")),0,0,null);
    }
}

