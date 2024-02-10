package View; 
import java.io.Console;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.PanelUI;

import Model.Gamesnakes;
import Model.BoardSquare;
import Model.Dice;
import Model.GameLadder;
import Model.SquareType;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
public class MediumGameBoard extends JFrame {
	private static final int GRID_SIZE = 10;
	private static final Color[] COLORS = new Color[]{Color.BLUE, Color.WHITE, Color.YELLOW, Color.RED, Color.GREEN};
	private Color[][] boardColors = new Color[GRID_SIZE][GRID_SIZE];
	private final JLabel label_1 = new JLabel("");
    private BoardSquare[][] squares = new BoardSquare[10][10];
    private JLabel[][] boardlabels = new JLabel[GRID_SIZE][GRID_SIZE];
    private Dice dice = new Dice("medium");
    //private GameBoard game ; 
    private Gamesnakes[] snakes = new Gamesnakes[6];
    private GameLadder[] ladders = new GameLadder[6];
    Random rand = new Random();
    int[] ladderLengths = {1, 2, 3, 4, 5, 6};
    public MediumGameBoard() {
        // Setting up the main frame
        setTitle("Game Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(985, 748);
 
        // Creating the outer panel with BorderLayout
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(null);
        
        JTextPane textPane = new JTextPane();
        textPane.setBounds(411, 23, 251, 55);
        outerPanel.add(textPane);
        
        JButton diceButton = new JButton("");
        diceButton.setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/dice 3.jpg")));
        diceButton.setBounds(857, 421, 78, 81);
        outerPanel.add(diceButton);
        
        diceButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int result = dice.DiceForMediumGame();
        		System.out.println(result);
        		String path = "/images/dice " + result + ".jpg";
                diceButton.setIcon(new ImageIcon(MediumGameBoard.class.getResource(path)));

        	}
        });
       
        

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
        
        JTextPane textPane_1 = new JTextPane();
        textPane_1.setBounds(28, 175, 106, 140);
        outerPanel.add(textPane_1);
        
  
        setVisible(true);
    }
    private void initializeBoard(JPanel panel, JPanel outerPanel) {
        int cellSize = 550 / GRID_SIZE; // the innerPanel is 550x550 and each cell is 55x55 pixels        
        for(int i = 0; i < GRID_SIZE; i++) {
            for(int j = 0; j < GRID_SIZE; j++) {
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
                
                // Calculate the bounds for each label
                int x = j * cellSize + panel.getBounds().x + 224; // Adjust for the actual position of the panel
                int y = i * cellSize + panel.getBounds().y + 118;
                squares[i][j] = new BoardSquare(Math.abs(i-9), j, SquareType.NORMAL, x, y, cellNumber);
                boardlabels[i][j] = label; 
                
                 System.out.println("Label " + cellNumber + " bounds: x=" + x + ", y=" + y + ", i=" + squares[i][j].getRow() + ", j=" + j);
            }
        }

        //setladder1(outerPanel);
        //setladder2(outerPanel);
        //setladder3(outerPanel);
        //setladder4(outerPanel);
        //setladder5(outerPanel);
        //setladder6(outerPanel);
        setRedSnakes(outerPanel);
        setYellowSnake(outerPanel);
        setBlueSnakes(outerPanel);
        setGreenSnakes(outerPanel);
        //uniqueSquares();
       
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
        int i1, j1, i2, j2; 
        // Place the first red snake
        do {
            i1 = rand.nextInt(9)+1; // Red snake 1
            j1 = rand.nextInt(9)+1;
        } while ((squares[i1][j1].getValue() == 1 && squares[i1][j1].getValue() == 100));

        // Place the second red snake
        do {
            i2 = rand.nextInt(9)+1; // Red snake 2
            j2 = rand.nextInt(9)+1;
        } while ((squares[i2][j2].getValue() == 1 && squares[i2][j2].getValue() == 100));

        label_1.setBounds(squares[i1][j1].getboundsX(), squares[i1][j1].getboundsY(), 55, 55);
        Gamesnakes redSnake1 = new Gamesnakes(squares[i1][j1], squares[9][0]);
        System.out.println(squares[i1][j1].getValue()+"redsnake1");
        snakes[0] = redSnake1;
        panel.add(label_1);
        label_1.setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/RedSnake.png")));
        JLabel label_2 = new JLabel();
        label_2.setBounds(squares[i2][j2].getboundsX(), squares[i2][j2].getboundsY(), 55, 55);
        //object red snake 2 
        Gamesnakes redSnake2 = new Gamesnakes(squares[i2][j2], squares[9][0]);
        System.out.println(squares[i2][j2].getValue()+"redsanke2");
        snakes[1] = redSnake2;
        panel.add(label_2);
        label_2.setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/RedSnake.png")));
    }

    
    private void setYellowSnake(JPanel panel) {
        int i, j;
        do {
            i = generateRandomNumber_I(Color.YELLOW); // Yellow snakes
            j= generateRandomNumber_J(Color.YELLOW);
        } while ((squares[i][j].getValue() == 1 && squares[i][j].getValue() ==100));
        System.out.println("index i :" + i + " index j " + j + " "+squares[i][j].getValue());

        JLabel yellowSnakeLabel = new JLabel();
        yellowSnakeLabel.setBounds(squares[i][j].getboundsX(), squares[i][j].getboundsY(), 100, 100);// Yellow
        System.out.println("X"+squares[i][j].getboundsX()+"y"+ squares[i][j].getboundsY());
        System.out.println(squares[i][j].getValue()+"start yellow" + squares[i][j].getRow());
        BoardSquare EndSquare = findSquare(squares[i][j], Color.YELLOW);
        Gamesnakes yellowSnake = new Gamesnakes(squares[i][j], EndSquare);
        snakes[2] = yellowSnake;
        yellowSnakeLabel.setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/rightYellow.png")));
        panel.add(yellowSnakeLabel);
    }

    private void setBlueSnakes(JPanel panel) {
        int i, j;
        do {
            j = generateRandomNumber_I(Color.BLUE); // Blue snakes
            i = generateRandomNumber_J(Color.BLUE);
            System.out.println("index i :" + i + " index j " + j + " "+squares[i][j].getValue());
        } while ((squares[i][j].getValue() == 1 && squares[i][j].getValue() ==100) || 31>squares[i][j].getValue() );

        JLabel labelBlue = new JLabel();
        labelBlue.setBounds(squares[i][j].getboundsX() - 110, squares[i][j].getboundsY() + 15, 140, 170);// BLUE
        BoardSquare EndSquare = findSquare(squares[i][j], Color.BLUE);
        System.out.println(squares[i][j].getValue()+"start blue"+squares[i][j]);
        Gamesnakes BlueSnake1 = new Gamesnakes(squares[i][j], EndSquare);
        snakes[3] = BlueSnake1;
        labelBlue.setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/SnakeBlueRight.png")));
        panel.add(labelBlue);
    }

    
    private void setGreenSnakes(JPanel panel) {
        int i1, j1,i2,j2;
        do {
            i1 = generateRandomNumber_I(Color.GREEN); // Green snakes
            j1 = generateRandomNumber_J(Color.GREEN);
        } while ((squares[i1][j1].getValue() == 1 && squares[i1][j1].getValue() ==100));
        
        do {
            i2 = generateRandomNumber_I(Color.GREEN); // Green snakes
            j2 = generateRandomNumber_J(Color.GREEN);
        } while ((squares[i2][j2].getValue() == 1 && squares[i2][j2].getValue() ==100));
        
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        label1.setBounds(squares[i1][j1].getboundsX(), squares[i1][j1].getboundsY() + 15, 170, 140);// Green
        label2.setBounds(squares[i2][j2].getboundsX(), squares[i2][j2].getboundsY() + 15, 170, 140);// Green
        System.out.println(squares[i1][j1].getValue() + "start Green1");
        BoardSquare EndSquare1 = findSquare(squares[i1][j1], Color.GREEN);
        System.out.println(squares[i2][j2].getValue() + "start Green2");
        BoardSquare EndSquare2 = findSquare(squares[i2][j2], Color.GREEN);
        Gamesnakes GreenSnake1 = new Gamesnakes(squares[i1][j1], EndSquare1);
        Gamesnakes GreenSnake2 = new Gamesnakes(squares[i2][j2], EndSquare2);
        snakes[4] = GreenSnake1;
        snakes[5] = GreenSnake2;
        label1.setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/GSnake.png")));
        label2.setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/Gsnake.png")));
        panel.add(label1);
        panel.add(label2);
    }
    
    private void uniqueSquares(){
    	int easyI , easyJ,hardI,hardJ, mediumI, mediumJ;
        do {
            easyI = generateRandomNumber_I(Color.WHITE); // Green snakes
            easyJ = generateRandomNumber_J(Color.WHITE);
        } while ((squares[easyI][easyJ].getValue() == 1 || squares[easyI][easyJ].getValue() ==100));
        System.out.println("Easy " + squares[easyI][easyI].getValue());
        boardlabels[easyI][easyI].setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/QuestionMark .png")));
    }
        
       
    private void setLadders(JPanel panel, int num, int random_i, int random_j, int boundX, int boundY, String imagePath, int width, int height) {
        int i, j, length = 0;
        boolean ladderOverlap,coincide;
        List<JLabel> ladderLabels = new ArrayList<>();
        
        do {
            i = random_i;
            j = random_j;

            JLabel ladderLabel = new JLabel();
            ladderLabels.add(ladderLabel);
            ladderLabel.setBounds(boundX, boundY, width, height);
            BoardSquare startSquare = findStartSquare_ladder(squares[i][j], num);
            BoardSquare endSquare = findEndSquare_ladder(squares[i][j], length, num, width);
            // Check if the start or end of the new ladder coincides with any existing ladder
             coincide = isLadderCoincide(startSquare.getRow(), startSquare.getCol(), endSquare.getRow(), endSquare.getCol());           
            if (coincide) {
                // If coincidence found, set a new random position for the ladder
                switch (num) {
                    case 1:
                        setladder1(panel);
                        break;
                    case 2:
                        setladder2(panel);
                        break;
                    case 3:
                        setladder3(panel);
                        break;
                    case 4:
                        setladder4(panel);
                        break;
                    case 5:
                        setladder5(panel);
                        break;
                    case 6:
                        setladder6(panel);
                        break;
                }
                return; // Exit the function to prevent setting the ladder again after finding a non-overlapping position
            }           
            // Print endSquare value and create ladder
            System.out.println(endSquare.getValue() + "end ladder" + num);
            GameLadder ladder = new GameLadder(startSquare, endSquare);
            ladders[length] = ladder;
            length++;
            
            // Use the provided image path
            ladderLabel.setIcon(new ImageIcon(MediumGameBoard.class.getResource(imagePath)));
            panel.add(ladderLabel);
        } while ( coincide);
    }

    private void setladder1(JPanel panel) {
    	int i = rand.nextInt(9);
    	int j = rand.nextInt(9);
    	setLadders(panel,1,i, j,squares[i][j].getboundsX(),squares[i][j].getboundsY(), "/images/ladder.png",110,110);
    }
    
    private void setladder2(JPanel panel) {
    	int i = rand.nextInt(8);
    	int j = rand.nextInt(8);
    	setLadders(panel,2, i, j,squares[i][j].getboundsX()-10, squares[i][j].getboundsY(), "/images/ladder2.png",110,165);
    }
    
    private void setladder3(JPanel panel) {
    	int i = rand.nextInt(7)+1;
    	int j = rand.nextInt(8);
    	setLadders(panel,3, i, j, squares[i][j].getboundsX()-20,squares[i][j].getboundsY()+25, "/images/ladder3.png",55,160);
    }
    private void setladder4(JPanel panel) {
    	int i = rand.nextInt(6)+1; //1 to 7
        int j = rand.nextInt(8)+1; //1 to 9
    	setLadders(panel,4 , i, j, squares[i][j].getboundsX()-10,squares[i][j].getboundsY(), "/images/ladder4.png",115,275);
    }
    private void setladder5(JPanel panel) {
    	int i = rand.nextInt(4)+1; //1 to 5
    	int j = rand.nextInt(7)+1; //1 to 9
    	setLadders(panel,5 ,i ,j ,squares[i][j].getboundsX()-10,squares[i][j].getboundsY(),"/images/ladder5.png" ,165,330);
    }
    private void setladder6(JPanel panel) {
		 int i = rand.nextInt(4); //row form 0 to 4 
		 int j = rand.nextInt(7)+2;//column from 2 to 9 
		 setLadders(panel,6, i, j,squares[i][j].getboundsX()-15, squares[i][j].getboundsY(),"/images/ladder6.png",165,385);
	}
    
    //private static int generateRandomNumber1() {
