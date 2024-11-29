import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Block extends JComponent {

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
    private int pieceRotation;
    private int color;
    
    private boolean ghost;
    private boolean scaled;
    private double offsetX, offsetY;
    private int boardX, boardY;
    private int pixelX, pixelY;

    private int spawnX, spawnY;

    private int mouseX, mouseY;
    private double startX, startY;
    private boolean dragging;


    private boolean visible;
    
    //type is a number 0 - 6 that refers to the type of tetromino
    public Block(int type, int x, int y, int pieceRotation, int color, boolean ghost, boolean scaled) {
        this.color = color;
        this.pieceRotation = pieceRotation;
        this.scaled = scaled;

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

        offsetX = startX + (endX - startX) / 2.0;
        offsetY = startY + (endY - startY) / 2.0;


        this.ghost = ghost;

        spawnX = x;
        spawnY = y;
        setPixelCoords(x, y);
        visible = true;


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                setPixelCoords(pixelX, pixelY);
                addListeners();
            }
        });

    }


    private void addListeners(){

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() != MouseEvent.BUTTON1) return;
                mouseX = e.getXOnScreen();
                mouseY = e.getYOnScreen();

                startX = pixelX;
                startY = pixelY;

                dragging = true;
                scaled = false;

                int diffX = (int)getParent().getLocationOnScreen().getX() -
                        (int) getParent().getParent().getLocationOnScreen().getX();
                int diffY = (int)getParent().getLocationOnScreen().getY() -
                        (int)getParent().getParent().getLocationOnScreen().getY();

                startX += diffX;
                startY += diffY;

                getParent().getParent().add(Block.this, 0);
                getParent().repaint();

                int newX = (int) startX;
                int newY = (int) startY;

                setPixelCoords(newX, newY);

            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getButton() != MouseEvent.BUTTON1) return;
                dragging = false;

            }

        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(!dragging) return;

                int eventX = e.getXOnScreen();
                int eventY = e.getYOnScreen();

                int deltaX = eventX - mouseX;
                int deltaY = eventY - mouseY;

                int newX = (int) startX + deltaX;
                int newY = (int) startY + deltaY;

                setPixelCoords(newX, newY);
            }
        });

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
        setBounds(
                x - (int)((offsetX + .5) * Constants.PIECE_SIZE * (scaled ? Constants.QUEUE_SCALE : 1.0)),
                y - (int)((offsetY + .5) * Constants.PIECE_SIZE * (scaled ? Constants.QUEUE_SCALE : 1.0)),
                Constants.PIECE_SIZE * 5,
                Constants.PIECE_SIZE * 5);
    }
    
    public void setBoardCoords(int x, int y) {
        this.boardX = x;
        this.boardY = y;
        
        updatePixelCoords();
    }
    
    public void updatePixelCoords() {
        this.pixelX = Constants.PIECE_SIZE * this.boardX;
        this.pixelY = Constants.PIECE_SIZE * this.boardY;
    }

    public void lock(int[][] board) {
        for (int indexY = 0; indexY < 5; indexY++) {
            for (int indexX = 0; indexX < 5; indexX++) {
                int cell = pieceMap[indexY][indexX];
                if (cell == 0) continue;
                board[boardX + indexX][boardY + indexY] = cell;
            }
        }
    }
    
    public void setGhost(boolean ghost) {
        this.ghost = ghost;
        pieceRotation = 0;
    }
    
    private boolean isOverlapped(int x, int y, int rotation, int[][] board) {
        for (int indexY = 0; indexY < 5; indexY++) {
            for (int indexX = 0; indexX < 5; indexX++) {
                //ignores tile if no mino occupies it
                if (pieceMap[indexY][indexX] == 0) continue;
                
                //if a mino exists, return true if it is touching another mino on the board
                if (board[x + indexX][y + indexY] != 0) return true;
            }
        }
        //return false if all minos pass the test
        return false;
    }

    @Override
    public boolean contains(int x, int y) {
        int pieceSize = (int)(Constants.PIECE_SIZE * (scaled ? Constants.QUEUE_SCALE : 1.0));
        for(int indexY = 0; indexY < 5; indexY++) {
            for(int indexX = 0; indexX < 5; indexX++) {
                if(pieceMap[indexY][indexX] == 0) continue;
                int pixelX = indexX * pieceSize;
                int pixelY = indexY * pieceSize;
                if(x >= pixelX && x <= pixelX + pieceSize &&
                   y >= pixelY && y <= pixelY + pieceSize) return true;
            }
        }
        return false;
    }

    public boolean isOverlapped(int x, int y, int[][] board) {
        return isOverlapped(x, y, this.pieceRotation, board);
    }
    public boolean isOverlapped(int[][] board) {
        return isOverlapped(this.boardX, this.boardY, this.pieceRotation, board);
    }
    public boolean isOutOfBounds(int x, int y, int rotation) {
        for (int indexY = 0; indexY < 5; indexY++) {
            for (int indexX = 0; indexX < 5; indexX++) {
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

    @Override
    public void paintComponent(Graphics g) {
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

        if (!visible) return;
        double scale = scaled ? Constants.QUEUE_SCALE : 1.0;
        for (int indexY = 0; indexY < 5; indexY++) {
            for (int indexX = 0; indexX < 5; indexX++) {
                if (pieceMap[indexY][indexX] == 0) continue;
                Draw.square(
                        (int)(indexX * Constants.PIECE_SIZE * scale),
                        (int)(indexY * Constants.PIECE_SIZE * scale),
                        scale,
                        color,
                        g2d);
            }
        }
    }

}
