import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GamePanel extends JPanel {


    final private MatrixPanel matrixPanel; //Declare the matrixPanel
    final private ScorePanel scorePanel;
    final private QueuePanel queuePanel;
    private boolean hasSwap; //Declare the hasSwap variable

    private int score; //Declare the score variable
    private int lines; //Declare the lines variable
    private int level; //Declare the level variable

    //GamePanel constructor
    public GamePanel() {

        matrixPanel = new MatrixPanel(); //Create the MatrixPanel object
        scorePanel = new ScorePanel();
        queuePanel = new QueuePanel();

        //Set up the panel properties
        setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Constants.PRIMARY_COLOR)); //Add a border around the frame
        setBackground(Constants.PRIMARY_COLOR); //Set the background color of the panel
        setLayout(new BorderLayout(10, 10)); //Sets the edge offset of member panels to properly space them

        // Add the matrixPanel and piecePanel to the panel
        add(matrixPanel); //Adds the matrixPanel object to the center of the parent panel
        add(scorePanel, BorderLayout.NORTH);
        add(queuePanel, BorderLayout.SOUTH);

        score = 0; //Initializes the score to 0
        lines = 0; //Initializes the lines to 0
        level = 1; //Initializes the level to 1
        this.hasSwap = false; //Initializes the hasSwap to false
    }



    public int[] getScores(){
        return new int[]{score, lines, level};
    }

}
