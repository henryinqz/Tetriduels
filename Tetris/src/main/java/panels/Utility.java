package panels;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class Utility {
    public static void setPanel (JPanel changePanel) {
        GUI.theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI.theframe.setContentPane(changePanel);
        GUI.theframe.pack();
        //GUI.theframe.setLocationRelativeTo(null);
        GUI.theframe.setResizable(false);
        GUI.theframe.setVisible(true);
    }
    public static void playSound (File Sound) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
