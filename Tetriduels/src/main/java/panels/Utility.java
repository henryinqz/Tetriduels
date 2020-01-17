package panels;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Utility {
    // METHODS
    public static void setPanel (JPanel changePanel) { // Changes panel within GUI.theframe
        GUI.theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close game when window is closed
        GUI.theframe.setContentPane(changePanel); // Set panel to specified panel
        GUI.theframe.pack(); // Change frame size if needed to allow for panel
        GUI.theframe.setResizable(false); // Disable resizing frame/window
        GUI.theframe.setVisible(true); // Set visible
    }
    public static void playSound (File Sound) { // Play sound clip
        if(SettingsMenu.blnEnableSound == true) { // If sound is enabled in Settings
            try {
                Clip clip = AudioSystem.getClip(); // Create soundclip object
                clip.open(AudioSystem.getAudioInputStream(Sound)); // Open soundclip from file specified
                clip.start(); // Play soundclip
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static BufferedImage loadImage (File Image) { // Load image into BufferedImage
        try { // Try to load image. If not possible, return null
            BufferedImage loadedImage = ImageIO.read(Image);
            return loadedImage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Font loadFont(String strFontName) { // Load font into Font
        Font font = null; // initialize font object
        try {
            font = Font.createFont(Font.PLAIN, new File("Tetriduels/assets/font/"+strFontName+".ttf")); // Try to load font object w/ specified file path
        } catch (Exception e) {
            e.printStackTrace();
        }
        return font;
    }

    // Font Size Methods
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
    public static void setFontSize (Graphics2D g2, int intSize) { // Graphics2D font size
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,intSize));
    }
}
