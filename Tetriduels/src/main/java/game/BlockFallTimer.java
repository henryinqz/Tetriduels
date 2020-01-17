package game;

import panels.*;

public class BlockFallTimer implements Runnable{
	// PROPERTIES
	public int intTimerInterval = 750; // Default falling interval 750ms
	
	// METHODS
	public void run(){
		blockDown(); // Call method for auto block down sleeps (threaded))
	}
	
	public void blockDown(){
		while (Tetriduels.blnGameLoop == true){ // Only run while game loop is running
			Controller.moveDown(BoardPanel.blockCurrent);
			try {
				Thread.sleep(intTimerInterval); // Delay before block moves down by itself
			} catch(InterruptedException e) {
			}
		}
	}
	
	// CONSTRUCTOR
	public BlockFallTimer(){
	}
}
