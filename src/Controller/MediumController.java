package Controller;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Model.Game;
import Model.Player;
import Model.Questions;
import Model.Square;
import Model.SquareType;
import Model.SysData;

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
	
	public int[] updatePlayerPosition(Player currentPlayer , int result , String type) { // update player position by dice result or by the type of the square 
		int newPosition = 0;
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
		return IAndJ;
	}
	
	public void checkTheTypeOfTheSquare(int i , int j , JFrame frame) { // call the show pop up if the square is a question 
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

		}
		else {
			for(int l = 0 ; l < game.getBoard().getSnakes().length ; l ++ ) {//check if the player in snake square 
                 if(s == game.getBoard().getSnakes()[l].getSquareStart()) {
                	 System.out.println("its a snakeeeee");
                	 updatePlayerPosition(game.getCurrentPlayer(),game.getBoard().getSnakes()[l].getSquareEnd().getValue(),"Snake");
                	 break;
                 }
			}
			for(int t = 0 ; t < game.getBoard().getLadders().length ; t ++ ) {//check if the player in snake square 
                if(s == game.getBoard().getLadders()[t].getSquareStart()) {
               	 System.out.println("its a Ladder !");
               	 updatePlayerPosition(game.getCurrentPlayer(),game.getBoard().getLadders()[t].getSquareEnd().getValue(),"Ladder");
               	 break;
                }
			}
		}
		
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
	
	private int[] FindSquareByValue(int val) {
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
			  if(result != question.getCorrectOption()) {
				 game.getCurrentPlayer().setPosition(game.getCurrentPlayer().getPosition()-1); 
			  }
		  }
		  if(question.getDiffculty() == 2) {
			  if(result != question.getCorrectOption()) {
				 game.getCurrentPlayer().setPosition(game.getCurrentPlayer().getPosition()-2); 
				  }
			  }
		  if(question.getDiffculty() == 3) {
			  if(result == question.getCorrectOption()) {
				  game.getCurrentPlayer().setPosition(game.getCurrentPlayer().getPosition()+1); 
			  }
			  else {
					 game.getCurrentPlayer().setPosition(game.getCurrentPlayer().getPosition()-3); 
				  }
		  } 
		  //System.out.println("val: "+ game.getCurrentPlayer().getPosition()+"correct answer: "+question.getCorrectOption());
	}
	  
}
