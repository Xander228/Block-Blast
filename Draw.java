import javax.swing.*;
import java.awt.*;

public class Draw
{
    public static void square(int x, int y, int color, Graphics g) {
        int pieceEdgeWidth = Constants.PIECE_EDGE_WIDTH;
        if(color == 0) pieceEdgeWidth /= 5;
        //draw main square
        g.setColor(Constants.COLORS[color][0]);
        g.fillRect(x, y, Constants.PIECE_SIZE, Constants.PIECE_SIZE);
        
        //draw top edge - edge 1
        g.setColor(Constants.COLORS[color][1]);
        g.fillRect(x, y, Constants.PIECE_SIZE, pieceEdgeWidth);
        //draw bottom edge
        g.setColor(Constants.COLORS[color][2]);
        g.fillRect(x, Constants.PIECE_SIZE - pieceEdgeWidth + y,
                    Constants.PIECE_SIZE, pieceEdgeWidth);
        //draw left edge
        g.setColor(Constants.COLORS[color][3]);
        for(int i = 0; i <= pieceEdgeWidth; i++) {
            g.fillRect(x + i, y + i, 1, Constants.PIECE_SIZE - (2 * i));
        }
        
        //draw right edge
        g.setColor(Constants.COLORS[color][4]);
        for(int i = pieceEdgeWidth; i >= 0; i--) {
            g.fillRect(Constants.PIECE_SIZE - i + x - 1, y + i,
                    1, Constants.PIECE_SIZE - (2 * i));
        }
    }

    public static void ghostSquare(int x, int y, int color, Graphics g) {
        //draw main square
        g.setColor(Constants.COLORS[color][0].darker().darker());
        g.fillRect(x, y, Constants.PIECE_SIZE, Constants.PIECE_SIZE);

        //draw top edge - edge 1
        g.setColor(Constants.COLORS[color][1].darker().darker());
        g.fillRect(x, y, Constants.PIECE_SIZE, Constants.PIECE_EDGE_WIDTH);
        //draw bottom edge
        g.setColor(Constants.COLORS[color][2].darker().darker());
        g.fillRect(x, Constants.PIECE_SIZE - Constants.PIECE_EDGE_WIDTH + y,
                Constants.PIECE_SIZE, Constants.PIECE_EDGE_WIDTH);
        //draw left edge
        g.setColor(Constants.COLORS[color][3].darker().darker());
        for (int i = 0; i <= Constants.PIECE_EDGE_WIDTH; i++) {
            g.fillRect(x + i, y + i, 1, Constants.PIECE_SIZE - (2 * i));
        }

        //draw right edge
        g.setColor(Constants.COLORS[color][4].darker().darker());
        for (int i = Constants.PIECE_EDGE_WIDTH; i >= 0; i--) {
            g.fillRect(Constants.PIECE_SIZE - i + x - 1, y + i,
                    1, Constants.PIECE_SIZE - (2 * i));
        }
    }
}
