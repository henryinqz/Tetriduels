package panels;
import javax.swing.*;

/* -----------------------------------------------
 *
 * 	Program Name: Tetriduels
 *	Creators: Henry Wong, Jeremy Selwin, Zuhair Siddiqi
 *
 *	Version: 1.0
 *	January 16, 2020
 *
 *	Course Code: ICS4U1
 *	Mr. Cadawas
 *
 * ------------------------------------------------ */

public class Tetriduels {
    public static boolean blnGameLoop = true; // Static boolean for game loop

    public static void main(String[] args) { // Main Method
        /*try { // Consider removing since it may mess up over cross platform support
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Modern Look&Feel UI
        } catch (Exception e) {
            e.printStackTrace(); // Print where error is
        }*/
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); // Fixes Mac devices showing native JComponent styles (and making text not visible)
        } catch (Exception e) {
            e.printStackTrace();
        }

        GUI.theframe = new JFrame("Tetriduels");
        SettingsMenu.getControls();
        Utility.setPanel(new SplashMenu().getPanel());
        GUI.theframe.setLocationRelativeTo(null);

    }
}
