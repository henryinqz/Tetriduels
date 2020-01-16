package game;

import panels.*;
import network.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class BoardPanel extends JPanel {
    // PROPERTIES
    public static final int MOVE = 30; // # of pixels moved every time
    public static final int BLOCKSIZE = 30; // game.Block size (25*25px)
    public static int intXMax = BLOCKSIZE * 10; // 10 blocks wide
    public static int intYMax = BLOCKSIZE * 22; // 22 blocks tall (should only show 20 blocks)
    public static int[][] intGrid = new int[intYMax / BLOCKSIZE][intXMax / BLOCKSIZE]; // 10x20 array grid of board
    public static int[][] intEnemyGrid = new int[intYMax / BLOCKSIZE][intXMax / BLOCKSIZE]; // 10x20 array grid of board (Enemy)
    public static Block blockCurrent;
    public static Block blockGhost;
    public static Block blockHeld;

    public static int intBag = -1; // -1 is initial value to generate the 1st & 2nd pieceArrays
    public static int intRandom = -1;
    public static Integer[] pieceArray = new Integer[]{1, 2, 3, 4, 5, 6, 7};
    public static Integer[] pieceArrayNext = new Integer[]{1, 2, 3, 4, 5, 6, 7};

    public static int intPreviouslyRemovedLine = 0; // Checks if the previous block placed was a line clear
    public static int intGarbageLinesSent = 0; //Calculates total amount of garbage sent

    Thread threadTotalGroundTimer = new Thread(new TotalGroundTimer());
    Thread threadGroundTimer = new Thread(new GroundTimer());

    // METHODS
    public synchronized void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g; // Use Graphics2D instead of regular Graphics
        super.paintComponent(g2); // Clear previous drawings (Windows only); super JPanel (original) paintComponent method

        //Background of board
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0,0,GUI.FRAME_WIDTH,GUI.FRAME_HEIGHT);

        // Player Board
        g2.setColor(Color.DARK_GRAY); // Fill in board background
        g2.fillRect(0, 0, intXMax, intYMax);

        drawOldBlocks(g2); // Draw previously stored blocks
        drawGridlines(0, (BLOCKSIZE*2), intXMax, intYMax, (intXMax/BLOCKSIZE), (intYMax/BLOCKSIZE)-2, g2); // Draw board gridlines
        drawGhostBlock(g2); // Draw ghost block (so that current block can overlap when they intersect)
        drawCurrentBlock(g2); // Draw current block on board
        drawHeldBlock(g2); // Draw held block on sidebar
        drawNextBlocks(g2); // Draw next blocks on side bar

        // Enemy Board
        g2.setColor(Color.DARK_GRAY); // Fill in enemy board background
        g2.fillRect(500, 0, intXMax, intYMax);

        drawEnemyOldBlocks(g2); // Draw enemy's previously stored blocks
        drawGridlines(500, (BLOCKSIZE*2), 500+intXMax, intYMax, (intXMax/BLOCKSIZE), (intYMax/BLOCKSIZE)-2, g2); // Draw board gridlines

        // Cover top three rows
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0,0, intXMax+6,BLOCKSIZE*2); // Player board
        g2.fillRect(500,0, intXMax+2,BLOCKSIZE*2); // Enemy board

        // Combo & total lines sent
        g2.setColor(Color.BLACK);
        g2.drawString("COMBO:"+intPreviouslyRemovedLine, intXMax+5,630); // Draw combo text
        g2.drawString("LINES SENT:"+intGarbageLinesSent,intXMax+5,660); // Draw total lines sent text

        // Chat
        g2.setColor(Color.GRAY);
        g2.fillRect((intXMax*2)+240,0,440, GUI.FRAME_HEIGHT); // Chat background
        g2.setColor(Color.BLACK);
        Utility.setFontSize(g2,30);
        g2.drawString("Press '" + KeyEvent.getKeyText(SettingsMenu.intKeyChat) + "' to open chat", 845,600);

        // Distinguish boards between player/enemy/chat
        Utility.setFontSize(g2, 40);
        g2.drawString("Your board",10,40);
        g2.drawString("Enemy board", 510,40);
        g2.drawString("CHAT", 1000,40);
        g2.drawLine(intXMax+160,0,intXMax+160,GUI.FRAME_HEIGHT); // Player/Enemy border
        g2.drawLine((intXMax*2)+240,0,(intXMax*2)+240,GUI.FRAME_HEIGHT); // Enemy/Chat border

        // Checks for if block is landed
        if (Controller.checkCollision(blockCurrent, "down") == true) { // Block hits bottom
            if (TotalGroundTimer.blnTotalGroundAllow == false || GroundTimer.blnGroundAllow == false || Controller.blnHardDrop == true) {
                storeOldBlocks(blockCurrent);
                removeFullLines(intGrid);


                if (blockCurrent.intY <= BLOCKSIZE*1 && blockCurrent.intX == BLOCKSIZE * 3) { // Collision at block spawn point
                    Tetriduels.blnGameLoop = false; // end game
                    Connections.sendMessage(Connections.GAME_OVER,"loss");
                    Game.endGame();
                    Game.intGameOverResult = Game.LOSER; // Set to loser
                } else { // If no collision at spawn point, generate a new block
                    blockCurrent = Controller.generateBlock();
                    Controller.updateGhostBlock(blockCurrent); // Update position of ghost block

                    TotalGroundTimer.blnTotalGroundAllow = true; // Reset TotalGroundTimer (Reset boolean to allow movement within total allowed time on ground)
                    GroundTimer.blnGroundAllow = true; // Reset GroundTimer (Reset boolean to allow movement within standard allowed time on ground)
                    Controller.blnHardDrop = false; // Reset hard drop boolean
                }
            } else if (TotalGroundTimer.blnTotalGroundAllow == true && TotalGroundTimer.blnRun == false) {
                // Total Ground Timer
                if (threadTotalGroundTimer.getState() == Thread.State.NEW) { // Thread not running yet
                   threadTotalGroundTimer.start(); // Start total ground timer to prevent rotations/moves on same level
                } else { // Thread already running
                   TotalGroundTimer.blnRun = true; // Allow loop to run timer code again
                }
            } else if (GroundTimer.blnGroundAllow == true && GroundTimer.blnRun == false) {
                // (Shorter) Ground Timer
                if (threadGroundTimer.getState() == Thread.State.NEW) { // Thread not running yet
                    threadGroundTimer.start(); // Start (shorter)) ground timer to prevent rotations/moves on same level
                } else { // Thread already running
                    GroundTimer.blnRun = true; // Allow loop to run timer code again
                }
            }
        }

    }

    private void drawBlock(Block blockDraw, int intX, int intY, Graphics2D g2) { // Draw block
        g2.setColor(blockDraw.colBlock);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blockDraw.intCurrentCoords[i][j] != 0) {
                    g2.fillRect(intX + (j * BLOCKSIZE), intY + (i * BLOCKSIZE), BLOCKSIZE, BLOCKSIZE); // Draw block

                    // Draw block outline
                    g2.setColor(Color.BLACK);
                    g2.drawRect(intX + (j * BLOCKSIZE), intY + (i * BLOCKSIZE), BLOCKSIZE, BLOCKSIZE);
                    g2.setColor(blockDraw.colBlock);
                }
            }
        }
    }

    private void drawGhostBlock(Graphics2D g2) {
        g2.setColor(blockGhost.colBlock);
        g2.setStroke(new BasicStroke(1));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blockGhost.intCurrentCoords[i][j] != 0) {
                    g2.drawRect(blockGhost.intX + 1 + (j * BLOCKSIZE), blockGhost.intY + 1 + (i * BLOCKSIZE), BLOCKSIZE - 2, BLOCKSIZE - 2);
                }
            }
        }
        g2.setStroke(new BasicStroke(1));
    }

    private void drawCurrentBlock(Graphics2D g2) { // Draw current block on board
        drawBlock(blockCurrent, blockCurrent.intX, blockCurrent.intY, g2);
    }

    private void drawHeldBlock(Graphics2D g2) { // Draw held block on sidebar
        int intHeldX = intXMax + 5;
        int intHeldY = (BLOCKSIZE*3)-5;

        if (blockHeld != null) { // Run if there is a held block
            drawBlock(blockHeld, intHeldX, intHeldY, g2); // Draws held block
        }

        g2.setColor(Color.BLACK);
        g2.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(g2,20);
        g2.drawString("HOLD", intHeldX, intHeldY - 8);
        drawGridlines(intHeldX, intHeldY, intHeldX + (BLOCKSIZE * 4), intHeldY + (BLOCKSIZE * 4), 4, 4, g2);
    }

    private void drawNextBlocks(Graphics2D g2) { // Draw next 3 blocks on sidebar
        int intNextX = intXMax + 5;
        int intNextY = (BLOCKSIZE*3)+145;
        Block blockNext1;
        Block blockNext2;
        Block blockNext3;

        if (intBag > 6) { // If value of intBag is greater than 6, read from next pieceArray
            blockNext1 = new Block(pieceArrayNext[intBag - 7]);
        } else {
            blockNext1 = new Block(pieceArray[intBag]);
        }
        if (intBag + 1 > 6) { // If value of intBag+1 is greater than 6, read from next pieceArray
            blockNext2 = new Block(pieceArrayNext[intBag - 6]);
        } else {
            blockNext2 = new Block(pieceArray[intBag + 1]);
        }
        if (intBag + 2 > 6) { // If value of intBag+2 is greater than 6, read from next pieceArray
            blockNext3 = new Block(pieceArrayNext[intBag - 5]);
        } else {
            blockNext3 = new Block(pieceArray[intBag + 2]);
        }

        g2.setStroke(new BasicStroke(1)); // Thin line stroke
        drawBlock(blockNext1, intNextX, intNextY, g2);
        drawBlock(blockNext2, intNextX, intNextY + (BLOCKSIZE * 4), g2);
        drawBlock(blockNext3, intNextX, intNextY + (2 * ((BLOCKSIZE * 4))), g2);

        g2.setColor(Color.BLACK);
        g2.setFont(Utility.loadFont("zorque"));
        Utility.setFontSize(g2,20);
        g2.drawString("NEXT", intNextX, intNextY-8);
        //drawGridlines(intNextX, intNextY, intNextX + (intBlockSize*4), intNextY + (intBlockSize*12), 4, 12, g2);
        drawGridlines(intNextX, intNextY, intNextX + (BLOCKSIZE * 4), intNextY + (BLOCKSIZE * 4), 4, 4, g2);
        drawGridlines(intNextX, intNextY + (BLOCKSIZE * 4), intNextX + (BLOCKSIZE * 4), intNextY + (BLOCKSIZE * 8), 4, 4, g2);
        drawGridlines(intNextX, intNextY + (BLOCKSIZE * 8), intNextX + (BLOCKSIZE * 4), intNextY + (BLOCKSIZE * 12), 4, 4, g2);
    }

    private void storeOldBlocks(Block blockCurrent) {
        for (int a = 0; a < 4; a++) { // Loop through entire block coordsArray (all 16 values of the 4x4 array)
            for (int b = 0; b < 4; b++) {
                if (blockCurrent.intCurrentCoords[a][b] != 0) { // If block coordsArray is not empty
                    //intGrid[(blockCurrent.intY / BLOCKSIZE) + a][(blockCurrent.intX / BLOCKSIZE) + b] = blockCurrent.intType; // Fill intGrid array w/ corresponding integer type values

                    int intSquareY = (blockCurrent.intY / BLOCKSIZE) + a; // Singular square of block X coordinate in intGrid array
                    int intSquareX = (blockCurrent.intX / BLOCKSIZE) + b;// Singular square of block Y coordinate in intGrid array
                    intGrid[intSquareY][intSquareX] = blockCurrent.intType; // Fill intGrid array w/ corresponding integer type values
                    Connections.sendMessage(Connections.GRID, "add," + intSquareY + "," + intSquareX + "," + blockCurrent.intType);
                } // If nothing in block coordsArray, intGrid[(blockCurrent.intY / intBlockSize) + a][(blockCurrent.intX / intBlockSize) + b] remains at 0
            }
        }

    }

    private void drawOldBlocks(Graphics2D g2) {
        for (int c = 0; c < (intYMax / BLOCKSIZE); c++) {
            for (int d = 0; d < (intXMax / BLOCKSIZE); d++) {
                if (intGrid[c][d] != 0) {
                    switch (intGrid[c][d]) { // Set block colours of corresponding block/values in intGrid
                        case Block.IBLOCK:
                            //g2.setColor(new Color(0, 240, 240)); // Cyan
                            g2.setColor(new Color(0, 200, 240)); // Darker cyan
                            break;
                        case Block.LBLOCK:
                            //g2.setColor(new Color(240, 160, 0)); // Orange
                            g2.setColor(new Color(240, 130, 0)); // Darker orange
                            break;
                        case Block.JBLOCK:
                            //g2.setColor(new Color(0, 0, 240)); // Darker blue
                            g2.setColor(new Color(0, 80, 255)); // Lighter blue
                            break;
                        case Block.SBLOCK:
                            //g2.setColor(new Color(0, 240, 0)); // Green
                            g2.setColor(new Color(0, 200, 0)); // Darker green
                            break;
                        case Block.ZBLOCK:
                            g2.setColor(new Color(240, 0, 0)); // Red
                            break;
                        case Block.TBLOCK:
                            g2.setColor(new Color(160, 0, 240)); // Purple
                            break;
                        case Block.OBLOCK:
                            //g2.setColor(new Color(240, 240, 0)); // Yellow
                            g2.setColor(new Color(240, 200, 0)); // Yellow
                            break;
                        case Block.GARBAGE:
                            g2.setColor(Color.LIGHT_GRAY);
                            break;
                    }
                    g2.fillRect(d * BLOCKSIZE, c * BLOCKSIZE, BLOCKSIZE, BLOCKSIZE); // Draw oldBlocks
                }
            }
        }
    }
    private void drawEnemyOldBlocks(Graphics2D g2) {
        for (int c = 0; c < (intYMax / BLOCKSIZE); c++) {
            for (int d = 0; d < (intXMax / BLOCKSIZE); d++) {
                if (intEnemyGrid[c][d] != 0) { // enemy grid
                    switch (intEnemyGrid[c][d]) { // Set block colours of corresponding block/values in intGrid
                        case Block.IBLOCK:
                            //g2.setColor(new Color(0, 240, 240)); // Cyan
                            g2.setColor(new Color(0, 200, 240)); // Darker cyan
                            break;
                        case Block.LBLOCK:
                            //g2.setColor(new Color(240, 160, 0)); // Orange
                            g2.setColor(new Color(240, 130, 0)); // Darker orange
                            break;
                        case Block.JBLOCK:
                            //g2.setColor(new Color(0, 0, 240)); // Darker blue
                            g2.setColor(new Color(0, 80, 255)); // Lighter blue
                            break;
                        case Block.SBLOCK:
                            //g2.setColor(new Color(0, 240, 0)); // Green
                            g2.setColor(new Color(0, 200, 0)); // Darker green
                            break;
                        case Block.ZBLOCK:
                            g2.setColor(new Color(240, 0, 0)); // Red
                            break;
                        case Block.TBLOCK:
                            g2.setColor(new Color(160, 0, 240)); // Purple
                            break;
                        case Block.OBLOCK:
                            //g2.setColor(new Color(240, 240, 0)); // Yellow
                            g2.setColor(new Color(240, 200, 0)); // Yellow
                            break;
                        case Block.GARBAGE:
                            g2.setColor(Color.LIGHT_GRAY);
                            break;
                    }
                    g2.fillRect(500+(d * BLOCKSIZE), c * BLOCKSIZE, BLOCKSIZE, BLOCKSIZE); // Draw oldBlocks
                }
            }
        }
    }

    private void removeFullLines(int[][] intGrid) {
        int intRemovedLines = 0; // Variable that determines how many rows/lines were removed after 1 block placement

        for (int y = 0; y < (intYMax / BLOCKSIZE); y++) {
            if (intGrid[y][0] != 0 && intGrid[y][1] != 0 && intGrid[y][2] != 0 && intGrid[y][3] != 0 && intGrid[y][4] != 0 && intGrid[y][5] != 0 && intGrid[y][6] != 0 && intGrid[y][7] != 0 && intGrid[y][8] != 0 && intGrid[y][9] != 0) { // Full Row
                intPreviouslyRemovedLine = intPreviouslyRemovedLine+1;

                for (int a = 0; a < y; a++) {
                    //intGrid[y - a] = intGrid[y - a - 1];  // Shift all blocks above down 1 block (DOESN'T WORK, SINCE intGrid[y-a] SIMPLY BECOMES A REFERENCE FOR intGrid[y-a-1]
                    System.arraycopy(intGrid[y - a - 1], 0, intGrid[y - a], 0, 10); // Shift all blocks above down 1 block, by copying the array
                }
                intRemovedLines++; // increase # of removes lines by 1
                Connections.sendMessage(Connections.GRID, "remove," + y); // Tell enemy to clear line of our board

            }

        }
        if (intRemovedLines > 1) { // Only send if 2 or more lines were cleared
            sendGarbageLines(intRemovedLines-1); // Call method to send garbage lines to opposite side
            intGarbageLinesSent = intGarbageLinesSent+(intRemovedLines-1);
        }else if(intPreviouslyRemovedLine > 1 && intRemovedLines>0){ //Only send if previous placed block was a line clear
            sendGarbageLines(intRemovedLines); // Call method to send garbage lines to opposite side

        }else if(intRemovedLines < 1) { // If there were no lines sent
            intPreviouslyRemovedLine = 0;
        }
    }

    private void sendGarbageLines(int intRemovedLines) {
        Connections.sendMessage(Connections.GRID, "garbage," + intRemovedLines);
        Utility.playSound(new File("assets/audio/blocks/GarbageNoise.wav")); // Play hard drop sound
    }

    private void drawGridlines(int intX1, int intY1, int intX2, int intY2, int intAmountVert, int intAmountHoriz, Graphics2D g2) { // Draw gridlines on board. intAmountX & Y determine how many gridlines to draw
        g2.setColor(Color.BLACK);
        for (int a = 0; a <= intAmountVert; a++) { // Draw thick outline outside vertical grid
            if (a == 0 || a == intAmountVert) {
                g2.setStroke(new BasicStroke(2));
            } else {
                g2.setStroke(new BasicStroke(1));
            }
            g2.drawLine(intX1 + (a * BLOCKSIZE), intY1, intX1 + (a * BLOCKSIZE), intY2); // Vertical gridlines
            //g2.drawLine(a*intBlockSize, 0, a*intBlockSize, intYMax); // Vertical gridlines (board)
        }
        for (int b = 0; b <= intAmountHoriz; b++) {
            if (b == 0 || b == intAmountHoriz) { // Draw thick outline outside horizontal grid
                g2.setStroke(new BasicStroke(2));
            } else {
                g2.setStroke(new BasicStroke(1));
            }
            g2.drawLine(intX1, intY1 + (b * BLOCKSIZE), intX2, intY1 + (b * BLOCKSIZE)); // Horizontal gridlines
            //g2.drawLine(0, b*intBlockSize, intXMax, b*intBlockSize); // Horizontal gridlines (board)
        }
    }

    // CONSTRUCTOR
    public BoardPanel() {
        super();
        this.intGrid = new int[intYMax / BLOCKSIZE][intXMax / BLOCKSIZE]; // Reset 10x20 array grid of board
        this.intEnemyGrid = new int[intYMax / BLOCKSIZE][intXMax / BLOCKSIZE]; // Reset 10x20 array grid of board (Enemy)
        this.blockHeld = null; // Reset held block
        this.intPreviouslyRemovedLine = 0;
        this.intGarbageLinesSent = 0;
        blockCurrent = Controller.generateBlock(); // Generate a new block

    }
}
