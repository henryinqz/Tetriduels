package panels;

import javax.swing.*;

public class Tetriduels {
    public static boolean blnGameLoop = true; // Static boolean for game loop

    public static void main(String[] args) { // Main Method
        /*try { // Consider removing since it may mess up over cross platform support
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Modern Look&Feel UI
        } catch (Exception e) {
            e.printStackTrace(); // Print where error is
        }*/

        GUI.theframe = new JFrame("Tetris");
        SettingsMenu.getControls();
        Utility.setPanel(new SplashMenu().getPanel());
        GUI.theframe.setLocationRelativeTo(null);

    }
}
