import java.util.Collections;
import java.util.Arrays;
import java.util.List; 
import java.util.ArrayList; 

public class Controller {
    // Methods
    public static void moveLeft(Block blockCurrent) {
        if (checkCollision(blockCurrent, "left") == false) {
            blockCurrent.intX -= BoardPanel.intMove; // Move left
        }
    }

    public static void moveRight(Block blockCurrent) {
        if (checkCollision(blockCurrent, "right") == false) {
            blockCurrent.intX += BoardPanel.intMove; // Move right
        }
    }

    public static void moveDown(Block blockCurrent) {
        if (checkCollision(blockCurrent, "down") == false) {
            blockCurrent.intY += BoardPanel.intMove; // Move down
        }
    }

    public static void moveUp(Block blockCurrent) {
        if (checkCollision(blockCurrent, "up") == false) {
            blockCurrent.intY -= BoardPanel.intMove; // Move up
        }
    }

    public static boolean checkCollision(Block blockCurrent, String strSide) { // Method to check block collision (side = "up", "right", "left", "down"). True = collides, false = no collision
        if (strSide.equalsIgnoreCase("left")) {
            //Block collision checks
            for (int i = 0; i < 4; i++) {
                if (blockCurrent.intCurrentCoords[i][0] != 0 && (blockCurrent.intX / BoardPanel.intBlockSize) > 0) { // Check if non-empty array column 0 (and prevent checking outside of intGrid array)
                    if (blockCurrent.intCurrentCoords[i][0] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.intBlockSize) + i][(blockCurrent.intX / BoardPanel.intBlockSize) - 1] != 0) { // Check if block in intGrid below blockCurrent (non-empty array column 3)
                        return true;
                    }
                } else if (blockCurrent.intCurrentCoords[i][0] == 0) { // Empty array column 0
                    if (blockCurrent.intCurrentCoords[i][1] == 0) { // Empty array column 0 and 1
                        if ((blockCurrent.intX / BoardPanel.intBlockSize) > -2) { // Prevent checking outside of intGrid array
                            if (blockCurrent.intCurrentCoords[i][2] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.intBlockSize) + i][(blockCurrent.intX / BoardPanel.intBlockSize) + 1] != 0) { // Check if block in intGrid below blockCurrent (empty array column 0 and 1)
                                return true;
                            }
                        }
                    }
                    if ((blockCurrent.intX / BoardPanel.intBlockSize) > -1) { // Prevent checking outside of intGrid array
                        if (blockCurrent.intCurrentCoords[i][1] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.intBlockSize) + i][(blockCurrent.intX / BoardPanel.intBlockSize) + 0] != 0) { // Check if block in intGrid below blockCurrent (empty array column 0)
                            return true;
                        }
                    }
                }
            }

            //Wall collision checks
            if (blockCurrent.intX - BoardPanel.intMove >= 0) { // Check if furthest left block can make move w/o exiting board on left (non-empty array column 0)
                return false;
            } else if (blockCurrent.intCurrentCoords[0][0] == 0 && blockCurrent.intCurrentCoords[1][0] == 0 && blockCurrent.intCurrentCoords[2][0] == 0 && blockCurrent.intCurrentCoords[3][0] == 0) { // Check if block coord array is empty in column 0
                if (blockCurrent.intCurrentCoords[0][1] == 0 && blockCurrent.intCurrentCoords[1][1] == 0 && blockCurrent.intCurrentCoords[2][1] == 0 && blockCurrent.intCurrentCoords[3][1] == 0) { // Check if block coord array is empty in column 1
                    if (blockCurrent.intX + BoardPanel.intBlockSize >= 0) { // Check if furthest left block can make move w/o exiting board on left (empty array column 0 and 1)
                        return false;
                    }
                }
                if (blockCurrent.intX >= 0) { // Check if furthest left block can make move without passing column 0 (empty array column 0)
                    return false;
                }
            }
        } else if (strSide.equalsIgnoreCase("right")) {
            //Block collision checks
            for (int i = 0; i < 4; i++) {
                if (blockCurrent.intCurrentCoords[i][3] != 0 && (blockCurrent.intX / BoardPanel.intBlockSize) < 6) { // Check if non-empty array column 3 (and prevent checking outside of intGrid array)
                    if (blockCurrent.intCurrentCoords[i][3] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.intBlockSize) + i][(blockCurrent.intX / BoardPanel.intBlockSize) + 4] != 0) { // Check if block in intGrid below blockCurrent (non-empty array column 3)
                        return true;
                    }
                } else if (blockCurrent.intCurrentCoords[i][3] == 0) { // Empty array column 3
                    if (blockCurrent.intCurrentCoords[i][2] == 0) { // Empty array column 2 and 3
                        if ((blockCurrent.intX / BoardPanel.intBlockSize) < 8) { // Prevent checking outside of intGrid array
                            if (blockCurrent.intCurrentCoords[i][1] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.intBlockSize) + i][(blockCurrent.intX / BoardPanel.intBlockSize) + 2] != 0) { // Check if block in intGrid below blockCurrent (empty array column 2 and 3)
                                return true;
                            }
                        }
                    }
                    if ((blockCurrent.intX / BoardPanel.intBlockSize) < 7) { // Prevent checking outside of intGrid array
                        if (blockCurrent.intCurrentCoords[i][2] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.intBlockSize) + i][(blockCurrent.intX / BoardPanel.intBlockSize) + 3] != 0) { // Check if block in intGrid below blockCurrent (empty array column 3)
                            return true;
                        }
                    }
                }
            }

            //Wall collision checks
            if (blockCurrent.intX + BoardPanel.intMove + (BoardPanel.intBlockSize * 4) <= BoardPanel.intXMax) { // Check if furthest left block can make move w/o exiting board on right (non-empty array column 3)
                return false;
            } else if (blockCurrent.intCurrentCoords[0][3] == 0 && blockCurrent.intCurrentCoords[1][3] == 0 && blockCurrent.intCurrentCoords[2][3] == 0 && blockCurrent.intCurrentCoords[3][3] == 0) { // Check if block coord array is empty in column 3
                if (blockCurrent.intCurrentCoords[0][2] == 0 && blockCurrent.intCurrentCoords[1][2] == 0 && blockCurrent.intCurrentCoords[2][2] == 0 && blockCurrent.intCurrentCoords[3][2] == 0) { // Check if block coord array is empty in column 2
                    if (blockCurrent.intX + (BoardPanel.intBlockSize * 3) <= BoardPanel.intXMax) { // Check if furthest left block can make move w/o exiting board on right (empty array column 2 and 3)
                        return false;
                    }
                }
                if (blockCurrent.intX + (BoardPanel.intBlockSize * 4) <= BoardPanel.intXMax) { // Check if furthest left block can make move w/o exiting board on right (empty array column 3)
                    return false;
                }
            }
        } else if (strSide.equalsIgnoreCase("down")) {
            //Block collision checks
            for (int i = 0; i < 4; i++) {
                if (blockCurrent.intCurrentCoords[3][i] != 0 && (blockCurrent.intY / BoardPanel.intBlockSize) < 16) { // Check if non-empty array row 3 (and prevent checking outside of intGrid array)
                    if (blockCurrent.intCurrentCoords[3][i] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.intBlockSize) + 4][(blockCurrent.intX / BoardPanel.intBlockSize) + i] != 0) { // Check if block in intGrid below blockCurrent (non-empty arrow row 3)
                        return true;
                    }
                } else if (blockCurrent.intCurrentCoords[3][i] == 0) { // Empty array row 3
                    if (blockCurrent.intCurrentCoords[2][i] == 0) { // Empty array row 2 and 3
                        if ((blockCurrent.intY / BoardPanel.intBlockSize) < 18) { // Prevent checking outside of intGrid array
                            if (blockCurrent.intCurrentCoords[1][i] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.intBlockSize) + 2][(blockCurrent.intX / BoardPanel.intBlockSize) + i] != 0) { // Check if block in intGrid below blockCurrent (empty arrow row 2 and 3)
                                return true;
                            }
                        }
                    }
                    if ((blockCurrent.intY / BoardPanel.intBlockSize) < 17) { // Prevent checking outside of intGrid array
                        if (blockCurrent.intCurrentCoords[2][i] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.intBlockSize) + 3][(blockCurrent.intX / BoardPanel.intBlockSize) + i] != 0) { // Check if block in intGrid below blockCurrent (empty arrow row 3, checking row 2)
                            return true;
                        } else if (blockCurrent.intCurrentCoords[1][i] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.intBlockSize) + 2][(blockCurrent.intX / BoardPanel.intBlockSize) + i] != 0) { // Check if block in intGrid below blockCurrent (empty arrow row 3 (or 2+3), checking row 1)
                            return true;
                        } else if (blockCurrent.intCurrentCoords[0][i] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.intBlockSize) + 1][(blockCurrent.intX / BoardPanel.intBlockSize) + i] != 0) { // Check if block in intGrid below blockCurrent (empty arrow row 3 (or 2+3), checking row 0)
                            return true;
                        }
                    }
                }
            }

            //Wall collision checks
            if (blockCurrent.intY + BoardPanel.intMove + (BoardPanel.intBlockSize * 4) <= BoardPanel.intYMax) { // Check if lowest block can make move w/o exiting board on bottom (non-empty array row 3)
                return false;
            } else if (blockCurrent.intCurrentCoords[3][0] == 0 && blockCurrent.intCurrentCoords[3][1] == 0 && blockCurrent.intCurrentCoords[3][2] == 0 && blockCurrent.intCurrentCoords[3][3] == 0) { // Check if block coord array is empty in row 3
                if (blockCurrent.intCurrentCoords[2][0] == 0 && blockCurrent.intCurrentCoords[2][1] == 0 && blockCurrent.intCurrentCoords[2][2] == 0 && blockCurrent.intCurrentCoords[2][3] == 0) { // Check if block coord array is empty in row 2
                    if (blockCurrent.intY + (BoardPanel.intBlockSize * 3) <= BoardPanel.intYMax) { // Check if lowest block can make move w/o exiting board on bottom (empty array row 2 and 3)
                        return false;
                    }
                }
                if (blockCurrent.intY + (BoardPanel.intBlockSize * 4) <= BoardPanel.intYMax) { // Check if lowest block can make move w/o exiting board on bottom (empty array row 3)
                    return false;
                }
            }


        } else if (strSide.equalsIgnoreCase("up")) {
            //Block collision checks

            //Wall collision checks
            if (blockCurrent.intY - BoardPanel.intMove >= 0) { // Check if highest block can make move w/o exiting board on top (non-empty array row 0)
                return false;
            } else if (blockCurrent.intCurrentCoords[0][0] == 0 && blockCurrent.intCurrentCoords[0][1] == 0 && blockCurrent.intCurrentCoords[0][2] == 0 && blockCurrent.intCurrentCoords[0][3] == 0) { // Check if block coord array is empty in row 0
                if (blockCurrent.intCurrentCoords[1][0] == 0 && blockCurrent.intCurrentCoords[1][1] == 0 && blockCurrent.intCurrentCoords[1][2] == 0 && blockCurrent.intCurrentCoords[1][3] == 0) { // Check if block coord array is empty in row 1
                    if (blockCurrent.intY + BoardPanel.intBlockSize >= 0) { // Check if lowest block can make move w/o exiting board on top (empty array row 0 and 1)
                        return false;
                    }
                }
                if (blockCurrent.intY >= 0) { // Check if highest block can make move w/o exiting board on top (empty array row 0)
                    return false;
                }
            }
        }
        return true;
    }

    public static void hardDrop(Block blockCurrent) {
        while (checkCollision(blockCurrent, "down") == false) { // Moves block down until collision, then ends loop
            moveDown(blockCurrent);
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
        } else if (blockCurrent.intX == (BoardPanel.intBlockSize * 7) && blockCurrent.intType == Block.IBlock) { // IBlock wallkick check for right wall
            if (blockCurrent.intRotation == 3) { // Right rotation (IBlock)
                moveLeft(blockCurrent); // Move block left
            }
        }
        // Wallkick: Bottom wall
        if (blockCurrent.intY == (BoardPanel.intBlockSize * 18)) {
            if (blockCurrent.intType == Block.IBlock) { // IBlock
                if (blockCurrent.intRotation == 0) { // Up rotation position (IBlock)
                    moveUp(blockCurrent); // Move block up twice
                    moveUp(blockCurrent);
                }
            } else if (blockCurrent.intType == Block.JBlock || blockCurrent.intType == Block.LBlock || blockCurrent.intType == Block.SBlock || blockCurrent.intType == Block.ZBlock || blockCurrent.intType == Block.TBlock) { // J,L,S,Z,T Blocks
                moveUp(blockCurrent); // Move block right
            }
        } else if (blockCurrent.intY == (BoardPanel.intBlockSize * 17) && blockCurrent.intType == Block.IBlock) { // IBlock wallkick check for bottom wall
            if (blockCurrent.intRotation == 2) { // Down rotation position (IBlock)
                moveUp(blockCurrent); // Move block up
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

    public static Block generateBlock() { // Generate a new Tetromino block
        Block blockCurrent = new Block(-1); // Default to -1 for return statement

        // Generate random integer from 1-7 to determine which block to generate
        // IBlock = 1, LBlock = 2, JBlock = 3, SBlock = 4, ZBlock = 5, TBlock = 6, OBlock = 7
        if (BoardPanel.intBag == 0) { // If intBag is at start, shuffle array of pieces
            List<Integer> pieceList = Arrays.asList(BoardPanel.pieceArray); // Convert pieceArray to an ArrayList
            Collections.shuffle(pieceList); // Shuffle pieceArray values inside pieceList ArrayList
            pieceList.toArray(BoardPanel.pieceArray); // Convert pieceList Arraylist --> pieceArray
        }
        BoardPanel.intRandom = BoardPanel.pieceArray[BoardPanel.intBag]; // Set intRandom value to next shuffled value of pieceArray

        if (BoardPanel.intBag == 6) { // If intBag is at final value (6), reset intBag to starting value (0)
            BoardPanel.intBag = 0;
        } else { // Increase intBag value by 1
            BoardPanel.intBag++;
        }

        //BoardPanel.intRandom = Block.IBlock; // debug to force select a block to spawn

        if (BoardPanel.intRandom == 1) { // Create an IBlock
            blockCurrent = new Block(Block.IBlock);
        } else if (BoardPanel.intRandom == 2) { // Create an LBlock
            blockCurrent = new Block(Block.LBlock);
        } else if (BoardPanel.intRandom == 3) { // Create an JBlock
            blockCurrent = new Block(Block.JBlock);
        } else if (BoardPanel.intRandom == 4) { // Create an SBlock
            blockCurrent = new Block(Block.SBlock);
        } else if (BoardPanel.intRandom == 5) { // Create an ZBlock
            blockCurrent = new Block(Block.ZBlock);
        } else if (BoardPanel.intRandom == 6) { // Create an TBlock
            blockCurrent = new Block(Block.TBlock);
        } else if (BoardPanel.intRandom == 7) { // Create an OBlock
            blockCurrent = new Block(Block.OBlock);
        }
        return (blockCurrent);
    }
}
