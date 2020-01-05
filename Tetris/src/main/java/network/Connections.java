package network;

import panels.*;
import game.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Connections implements ActionListener {
    // PROPERTIES
    public static final int READY=0, START=1, CHAT_MESSAGE=2, GRID=3, GAME_OVER=4; // Message types

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
                    int intSquareY = Integer.parseInt(strMessageSegment[2]);
                    int intSquareX = Integer.parseInt(strMessageSegment[3]);
                    int intBlockType = Integer.parseInt(strMessageSegment[4]);
                    BoardPanel.intEnemyGrid[intSquareY][intSquareX] = intBlockType;
                } else if (strMessageSegment[1].equalsIgnoreCase("remove")) { // Remove rows from grid
                    int intRow = Integer.parseInt(strMessageSegment[2]);
                    for (int a = 0; a < intRow; a++) {
                        System.arraycopy(BoardPanel.intEnemyGrid[intRow-a-1], 0, BoardPanel.intEnemyGrid[intRow-a], 0, 10); // Shift all blocks above down 1 block, by copying the array
                    }
                } else if (strMessageSegment[1].equalsIgnoreCase("garbage")) { // Add garbage to player grid
                    int intGarbageLines = Integer.parseInt(strMessageSegment[2]); // Store # of garbage lines being received

                    if (intGarbageLines > 0) { // Only run if garbage lines sent was above 0
                        for (int a = 0; a < intGarbageLines; a++) { // Loop for # of garbage lines being received
                            for (int b = 0; b < 19; b++) { // Loop through entire y-axis of board
                                if (BoardPanel.intGrid[b+1][0] != 0 || BoardPanel.intGrid[b+1][1] != 0 || BoardPanel.intGrid[b+1][2] != 0 || BoardPanel.intGrid[b+1][3] != 0 || BoardPanel.intGrid[b+1][4] != 0 || BoardPanel.intGrid[b+1][5] != 0 || BoardPanel.intGrid[b+1][6] != 0 || BoardPanel.intGrid[b+1][7] != 0 || BoardPanel.intGrid[b+1][8] != 0 || BoardPanel.intGrid[b+1][9] != 0) { // Skip line if empty
                                    System.arraycopy(BoardPanel.intGrid[b + 1], 0, BoardPanel.intGrid[b], 0, 10); // Shift all blocks above up 1 block, by copying the array
                                }
                                for (int c = 0; c < 10; c++) { // Loop through X-axis
                                    sendMessage(GRID, "add," + (b) + "," + (c) + "," + BoardPanel.intGrid[b][c]); // Tell enemy to change their view of our board by moving blocks up
                                }
                            }
                        }
                        for (int d = 0; d < intGarbageLines; d++) {
                            int intSquareY = 19 - d;
                            int intBlockType = Block.GARBAGE;

                            int intRandom = (int)(Math.floor(Math.random()*10)); // Generate a random # between 0-9 for a gap block in the new garbage line
                            for (int intSquareX=0; intSquareX < 10; intSquareX++) {
                                if (intSquareX == intRandom) { // If the preselected number above is equal to the x-axis value being looped through
                                    BoardPanel.intGrid[intSquareY][intSquareX] = 0; // Set block to air instead of garbage
                                    sendMessage(GRID,"add,"+intSquareY+","+intSquareX+","+0); // Tell enemy to change their view of our board by adding air space
                                } else {
                                    BoardPanel.intGrid[intSquareY][intSquareX] = intBlockType; // Set starting from bottom of board to garbage
                                    sendMessage(GRID,"add,"+intSquareY+","+intSquareX+","+intBlockType); // Tell enemy to change their view of our board by adding garbage blocks
                                }
                            }
                        }
                    }
                    Controller.updateGhostBlock(BoardPanel.blockCurrent); // update ghost block
                }
            } else if (intMessageType == GAME_OVER) {
                if (strMessageSegment[1].equalsIgnoreCase("loss")) { // Enemy lost
                    Tetris.blnGameLoop = false;
                    Game.endGame();
                    // print game over
                    //wait 5 seconds
                    // stats
                    // rematch
                    // close/leave server
                }// else if (strMessageSegment[1].equalsIgnoreCase("win"))
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
    public Connections(int intPort) {
        this.blnIsServer = true;
        this.ssm = new SuperSocketMaster(intPort, this); // (intPort, listener)
        this.ssm.connect();
    }

    // Client Constructor
    public Connections(String strServerIP, int intPort) {
        this.blnIsServer = false;
        this.ssm = new SuperSocketMaster(strServerIP, intPort, this); // (strServerIP, intPort, listener)
        if (this.ssm.connect() == true) { // Connect to server
            this.blnConnected = true;
        } else { // Failed to connect to server
            this.blnConnected = false;
        }
    }
}
