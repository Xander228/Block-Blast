import java.awt.*;

public class Constants
{

    public static final int BOARD_COLS = 8;
    public static final int BOARD_ROWS = 8;
    public static final int QUEUE_SIZE = 3;
    
    public static final int PIECE_SIZE = 60;
    public static final int PIECE_EDGE_WIDTH = PIECE_SIZE / 7;
    public static final int EMPTY_EDGE_WIDTH = PIECE_SIZE / 30;
    public static final int EMPTY_EDGE_RADIUS = PIECE_SIZE / 10;
    public static final int BOARD_WIDTH = BOARD_COLS * PIECE_SIZE;
    public static final int BOARD_HEIGHT = BOARD_ROWS * PIECE_SIZE;
    public static final double QUEUE_SCALE = 0.5;

    public static final int GAMEOVER_HEIGHT = 200;
    public static final int GAMEOVER_WIDTH = 300;

    public static final int SCORE_PANEL_HEIGHT = 40;
    public static final int QUEUE_PANEL_HEIGHT = 160;
    
    public static final  Color BACKGROUND_COLOR = new Color((int)0x354c87);
    public static final  Color ACCENT_COLOR = new Color((int)0x171e3a);
    public static final  Color ACCENT_COLOR_2 = new Color((int)0x293669);

    public static final  Color PRIMARY_COLOR = new Color((int)0xFFFFFF);


    public static final Color[][] COLORS = {
            {new Color((int)0x1d2446),new Color((int)0x171e3a),new Color((int)0x171e3a),new Color((int)0x171e3a),new Color((int)0x171e3a)},
            {new Color((int)0xce3736),new Color((int)0xef7d7b),new Color((int)0x841c21),new Color((int)0xde4943),new Color((int)0xb52829)},
            {new Color((int)0xf78229),new Color((int)0xffb87b),new Color((int)0x954508),new Color((int)0xff9242),new Color((int)0xc66118)},
            {new Color((int)0xefc239),new Color((int)0xffe373),new Color((int)0xad7118),new Color((int)0xffd345),new Color((int)0xc69618)},
            {new Color((int)0x46cc3e),new Color((int)0x84ef9c),new Color((int)0x086d29),new Color((int)0x4adf4a),new Color((int)0x299e31)},
            {new Color((int)0x42bfef),new Color((int)0x94e9ff),new Color((int)0x0069a5),new Color((int)0x52d7ff),new Color((int)0x2196d6)},
            {new Color((int)0x4266e7),new Color((int)0x82acfd),new Color((int)0x21348c),new Color((int)0x4271f7),new Color((int)0x294dce)},
            {new Color((int)0x945dd6),new Color((int)0xd69af7),new Color((int)0x52217b),new Color((int)0xa569de),new Color((int)0x733d9c)},
            {new Color((int)0xf2f2f2),new Color((int)0xffffff),new Color((int)0x959595),new Color((int)0xcacaca),new Color((int)0xdddddd)},
    };
    
}
