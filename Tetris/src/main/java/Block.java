import java.awt.*;

public class Block {
    // PROPERTIES
    public static final int IBlock = 1, LBlock = 2, JBlock = 3, SBlock = 4, ZBlock = 5, TBlock = 6, OBlock = 7;
    public int intType;
    private int[][][] intCoordsArray = new int[4][4][4];
    public int[][] intCurrentCoords = new int[4][4];
    public int intRotation = 0; // 0=up, 1=left, 2=down, 3=right
    public Color colBlock;

    // Spawning coordinates
    public int intX = BoardPanel.intBlockSize * 3; // Spawn 4 blocks over on x axis
    public int intY = BoardPanel.intBlockSize * 0; // Spawn at top of board

    // METHODS
    private void makePiece(int intType) {
        if (intType == IBlock) {
            intCoordsArray = new int[][][]{
                    {{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}}, // Up
                    {{0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}}, // Left
                    {{0, 0, 0, 0}, {0, 0, 0, 0}, {1, 1, 1, 1}, {0, 0, 0, 0}}, // Down
                    {{0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}} // Right
            };
            colBlock = new Color(1, 240, 240); // Cyan
        } else if (intType == LBlock) {
            intCoordsArray = new int[][][] {
                    {{0,0,1,0},{1,1,1,0},{0,0,0,0},{0,0,0,0}}, // Up
                    {{1,1,0,0},{0,1,0,0},{0,1,0,0},{0,0,0,0}}, // Left
                    {{0,0,0,0},{1,1,1,0},{1,0,0,0},{0,0,0,0}},  // Down
                    {{0,1,0,0},{0,1,0,0},{0,1,1,0},{0,0,0,0}} // Right
            };
            colBlock = new Color(240, 160, 0); // Orange
        } else if (intType == JBlock) {
            intCoordsArray = new int[][][] {
                    {{1,0,0,0},{1,1,1,0},{0,0,0,0},{0,0,0,0}},  // Up
                    {{0,1,0,0},{0,1,0,0},{1,1,0,0},{0,0,0,0}}, // Left
                    {{0,0,0,0},{1,1,1,0},{0,0,1,0},{0,0,0,0}}, // Down
                    {{0,1,1,0},{0,1,0,0},{0,1,0,0},{0,0,0,0}} // Right
            };
            colBlock = new Color(0, 1, 240); // Blue
        } else if (intType == SBlock) {
            intCoordsArray = new int[][][] {
                    {{0,1,1,0},{1,1,0,0},{0,0,0,0},{0,0,0,0}}, // Up
                    {{1,0,0,0},{1,1,0,0},{0,1,0,0},{0,0,0,0}},  // Left
                    {{0,0,0,0},{0,1,1,0},{1,1,0,0},{0,0,0,0}}, // Down
                    {{0,1,0,0},{0,1,1,0},{0,0,1,0},{0,0,0,0}}  // Right
            };
            colBlock = new Color(0, 240, 0); // Green
        } else if (intType == ZBlock) {
            intCoordsArray = new int[][][] {
                    {{1,1,0,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}}, // Up
                    {{0,1,0,0},{1,1,0,0},{1,0,0,0},{0,0,0,0}},  //Left
                    {{0,0,0,0},{1,1,0,0},{0,1,1,0},{0,0,0,0}}, // down
                    {{0,0,1,0},{0,1,1,0},{0,1,0,0},{0,0,0,0}}  // Right
            };
            colBlock = new Color(240, 0, 0); // Red
        } else if (intType == TBlock) {
            intCoordsArray = new int[][][] {
                    {{0,1,0,0},{1,1,1,0},{0,0,0,0},{0,0,0,0}}, // Up
                    {{0,1,0,0},{1,1,0,0},{0,1,0,0},{0,0,0,0}}, //Left
                    {{0,0,0,0},{1,1,1,0},{0,1,0,0},{0,0,0,0}}, // Down
                    {{0,1,0,0},{0,1,1,0},{0,1,0,0},{0,0,0,0}}  //Right
            };
            colBlock = new Color(160, 0, 240); // Purple
        } else if (intType == OBlock) {
            intCoordsArray = new int[][][] {
                    {{0,1,1,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}}, // All directions are same
                    {{0,1,1,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}},
                    {{0,1,1,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}},
                    {{0,1,1,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}}
            };
            colBlock = new Color(240, 240, 1); // Yellow
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
        }
        intCurrentCoords = intCoordsArray[intRotation]; // Sets current coordinates of block to new rotation
    }
    public void spawn() {

    }

    //CONSTRUCTOR
    public Block(int intBlockType) {
        this.intType = intBlockType;
        makePiece(this.intType);
    }
}
