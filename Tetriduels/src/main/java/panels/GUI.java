package panels;

import network.Connections;

import javax.swing.*;
import java.awt.event.*;

public class GUI extends WindowAdapter {
    // PROPERTIES
    public final static int MAIN_MENU = 0, HELP = 1, SETTINGS = 2, CONNECT = 3, GAME = 4;
    public final static int FRAME_WIDTH = 1280, FRAME_HEIGHT = 720;

    public static JFrame theframe;

    // METHODS
    public void windowList() {
        theframe.addWindowListener(this);
    }

    public void windowClosing(WindowEvent e) { // User closes window, disconnect from servers if they are connected
        System.out.println("Closing window...");
        if (Connections.ssm != null) {
            Connections.disconnect();
        }
    }

    // CONSTRUCTORS
    public GUI() {
    }

}
