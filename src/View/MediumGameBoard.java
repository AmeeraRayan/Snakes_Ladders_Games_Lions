package View;
 
import java.awt.Color;

import java.io.Console;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
 
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Model.Board;
import Model.BoardSnake;
import Model.BoardSquare;
import Model.Game;
import Model.GameBoard;
import Model.Snake;
import Model.Square;
import Model.SquareType;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JTextPane;
 
public class MediumGameBoard extends JFrame {
	private static final int GRID_SIZE = 10;
	private static final Color[] COLORS = new Color[]{Color.BLUE, Color.WHITE, Color.YELLOW, Color.RED, Color.GREEN};
	private Color[][] boardColors = new Color[GRID_SIZE][GRID_SIZE];
	private final JLabel label_1 = new JLabel("");
    private BoardSquare[][] squares = new BoardSquare[10][10];
    //private GameBoard game ; 
    private BoardSnake[] Snakes = new BoardSnake[6];
    public MediumGameBoard() {
        // Setting up the main frame
        setTitle("Game Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(985, 748);
 
        // Creating the outer panel with BorderLayout
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(null);
        
        JButton btnNewButton = new JButton("");
        btnNewButton.setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/dice 3.jpg")));
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
 
        setVisible(true);
    }
    private void initializeBoard(JPanel panel , JPanel outerPanel ) {
        Random rand = new Random();
        int cellSize = 550 / GRID_SIZE; //the innerPanel is 550x550 and each cell is 55x55 pixels
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int cellNumber = i * GRID_SIZE + (i % 2 == 0 ? j : GRID_SIZE - 1 - j);
                cellNumber = GRID_SIZE * GRID_SIZE - cellNumber;
     
                JPanel cell = new JPanel(new BorderLayout());
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JLabel label = new JLabel(String.valueOf(cellNumber), SwingConstants.CENTER);
                cell.add(label, BorderLayout.CENTER);
     
                // Get a color for the cell that is not equal to the adjacent cells
                cell.setBackground(getUniqueColor(i, j));
     
                // Update the board colors array with the new color for reference
                boardColors[i][j] = cell.getBackground();
     
                // Make the text color contrast with the background
                label.setForeground(getContrastColor(cell.getBackground()));
     
                panel.add(cell);
                
                //  calculate the bounds for each label
                int x = j * cellSize + panel.getBounds().x +224 ; // Adjust for the actual position of the panel
                int y = i * cellSize + panel.getBounds().y +118 ;
                squares[i][j]= new BoardSquare(i, j, SquareType.NORMAL, x, y,cellNumber);
               // System.out.println( squares[i][j] );
                System.out.println("Label " + cellNumber + " bounds: x=" + x + ", y=" + y + ", "+ " i "+ i + " j" + j);
            }
        }
         setRedSnakes(outerPanel);
         setYellowSnake(outerPanel);
         setBlueSnakes(outerPanel);
         setGreenSnakes(outerPanel);
    
        
    }


    // Get a color that is different from the adjacent cell
    private Color getUniqueColor(int row, int col) {
        List<Color> availableColors = new ArrayList<>(Arrays.asList(COLORS));
       
        if (row > 0) { availableColors.remove(boardColors[row - 1][col]); } 
        if (col > 0) { availableColors.remove(boardColors[row][col - 1]); } 
        if (row > 0 && col > 0) { availableColors.remove(boardColors[row - 1][col - 1]); } 
        if (row > 0 && col < GRID_SIZE - 1) { availableColors.remove(boardColors[row - 1][col + 1]); } 
 
        return availableColors.get(new Random().nextInt(availableColors.size()));
    }
 
    private Color getContrastColor(Color color) { //method that choose the color of the number count on the square 
        double luminance = (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255;
        return luminance > 0.5 ? Color.BLACK : Color.WHITE;  
    }
    
    
    private void setRedSnakes(JPanel panel) {
    	int i = generateRandomNumber1(); //Red snakes
    	int j = generateRandomNumber1();
    	int j_1=generateRandomNumber1();
    	int i_1=generateRandomNumber1();
    	label_1.setBounds(squares[i][j].getboundsX(), squares[i][j].getboundsY(), 55, 55);
        //the red snake cant be at square number 1 
    	
        BoardSnake redSnake1 = new BoardSnake(squares[i][j],squares[9][0]);
        BoardSnake redSnake2 = new BoardSnake(squares[i_1][j_1],squares[9][0]);
        Snakes[0] = redSnake1;
        Snakes[1] = redSnake2;
        panel.add(label_1);
        label_1.setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/RedSnake.png")));

    }
    
    private void setYellowSnake(JPanel panel) {
    	int i = generateRandomNumber2(); //Red snakes
    	int j = generateRandomNumber3();
    	JLabel yellowSnakeLabel = new JLabel();
    	yellowSnakeLabel.setBounds(squares[i][j].getboundsX(), squares[i][j].getboundsY(), 100, 100);//Yellow
    	System.out.println(squares[i][j].getValue());
    	BoardSquare EndSquare = findSquare(squares[i][j],Color.YELLOW);
    	BoardSnake yellowSnake = new BoardSnake(squares[i][j],EndSquare);
        Snakes[2] = yellowSnake;
        yellowSnakeLabel.setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/rightYellow.png")));
        panel.add(yellowSnakeLabel);
    	
    }
    private void setBlueSnakes(JPanel panel) {
    	int i = generateRandomNumber4(); //BLUE snakes
    	int j = generateRandomNumber3();
    	System.out.println(i +" " + j );
    	JLabel labelBlue = new JLabel();
    	labelBlue.setBounds(squares[i][j].getboundsX()-110, squares[i][j].getboundsY()+15, 140, 170);//BLUE
    	BoardSquare EndSquare = findSquare(squares[i][j],Color.BLUE);
        System.out.println(squares[i][j].getValue());
        BoardSnake BlueSnake1 = new BoardSnake(squares[i][j],EndSquare);
        System.out.println(squares[i][j].getValue());
        Snakes[3] = BlueSnake1;
        labelBlue.setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/SnakeBlueRight.png")));
        panel.add(labelBlue);
    }
    
    private void setGreenSnakes(JPanel panel) {
    	int i = generateRandomNumber4(); //Red snakes
    	int j = generateRandomNumber4();
    	JLabel label = new JLabel();
    	
    	label.setBounds(squares[i][j].getboundsX(), squares[i][j].getboundsY()+15, 170, 140);//Green
        //the red snake cant be at square number 1 
    	BoardSquare EndSquare = findSquare(squares[i][j],Color.GREEN);
        BoardSnake GreenSnake1 = new BoardSnake(squares[i][j],EndSquare);
        Snakes[4] = GreenSnake1;
        System.out.println(squares[i][j].getValue() + ">> Green");
        label.setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/GSnake.png")));
        panel.add(label);


    }
    
    
    private static int generateRandomNumber1() {
        Random random = new Random();
        int num = random.nextInt(9) + 1; 
        return num; 
    }

    public static int generateRandomNumber2() {
        Random random = new Random();
        int num = random.nextInt(6) + 3; 
        return num; 
    }
    public static int generateRandomNumber3() {
        Random random = new Random();
        int num = random.nextInt(5) + 2; 
        return num; 
    }
    
    public static int generateRandomNumber4() {
        Random random = new Random();
        int num = random.nextInt(4)+3; 
        return num; 
    }
  

    
    private BoardSquare findSquare(BoardSquare StartSquare,Color color) {
    	  for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
            if(color == Color.YELLOW) {
              if(squares[i][j].getboundsX() == StartSquare.getboundsX()+ 55 &&squares[i][j].getboundsY() == StartSquare.getboundsY()+ 55) {
            	  System.out.println(squares[i][j].getValue()+ " end yellow");
            	  return squares[i][j];
              }
            }
            if(color == Color.BLUE) {
            	if(squares[i][j].getboundsX()+110 == StartSquare.getboundsX() &&squares[i][j].getboundsY()-165 == StartSquare.getboundsY()) {
              	  System.out.println(squares[i][j].getValue()+ " EndBlue");
              	  return squares[i][j];
                }
            	
            }
            if(color == Color.GREEN) {
            	if(squares[i][j].getboundsX() == StartSquare.getboundsX()+55 &&squares[i][j].getboundsY() == StartSquare.getboundsY()+110) {
              	  System.out.println(squares[i][j].getValue()+" EndSquare");
              	  return squares[i][j];
                }
            	
            }
            }
        }
    	return null;
    }
 

 
 
    public static void main(String[] args) {
        // Running the application
        SwingUtilities.invokeLater(() -> new MediumGameBoard());
    }
}
