package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class About implements ActionListener {
    // PROPERTIES
    AboutPanel aboutPanel = new AboutPanel(); // Create new aboutPanel JPanel object

    // Buttons
    JButton butBack = new JButton("Return to Menu");
    JButton butGitHubLink  = new JButton("GitHub");

    // METHODS
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == butBack) { // Return to menu button clicked
            Utility.setPanel(new MainMenu().getPanel()); // Open main menu
        } else if (evt.getSource() == butGitHubLink) { // Github button clicked
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI("http://www.github.com/henryinqz/Tetriduels")); // Open Tetriduels github repository in webbrowser
                } catch (IOException e) {
                } catch (URISyntaxException e) {
                }
            }
        }
    }

    public JPanel getPanel() { // Return current panel
        return aboutPanel;
    }

    // CONSTRUCTOR
    public About(){
        this.aboutPanel.setPreferredSize(new Dimension(GUI.FRAME_WIDTH,GUI.FRAME_HEIGHT));
        this.aboutPanel.setLayout(null);

        // Return to menu button
        this.butBack.setBounds(460,600,360,100);
        this.butBack.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.butBack,36);
        this.butBack.setBackground(Color.BLACK);
        this.butBack.setForeground(Color.WHITE);
        this.butBack.addActionListener(this);
        this.aboutPanel.add(butBack);

        // Github link button
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
        g.drawImage(Utility.loadImage(new File("Tetriduels/assets/images/about.png")),0,0,null); // Draw about picture
    }
}

