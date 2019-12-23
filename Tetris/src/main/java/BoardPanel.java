import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    // PROPERTIES
    public static final int intMove = 25; // # of pixels moved every time
    public static final int intSize = 25; // Block size (25*25px)
    public static int intXMax = intSize * 10; // 10 blocks wide
    public static int intYMax = intSize * 20; // 20 blocks tall
    public static int[][] intGrid = new int[intXMax/intSize][intYMax/intSize]; // 10x20 array grid of board

    // METHODS
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g; // Use Graphics2D instead of regular Graphics
        super.paintComponent(g2); // Clear previous drawings (Windows only); super JPanel (original) paintComponent method
    }

    // CONSTRUCTOR
    public BoardPanel() {
        super();
    }
}
