import javax.swing.*;
import java.awt.*;

public class QueuePanel extends JPanel {
    private PieceBag pieceBag;
    
    public QueuePanel() {
        // Initialize components, set layout, etc.
        setPreferredSize( new Dimension(Constants.BOARD_WIDTH,
                                        Constants.QUEUE_PANEL_HEIGHT));
        setBackground(Constants.BACKGROUND_COLOR);

        setLayout(null);
        
        pieceBag = new PieceBag();
        initializeQueue();
        
    }
    
    private void initializeQueue() {

        for (int i = 0; i < Constants.QUEUE_SIZE; i++) {
            int color = (int)(Math.random() * 7) + 1;
            int rotation = (int)(Math.random() * 4);
            add(new Block(
                    pieceBag.pullNewPiece(),
                    (int)(i * (Constants.BOARD_WIDTH - 160) / 2.0) + 80,
                    Constants.QUEUE_PANEL_HEIGHT / 2,
                    rotation,
                    color,
                    false,
                    true));
        }
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
    }
    
    public void update() {
        repaint();
    }
}
