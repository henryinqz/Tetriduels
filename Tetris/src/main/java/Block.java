import java.awt.*;

public class Block {
    // PROPERTIES
    public static final int IBlock = 0, LBlock = 1, JBlock = 2, SBlock = 3, ZBlock = 4, TBlock = 5, OBlock = 6;
    private int intType;
    public int[][][] intCoordsArray = new int[4][4][4];
    public Color colBlock;

    // METHODS
    public void makePiece(int intType) {
        if (this.intType == IBlock) {
            intCoordsArray = new int[][][]{
                    {{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}}, // Up
                    {{0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}}, // Left
                    {{0, 0, 0, 0}, {0, 0, 0, 0}, {1, 1, 1, 1}, {0, 0, 0, 0}}, // Down
                    {{0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}} // Right
            };
            colBlock = new Color(1, 240, 240); // Cyan
        } else if (this.intType == LBlock) {
            intCoordsArray = new int[][][] {
                    {{0,1,0,0},{0,1,0,0},{0,1,1,0},{0,0,0,0}}, // Up
                    {{0,0,1,0},{1,1,1,0},{0,0,0,0},{0,0,0,0}}, //Left
                    {{1,1,0,0},{0,1,0,0},{0,1,0,0},{0,0,0,0}}, // Down
                    {{0,0,0,0},{1,1,1,0},{1,0,0,0},{0,0,0,0}}  //Right
            };
            colBlock = new Color(240, 160, 0); // Orange
        } else if (this.intType == JBlock) {
            intCoordsArray = new int[][][] {
                    {{0,1,0,0},{0,1,0,0},{1,1,0,0},{0,0,0,0}}, // Up
                    {{0,0,0,0},{1,1,1,0},{0,0,1,0},{0,0,0,0}}, //Left
                    {{0,1,1,0},{0,1,0,0},{0,1,0,0},{0,0,0,0}}, // Down
                    {{0,0,0,0},{1,0,0,0},{1,1,1,0},{0,0,0,0}}  //Right
            };
            colBlock = new Color(0, 1, 240); // Blue
        } else if (this.intType == SBlock) {
            intCoordsArray = new int[][][] {
                    {{0,0,0,0},{0,1,1,0},{1,1,0,0},{0,0,0,0}}, // Up
                    {{1,0,0,0},{1,1,0,0},{0,1,0,0},{0,0,0,0}},  // Left
                    {{0,1,1,0},{1,1,0,0},{0,0,0,0},{0,0,0,0}}, // Down
                    {{0,1,0,0},{0,1,1,0},{0,0,1,0},{0,0,0,0}}  // Right
            };
            colBlock = new Color(0, 240, 0); // Green
        } else if (this.intType == ZBlock) {
            intCoordsArray = new int[][][] {
                    {{1,1,0,0},{0,1,1,0},{0,0,0,0},{0,0,0,0}}, // Up
                    {{0,1,0,0},{1,1,0,0},{1,0,0,0},{0,0,0,0}},  //Left
                    {{0,0,0,0},{1,1,0,0},{0,1,1,0},{0,0,0,0}}, // down
                    {{0,0,1,0},{0,1,1,0},{0,1,0,0},{0,0,0,0}}  // Right
            };
            colBlock = new Color(240, 0, 0); // Red
        } else if (this.intType == TBlock) {
            intCoordsArray = new int[][][] {
                    {{0,1,0,0},{1,1,1,0},{0,0,0,0},{0,0,0,0}}, // Up
                    {{0,1,0,0},{1,1,0,0},{0,1,0,0},{0,0,0,0}}, //Left
                    {{0,0,0,0},{1,1,1,0},{0,1,0,0},{0,0,0,0}}, // Down
                    {{0,1,0,0},{0,1,1,0},{0,1,0,0},{0,0,0,0}}  //Right
            };
            colBlock = new Color(160, 0, 240); // Purple
        } else if (this.intType == OBlock) {
            intCoordsArray = new int[][][] {
                    {{1,1,0,0},{1,1,0,0},{0,0,0,0},{0,0,0,0}} // All direction
            };
            colBlock = new Color(240, 240, 1); // Yellow
        }
    }
    public void spawn() {

    }
    public void rotate() {

    }
    //CONSTRUCTOR
    public Block(int intBlockType) {
        this.intType = intBlockType;
        makePiece(this.intType);
    }
}
