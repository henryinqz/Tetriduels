package panels;

import network.Connections;

import javax.swing.*;
import java.awt.event.*;

public class GUI extends WindowAdapter {
    // PROPERTIES
    public final static int FRAME_WIDTH = 1280, FRAME_HEIGHT = 720; // Required dimensions of game

    public static JFrame theframe; // The only JFrame in the game which all panels are switched into

    // METHODS
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
