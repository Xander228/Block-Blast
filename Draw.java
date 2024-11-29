import javax.swing.*;
import java.awt.*;

public class Draw
{
    public static void square(int x, int y, double scale, int color, Graphics2D g) {
        int pieceSize = (int)(Constants.PIECE_SIZE * scale);
        int pieceEdgeWidth = (int)(Constants.PIECE_EDGE_WIDTH * scale);
        int pixelInset = 1;

        if(color == 0) {
            int emptyEdgeWidth = (int)(Constants.EMPTY_EDGE_WIDTH * scale);
            g.setColor(Constants.COLORS[color][1]);
            g.fillRect(x, y, pieceSize, pieceSize);
            g.setColor(Constants.COLORS[color][0]);
            //draw a square with rounded corners
            g.fillRoundRect(
                    x + emptyEdgeWidth,
                    y + emptyEdgeWidth,
                    pieceSize - (2 * emptyEdgeWidth),
                    pieceSize - (2 * emptyEdgeWidth),
                    Constants.EMPTY_EDGE_RADIUS,
                    Constants.EMPTY_EDGE_RADIUS);
            return;
        }
        g.setColor(Constants.COLORS[color][2].darker());
        g.fillRect(x, y, pieceSize, pieceSize);

        //draw main square
        g.setColor(Constants.COLORS[color][0]);
        g.fillRect(x + pixelInset, y + pixelInset, pieceSize - (2 * pixelInset), pieceSize - (2 * pixelInset));


        //draw top edge
        g.setColor(Constants.COLORS[color][1]);
        g.fillRect(x + pixelInset, y + pixelInset,
                pieceSize - 2 * pixelInset, pieceEdgeWidth - pixelInset);


        //draw bottom edge
        g.setColor(Constants.COLORS[color][2]);
        g.fillRect(x + pixelInset, pieceSize - pieceEdgeWidth + y,
                pieceSize - 2 * pixelInset, pieceEdgeWidth - pixelInset);


        //draw left edge with angled edges by using triangles
        g.setColor(Constants.COLORS[color][3]);
        g.fillRect(x + pixelInset, y + pieceEdgeWidth,
                pieceEdgeWidth - pixelInset, pieceSize - (2 * pieceEdgeWidth));
        g.fillPolygon(new int[]{x + pixelInset, x + pieceEdgeWidth, x + pixelInset},
                new int[]{y + pixelInset, y + pieceEdgeWidth, y + pieceSize - pieceEdgeWidth}, 3);
        g.fillPolygon(new int[]{x + pixelInset, x + pieceEdgeWidth, x + pixelInset},
                new int[]{y + pieceSize - pixelInset, y + pieceSize - pieceEdgeWidth, y + pieceEdgeWidth}, 3);

        //draw right edge
        g.setColor(Constants.COLORS[color][4]);
        g.fillRect(x + pieceSize - pieceEdgeWidth, y + pieceEdgeWidth,
                pieceEdgeWidth - pixelInset, pieceSize - (2 * pieceEdgeWidth));
        g.fillPolygon(new int[]{x + pieceSize - pixelInset, x + pieceSize - pieceEdgeWidth, x + pieceSize - pixelInset},
                new int[]{y + pixelInset, y + pieceEdgeWidth, y + pieceSize - pieceEdgeWidth}, 3);
        g.fillPolygon(new int[]{x + pieceSize - pixelInset, x + pieceSize - pieceEdgeWidth, x + pieceSize - pixelInset},
                new int[]{y + pieceSize - pixelInset, y + pieceSize - pieceEdgeWidth, y + pieceEdgeWidth}, 3);




    }

    public static void ghostSquare(int x, int y, int color, Graphics2D g) {
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
