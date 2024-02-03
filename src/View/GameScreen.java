package View;
<<<<<<< Updated upstream

import java.awt.Color;
=======
 
import java.awt.Color;
import java.io.Console;
>>>>>>> Stashed changes
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
<<<<<<< Updated upstream

=======
 
>>>>>>> Stashed changes
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

<<<<<<< Updated upstream
import java.awt.*;

=======
import Model.Board;
import Model.BoardSnake;
import Model.BoardSquare;
import Model.Game;
import Model.GameBoard;
import Model.Snake;
import Model.Square;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JTextPane;
 
>>>>>>> Stashed changes
public class GameScreen extends JFrame {
	private static final int GRID_SIZE = 10;
	private static final int Board_SIZE = 10;
	private static final Color[] COLORS = new Color[]{Color.BLUE, Color.WHITE, Color.YELLOW, Color.RED, Color.GREEN};
	private Color[][] boardColors = new Color[GRID_SIZE][GRID_SIZE];
<<<<<<< Updated upstream


    public GameScreen() {
            setTitle("Game Board");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(749, 652);

            JPanel outerPanel = new JPanel();
            outerPanel.setLayout(null);

            JPanel innerPanel = new JPanel();
            innerPanel.setBounds(120, 65, 500, 500);
            initializeBoard(innerPanel);
            innerPanel.setBackground(Color.WHITE);

            outerPanel.add(innerPanel);
            innerPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

            // Adding the snake image above the board
            ImageIcon snakeImageIcon = new ImageIcon("/images/Snakeforgame.png");
            JLabel snakeLabel = new JLabel(snakeImageIcon);
            snakeLabel.setBounds(127, 10, 50, 100);
            outerPanel.add(snakeLabel);

            getContentPane().add(outerPanel);
            
            JLabel lblNewLabel = new JLabel("");
            lblNewLabel.setBounds(6, 205, 104, 50);
            outerPanel.add(lblNewLabel);
            lblNewLabel.setIcon(new ImageIcon(GameScreen.class.getResource("/images/Vector 208.png")));
            setVisible(true);
        
    }
    private void initializeBoard(JPanel panel) {
        Random rand = new Random();
=======
	private final JLabel label_1 = new JLabel("");
    private BoardSquare[][] squares = new BoardSquare[10][10];
    private GameBoard game ; 
    private BoardSnake[] Snakes = new BoardSnake[6];
    public GameScreen() {
        // Setting up the main frame
        setTitle("Game Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(985, 748);
 
        // Creating the outer panel with BorderLayout
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(null);
        
        JButton btnNewButton = new JButton("");
        btnNewButton.setIcon(new ImageIcon(GameScreen.class.getResource("/images/dice 3.jpg")));
        btnNewButton.setBounds(857, 507, 78, 81);
        outerPanel.add(btnNewButton);
        

        // Creating the inner panel
        JPanel innerPanel = new JPanel();
        initializeBoard(innerPanel,outerPanel);
 
        innerPanel.setBounds(224, 118, 550, 550);
        innerPanel.setBackground(Color.WHITE);
 
        // Adding the inner panel to the center of the outer panel
        outerPanel.add(innerPanel);
        innerPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        
        // Adding the outer panel to the frame
        getContentPane().add(outerPanel);
        
        JTextPane textPane = new JTextPane();
        textPane.setBounds(335, 10, 323, 72);
        outerPanel.add(textPane);
        
        JTextPane textPane_1 = new JTextPane();
        textPane_1.setBounds(10, 231, 120, 229);
        outerPanel.add(textPane_1);
 
        // Make the frame visible
        setVisible(true);
    }
    private void initializeBoard(JPanel panel , JPanel outerPanel ) {
        Random rand = new Random();
        int cellSize = 550 / GRID_SIZE; // Assuming the innerPanel is 500x500 and each cell is 50x50 pixels
>>>>>>> Stashed changes
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int cellNumber = i * GRID_SIZE + (i % 2 == 0 ? j : GRID_SIZE - 1 - j);
                cellNumber = GRID_SIZE * GRID_SIZE - cellNumber;
<<<<<<< Updated upstream

=======
     
>>>>>>> Stashed changes
                JPanel cell = new JPanel(new BorderLayout());
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JLabel label = new JLabel(String.valueOf(cellNumber), SwingConstants.CENTER);
                cell.add(label, BorderLayout.CENTER);
<<<<<<< Updated upstream

                // Get a color for the cell that is not equal to the adjacent cells
                cell.setBackground(getUniqueColor(i, j));

                // Update the board colors array with the new color for reference
                boardColors[i][j] = cell.getBackground();

                // Make the text color contrast with the background
                label.setForeground(getContrastColor(cell.getBackground()));

                panel.add(cell);
            }
        }
=======
     
                // Get a color for the cell that is not equal to the adjacent cells
                cell.setBackground(getUniqueColor(i, j));
     
                // Update the board colors array with the new color for reference
                boardColors[i][j] = cell.getBackground();
     
                // Make the text color contrast with the background
                label.setForeground(getContrastColor(cell.getBackground()));
     
                panel.add(cell);
                
                // Now we calculate the bounds for each label
                int x = j * cellSize + panel.getBounds().x +224 ; // Adjust for the actual position of the panel
                int y = i * cellSize + panel.getBounds().y +118 ;
                squares[i][j]= new BoardSquare(i, j, cellNumber, x, y);
                // Since the layout of the panel has not been laid out yet, we can't get the actual bounds directly
                // Instead, we calculate them based on the known size of the cells and the position of the panel
               // System.out.println( squares[i][j] );
               // System.out.println("Label " + cellNumber + " bounds: x=" + x + ", y=" + y + ", width=" + cellSize + ", height=" + cellSize + " i "+ i + " j" + j);
            }
        }
        setRedSnakes(outerPanel);
        setYellowSnake(outerPanel);
    
        
