package network;

import panels.*;
import game.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Connections implements ActionListener {
    // PROPERTIES
    public static final int READY=0, START=1, CHAT_MESSAGE=2, GRID=3; // Message types

    public static SuperSocketMaster ssm; // Create SuperSocketMaster object to communicate over a network (https://github.com/MrCadawas). Wraps BufferedReader & PrintWriter objects.
    public static boolean blnIsServer;
    public static boolean blnConnected;

    private String[][] strMessageSegment;

    // METHODS
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == ssm) { // Incoming message
            String strMessage = ssm.readText();
            String strMessageSegment[] = strMessage.split(",");
            int intMessageType = Integer.parseInt(strMessageSegment[0]);

            if (intMessageType == READY) { // Opponent is ready to start game
                ConnectMenu.blnEnemyReady = true; // Player is ready to start game
                if (blnIsServer == true && ConnectMenu.blnReady == true) {
                    Connections.sendMessage(START);
                    panels.Utility.setPanel(new Game().getPanel());
                }
            } else if (intMessageType == START) {
                panels.Utility.setPanel(new Game().getPanel());
            } else if (intMessageType == CHAT_MESSAGE) {
                ConnectMenu.areaChat.append(strMessageSegment[1] + "\n"); // Add new chat message to chat
            } else if (intMessageType == GRID) {
                if (strMessageSegment[1].equalsIgnoreCase("add")) { // Add blocks to grind
                    int intSquareX = Integer.parseInt(strMessageSegment[2]);
                    int intSquareY = Integer.parseInt(strMessageSegment[3]);
                    int intBlockType = Integer.parseInt(strMessageSegment[4]);
                    BoardPanel.intEnemyGrid[intSquareX][intSquareY] = intBlockType;
                } else if (strMessageSegment[1].equalsIgnoreCase("remove")) { // Remov rows from grid
                    int intRow = Integer.parseInt(strMessageSegment[2]);
                    for (int a = 0; a < intRow; a++) {
                        System.arraycopy(BoardPanel.intEnemyGrid[intRow - a - 1], 0, BoardPanel.intEnemyGrid[intRow - a], 0, 10); // Shift all blocks above down 1 block, by copying the array
                    }
                }
            }
        }
    }

    public static void sendMessage(int intMessageType) {
        ssm.sendText(intMessageType + ",");
    }

    public static void sendMessage(int intMessageType, String strMessage) {
        ssm.sendText(intMessageType + "," + strMessage);
    }

    public static void sendMessage(int intMessageType, String[] strMessage) {
        String strSendMessage = intMessageType + "";
        for (int i=0; i<strMessage.length;i++) {
            strSendMessage = strSendMessage + "," + strMessage[i];
        }
        ssm.sendText(strSendMessage);
    }




    // CONSTRUCTORS
    // Server Constructor
    public Connections(int intPort, ActionListener actionListener) {
        this.blnIsServer = true;
        this.ssm = new SuperSocketMaster(intPort, this); // (intPort, listener)
        this.ssm.connect();
    }

    // Client Constructor
    public Connections(String strServerIP, int intPort, ActionListener actionListener) {
        this.blnIsServer = false;
        this.ssm = new SuperSocketMaster(strServerIP, intPort, this); // (strServerIP, intPort, listener)
        if (this.ssm.connect() == true) { // Connect to server
            this.blnConnected = true;
        } else { // Failed to connect to server
            this.blnConnected = false;
        }
    }
}
