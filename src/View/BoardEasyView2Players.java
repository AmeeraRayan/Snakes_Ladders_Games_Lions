package View;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import Controller.PreGameController;
import Model.Color;
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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoardEasyView2Players extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Game game;
	private Player currentPlayer;
	private int currentPlayerIndex;
    private int rollResult;
	private JButton diceButton;
	private JLabel currentPlayerLabel;
	public static JTextPane txtpnHi;
	public static StringBuilder positionsText;

	public static HashMap<String,Questions> questionsPOPUP= new HashMap<String, Questions>();

	public BoardEasyView2Players(Game game ) {
		this.currentPlayer=game.getCurrentPlayer();
		this.game=game;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// Add this inside the constructor
		currentPlayerLabel = new JLabel("");
		currentPlayerLabel.setForeground(new java.awt.Color(47, 79, 79));
		currentPlayerLabel.setFont(new Font("High Tower Text", Font.BOLD, 35));
		currentPlayerLabel.setBounds(20, 20, 450, 50); 
		contentPane.add(currentPlayerLabel);
		setContentPane(contentPane);
		contentPane.setLayout(null);
	       
	         txtpnHi = new JTextPane();
	        txtpnHi.setFont(new Font("Palatino Linotype", Font.BOLD, 24));
	        txtpnHi.setForeground(java.awt.Color.BLUE);
	        txtpnHi.setBackground(new java.awt.Color(255, 250, 250));
	        
	        txtpnHi.setBounds(10, 77, 519, 70);
	        contentPane.add(txtpnHi);
	      
		 diceButton = new JButton("");
        diceButton.setIcon(new ImageIcon(PlayerTurn.class.getResource("/images/dice 4.jpg")));
		diceButton.setBounds(760, 290, 130, 145);
		contentPane.add(diceButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BoardEasyView2Players.class.getResource("/images/boradeasy2.png")));
		lblNewLabel.setBounds(0, 10, 900, 670);

		contentPane.add(lblNewLabel);
   
		startGame();
		
		
	}
	private void advanceToNextPlayer() {
	    currentPlayerIndex = (currentPlayerIndex + 1) % game.getPlayers().size();
	    currentPlayer = game.getPlayers().get(currentPlayerIndex);
	    
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
	private void performDiceRollAndMove() {
	    diceButton.setEnabled(false);
	    rollResult = game.getDice().rollForEasy();
	    ImageIcon diceIcon = new ImageIcon(getClass().getResource("/images/dice " + rollResult + ".jpg"));
	    diceButton.setIcon(diceIcon);
	    
	    // Show popup with dice roll result
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
	    positionsText = new StringBuilder();
	    for (Player player : game.getPlayers()) {
	        positionsText.append(" " + player.getName()).append(" on sqaure: ").append(player.getPosition()).append("\n");
	    }
	    txtpnHi.setText(positionsText.toString());
	    contentPane.revalidate();
	    contentPane.repaint();
	}

	private boolean hasPlayerWon(Player player) {
	    int maxPosition = game.getBoard().getSize() * game.getBoard().getSize();
	    return player.getPosition() >= maxPosition;
	}

	private void rollDiceAndMovePlayer() {
	    currentPlayer = game.getCurrentPlayer();
	    displayCurrentPlayer(); // Display the current player's name
	    enableDiceRollForCurrentPlayer();
	}

	private void endGame(Player winner) {
	    JOptionPane.showMessageDialog(this, winner.getName() + " wins the game!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
	    diceButton.setEnabled(false);
	}

	    private void movePlayer(Player player, int roll) {
	        int newPosition = player.getPosition() + roll;
	        newPosition = Math.min(newPosition, game.getBoard().getSize() * game.getBoard().getSize()); // Assuming a square board
	        player.setPosition(newPosition);
	        System.out.println("player="+player.getName()+" "+player.getPosition());

	    }
		private void checkForSnakesAndLadders(Player player) {
	
	        for (Snake snake : game.getBoard().getSnakes()) {
	            if (player.getPosition() == Integer.parseInt(snake.getSquareStart().getValue()) ) {
	                player.setPosition(Integer.parseInt(snake.getSquareEnd().getValue()));
	                showSnakePopup(player); 
	    	        System.out.println("player if snake="+player.getName()+" "+player.getPosition());
	                break;
	            }
	        }
	        
	        for (Ladder ladder : game.getBoard().getLadders()) {
	            if (player.getPosition() == Integer.parseInt(ladder.getSquareStart().getValue()) ) {
	                player.setPosition(Integer.parseInt(ladder.getSquareEnd().getValue()));
	                showLadderPopup(player); 
	    	        System.out.println("player if ladder="+player.getName()+" "+player.getPosition());
	                break;
	            }
	        }
	        for (Square q : game.getBoard().getQuestions()) {
	        	Questions quesTemp;
	            if (player.getPosition() == Integer.parseInt(q.getValue()) ) {
	    	        System.out.println("square question here");///question
	    	        SysData sysdata=new SysData();
	    	        sysdata.LoadQuestions();
					questionsPOPUP=SysData.getQuestionsPOPUP();
	    	        SysData.putQuestions(questionsPOPUP);
	    	        quesTemp= SysData.getQuestionForPosition(player.getPosition());
	    	        System.out.println(quesTemp);
	                new QuestionpopUP(quesTemp , player,game).setVisible(true);
	                break;
	            }}
	        
	    }
	    
	    private void displayCurrentPlayer() {
	        if (currentPlayer != null) {
	            currentPlayerLabel.setText("Player Turn: " + currentPlayer.getName());
	            setTitle("Current Player: " + currentPlayer.getName() + "'s Turn");
	        }
	    }

	    public void startGame() {
	        initializePlayerPositions();
	        rollDiceAndMovePlayer(); 
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