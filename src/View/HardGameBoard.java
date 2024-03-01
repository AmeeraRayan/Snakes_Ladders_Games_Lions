package View; 
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Controller.GameController;
import Model.Ladder;
import Model.MediumBoard;
import Model.Player;
import Model.Snake;
import Model.Square;
import Model.BoardLevelTemplate;
import Model.Dice;
import Model.Game;
import Model.HardBoard;
import Model.GameDetails;
import Model.SquareType;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
 
public class HardGameBoard extends JFrame{
    private Game game;
    final JLabel jl;
	private static final int GRID_SIZE = 13;
	private static final Color[] COLORS = new Color[]{new Color(175, 238, 238), Color.WHITE, new Color(255, 255, 204), new Color(255, 51, 102), new Color(152, 251, 152)};
	private Color[][] boardColors = new Color[GRID_SIZE][GRID_SIZE];
    private Square[][] squares = new Square[GRID_SIZE][GRID_SIZE];
    private JLabel[][] boardlabels = new JLabel[GRID_SIZE][GRID_SIZE];
    private Dice dice = new Dice("Hard"); 
	private static Map<ArrayList<Integer>,String> takenCells = new HashMap<>();
    private Snake[] snakes = new Snake[8];
    private Ladder[] ladders = new Ladder[8];
    public static JLabel[] playersLable;
    private GameController controller ; 
    private int index = 0 ;
    private boolean musicStatus ;
   private List<Player> arraylistOrderByPosition ; 
    private Square[] quastionSquares = new Square[3];
    private Square[] surpriseSquares = new Square[2];
    private JButton diceButton;
    private JTextField textPane ; 
    private int WinValue = 169 ; 
    private long startTime;
	private Timer gameTimer;
    //JFrame frame;
    Random rand = new Random();
    int[] ladderLengths = {1, 2, 3, 4, 5, 6 , 7 , 8 };
    private JTextField playernames;
    Player CurrentPlayer ;
	private StringBuilder htmlBuilder ;
    private JTextPane textPane_1 ;
    private BoardLevelTemplate hardBoard;
    private boolean status = false ; 
    private Timer turnTimer;
   private boolean isGamePaused = false  ; 
    
    public HardGameBoard(Game game) {
    	this.game=game;
    	setTitle("Game Board");
    	this.hardBoard=new HardBoard();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1166, 816);
        // Creating the outer panel with BorderLayout
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(null);
        
        JButton pauseButton = new JButton("Stop game");
//        pauseButton.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		 if (isGamePaused) {
//        		        resumeGame();
//        		        pauseButton.setText("Pause");
//        		    } else {
//        		        pauseGame();
//        		        pauseButton.setText("Resume");
//        		    }
//        		
//        	}
//        });
        pauseButton.setBounds(1092, 25, 50, 30);
        outerPanel.add(pauseButton);
        
     
        
        
         textPane = new JTextField();
        textPane.setBounds(792, 81, 350, 65);
        outerPanel.add(textPane);
        
