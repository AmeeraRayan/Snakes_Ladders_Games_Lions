package View;


import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.Color;
import javax.swing.border.LineBorder;
import Model.Game;
import Model.Ladder;
import Model.Player;
import Model.Questions;
import Model.Snake;
import Model.Square;
import Model.SysData;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.util.concurrent.TimeUnit;
import java.awt.Dimension;


public class BoardEasyViewPlayers extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Object BLUE = null;
	private static final Object YELLOW = null;
	private static final Object GREEN = null;
	private static final Object RED = null;
	private JPanel contentPane;
	private Game game;
	private Player currentPlayer;
	private int currentPlayerIndex;
    private int rollResult;
	private JButton diceButton;
	private JLabel currentPlayerLabel;
	private JTextPane txtpnHi;
	private JLabel timerLabel;
	private long startTime;
	private Timer gameTimer;
	private Questions quesTemp;
    private final int totalSquaresOnBoard = 7 * 7; 
    private  int selectedAnswer = -1;
    private JLabel greenPlayerLabel;
    private JLabel redPlayerLabel;
    private JLabel yellowPlayerLabel;
    private JLabel bluePlayerLabel;
    private SysData sysdata;
    private JLabel lblNewLabel;


	public static HashMap<String,Questions> questionsPOPUP= new HashMap<String, Questions>();

	public BoardEasyViewPlayers(Game game ) {
		this.currentPlayer=game.getCurrentPlayer();
		this.game=game;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 810);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		currentPlayerLabel = new JLabel("");
		currentPlayerLabel.setBounds(420, 80, 450, 50);
		currentPlayerLabel.setForeground(new java.awt.Color(0, 0, 0));
		currentPlayerLabel.setFont(new Font("Rage Italic", Font.BOLD, 35));
		contentPane.add(currentPlayerLabel);
		setContentPane(contentPane);
	     txtpnHi = new JTextPane();
	     txtpnHi.setEditable(false);
	      txtpnHi.setBounds(10, 10, 325, 145);
	      txtpnHi.setFont(new Font("Palatino Linotype", Font.BOLD, 24));
	      txtpnHi.setForeground(java.awt.Color.BLUE);
	      txtpnHi.setBackground(UIManager.getColor("Tree.selectionBackground"));
	      contentPane.add(txtpnHi);
	      
		 diceButton = new JButton("");
		 diceButton.setBounds(920, 360, 150, 145);
        diceButton.setIcon(new ImageIcon(PlayerTurn.class.getResource("/images/dice 4.jpg")));
		contentPane.add(diceButton);
		
		
		timerLabel = new JLabel("00:00");
		timerLabel.setBounds(920, 50, 100, 50);
		timerLabel.setFont(new Font("Snap ITC", Font.BOLD, 25));
		contentPane.add(timerLabel);


		 bluePlayerLabel = new JLabel(new ImageIcon(getClass().getResource("/images/blueplayer.jpg")));
		bluePlayerLabel.setSize(30, 30);

		// Create JLabel for the green player
		 greenPlayerLabel = new JLabel(new ImageIcon(getClass().getResource("/images/greenplayer.png")));
		greenPlayerLabel.setSize(30, 30);
		

		// Create JLabel for the red player
		 redPlayerLabel = new JLabel(new ImageIcon(getClass().getResource("/images/redplayer.png")));
		redPlayerLabel.setSize(30, 30);

		// Create JLabel for the yellow player
		 yellowPlayerLabel = new JLabel(new ImageIcon(getClass().getResource("/images/yellowplayer.png")));
		yellowPlayerLabel.setSize(30, 30);

	  
		startGame();
		
		
	}
	public void startGame() {
	    initializePlayerPositions();
	    lblNewLabel = new JLabel("");
	  		lblNewLabel.setBounds(0, 10, 1095, 772);
	  		lblNewLabel.setIcon(new ImageIcon(BoardEasyViewPlayers.class.getResource("/images/boradeasy2.png")));
	  		contentPane.add(lblNewLabel);

	    rollDiceAndMovePlayer();
	    animatePlayerTurnTitle(); 
	    startGameTimer(); 

	}
	private void startGameTimer() {
	    startTime = System.currentTimeMillis();
	    gameTimer = new Timer(1000, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            long now = System.currentTimeMillis();
	            long elapsed = now - startTime;
	            long minutes = TimeUnit.MILLISECONDS.toMinutes(elapsed);
	            long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsed) % 60;
	            timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
	        }
	    });
	    gameTimer.start();
	}

	
	private void animatePlayerTurnTitle() {
	    final int delay = 500;
	    ActionListener taskPerformer = new ActionListener() {
	        private boolean flag = false;
	        public void actionPerformed(ActionEvent evt) {
	            if (currentPlayer != null) {
	                if (flag) {
	                    currentPlayerLabel.setText("Player Turn: " + currentPlayer.getName());
	                } else {
	                    currentPlayerLabel.setText(">> Player Turn: " + currentPlayer.getName() + " <<");
	                }
	                flag = !flag; 
	            }
	        }
	    };
	    new Timer(delay, taskPerformer).start();
	}
	
	   

	private void advanceToNextPlayer() {
	    currentPlayerIndex = (currentPlayerIndex + 1) % game.getPlayers().size();
	    currentPlayer = game.getPlayers().get(currentPlayerIndex);
	    game.setCurrentPlayer(currentPlayer);
	    game.setCurrentPlayerIndex(currentPlayerIndex);
	    displayCurrentPlayer(); 
	    enableDiceRollForCurrentPlayer(); 
	}


	private void enableDiceRollForCurrentPlayer() {
	    for (ActionListener al : diceButton.getActionListeners()) {
	        diceButton.removeActionListener(al);
	    }

	    diceButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent arg0) {
	            diceButton.setEnabled(false);
	            performDiceRollAndMove();
	        }
	    });

	    diceButton.setEnabled(true);
	}
	public void updatePlayerPosition(Player player, int x, int y) {
	    
	   System.out.println(player.getName() + " moves to X: " + x + " Y: " + y );
	    contentPane.revalidate();
	    contentPane.repaint();
	    
	    }
	
	public Point boardPositionToPixel(int boardPosition) {
	    // Define the difference in pixels between squares horizontally and vertically
	    int xDiff = 80; // the horizontal distance between squares
	    int yDiff = 70; // vertical distance between squares

	    int row = (boardPosition - 1) / 7;
	    int col = (boardPosition - 1) % 7;

	    int x = 300;
	    int y = 645;
	    if (boardPosition==1)
	    {
		    return new Point(300,645);
	    }
	    
	    x += col * xDiff;
	   
	    y -= row * yDiff;

	    return new Point(x, y);
	}

	private void updateBoardView() {
	    currentPlayer = game.getCurrentPlayer();

	    int boardSize = game.getBoard().getSize();
	    int position = currentPlayer.getPosition() - 1;
	    int y = position % boardSize;
	    int x = position / boardSize;

	    if (x % 2 == 1) {
	        y = boardSize - 1 - y;
	    }

	    x= (boardSize - 1) - x;

	    this.updatePlayerPosition(currentPlayer, x, y);
	    
	}
	private void showLadderPopup(Player player,int lastpos) {
	    JOptionPane.showMessageDialog(this,
		    "<html><body><p> U have been move to "+lastpos+" but the Ladder will move u to "+player.getLastPosition() +"🎉</p><img src='" + getClass().getResource("/images/giphy.gif") + "' width='100' height='100'></body></html>",
	        "Congratss!! "+player.getName()+" got a ladder!!!", JOptionPane.INFORMATION_MESSAGE);
	}

	private void showSnakePopup(Player player,int lastpos) {
	    JOptionPane.showMessageDialog(this,
	        "<html><body><p> U have been move to "+lastpos+" but the snake will move u to "+player.getLastPosition()+" 😭</p><img src='" + getClass().getResource("/images/fall.gif") + "' width='100' height='100'></body></html>",
	        "Oh no! "+player.getName()+" got a snake ", JOptionPane.WARNING_MESSAGE);

	}

	private void performDiceRollAndMove() {
	    diceButton.setEnabled(false);
	    ImageIcon diceIcon;
	    rollResult = game.getDice().rollForEasy();

	    if(rollResult==5)
	    {
	      	 System.out.println("Im a question   ");

		     diceIcon = new ImageIcon(getClass().getResource("/images/question.png"));
			    diceButton.setIcon(diceIcon);
		     SysData sysdata=new SysData();
		     sysdata.LoadQuestions();
			questionsPOPUP=SysData.getQuestionsPOPUP();
 	        SysData.putQuestions(questionsPOPUP);
 	       String[] difficulty_levels ={"easy", "medium", "hard"};
  	      String temp=SysData.getRandomQuestion(difficulty_levels);
 	        quesTemp= SysData.getQuestionForPosition(temp);
 		    JOptionPane.showMessageDialog(this, currentPlayer.getName() + " rolled a question!");
 	        showEditQuestionDialog(this.currentPlayer);
 	       movePlayer(currentPlayer);
 		    updateBoardView();
 		    displayPlayerPositions(); 

 	             
	    }
	    else {
	      	 System.out.println("rollResult  " + rollResult );

		     diceIcon = new ImageIcon(getClass().getResource("/images/dice " + rollResult + ".jpg"));

	    
	    diceButton.setIcon(diceIcon);
	    
	    JOptionPane.showMessageDialog(this, currentPlayer.getName() + " rolled a " + rollResult, "Dice Roll", JOptionPane.INFORMATION_MESSAGE);
	    
	    movePlayer(currentPlayer, rollResult);
	    checkForSnakesAndLadders(currentPlayer);
	    updateBoardView();
	    displayPlayerPositions(); // Update the display of player positions
	    }
	    if (hasPlayerWon(currentPlayer)) {
	        endGame(currentPlayer);
	    } else {
	        advanceToNextPlayer();
	    }
	}
	private void displayPlayerPositions() {
	    StringBuilder positionsText = new StringBuilder();
	    for (Player player : game.getPlayers()) {
	        positionsText.append(" " + player.getName()).append(" on sqaure: ").append(player.getPosition()).append("\n");
	    }
	    txtpnHi.setText(positionsText.toString());
	    contentPane.revalidate();
	    contentPane.repaint();
	}

	private boolean hasPlayerWon(Player player) {
	    //int maxPosition = game.getBoard().getSize() * game.getBoard().getSize();
	    return game.getCurrentPlayer().getPosition() == 49;
	}

	private void rollDiceAndMovePlayer() {
	    currentPlayer = game.getCurrentPlayer();
	    displayCurrentPlayer();
	    enableDiceRollForCurrentPlayer();
	}
	



	private void endGame(Player winner) {
	    gameTimer.stop(); // Stop the timer
	    switch (winner.getColor()) {
        case RED:
	         BoardEasyViewPlayers.this.setVisible(false);	
        	 new RedWin(winner.getName(),timerLabel.getText(),game).setVisible(true);
	         break;
        case GREEN:
	         BoardEasyViewPlayers.this.setVisible(false);
        	new GreenWin(winner.getName(),timerLabel.getText(),game).setVisible(true);
	         break;
        case BLUE:
	         BoardEasyViewPlayers.this.setVisible(false);
        	new BlueWin(winner.getName(),timerLabel.getText(),game).setVisible(true);
	         break;
        case YELLOW:
	         BoardEasyViewPlayers.this.setVisible(false);
        	new YellowWin(winner.getName(),timerLabel.getText(),game).setVisible(true);
	         break;
        
    }

	}
	
	private void movePlayer(Player player, int roll) {
	    int oldPosition = currentPlayer.getLastPosition();
	    int newPosition = oldPosition + roll;

	    // Ensure the player does not go past the last square
	    if (newPosition > totalSquaresOnBoard) {
	        newPosition = totalSquaresOnBoard;
	    }

	    checkForSnakesAndLadders(player);


	    // Update the player's position in the game logic
	    player.setPosition(newPosition);
	    game.getCurrentPlayer().setPosition(newPosition);
	    currentPlayer.setPosition(newPosition);

	    Point startPoint = boardPositionToPixel(oldPosition);
	    Point endPoint = boardPositionToPixel(newPosition); 

	    JLabel playerLabel = getPlayerLabel(currentPlayer);
        System.out.println("the player now on "+currentPlayer.getPosition());
	    // Animate the movement to the final position
	    if (playerLabel != null) {
		    animateMovement(playerLabel, startPoint, endPoint, currentPlayer);
	    }
	    // Check if the game is over
	    if (newPosition == totalSquaresOnBoard) {
	        endGame(currentPlayer);
	    }
	    currentPlayer.setLastPosition(newPosition);

	}

	//for a question on dice 
	private void movePlayer(Player player) {
	    int newPosition = currentPlayer.getPosition();

	    // Ensure the player does not go past the last square
	    if (newPosition > totalSquaresOnBoard) {
	        newPosition = totalSquaresOnBoard;
	    }
	    if (newPosition <= 0) {
	        newPosition = 1;
	    }

	    // Update the player's position in the game logic
	    player.setPosition(newPosition);
	    game.getCurrentPlayer().setPosition(newPosition);
	    currentPlayer.setPosition(newPosition);

	  
	    Point startPoint = boardPositionToPixel(currentPlayer.getLastPosition());
	    Point endPoint = boardPositionToPixel(newPosition);

	    JLabel playerLabel = getPlayerLabel(currentPlayer);

	    if (playerLabel != null) {
		    animateMovement(playerLabel, startPoint, endPoint, currentPlayer);
		   // checkForSnakesAndLadders(player);

	    }
	    // Check if the game is over
	    if (newPosition == totalSquaresOnBoard) {
	        endGame(player);
	    }
	    currentPlayer.setLastPosition(newPosition);
	    
	}
	private void animateMovement(JLabel playerLabel, Point start, Point end, Player player) {
	    // Define the target end point based on specific positions
		 System.out.println("222"+currentPlayer.getLastPosition());
 	    System.out.println("222"+currentPlayer.getPosition());

	    Point targetEndPoint = getCustomEndPoint(currentPlayer.getPosition(), end);

	    final int numberOfSteps = 10;
	    final Timer timer = new Timer(100, null);
	    timer.addActionListener(new ActionListener() {
	        int currentStep = 0;

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            double xIncrement = (targetEndPoint.x - start.x) / (double) numberOfSteps;
	            double yIncrement = (targetEndPoint.y - start.y) / (double) numberOfSteps;

	            int newX = (int) (start.x + (xIncrement * currentStep));
	            int newY = (int) (start.y + (yIncrement * currentStep));

	            playerLabel.setLocation(newX, newY);
	            contentPane.revalidate();
	            contentPane.repaint();

	            currentStep++;
	            if (currentStep > numberOfSteps) {
	                timer.stop();
	            }
	        }
	    });
	    timer.start();
	}

	private Point getCustomEndPoint(int position, Point defaultEndPoint) {
	    switch (position) {
	        case 2: return new Point(380, 420);
	        case 36: return new Point(380, 210);
	        case 45: return new Point(300, 645);
	        case 30: return new Point(460, 420);
	        case 21: return new Point(680, 645);
	        case 26: return new Point(540, 280);
	        case 13: return new Point(770, 280);
	        case 47: return new Point(680, 430);
	        default: return defaultEndPoint; // Use the calculated end point if no special case
	    }
	}


	private JLabel getPlayerLabel(Player player) {
	    switch (player.getColor()) {
	        case BLUE:
	            return bluePlayerLabel;
	        case GREEN:
	            return greenPlayerLabel;
	        case RED:
	            return redPlayerLabel;
	        case YELLOW:
	            return yellowPlayerLabel;
	        default:
	            return null; // Or handle error
	    }
	}
	
	

	    private void checkForSnakesAndLadders(Player player) {
        	int lastpos=player.getPosition();
	        for (Snake snake : game.getBoard().getSnakes()) {
	            if (player.getPosition() == Integer.parseInt(snake.getSquareStart().getValue())) {
	                player.setPosition(Integer.parseInt(snake.getSquareEnd().getValue()));
	                currentPlayer.setPosition(Integer.parseInt(snake.getSquareEnd().getValue()));
		            game.getCurrentPlayer().setPosition(Integer.parseInt(snake.getSquareEnd().getValue()));
	                showSnakePopup(player,lastpos); 
	                break;
	            }
	        }

	        for (Ladder ladder : game.getBoard().getLadders()) {
	            if (player.getPosition() == Integer.parseInt(ladder.getSquareStart().getValue())) {
	                player.setPosition(Integer.parseInt(ladder.getSquareEnd().getValue()));
		            game.getCurrentPlayer().setPosition(Integer.parseInt(ladder.getSquareEnd().getValue()));
	                currentPlayer.setPosition(Integer.parseInt(ladder.getSquareEnd().getValue()));
	                showLadderPopup(player,lastpos); 
	                break;
	            }
	        }

	        for (Square q : game.getBoard().getQuestions()) {
	            if (player.getPosition() == Integer.parseInt(q.getValue())) {
	            	System.out.println("square question here");///question
	            	SysData sysdata=new SysData();
	    	        sysdata.LoadQuestions();
					questionsPOPUP=SysData.getQuestionsPOPUP();
	    	        SysData.putQuestions(questionsPOPUP);
	    	        quesTemp= SysData.getQuestionForPosition(player.getPosition());
	    	        System.out.println(quesTemp);
	    	        showEditQuestionDialog(player);
	                break;

	            }
	        
	            
	            }
	        
	    }
	    private void displayCurrentPlayer() {
	        if (currentPlayer != null) {
	            currentPlayerLabel.setText("Player Turn: " + currentPlayer.getName());
	            setTitle("Current Player: " + currentPlayer.getName() + "'s Turn");
	        }
	    }

	    private void initializePlayerPositions() {
	        StringBuilder positionsText = new StringBuilder();
	        for (Player player : game.getPlayers()) {
	            player.setPosition(1);
	            positionsText.append(player.getName()).append(" on square: ").append(player.getPosition()).append("\n");
	        }
	        txtpnHi.setText(positionsText.toString());
	        Point bluePlayerStartPos = boardPositionToPixel(1); 
	        Point greenPlayerStartPos = boardPositionToPixel(1); 
	        Point redPlayerStartPos = boardPositionToPixel(1); 
	        Point yellowPlayerStartPos = boardPositionToPixel(1); 
	        if (game.getPlayers().size()==2)
	        {
	        	bluePlayerLabel.setLocation(bluePlayerStartPos.x, bluePlayerStartPos.y);
		        greenPlayerLabel.setLocation(greenPlayerStartPos.x, greenPlayerStartPos.y);
				contentPane.add(greenPlayerLabel);
				contentPane.add(bluePlayerLabel);

	        }
	        else if(game.getPlayers().size()==3)
	        {
	        	redPlayerLabel.setLocation(redPlayerStartPos.x, redPlayerStartPos.y);
	        	bluePlayerLabel.setLocation(bluePlayerStartPos.x, bluePlayerStartPos.y);
		        greenPlayerLabel.setLocation(greenPlayerStartPos.x, greenPlayerStartPos.y);
		        contentPane.add(greenPlayerLabel);
				contentPane.add(bluePlayerLabel);
				contentPane.add(redPlayerLabel);
	        }
	        else {
		        yellowPlayerLabel.setLocation(yellowPlayerStartPos.x, yellowPlayerStartPos.y);
		        redPlayerLabel.setLocation(redPlayerStartPos.x, redPlayerStartPos.y);
	        	bluePlayerLabel.setLocation(bluePlayerStartPos.x, bluePlayerStartPos.y);
		        greenPlayerLabel.setLocation(greenPlayerStartPos.x, greenPlayerStartPos.y);
		        contentPane.add(greenPlayerLabel);
				contentPane.add(bluePlayerLabel);
				contentPane.add(yellowPlayerLabel);
				contentPane.add(redPlayerLabel);

	        }

	        contentPane.revalidate();
	        contentPane.repaint();
	    }
	    
		private void showEditQuestionDialog( Player player) {
	    	 JPanel questionPanel = new JPanel();
	    	    questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.PAGE_AXIS));
	    	    Dimension preferredSize = new Dimension(700, 400);
	    	    questionPanel.setPreferredSize(preferredSize);
	    	  // Create and add the position label to the panel
	    	   	 System.out.println("the correct answer  " + this.quesTemp.getCorrectOption() );

	    	  String message;
	    	    switch (this.quesTemp.getDiffculty()) {
	    	        case 1: // Easy
	    	            message = "This is an easy question. A wrong answer will set you back one square, and a correct answer will keep you on place.";
	    	            break;
	    	        case 2: // Medium
	    	            message = "You're facing a medium difficulty question. Incorrectly answering will set you back two squares , and a correct answer will stay on place";
	    	            break;
	    	        case 3: // Hard
	    	            message = "This is a hard question. A wrong answer will set you back three squares, but a correct answer will move you forward one square ";
	    	            break;
	    	        default:
	    	            message = "Difficulty level is unknown. Be cautious with your answer.";
	    	            break;
	    	    }
	    	    
	    	    JLabel infoLabel = new JLabel("<html><div style='background-color: yellow; padding: 10px; font-size: 20px; font-family: Serif; font-style: italic;'>😃 " + message + " 😃</div></html>");
	    	    infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    	    questionPanel.add(infoLabel);


	    	    questionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

	    	    JLabel questionLabel = new JLabel("<html><div style='padding: 10px;font-size: 20px;font-family: Serif; font-style: italic;'>" + this.quesTemp.getQuestionText() + "</div></html>");
	    	    questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    	    questionPanel.add(questionLabel);
	    	    questionPanel.add(Box.createRigidArea(new Dimension(0, 10)));


	    	  ButtonGroup optionsGroup = new ButtonGroup();
	    	  JRadioButton option1 = new JRadioButton(this.quesTemp.getOptions()[0]);
	    	  option1.setForeground(Color.RED); // Color the text
	    	  option1.setFont(new Font("Comic Sans MS", Font.BOLD, 20)); // Set the font

	    	  JRadioButton option2 = new JRadioButton(this.quesTemp.getOptions()[1]);
	    	  option2.setForeground(Color.ORANGE);
	    	  option2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

	    	  JRadioButton option3 = new JRadioButton(this.quesTemp.getOptions()[2]);
	    	  option3.setForeground(Color.BLUE);
	    	  option3.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

	    	  JRadioButton option4 = new JRadioButton(this.quesTemp.getOptions()[3]);
	    	  option4.setForeground(Color.GREEN);
	    	  option4.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
	    	  optionsGroup.add(option1);
	    	  optionsGroup.add(option2);
	    	  optionsGroup.add(option3);
	    	  optionsGroup.add(option4);
	    	  questionPanel.add(option1);
	    	  questionPanel.add(option2);
	    	  questionPanel.add(option3);
	    	  questionPanel.add(option4);


	    	  int result = JOptionPane.showOptionDialog(null, questionPanel, 
	    	      "Hii " + player.getName()+" you are on "+ player.getPosition(), 
	    	      JOptionPane.OK_CANCEL_OPTION, 
	    	      JOptionPane.PLAIN_MESSAGE, 
	    	      null, null, null);
               
	           if (result == JOptionPane.OK_OPTION) {
	               if (option1.isSelected()) selectedAnswer = 1;
	               if (option2.isSelected()) selectedAnswer = 2;
	               if (option3.isSelected()) selectedAnswer = 3;
	               if (option4.isSelected()) selectedAnswer = 4;

	               handleAnswer(selectedAnswer,player);
	           }
	       }



	    private void handleAnswer(int selectedAnswer , Player player) {
	    	boolean isCorrectAnswer=true;
	       
	        
	        if(selectedAnswer == this.quesTemp.getCorrectOption()) {
	            JOptionPane.showMessageDialog(this, "Congratsss! It's a correct Answer!");
	            if(this.quesTemp.getDiffculty()==1)
	            {

	                movePlayerBasedOnQuestion(1 ,isCorrectAnswer,player);

	            }
	            if(this.quesTemp.getDiffculty()==2)
	            {

	                movePlayerBasedOnQuestion(2 ,isCorrectAnswer,player);

	            }
	            if(this.quesTemp.getDiffculty()==3)
	            {

	                movePlayerBasedOnQuestion(3 ,isCorrectAnswer,player);

	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "Opss! It's an wrong Answer!");
	            isCorrectAnswer=false;
	            if(this.quesTemp.getDiffculty()==1)
	            {
	                movePlayerBasedOnQuestion(1 ,isCorrectAnswer,player);

	            }
	            if(this.quesTemp.getDiffculty()==2)
	            {

	                movePlayerBasedOnQuestion(2 ,isCorrectAnswer,player);

	            }
	            if(this.quesTemp.getDiffculty()==3)
	            {

	                movePlayerBasedOnQuestion(3 ,isCorrectAnswer,player);

	            }
	        }
	        
	        
	    }

	    public void movePlayerBasedOnQuestion(int questionDifficulty, boolean isCorrectAnswer,Player player) {
	    	 System.out.println("questionDifficulty  " + questionDifficulty +" isCorrectAnswer"+ isCorrectAnswer);
	        // Define the movement rules based on difficulty and correctness
	    	
	        int steps=0;
	        if (isCorrectAnswer) {
	            if (questionDifficulty == 3) { // Hard question
	                steps = 1; // Advance one step
	            } else {
	                steps = 0; // Stay in place for easy and medium questions
	            }
	        } else {
	            // For wrong answers, move back 1, 2, or 3 steps based on difficulty
	            steps = -questionDifficulty;
	        }

	        // Apply the movement to the player's position
	        updatePlayerPosition(steps,player);
	    }

	    private void updatePlayerPosition(int steps,Player player) {
	        int currentPosition = player.getLastPosition();
	        System.out.println( "last"+player.getLastPosition());
	        int newPosition = currentPosition + steps;
	        System.out.println("new"+newPosition);
	        if (newPosition < 0) {
	            newPosition = 1; // Prevent moving beyond the start
	          	 System.out.println("Prevent moving beyond the start ");

	        } else if (newPosition > totalSquaresOnBoard) {
	            newPosition = totalSquaresOnBoard; // Prevent moving beyond the end
	          	 System.out.println("Prevent moving beyond the end  " );

	        }

	        player.setPosition(newPosition);
            game.getCurrentPlayer().setPosition(newPosition);
    	    currentPlayer.setPosition(newPosition);
    	    

	    }
	   }

	    
	    
	    
	  