package panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    // PROPERTIES
    public final static int MAIN_MENU = 0, HELP = 1, SETTINGS = 2, CONNECT = 3, GAME = 4;
    public final static int FRAME_WIDTH = 1280, FRAME_HEIGHT = 720;

    public static JFrame theframe;

    // METHODS
    public void actionPerformed(ActionEvent e) {
    }

    // CONSTRUCTORS
    public GUI() {
        new MainMenu();
    }



}
