package View;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
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
	        player.setPosition(newPosition);
	        System.out.println("player="+player.getName()+" "+player.getPosition());

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
	        	Questions quesTemp;
	            if (player.getPosition() == Integer.parseInt(q.getValue())) {
	            	System.out.println("square question here");///question
	    	        SysData sysdata=new SysData();
	    	        sysdata.LoadQuestions();
					questionsPOPUP=SysData.getQuestionsPOPUP();
	    	        SysData.putQuestions(questionsPOPUP);
	    	        quesTemp= SysData.getQuestionForPosition(player.getPosition());
	    	        System.out.println(quesTemp);
	                new QuestionpopUP(quesTemp , player,game).setVisible(true);
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
}