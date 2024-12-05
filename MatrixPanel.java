import javax.swing.*;
import java.awt.*;

public class MatrixPanel extends JPanel {
    //Game board array formatted in cols x rows (x,y)
    private int[][] board = new int[Constants.BOARD_COLS][Constants.BOARD_ROWS];
    
    //Create object variable to hold the current piece in hand
    private Block ghost;

    //Create a counter to count the number of gameLoops elapsed since the last drop



    public MatrixPanel() {
        // Initialize components, set layout
        setPreferredSize( new Dimension(Constants.BOARD_WIDTH, 
                                        Constants.BOARD_HEIGHT));
    }

    public void setGhost(Block block) {
        removeAll();
        ghost = block;
        add(ghost);
    }

    public boolean tryLock() {
        if (ghost == null) return false;
        if (!ghost.isValidPosition(board)) return false;
        ghost.lock(board);
        remove(ghost);
        ScorePanel scorePanel = MainFrame.getGamePanel().getScorePanel();
        scorePanel.addLines(clearLines());
        if(isBoardEmpty()) scorePanel.addScore(300);
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


        if (ghost.isValidPosition(boardX, boardY, board))
            ghost.setBoardCoords(boardX, boardY);

        else if (ghost.isValidPosition(boardX, nextClosestY, board))
            ghost.setBoardCoords(boardX, nextClosestY);
        else if (ghost.isValidPosition(nextClosestX, boardY, board))
            ghost.setBoardCoords(nextClosestX, boardY);
        else if (ghost.isValidPosition(otherClosestX, boardY, board))
            ghost.setBoardCoords(otherClosestX, boardY);
        else if (ghost.isValidPosition(boardX, otherClosestY, board))
            ghost.setBoardCoords(boardX, otherClosestY);

        else if (ghost.isValidPosition(nextClosestX, nextClosestY, board))
            ghost.setBoardCoords(nextClosestX, nextClosestY);
        else if (ghost.isValidPosition(otherClosestX, nextClosestY, board))
            ghost.setBoardCoords(otherClosestX, nextClosestY);
        else if (ghost.isValidPosition(nextClosestX, otherClosestY, board))
            ghost.setBoardCoords(nextClosestX, otherClosestY);
        else if (ghost.isValidPosition(otherClosestX, otherClosestY, board))
            ghost.setBoardCoords(otherClosestX, otherClosestY);

        else ghost.setBoardCoords(Constants.BOARD_COLS, Constants.BOARD_ROWS);
        repaint();
    }

    public boolean[] identifyRows(){
        boolean[] rows = new boolean[Constants.BOARD_ROWS];
        int blockWidth = 0;
        int blockHeight = 0;
        if (ghost != null) {
            blockHeight = ghost.getPieceMap().length;
            blockWidth = ghost.getPieceMap()[0].length;
        }
        for (int indexY = 0; indexY < Constants.BOARD_ROWS; indexY++) {
            boolean rowFull = true;
            for (int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
                boolean ghostEmpty = true;
                if (ghost != null && indexX >= ghost.getBoardX() && indexX < ghost.getBoardX() + blockWidth &&
                        indexY >= ghost.getBoardY() && indexY < ghost.getBoardY() + blockHeight) {
                    if (ghost.getPieceMap()[indexY - ghost.getBoardY()][indexX - ghost.getBoardX()] != 0) ghostEmpty = false;
                }

                if (board[indexX][indexY] == 0 && ghostEmpty) rowFull = false;

            }
            if (rowFull) {
                rows[indexY] = true;
            }
        }
        return rows;
    }

    public boolean[] identifyCols(){
        boolean[] cols = new boolean[Constants.BOARD_COLS];
        int blockWidth = 0;
        int blockHeight = 0;
        if (ghost != null) {
            blockHeight = ghost.getPieceMap().length;
            blockWidth = ghost.getPieceMap()[0].length;
        }
        for (int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
            boolean colFull = true;
            for (int indexY = 0; indexY < Constants.BOARD_ROWS; indexY++) {
                boolean ghostEmpty = true;
                if (ghost != null && indexX >= ghost.getBoardX() && indexX < ghost.getBoardX() + blockWidth &&
                        indexY >= ghost.getBoardY() && indexY < ghost.getBoardY() + blockHeight) {
                    if (ghost.getPieceMap()[indexY - ghost.getBoardY()][indexX - ghost.getBoardX()] != 0) ghostEmpty = false;
                }

                if (board[indexX][indexY] == 0 && ghostEmpty) colFull = false;

            }
            if (colFull) {
                cols[indexX] = true;
            }
        }
        return cols;
    }

    public int clearLines() {
        int linesCleared = 0;
        boolean[] rows = identifyRows();
        boolean[] cols = identifyCols();
        for (int indexY = 0; indexY < Constants.BOARD_ROWS; indexY++) {
            if (rows[indexY]) {
                for (int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
                    board[indexX][indexY] = 0;
                }
                linesCleared++;
            }
        }
        for (int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
            if (cols[indexX]) {
                for (int indexY = 0; indexY < Constants.BOARD_ROWS; indexY++) {
                    board[indexX][indexY] = 0;
                }
                linesCleared++;
            }
        }
        return linesCleared;
    }


    public boolean isBoardEmpty(){
        for(int indexY = 0; indexY < Constants.BOARD_ROWS; indexY++) {
            for(int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
                if (board[indexX][indexY] != 0) {
                    return false;
                }
            }
        }
        return true;
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

    private void paintBoard(Graphics2D g2d) {
        for(int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
            for(int indexY = 0; indexY < Constants.BOARD_ROWS; indexY++) {
                Draw.square(indexX * Constants.PIECE_SIZE,
                        indexY * Constants.PIECE_SIZE,
                        1,
                        board[indexX][indexY],
                        false,
                        g2d);
            }
        }
    }

    private void paintHighlight(Graphics2D g2d) {
        if (ghost == null) return;

        for(int indexY = 0; indexY < Constants.BOARD_ROWS; indexY++) {
            boolean[] rows = identifyRows();
            if (rows[indexY]) {
                for(int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
                    Draw.glow(indexX * Constants.PIECE_SIZE,
                            indexY * Constants.PIECE_SIZE,
                            ghost.getColor(),
                            g2d);
                    Draw.square(indexX * Constants.PIECE_SIZE,
                            indexY * Constants.PIECE_SIZE,
                            1,
                            ghost.getColor(),
                            false,
                            g2d);
                }
            }
        }

        for(int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
            boolean[] cols = identifyCols();
            if (cols[indexX]) {
                for(int indexY = 0; indexY < Constants.BOARD_ROWS; indexY++) {
                    Draw.glow(indexX * Constants.PIECE_SIZE,
                            indexY * Constants.PIECE_SIZE,
                            ghost.getColor(),
                            g2d);
                    Draw.square(indexX * Constants.PIECE_SIZE,
                            indexY * Constants.PIECE_SIZE,
                            1,
                            ghost.getColor(),
                            false,
                            g2d);
                }
            }
        }
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
        paintBoard(g2d);
        paintHighlight(g2d);

    }
    

}
