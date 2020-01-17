package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class SettingsMenu implements ActionListener, KeyListener {
    // PROPERTIES
    //Static variables for all keybinds
    public static int intKeyHardDrop;
    public static int intKeyLeft;
    public static int intKeyRight;
    public static int intKeyRotateRight;
    public static int intKeyRotateLeft;
    public static int intKeyDown;
    public static int intKeyHold;
    public static int intKeyChat;

    int intKeyHardDropDefault = KeyEvent.VK_SPACE;
    int intKeyLeftDefault = KeyEvent.VK_LEFT;
    int intKeyRightDefault = KeyEvent.VK_RIGHT;
    int intKeyRotateRightDefault = KeyEvent.VK_UP;
    int intKeyRotateLeftDefault = KeyEvent.VK_Z;
    int intKeyDownDefault = KeyEvent.VK_DOWN;
    int intKeyHoldDefault = KeyEvent.VK_C;
    int intKeyChatDefault = KeyEvent.VK_T;

    int intDefaultPort = 2626;
    public static int intPort = 2626;

    public static boolean blnEnableSound = true;

    private String strBindText = "Enter new key";

    public static FileReader settingsFile;
    public static BufferedReader settingsFileData;

    public static FileWriter settingsOutput;
    public static PrintWriter settingsOutputData;

    SettingsMenuPanel settingsPanel = new SettingsMenuPanel();
    JLabel labelSettingsTitle = new JLabel("Settings");
    JButton butChangeHardDrop = new JButton(KeyEvent.getKeyText(intKeyHardDrop));
    JButton butChangeMoveLeft = new JButton(KeyEvent.getKeyText(intKeyLeft));
    JButton butChangeMoveRight = new JButton(KeyEvent.getKeyText(intKeyRight));
    JButton butChangeRotateRight = new JButton(KeyEvent.getKeyText(intKeyRotateRight));
    JButton butChangeRotateLeft = new JButton(KeyEvent.getKeyText(intKeyRotateLeft));
    JButton butChangeMoveDown = new JButton(KeyEvent.getKeyText(intKeyDown));
    JButton butChangeHold = new JButton(KeyEvent.getKeyText(intKeyHold));
    JButton butChangeChatKey = new JButton(KeyEvent.getKeyText(intKeyChat));

    JLabel labelChangeHardDrop = new JLabel("Hard Drop:");
    JLabel labelChangeMoveLeft = new JLabel("Move Left:");
    JLabel labelChangeMoveRight = new JLabel("Move Right:");
    JLabel labelChangeRotateRight = new JLabel("Rotate Right:");
    JLabel labelChangeRotateLeft = new JLabel("Rotate Left:");
    JLabel labelChangeMoveDown = new JLabel("Move Down:");
    JLabel labelChangeHold = new JLabel("Hold block");
    JLabel labelChangeChatKey = new JLabel("Open Chat:");

    JButton butBack = new JButton("Return to menu");
    JButton butReset = new JButton("Reset all to default");

    public static JCheckBox boxSound = new JCheckBox("Enable sound effects");

    JTextField fieldPort = new JTextField(6);
    JLabel labelPort = new JLabel("Port Number:");
    JButton butPort = new JButton("Apply");
    JLabel labelPortError = new JLabel("Enter a valid port between 0-65535!", SwingConstants.CENTER);

    // METHODS
    public JPanel getPanel() {
        return settingsPanel;
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == butChangeHardDrop) {
            butChangeHardDrop.setText(strBindText);
        } else if (evt.getSource() == butChangeMoveDown) {
            butChangeMoveDown.setText(strBindText);
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
        } else if (evt.getSource() == butChangeChatKey) {
            butChangeChatKey.setText(strBindText);
        } else if (evt.getSource() == butBack) {
            saveControls();
            Utility.setPanel(new MainMenu().getPanel());
        } else if (evt.getSource() == butReset) {
            intKeyHardDrop = intKeyHardDropDefault;
            intKeyDown = intKeyDownDefault;
            intKeyLeft = intKeyLeftDefault;
            intKeyRight = intKeyRightDefault;
            intKeyRotateLeft = intKeyRotateLeftDefault;
            intKeyRotateRight = intKeyRotateRightDefault;
            intKeyHold = intKeyHoldDefault;
            intKeyChat = intKeyChatDefault;

            butChangeHardDrop.setText(KeyEvent.getKeyText(intKeyHardDrop));
            butChangeMoveDown.setText(KeyEvent.getKeyText(intKeyDown));
            butChangeMoveLeft.setText(KeyEvent.getKeyText(intKeyLeft));
            butChangeMoveRight.setText(KeyEvent.getKeyText(intKeyRight));
            butChangeMoveRight.setText(KeyEvent.getKeyText(intKeyRight));
            butChangeRotateLeft.setText(KeyEvent.getKeyText(intKeyRotateLeft));
            butChangeRotateRight.setText(KeyEvent.getKeyText(intKeyRotateRight));
            butChangeHold.setText(KeyEvent.getKeyText(intKeyHold));
            butChangeChatKey.setText(KeyEvent.getKeyText(intKeyChat));

            blnEnableSound = true;
            this.boxSound.setSelected(true);

            intPort = intDefaultPort;
            fieldPort.setText(String.valueOf(intDefaultPort));

            settingsPanel.requestFocus();
            saveControls();
        } else if (evt.getSource() == boxSound) {
            blnEnableSound = !blnEnableSound;
            saveControls();
        } else if (evt.getSource() == butPort) {
            try {
                if (0 <= Integer.parseInt(fieldPort.getText()) && Integer.parseInt(fieldPort.getText()) <= 65535) {
                    intPort = Integer.parseInt(fieldPort.getText());
                    labelPortError.setVisible(false);
                } else {
                    labelPortError.setVisible(true);
                }
            } catch (NumberFormatException e) { // Port is blank or user entered in letters
                labelPortError.setVisible(true);
            }
            saveControls();
        }
    }
    public static void getControls() {
        try {
            settingsFile = new FileReader("assets/textfiles/settings.csv");

            settingsFileData = new BufferedReader(settingsFile);
            String strSplit;

            while (settingsFileData != null) {
                strSplit = settingsFileData.readLine();
                String strData[] = strSplit.split(",");

                intKeyHardDrop = Integer.parseInt(strData[0]);
                intKeyDown = Integer.parseInt(strData[1]);
                intKeyLeft = Integer.parseInt(strData[2]);
                intKeyRight = Integer.parseInt(strData[3]);
                intKeyRotateLeft = Integer.parseInt(strData[4]);
                intKeyRotateRight = Integer.parseInt(strData[5]);
                intKeyHold = Integer.parseInt(strData[6]);
                intKeyChat = Integer.parseInt(strData[7]);
                intPort = Integer.parseInt(strData[8]);
                boxSound.setSelected(Boolean.parseBoolean(strData[9]));
                blnEnableSound = Boolean.parseBoolean(strData[9]);
                settingsFileData.close();
                settingsFile.close();
            }
        } catch (Exception e) { // Catch exception (the code throws an IO exception when the file closes and it tries to loop again)
            //e.printStackTrace();
        }
    }
    public static void saveControls() {
        try {
            settingsOutput = new FileWriter("assets/textfiles/settings.csv");
        } catch(IOException e) {
            e.printStackTrace();
        }
        settingsOutputData = new PrintWriter(settingsOutput);
        settingsOutputData.println(intKeyHardDrop+","+ intKeyDown +","+intKeyLeft+","+intKeyRight+","+intKeyRotateLeft+","+intKeyRotateRight+","+intKeyHold+","+intKeyChat+","+intPort+","+blnEnableSound);
        settingsOutputData.close();
        try {
            settingsOutput.close();
        } catch(IOException e) {
        }
    }

    public void keyTyped(KeyEvent evt) {
        if(fieldPort.getText().length()>=5) {
            evt.consume();
        }
    }

    public void keyPressed(KeyEvent evt) {
        if (evt.getSource() == butChangeHardDrop) {
            intKeyHardDrop = evt.getKeyCode();

            butChangeHardDrop.setText(KeyEvent.getKeyText(intKeyHardDrop));
            settingsPanel.requestFocus(); // Return focus back to the panel
            saveControls();

        } else if (evt.getSource() == butChangeMoveDown) {
            intKeyDown = evt.getKeyCode();
            butChangeMoveDown.setText(KeyEvent.getKeyText(intKeyDown));
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
        } else if (evt.getSource() == butChangeChatKey) {
            intKeyChat = evt.getKeyCode();
            butChangeChatKey.setText(KeyEvent.getKeyText(intKeyChat));
            settingsPanel.requestFocus();
            saveControls();
        }
    }

    public void formatKeyBinds(JLabel labelFormat, JButton butFormat, int intXPos, int intYPos) { // Method to format key bind label/button
        labelFormat.setForeground(Color.BLACK);
        labelFormat.setBounds(intXPos, intYPos,230,35);
        labelFormat.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(labelFormat, 30);

        butFormat.addActionListener(this);
        butFormat.addKeyListener(this);
        butFormat.setBounds(intXPos + 235, intYPos-10, 200, 60);
        butFormat.setBackground(Color.DARK_GRAY);
        butFormat.setForeground(Color.WHITE);
        butFormat.setFont(Utility.loadFont("fannabella"));
        Utility.setFontSize(butFormat,35);
        butFormat.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none"); // Disable space bar from activating JButtons
    }

    public void keyReleased(KeyEvent evt) {
    }

    // CONSTRUCTOR
    public SettingsMenu() {
        this.settingsPanel.setPreferredSize(new Dimension(GUI.FRAME_WIDTH, GUI.FRAME_HEIGHT));
        this.settingsPanel.setLayout(null);
        this.settingsPanel.setBackground(Color.LIGHT_GRAY);

        // Title
        this.labelSettingsTitle.setBounds(515,2,250,100);
        this.labelSettingsTitle.setForeground(Color.BLACK);
        this.labelSettingsTitle.setFont(Utility.loadFont("fannabella"));
        Utility.setFontSize(this.labelSettingsTitle, 80);
        this.settingsPanel.add(this.labelSettingsTitle);

        //Buttons for changing keybinds
        // Hard Drop
        formatKeyBinds(this.labelChangeHardDrop,this.butChangeHardDrop,105,125);
        this.settingsPanel.add(butChangeHardDrop);
        this.settingsPanel.add(labelChangeHardDrop);

        // Move Down
        formatKeyBinds(this.labelChangeMoveDown,this.butChangeMoveDown,105,125+65);
        this.settingsPanel.add(butChangeMoveDown);
        this.settingsPanel.add(labelChangeMoveDown);

        // Move Left
        formatKeyBinds(this.labelChangeMoveLeft,this.butChangeMoveLeft,105,125+(65*2));
        this.settingsPanel.add(butChangeMoveLeft);
        this.settingsPanel.add(labelChangeMoveLeft);

        // Move Right
        formatKeyBinds(this.labelChangeMoveRight,this.butChangeMoveRight,105,125+(65*3));
        this.settingsPanel.add(butChangeMoveRight);
        this.settingsPanel.add(labelChangeMoveRight);

        // Rotate left
        formatKeyBinds(this.labelChangeRotateLeft,this.butChangeRotateLeft,105,125+(65*4));
        this.settingsPanel.add(butChangeRotateLeft);
        this.settingsPanel.add(labelChangeRotateLeft);

        // Rotate right
        formatKeyBinds(this.labelChangeRotateRight,this.butChangeRotateRight,105,125+(65*5));
        this.settingsPanel.add(butChangeRotateRight);
        this.settingsPanel.add(labelChangeRotateRight);

        // Hold block
        formatKeyBinds(this.labelChangeHold,this.butChangeHold,105,125+(65*6));
        this.settingsPanel.add(butChangeHold);
        this.settingsPanel.add(labelChangeHold);

        //Chat
        formatKeyBinds(this.labelChangeChatKey,this.butChangeChatKey,740,120);
        this.settingsPanel.add(butChangeChatKey);
        this.settingsPanel.add(labelChangeChatKey);

        // Return to main menu
        this.butBack.setBounds(460,600,360,100);
        this.butBack.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.butBack,35);
        this.butBack.setBackground(Color.BLACK);
        this.butBack.setForeground(Color.WHITE);
        this.butBack.addActionListener(this);
        this.settingsPanel.add(this.butBack);

        // Revert to default settings
        this.butReset.setBounds(735,510,450,60);
        this.butReset.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.butReset,30);
        this.butReset.setBackground(Color.DARK_GRAY);
        this.butReset.setForeground(Color.WHITE);
        this.butReset.addActionListener(this);
        this.settingsPanel.add(this.butReset);

        // Enable/disable sound
        this.boxSound.setBounds(790,430,340,30);
        this.boxSound.setSelected(blnEnableSound);
        this.boxSound.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(boxSound,25);
        this.boxSound.setBackground(Color.GRAY);
        this.boxSound.setForeground(Color.BLACK);
        this.boxSound.addActionListener(this);
        this.settingsPanel.add(this.boxSound);

        // Port number
        this.labelPort.setBounds(740,190,230,40);
        this.labelPort.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.labelPort,30);
        this.labelPort.setForeground(Color.BLACK);
        this.settingsPanel.add(this.labelPort);

        this.fieldPort.setBounds(975,190,55,40);
        this.fieldPort.setFont(Utility.loadFont("fannabella"));
        Utility.setFontSize(this.fieldPort,20);
        this.fieldPort.addKeyListener(this);
        this.fieldPort.setText(String.valueOf(intPort));
        this.settingsPanel.add(this.fieldPort);

        this.butPort.setBounds(1040,190,100,40);
        this.butPort.setFont(Utility.loadFont("fannabella"));
        Utility.setFontSize(this.butPort,30);
        this.butPort.setBackground(Color.DARK_GRAY);
        this.butPort.setForeground(Color.WHITE);
        this.butPort.addActionListener(this);
        this.settingsPanel.add(this.butPort);

        this.labelPortError.setBounds(725,240,470,40);
        this.labelPortError.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.labelPortError,20);
        this.labelPortError.setForeground(new Color(153,0,0)); // Dark red
        this.settingsPanel.add(this.labelPortError);
        this.labelPortError.setVisible(false);

        this.settingsPanel.repaint(); // Draw background rectangles
    }
} class SettingsMenuPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY); // Rectangles
        g.fillRect(85,100,470,480); // Left rectangle
        g.fillRect(85+640,100,470,480); // Right rectangle

        g.setColor(Color.BLACK); // Outlines
        g.drawRect(85,100,470,480); // Left rectangle outline
        g.drawRect(85+640,100,470,480); // Right rectangle outline
    }
}
