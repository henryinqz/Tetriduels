package panels;

import network.*;
import game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConnectMenu implements ActionListener, KeyListener {
    // PROPERTIES
    ConnectMenuPanel connectPanel = new ConnectMenuPanel();

    JLabel labelConnectTitle = new JLabel("Multiplayer");

    JButton butHost = new JButton("Host game");
    JButton butClient = new JButton("Join game");
    JButton butBack = new JButton("Return to menu");

    JLabel labelServerIP = new JLabel("Server IP:");
    JLabel labelPort = new JLabel("Port number:");
    JTextField fieldServerIP = new JTextField();
    JTextField fieldPort = new JTextField();
    JButton butConnect = new JButton("Connect");
    JLabel labelError = new JLabel("",SwingConstants.CENTER);
    JLabel labelReady = new JLabel("Ready up once all players have connected.",SwingConstants.CENTER);

    public int intPort;

    JButton butReady = new JButton("Ready");
    public static boolean blnReady = false;
    public static boolean blnEnemyReady = false;

    // Chat
    public static JTextArea areaChat = new JTextArea();
    public static JScrollPane scrollChat = new JScrollPane(areaChat);
    public static JTextField fieldChatMessage = new JTextField();
    public static JButton butChatMessageSend = new JButton("Send");

    // METHODS
    public JPanel getPanel() {
        return connectPanel;
    }
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == butHost) {
            labelError.setVisible(false);
            if (Connections.ssm != null) {
                Connections.ssm.disconnect();
                Connections.ssm = null;
                areaChat.append("> Disconnected from server.\n"); // Show that server is offline in chat
            }
            new Connections(SettingsMenu.intPort); // Server; create SuperSocketMaster object
            areaChat.append("> Server is now online.\n"); // Show that server is online in chat
            fieldServerIP.setEditable(false);
            fieldServerIP.setText(Connections.ssm.getMyAddress());
            fieldPort.setEditable(false);
            fieldPort.setText(SettingsMenu.intPort + "");
            butConnect.setEnabled(false);
            butReady.setVisible(true);
            butReady.setEnabled(true);
            labelReady.setVisible(true);

            fieldChatMessage.setEnabled(true);
            butChatMessageSend.setEnabled(true);
        } else if (evt.getSource() == butClient) {
            labelError.setVisible(false);
            butReady.setVisible(false);
            labelReady.setVisible(false);
            if (Connections.ssm != null) {
                Connections.ssm.disconnect();
                Connections.ssm = null;
                areaChat.append("> Disconnected from server.\n"); // Show that server is offline in chat
            }
            fieldServerIP.setEditable(true);
            fieldServerIP.setText("");
            fieldPort.setEditable(true);
            fieldPort.setText("");
            butConnect.setEnabled(true);
            fieldChatMessage.setEnabled(false);
            butChatMessageSend.setEnabled(false);

        } else if (evt.getSource() == butConnect) { // Client connection
            if (!fieldServerIP.getText().equals("") && !fieldPort.getText().equals("")) { // Check if server IP & port are blank
                String strServerIP = fieldServerIP.getText();
                try {
                    intPort = Integer.parseInt(fieldPort.getText());
                } catch (NumberFormatException e) { // User did not input numbers
                    intPort = -1;
                }

                if (intPort >= 0 && intPort <= 65535) { // Check if port entered is within range of all ports
                    new Connections(strServerIP, intPort); // Client; create SuperSocketMaster object
                    if (Connections.blnConnected == true) { // Connected to server
                        areaChat.append("> Connected to server.\n"); // Show that server is online in chat
                        labelError.setVisible(false);
                        butConnect.setEnabled(false);
                        butReady.setVisible(true);
                        butReady.setEnabled(true);
                        labelReady.setVisible(true);
                        fieldChatMessage.setEnabled(true);
                        butChatMessageSend.setEnabled(true);
                    } else { // Failed to connect to server
                        butConnect.setEnabled(true);
                        labelError.setText("Could not connect to server.");
                        labelError.setVisible(true);
                    }
                } else { // Port out of range
                    labelError.setText("Enter valid port from 0-65535!");
                    labelError.setVisible(true);
                    butReady.setVisible(false);
                    labelReady.setVisible(false);
                }
            } else { // Blank server IP or port
                labelError.setText("Enter a valid IP and port!");
                labelError.setVisible(true);
                butReady.setVisible(false);
                labelReady.setVisible(false);
            }
        } else if (evt.getSource() == butBack) {
            Utility.setPanel(new MainMenu().getPanel());
        } else if (evt.getSource() == butReady) {
            butReady.setEnabled(false);
            blnReady = true;
            Tetriduels.blnGameLoop = true;
            Connections.sendMessage(Connections.READY);
        }

        // Chat
        if(evt.getSource() == butChatMessageSend) { // Send text over network
            Connections.sendMessage(Connections.CHAT_MESSAGE, fieldChatMessage.getText());
            areaChat.append("<You>: " + fieldChatMessage.getText() + "\n"); // Add player chat message to chat
            fieldChatMessage.setText(""); // Clear send message box
            Game.blnChatOpen = false; // If user sends message in game, close chat
        }
        JScrollBar scrollBarChat = scrollChat.getVerticalScrollBar(); // Always scroll bottom
        scrollBarChat.setValue(scrollBarChat.getMaximum());

    }

    public void keyReleased(KeyEvent evt) {
    }
    public void keyPressed(KeyEvent evt) {
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!fieldChatMessage.getText().equals("")) {
                butChatMessageSend.doClick();
            }
        }
    }
    public void keyTyped(KeyEvent evt) {
    }

    // CONSTRUCTOR
    public ConnectMenu() {
        this.connectPanel.setPreferredSize(new Dimension(GUI.FRAME_WIDTH,GUI.FRAME_HEIGHT));
        this.connectPanel.setLayout(null);
        this.connectPanel.setBackground(Color.LIGHT_GRAY);

        // Title
        this.labelConnectTitle.setBounds(488,2,304,100);
        this.labelConnectTitle.setForeground(Color.BLACK);
        this.labelConnectTitle.setFont(Utility.loadFont("fannabella"));
        Utility.setFontSize(this.labelConnectTitle, 80);
        this.connectPanel.add(this.labelConnectTitle);

        // (Server) Host button
        this.butHost.addActionListener(this);
        this.butHost.setBounds(30,110,380,150);
        this.butHost.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(butHost,40);
        this.butHost.setBackground(Color.DARK_GRAY);
        this.butHost.setForeground(Color.WHITE);
        this.connectPanel.add(butHost);

        // (Client) Join game button
        this.butClient.addActionListener(this);
        this.butClient.setBounds(30,270,380,150);
        this.butClient.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(butClient,40);
        this.butClient.setBackground(Color.DARK_GRAY);
        this.butClient.setForeground(Color.WHITE);
        this.connectPanel.add(butClient);

        // Return to main menu
        this.butBack.setBounds(460,600,360,100);
        this.butBack.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.butBack,35);
        this.butBack.setBackground(Color.BLACK);
        this.butBack.setForeground(Color.WHITE);
        this.butBack.addActionListener(this);
        this.connectPanel.add(this.butBack);

        // ServerIP
        this.labelServerIP.setBounds(465,140,160,40);
        this.labelServerIP.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.labelServerIP,30);
        this.labelServerIP.setForeground(Color.BLACK);
        this.connectPanel.add(labelServerIP);

        this.fieldServerIP.setEditable(false);
        this.fieldServerIP.setBounds(465+165,140,175,40);
        this.fieldServerIP.setFont(Utility.loadFont("fannabella"));
        Utility.setFontSize(this.fieldServerIP,20);
        this.connectPanel.add(fieldServerIP);

        // Port
        this.labelPort.setBounds(465,190,230,40);
        this.labelPort.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.labelPort,30);
        this.labelPort.setForeground(Color.BLACK);
        this.connectPanel.add(labelPort);

        this.fieldPort.setEditable(false);
        this.fieldPort.setBounds(465+235,190,105,40);
        this.fieldPort.setFont(Utility.loadFont("fannabella"));
        Utility.setFontSize(this.fieldPort,20);
        this.connectPanel.add(fieldPort);

        // (Client) connect button
        this.butConnect.addActionListener(this);
        this.butConnect.setEnabled(false);
        this.butConnect.setBounds(560,290,160,60);
        this.butConnect.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(butConnect,25);
        this.butConnect.setBackground(Color.DARK_GRAY);
        this.butConnect.setForeground(Color.WHITE);
        this.connectPanel.add(butConnect);

        // Initialize error label
        this.labelError.setBounds(445,240,390,40);
        this.labelError.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.labelError,20);
        this.labelError.setForeground(new Color(153,0,0)); // Dark red
        this.connectPanel.add(this.labelError);
        this.labelError.setVisible(false);

        // Initialize ready button
        this.butReady.addActionListener(this);
        this.butReady.setBounds(550,490,180,80);
        this.butReady.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(butReady,30);
        this.butReady.setBackground(new Color(0,180,0));
        this.butReady.setForeground(Color.WHITE);
        this.butReady.setVisible(false);
        this.connectPanel.add(butReady);

        // Ready info label
        this.labelReady.setBounds(445,450,390,40);
        this.labelReady.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(this.labelReady,15);
        this.labelReady.setForeground(Color.BLACK);
        this.labelReady.setVisible(false);
        this.connectPanel.add(this.labelReady);

        // Chat
        this.areaChat.setEditable(false);
        this.areaChat.setLineWrap(true);
        this.scrollChat.setBounds(60+(400*2)+10,110,380,410);
        this.connectPanel.add(scrollChat);

        this.fieldChatMessage.setEnabled(false);
        this.fieldChatMessage.setBounds(60+(400*2)+10,520,300,50);
        this.fieldChatMessage.addKeyListener(this);
        this.connectPanel.add(fieldChatMessage);

        this.butChatMessageSend.addActionListener(this);
        this.butChatMessageSend.setEnabled(false);
        this.butChatMessageSend.setBounds(60+(400*2)+10+300,520,80,50);
        this.butChatMessageSend.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(butChatMessageSend,15);
        this.butChatMessageSend.setBackground(Color.DARK_GRAY);
        this.butChatMessageSend.setForeground(Color.WHITE);
        this.connectPanel.add(butChatMessageSend);

        this.connectPanel.repaint();

    }
} class ConnectMenuPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY); // Rectangles
        g.fillRect(20,100,400,480); // Left rectangle
        g.fillRect(40+400,100,400,480); // Middle rectangle
        g.fillRect(60+(400*2),100,400,480); // Right rectangle

        g.setColor(Color.BLACK); // Outlines
        g.drawRect(20,100,400,480); // Left rectangle outline
        g.drawRect(40+400,100,400,480); // Middle rectangle outline
        g.drawRect(60+(400*2),100,400,480); // Right rectangle outline
    }
}