//
//        do {
//            hardI = generateRandomNumber1(Color.WHITE); // Green snakes
//            hardJ = generateRandomNumber1(Color.WHITE);
//        } while (isSquareOccupied(hardI, hardJ) || (squares[hardI][hardJ].getValue() == 1 || squares[hardI][hardJ].getValue() ==100 || squares[hardI][hardJ].getValue() == squares[easyI][easyJ].getValue() ));
//        System.out.println("Hard " + squares[hardI][hardJ].getValue());
//        JLabel label2 = new JLabel();
//        label2.setBounds(squares[hardI][hardJ].getboundsX(), squares[hardI][hardJ].getboundsY(), 55, 55);
//        label2.setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/QuestionMark.png")));
//
//      do {
//            mediumI = generateRandomNumber1(Color.WHITE); // Green snakes
//            mediumJ = generateRandomNumber1(Color.WHITE);
//        } while (isSquareOccupied(mediumI, mediumJ) || (squares[mediumI][mediumJ].getValue() == 1 || squares[mediumI][mediumJ].getValue() ==100 ||squares[hardI][hardJ].getValue() == squares[mediumI][mediumJ].getValue()));
//       System.out.println("Hard " + squares[mediumI][mediumJ].getValue());
//       JLabel label3 = new JLabel();
//       label3.setBounds(squares[mediumI][mediumJ].getboundsX(), squares[mediumI][mediumJ].getboundsY(), 55, 55);
//       label3.setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/QuestionMark.png")));


 //   }
    
    private static int generateRandomNumber_I(Color color) { //..-9
        Random random = new Random();
        int num_i;
        if(color == Color.GREEN ) { 
             num_i = random.nextInt(8)+2; //2-9
        }
        if(color == Color.BLUE ){ 
            num_i = random.nextInt(7)+3; //3-9
        }
        if(color == Color.YELLOW ){ 
        	num_i = random.nextInt(9)+1;//1-9
        }
        else {
             num_i = random.nextInt(10); //0-9
        }
        return num_i; 
    }
    
    private static int generateRandomNumber_J(Color color) { //..-9
        Random random = new Random();
        int num_j;
        if(color == Color.GREEN ) { 
             num_j = random.nextInt(9);  //0-8
        }
        if(color == Color.BLUE ){ 
            num_j = random.nextInt(7)+3; //3-9
        }
        if(color == Color.YELLOW ){ 
            num_j = random.nextInt(9); //0-8
        }
        else {
            num_j = random.nextInt(10); //0-9
        }
        return num_j; 
    }
    
      

 // Method to check if the start or end of the new ladder coincides with any existing ladder
    private boolean isLadderCoincide(int startRow, int startCol, int endRow, int endCol) {
        // Check if the start or end coincides with any existing ladder
        for (GameLadder ladder : ladders) {
            if (ladder != null) {
                if ((ladder.getStartSquare().getRow() == startRow && ladder.getStartSquare().getCol() == startCol)
                    || (ladder.getEndSquare().getRow() == endRow && ladder.getEndSquare().getCol() == endCol)) {
                    return true; // If the start or end coincides with an existing ladder, return true
                }
            }
        }
        return false;
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
 

    private BoardSquare findStartSquare_ladder(BoardSquare startSquare,int number) {
    	  for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
            if(number == 1) {
              if(squares[i][j].getboundsX() == startSquare.getboundsX()+ 55 &&squares[i][j].getboundsY() == startSquare.getboundsY()+ 55) {
            	  System.out.println(squares[i][j].getValue()+ " start ladder"+number);
            	  return squares[i][j];
              }
            }
            if(number == 2) {
            	if(squares[i][j].getboundsX() == startSquare.getboundsX()+55 &&squares[i][j].getboundsY() == startSquare.getboundsY()+110) {
              	  System.out.println(squares[i][j].getValue()+ " start ladder"+number);
              	  return squares[i][j];
                }
            	
            }
            if(number == 3) {
            	if(squares[i][j].getboundsX() == startSquare.getboundsX() &&squares[i][j].getboundsY()-165 == startSquare.getboundsY()) {
              	  System.out.println(squares[i][j].getValue()+"start ladder "+number);
              	  return squares[i][j];
                }
            	
            }
            if(number == 4) {
            	if(squares[i][j].getboundsX() == startSquare.getboundsX() &&squares[i][j].getboundsY()-220 == startSquare.getboundsY()) {
                	  System.out.println(squares[i][j].getValue()+ "start ladder"+number);
                	  return squares[i][j];
                  }
            }
            if(number == 5) {
            	if(squares[i][j].getboundsX() == startSquare.getboundsX() &&squares[i][j].getboundsY()-275 == startSquare.getboundsY()) {
                	  System.out.println(squares[i][j].getValue()+ "start ladder"+number);
                	  return squares[i][j];
                  }
            }
            if(number == 6) {
            	if(squares[i][j].getboundsX() == startSquare.getboundsX()+110 &&squares[i][j].getboundsY()-330 == startSquare.getboundsY()) {
                	  System.out.println(squares[i][j].getValue()+ "start ladder"+number);
                	  return squares[i][j];
                  }
            }
            }
        }
    	return null;
    }
    
    private BoardSquare findEndSquare_ladder(BoardSquare startSquare, int ladderLength, int laddernum, int width) {
        int startBoundsX = startSquare.getboundsX();
        int startBoundsY = startSquare.getboundsY();
        
        if(laddernum == 3 || laddernum == 2 || laddernum == 1) {
        	int endBoundsX = startBoundsX - (ladderLength * width); //Because ladder 4 is extended to i+1 in j column
 	        int endBoundsY = startBoundsY;
 	        // Find the corresponding end square based on bounds
 	        for (int i = 0; i < 10; i++) {
 	            for (int j = 0; j < 10; j++) {
 	                if (squares[i][j].getboundsX() == endBoundsX && squares[i][j].getboundsY() == endBoundsY) {
 	                    return squares[i][j];
 	                }
 	            }
 	        }
        }
        
        if(laddernum == 4) {
	        int endBoundsX = startBoundsX - (ladderLength * width) + 55; //Because ladder 4 is extended to i+1 in j column
	        int endBoundsY = startBoundsY;
	        // Find the corresponding end square based on bounds
	        for (int i = 0; i < 10; i++) {
	            for (int j = 0; j < 10; j++) {
	                if (squares[i][j].getboundsX() == endBoundsX && squares[i][j].getboundsY() == endBoundsY) {
	                    return squares[i][j];
	                }
	            }
	        }
        }
        
        if(laddernum == 5) {
        	int extendedSquares = 2; // Number of squares the ladder is extended to the right
            int endBoundsX = startBoundsX + (ladderLength * width) + (extendedSquares * 55); // Adjust for ladder 5 extending to i+2 in the j column
	        int endBoundsY = startBoundsY;
	        // Find the corresponding end square based on bounds
	        for (int i = 0; i < 10; i++) {
	            for (int j = 0; j < 10; j++) {
	                if (squares[i][j].getboundsX() == endBoundsX && squares[i][j].getboundsY() == endBoundsY) {
	                    return squares[i][j];
	                }
	            }
	        }
        }
        if(laddernum == 6) {
            int endBoundsX = startBoundsX + (ladderLength * width); 
	        int endBoundsY = startBoundsY;
	        // Find the corresponding end square based on bounds
	        for (int i = 0; i < 10; i++) {
	            for (int j = 0; j < 10; j++) {
	                if (squares[i][j].getboundsX() == endBoundsX && squares[i][j].getboundsY() == endBoundsY) {	         
	                    return squares[i][j];
	                }
	            }
	        }
        }
        
        return null; // Handle case where start square is not found
    }
 
 
    public static void main(String[] args) {
        // Running the application
        SwingUtilities.invokeLater(() -> new MediumGameBoard());
    }
}
