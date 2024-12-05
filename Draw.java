import javax.swing.*;
import java.awt.*;

public class Draw
{
    public static void square(int x, int y, double scale, int color, boolean ghost, Graphics2D g) {
        int pieceSize = (int)(Constants.PIECE_SIZE * scale);
        int pieceEdgeWidth = (int)(Constants.PIECE_EDGE_WIDTH * scale);
        int pixelInset = 1;

        if(color == 0) {
            int emptyEdgeWidth = (int)(Constants.EMPTY_EDGE_WIDTH * scale);
            g.setColor(ghost ?
                    Constants.COLORS[color][1].darker().darker() :
                    Constants.COLORS[color][1]);

            g.fillRect(x, y, pieceSize, pieceSize);
            g.setColor(ghost ?
                    Constants.COLORS[color][0].darker().darker() :
                    Constants.COLORS[color][0]);

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
        g.setColor(ghost ?
                Constants.COLORS[color][2].darker().darker().darker() :
                Constants.COLORS[color][2].darker());
        g.fillRect(x, y, pieceSize, pieceSize);

        //draw main square
        g.setColor(ghost ?
                Constants.COLORS[color][0].darker().darker() :
                Constants.COLORS[color][0]);
        g.fillRect(x + pixelInset, y + pixelInset, pieceSize - (2 * pixelInset), pieceSize - (2 * pixelInset));


        //draw top edge
        g.setColor(ghost ?
                Constants.COLORS[color][1].darker().darker() :
                Constants.COLORS[color][1]);
        g.fillRect(x + pixelInset, y + pixelInset,
                pieceSize - 2 * pixelInset, pieceEdgeWidth - pixelInset);


        //draw bottom edge
        g.setColor(ghost ?
                Constants.COLORS[color][2].darker().darker() :
                Constants.COLORS[color][2]);
        g.fillRect(x + pixelInset, pieceSize - pieceEdgeWidth + y,
                pieceSize - 2 * pixelInset, pieceEdgeWidth - pixelInset);


        //draw left edge with angled edges by using triangles
        g.setColor(ghost ?
                Constants.COLORS[color][3].darker().darker() :
                Constants.COLORS[color][3]);
        g.fillRect(x + pixelInset, y + pieceEdgeWidth,
                pieceEdgeWidth - pixelInset, pieceSize - (2 * pieceEdgeWidth));
        g.fillPolygon(new int[]{x + pixelInset, x + pieceEdgeWidth, x + pixelInset},
                new int[]{y + pixelInset, y + pieceEdgeWidth, y + pieceSize - pieceEdgeWidth}, 3);
        g.fillPolygon(new int[]{x + pixelInset, x + pieceEdgeWidth, x + pixelInset},
                new int[]{y + pieceSize - pixelInset, y + pieceSize - pieceEdgeWidth, y + pieceEdgeWidth}, 3);

        //draw right edge
        g.setColor(ghost ?
                Constants.COLORS[color][4].darker().darker() :
                Constants.COLORS[color][4]);
        g.fillRect(x + pieceSize - pieceEdgeWidth, y + pieceEdgeWidth,
                pieceEdgeWidth - pixelInset, pieceSize - (2 * pieceEdgeWidth));
        g.fillPolygon(new int[]{x + pieceSize - pixelInset, x + pieceSize - pieceEdgeWidth, x + pieceSize - pixelInset},
                new int[]{y + pixelInset, y + pieceEdgeWidth, y + pieceSize - pieceEdgeWidth}, 3);
        g.fillPolygon(new int[]{x + pieceSize - pixelInset, x + pieceSize - pieceEdgeWidth, x + pieceSize - pixelInset},
                new int[]{y + pieceSize - pixelInset, y + pieceSize - pieceEdgeWidth, y + pieceEdgeWidth}, 3);

    }

    public static void glow(int x, int y, int color, Graphics2D g){
        int offset = 3;
        g.setColor(Constants.COLORS[color][1]);
        g.fillRect(x - offset, y - offset, Constants.PIECE_SIZE + offset * 2, Constants.PIECE_SIZE + offset * 2);
        offset = 2;
        g.setColor(Constants.COLORS[color][4]);
        g.fillRect(x - offset, y - offset, Constants.PIECE_SIZE + offset * 2, Constants.PIECE_SIZE + offset * 2);
        offset = 1;
        g.setColor(Constants.COLORS[color][0]);
        g.fillRect(x - offset, y - offset, Constants.PIECE_SIZE + offset * 2, Constants.PIECE_SIZE + offset * 2);

    }

}
