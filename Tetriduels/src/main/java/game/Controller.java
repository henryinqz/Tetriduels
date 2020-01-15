package game;

import panels.*;
import network.*;
import java.io.File;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;

public class Controller {
    // Properties
    public static boolean blnHardDrop = false;

    // Methods
    public static void moveLeft(Block blockCurrent) {
        if (checkCollision(blockCurrent, "left") == false) {
            blockCurrent.intX -= BoardPanel.MOVE; // Move left
            updateGhostBlock(blockCurrent); // Update position of ghost block
        }
        GroundTimer.blnMoving = true;
    }

    public static void moveRight(Block blockCurrent) {
        if (checkCollision(blockCurrent, "right") == false) {
            blockCurrent.intX += BoardPanel.MOVE; // Move right
            updateGhostBlock(blockCurrent); // Update position of ghost block
        }
        GroundTimer.blnMoving = true;
    }

    public static void moveDown(Block blockCurrent) {
        if (checkCollision(blockCurrent, "down") == false) {
            blockCurrent.intY += BoardPanel.MOVE; // Move down
            updateGhostBlock(blockCurrent); // Update position of ghost block
        }
        GroundTimer.blnMoving = true;
    }

    public static void moveUp(Block blockCurrent) {
        if (checkCollision(blockCurrent, "up") == false) {
            blockCurrent.intY -= BoardPanel.MOVE; // Move up
            updateGhostBlock(blockCurrent); // Update position of ghost block
        }
        GroundTimer.blnMoving = true;
    }