        controller = new GameController(game,this);
        controller.CallQuestionDataFunc();
        diceButton = new JButton("");

     
         jl = new JLabel("00:00", SwingConstants.CENTER);
        jl.setLocation(792, 185);
        outerPanel.add(jl);
        jl.setVisible(true);
        jl.setSize(170, 106);
        Font labelFont = jl.getFont();
        jl.setFont(new Font(labelFont.getName(), Font.PLAIN, 28));
        startTime = System.currentTimeMillis();
		gameTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long now = System.currentTimeMillis();
				long elapsed = now - startTime;
				long minutes = TimeUnit.MILLISECONDS.toMinutes(elapsed);
				long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsed) % 60;
				jl.setText(String.format("%02d:%02d", minutes, seconds));
			}
		});
		 turnTimer = new Timer(10000, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                turnTimer.stop(); // Stop the timer to prevent it from repeating
	                animateDiceRoll(); // Automatically roll the dice
	            }
	        });
		
		gameTimer.start();
        controller.MainSound("play");  
        musicStatus = true ; 
        JButton btnNewButton = new JButton("s");
        btnNewButton.setBounds(1034, 25, 50, 30);
        outerPanel.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(musicStatus == true ) {
                controller.MainSound("stop");
        		musicStatus = false ;
        		}
        		else {
                    controller.MainSound("play");
            		musicStatus = true ;

        		}

        	}
        });

		textPane.setText("\n    Turn : " + game.getCurrentPlayer().getName());
        textPane.setAlignmentX(0.2f);
        textPane.setFont(new Font("David", Font.BOLD | Font.ITALIC, 27));
        textPane.setAlignmentY(Component.TOP_ALIGNMENT);
        controller.setPlayerBackgroundColor(game.getCurrentPlayer().getColor(), textPane);
        arraylistOrderByPosition = game.getPlayers();
        startNewTurn();
        diceButton.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent e) {
                  controller.DiceRollingSound();
                  index = game.getCurrentPlayerIndex();
                  if (turnTimer.isRunning()) {
                      turnTimer.stop(); // Stop the countdown as the player is taking action
                  }
                  animateDiceRoll();
                  Player CurrentPlayer = game.getPlayers().get(index);
                  // Start dice roll animation
                   // This should ideally be called AFTER the animation, consider simulating the result for the animation and calculating it for the game logic after
                              diceButton.setEnabled(true);
                              game.setCurrentPlayerIndex(index);
                              game.setCurrentPlayer(game.getPlayers().get(index));
                              textPane.setText("\n Turn: " + game.getCurrentPlayer().getName());
                              textPane.setEditable(false);
                              endTurn();
                              controller.setPlayerBackgroundColor(game.getCurrentPlayer().getColor(), textPane);
                              updateTextPane(arraylistOrderByPosition);
                              startNewTurn();
                       
              }
          });
     
       
        turnTimer.setRepeats(true); 
        diceButton.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/dice 3.jpg")));
        diceButton.setBounds(1006, 655, 78, 81);
        outerPanel.add(diceButton);

        // Creating the inner panel
        JPanel innerPanel = new JPanel();
        initializeBoard(innerPanel,outerPanel);
        
        game.setBoard(hardBoard);
        game.setDice(dice);
        textPane.setText("\n    Turn : " + game.getCurrentPlayer().getName());
        textPane.setAlignmentX(0.2f);
        textPane.setFont(new Font("David", Font.BOLD | Font.ITALIC, 27));
        
        IntilaizePlayerPositionView(game , controller , outerPanel);


     
        innerPanel.setBounds(42, 42, 715, 715);
        innerPanel.setBackground(Color.WHITE);
        // Adding the inner panel to the center of the outer panel
        outerPanel.add(innerPanel);
        innerPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html><body><ul>");
        for (Player p : arraylistOrderByPosition) {
            htmlBuilder.append("<li>").append(p.getName() + " " + p.getPosition()).append("</li>");
        }
        htmlBuilder.append("</ul></body></html>");
        String htmlString = htmlBuilder.toString();
        
        

         textPane_1 = new JTextPane();
         textPane_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        
        textPane_1.setBackground(new Color(204, 153, 102));
        textPane_1.setContentType("text/html"); // Set content type to text/html
        textPane_1.setText(htmlString);
        outerPanel.add(textPane_1);
         
        textPane_1.setBounds(977, 179, 140, 150);
        outerPanel.add(textPane_1);
        // Adding the outer panel to the frame
        this.getContentPane().add(outerPanel);
        

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/BackHard.png")));
        lblNewLabel.setBounds(-41, -39, 1257, 1200);
        outerPanel.add(lblNewLabel);
        
        JLabel label = new JLabel("New label");
        label.setBounds(866, 455, 45, 13);
        outerPanel.add(label);
        this.setVisible(true);
    }
   


	private void initializeBoard(JPanel panel, JPanel outerPanel) { 
        int cellSize = 715 / GRID_SIZE; // the innerPanel is 715*715 and each cell is 55x55 pixels
        int count=0;
        int surpriseCount=0;
        Set<Integer> chosenCells = new HashSet<>(); // Track chosen cell numbers
        Set<Integer> chosenSurpriseCells = new HashSet<>(); // Track chosen cell numbers for surprise squares
        while (chosenCells.size() < 3) {
            int cellNumber = rand.nextInt(167) + 2; // Generate a random cell number between 2 and 99
            chosenCells.add(cellNumber); // Add the chosen cell number to the set
        }
     // Add surprise squares
        while (chosenSurpriseCells.size() < 2) {
            int cellNumber = rand.nextInt(167) + 2; // Generate a random cell number between 2 and 99
            if (!chosenCells.contains(cellNumber) && !chosenSurpriseCells.contains(cellNumber)) {
                chosenSurpriseCells.add(cellNumber); // Add the chosen cell number to the set
            }
        }
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
            	  int cellNumber = (GRID_SIZE - i) * GRID_SIZE - j;
                  if (i % 2 == 0) {
                      cellNumber = (GRID_SIZE - i - 1) * GRID_SIZE + j + 1;
                  }
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
                int x = j * cellSize + panel.getBounds().x + 51; // Adjust for the actual position of the panel
                int y = i * cellSize + panel.getBounds().y + 49;
                if (chosenCells.contains(cellNumber)) {
                    label.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/QuestionMark.png")));
                    label.setText(""); // Set empty string for text
                    squares[i][j] = new Square(i, j, SquareType.QUESTION, x, y, cellNumber);
                    quastionSquares[count] = squares[i][j];
                    ArrayList<Integer> arrayList= new ArrayList<Integer>();
                    arrayList.add(i);
                    arrayList.add(j);
                    takenCells.put(arrayList,"question"+count);
                    count++;
                } else if (chosenSurpriseCells.contains(cellNumber)) {
                    label.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/QuestionMarkM.png")));
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
                if(cellNumber == 169) {
                    label.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/StarWin.png")));
                    label.setText(""); // Set empty string for text
 
                }
                // Initialize square type based on cellNumber
              
                boardlabels[i][j] = label;
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
        }
 
        hardBoard.startGame(squares,snakes,ladders,quastionSquares, 0);
       
     
 
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
        snakes[0] = redSnake1;
        panel.add(label_1);
        label_1.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/RedSnake.png")));
        JLabel label_2 = new JLabel();
        label_2.setBounds(squares[i2][j2].getBoundsX()+10, squares[i2][j2].getBoundsY(), 50, 50);
        //object red snake 2 
        Snake redSnake2 = new Snake(squares[i2][j2], squares[9][0]);
        snakes[1] = redSnake2;
        panel.add(label_2);
        label_2.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/RedSnake.png")));
    }

