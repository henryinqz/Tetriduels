package panels;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.image.BufferedImage;
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
        if(SettingsMenu.blnSoundCheck == true) {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(Sound));
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static BufferedImage loadImage (File Image) {
        try {
            BufferedImage loadedImage = ImageIO.read(Image);
            return loadedImage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
