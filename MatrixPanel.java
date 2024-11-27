import javax.swing.*;
import java.awt.*;

public class MatrixPanel extends JPanel {
    //Game board array formatted in cols x rows (x,y)
    private int[][] board = new int[Constants.BOARD_COLS][Constants.TOTAL_BOARD_ROWS];
    
    //Create object variable to hold the current piece in hand
    private Block block;

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



    public Block getPiece() {
        return this.block;
    }

    public void setPiece(Block tetromino) {
        this.block = tetromino;
        this.block.setBoardRelative(true);
        this.block.setBoardCoords(5, -2);
        int i = -1;
        while(tetromino.isOverlapped(board)) {
            tetromino.setBoardCoords(5, -2 + i);
            i--;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        this.drawBoard(g);
        if (block == null) return;
        block.drawGhost(board, g);
        block.draw(g);
    }

    public void lockTetromino() {
        block.lock(board);
        block.hide();
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

    private void drawBoard(Graphics g) {
        for(int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
            for(int indexY = 0; indexY < Constants.BOARD_ROWS; indexY++) {
                Draw.square(indexX * Constants.PIECE_SIZE, 
                            indexY * Constants.PIECE_SIZE, 
                            board[indexX][indexY],
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
