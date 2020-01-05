package panels;

import javax.swing.*;

public class Utility {
    public static void setPanel (JPanel changePanel) {
        GUI.theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI.theframe.setContentPane(changePanel);
        GUI.theframe.pack();
        //GUI.theframe.setLocationRelativeTo(null);
        GUI.theframe.setResizable(false);
        GUI.theframe.setVisible(true);
    }
}
