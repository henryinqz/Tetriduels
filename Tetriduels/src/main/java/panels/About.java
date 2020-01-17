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

public class About implements ActionListener {
    // PROPERTIES
    AboutPanel aboutPanel = new AboutPanel();
    JButton butMenu = new JButton("Return to Menu");
    JButton butGitHubLink  = new JButton("GitHub");

    // METHODS
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == butMenu) {
            Utility.setPanel(new MainMenu().getPanel());
        } else if (evt.getSource() == butGitHubLink) {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI("http://www.github.com/henryinqz/Tetriduels"));
                } catch (IOException e) {
                } catch (URISyntaxException e) {
                }

            }
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
        Utility.setFontSize(this.butMenu,36);
        this.butMenu.setBackground(Color.BLACK);
        this.butMenu.setForeground(Color.WHITE);
        this.butMenu.addActionListener(this);
        this.aboutPanel.add(butMenu);
        this.aboutPanel.add(butMenu);

        this.butGitHubLink.setBounds(460,30,360,50);
        this.butGitHubLink.setFont(Utility.loadFont("zorque"));
        this.butGitHubLink.setForeground(Color.WHITE);
        this.butGitHubLink.setBackground(Color.BLACK);
        Utility.setFontSize(this.butGitHubLink,35);
        this.butGitHubLink.addActionListener(this);
        this.aboutPanel.add(this.butGitHubLink);

        this.aboutPanel.repaint();
    }

} class AboutPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Utility.loadImage(new File("assets/images/about.png")),0,0,null);
    }
}

