import javax.swing.*;
import java.awt.*;

public class MatrixPanel extends JPanel {
    //Game board array formatted in cols x rows (x,y)
    private int[][] board = new int[Constants.BOARD_COLS][Constants.TOTAL_BOARD_ROWS];
    
    //Create object variable to hold the current piece in hand
    private Block ghost;

    //Create a counter to count the number of gameLoops elapsed since the last drop



    public MatrixPanel() {
        // Initialize components, set layout
        setPreferredSize( new Dimension(Constants.BOARD_WIDTH, 
                                        Constants.BOARD_HEIGHT));




        board[1][1] = 3;
        board[2][1] = 4;
        board[3][1] = 5;
        board[4][1] = 6;
        board[1][2] = 7;
        board[2][2] = 1;
        board[3][2] = 2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                             RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                             RenderingHints.VALUE_STROKE_PURE);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                             RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
                             RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING,
                             RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        super.paintComponent(g2d);
        this.drawBoard(g2d);
    }

    public void setGhost(Block block) {
        removeAll();
        ghost = block;
        add(ghost);
    }

    public boolean tryLock() {
        ghost.lock(board);
        remove(ghost);
        ghost = null;

        return true;
    }

    public void  updateGhostCoords(int pixelX, int pixelY) {
        if (ghost == null) return;

        double boardPixelX = pixelX / (double)Constants.PIECE_SIZE;
        double boardPixelY = pixelY / (double)Constants.PIECE_SIZE;

        int boardX = (int)Math.round(boardPixelX);
        int boardY = (int)Math.round(boardPixelY);
        int nextClosestX = boardPixelX - boardX > 0 ? boardX + 1 : boardX - 1;
        int nextClosestY = boardPixelY - boardY > 0 ? boardY + 1 : boardY - 1;
        int otherClosestX = boardPixelX - boardX > 0 ? boardX - 1 : boardX + 1;
        int otherClosestY = boardPixelY - boardY > 0 ? boardY - 1 : boardY + 1;

        if (ghost.isValidPosition(boardX, boardY, board)) {
            ghost.setBoardCoords(boardX, boardY);
            return;
        }
        
        if (ghost.isValidPosition(nextClosestX, nextClosestY, board)) {
            ghost.setBoardCoords(nextClosestX, nextClosestY);
            return;
        }
        if (ghost.isValidPosition(otherClosestX, nextClosestY, board)) {
            ghost.setBoardCoords(otherClosestX, otherClosestY);
            return;
        }
        if (ghost.isValidPosition(nextClosestX, otherClosestY, board)) {
            ghost.setBoardCoords(nextClosestX, otherClosestY);
            return;
        }
        if (ghost.isValidPosition(otherClosestX, otherClosestY, board)) {
            ghost.setBoardCoords(otherClosestX, otherClosestY);
            return;
        }


    }

    public boolean identifyRows(){
        boolean rowsToClear = false;
        for (int indexY = Constants.TOTAL_BOARD_ROWS - 1; indexY >= 0; indexY--) {
            boolean rowFull = true;
            boolean rowEmpty = true;
            for (int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
                if (board[indexX][indexY] == 0) rowFull = false;
                if (board[indexX][indexY] != 0) rowEmpty = false;
            }
            if (rowEmpty) break;
            if (rowFull) {
                for (int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) board[indexX][indexY] = 8;
                rowsToClear = true;
            }
        }
        return rowsToClear;
    }

    public int clearRows() {
        int lines = 0;
        for (int indexY = 0; indexY < Constants.TOTAL_BOARD_ROWS; indexY++) {
            boolean rowFull = true;
            for (int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
                if (board[indexX][indexY] == 0) {
                    rowFull = false;
                    break;
                }
            }
            if (rowFull) {
                for (int writeY = indexY; writeY >= 0; writeY--) {
                    boolean rowFinish = true;
                    for (int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
                        board[indexX][writeY] = board[indexX][writeY - 1];
                        if (board[indexX][writeY] != 0) rowFinish = false;
                    }
                    if (rowFinish) break;
                }
                lines++;
            }
        }
        return lines;
    }


    public boolean lockedAboveBoard() {
        for (int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
            if (board[indexX][3] != 0) {
                return true;
            }
        }
        return false;
    }

    private void drawBoard(Graphics2D g) {
        for(int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
            for(int indexY = 0; indexY < Constants.BOARD_ROWS; indexY++) {
                Draw.square(indexX * Constants.PIECE_SIZE, 
                            indexY * Constants.PIECE_SIZE,
                            1,
                            board[indexX][indexY],
                            false,
                            g);
            }   
        }
    }

    public void destroyBoard(){
        identifyBoard();
    }

    public void identifyBoard(){
        for(int indexY = 0; indexY < Constants.BOARD_ROWS; indexY++) {
            for(int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
                if (board[indexX][indexY] != 0) {
                    board[indexX][indexY] = 8;
                }
            }
        }

    }
    

}
