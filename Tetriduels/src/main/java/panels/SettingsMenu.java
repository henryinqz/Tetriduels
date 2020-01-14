package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class SettingsMenu implements ActionListener, KeyListener {
    //Static variables for all keybinds
    public static int intKeyHardDrop;
    public static int intKeyLeft;
    public static int intKeyRight;
    public static int intKeyRotateRight;
    public static int intKeyRotateLeft;
    public static int intKeySoftDrop;
    public static int intKeyHold;

    int intKeyHardDropDefault = KeyEvent.VK_SPACE;
    int intKeyLeftDefault = KeyEvent.VK_LEFT;
    int intKeyRightDefault = KeyEvent.VK_RIGHT;
    int intKeyRotateRightDefault = KeyEvent.VK_UP;
    int intKeyRotateLeftDefault = KeyEvent.VK_Z;
    int intKeySoftDropDefault = KeyEvent.VK_DOWN;
    int intKeyHoldDefault = KeyEvent.VK_C;

    int intDefaultPort = 2626;
    public static int intPort = 2626;

    public static boolean blnSoundCheck = true;

    private String strBindText = "Please type the key you would like to change to";

    public static FileReader settingsFile;
    public static BufferedReader settingsFileData;

    public static FileWriter settingsOutput;
    public static PrintWriter settingsOutputData;

    JPanel settingsPanel = new JPanel();
    JButton butChangeHardDrop = new JButton(KeyEvent.getKeyText(intKeyHardDrop));
    JButton butChangeMoveLeft = new JButton(KeyEvent.getKeyText(intKeyLeft));
    JButton butChangeMoveRight = new JButton(KeyEvent.getKeyText(intKeyRight));
    JButton butChangeRotateRight = new JButton(KeyEvent.getKeyText(intKeyRotateRight));
    JButton butChangeRotateLeft = new JButton(KeyEvent.getKeyText(intKeyRotateLeft));
    JButton butChangeSoftDrop = new JButton(KeyEvent.getKeyText(intKeySoftDrop));
    JButton butChangeHold = new JButton(KeyEvent.getKeyText(intKeyHold));

    JLabel labelChangeHardDrop = new JLabel("Hard Drop");
    JLabel labelChangeMoveLeft = new JLabel("Move Left");
    JLabel labelChangeMoveRight = new JLabel("Move Right");
    JLabel labelChangeRotateRight = new JLabel("Rotate Right");
    JLabel labelChangeRotateLeft = new JLabel("Rotate Left");
    JLabel labelChangeSoftDrop = new JLabel("Soft Drop");
    JLabel labelChangeHold = new JLabel("Hold");

    JButton butBack = new JButton("Return to main menu");

    JButton butRevert = new JButton("Revert to default settings");

    public static JCheckBox soundCheck = new JCheckBox("Enable sound");

    JTextField portField = new JTextField(5);
    JLabel labelPort = new JLabel("Port Number:");
    JButton butPort = new JButton("Apply port number");


    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == butChangeHardDrop) {
            butChangeHardDrop.setText(strBindText);
        } else if (evt.getSource() == butChangeSoftDrop) {
            butChangeSoftDrop.setText(strBindText);
        } else if (evt.getSource() == butChangeMoveLeft) {
            butChangeMoveLeft.setText(strBindText);
        } else if (evt.getSource() == butChangeMoveRight) {
            butChangeMoveRight.setText(strBindText);
        } else if (evt.getSource() == butChangeRotateRight) {
            butChangeRotateRight.setText(strBindText);
        } else if (evt.getSource() == butChangeRotateLeft) {
            butChangeRotateLeft.setText(strBindText);
        } else if (evt.getSource() == butChangeHold) {
            butChangeHold.setText(strBindText);
        } else if (evt.getSource() == butBack) {
            saveControls();
            Utility.setPanel(new MainMenu().getPanel());
        } else if (evt.getSource() == butRevert) {
            intKeyHardDrop = intKeyHardDropDefault;
            intKeySoftDrop = intKeySoftDropDefault;
            intKeyLeft = intKeyLeftDefault;
            intKeyRight = intKeyRightDefault;
            intKeyRotateLeft = intKeyRotateLeftDefault;
            intKeyRotateRight = intKeyRotateRightDefault;
            intKeyHold = intKeyHoldDefault;

            butChangeHardDrop.setText(KeyEvent.getKeyText(intKeyHardDrop));
            butChangeSoftDrop.setText(KeyEvent.getKeyText(intKeySoftDrop));
            butChangeMoveLeft.setText(KeyEvent.getKeyText(intKeyLeft));
            butChangeMoveRight.setText(KeyEvent.getKeyText(intKeyRight));
            butChangeMoveRight.setText(KeyEvent.getKeyText(intKeyRight));
            butChangeRotateLeft.setText(KeyEvent.getKeyText(intKeyRotateLeft));
            butChangeRotateRight.setText(KeyEvent.getKeyText(intKeyRotateRight));
            butChangeHold.setText(KeyEvent.getKeyText(intKeyHold));

            blnSoundCheck = true;
            this.soundCheck.setSelected(true);

            intPort = intDefaultPort;
            portField.setText(String.valueOf(intDefaultPort));

            settingsPanel.requestFocus();
            saveControls();
        } else if (evt.getSource() == soundCheck) {
            blnSoundCheck = !blnSoundCheck;
            saveControls();
        } else if (evt.getSource() == butPort) {
            try {
                if (0 <= Integer.parseInt(portField.getText()) && Integer.parseInt(portField.getText()) <= 65535) {
                    intPort = Integer.parseInt(portField.getText());
                } else {
                    intPort = intDefaultPort;
                    portField.setText(String.valueOf(intDefaultPort));
                    System.out.println("Erorr: Number must be between 0 and 65535");

                }
            } catch (NumberFormatException e) {
                intPort = intDefaultPort;
                portField.setText(String.valueOf(intDefaultPort));
                System.out.println("Must be a number!");
            }
            saveControls();
        }
    }

    public JPanel getPanel() {
        return settingsPanel;

    }
    public static void getControls() {
        try {
            settingsFile = new FileReader("Tetriduels/assets/textfiles/settings.csv");

            settingsFileData = new BufferedReader(settingsFile);
            String strSplit;

            while (settingsFileData != null) {
                strSplit = settingsFileData.readLine();

                String strData[] = strSplit.split(",");
                intKeyHardDrop = Integer.parseInt(strData[0]);
                intKeySoftDrop = Integer.parseInt(strData[1]);
                intKeyLeft = Integer.parseInt(strData[2]);
                intKeyRight = Integer.parseInt(strData[3]);
                intKeyRotateLeft = Integer.parseInt(strData[4]);
                intKeyRotateRight = Integer.parseInt(strData[5]);
                intKeyHold = Integer.parseInt(strData[6]);
                intPort = Integer.parseInt(strData[7]);
                soundCheck.setSelected(Boolean.parseBoolean(strData[8]));
                blnSoundCheck = Boolean.parseBoolean(strData[8]);
                settingsFileData.close();
                settingsFile.close();

            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (NullPointerException e){
        }
    }
    public static void saveControls() {
        try {
            settingsOutput = new FileWriter("Tetriduels/assets/textfiles/settings.csv");
        }catch(IOException e) {
        }
        settingsOutputData = new PrintWriter(settingsOutput);
        settingsOutputData.println(intKeyHardDrop+","+intKeySoftDrop+","+intKeyLeft+","+intKeyRight+","+intKeyRotateLeft+","+intKeyRotateRight+","+intKeyHold+","+intPort+","+blnSoundCheck);
        settingsOutputData.close();
        try {
            settingsOutput.close();
        }catch(IOException e) {
        }
    }


    @Override
    public void keyTyped(KeyEvent evt) {
        if(portField.getText().length()>=5) {
            evt.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        if (evt.getSource() == butChangeHardDrop) {
            intKeyHardDrop = evt.getKeyCode();

            butChangeHardDrop.setText(KeyEvent.getKeyText(intKeyHardDrop));
            settingsPanel.requestFocus(); // Return focus back to the panel
            saveControls();

        } else if (evt.getSource() == butChangeSoftDrop) {
            intKeySoftDrop = evt.getKeyCode();
            butChangeSoftDrop.setText(KeyEvent.getKeyText(intKeySoftDrop));
            settingsPanel.requestFocus(); // Return focus back to the panel
            saveControls();
        } else if (evt.getSource() == butChangeMoveLeft) {
            intKeyLeft = evt.getKeyCode();
            butChangeMoveLeft.setText(KeyEvent.getKeyText(intKeyLeft));
            settingsPanel.requestFocus(); // Return focus back to the panel
        } else if (evt.getSource() == butChangeMoveRight) {
            intKeyRight = evt.getKeyCode();
            butChangeMoveRight.setText(KeyEvent.getKeyText(intKeyRight));
            settingsPanel.requestFocus(); // Return focus back to the panel
            saveControls();
        } else if (evt.getSource() == butChangeRotateLeft) {
            intKeyRotateLeft = evt.getKeyCode();
            butChangeRotateLeft.setText(KeyEvent.getKeyText(intKeyRotateLeft));
            settingsPanel.requestFocus(); // Return focus back to the panel
            saveControls();

        } else if (evt.getSource() == butChangeRotateRight) {
            intKeyRotateRight = evt.getKeyCode();
            butChangeRotateRight.setText(KeyEvent.getKeyText(intKeyRotateRight));
            settingsPanel.requestFocus(); // Return focus back to the panel
            saveControls();

        } else if (evt.getSource() == butChangeHold) {
            intKeyHold = evt.getKeyCode();
            butChangeHold.setText(KeyEvent.getKeyText(intKeyHold));
            settingsPanel.requestFocus(); // Return focus back to the panel
            saveControls();
        }
    }
    @Override
    public void keyReleased(KeyEvent evt) {

    }

    public SettingsMenu() {
        this.settingsPanel.setPreferredSize(new Dimension(GUI.FRAME_WIDTH, GUI.FRAME_HEIGHT));
        this.settingsPanel.setLayout(null);

        //Buttons for changing keybinds
        this.labelChangeHardDrop.setBounds(5,5,100,35);
        this.butChangeHardDrop.addActionListener(this);
        this.butChangeHardDrop.addKeyListener(this);
        this.butChangeHardDrop.setBounds(100, 5, 305, 35);
        this.butChangeHardDrop.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        this.settingsPanel.add(butChangeHardDrop);
        this.settingsPanel.add(labelChangeHardDrop);

        this.labelChangeSoftDrop.setBounds(5,60,100,35);
        this.butChangeSoftDrop.addActionListener(this);
        this.butChangeSoftDrop.addKeyListener(this);
        this.butChangeSoftDrop.setBounds(100, 60, 305, 35);
        this.butChangeSoftDrop.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        this.settingsPanel.add(butChangeSoftDrop);
        this.settingsPanel.add(labelChangeSoftDrop);

        this.labelChangeMoveLeft.setBounds(5,105,100,35);
        this.butChangeMoveLeft.addActionListener(this);
        this.butChangeMoveLeft.addKeyListener(this);
        this.butChangeMoveLeft.setBounds(100, 105, 305, 35);
        this.butChangeMoveLeft.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        this.settingsPanel.add(butChangeMoveLeft);
        this.settingsPanel.add(labelChangeMoveLeft);

        this.labelChangeMoveRight.setBounds(5,160,100,35);
        this.butChangeMoveRight.addActionListener(this);
        this.butChangeMoveRight.addKeyListener(this);
        this.butChangeMoveRight.setBounds(100, 160, 305, 35);
        this.butChangeMoveRight.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        this.settingsPanel.add(butChangeMoveRight);
        this.settingsPanel.add(labelChangeMoveRight);

        this.labelChangeRotateLeft.setBounds(5,205,100,35);
        this.butChangeRotateLeft.addActionListener(this);
        this.butChangeRotateLeft.addKeyListener(this);
        this.butChangeRotateLeft.setBounds(100, 205, 305, 35);
        this.butChangeRotateLeft.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        this.settingsPanel.add(butChangeRotateLeft);
        this.settingsPanel.add(labelChangeRotateLeft);

        this.labelChangeRotateRight.setBounds(5,260,100,35);
        this.butChangeRotateRight.addActionListener(this);
        this.butChangeRotateRight.addKeyListener(this);
        this.butChangeRotateRight.setBounds(100, 260, 305, 35);
        this.butChangeRotateRight.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        this.settingsPanel.add(butChangeRotateRight);
        this.settingsPanel.add(labelChangeRotateRight);

        this.labelChangeHold.setBounds(5,305,100,35);
        this.butChangeHold.addActionListener(this);
        this.butChangeHold.addKeyListener(this);
        this.butChangeHold.setBounds(100, 305, 305, 35);
        this.butChangeHold.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        this.settingsPanel.add(butChangeHold);
        this.settingsPanel.add(labelChangeHold);

        this.butBack.setBounds(500,5,200,100);
        this.butBack.addActionListener(this);
        this.settingsPanel.add(this.butBack);

        this.butRevert.setBounds(500,120,200,100);
        this.butRevert.addActionListener(this);
        this.settingsPanel.add(this.butRevert);

        this.soundCheck.setBounds(500,230,125,30);
        this.soundCheck.setSelected(blnSoundCheck);
        this.soundCheck.addActionListener(this);
        this.settingsPanel.add(this.soundCheck);

        this.portField.setBounds(580,270,55,30);
        this.portField.addKeyListener(this);
        this.portField.setText(String.valueOf(intPort));
        this.settingsPanel.add(this.portField);

        this.labelPort.setBounds(500,270,100,30);
        this.settingsPanel.add(this.labelPort);

        this.butPort.setBounds(645,270,150,30);
        this.butPort.addActionListener(this);
        this.settingsPanel.add(this.butPort);

    }
}
