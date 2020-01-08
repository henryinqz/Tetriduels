package panels;

import game.Sound;

import javax.swing.*;
import java.io.File;

public class Tetris {
    public static boolean blnGameLoop = true; // Static boolean for game loop

    public static void main(String[] args) { // Main Method
        GUI.theframe = new JFrame("Tetris");
        Utility.setPanel(new MainMenu().getPanel());
        GUI.theframe.setLocationRelativeTo(null);
        //Utility.setPanel(new game.Game().getPanel()); // UNCOMMENT THIS AND COMMENT ABOVE IF YOU ONLY WANT TO RUN THE GAME

    }
}
