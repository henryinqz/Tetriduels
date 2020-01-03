package panels;

import javax.swing.*;

public class Tetris {
    public static boolean blnGameLoop = true; // Static boolean for game loop

    public static void main(String[] args) { // Main Method
        GUI.theframe = new JFrame("Tetris");
        Utility.setPanel(new MainMenu().getPanel());
        //Utility.setPanel(new game.Game().getPanel()); // UNCOMMENT THIS AND COMMENT ABOVE IF YOU ONLY WANT TO RUN THE GAME

        //if (blnGameLoop == true) {
        //    new game.Game();
        //}

    }
}
