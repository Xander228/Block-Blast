import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.util.HashMap;

public class GamePanel extends JLayeredPane {


    final private MatrixPanel matrixPanel; //Declare the matrixPanel
    final private ScorePanel scorePanel;
    final private QueuePanel queuePanel;
    private boolean hasSwap; //Declare the hasSwap variable

    private int score; //Declare the score variable
    private int lines; //Declare the lines variable
    private int level; //Declare the level variable

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
        setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Constants.BACKGROUND_COLOR)); //Add a border around the frame
        setBackground(Constants.BACKGROUND_COLOR); //Set the background color of the panel
        setLayout(new BorderLayout(10, 10)); //Sets the edge offset of member panels to properly space them

        // Add the matrixPanel and piecePanel to the panel
        JPanel outerBoardPanel = new JPanel();
        outerBoardPanel.setBorder(
                new RoundedBorder(Constants.ACCENT_COLOR, Constants.ACCENT_COLOR_2));
        outerBoardPanel.setBackground(Constants.BACKGROUND_COLOR);
        outerBoardPanel.add(matrixPanel, BorderLayout.CENTER);
        add(outerBoardPanel); //Adds the matrixPanel object to the center of the parent panel
        add(scorePanel, BorderLayout.NORTH);
        add(queuePanel, BorderLayout.SOUTH);

        score = 0; //Initializes the score to 0
        lines = 0; //Initializes the lines to 0
        level = 1; //Initializes the level to 1
        this.hasSwap = false; //Initializes the hasSwap to false
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



    public int[] getScores(){
        return new int[]{score, lines, level};
    }

}
