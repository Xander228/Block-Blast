import java.awt.*;

public class Constants
{

    public static final int BOARD_COLS = 8;
    public static final int BOARD_ROWS = 8;
    public static final int TOTAL_BOARD_ROWS = BOARD_ROWS;
    public static final int QUEUE_SIZE = 3;
    
    public static final int PIECE_SIZE = 60;
    public static final int PIECE_EDGE_WIDTH = PIECE_SIZE / 7;
    public static final int BOARD_WIDTH = BOARD_COLS * PIECE_SIZE;
    public static final int BOARD_HEIGHT = BOARD_ROWS * PIECE_SIZE;

    public static final int GAMEOVER_HEIGHT = 200;
    public static final int GAMEOVER_WIDTH = 300;

    public static final int SCORE_PANEL_HEIGHT = 40;
    public static final int QUEUE_PANEL_HEIGHT = 100;
    
    public static final  Color BACKGROUND_COLOR = new Color((int)0x3c5394);
    public static final  Color ACCENT_COLOR = new Color((int)0x373737);


    public static final  Color PRIMARY_COLOR = new Color((int)0xFFFFFF);
    

    public static final  Color[][] COLORS = {
        {new Color((int)0x232d55),new Color((int)0x1e254c),new Color((int)0x1e254c),new Color((int)0x1e254c),new Color((int)0x1e254c)},
        {new Color((int)0xff0000),new Color((int)0xff8888),new Color((int)0x950000),new Color((int)0xca0000),new Color((int)0xff4040)},
        {new Color((int)0xffc800),new Color((int)0xffe99d),new Color((int)0x955e00),new Color((int)0xca9300),new Color((int)0xffd955)},
        {new Color((int)0xfff200),new Color((int)0xffff9f),new Color((int)0x959500),new Color((int)0xcaca00),new Color((int)0xffff5e)},
        {new Color((int)0x00ff00),new Color((int)0xa6ffa6),new Color((int)0x009500),new Color((int)0x00ca00),new Color((int)0x77ff77)},
        {new Color((int)0x00ffff),new Color((int)0xaeffff),new Color((int)0x009595),new Color((int)0x00caca),new Color((int)0x77ffff)},
        {new Color((int)0x0000ff),new Color((int)0x8a8aff),new Color((int)0x000095),new Color((int)0x0000ca),new Color((int)0x4646ff)},
        {new Color((int)0xff00ff),new Color((int)0xff93ff),new Color((int)0x950095),new Color((int)0xca00ca),new Color((int)0xff5eff)},
        {new Color((int)0xf2f2f2),new Color((int)0xffffff),new Color((int)0x959595),new Color((int)0xcacaca),new Color((int)0xdddddd)},
    };
    
}
