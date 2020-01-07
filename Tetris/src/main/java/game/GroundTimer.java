package game;

import panels.Tetris;

public class GroundTimer implements Runnable { // Shorter timer to allow moves on ground (resets everytime a move/rotate is made once block has collided on bottom)
    // PROPERTIES
    public static boolean blnGroundAllow = true; // Default to true. After timer finishes, sets to false and places block.
    public static boolean blnMoving = true;
    public static boolean blnRun;

    // METHODS
    public void run() {
        while (Tetris.blnGameLoop == true) { // Loop that keeps thread alive. Runs while game loop is true
            if (blnRun == true) {
                blnGroundAllow = true; // Reset blnGroundAllow to true
                blnMoving = false; // Set before sleep incase movement methods set it to true while method is sleeping
                try {
                    Thread.sleep(1500); // Time allowed to move/rotate on ground before block is placed (1.5s)
                } catch (InterruptedException e) {
                }

                if (blnMoving == false) { // Block has not moved since last time
                    blnGroundAllow = false; // Reset blnGroundAllow to true
                    blnRun = false;
                } else { // Block has moved again, loop through again
                }
            }

            try { // This 1ms exists otherwise the code would not continously loop (likely since there is no code outside the if statement)
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }

    // CONSTRUCTOR
    public GroundTimer() {
    }
}

class TotalGroundTimer implements Runnable { // TOTAL TIMER TO ALLOW MOVES ON GROUND (If GroundTimer thread hasn't permanently placed the block yet, this timer will forcefully place the block)
    // PROPERTIES
    public static boolean blnTotalGroundAllow = true; // Default to true. After timer finishes, sets to false and places block.
    public static boolean blnRun;

    // METHODS
    public void run() {
        while (Tetris.blnGameLoop == true) { // Loop that keeps thread alive. Runs while game loop is true
            if (blnRun == true) { // Allow run only once
                blnTotalGroundAllow = true; // Reset blnTotalGroundAllow to true
                try {
                    Thread.sleep(4000); // Maximum time allowed to move on ground (4s)
                } catch (InterruptedException e) {
                }
                blnTotalGroundAllow = false; // Prevent block from moving once it touches the ground again
                blnRun = false; // Prevent code from running again until blnRun is set to true again by a different class
            }
            try { // This 1ms exists otherwise the code would not continously loop (likely since there is no code outside the if statement)
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }

    // CONSTRUCTOR
    public TotalGroundTimer() {
    }
}
