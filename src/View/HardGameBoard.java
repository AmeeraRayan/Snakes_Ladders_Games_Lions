package View; 
import java.io.Console;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.PanelUI;

import Controller.GameController;
import Model.Ladder;
import Model.Player;
import Model.Snake;
import Model.Square;
import Model.Board;
import Model.Dice;
import Model.Game;
import Model.SquareType;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.OverlayLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
public class HardGameBoard extends JFrame{
	private static final int GRID_SIZE = 13;
	private static final Color[] COLORS = new Color[]{new Color(175, 238, 238), Color.WHITE, new Color(255, 255, 204), new Color(255, 51, 102), new Color(152, 251, 152)};
	private Color[][] boardColors = new Color[GRID_SIZE][GRID_SIZE];
    private Square[][] squares = new Square[GRID_SIZE][GRID_SIZE];
    private JLabel[][] boardlabels = new JLabel[GRID_SIZE][GRID_SIZE];
    private Dice dice = new Dice("medium"); 
    private Snake[] snakes = new Snake[8];
    private Ladder[] ladders = new Ladder[8];
    private Square[] quastionSquares = new Square[6];
    private Square[] surpriseSquares = new Square[2];
	private static Map<ArrayList<Integer>,String> takenCells = new HashMap<>();

    private Board HardBoard = new Board(GRID_SIZE);
    //JFrame frame;
    Random rand = new Random();
    int[] ladderLengths = {1, 2, 3, 4, 5, 6 , 7 , 8 };
    public HardGameBoard(Game game) {
        // Setting up the main frame
    	//frame = new JFrame();
        setTitle("Game Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);
        // Creating the outer panel with BorderLayout
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(null);
        
        JLabel playerName = new JLabel("");
        playerName.setBounds(853, 51, 300, 100);
        outerPanel.add(playerName);

        
        JButton diceButton = new JButton("");
        diceButton.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/dice 3.jpg")));
        diceButton.setBounds(1012, 640, 78, 81);
        outerPanel.add(diceButton);
  
        
        // Creating the inner panel
        JPanel innerPanel = new JPanel();
        initializeBoard(innerPanel,outerPanel);
        game.setBoard(HardBoard);
        game.setDice(dice);

        innerPanel.setBounds(81, 51, 715, 715);
        innerPanel.setBackground(Color.WHITE);
 
        // Adding the inner panel to the center of the outer panel
        outerPanel.add(innerPanel);
        innerPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        // Adding the outer panel to the frame
        this.getContentPane().add(outerPanel);

        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/HardGame .png")));
        lblNewLabel.setBounds(0, 0, 1210, 850);
        outerPanel.add(lblNewLabel);
        this.setVisible(true);
        //81 51
    }
    private void initializeBoard(JPanel panel, JPanel outerPanel) { 
    	 int cellSize = 715 / GRID_SIZE; // the innerPanel is 550x550 and each cell is 55x55 pixels
         int count=0;
         int surpriseCount=0;
         Set<Integer> chosenCells = new HashSet<>(); // Track chosen cell numbers
         Set<Integer> chosenSurpriseCells = new HashSet<>(); // Track chosen cell numbers for surprise squares
         while (chosenCells.size() < 3) {
             int cellNumber = rand.nextInt(98) + 2; // Generate a random cell number between 2 and 99
             chosenCells.add(cellNumber); // Add the chosen cell number to the set
         }
      // Add surprise squares
         while (chosenSurpriseCells.size() < 2) {
             int cellNumber = rand.nextInt(98) + 2; // Generate a random cell number between 2 and 99
             if (!chosenCells.contains(cellNumber) && !chosenSurpriseCells.contains(cellNumber)) {
                 chosenSurpriseCells.add(cellNumber); // Add the chosen cell number to the set
             }
         }
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
                 // Calculate the bounds for each label
                 int x = j * cellSize + panel.getBounds().x + 81; // Adjust for the actual position of the panel
                 int y = i * cellSize + panel.getBounds().y + 51;
                 // Initialize square type based on cellNumber
                 if (chosenCells.contains(cellNumber)) {
                     label.setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/QuestionMark.png")));
                     label.setText(""); // Set empty string for text
                     squares[i][j] = new Square(i, j, SquareType.QUESTION, x, y, cellNumber);
                     quastionSquares[count] = squares[i][j];
                     ArrayList<Integer> arrayList= new ArrayList<Integer>();
                     arrayList.add(i);
                     arrayList.add(j);
                     takenCells.put(arrayList,"question"+count);
                     count++;
                 } else if (chosenSurpriseCells.contains(cellNumber)) {
                     label.setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/!.png")));
                     label.setText(""); // Set empty string for text
                     squares[i][j] = new Square(i, j, SquareType.SURPRISE, x, y, cellNumber);
                     surpriseSquares[surpriseCount] = squares[i][j];
                     ArrayList<Integer> arrayList= new ArrayList<Integer>();
                     arrayList.add(i);
                     arrayList.add(j);
                     takenCells.put(arrayList,"surprise"+surpriseCount);
                     surpriseCount++; // Increment the surprise count
                 } else {
                     squares[i][j] = new Square(i, j, SquareType.NORMAL, x, y, cellNumber);
                 }
                 boardlabels[i][j] = label;
                
                 //System.out.println("Label " + cellNumber + " bounds: x=" + x + ", y=" + y + ", i=" + squares[i][j].getRow() + ", j=" + j);
             }
         }
  
 
        setRedSnakes(outerPanel);
        setYellowSnake(outerPanel);
        setBlueSnakes(outerPanel);
        setGreenSnakes(outerPanel);
        setLadders(outerPanel);
        for (Map.Entry<ArrayList<Integer>,String> entry : takenCells.entrySet()) {
        	ArrayList<Integer>  key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
        }
        HardBoard.initializeSnakesAndLaddersForMedium(squares,snakes,ladders,quastionSquares);
             
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
        ArrayList<Integer> arr= new ArrayList<Integer>();
        ArrayList<Integer> arr2= new ArrayList<Integer>();
        // Place the first red snake
        do {
            i1 = rand.nextInt(9)+1; // Red snake 1
            j1 = rand.nextInt(9)+1;
            arr.add(i1);
            arr.add(j1);
        } while (takenCells.containsKey(arr) || (i1==0 && j1==0));
        takenCells.put(arr,"redsnak1");
        // Place the second red snake
        do {
            i2 = rand.nextInt(9)+1; // Red snake 2
            j2 = rand.nextInt(9)+1;
            arr2.add(i2);
            arr2.add(j2);
        } while (takenCells.containsKey(arr2) || (i2 == i1 && j2 == j1)|| (i2==0 && j2==0));
        takenCells.put(arr2,"redsnake2");

