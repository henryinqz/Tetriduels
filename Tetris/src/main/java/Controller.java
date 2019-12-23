public class Controller {
    /*


    Need to add wallkick checks for rotation methods

     */

    public static void moveLeft(Block blockCurrent) {
        if (blockCurrent.intX - BoardPanel.intMove >= 0) { // Check if furthest left block can make move without passing column 0
            blockCurrent.intX -= BoardPanel.intMove; // Move left
        } else if (blockCurrent.intCurrentCoords[0][0] == 0 && blockCurrent.intCurrentCoords[1][0] == 0 && blockCurrent.intCurrentCoords[2][0] == 0 && blockCurrent.intCurrentCoords[3][0] == 0) { // Check if block coord array is empty in column 0
            if (blockCurrent.intCurrentCoords[0][1] == 0 && blockCurrent.intCurrentCoords[1][1] == 0 && blockCurrent.intCurrentCoords[2][1] == 0 && blockCurrent.intCurrentCoords[3][1] == 0) { // Check if block coord array is empty in column 1
                if (blockCurrent.intX + BoardPanel.intBlockSize >= 0) { // Check if furthest left block can make move without passing column 0
                    blockCurrent.intX -= BoardPanel.intMove; // Move left
                }
            }
            if (blockCurrent.intX >= 0) { // Check if furthest left block can make move without passing column 0
                blockCurrent.intX -= BoardPanel.intMove; // Move left
            }
        }
    }
    public static void moveRight(Block blockCurrent) {
        if (blockCurrent.intX + BoardPanel.intMove + (BoardPanel.intBlockSize*4) <= BoardPanel.intXMax) { // Check if furthest left block can make move without passing column 10
            blockCurrent.intX += BoardPanel.intBlockSize; // Move right
        } else if (blockCurrent.intCurrentCoords[0][3] == 0 && blockCurrent.intCurrentCoords[1][3] == 0 && blockCurrent.intCurrentCoords[2][3] == 0 && blockCurrent.intCurrentCoords[3][3] == 0) { // Check if block coord array is empty in column 3
            if (blockCurrent.intCurrentCoords[0][2] == 0 && blockCurrent.intCurrentCoords[1][2] == 0 && blockCurrent.intCurrentCoords[2][2] == 0 && blockCurrent.intCurrentCoords[3][2] == 0) { // Check if block coord array is empty in column 2
                if (blockCurrent.intX + (BoardPanel.intBlockSize * 3) <= BoardPanel.intXMax) { // Check if furthest left block can make move without passing column 10
                    blockCurrent.intX += BoardPanel.intMove; // Move right
                }
            }
            if (blockCurrent.intX + (BoardPanel.intBlockSize * 4) <= BoardPanel.intXMax) { // Check if furthest left block can make move without passing column 10
                blockCurrent.intX += BoardPanel.intMove; // Move right
            }
        }

    }
    public static void moveDown(Block blockCurrent) {
        if (blockCurrent.intY + BoardPanel.intMove + (BoardPanel.intBlockSize * 4) <= BoardPanel.intYMax) { // Check if lowest block can make move w/o exiting board
            blockCurrent.intY += BoardPanel.intMove; // Move down
        } else if (blockCurrent.intCurrentCoords[3][0] == 0 && blockCurrent.intCurrentCoords[3][1] == 0 && blockCurrent.intCurrentCoords[3][2] == 0 && blockCurrent.intCurrentCoords[3][3] == 0) { // Check if block coord array is empty in row 3
            if (blockCurrent.intCurrentCoords[2][0] == 0 && blockCurrent.intCurrentCoords[2][1] == 0 && blockCurrent.intCurrentCoords[2][2] == 0 && blockCurrent.intCurrentCoords[2][3] == 0) { // Check if block coord array is empty in row 2
                if (blockCurrent.intY + (BoardPanel.intBlockSize * 3) <= BoardPanel.intYMax) { // Check if lowest block can make move w/o exiting board
                    blockCurrent.intY += BoardPanel.intMove; // Move down
                }
            }
            if (blockCurrent.intY + (BoardPanel.intBlockSize * 4) <= BoardPanel.intYMax) { // Check if lowest block can make move w/o exiting board
                blockCurrent.intY += BoardPanel.intMove; // Move down
            }
        }
    }

    public static void rotate(Block blockCurrent, String strDirection) {
        blockCurrent.rotatePiece(strDirection);
    }

    public static Block generateBlock() { // Generate a new Tetromino (
        Block blockCurrent = new Block(-1); // Default to -1 for return statement

        // Generate random integer from 0-7 to determine which block to generate
        // IBlock = 0, LBlock = 1, JBlock = 2, SBlock = 3, ZBlock = 4, TBlock = 5, OBlock = 6
        int intRandom = (int)(Math.random()*7);

        if (intRandom == 0) { // Create an IBlock
            blockCurrent = new Block(Block.IBlock);
        } else if (intRandom == 1) { // Create an LBlock
            blockCurrent = new Block(Block.LBlock);
        } else if (intRandom == 2) { // Create an JBlock
            blockCurrent = new Block(Block.JBlock);
        } else if (intRandom == 3) { // Create an SBlock
            blockCurrent = new Block(Block.SBlock);
        } else if (intRandom == 4) { // Create an ZBlock
            blockCurrent = new Block(Block.ZBlock);
        } else if (intRandom == 5) { // Create an TBlock
           blockCurrent = new Block(Block.TBlock);
        } else if (intRandom == 6) { // Create an OBlock
            blockCurrent = new Block(Block.OBlock);
        }
        return (blockCurrent);
    }
}
