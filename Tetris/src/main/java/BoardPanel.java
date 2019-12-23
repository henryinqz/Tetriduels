import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    // PROPERTIES
    public static final int intMove = 25; // # of pixels moved every time
    public static final int intSize = 25; // Block size (25*25px)
    public static int intXMax = intSize * 10; // 10 blocks wide
    public static int intYMax = intSize * 20; // 20 blocks tall
    public static int[][] intGrid = new int[intXMax/intSize][intYMax/intSize]; // 10x20 array grid of board

    public Block IBlock;

    // METHODS
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g; // Use Graphics2D instead of regular Graphics
        super.paintComponent(g2); // Clear previous drawings (Windows only); super JPanel (original) paintComponent method

        g2.setColor(IBlock.colBlock);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (IBlock.intCoordsArray[0][i][j] != 0){
                    g2.fillRect(j * intSize, i * intSize, intSize, intSize);
                    g2.setColor(Color.WHITE); // Outline
                    g2.drawRect(j * intSize, i * intSize, intSize, intSize);
                    g2.setColor(IBlock.colBlock);
                }
            }
        }
    }

    // CONSTRUCTOR
    public BoardPanel() {
        super();
        IBlock = new Block(Block.IBlock);
    }
}
