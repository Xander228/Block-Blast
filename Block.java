import java.awt.*;

public class Block {

    //Block bitmaps are formatted as blocks[piece number][y index][x index]
    private static final int[][][] blocks = {
        {        
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,1,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
        }, {
            {0,0,0,0,0},
            {0,0,0,1,0},
            {0,0,1,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
        }, {
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,1,1,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
        }, {
            {0,0,0,0,0},
            {0,0,0,1,0},
            {0,0,1,1,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
        }, {
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,1,1,1,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
        }, {
            {0,0,0,0,0},
            {0,0,0,1,0},
            {0,0,1,0,0},
            {0,1,0,0,0},
            {0,0,0,0,0}
        }, {
            {0,0,0,0,0},
            {0,0,0,1,0},
            {0,1,1,1,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
        }, {
            {0,0,0,0,0},
            {0,0,1,0,0},
            {0,1,1,1,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
        }, {
            {0,0,0,0,0},
            {0,1,0,0,0},
            {0,1,1,1,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
        }, {
            {0,0,0,0,0},
            {0,1,1,0,0},
            {0,0,1,1,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
        }, {
            {0,0,0,0,0},
            {0,0,1,1,0},
            {0,1,1,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
        }, {
            {0,0,0,0,0},
            {0,0,0,0,0},
            {1,1,1,1,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
        }, {
            {0,0,0,0,0},
            {0,0,1,1,0},
            {0,0,1,1,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
        }, {
            {0,0,0,0,0},
            {0,1,0,0,0},
            {0,1,0,0,0},
            {0,1,1,1,0},
            {0,0,0,0,0}
        }, {
            {0,0,0,0,0},
            {0,0,0,1,0},
            {0,0,0,1,0},
            {0,1,1,1,0},
            {0,0,0,0,0}
        }, {
            {0,0,0,0,0},
            {0,0,0,0,0},
            {1,1,1,1,1},
            {0,0,0,0,0},
            {0,0,0,0,0}
        }, {
            {0,0,0,0,0},
            {0,1,1,1,0},
            {0,1,1,1,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
        }, {
            {0,0,0,0,0},
            {0,1,1,1,0},
            {0,1,1,1,0},
            {0,1,1,1,0},
            {0,0,0,0,0}

        },

    };


    private int[][] pieceMap;
    private int type;
    private int pieceRotation;
    private int color;
    
    private boolean boardRelative;
    private boolean scaled;
    private int offsetX, offsetY;
    private int boardX, boardY;
    private int pixelX, pixelY;


    private boolean visible;
    
    //type is a number 0 - 6 that refers to the type of tetromino
    public Block(int type, int x, int y, int pieceRotation, int color, boolean boardRelative) {
        this.type = type;
        this.color = color;
        this.pieceRotation = pieceRotation;

        this.pieceMap = new int[5][5];
        for (int indexY = 0; indexY < 5; indexY++) {
            for (int indexX = 0; indexX < 5; indexX++) {
                pieceMap[indexY][indexX] = blocks[type][indexY][indexX];
            }
        }

        for (int i = 0; i < pieceRotation; i++) {
            pieceMap = rotateMatrix(pieceMap);
        }

        int startX = 5;
        int startY = 5;
        int endX = 0;
        int endY = 0;
        for (int indexY = 0; indexY < 5; indexY++) {
            for (int indexX = 0; indexX < 5; indexX++) {
                if (pieceMap[indexY][indexX] == 0) continue;
                startX = Math.min(indexX, startX);
                startY = Math.min(indexY, startY);
                endX = Math.max(indexX, endX);
                endY = Math.max(indexY, endY);
            }
        }

        offsetX = (endX - startX) / 2;
        offsetY = (endY - startY) / 2;


        this.boardRelative = boardRelative;
        if (boardRelative)  setBoardCoords(x, y);
        else                setPixelCoords(x, y);

        visible = true;

    }

    private int[][] rotateMatrix(int[][] pieceMap) {
        int[][] rotatedMatrix = new int[5][5];
        for (int indexY = 0; indexY < 5; indexY++) {
            for (int indexX = 0; indexX < 5; indexX++) {
                rotatedMatrix[indexY][indexX] = pieceMap[4 - indexX][indexY];
            }
        }
        return rotatedMatrix;
    }

    public static int getBlockCount() {
        return blocks.length;
    }

    public void setPixelCoords(int x, int y) {
        this.pixelX = x;
        this.pixelY = y;
    }
    
    public void setBoardCoords(int x, int y) {
        this.boardX = x;
        this.boardY = y;
        
        this.pixelX = Constants.PIECE_SIZE * x;
        this.pixelY = Constants.PIECE_SIZE * y;
    }
    
    public void updatePixelCoords() {
        this.pixelX = Constants.PIECE_SIZE * this.boardX;
        this.pixelY = Constants.PIECE_SIZE * this.boardY;
    }

    public void lock(int[][] board) {
        for (int indexY = 0; indexY < 4; indexY++) {
            for (int indexX = 0; indexX < 4; indexX++) {
                int cell = pieceMap[indexY][indexX];
                if (cell == 0) continue;
                board[boardX + indexX][boardY + indexY] = cell;
            }
        }
    }
    
    public void setBoardRelative(boolean boardRelative) {
        this.boardRelative = boardRelative;
        pieceRotation = 0;
    }
    
    private boolean isOverlapped(int x, int y, int rotation, int[][] board) {
        for (int indexY = 0; indexY < 4; indexY++) {
            for (int indexX = 0; indexX < 4; indexX++) {
                //ignores tile if no mino occupies it
                if (pieceMap[indexY][indexX] == 0) continue;
                
                //if a mino exists, return true if it is touching another mino on the board
                if (board[x + indexX][y + indexY] != 0) return true;
            }
        }
        //return false if all minos pass the test
        return false;
    }
    
    public boolean isOverlapped(int x, int y, int[][] board) {
        return isOverlapped(x, y, this.pieceRotation, board);
    }
    public boolean isOverlapped(int[][] board) {
        return isOverlapped(this.boardX, this.boardY, this.pieceRotation, board);
    }
    public boolean isOutOfBounds(int x, int y, int rotation) {
        for (int indexY = 0; indexY < 4; indexY++) {
            for (int indexX = 0; indexX < 4; indexX++) {
                //ignores tile if no mino occupies it
                if (pieceMap[indexY][indexX] == 0) continue;
                
                //if a mino exists, return true if it is outside the left right or bottom bounds
                if (x + indexX < 0 || 
                    x + indexX > Constants.BOARD_COLS - 1 || 
                    y + indexY > Constants.TOTAL_BOARD_ROWS - 1) return true;
            }
        }
        //return false if all minos pass the test
        return false;
    }
    
    public boolean isOutOfBounds(int x, int y) {
        return isOutOfBounds(x, y, this.pieceRotation);
    }

    public void hide() {
        visible = false;
    }
    public void draw(Graphics g) {
        if (!visible) return;
        int xOffset = 0; //boardRelative ? 0 : centerOffsets[0];
        int yOffset = 0; //boardRelative ? 0 : centerOffsets[1];
        for (int indexY = 0; indexY < 4; indexY++) {
            for (int indexX = 0; indexX < 4; indexX++) {
                if (pieceMap[indexY][indexX] == 0) continue;
                Draw.square(indexX * Constants.PIECE_SIZE + pixelX - xOffset, 
                            indexY * Constants.PIECE_SIZE + pixelY - yOffset, 
                            type,
                            g);
            }
        }
    }

    public void drawGhost(int[][] board, Graphics g) {
        if (!visible) return;
        int lowestY = Constants.TOTAL_BOARD_ROWS;
        for(int i = 0; i < Constants.BOARD_ROWS - this.boardY; i++) {
            if(isOutOfBounds(this.boardX, this.boardY + i, this.pieceRotation)) {
                lowestY = this.boardY + i - 1;
                break;
            }
            
            if(isOverlapped(this.boardX, this.boardY + i, this.pieceRotation, board)) {
                lowestY = this.boardY + i - 1;
                break;
            }
        }
        
        for (int indexY = 0; indexY < 4; indexY++) {
            for (int indexX = 0; indexX < 4; indexX++) {
                if (pieceMap[indexY][indexX] == 0) continue;
                Draw.ghostSquare(indexX * Constants.PIECE_SIZE + pixelX, 
                            (indexY + lowestY) * Constants.PIECE_SIZE, 
                            type,
                            g);
            }
        }
    }
}
