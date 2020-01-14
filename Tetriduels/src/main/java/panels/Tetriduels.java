package panels;

import javax.swing.*;

public class Tetriduels {
    public static boolean blnGameLoop = true; // Static boolean for game loop

    public static void main(String[] args) { // Main Method
        GUI.theframe = new JFrame("Tetris");
        SettingsMenu.getControls();
        Utility.setPanel(new MainMenu().getPanel());
        GUI.theframe.setLocationRelativeTo(null);
        //Utility.setPanel(new game.Game().getPanel()); // UNCOMMENT THIS AND COMMENT ABOVE IF YOU ONLY WANT TO RUN THE GAME

    }
}