    public static boolean checkCollision(Block blockCurrent, String strSide) { // Method to check block collision (side = "up", "right", "left", "down"). True = collides, false = no collision
        // Directional checks
        if (strSide.equalsIgnoreCase("left")) {
            //Block collision checks
            for (int i = 0; i < 4; i++) {
                if (blockCurrent.intCurrentCoords[i][0] != 0 && (blockCurrent.intX / BoardPanel.BLOCKSIZE) > 0) { // Check if non-empty array column 0 (and prevent checking outside of intGrid array)
                    if (blockCurrent.intCurrentCoords[i][0] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + i][(blockCurrent.intX / BoardPanel.BLOCKSIZE) - 1] != 0) { // Check if block in intGrid below blockCurrent (non-empty array column 3)
                        return true;
                    }
                } else if (blockCurrent.intCurrentCoords[i][0] == 0) { // Empty array column 0
                    if (blockCurrent.intCurrentCoords[i][1] == 0) { // Empty array column 0 and 1
                        if ((blockCurrent.intX / BoardPanel.BLOCKSIZE) > -2) { // Prevent checking outside of intGrid array
                            if (blockCurrent.intCurrentCoords[i][2] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + i][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + 1] != 0) { // Check if block in intGrid below blockCurrent (empty array column 0 and 1)
                                return true;
                            }
                        }
                    }
                    if ((blockCurrent.intX / BoardPanel.BLOCKSIZE) > -1) { // Prevent checking outside of intGrid array
                        if (blockCurrent.intCurrentCoords[i][1] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + i][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + 0] != 0) { // Check if block in intGrid below blockCurrent (empty array column 0)
                            return true;
                        }
                    }
                }
            }

            //Wall collision checks
            if (blockCurrent.intX - BoardPanel.MOVE >= 0) { // Check if furthest left block can make move w/o exiting board on left (non-empty array column 0)
                return false;
            } else if (blockCurrent.intCurrentCoords[0][0] == 0 && blockCurrent.intCurrentCoords[1][0] == 0 && blockCurrent.intCurrentCoords[2][0] == 0 && blockCurrent.intCurrentCoords[3][0] == 0) { // Check if block coord array is empty in column 0
                if (blockCurrent.intCurrentCoords[0][1] == 0 && blockCurrent.intCurrentCoords[1][1] == 0 && blockCurrent.intCurrentCoords[2][1] == 0 && blockCurrent.intCurrentCoords[3][1] == 0) { // Check if block coord array is empty in column 1
                    if (blockCurrent.intX + BoardPanel.BLOCKSIZE >= 0) { // Check if furthest left block can make move w/o exiting board on left (empty array column 0 and 1)
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
                if (blockCurrent.intCurrentCoords[i][3] != 0 && (blockCurrent.intX / BoardPanel.BLOCKSIZE) < 6) { // Check if non-empty array column 3 (and prevent checking outside of intGrid array)
                    if (blockCurrent.intCurrentCoords[i][3] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + i][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + 4] != 0) { // Check if block in intGrid below blockCurrent (non-empty array column 3)
                        return true;
                    }
                } else if (blockCurrent.intCurrentCoords[i][3] == 0) { // Empty array column 3
                    if (blockCurrent.intCurrentCoords[i][2] == 0) { // Empty array column 2 and 3
                        if ((blockCurrent.intX / BoardPanel.BLOCKSIZE) < 8) { // Prevent checking outside of intGrid array
                            if (blockCurrent.intCurrentCoords[i][1] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + i][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + 2] != 0) { // Check if block in intGrid below blockCurrent (empty array column 2 and 3)
                                return true;
                            }
                        }
                    }
                    if ((blockCurrent.intX / BoardPanel.BLOCKSIZE) < 7) { // Prevent checking outside of intGrid array
                        if (blockCurrent.intCurrentCoords[i][2] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + i][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + 3] != 0) { // Check if block in intGrid below blockCurrent (empty array column 3)
                            return true;
                        }
                    }
                }
            }

            //Wall collision checks
            if (blockCurrent.intX + BoardPanel.MOVE + (BoardPanel.BLOCKSIZE * 4) <= BoardPanel.intXMax) { // Check if furthest left block can make move w/o exiting board on right (non-empty array column 3)
                return false;
            } else if (blockCurrent.intCurrentCoords[0][3] == 0 && blockCurrent.intCurrentCoords[1][3] == 0 && blockCurrent.intCurrentCoords[2][3] == 0 && blockCurrent.intCurrentCoords[3][3] == 0) { // Check if block coord array is empty in column 3
                if (blockCurrent.intCurrentCoords[0][2] == 0 && blockCurrent.intCurrentCoords[1][2] == 0 && blockCurrent.intCurrentCoords[2][2] == 0 && blockCurrent.intCurrentCoords[3][2] == 0) { // Check if block coord array is empty in column 2
                    if (blockCurrent.intX + (BoardPanel.BLOCKSIZE * 3) <= BoardPanel.intXMax) { // Check if furthest left block can make move w/o exiting board on right (empty array column 2 and 3)
                        return false;
                    }
                }
                if (blockCurrent.intX + (BoardPanel.BLOCKSIZE * 4) <= BoardPanel.intXMax) { // Check if furthest left block can make move w/o exiting board on right (empty array column 3)
                    return false;
                }
            }
        } else if (strSide.equalsIgnoreCase("down")) {
            //Block collision checks
            for (int i = 0; i < 4; i++) {
                if (blockCurrent.intCurrentCoords[3][i] != 0 && (blockCurrent.intY / BoardPanel.BLOCKSIZE) < 16+2) { // Check if non-empty array row 3 (and prevent checking outside of intGrid array)
                    if (blockCurrent.intCurrentCoords[3][i] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + 4][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + i] != 0) { // Check if block in intGrid below blockCurrent (non-empty arrow row 3)
                        return true;
                    }
                } else if (blockCurrent.intCurrentCoords[3][i] == 0) { // Empty array row 3
                    if (blockCurrent.intCurrentCoords[2][i] == 0) { // Empty array row 2 and 3
                        if ((blockCurrent.intY / BoardPanel.BLOCKSIZE) < 18+2) { // Prevent checking outside of intGrid array
                            if (blockCurrent.intCurrentCoords[1][i] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + 2][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + i] != 0) { // Check if block in intGrid below blockCurrent (empty arrow row 2 and 3, checking row 1)
                                return true;
                            } else if (blockCurrent.intCurrentCoords[0][i] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + 1][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + i] != 0) { // Check if block in intGrid below blockCurrent (empty arrow row 2 and 3), checking row 0)
                                return true;
                            }
                        }
                    }
                    if ((blockCurrent.intY/BoardPanel.BLOCKSIZE) < 17+2) { // Prevent checking outside of intGrid array
                        if (blockCurrent.intCurrentCoords[2][i] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + 3][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + i] != 0) { // Check if block in intGrid below blockCurrent (empty arrow row 3, checking row 2)
                            return true;
                        } else if (blockCurrent.intCurrentCoords[1][i] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + 2][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + i] != 0) { // Check if block in intGrid below blockCurrent (empty arrow row 3 (or 2+3), checking row 1)
                            return true;
                        } else if (blockCurrent.intCurrentCoords[0][i] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + 1][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + i] != 0) { // Check if block in intGrid below blockCurrent (empty arrow row 3 (or 2+3), checking row 0)
                            return true;
                        }
                    }
                }
            }

            //Wall collision checks
            if (blockCurrent.intY + BoardPanel.MOVE + (BoardPanel.BLOCKSIZE * 4) <= BoardPanel.intYMax) { // Check if lowest block can make move w/o exiting board on bottom (non-empty array row 3)
                return false;
            } else if (blockCurrent.intCurrentCoords[3][0] == 0 && blockCurrent.intCurrentCoords[3][1] == 0 && blockCurrent.intCurrentCoords[3][2] == 0 && blockCurrent.intCurrentCoords[3][3] == 0) { // Check if block coord array is empty in row 3
                if (blockCurrent.intCurrentCoords[2][0] == 0 && blockCurrent.intCurrentCoords[2][1] == 0 && blockCurrent.intCurrentCoords[2][2] == 0 && blockCurrent.intCurrentCoords[2][3] == 0) { // Check if block coord array is empty in row 2
                    if (blockCurrent.intY + (BoardPanel.BLOCKSIZE * 3) <= BoardPanel.intYMax) { // Check if lowest block can make move w/o exiting board on bottom (empty array row 2 and 3)
                        return false;
                    }
                }
                if (blockCurrent.intY + (BoardPanel.BLOCKSIZE * 4) <= BoardPanel.intYMax) { // Check if lowest block can make move w/o exiting board on bottom (empty array row 3)
                    return false;
                }
            }
        } else if (strSide.equalsIgnoreCase("up")) {
            //Block collision checks
            for (int i = 0; i < 4; i++) {
                if (blockCurrent.intCurrentCoords[0][i] != 0 && (blockCurrent.intY / BoardPanel.BLOCKSIZE) > 0) { // Check if non-empty array row 0 (and prevent checking outside of intGrid array)
                    if (blockCurrent.intCurrentCoords[0][i] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) - 1][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + i] != 0) { // Check if block in intGrid above blockCurrent (non-empty arrow row 0)
                        return true;
                    }
                } else if (blockCurrent.intCurrentCoords[0][i] == 0) { // Empty array row 0
                    if (blockCurrent.intCurrentCoords[1][i] == 0) { // Empty array row 0 and 1
                        if ((blockCurrent.intY / BoardPanel.BLOCKSIZE) < 18+2) { // Prevent checking outside of intGrid array
                            if (blockCurrent.intCurrentCoords[2][i] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + 1][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + i] != 0) { // Check if block in intGrid above blockCurrent (empty arrow row 0 and 1, checking row 2)
                                return true;
                            } else if (blockCurrent.intCurrentCoords[3][i] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + 2][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + i] != 0) { // Check if block in intGrid above blockCurrent (empty arrow row 0 and 1), checking row 3)
                                return true;
                            }
                        }
                    }
                    if ((blockCurrent.intY / BoardPanel.BLOCKSIZE) < 18+2) { // Prevent checking outside of intGrid array
                        if (blockCurrent.intCurrentCoords[1][i] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + 0][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + i] != 0) { // Check if block in intGrid above blockCurrent (empty arrow row 0, checking row 1)
                            return true;
                        } else if (blockCurrent.intCurrentCoords[2][i] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + 1][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + i] != 0) { // Check if block in intGrid above blockCurrent (empty arrow row 0 (or 0+1), checking row 2)
                            return true;
                        } else if (blockCurrent.intCurrentCoords[3][i] == 1 && BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + 2][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + i] != 0) { // Check if block in intGrid above blockCurrent (empty arrow row 0 (or 0+1), checking row 3)
                            return true;
                        }
                    }
                }
            }

            //Wall collision checks
            if (blockCurrent.intY - BoardPanel.MOVE >= 0) { // Check if highest block can make move w/o exiting board on top (non-empty array row 0)
                return false;
            } else if (blockCurrent.intCurrentCoords[0][0] == 0 && blockCurrent.intCurrentCoords[0][1] == 0 && blockCurrent.intCurrentCoords[0][2] == 0 && blockCurrent.intCurrentCoords[0][3] == 0) { // Check if block coord array is empty in row 0
                if (blockCurrent.intCurrentCoords[1][0] == 0 && blockCurrent.intCurrentCoords[1][1] == 0 && blockCurrent.intCurrentCoords[1][2] == 0 && blockCurrent.intCurrentCoords[1][3] == 0) { // Check if block coord array is empty in row 1
                    if (blockCurrent.intY + BoardPanel.BLOCKSIZE >= 0) { // Check if lowest block can make move w/o exiting board on top (empty array row 0 and 1)
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
        Utility.playSound(new File("assets/audio/blocks/harddrop.wav")); // Play hard drop sound
        blnHardDrop = true;
    }

    public static void updateGhostBlock(Block blockCurrent) {
        // Set properties of ghost block to match current block
        BoardPanel.blockGhost.intX = blockCurrent.intX;
        BoardPanel.blockGhost.intY = blockCurrent.intY;
        BoardPanel.blockGhost.intCurrentCoords = blockCurrent.intCurrentCoords;

        while (checkCollision(BoardPanel.blockGhost, "down") == false) { // Moves block down until collision, then ends loop
            moveDown(BoardPanel.blockGhost);
        }
    }

    public static void rotate(Block blockCurrent, String strDirection) {
        GroundTimer.blnMoving = true;
        boolean blnStuckCheck = false; // Default allow true
        // STUCK CHECK
        Block blockRotateCheck = new Block(blockCurrent); // Clone the current block to test rotation
        boolean blnHitsLeft = false;
        boolean blnHitsRight = false;
        boolean blnHitsTop = false;
        boolean blnHitsBottom = false;

        // Rotate blockRotateCheck to test rotation of blockCurrent
        if (strDirection.equalsIgnoreCase("left")) {
            blockRotateCheck.rotatePiece("left"); // Rotate the test piece left
        } else if (strDirection.equalsIgnoreCase("right")) {
            blockRotateCheck.rotatePiece("right"); // Rotate the test piece right
        }

        // Check if block is currently "sharing" a space w/ another block
        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
                try {
                    if (blockRotateCheck.intCurrentCoords[a][b] != 0 && BoardPanel.intGrid[(blockRotateCheck.intY / BoardPanel.BLOCKSIZE) + a][(blockRotateCheck.intX / BoardPanel.BLOCKSIZE) + b] != 0) { // Check if the test rotate block intersects/collides w/ any oldPiece within intGrid (w/o moving)
                        if (b == 0 || b == 1) { // Boolean to determine if rotateCheck intersects on left
                            blnHitsLeft = true;
                        } else if (b == 3 || b == 2) { // Boolean to determine if rotateCheck intersects on right
                            blnHitsRight = true;
                        }
                        if (a == 0 || a == 1) { // Boolean to determine if rotateCheck intersects on top
                            blnHitsTop = true;
                        } else if (a == 3 || a == 2) { // Boolean to determine if rotateCheck intersects on bottom
                            blnHitsBottom = true;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) { // Catch exception is block is beside left/right/top/bottom wall. Continues to cycle through the rest of array
                    if (b == 0 || b == 1) { // Boolean to determine if rotateCheck intersects on left
                        blnHitsLeft = true;
                    } else if (b == 3 || b == 2) { // Boolean to determine if rotateCheck intersects on right
                        blnHitsRight = true;
                    }
                    if (a == 0 || a == 1) { // Boolean to determine if rotateCheck intersects on top
                        blnHitsTop = true;
                    } else if (a == 3 || a == 2) { // Boolean to determine if rotateCheck intersects on bottom
                        blnHitsBottom = true;
                    }

                    if (blockRotateCheck.intType == Block.LBLOCK) { // LBlock exceptions
                        if (blockRotateCheck.intRotation == 3 && (blockRotateCheck.intY / BoardPanel.BLOCKSIZE) == 20) {
                            blnHitsRight = false;
                        } else if (blockRotateCheck.intRotation == 2) {
                            blnHitsBottom = false;
                            if (checkCollision(blockRotateCheck, "right") == true) {  // Check if collision on right side (Fixes bug that would allow rotation)
                                blnHitsRight = true;
                            }
                        } else if (blockRotateCheck.intRotation == 0) { //
                            if (checkCollision(blockRotateCheck, "right") == true) {  // Check if collision on right side (Fixes bug that would allow rotation)
                                blnHitsRight = true;
                            }
                        }
                    } else if (blockRotateCheck.intType == Block.JBLOCK && blockRotateCheck.intRotation == 2) { // JBlock expections
                        blnHitsTop = false;
                    } //  && (blockRotateCheck.intY/BoardPanel.BLOCKSIZE) == 19
                }
            }
        }

        // Stuck checks (blockRotateCheck cannot move left or right without colliding/intersecting w/ oldBlocks)
        if (blnHitsLeft == true && blnHitsRight == true) {
            blnStuckCheck = true;
        } else if (blnHitsTop == true && blnHitsBottom == true) {
            blnStuckCheck = true;
        }

        if (blockRotateCheck.intX <= 0) { // Out of bounds of array
            if (blnHitsLeft == true && blockRotateCheck.intY <= 20) { // If block hits left & block is on the bottom of the board
                if (checkCollision(blockRotateCheck, "right") == true) {  // If left side hits, make sure otherside is not hitting
                    blnStuckCheck = true;
                } else if (blockRotateCheck.intType == Block.IBLOCK) {
                    blockRotateCheck.intX += BoardPanel.MOVE; // Test moving IBlock right (moveRight method would not allow this since it would be checking for collision)
                    if (checkCollision(blockRotateCheck, "right") == true) { // If left side hits, make sure otherside is not hitting
                        blnStuckCheck = true;
                    }
                }
            } else if (blnHitsRight == true && blockRotateCheck.intY <= 20) { // If block hits right & block is on the bottom of the board
                blnStuckCheck = true;
            }
        } else if ((blockRotateCheck.intX / BoardPanel.BLOCKSIZE) >= 7) { // Out of bounds of array
            if (blnHitsLeft == true && blockRotateCheck.intY <= 20) { // If block is on the bottom of the board
                blnStuckCheck = true;
            } else if (blnHitsRight == true) {
                if (checkCollision(blockRotateCheck, "left") == true) { // If left side hits, make sure otherside is not hitting
                    blnStuckCheck = true;
                } else if (blockRotateCheck.intType == Block.IBLOCK) {
                    blockRotateCheck.intX -= BoardPanel.MOVE;
                    if (checkCollision(blockRotateCheck, "left") == true) { // If right side hits, make sure otherside is not hitting
                        blnStuckCheck = true;
                    }
                }
            }
        } else { // If block is not touching the side walls
            if (blnHitsTop == true && blnHitsBottom == false) { // If top hits, make sure otherside is not hitting
                if (checkCollision(blockRotateCheck, "down") == true) {
                    blnStuckCheck = true;
                }
            } else if (blnHitsTop == false && blnHitsBottom == true) { // If bottom hits, make sure otherside is not hitting
                if (checkCollision(blockRotateCheck, "up") == true) {
                    blnStuckCheck = true;
                }
            }
        }

        if ((blockRotateCheck.intY/BoardPanel.BLOCKSIZE) <= 18 && blockRotateCheck.intType == Block.IBLOCK) { // Catch IBlock before it can go into the else if (where it can throw an exception)
            if (blnHitsLeft == true && blnHitsRight == false) { // If left side hits, make sure otherside is not hitting
                if (checkCollision(blockRotateCheck, "right") == true) {
                    blnStuckCheck = true;
                }
            } else if (blnHitsLeft == false && blnHitsRight == true) { // If right side hits, make sure otherside is not hitting
                if (checkCollision(blockRotateCheck, "left") == true) {
                    blnStuckCheck = true;
                }
            }
        } else if ((blockRotateCheck.intY/BoardPanel.BLOCKSIZE) <= 19 && blockRotateCheck.intType != Block.IBLOCK) { // Only check if not in bottom row & not IBlock
            if (blnHitsLeft == true && blnHitsRight == false) { // If left side hits, make sure otherside is not hitting
                if (checkCollision(blockRotateCheck, "right") == true) {
                    blnStuckCheck = true;
                }
            } else if (blnHitsLeft == false && blnHitsRight == true) { // If right side hits, make sure otherside is not hitting
                if (checkCollision(blockRotateCheck, "left") == true) {
                    blnStuckCheck = true;
                }
            }
        }

        // WALLKICKS
        if (blnStuckCheck == false) { // If stuck checks allowed rotate, then run wallkick checks
            // Wallkick: Side walls (prevents rotation from sending block out of screen)
            if (blockCurrent.intX < 0) { // Wallkick checks for left wall
                if (blockCurrent.intType == Block.IBLOCK) { // IBlock
                    if (blockCurrent.intRotation == 1) { // Left rotation position (IBlock)
                        moveRight(blockCurrent); // Move block right
                    } else if (blockCurrent.intRotation == 3) { // Right rotation position (IBlock)
                        if ((blockCurrent.intX/BoardPanel.BLOCKSIZE) == -1) { // IBlock is not against the wall, but still close enough that rotation could go through
                            moveRight(blockCurrent); // Move block right once
                        } else { // IBlock is against the wall
                            moveRight(blockCurrent); // Move block right twice
                            moveRight(blockCurrent);
                        }
                    }
                } else if (blockCurrent.intType == Block.JBLOCK || blockCurrent.intType == Block.LBLOCK || blockCurrent.intType == Block.SBLOCK || blockCurrent.intType == Block.ZBLOCK || blockCurrent.intType == Block.TBLOCK) { // J,L,S,Z,T Blocks
                    moveRight(blockCurrent); // Move block right
                }
            } else if (blockCurrent.intX == (BoardPanel.BLOCKSIZE * 8)) { // Wallkick checks for right wall
                if (blockCurrent.intType == Block.JBLOCK || blockCurrent.intType == Block.LBLOCK || blockCurrent.intType == Block.SBLOCK || blockCurrent.intType == Block.ZBLOCK || blockCurrent.intType == Block.TBLOCK) { // J,L,S,Z,T Blocks
                    moveLeft(blockCurrent); // Move block left
                }
            } else if (blockCurrent.intX >= (BoardPanel.BLOCKSIZE * 7) && blockCurrent.intType == Block.IBLOCK) { // IBlock wallkick check for right wall
                if (blockCurrent.intRotation == 1) { // Left rotation (IBlock)
                    if ((blockCurrent.intX/BoardPanel.BLOCKSIZE) == 7) { // IBlock not against wall
                        moveLeft(blockCurrent); // Move block left
                    } else { // IBlock against wall
                        moveLeft(blockCurrent); // Move block left twice
                        moveLeft(blockCurrent);
                    }
                } else if (blockCurrent.intRotation == 3) { // Right rotation (IBlock)
                    moveLeft(blockCurrent); // Move block left
                }
            }
            // Wallkick: Bottom wall
            if (blockCurrent.intY == (BoardPanel.BLOCKSIZE * (18+2))) {
                if (blockCurrent.intType == Block.IBLOCK) { // IBlock
                    if (blockCurrent.intRotation == 0) { // Up rotation position (IBlock)
                        moveUp(blockCurrent); // Move block up twice
                        moveUp(blockCurrent);
                    }
                } else if (blockCurrent.intType == Block.JBLOCK || blockCurrent.intType == Block.LBLOCK || blockCurrent.intType == Block.SBLOCK || blockCurrent.intType == Block.ZBLOCK || blockCurrent.intType == Block.TBLOCK) { // J,L,S,Z,T Blocks
                    moveUp(blockCurrent); // Move block right
                }
            } else if (blockCurrent.intY == (BoardPanel.BLOCKSIZE * (17+2)) && blockCurrent.intType == Block.IBLOCK) { // IBlock wallkick check for bottom wall
                if (blockCurrent.intRotation == 2 || blockCurrent.intRotation == 0) { // Up/down rotation position (IBlock)
                    moveUp(blockCurrent); // Move block up
                }
            }
            // Wallkick: Top wall (rare)
            if (blockCurrent.intY < 0) { // Block is above 0
                //System.out.println("Wallkicked off top wall");
                //moveDown(blockCurrent); // Move block down
                blockCurrent.intY = 0; // Reset block to row 0 of board (moveDown would've required more checks for IBlock)
            }
        }

        // BLOCKKICKS
        if (blnStuckCheck == false) { // If stuck checks allowed rotate, then run blockkick checks
            // Blockkick: Side walls (prevents rotation from sending block into other blocks/oldBlocks)
            if (checkCollision(blockCurrent, "left")) { // Blockkick checks for blocks on left
                if (blockCurrent.intType == Block.IBLOCK) { // IBlock
                    if (blockCurrent.intRotation == 1) { // Left rotation position (IBlock)
                        moveRight(blockCurrent); // Move block right
                    } else if (blockCurrent.intRotation == 3) { // Right rotation position (IBlock)
                        moveRight(blockCurrent); // Move block right twice
                        moveRight(blockCurrent);
                    }
                } else if (blockCurrent.intType == Block.JBLOCK || blockCurrent.intType == Block.LBLOCK || blockCurrent.intType == Block.SBLOCK || blockCurrent.intType == Block.ZBLOCK || blockCurrent.intType == Block.TBLOCK) { // J,L,S,Z,T Blocks
                    if (blockCurrent.intRotation == 3) { // Right rotation position
                        moveRight(blockCurrent); // Move block right
                    } // Else, don't move block if in up/left/down rotation position
                }
            } else if (checkCollision(blockCurrent, "right")) { // Blockkick checks for blocks on right
                if (blockCurrent.intType == Block.IBLOCK) { // IBlock
                    if (blockCurrent.intRotation == 1) { // Left rotation (IBlock)
                        moveLeft(blockCurrent); // Move block left twice
                        moveLeft(blockCurrent);
                    } else if (blockCurrent.intRotation == 3) { // Right rotation position (IBlock)
                        moveLeft(blockCurrent); // Move block left
                    }
                } else if (blockCurrent.intType == Block.JBLOCK || blockCurrent.intType == Block.LBLOCK || blockCurrent.intType == Block.SBLOCK || blockCurrent.intType == Block.ZBLOCK || blockCurrent.intType == Block.TBLOCK) { // J,L,S,Z,T Blocks
                    if (blockCurrent.intRotation == 1) { // Left rotation position
                        moveLeft(blockCurrent); // Move block left
                    } // Else, don't move block if in up/down/right rotation position
                }
            }

            // Blockick: Bottom wall
            if (checkCollision(blockCurrent, "down")) {
                if (blockCurrent.intType == Block.IBLOCK) { // IBlock
                    if (blockCurrent.intRotation == 0) { // Up rotation (IBlock)
                        moveUp(blockCurrent); // Move block up twice
                        moveUp(blockCurrent);
                    } else if (blockCurrent.intRotation == 2) { // Down rotation position (IBlock)
                        moveUp(blockCurrent); // Move block down
                    }
                } else if (blockCurrent.intType == Block.JBLOCK || blockCurrent.intType == Block.LBLOCK || blockCurrent.intType == Block.SBLOCK || blockCurrent.intType == Block.ZBLOCK || blockCurrent.intType == Block.TBLOCK) { // J,L,S,Z,T Blocks
                    if (blockCurrent.intRotation == 0) { // Up rotation position
                        moveUp(blockCurrent); // Move block up
                    } // Else, don't move block if in left/down/right rotation position
                }
            } else if (blockCurrent.intType == Block.IBLOCK) { // IBlock
                if (blockCurrent.intRotation == 0) { // Up rotation (IBlock)
                    //try {
                    if (BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + 3][(blockCurrent.intX / BoardPanel.BLOCKSIZE)] != 0 || BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + 3][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + 1] != 0
                            || BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + 3][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + 2] != 0 || BoardPanel.intGrid[(blockCurrent.intY / BoardPanel.BLOCKSIZE) + 3][(blockCurrent.intX / BoardPanel.BLOCKSIZE) + 3] != 0) { // Check if there is a 1 block gap between another block (IBlock in up rotation position)
                        moveUp(blockCurrent); // Move block up
                    }
                    /*} catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                        System.out.println("its here");
                    }*/
                }
            }

            // Blockkick: Top wall
            if (checkCollision(blockCurrent, "up")) { // Blockkick checks for blocks above
                if (blockCurrent.intType == Block.IBLOCK) { // IBlock
                    if (blockCurrent.intRotation == 0) { // Up rotation (IBlock)
                        moveDown(blockCurrent); // Move block down
                    } else if (blockCurrent.intRotation == 2) { // Down rotation position (IBlock)
                        moveDown(blockCurrent); // Move block down twice
                        moveDown(blockCurrent);
                    }
                } else if (blockCurrent.intType == Block.JBLOCK || blockCurrent.intType == Block.LBLOCK || blockCurrent.intType == Block.SBLOCK || blockCurrent.intType == Block.ZBLOCK || blockCurrent.intType == Block.TBLOCK) { // J,L,S,Z,T Blocks
                    if (blockCurrent.intRotation == 2) { // Down rotation position
                        moveDown(blockCurrent); // Move block down
                    } // Else, don't move block if in Up/left/right rotation position
                }
            } else if (blockCurrent.intType == Block.IBLOCK) { // IBlock
                if (blockCurrent.intRotation == 2) { // Down rotation (IBlock)
                    if (BoardPanel.intGrid[(blockCurrent.intY/BoardPanel.BLOCKSIZE)][(blockCurrent.intX/BoardPanel.BLOCKSIZE)] != 0 || BoardPanel.intGrid[(blockCurrent.intY/BoardPanel.BLOCKSIZE)][(blockCurrent.intX/BoardPanel.BLOCKSIZE)+1] != 0
                            || BoardPanel.intGrid[(blockCurrent.intY/BoardPanel.BLOCKSIZE)][(blockCurrent.intX/BoardPanel.BLOCKSIZE)+2] != 0 || BoardPanel.intGrid[(blockCurrent.intY/BoardPanel.BLOCKSIZE)][(blockCurrent.intX/BoardPanel.BLOCKSIZE)+3] != 0) { // Check if there is a 1 block gap between another block (IBlock in down rotation position)
                        moveDown(blockCurrent); // Move block down
                    }
                }
            }
        }


        if (blnStuckCheck == false) {
            blockCurrent.rotatePiece(strDirection); // Rotate piece
            updateGhostBlock(blockCurrent); // Update position of ghost block
        }
    }

    public static Block generateBlock() { // Generate a new Tetromino block
        Block blockCurrent = new Block(-1); // Default to -1 for return statement

        if (BoardPanel.intBag > 6) { // If intBag is past final value of (6), reset intBag to starting value (0)
            BoardPanel.intBag = 0;
        }

        // Generate random integers from 1-7 to determine which block to generate
        // IBlock = 1, LBlock = 2, JBlock = 3, SBlock = 4, ZBlock = 5, TBlock = 6, OBlock = 7
        if (BoardPanel.intBag == -1) { // Initial intBag value. Only runs for the first generated pieceArray
            // Generate/shuffle initial pieceArray
            List<Integer> pieceList = Arrays.asList(BoardPanel.pieceArray); // Convert pieceArray to an ArrayList
            Collections.shuffle(pieceList); // Shuffle pieceArray values inside pieceList ArrayList
            pieceList.toArray(BoardPanel.pieceArray); // Convert pieceList ArrayList --> pieceArray

            // Generate/shuffle next pieceArray
            pieceList = Arrays.asList(BoardPanel.pieceArrayNext); // Convert pieceArray to an ArrayList
            Collections.shuffle(pieceList); // Shuffle pieceArray values inside pieceList ArrayList
            pieceList.toArray(BoardPanel.pieceArrayNext); // Convert pieceList ArrayList --> pieceArrayNext

            BoardPanel.intBag++; // Increase from -1 --> 0
        } else if (BoardPanel.intBag == 0) { // If intBag is at start, shuffle array of pieces
            System.arraycopy(BoardPanel.pieceArrayNext, 0, BoardPanel.pieceArray, 0, 7); // Set current pieceArray to the next pieceArray that is already generated
            //BoardPanel.pieceArray = BoardPanel.pieceArrayNext; // Set current pieceArray to the next pieceArray that is already generated

            // Generate/shuffle next pieceArray (pieceArrayNext)
            List<Integer> pieceList = Arrays.asList(BoardPanel.pieceArrayNext); // Convert pieceArray to an ArrayList
            Collections.shuffle(pieceList); // Shuffle pieceArray values inside pieceList ArrayList
            pieceList.toArray(BoardPanel.pieceArrayNext); // Convert pieceList ArrayList --> pieceArrayNext
        }
        BoardPanel.intRandom = BoardPanel.pieceArray[BoardPanel.intBag]; // Set intRandom value to next shuffled value of pieceArray
        BoardPanel.intBag++; // Increase intBag value by 1

        if (BoardPanel.intRandom == 1) { // Create an IBlock
            blockCurrent = new Block(Block.IBLOCK);
        } else if (BoardPanel.intRandom == 2) { // Create an LBlock
            blockCurrent = new Block(Block.LBLOCK);
        } else if (BoardPanel.intRandom == 3) { // Create an JBlock
            blockCurrent = new Block(Block.JBLOCK);
        } else if (BoardPanel.intRandom == 4) { // Create an SBlock
            blockCurrent = new Block(Block.SBLOCK);
        } else if (BoardPanel.intRandom == 5) { // Create an ZBlock
            blockCurrent = new Block(Block.ZBLOCK);
        } else if (BoardPanel.intRandom == 6) { // Create an TBlock
            blockCurrent = new Block(Block.TBLOCK);
        } else if (BoardPanel.intRandom == 7) { // Create an OBlock
            blockCurrent = new Block(Block.OBLOCK);
        }
        BoardPanel.blockGhost = new Block(BoardPanel.intRandom); // Set ghost block shape
        return (blockCurrent);
    }

    public static Block generateBlock(int intBlockType) { // Generate a specific Tetromino block
        try { // Try catch incase incorrect block type entered
            Block blockCurrent = new Block(intBlockType); // Create new block w/ specific block type
            BoardPanel.blockGhost = new Block(intBlockType); // Set ghost block shape
            return (blockCurrent);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect blockType used to generateBlock");
            return null;
        }
    }

    public static Block holdBlock(Block blockCurrent) { // Hold the current block, and return a block that will become the new current block
        if (BoardPanel.blockHeld == null) { // If no block currently held
            BoardPanel.blockHeld = blockCurrent;
            return (generateBlock()); // Return a newly generated block
        } else { // Block already being held; swap w/ current block
            int intHeldType; // Temporary integer to allow for switching between blockCurrent <--> blockHeld

            intHeldType = BoardPanel.blockHeld.intType; // Store the type of 'held' block (to generate a new current block)
            BoardPanel.blockHeld = blockCurrent; // Set 'held' block to the 'current' block
            BoardPanel.blockHeld.rotatePiece("default"); // Rotate 'held' block to default positioning
            //BoardPanel.blockHeld = generateBlock(blockCurrent.intType); // Set 'held' block to the 'current' block

            blockCurrent = generateBlock(intHeldType); // Set 'current' block to the 'temp' block ('held' block)
            blockCurrent.blnHeldBefore = true; // Boolean to prevent holding the same block multiple times

            return (blockCurrent); // Returns generated block instead of just blockCurrent to reset X,Y,rotation,etc of block
        }
    }
}