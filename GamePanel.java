import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.util.HashMap;

public class GamePanel extends JLayeredPane {


    final private MatrixPanel matrixPanel; //Declare the matrixPanel
    final private ScorePanel scorePanel;
    final private QueuePanel queuePanel;

    private class RoundedBorder extends AbstractBorder {
        private final Color color;
        private final Color color2;

        RoundedBorder(Color color, Color color2){
            this.color = color;
            this.color2 = color2;

        }


        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setColor(color2);
            int radii = 10;
            g2d.fillRoundRect(x, y, width, height, radii * 2, radii * 2);
            g2d.setColor(color);
            int offset = 3;
            g2d.fillRoundRect(
                    x + offset, y + offset,
                    width - offset * 2, height - offset * 2,
                    radii * 2 - offset, radii * 2 - offset);
        }
    }


    //GamePanel constructor
    public GamePanel() {

        matrixPanel = new MatrixPanel(); //Create the MatrixPanel object
        scorePanel = new ScorePanel();
        queuePanel = new QueuePanel();

        //Set up the panel properties

        setLayout(new OverlayLayout(this));

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BorderLayout(10, 10));
        innerPanel.setBackground(Constants.BACKGROUND_COLOR);
        innerPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Constants.BACKGROUND_COLOR));
        // Add the matrixPanel and piecePanel to the panel
        JPanel outerBoardPanel = new JPanel();
        outerBoardPanel.setBorder(
                new RoundedBorder(Constants.ACCENT_COLOR, Constants.ACCENT_COLOR_2));
        outerBoardPanel.setBackground(Constants.BACKGROUND_COLOR);
        outerBoardPanel.add(matrixPanel, BorderLayout.CENTER);
        innerPanel.add(outerBoardPanel); //Adds the matrixPanel object to the center of the parent panel
        innerPanel.add(scorePanel, BorderLayout.NORTH);
        innerPanel.add(queuePanel, BorderLayout.SOUTH);
        add(innerPanel, 0); //Adds the piecePanel object to the bottom of the parent panel
    }

    MatrixPanel getMatrixPanel(){
        return matrixPanel;
    }

    ScorePanel getScorePanel(){
        return scorePanel;
    }

    QueuePanel getQueuePanel(){
        return queuePanel;
    }

}