//    
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
        Square EndSquare = findSquare(squares[i][j], Color.YELLOW);
        Snake yellowSnake = new Snake(squares[i][j], EndSquare);
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
        Square EndSquare2 = findSquare(squares[i1][j1], Color.YELLOW);
        Snake yellowSnake2 = new Snake(squares[i1][j1], EndSquare2);
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
        Snake BlueSnake1 = new Snake(squares[i][j], EndSquare);
        snakes[4] = BlueSnake1;
        labelBlue1.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/SnakeBlueRight.png")));
        panel.add(labelBlue1);
        
        JLabel labelBlue2 = new JLabel();
        labelBlue2.setBounds(squares[i1][j1].getBoundsX() - 100, squares[i1][j1].getBoundsY() + 15, 140, 170);// BLUE
        Square EndSquare2 = findSquare(squares[i1][j1], Color.BLUE);
        Snake BlueSnake2 = new Snake(squares[i1][j1], EndSquare2);
        snakes[5] = BlueSnake2;
        labelBlue2.setIcon(new ImageIcon(HardGameBoard.class.getResource("/images/SnakeBlueRight.png")));
        panel.add(labelBlue2);
    }

    public void saveGameDetails(Player winner) {
	    Gson gson = new Gson();
	    java.lang.reflect.Type gameListType = new TypeToken<ArrayList<GameDetails>>(){}.getType();
	    List<GameDetails> gameList;
	    File gameHistory = new File("src/game_history.json");
	    if (!gameHistory.exists()) {
	        try {
				gameHistory.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // This will throw IOException if the file cannot be created
	    }

	    // Load existing game details
	    try (FileReader reader = new FileReader("src/game_history.json")) {
	        gameList = gson.fromJson(reader, gameListType);
	        if (gameList == null) {
	            gameList = new ArrayList<>();
	        }
	    } catch (IOException e) {
	        gameList = new ArrayList<>();
	    }

	    // Add new game details
	    GameDetails details = new GameDetails();
	    details.winnerName = winner.getName();
	    details.difficulty = game.getDifficulty();
	    details.time = jl.getText();
	    gameList.add(details);

	    // Save updated game details
	    try (FileWriter writer = new FileWriter("src/game_history.json")) {
	        gson.toJson(gameList, writer);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
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
        Square EndSquare1 = findSquare(squares[i1][j1], Color.GREEN);
        Square EndSquare2 = findSquare(squares[i2][j2], Color.GREEN);
        Snake GreenSnake1 = new Snake(squares[i1][j1], EndSquare1);
        Snake GreenSnake2 = new Snake(squares[i2][j2], EndSquare2);
        snakes[6] = GreenSnake1;
        snakes[7] = GreenSnake2;
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
        Square startSquare=null, endSquare;
        JLabel ladderLabel;
        ArrayList<Integer> arr1= new ArrayList<Integer>();
        ArrayList<Integer> arr2= new ArrayList<Integer>();
        do {
        	arr1.clear(); // Clear the list before adding new values
            arr2.clear(); // Clear the list before adding new values
            i = generateRandomIJ(num)[0]; // Generate random row index
            j = generateRandomIJ(num)[1]; // Generate random column index
            arr2.add(i);
            arr2.add(j);
            if(j!=0) {
            startSquare = findStartSquare_ladder(squares[i][j], num);
            arr1.add(startSquare.getRow());
            arr1.add(startSquare.getCol());
            }
        } while (takenCells.containsKey(arr1) || takenCells.containsKey(arr2) || (i==9 && j==0) );
        takenCells.put(arr1,"startladder"+num);
        takenCells.put(arr2,"endladder"+num);
        //startSquare = findStartSquare_ladder(squares[i][j], num);
        endSquare = squares[i][j];
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
             num_i = random.nextInt(10); //0-9
        }
        if(color == Color.BLUE ){ 
            num_i = random.nextInt(10); //0-9
        }
        if(color == Color.YELLOW ){ 
        	num_i = random.nextInt(12);//0-11
        }
       
        return num_i; 
    }
    
    private static int generateRandomNumber_J(Color color) { //..-9
        Random random = new Random();
        int num_j = 0;
        if(color == Color.GREEN ) { 
             num_j = random.nextInt(12);  //0-11
        }
        if(color == Color.BLUE ){ 
            num_j = random.nextInt(10)+3; //3-12
        }
        if(color == Color.YELLOW ){ 
            num_j = random.nextInt(12); //0-11
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
    		j = random.nextInt(9)+1; //1-9
        	IANDJ[0] = i;
        	IANDJ[1] = j;
    	}
    	if(num ==5) {
    		i = random.nextInt(5); //0-4
    		j = random.nextInt(8)+2; //2-9
        	IANDJ[0] = i;
        	IANDJ[1] = j;
    	}
     if(num == 6) {
    		 i = random.nextInt(4);//0-3
   		  	 j = random.nextInt(8);//0-7
             IANDJ[0] = i;
             IANDJ[1] = j;
    	}
     if(num == 7) {
		 i = random.nextInt(6);//0-5
		 j = random.nextInt(10);//0-9
         IANDJ[0] = i;
         IANDJ[1] = j;
	}
     
     if(num == 8) {
		 i = random.nextInt(5);//0-4
		  	 j = random.nextInt(8)+2;//2-9
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
            	  return squares[i][j];
              }
            }
            if(color == Color.BLUE) {
            	if(squares[i][j].getBoundsX()+110 == StartSquare.getBoundsX() &&squares[i][j].getBoundsY()-165 == StartSquare.getBoundsY()) {
              	  return squares[i][j];
                }
            	
            }
            if(color == Color.GREEN) {
            	if(squares[i][j].getBoundsX() == StartSquare.getBoundsX()+55 &&squares[i][j].getBoundsY() == StartSquare.getBoundsY()+110) {
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
            	  return squares[i][j];
              }
            }
            if(number == 2) {
            	if(squares[i][j].getBoundsX() == startSquare.getBoundsX()+55 &&squares[i][j].getBoundsY() == startSquare.getBoundsY()+110) {
              	  return squares[i][j];
                }
            	
            }
            if(number == 3) {
            	if(squares[i][j].getBoundsX() == startSquare.getBoundsX() &&squares[i][j].getBoundsY()-165 == startSquare.getBoundsY()) {
              	  return squares[i][j];
                }
            	
            }
            if(number == 4) {
            	if(squares[i][j].getBoundsX() == startSquare.getBoundsX()-55 &&squares[i][j].getBoundsY()-220 == startSquare.getBoundsY()) {
                	  return squares[i][j];
                  }
            }
            if(number == 5) {
            	if(squares[i][j].getBoundsX() == startSquare.getBoundsX()-110 &&squares[i][j].getBoundsY()-275 == startSquare.getBoundsY()) {
                	  return squares[i][j];
                  }
            }
            if(number == 6) {
            	if(squares[i][j].getBoundsX() == startSquare.getBoundsX()+110 &&squares[i][j].getBoundsY()-330 == startSquare.getBoundsY()) {
                	  return squares[i][j];
                  }
            }
            if(number == 7) {
            	if(squares[i][j].getBoundsX() == startSquare.getBoundsX() &&squares[i][j].getBoundsY()-385 == startSquare.getBoundsY()) {
                	  return squares[i][j];
                  }
            }
            if(number == 8) {
            	if(squares[i][j].getBoundsX() == startSquare.getBoundsX()+110 &&squares[i][j].getBoundsY()-440 == startSquare.getBoundsY()) {
                	  return squares[i][j];
                  }
            }
            
            }
        }
    	return null;
    }
    
    private static void IntilaizePlayerPositionView(Game g , GameController control,JPanel panel) { //intilaize player position FrontEnd
    	playersLable = new JLabel[g.getPlayers().size()];
    	int[] indexes = new int[2];
		indexes = control.FindSquareByValue(1);   
		
		int spaceX = 0;          
		int spaceY = 0;          
		

    	for(int i = 0 ; i < playersLable.length ; i++) {
    	
    		if(i == 1) {
    			spaceX= 20;	
    		}

    		if(i == 0 || i == 2 ) {
    			spaceX = 0;
    		}
    		if(i == 2 || i == 3) {
    			spaceY =20;
    		}
    		if(i == 1 || i == 3) {
    			spaceX = 20;
    		}
    		
    		
            int x = g.getBoard().getCells()[indexes[0]][indexes[1]].getBoundsX()+spaceX;
            int y = g.getBoard().getCells()[indexes[0]][indexes[1]].getBoundsY()-15 + spaceY ; 
    		playersLable[i] = new JLabel();
    		playersLable[i].setBounds(x,y , 37, 35);
    		if(g.getPlayers().get(i).getColor() == Model.Color.GREEN) {
    			String path = "/images/greenPlayer.png";
                playersLable[i].setIcon(new ImageIcon(HardGameBoard.class.getResource(path)));

    			
    		}
    		if(g.getPlayers().get(i).getColor() == Model.Color.YELLOW) {
    			String path = "/images/yellowPlayer1.png";
                playersLable[i].setIcon(new ImageIcon(HardGameBoard.class.getResource(path)));

    		}
    		if(g.getPlayers().get(i).getColor() == Model.Color.RED) {
    			String path = "/images/RedPlayer1.png";
                playersLable[i].setIcon(new ImageIcon(HardGameBoard.class.getResource(path)));

    		}
    		if(g.getPlayers().get(i).getColor() == Model.Color.BLUE) {

    			String path = "/images/BluePlayer1.png";
                playersLable[i].setIcon(new ImageIcon(HardGameBoard.class.getResource(path)));
                
    		}
    	
            panel.add(playersLable[i]);
    		panel.setComponentZOrder(playersLable[i], 0);

    	}

    }
    
    private void updateTextPane(List<Player> arraylistOrderByPosition2) {
        StringBuilder sb = new StringBuilder("<html><body><ul>");
        for (Player p : arraylistOrderByPosition2) {
            sb.append("<li>").append(p.getName() + " - " + p.getPosition()).append("</li>");
        }
        sb.append("</ul></body></html>");
        textPane_1.setText(sb.toString()); // Update the JTextPane content
    }
    
    
    private void animateDiceRoll() {
        final int[] currentNumber = {1}; 
        final int numberOfFaces = 6; 
        final Timer timer = new Timer(100, null); 
        timer.addActionListener(new ActionListener() {
            int count = 0;
            int animationCycles = numberOfFaces * 2; 
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (count < animationCycles) {
                    // Update the dice icon to show the next face
                    String path = "/images/dice " + currentNumber[0] + ".jpg";
                    diceButton.setIcon(new ImageIcon(HardGameBoard.class.getResource(path)));
                    currentNumber[0] = currentNumber[0] % numberOfFaces + 1;
                    count++;
                } else {
                    // Animation ends
                    timer.stop();
                    diceButton.setEnabled(true); // Re-enable the dice button after animation
                    // Call method to handle the end of the dice roll, such as updating game state
                   onDiceAnimationEnd();
                }
            }
        });
        diceButton.setEnabled(false); // Disable the dice button during animation
        timer.start(); // Start the animation
    }
    
    private void onDiceAnimationEnd() {
        int result = dice.DiceForHardGame(); // Simulate the dice roll result
        // Update the dice icon to show the final result
        String path = "/images/dice " + result + ".jpg";
        diceButton.setIcon(new ImageIcon(HardGameBoard.class.getResource(path)));
        boolean flag = false ; 

        
        if(result < 7) {
        	  flag=hardBoard.endGame(index, result, "Dice",playersLable,WinValue,null,controller);                                                                 
        } else {
            controller.DiceQuestion(index ,result,playersLable,WinValue);
           
        }
        if(flag == true) {
       	saveGameDetails(game.getPlayers().get(index));
       	HardGameBoard.this.setVisible(false); 
       	Player winner = game.getPlayers().get(index);
  		((HardBoard) hardBoard).openFrameForWinner(winner,jl.getText(),game);
       }else {
    	   
    	     index++;
             if(index >= game.getPlayers().size()) {
                 index = 0;
             }
         
             game.setCurrentPlayerIndex(index);
             game.setCurrentPlayer(game.getPlayers().get(index));
             textPane.setText("\n Turn: " + game.getCurrentPlayer().getName());
             controller.setPlayerBackgroundColor(game.getCurrentPlayer().getColor(), textPane);

       }
  
       
    }
    
    public void startNewTurn() {// Start the 30-second countdown for the player's turn
        turnTimer.start(); 
        diceButton.setEnabled(true); 
    }
    
    private void endTurn() {
        startNewTurn(); 
    }
    
//    public void pauseGame() {
//        if (!isGamePaused) {
//            gameTimer.stop();
//            turnTimer.stop();
//            
//            // Calculate the remaining time for the player turn timer
//            long now = System.currentTimeMillis();
//            remainingPlayerTime -= (now - turnTimer);
//            
//            isGamePaused = true;
//        }
//    }
//    
//    public void resumeGame() {
//        if (isGamePaused) {
//            gameTimer.start();
//            
//            // Adjust the player turn timer to fire after the remaining time
//            turnTimer.setInitialDelay((int) remainingPlayerTime);
//            turnTimer.start();
//            turnTimer = System.currentTimeMillis(); // Reset start time
//            
//            isGamePaused = false;
//        }
//    }


}
