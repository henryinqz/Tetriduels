package game;

import panels.*;
import network.*;

import java.awt.*;

public class Block {
    // PROPERTIES
    public static final int IBLOCK = 1, LBLOCK = 2, JBLOCK = 3, SBLOCK = 4, ZBLOCK = 5, TBLOCK = 6, OBLOCK = 7, GARBAGE = 8;
    public int intType;
    private int[][][] intCoordsArray = new int[4][4][4];
    public int[][] intCurrentCoords = new int[4][4];
    public int intRotation = 0; // 0=up, 1=left, 2=down, 3=right
    public Color colBlock;

    // Spawning coordinates
    public int intX = BoardPanel.BLOCKSIZE * 3; // Spawn 4 blocks over on x axis
    public int intY = BoardPanel.BLOCKSIZE * 1; // Spawn at top of board

    public boolean blnHeldBefore = false; // Boolean property to prevent holding same block multiple times

    // METHODS
    private void makePiece(int intType) {
        if (intType == IBLOCK) {
            intCoordsArray = new int[][][]{
                    {{0,0,0,0}, {1,1,1,1}, {0,0,0,0}, {0,0,0,0}}, // Up
                    {{0,1,0,0}, {0,1,0,0}, {0,1,0,0}, {0,1,0,0}}, // Left
                    {{0,0,0,0}, {0,0,0,0}, {1,1,1,1}, {0,0,0,0}}, // Down
                    {{0,0,1,0}, {0,0,1,0}, {0,0,1,0}, {0,0,1,0}} // Right
            };
            //colBlock = new Color(0, 240, 240); // Cyan
            colBlock = new Color(0, 200, 240); // Darker cyan
        } else if (intType == LBLOCK) {
            intCoordsArray = new int[][][] {
                    {{0,0,1,0},{1,1,1,0},{0,0,0,0},{0,0,0,0}}, // Up
                    {{1,1,0,0},{0,1,0,0},{0,1,0,0},{0,0,0,0}}, // Left
                    {{0,0,0,0},{1,1,1,0},{1,0,0,0},{0,0,0,0}},  // Down
                    {{0,1,0,0},{0,1,0,0},{0,1,1,0},{0,0,0,0}} // Right
            };
            //colBlock = new Color(240, 160, 0); // Orange
            colBlock = new Color(240, 130, 0); // Darker orange
        } else if (intType == JBLOCK) {
            intCoordsArray = new int[][][]{
                    {{1,0,0,0}, {1,1,1,0}, {0,0,0,0}, {0,0,0,0}},  // Up
                    {{0,1,0,0}, {0,1,0,0}, {1,1,0,0}, {0,0,0,0}}, // Left
                    {{0,0,0,0}, {1,1,1,0}, {0,0,1,0}, {0,0,0,0}}, // Down
                    {{0,1,1,0}, {0,1,0,0}, {0,1,0,0}, {0,0,0,0}} // Right
            };
            //colBlock = new Color(0, 0, 240); // Darker blue
            colBlock = new Color(0, 80, 255); // Lighter blue
        } else if (intType == SBLOCK) {
            intCoordsArray = new int[][][] {
                    {{0,1,1,0},{1,1,0,0},{0,0,0,0},{0,0,0,0}}, // Up
                    {{1,0,0,0},{1,1,0,0},{0,1,0,0},{0,0,0,0}},  // Left
                    {{0,0,0,0},{0,1,1,0},{1,1,0,0},{0,0,0,0}}, // Down
                    {{0,1,0,0},{0,1,1,0},{0,0,1,0},{0,0,0,0}}  // Right
            };
            //colBlock = new Color(0, 240, 0); // Green
            colBlock = new Color(0,200,0); // Darker green
        } else if (intType == ZBLOCK) {
            intCoordsArray = new int[][][] {
                    {{1,1,0,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}}, // Up
                    {{0,1,0,0},{1,1,0,0},{1,0,0,0},{0,0,0,0}},  //Left
                    {{0,0,0,0},{1,1,0,0},{0,1,1,0},{0,0,0,0}}, // down
                    {{0,0,1,0},{0,1,1,0},{0,1,0,0},{0,0,0,0}}  // Right
            };
            colBlock = new Color(240, 0, 0); // Red
        } else if (intType == TBLOCK) {
            intCoordsArray = new int[][][] {
                    {{0,1,0,0},{1,1,1,0},{0,0,0,0},{0,0,0,0}}, // Up
                    {{0,1,0,0},{1,1,0,0},{0,1,0,0},{0,0,0,0}}, //Left
                    {{0,0,0,0},{1,1,1,0},{0,1,0,0},{0,0,0,0}}, // Down
                    {{0,1,0,0},{0,1,1,0},{0,1,0,0},{0,0,0,0}}  //Right
            };
            colBlock = new Color(160, 0, 240); // Purple
        } else if (intType == OBLOCK) {
            intCoordsArray = new int[][][] {
                    {{0,1,1,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}}, // All directions are same
                    {{0,1,1,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}},
                    {{0,1,1,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}},
                    {{0,1,1,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}}
            };
            //colBlock = new Color(240, 240, 0); // Yellow
            colBlock = new Color(240, 200, 0); // Yellow
        } else if (intType == GARBAGE) {
            colBlock = Color.LIGHT_GRAY;
        }
        intCurrentCoords = intCoordsArray[intRotation]; // Set 2D array of current coordinates. Default value when making piece is up (intRotation=0)
    }
    public void rotatePiece(String strDirection) {
        if (strDirection.equalsIgnoreCase("left")) { // left rotation
            if (intRotation != 3) {
                intRotation++;
            } else { // Resets intRotation so it loops from 0-3
                intRotation = 0;
            }
        } else if (strDirection.equalsIgnoreCase("right")) { // right rotation
            if (intRotation != 0) {
                intRotation--;
            } else { // Resets intRotation so it loops from 0-3
                intRotation = 3;
            }
        } else if (strDirection.equalsIgnoreCase("default")) { // default rotation
            intRotation = 0;
        }
        intCurrentCoords = intCoordsArray[intRotation]; // Sets current coordinates of block to new rotation
    }

    //CONSTRUCTORS
    public Block(int intBlockType) { // Default constructor to create block using given block type
        this.intType = intBlockType;
        makePiece(this.intType);
    }
    public Block(Block blockToCopy) { // Constructor to clone a block
        this.intType = blockToCopy.intType;
        this.intRotation = blockToCopy.intRotation;
        this.intX = blockToCopy.intX;
        this.intY = blockToCopy.intY;
        makePiece(this.intType);

    }
}
