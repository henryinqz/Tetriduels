public class Controller {
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
    public static void moveUp(Block blockCurrent) { // Should only be used for bottom floor wallkick
        if (blockCurrent.intY - BoardPanel.intMove >= 0) { // Check if highest block can make move w/o exiting board
            blockCurrent.intY -= BoardPanel.intMove; // Move up
        } else if (blockCurrent.intCurrentCoords[0][0] == 0 && blockCurrent.intCurrentCoords[0][1] == 0 && blockCurrent.intCurrentCoords[0][2] == 0 && blockCurrent.intCurrentCoords[0][3] == 0) { // Check if block coord array is empty in row 0
            if (blockCurrent.intCurrentCoords[1][0] == 0 && blockCurrent.intCurrentCoords[1][1] == 0 && blockCurrent.intCurrentCoords[1][2] == 0 && blockCurrent.intCurrentCoords[1][3] == 0) { // Check if block coord array is empty in row 1
                if (blockCurrent.intY + BoardPanel.intBlockSize >= 0) { // Check if lowest block can make move w/o exiting board
                    blockCurrent.intY -= BoardPanel.intMove; // Move up
                }
            }
            if (blockCurrent.intY >= 0) { // Check if highest block can make move w/o exiting board
                blockCurrent.intY -= BoardPanel.intMove; // Move up
            }
        }
    }

    public static void rotate(Block blockCurrent, String strDirection) {
       // Wallkick: Side walls (prevents rotation from sending block out of screen)
        if (blockCurrent.intX < (BoardPanel.intBlockSize * 0)) { // Wallkick checks for left wall
           if (blockCurrent.intType == Block.IBlock) { // IBlock
               if (blockCurrent.intRotation == 1) { // Left rotation position (IBlock)
                   moveRight(blockCurrent); // Move block right
               } else if (blockCurrent.intRotation == 3) { // Right rotation position (IBlock)
                   moveRight(blockCurrent); // Move block right twice
                   moveRight(blockCurrent);
               }
           } else if (blockCurrent.intType == Block.JBlock || blockCurrent.intType == Block.LBlock || blockCurrent.intType == Block.SBlock || blockCurrent.intType == Block.ZBlock || blockCurrent.intType == Block.TBlock) { // J,L,S,Z,T Blocks
               moveRight(blockCurrent); // Move block right
           }
        } else if (blockCurrent.intX == (BoardPanel.intBlockSize * 8)) { // Wallkick checks for right wall
           if (blockCurrent.intType == Block.IBlock) { // IBlock
               if (blockCurrent.intRotation == 1) { // Left rotation (IBlock)
                       moveLeft(blockCurrent); // Move block left twice
                       moveLeft(blockCurrent);
               }
           } else if (blockCurrent.intType == Block.JBlock || blockCurrent.intType == Block.LBlock || blockCurrent.intType == Block.SBlock || blockCurrent.intType == Block.ZBlock || blockCurrent.intType == Block.TBlock) { // J,L,S,Z,T Blocks
               moveLeft(blockCurrent); // Move block left
           }
        } else if (blockCurrent.intX == (BoardPanel.intBlockSize * 7)) { // IBlock wallkick check for right wall
            if (blockCurrent.intRotation == 3) { // Right rotation (IBlock)
               moveLeft(blockCurrent); // Move block left
           }
        }
        // Wallkick: Bottom wall
        if (blockCurrent.intY == (BoardPanel.intBlockSize*18)) {
            if (blockCurrent.intType == Block.IBlock) { // IBlock
                System.out.println("18");
                if (blockCurrent.intRotation == 0) { // Up rotation position (IBlock)
                    moveUp(blockCurrent); // Move block up twice
                    moveUp(blockCurrent);
                }
            } else if (blockCurrent.intType == Block.JBlock || blockCurrent.intType == Block.LBlock || blockCurrent.intType == Block.SBlock || blockCurrent.intType == Block.ZBlock || blockCurrent.intType == Block.TBlock) { // J,L,S,Z,T Blocks
                moveUp(blockCurrent); // Move block right
            }
        } else if (blockCurrent.intY == (BoardPanel.intBlockSize*17)) { // IBlock wallkick check for bottom wall
            System.out.println("17");
            if (blockCurrent.intType == Block.IBlock) { // IBlock
                if (blockCurrent.intRotation == 2) { // Down rotation position (IBlock)
                    moveUp(blockCurrent); // Move block up
                }
            }
        }
        // Wallkick: Top wall (rare)
        if (blockCurrent.intY < 0) { // Block is above 0
            //System.out.println("Wallkicked off top wall");
            //moveDown(blockCurrent); // Move block down
            blockCurrent.intY = 0; // Reset block to row 0 of board (moveDown would've required more checks for IBlock)
        }
        blockCurrent.rotatePiece(strDirection); // Rotate piece
    }

    public static Block generateBlock() { // Generate a new Tetromino (
        Block blockCurrent = new Block(-1); // Default to -1 for return statement

        // Generate random integer from 0-7 to determine which block to generate
        // IBlock = 0, LBlock = 1, JBlock = 2, SBlock = 3, ZBlock = 4, TBlock = 5, OBlock = 6
        int intRandom = (int)(Math.random()*7);
        //intRandom = Block.IBlock; // debug

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
