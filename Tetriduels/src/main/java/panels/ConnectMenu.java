package panels;

import network.*;
import game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectMenu implements ActionListener {
    // PROPERTIES
    JPanel connectPanel = new JPanel();

    JButton butHost = new JButton("Host game");
    JButton butClient = new JButton("Join game");
    JButton butBack = new JButton("Return to menu");

    JLabel labelServerIP = new JLabel("Server IP:");
    JLabel labelPort = new JLabel("Port:");
    JTextField fieldServerIP = new JTextField();
    JTextField fieldPort = new JTextField();
    JButton butConnect = new JButton("Connect");
    JLabel labelError = new JLabel("",SwingConstants.CENTER);

    public String strServerPort;
    public int intPort;

    JButton butReady = new JButton("Ready");
    public static boolean blnReady = false;
    public static boolean blnEnemyReady = false;

    // Chat
    public static JTextArea areaChat = new JTextArea();
    JScrollPane scrollChat = new JScrollPane(areaChat);
    JTextField fieldChatMessage = new JTextField();
    JButton butChatMessageSend = new JButton("Send");

    // METHODS
    public JPanel getPanel() {
        return connectPanel;
    }
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == butHost) {
            labelError.setVisible(false);
            if (Connections.ssm != null) {
                Connections.ssm.disconnect();
            }
            new Connections(SettingsMenu.intPort); // Server; create SuperSocketMaster object
            fieldServerIP.setEditable(false);
            fieldServerIP.setText(Connections.ssm.getMyAddress());
            fieldPort.setEditable(false);
            fieldPort.setText(SettingsMenu.intPort + "");
            butConnect.setEnabled(false);
            butReady.setVisible(true);
            butReady.setEnabled(true);

            fieldChatMessage.setEnabled(true);
            butChatMessageSend.setEnabled(true);

            //panels.Utility.setPanel(new Game().getPanel());
        } else if (evt.getSource() == butClient) {
            labelError.setVisible(false);
            butReady.setVisible(false);
            if (Connections.ssm != null) {
                Connections.ssm.disconnect();
            }
            fieldServerIP.setEditable(true);
            fieldServerIP.setText("");
            fieldPort.setEditable(true);
            fieldPort.setText("");
            butConnect.setEnabled(true);

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
                        labelError.setVisible(false);
                        butConnect.setEnabled(false);
                        butReady.setVisible(true);
                        butReady.setEnabled(true);
                        fieldChatMessage.setEnabled(true);
                        butChatMessageSend.setEnabled(true);
                    } else { // Failed to connect to server
                        butConnect.setEnabled(true);
                        labelError.setText("Could not connect to server. \n Make sure IP and port are entered correctly.");
                        labelError.setVisible(true);
                    }
                } else { // Port out of range
                    labelError.setText("Enter a valid port between 0-65535!");
                    labelError.setVisible(true);
                    butReady.setVisible(false);
                }
            } else { // Blank server IP or port
                labelError.setText("Enter a valid IP and port!");
                labelError.setVisible(true);
                butReady.setVisible(false);
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
            //Connections.ssm.sendText(fieldChatMessage.getText()); // Send text
            Connections.sendMessage(Connections.CHAT_MESSAGE, fieldChatMessage.getText());
            areaChat.append("You: " + fieldChatMessage.getText() + "\n"); // Add player chat message to chat
            fieldChatMessage.setText(""); // Clear send message box
        }/* else if(evt.getSource() == Connections.ssm) { // Receive data over network
            areaChat.append(Connections.ssm.readText() + "\n"); // Add new chat message to chat
        }*/
        JScrollBar scrollBarChat = scrollChat.getVerticalScrollBar(); // Always scroll bottom
        scrollBarChat.setValue(scrollBarChat.getMaximum());

    }

    // CONSTRUCTOR
    public ConnectMenu() {
        this.connectPanel.setPreferredSize(new Dimension(GUI.FRAME_WIDTH,GUI.FRAME_HEIGHT));
        this.connectPanel.setLayout(null);

        // (Server) Host button
        this.butHost.addActionListener(this);
        this.butHost.setBounds(5,5,400,150);
        this.connectPanel.add(butHost);

        // (Client) Join game button
        this.butClient.addActionListener(this);
        this.butClient.setBounds(5,(5*2)+150,400,150);
        this.connectPanel.add(butClient);

        // Exit button
        this.butBack.addActionListener(this);
        this.butBack.setBounds(5,(5*4)+(150*3),400,150);
        this.connectPanel.add(butBack);

        // ServerIP
        this.labelServerIP.setBounds(420,10,80,30);
        this.fieldServerIP.setEditable(false);
        this.fieldServerIP.setBounds(500,10,130,30);
        this.connectPanel.add(labelServerIP);
        this.connectPanel.add(fieldServerIP);

        // Port
        this.labelPort.setBounds(450,50,40,30);
        this.fieldPort.setEditable(false);
        this.fieldPort.setBounds(500,50,40,30);
        this.connectPanel.add(labelPort);
        this.connectPanel.add(fieldPort);

        // (Client) connect button
        this.butConnect.addActionListener(this);
        this.butConnect.setEnabled(false);
        this.butConnect.setBounds(500,120,110,30);
        this.connectPanel.add(butConnect);

        // Initialize ready button
        this.butReady.addActionListener(this);
        this.butReady.setLocation(900,400);
        this.butReady.setSize(110,30);
        this.butReady.setVisible(false);
        this.connectPanel.add(butReady);

        // Initialize error label
        this.labelError.setLocation(900,5);
        this.labelError.setSize(300,300);
        this.labelError.setForeground(Color.RED);
        this.labelError.setVisible(false);
        this.connectPanel.add(labelError);

        // Chat
        this.areaChat.setEditable(false);
        this.areaChat.setLineWrap(true);
        this.scrollChat.setSize(300,300);
        this.scrollChat.setLocation(500,300);
        this.connectPanel.add(scrollChat);

        this.fieldChatMessage.setEnabled(false);
        this.fieldChatMessage.setSize(220,50);
        this.fieldChatMessage.setLocation(500,600);
        this.connectPanel.add(fieldChatMessage);

        this.butChatMessageSend.addActionListener(this);
        this.butChatMessageSend.setEnabled(false);
        this.butChatMessageSend.setSize(80,50);
        this.butChatMessageSend.setLocation(720,600);
        this.connectPanel.add(butChatMessageSend);

    }
}
