public class Controller {
    public void moveLeft() {
        
    }
    public void moveRight() {

    }

    public static Block generateBlock() { // Generate a new Tetromino (
        int intRandom = (int)(Math.random()*7);
        Block blockCurrent = new Block(-1);

        // IBlock = 0, LBlock = 1, JBlock = 2, SBlock = 3, ZBlock = 4, TBlock = 5, OBlock = 6
        if (intRandom == 0) { // IBlock
            blockCurrent = new Block(Block.IBlock);
        } else if (intRandom == 1) { // LBlock
            blockCurrent = new Block(Block.LBlock);
        } else if (intRandom == 2) { // JBlock
            blockCurrent = new Block(Block.JBlock);
        } else if (intRandom == 3) { // SBlock
            blockCurrent = new Block(Block.SBlock);
        } else if (intRandom == 4) { // ZBlock
            blockCurrent = new Block(Block.ZBlock);
        } else if (intRandom == 5) { // TBlock
           blockCurrent = new Block(Block.TBlock);
        } else if (intRandom == 6) { // OBlock
            blockCurrent = new Block(Block.OBlock);
        }
        return (blockCurrent);
    }
}
