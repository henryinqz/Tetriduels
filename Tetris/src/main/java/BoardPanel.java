import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    // PROPERTIES
    public static final int intMove = 25; // # of pixels moved every time
    public static final int intBlockSize = 25; // Block size (25*25px)
    public static int intXMax = intBlockSize * 10; // 10 blocks wide
    public static int intYMax = intBlockSize * 20; // 20 blocks tall
    public static int[][] intGrid = new int[intYMax/intBlockSize][intXMax/intBlockSize]; // 10x20 array grid of board

    public static Block blockCurrent;

    // METHODS
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g; // Use Graphics2D instead of regular Graphics
        super.paintComponent(g2); // Clear previous drawings (Windows only); super JPanel (original) paintComponent method

        drawCurrentBlock(g2); // Draw current block on board

        if (blockCurrent.intY == intYMax - (intBlockSize * 2)) { // Block hits bottom
            storeOldBlocks(blockCurrent);
            /*for (int i=0;i<20;i++) { // Debug print old blocks array
                for (int j=0;j<10;j++) {
                    System.out.print(intGrid[i][j] + " ");
                }
                System.out.println("");
            }
            System.out.println("---");*/
            blockCurrent = Controller.generateBlock();
        }

        drawOldBlocks(g2);
        drawGridlines(g2);
    }
    private void drawCurrentBlock(Graphics2D g2) { // Draw current block on board
        g2.setColor(blockCurrent.colBlock);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blockCurrent.intCurrentCoords[i][j] != 0){
                    g2.fillRect(blockCurrent.intX + j * intBlockSize, blockCurrent.intY + i * intBlockSize, intBlockSize, intBlockSize);
                    g2.setColor(Color.WHITE); // Outline
                    g2.drawRect(blockCurrent.intX + j * intBlockSize, blockCurrent.intY + i * intBlockSize, intBlockSize, intBlockSize);
                    g2.setColor(blockCurrent.colBlock);
                }
            }
        }
    }

    private void storeOldBlocks(Block blockCurrent) {
        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
                if (blockCurrent.intCurrentCoords[a][b] != 0) {
                    switch (blockCurrent.intType) {
                        case (Block.IBlock):
                            intGrid[(blockCurrent.intY/intBlockSize) + a][(blockCurrent.intX/intBlockSize) + b] = -1;
                            break;
                        case (Block.LBlock):
                            intGrid[(blockCurrent.intY/intBlockSize) + a][(blockCurrent.intX/intBlockSize) + b] = 1;
                            break;
                        case (Block.JBlock):
                            intGrid[(blockCurrent.intY/intBlockSize) + a][(blockCurrent.intX/intBlockSize) + b] = 2;
                            break;
                        case (Block.SBlock):
                            intGrid[(blockCurrent.intY/intBlockSize) + a][(blockCurrent.intX/intBlockSize) + b] = 3;
                            break;
                        case (Block.ZBlock):
                            intGrid[(blockCurrent.intY/intBlockSize) + a][(blockCurrent.intX/intBlockSize) + b] = 4;
                            break;
                        case (Block.TBlock):
                            intGrid[(blockCurrent.intY/intBlockSize) + a][(blockCurrent.intX/intBlockSize) + b] = 5;
                            break;
                        case (Block.OBlock):
                            intGrid[(blockCurrent.intY/intBlockSize) + a][(blockCurrent.intX/intBlockSize) + b] = 6;
                            break;
                    }
                } else {
                    intGrid[(blockCurrent.intY/intBlockSize) + a][(blockCurrent.intX/intBlockSize) + b] = 0;
                }
            }
        }
    }

    private void drawOldBlocks(Graphics2D g2) {
        for (int c = 0; c < (intYMax/intBlockSize); c++) {
            for (int d = 0; d < (intXMax / intBlockSize); d++) {
                if (intGrid[c][d] != 0) {
                    //System.out.println(intGrid[c][d]);
                    switch (intGrid[c][d]) {
                        case -1:
                            g2.setColor(new Color(1, 240, 240)); // Cyan)
                            break;
                        case 1:
                            g2.setColor(new Color(240, 160, 0)); // Orange
                            break;
                        case 2:
                            g2.setColor(new Color(0, 1, 240)); // Blue
                            break;
                        case 3:
                            g2.setColor(new Color(0, 240, 0)); // Green
                            break;
                        case 4:
                            g2.setColor(new Color(240, 0, 0)); // Red
                            break;
                        case 5:
                            g2.setColor(new Color(160, 0, 240)); // Purple
                            break;
                        case 6:
                            g2.setColor(new Color(240, 240, 1)); // Yellow
                            break;
                    }
                    g2.fillRect(d * intBlockSize, c * intBlockSize, intBlockSize, intBlockSize);
                }
            }
        }
    }

    private void drawGridlines(Graphics2D g2) { // Draw gridlines on board
        // Gridlines
        g2.setColor(Color.BLACK);
        for (int a = 0; a <= intXMax/intBlockSize; a++) {
            g2.drawLine(a*intBlockSize, 0, a*intBlockSize, intYMax); // Vertical lines
        }
        for (int b = 0; b <= intYMax/intBlockSize; b++) {
            g2.drawLine(0, b*intBlockSize, intXMax, b*intBlockSize); // Horizontal lines
        }
    }

    // CONSTRUCTOR
    public BoardPanel() {
        super();
        //IBlock = new Block(Block.IBlock);
        blockCurrent = Controller.generateBlock();
    }
}
