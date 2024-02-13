package Controller;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.Timer;

import Model.Game;
import Model.Player;
import Model.Questions;
import Model.Square;
import Model.SquareType;
import Model.SysData;
import View.MediumGameBoard;
import javafx.event.ActionEvent;

public class MediumController {
	private Game game;

	public MediumController(Game game) {
		super();
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	public void CallQuestionDataFunc() {
		SysData.getInstance().LoadQuestions();
		SysData.getInstance().putQuestions(SysData.getInstance().questionsPOPUP);
	}
	
	public int[] updatePlayerPosition(Player currentPlayer , int result , String type , JFrame frame, JLabel playerLabel) { // update player position by dice result or by the type of the square 
		int newPosition = 0;
		//MeduimFrame = frame;
		int[] IAndJ = new int[2];
		if(type.equals("Dice")) {
	     newPosition = currentPlayer.getPosition()+result;
		}
		if(type.equals("Snake") || type.equals("Ladder")) {
		     newPosition = result;
		}
	
		if(newPosition >=100) {
			newPosition = 100;
		}
		currentPlayer.setPosition(newPosition);
		IAndJ = FindSquareByValue(newPosition);
		checkTheTypeOfTheSquare(IAndJ[0],IAndJ[1],frame,playerLabel);
		animatePlayerMovement(playerLabel, IAndJ, game);
		return IAndJ;
	}
	
	public void checkTheTypeOfTheSquare(int i , int j , JFrame frame, JLabel playerLabel) { // call the show pop up if the square is a question 
		Square s = game.getBoard().getCells()[i][j];
		Questions question = null ; 
		if(s.getType() ==  SquareType.QUESTION) {
			int index = -1 ;
			Square[] q = game.getBoard().getQuestions();
			for (int k = 0 ; k < q.length ; k ++ ) {
				if (q[k] == s) {
					index = k ;
				}
			}
			if(index == 0 ) {
				question = SysData.getInstance().getQuestionLevel("easy");
			}
			if(index == 1 ) {
				question = SysData.getInstance().getQuestionLevel("medium");

			}
			if(index == 2 ) {
				question = SysData.getInstance().getQuestionLevel("hard");

			}
			showAddQuestionPopup(question ,frame );
			System.out.println("its a Question ");

		}
		else {
			for(int l = 0 ; l < game.getBoard().getSnakes().length ; l ++ ) {//check if the player in snake square 
                 if(s == game.getBoard().getSnakes()[l].getSquareStart()) {
                	 System.out.println("its a snakeeeee");
                	 updatePlayerPosition(game.getCurrentPlayer(),game.getBoard().getSnakes()[l].getSquareEnd().getValue(),"Snake",frame,playerLabel);
                	 break;
			}
			for(int t = 0 ; t < game.getBoard().getLadders().length ; t ++ ) {//check if the player in snake square 
                if(s == game.getBoard().getLadders()[t].getSquareStart()) {
               	 System.out.println("its a Ladder !");
               	 updatePlayerPosition(game.getCurrentPlayer(),game.getBoard().getLadders()[t].getSquareEnd().getValue(),"Ladder",frame,playerLabel);
               	 break;
                }
			}
			}}
		
	}
	
	public void DiceQuestion(int result , JFrame frame) {
		System.out.println(result);
		Questions question =null;
		if(result == 7) {
			 question = SysData.getQuestionLevel("easy");
		}
		else if(result == 8) {
			 question = SysData.getQuestionLevel("medium");
		}
		else {
			 question = SysData.getQuestionLevel("hard");
		}
		showAddQuestionPopup(question, frame);
		
	}
	
	public int[] FindSquareByValue(int val) {
		int[] IAndJ = new int[2];
		 for (int i = 0; i < game.getBoard().getCells().length; i++) {
	            for (int j = 0; j < game.getBoard().getCells().length; j++) {
	            	if(val == game.getBoard().getCells()[i][j].getValue()) {
	            		IAndJ[0]=i;
	            		IAndJ[1] = j;
	            		break;
	            	}
	            }
	        }
		 return IAndJ;
	}
	
	  public void showAddQuestionPopup(Questions question , JFrame frame) {
		  JRadioButton answer1Button = new JRadioButton();
		  JRadioButton answer2Button = new JRadioButton();
		  JRadioButton answer3Button = new JRadioButton();
		  JRadioButton answer4Button = new JRadioButton();

		  JLabel questionField = new JLabel(question.getQuestionText());
		  JLabel answer1Field = new JLabel(question.getOptions()[0]);
		  JLabel answer2Field = new JLabel(question.getOptions()[1]);
		  JLabel answer3Field = new JLabel(question.getOptions()[2]);
		  JLabel answer4Field = new JLabel(question.getOptions()[3]);
		  JLabel difficultyField = new JLabel(String.valueOf(question.getDiffculty()));

		  // Create JPanel for each answer option to include both the label and radio button
		  JPanel answer1Panel = new JPanel();
		  JPanel answer2Panel = new JPanel();
		  JPanel answer3Panel = new JPanel();
		  JPanel answer4Panel = new JPanel();

		  answer1Panel.add(answer1Button);
		  answer1Panel.add(answer1Field);
		  answer2Panel.add(answer2Button);
		  answer2Panel.add(answer2Field);
		  answer3Panel.add(answer3Button);
		  answer3Panel.add(answer3Field);
		  answer4Panel.add(answer4Button);
		  answer4Panel.add(answer4Field);

		  // Add radio buttons to button group
		  ButtonGroup buttonGroup = new ButtonGroup();
		  buttonGroup.add(answer1Button);
		  buttonGroup.add(answer2Button);
		  buttonGroup.add(answer3Button);
		  buttonGroup.add(answer4Button);

		  Object[] fields = {
		      "Question:", questionField,
		      "Answer 1:", answer1Panel,
		      "Answer 2:", answer2Panel,
		      "Answer 3:", answer3Panel,
		      "Answer 4:", answer4Panel,
		      "Difficulty:", difficultyField
		  };

		  int result = JOptionPane.showConfirmDialog(frame, fields, "Answer Question", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		  // Get the selected answer option
		  int selectedOption = -1;
		  if (result == JOptionPane.OK_OPTION) {
		      if (answer1Button.isSelected()) {
		          selectedOption = 0;
		      } else if (answer2Button.isSelected()) {
		          selectedOption = 1;
		      } else if (answer3Button.isSelected()) {
		          selectedOption = 2;
		      } else if (answer4Button.isSelected()) {
		          selectedOption = 3;
		      }
		
		  }
		  updateplayerbyAnswer(question, selectedOption);
	       
	    }
	  public void updateplayerbyAnswer(Questions question,int result) {
		  if(question.getDiffculty() == 1 ) {
			  if(result != question.getCorrectOption() && game.getCurrentPlayer().getPosition()!=1) {
				 game.getCurrentPlayer().setPosition(game.getCurrentPlayer().getPosition()-1); 
				 JOptionPane.showMessageDialog(null,"You have selected the wrong answer , sequensly u will move to "+game.getCurrentPlayer().getPosition()+" position" );
			  }
			  else {
					 JOptionPane.showMessageDialog(null,"You have selected the right answer , sequensly u will stay in your position" );
			  }
		  }
		  if(question.getDiffculty() == 2) {
			  if(result != question.getCorrectOption() && game.getCurrentPlayer().getPosition()>=3) {
				 game.getCurrentPlayer().setPosition(game.getCurrentPlayer().getPosition()-2); 
				 JOptionPane.showMessageDialog(null,"You have selected the wrong answer , sequensly u will move to "+game.getCurrentPlayer().getPosition()+" position" );
				  }
			  else {
					 JOptionPane.showMessageDialog(null,"You have selected the right answer , sequensly u will stay in your position" );
			  }
		  }
		  if(question.getDiffculty() == 3) {
			  System.out.println("hii , question diffculty 3 ");
			  if(result == question.getCorrectOption()) {
				  game.getCurrentPlayer().setPosition(game.getCurrentPlayer().getPosition()+1); 
				  JOptionPane.showMessageDialog(null,"You have selected the right answer , sequensly u will move to "+game.getCurrentPlayer().getPosition()+" position" );
			  }
			  else if(result == question.getCorrectOption() && game.getCurrentPlayer().getPosition()>=4){
					 game.getCurrentPlayer().setPosition(game.getCurrentPlayer().getPosition()-3); 
					 JOptionPane.showMessageDialog(null,"You have selected the wrong answer , sequensly u will move to "+game.getCurrentPlayer().getPosition()+" position" );
				  }
		  } 
		  //System.out.println("val: "+ game.getCurrentPlayer().getPosition()+"correct answer: "+question.getCorrectOption());
	}
	  
	  public void animatePlayerMovement(JLabel j, int[] iAndJ, Game g) {
		    final int targetX = g.getBoard().getCells()[iAndJ[0]][iAndJ[1]].getBoundsX();
		    final int targetY = g.getBoard().getCells()[iAndJ[0]][iAndJ[1]].getBoundsY() - 15; // Adjusting Y as in your method
		    final Timer timer = new Timer(10, null); // Adjust timing as needed for smoothness
		    timer.addActionListener(new ActionListener() {
		       

				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// TODO Auto-generated method stub
					 // Current position
		            int currentX = j.getX();
		            int currentY = j.getY();

		            // Determine the direction of movement
		            int dx = targetX - currentX;
		            int dy = targetY - currentY;

		            // Determine the step size for each timer tick (adjust for speed/smoothness)
		            int stepX = (int)Math.signum(dx);
		            int stepY = (int)Math.signum(dy);

		            // Move the JLabel towards the target location
		            if (Math.abs(dx) > 0 || Math.abs(dy) > 0) {
		                j.setLocation(currentX + stepX, currentY + stepY);
		            } else {
		                // Stop the timer when the target location is reached
		                timer.stop();
		            }
				}
		    });
		    timer.start();
		}
	  
	  public void setPlayerBackgroundColor(Model.Color color , JTextPane txtrPlayer) {//change the jtext background - by the player color
	        switch (color.toString()) {
	        case "BLUE":
	            txtrPlayer.setBackground(new java.awt.Color(0, 200, 220)); // Blue
	            break;
	        case "GREEN":
	            txtrPlayer.setBackground(new java.awt.Color(0, 120, 30)); // Green
	            break;
	        case "RED":
	            txtrPlayer.setBackground(new java.awt.Color(255, 102, 102)); // Red
	            break;
	        case "YELLOW":
	            txtrPlayer.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
	            break;
	        default:
	            // Default color for other players
	            txtrPlayer.setBackground(new java.awt.Color(192, 192, 192));
	            break;
	        } }

	
}
