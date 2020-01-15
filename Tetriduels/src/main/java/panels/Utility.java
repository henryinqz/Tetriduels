package panels;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Utility {
    public static void setPanel (JPanel changePanel) {
        GUI.theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI.theframe.setContentPane(changePanel);
        GUI.theframe.pack();
        GUI.theframe.setResizable(false);
        GUI.theframe.setVisible(true);
    }
    public static void playSound (File Sound) {
        if(SettingsMenu.blnEnableSound == true) {
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
    public static Font loadFont(String strFontName) {
        Font font = null;
        try {
            font = Font.createFont(Font.PLAIN, new File("Tetriduels/assets/font/"+strFontName+".ttf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return font;
    }
    public static void setFontSize (JLabel labelFont, int intSize) { // JLabel font size
        labelFont.setFont(labelFont.getFont().deriveFont(Font.PLAIN,intSize));
    }
    public static void setFontSize (JButton butFont, int intSize) { // JButton font size
        butFont.setFont(butFont.getFont().deriveFont(Font.PLAIN,intSize));
    }
    public static void setFontSize (JCheckBox boxFont, int intSize) { // JButton font size
        boxFont.setFont(boxFont.getFont().deriveFont(Font.PLAIN,intSize));
    }
    public static void setFontSize (JTextField fieldFont, int intSize) { // JButton font size
        fieldFont.setFont(fieldFont.getFont().deriveFont(Font.PLAIN,intSize));
    }
}
