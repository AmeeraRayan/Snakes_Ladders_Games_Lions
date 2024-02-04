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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Timer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.util.concurrent.TimeUnit;

public class BoardEasyView2Players extends JFrame {

	private static final long serialVersionUID = 1L;
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
    private final int totalSquaresOnBoard = 7 * 7; // for a 7x7 board, this would be 49
    private  int selectedAnswer = -1;
	public static HashMap<String,Questions> questionsPOPUP= new HashMap<String, Questions>();

	public BoardEasyView2Players(Game game ) {
		this.currentPlayer=game.getCurrentPlayer();
		this.game=game;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		currentPlayerLabel = new JLabel("");
		currentPlayerLabel.setForeground(new java.awt.Color(0, 0, 0));
		currentPlayerLabel.setFont(new Font("Rage Italic", Font.BOLD, 35));
		currentPlayerLabel.setBounds(20, 20, 450, 50); 
		contentPane.add(currentPlayerLabel);
		setContentPane(contentPane);
		contentPane.setLayout(null);
	       
	         txtpnHi = new JTextPane();
	        txtpnHi.setFont(new Font("Palatino Linotype", Font.BOLD, 24));
	        txtpnHi.setForeground(java.awt.Color.BLUE);
	        txtpnHi.setBackground(UIManager.getColor("Tree.selectionBackground"));
	        
	        txtpnHi.setBounds(10, 67, 519, 70);
	        contentPane.add(txtpnHi);
	      
		 diceButton = new JButton("");
        diceButton.setIcon(new ImageIcon(PlayerTurn.class.getResource("/images/dice 4.jpg")));
		diceButton.setBounds(850, 330, 150, 145);
		contentPane.add(diceButton);
		
		
		timerLabel = new JLabel("00:00");
		timerLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		timerLabel.setBounds(730, 20, 100, 50); 
		contentPane.add(timerLabel);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BoardEasyView2Players.class.getResource("/images/boradeasy2.png")));
		lblNewLabel.setBounds(0, 10, 1000, 700);

		contentPane.add(lblNewLabel);
		startGame();
		
		
	}
	public void startGame() {
	    initializePlayerPositions();
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
	   /* for(Player p: game.getPlayers())
	    {
	    	if (!game.getCurrentPlayer().getName().equals(p.getName()))
	    			{
	    	   game.setCurrentPlayer(p);
	    	   curentplayer=had leshu
	    			}
	    }*/
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
	private void showLadderPopup(Player player) {
	    JOptionPane.showMessageDialog(this,
	        "<html><body><p>Heyyy! " + player.getName() + " climbed a ladder 🎉</p><img src='" + getClass().getResource("/images/giphy.gif") + "' width='100' height='100'></body></html>",
	        "Ladder Encounter!", JOptionPane.INFORMATION_MESSAGE);
	}

	private void showSnakePopup(Player player) {
	    JOptionPane.showMessageDialog(this,
	        "<html><body><p>Oh no! " + player.getName() + " encountered a snake! 😭</p><img src='" + getClass().getResource("/images/fall.gif") + "' width='100' height='100'></body></html>",
	        "Snake Encounter!", JOptionPane.WARNING_MESSAGE);
	}

	private void performDiceRollAndMove() {
	    diceButton.setEnabled(false);
	    rollResult = game.getDice().rollForEasy();
	    ImageIcon diceIcon = new ImageIcon(getClass().getResource("/images/dice " + rollResult + ".jpg"));
	    diceButton.setIcon(diceIcon);
	    
	    JOptionPane.showMessageDialog(this, currentPlayer.getName() + " rolled a " + rollResult, "Dice Roll", JOptionPane.INFORMATION_MESSAGE);
	    
	    movePlayer(currentPlayer, rollResult);
	    checkForSnakesAndLadders(currentPlayer);
	    updateBoardView();
	    displayPlayerPositions(); // Update the display of player positions

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
	    return currentPlayer.getPosition() == 49;
	}

	private void rollDiceAndMovePlayer() {
	    currentPlayer = game.getCurrentPlayer();
	    displayCurrentPlayer();
	    enableDiceRollForCurrentPlayer();
	}

	private void endGame(Player winner) {
	    gameTimer.stop(); // Stop the timer
	    JOptionPane.showMessageDialog(this, winner.getName() + " wins the game! Time: " + timerLabel.getText(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
	    int playAgain = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);
	    if (playAgain == JOptionPane.YES_OPTION) {
	        restartGame();
	    } else {
	        BoardEasyView2Players.this.setVisible(false);
			new DataReception().setVisible(true);
	    }
	}
	private void restartGame() {
	    if (gameTimer != null) {
	        gameTimer.stop();
	    }

	    for (Player player : game.getPlayers()) {
	        player.setPosition(1);
	    }
	    	    
	    initializePlayerPositions();
	    startGameTimer();
	    rollDiceAndMovePlayer();
	    animatePlayerTurnTitle();
	}
	    private void movePlayer(Player player, int roll) {
	        int newPosition = player.getPosition() + roll;
	     // Ensure the player does not go past the last square
	        if (newPosition >= totalSquaresOnBoard) {
	            newPosition = totalSquaresOnBoard;
	            player.setPosition(newPosition);
	            endGame(player); // Call the end game method
	        } else {
	            player.setPosition(newPosition);
	        }
	        System.out.println("player=" + player.getName() + " " + player.getPosition());
	    }
	    private void checkForSnakesAndLadders(Player player) {
	        for (Snake snake : game.getBoard().getSnakes()) {
	            if (player.getPosition() == Integer.parseInt(snake.getSquareStart().getValue())) {
	                player.setPosition(Integer.parseInt(snake.getSquareEnd().getValue()));
	                showSnakePopup(player); 
	                break;
	            }
	        }

	        for (Ladder ladder : game.getBoard().getLadders()) {
	            if (player.getPosition() == Integer.parseInt(ladder.getSquareStart().getValue())) {
	                player.setPosition(Integer.parseInt(ladder.getSquareEnd().getValue()));
	                showLadderPopup(player); 
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
	    	        System.out.println(player.getPosition()+"aaaaaaaaaaaaaaaaaaaaa");
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
	        contentPane.revalidate();
	        contentPane.repaint();
	    }
	    
	    private void showEditQuestionDialog( Player player) {
	    	  ButtonGroup optionsGroup;
	    	  JRadioButton option1, option2, option3, option4;
	    	  JLabel questionLabel;
	    	     // Radio buttons for options
	           optionsGroup = new ButtonGroup();
	           option1 = new JRadioButton(this.quesTemp.getOptions()[0]);
	           option2 = new JRadioButton(this.quesTemp.getOptions()[1]);
	           option3 = new JRadioButton(this.quesTemp.getOptions()[2]);
	           option4 = new JRadioButton(this.quesTemp.getOptions()[3]);
	           optionsGroup.add(option1);
	           optionsGroup.add(option2);
	           optionsGroup.add(option3);
	           optionsGroup.add(option4);
	    	   // Question label
	    	    JPanel questionPanel = new JPanel();
	           questionLabel = new JLabel(this.quesTemp.getQuestionText());
	           questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	           questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.PAGE_AXIS));
	           questionPanel.add(new JLabel(quesTemp.getQuestionText()));
	           questionPanel.add(option1);
	           questionPanel.add(option2);
	           questionPanel.add(option3);
	           questionPanel.add(option4);
	           // Show the dialog
	           int result = JOptionPane.showConfirmDialog(null, questionPanel, 
	               "Select Your Answer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

	           if (result == JOptionPane.OK_OPTION) {
	               if (option1.isSelected()) selectedAnswer = 0;
	               if (option2.isSelected()) selectedAnswer = 1;
	               if (option3.isSelected()) selectedAnswer = 2;
	               if (option4.isSelected()) selectedAnswer = 3;

	               // Call your method to handle the answer and update player position
	               handleAnswer(selectedAnswer,player);
	           }
	       }



	    private void handleAnswer(int selectedAnswer , Player player) {
	    	boolean isCorrectAnswer=true;
	       
	        
	        if(selectedAnswer == this.quesTemp.getCorrectOption()) {
	            JOptionPane.showMessageDialog(this, "Correct Answer!");
	            if(this.quesTemp.getDiffculty()==1)
	            {
	            	System.out.println("sahkshaksk");

	                movePlayerBasedOnQuestion(1 ,isCorrectAnswer,player);

	            }
	            if(this.quesTemp.getDiffculty()==2)
	            {
	            	System.out.println("sahkshaksk");

	                movePlayerBasedOnQuestion(2 ,isCorrectAnswer,player);

	            }
	            if(this.quesTemp.getDiffculty()==3)
	            {
	            	System.out.println("sahkshaksk");

	                movePlayerBasedOnQuestion(3 ,isCorrectAnswer,player);

	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "Wrong Answer!");
	            isCorrectAnswer=false;
	            if(this.quesTemp.getDiffculty()==1)
	            {
	            	System.out.println("sahkshaksk");
	                movePlayerBasedOnQuestion(1 ,isCorrectAnswer,player);

	            }
	            if(this.quesTemp.getDiffculty()==2)
	            {
	            	System.out.println("sahkshaksk");

	                movePlayerBasedOnQuestion(2 ,isCorrectAnswer,player);

	            }
	            if(this.quesTemp.getDiffculty()==3)
	            {
	            	System.out.println("sahkshaksk");

	                movePlayerBasedOnQuestion(3 ,isCorrectAnswer,player);

	            }
	        }
	        
	        
	    }

	    /**
	     * Moves the player based on the result of the question.
	     */
	    public void movePlayerBasedOnQuestion(int questionDifficulty, boolean isCorrectAnswer,Player player) {
	    	 System.out.println("heeeeeeeeeeeello  " + questionDifficulty +" "+ isCorrectAnswer);
	        // Define the movement rules based on difficulty and correctness
	    	
	        int steps;
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

	    /**
	     * Update the player's position on the board.
	     */
	    private void updatePlayerPosition(int steps,Player player) {
	        // Assume we have a currentPlayer object with a method setPosition
	        int currentPosition = player.getPosition();
	        int newPosition = currentPosition + steps;

	        // Ensure the new position is within bounds
	        if (newPosition < 0) {
	            newPosition = 0; // Prevent moving beyond the start
	          	 System.out.println("Prevent moving beyond the start ");

	        } else if (newPosition > totalSquaresOnBoard) {
	            newPosition = totalSquaresOnBoard; // Prevent moving beyond the end
	          	 System.out.println("Prevent moving beyond the end  " );

	        }

	        player.setPosition(newPosition);
	      	 System.out.println("ameeeeeeeeeeera  " + newPosition);
	    }
	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	       
	    }

	    
	    
	    
	  