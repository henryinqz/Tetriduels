import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    // PROPERTIES
    public static final int intMove = 25; // # of pixels moved every time
    public static final int intBlockSize = 25; // Block size (25*25px)
    public static int intXMax = intBlockSize * 10; // 10 blocks wide
    public static int intYMax = intBlockSize * 20; // 20 blocks tall
    public static int[][] intGrid = new int[intXMax/intBlockSize][intYMax/intBlockSize]; // 10x20 array grid of board

    public static Block blockCurrent;

    // METHODS
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g; // Use Graphics2D instead of regular Graphics
        super.paintComponent(g2); // Clear previous drawings (Windows only); super JPanel (original) paintComponent method

        //Block
        g2.setColor(blockCurrent.colBlock);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blockCurrent.intCurrentCoords[i][j] != 0){
                    intGrid[i][j] = 1; // Array to determine where each block piece is
                    g2.fillRect(blockCurrent.intX + j * intBlockSize, blockCurrent.intY + i * intBlockSize, intBlockSize, intBlockSize);
                    g2.setColor(Color.WHITE); // Outline
                    g2.drawRect(blockCurrent.intX + j * intBlockSize, blockCurrent.intY + i * intBlockSize, intBlockSize, intBlockSize);
                    g2.setColor(blockCurrent.colBlock);
                } else {
                    intGrid[i][j] = 0;
                }
            }
        }

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