        JLabel label_1 = new JLabel();
		label_1.setBounds(squares[i1][j1].getBoundsX()+10, squares[i1][j1].getBoundsY(), 55, 55);
        Snake redSnake1 = new Snake(squares[i1][j1], squares[9][0]);
        System.out.println(squares[i1][j1].getValue()+"redsnake1");
        snakes[0] = redSnake1;
        panel.add(label_1);
        label_1.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/RedSnake.png")));
        JLabel label_2 = new JLabel();
        label_2.setBounds(squares[i2][j2].getBoundsX()+10, squares[i2][j2].getBoundsY(), 50, 50);
        //object red snake 2 
        Snake redSnake2 = new Snake(squares[i2][j2], squares[9][0]);
        System.out.println(squares[i2][j2].getValue()+"redsanke2");
        snakes[1] = redSnake2;
        panel.add(label_2);
        label_2.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/RedSnake.png")));
    }

    
    private void setYellowSnake(JPanel panel) {
        int i, j, i1 , j1;
        ArrayList<Integer> arr= new ArrayList<Integer>();
        ArrayList<Integer> arr1= new ArrayList<Integer>();
        do {
            i = generateRandomNumber_I(Color.YELLOW); // Yellow snakes
            j= generateRandomNumber_J(Color.YELLOW);
            arr.add(i);
            arr.add(j);
        } while(takenCells.containsKey(arr)  || (i==0 && j==0));       
        takenCells.put(arr,"yellowSnake1");   

        JLabel yellowSnakeLabel = new JLabel();
        yellowSnakeLabel.setBounds(squares[i][j].getBoundsX()+10, squares[i][j].getBoundsY(), 100, 100);// Yellow
        System.out.println(squares[i][j].getValue()+"start yellow" + squares[i][j].getRow()+ "i="+i);
        Square EndSquare = findSquare(squares[i][j], Color.YELLOW);
        Snake yellowSnake = new Snake(squares[i][j], EndSquare);
        //System.out.println("start: "+squares[i][j].getValue() + " End : "+EndSquare.getValue());
        snakes[2] = yellowSnake;
        yellowSnakeLabel.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/rightYellow.png")));
        do {
            i1 = generateRandomNumber_I(Color.YELLOW); // Yellow snakes
            j1= generateRandomNumber_J(Color.YELLOW);
            arr1.add(i1);
            arr1.add(j1);
        } while(takenCells.containsKey(arr1) ||  (i1 == i && j1 == j)|| (i==0 && j==0));       
        takenCells.put(arr1,"yellowSnake2");    

        JLabel yellowSnakeLabel2 = new JLabel();
        yellowSnakeLabel2.setBounds(squares[i1][j1].getBoundsX()+10, squares[i1][j1].getBoundsY(), 100, 100);// Yellow
        System.out.println(squares[i1][j1].getValue()+"start yellow" + squares[i1][j1].getRow()+ "i1="+i1);
        Square EndSquare2 = findSquare(squares[i1][j1], Color.YELLOW);
        Snake yellowSnake2 = new Snake(squares[i1][j1], EndSquare2);
        //System.out.println("start: "+squares[i][j].getValue() + " End : "+EndSquare.getValue());
        snakes[3] = yellowSnake2;
        yellowSnakeLabel2.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/rightYellow.png")));
        
        panel.add(yellowSnakeLabel);
        panel.add(yellowSnakeLabel2);

    }

    private void setBlueSnakes(JPanel panel) {
        int i, j , i1 , j1;
        ArrayList<Integer> arr= new ArrayList<Integer>();
        ArrayList<Integer> arr1= new ArrayList<Integer>();
        do {
            i = generateRandomNumber_I(Color.BLUE); // Blue snakes
            j = generateRandomNumber_J(Color.BLUE);
            arr.add(i);
            arr.add(j);
        } while(takenCells.containsKey(arr)  || (i==0 && j==0));
        takenCells.put(arr,"blueSnake1");
        
        do {
            i1 = generateRandomNumber_I(Color.BLUE); // Blue snakes
            j1 = generateRandomNumber_J(Color.BLUE);
            arr1.add(i1);
            arr1.add(j1);
        } while(takenCells.containsKey(arr1)  || (i1 == i && j1 == j)|| (i==0 && j==0));
        takenCells.put(arr1,"blueSnake2");
        
        JLabel labelBlue1 = new JLabel();
        labelBlue1.setBounds(squares[i][j].getBoundsX() - 100, squares[i][j].getBoundsY() + 15, 140, 170);// BLUE
        Square EndSquare = findSquare(squares[i][j], Color.BLUE);
        System.out.println(squares[i][j].getValue()+"start blue"+" row="+squares[i][j].getRow()+ "i="+i);
        Snake BlueSnake1 = new Snake(squares[i][j], EndSquare);
        snakes[4] = BlueSnake1;
        labelBlue1.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/SnakeBlueRight.png")));
        panel.add(labelBlue1);
        
        JLabel labelBlue2 = new JLabel();
        labelBlue2.setBounds(squares[i1][j1].getBoundsX() - 100, squares[i1][j1].getBoundsY() + 15, 140, 170);// BLUE
        Square EndSquare2 = findSquare(squares[i1][j1], Color.BLUE);
        System.out.println(squares[i1][j1].getValue()+"start blue"+" row="+squares[i1][j1].getRow()+ "i1="+i1);
        Snake BlueSnake2 = new Snake(squares[i1][j1], EndSquare2);
        snakes[5] = BlueSnake2;
        labelBlue2.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/SnakeBlueRight.png")));
        panel.add(labelBlue2);
    }

    
    private void setGreenSnakes(JPanel panel) {
        int i1, j1,i2,j2;
        ArrayList<Integer> arr1= new ArrayList<Integer>();
        ArrayList<Integer> arr2= new ArrayList<Integer>();
        do {
            i1 = generateRandomNumber_I(Color.GREEN); // Green snakes
            j1 = generateRandomNumber_J(Color.GREEN);
            arr1.add(i1);
            arr1.add(j1);
        }while(takenCells.containsKey(arr1) || (i1==0 && j1==0));
        takenCells.put(arr1,"greensnake1");
        do {             
            i2 = generateRandomNumber_I(Color.GREEN); // Green snakes
            j2 = generateRandomNumber_J(Color.GREEN);
            arr2.add(i2);
            arr2.add(j2);
        }while(takenCells.containsKey(arr2) || (i2 == i1 && j2 == j1) || (i2==0 && j2==0));
        takenCells.put(arr2,"greensnake2");
             
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        label1.setBounds(squares[i1][j1].getBoundsX()+10, squares[i1][j1].getBoundsY() + 15, 170, 140);// Green
        label2.setBounds(squares[i2][j2].getBoundsX()+10, squares[i2][j2].getBoundsY() + 15, 170, 140);// Green
        System.out.println(squares[i1][j1].getValue() + "start Green1"+" i="+squares[i1][j1].getRow());
        Square EndSquare1 = findSquare(squares[i1][j1], Color.GREEN);
        System.out.println(squares[i2][j2].getValue() + "start Green2"+" i="+squares[i2][j2].getRow());
        Square EndSquare2 = findSquare(squares[i2][j2], Color.GREEN);
        Snake GreenSnake1 = new Snake(squares[i1][j1], EndSquare1);
        Snake GreenSnake2 = new Snake(squares[i2][j2], EndSquare2);
        snakes[4] = GreenSnake1;
        snakes[5] = GreenSnake2;
        label1.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/GSnake.png")));
        label2.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/Gsnake.png")));
        panel.add(label1);
        panel.add(label2);
    }
           

    private void setLadders(JPanel panel) {
        for (int num = 1; num <= 8; num++) {
            setLadder(panel, num);
        }
    }
    
    private void setLadder(JPanel panel, int num) {
        int i, j;
        Square startSquare, endSquare;
        JLabel ladderLabel;
        ArrayList<Integer> arr1= new ArrayList<Integer>();
        do {
            i = generateRandomIJ(num)[0]; // Generate random row index
            j = generateRandomIJ(num)[1]; // Generate random column index
            arr1.clear();
            startSquare = findStartSquare_ladder(squares[i][j], num);
            arr1.add(startSquare.getRow());
            arr1.add(startSquare.getCol());
        } while (takenCells.containsKey(arr1) || (i==0 && j==0));
        takenCells.put(arr1,"ladder"+num);
 
        //startSquare = findStartSquare_ladder(squares[i][j], num);
        endSquare = squares[i][j];
        System.out.println(endSquare.getValue()+"end ladder "+num +" j=" +endSquare.getCol());
        ladderLabel = new JLabel();
        Ladder ladder = new Ladder(startSquare, endSquare);
        ladders[num - 1] = ladder;
        // Set ladder image and add it to the panel
        ladderLabel.setBounds((ladder.getSquareEnd().getBoundsX()+ladderCalc(num)[2]), (ladder.getSquareEnd().getBoundsY()+ladderCalc(num)[3]), ladderCalc(num)[0], ladderCalc(num)[1]);
        ladderLabel.setIcon(new ImageIcon(MediumGameBoard.class.getResource("/images/ladder" + num + ".png")));
        panel.add(ladderLabel);
       
    }

    private int[] ladderCalc(int num) {
    	int width,heigth,X,Y;
    	int[] clac = new int[4];
        if(num == 1) {
        	width = 110;
        	heigth = 110;
        	X = 0;
        	Y = -5;
        	clac[0] = width;
        	clac[1] = heigth;
        	clac[2] = X;
        	clac[3] = Y;
        }
        if(num == 2) {
        	width = 110;
        	heigth = 165;
        	X = -10;
        	Y = 0;
        	clac[0] = width;
        	clac[1] = heigth;
        	clac[2] = X;
        	clac[3] = Y;
        }
        if(num == 3) {
        	width = 55;
        	heigth = 165;
        	X = -20;
        	Y = 25;
        	clac[0] = width;
        	clac[1] = heigth;
        	clac[2] = X;
        	clac[3] = Y;
        }
        if(num == 4) {
        	width = 115;
        	heigth = 275;
        	X = -65;
        	Y = 0;
        	clac[0] = width;
        	clac[1] = heigth;
        	clac[2] = X;
        	clac[3] = Y;
        }
        if(num == 5) {
        	width = 165;
        	heigth = 330;
        	X = -115;
        	Y = 0;
        	clac[0] = width;
        	clac[1] = heigth;
        	clac[2] = X;
        	clac[3] = Y;
        }
        if(num == 6) {
        	width = 165;
        	heigth = 385;
        	X = -15;
        	Y = 0;
        	clac[0] = width;
        	clac[1] = heigth;
        	clac[2] = X;
        	clac[3] = Y;
        }
        if(num == 7) {
        	width = 165;
        	heigth = 440;
        	X = -10;
        	Y = 10;
        	clac[0] = width;
        	clac[1] = heigth;
        	clac[2] = X;
        	clac[3] = Y;
        }
        if(num == 8) {
        	width = 165;
        	heigth = 460;
        	X = -15;
        	Y = 20;
        	clac[0] = width;
        	clac[1] = heigth;
        	clac[2] = X;
        	clac[3] = Y;
        }
        return clac;
    }
    
    
    private static int generateRandomNumber_I(Color color) { //..-9
        Random random = new Random();
        int num_i = 0;
        if(color == Color.GREEN ) { 
             num_i = random.nextInt(10); //0-7
             System.out.println(num_i+"random i in func");
        }
        if(color == Color.BLUE ){ 
            num_i = random.nextInt(10); //0-6
            System.out.println(num_i+"random i in func blue");
        }
        if(color == Color.YELLOW ){ 
        	num_i = random.nextInt(12);//0-8
        	System.out.println(num_i+"random i in func");
        }
       
        return num_i; 
    }
    
    private static int generateRandomNumber_J(Color color) { //..-9
        Random random = new Random();
        int num_j = 0;
        if(color == Color.GREEN ) { 
             num_j = random.nextInt(12);  //0-8
        }
        if(color == Color.BLUE ){ 
            num_j = random.nextInt(10)+3; //3-9
        }
        if(color == Color.YELLOW ){ 
            num_j = random.nextInt(12); //0-8
        }
        
        return num_j; 
    }
    
    private static int[] generateRandomIJ(int num) {
    	Random random = new Random();
    	int i,j;
    	int IANDJ[] = new int[2];
    	if(num ==1) {
    		i = random.nextInt(9); //0-8
        	j = random.nextInt(9); //0-8
        	IANDJ[0] = i;
        	IANDJ[1] = j;
    	}
    	if(num ==2) {
    		i = random.nextInt(8);//0-7
        	j = random.nextInt(9);//0-8
        	IANDJ[0] = i;
        	IANDJ[1] = j;
    	}
    	if(num ==3) {
    		i = random.nextInt(7);//0-6
        	j = random.nextInt(10);//0-9
        	IANDJ[0] = i;
        	IANDJ[1] = j;
    	}
    	if(num ==4) {
    		i = random.nextInt(6); //0-5
    		j = random.nextInt(8)+1; //1-9
        	IANDJ[0] = i;
        	IANDJ[1] = j;
    	}
    	if(num ==5) {
    		i = random.nextInt(5); //0-4
    		j = random.nextInt(8)+2; //2-9
        	IANDJ[0] = i;
        	IANDJ[1] = j;
    	}
    	else {
    		 i = random.nextInt(4);//0-3
   		  	 j = random.nextInt(8);//0-7
             IANDJ[0] = i;
             IANDJ[1] = j;
    	}
    	return IANDJ;
    }

    private Square findSquare(Square StartSquare,Color color) {
    	  for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
            if(color == Color.YELLOW) {
              if(squares[i][j].getBoundsX() == StartSquare.getBoundsX()+ 55 &&squares[i][j].getBoundsY() == StartSquare.getBoundsY()+ 55) {
            	  System.out.println(squares[i][j].getValue()+ " End yellow");
            	  return squares[i][j];
              }
            }
            if(color == Color.BLUE) {
            	if(squares[i][j].getBoundsX()+110 == StartSquare.getBoundsX() &&squares[i][j].getBoundsY()-165 == StartSquare.getBoundsY()) {
              	  System.out.println(squares[i][j].getValue()+ " EndBlue");
              	  return squares[i][j];
                }
            	
            }
            if(color == Color.GREEN) {
            	if(squares[i][j].getBoundsX() == StartSquare.getBoundsX()+55 &&squares[i][j].getBoundsY() == StartSquare.getBoundsY()+110) {
              	  System.out.println(squares[i][j].getValue()+" EndSquare");
              	  return squares[i][j];
                }
            	
            }
            }
        }
    	return null;
    }
    

    private Square findStartSquare_ladder(Square startSquare,int number) {
    	  for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
            if(number == 1) {
              if(squares[i][j].getBoundsX() == startSquare.getBoundsX()+55 &&squares[i][j].getBoundsY() == startSquare.getBoundsY()+ 55) {
            	  System.out.println(squares[i][j].getValue()+ " start ladder"+number);
            	  return squares[i][j];
              }
            }
            if(number == 2) {
            	if(squares[i][j].getBoundsX() == startSquare.getBoundsX()+55 &&squares[i][j].getBoundsY() == startSquare.getBoundsY()+110) {
              	  System.out.println(squares[i][j].getValue()+ " start ladder"+number);
              	  return squares[i][j];
                }
            	
            }
            if(number == 3) {
            	if(squares[i][j].getBoundsX() == startSquare.getBoundsX() &&squares[i][j].getBoundsY()-165 == startSquare.getBoundsY()) {
              	  System.out.println(squares[i][j].getValue()+"start ladder "+number);
              	  return squares[i][j];
                }
            	
            }
            if(number == 4) {
            	if(squares[i][j].getBoundsX() == startSquare.getBoundsX()-55 &&squares[i][j].getBoundsY()-220 == startSquare.getBoundsY()) {
                	  System.out.println(squares[i][j].getValue()+ "start ladder"+number);
                	  return squares[i][j];
                  }
            }
            if(number == 5) {
            	if(squares[i][j].getBoundsX() == startSquare.getBoundsX()-110 &&squares[i][j].getBoundsY()-275 == startSquare.getBoundsY()) {
                	  System.out.println(squares[i][j].getValue()+ "start ladder"+number);
                	  return squares[i][j];
                  }
            }
            if(number == 6) {
            	if(squares[i][j].getBoundsX() == startSquare.getBoundsX()+110 &&squares[i][j].getBoundsY()-330 == startSquare.getBoundsY()) {
                	  System.out.println(squares[i][j].getValue()+ "start ladder"+number);
                	  return squares[i][j];
                  }
            }
            if(number == 7) {
            	if(squares[i][j].getBoundsX() == startSquare.getBoundsX() &&squares[i][j].getBoundsY()-385 == startSquare.getBoundsY()) {
                	  System.out.println(squares[i][j].getValue()+ "start ladder"+number);
                	  return squares[i][j];
                  }
            }
            if(number == 8) {
            	if(squares[i][j].getBoundsX() == startSquare.getBoundsX()+110 &&squares[i][j].getBoundsY()-440 == startSquare.getBoundsY()) {
                	  System.out.println(squares[i][j].getValue()+ "start ladder"+number);
                	  return squares[i][j];
                  }
            }
            
            }
        }
    	return null;
    }
    
}
