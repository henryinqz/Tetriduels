public class BlockFallTimer implements Runnable{
	// PROPERTIES
	public int intTimerInterval = 750; // Default falling interval 750ms 
	//public static boolean blnGameLoop = true; // Later make this static in Tetris.java or GUI.java
	
	// METHODS
	public void run(){
		blockDown();
	}
	
	public void blockDown(){
		while (Tetris.blnGameLoop == true){
			Controller.moveDown(BoardPanel.blockCurrent);
			try {
				Thread.sleep(750); // Replace w/ Timer.schedule
			} catch(InterruptedException e) {	
			}
		}
	}
	
	// CONSTRUCTOR
	public BlockFallTimer(){
	}
}