>>>>>>> Stashed changes
    }

    // Get a color that is different from the adjacent cells
    private Color getUniqueColor(int row, int col) {
        List<Color> availableColors = new ArrayList<>(Arrays.asList(COLORS));
        // Remove colors from the list that are the same as adjacent cells
        if (row > 0) { availableColors.remove(boardColors[row - 1][col]); } // Check cell above
        if (col > 0) { availableColors.remove(boardColors[row][col - 1]); } // Check cell to the left
        if (row > 0 && col > 0) { availableColors.remove(boardColors[row - 1][col - 1]); } // Check upper left diagonal
        if (row > 0 && col < GRID_SIZE - 1) { availableColors.remove(boardColors[row - 1][col + 1]); } // Check upper right diagonal
<<<<<<< Updated upstream

        // Randomly select from the remaining colors
        return availableColors.get(new Random().nextInt(availableColors.size()));
    }

=======
 
        // Randomly select from the remaining colors
        return availableColors.get(new Random().nextInt(availableColors.size()));
    }
 
>>>>>>> Stashed changes
    // Method to get a contrasting color (black or white) based on the background color for readability
    private Color getContrastColor(Color color) {
        double luminance = (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255;
        return luminance > 0.5 ? Color.BLACK : Color.WHITE;  // Higher luminance means lighter color, so return black for contrast and vice versa
    }
    
    
<<<<<<< Updated upstream

=======
    private void setRedSnakes(JPanel panel) {
    	int i = generateRandomNumber1(); //Red snakes
    	int j = generateRandomNumber1();
    	int j_1=generateRandomNumber1();
    	int i_1=generateRandomNumber1();
    	JLabel label = new JLabel();
    	label_1.setBounds(squares[i][j].getboundsX(), squares[i][j].getboundsY(), 55, 55);//RED
    	label.setBounds(squares[i_1][j_1].getboundsX(), squares[i_1][i_1].getboundsY(), 55, 55);//RED
        //the red snake cant be at square number 1 
    	
        BoardSnake redSnake1 = new BoardSnake(squares[i][j],squares[9][0]);
        BoardSnake redSnake2 = new BoardSnake(squares[i_1][j_1],squares[9][0]);
        Snakes[0] = redSnake1;
        Snakes[1] = redSnake2;
      //  System.out.println(squares[9][0].getValue());
        panel.add(label);
        label.setIcon(new ImageIcon(GameScreen.class.getResource("/images/RedSnake.png")));
        panel.add(label_1);
        label_1.setIcon(new ImageIcon(GameScreen.class.getResource("/images/RedSnake.png")));

    }
    
    private void setYellowSnake(JPanel panel) {
    	int i = generateRandomNumber2(); //Red snakes
    	int j = generateRandomNumber3();
    	JLabel yellowSnakeLabel = new JLabel();
    	yellowSnakeLabel.setBounds(squares[i][j].getboundsX(), squares[i][j].getboundsY(), 100, 70);//Yellow
    	System.out.println(squares[i][j].getValue());
    	BoardSquare EndSquare = findSquare(squares[i][j],Color.YELLOW);
    	BoardSnake yellowSnake = new BoardSnake(squares[i][j],EndSquare);
        Snakes[2] = yellowSnake;
        yellowSnakeLabel.setIcon(new ImageIcon(GameScreen.class.getResource("/images/rightYellow.png")));
        panel.add(yellowSnakeLabel);

    	
    }
    
    private static int generateRandomNumber1() {
        Random random = new Random();
        int num = random.nextInt(9) + 1; 
      //  System.out.println(num);
        return num; // Generates a random number between 0 and 9, then adds 2 to make it between 2 and 10.
    }

    public static int generateRandomNumber2() {
        Random random = new Random();
        int num = random.nextInt(6) + 3; 
       // System.out.println(num);
        return num; // Generates a random number between 0 and 9, then adds 2 to make it between 2 and 10.
    }
    public static int generateRandomNumber3() {
        Random random = new Random();
        int num = random.nextInt(5) + 2; 
       // System.out.println(num);
        return num; // Generates a random number between 0 and 9, then adds 2 to make it between 2 and 10.
    }
    
//    private BoardSquare findGoalSquare (BoardSquare square) {
//        for (int i = 0; i < squares.length; i++) {
//            for (int j = 0; j < squares[i].length; j++) {
//              if(squares[i][j].getValue() == square.getValue().)
//            }
//        }
//    	
//    }
//    
    
    private BoardSquare findSquare(BoardSquare StartSquare,Color color) {
    	  for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
            if(color == Color.YELLOW) {
              if(squares[i][j].getboundsX() == StartSquare.getboundsX()+ 55 &&squares[i][j].getboundsY() == StartSquare.getboundsY()+ 55) {
            	  System.out.println(squares[i][j].getValue());
            	  return squares[i][j];
              }
            }
            if(color == Color.GREEN) {
            	
            }
            }
        }
    	return null;
    }
 

 
 
>>>>>>> Stashed changes
    public static void main(String[] args) {
        // Running the application
        SwingUtilities.invokeLater(() -> new GameScreen());
    }
}
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
