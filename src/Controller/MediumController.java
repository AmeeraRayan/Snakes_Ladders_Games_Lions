package Controller;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
		int i , j  ; 
		int newPosition = 0;
		int[] IAndJ = new int[2];
		if(type.equals("Dice")) {
	     newPosition = currentPlayer.getPosition()+result;
		}
		if(type.equals("Snake") || type.equals("Ladder")) {
		     newPosition = result;
			}
		if(newPosition<100) {
			currentPlayer.setPosition(newPosition);
			IAndJ = FindSquareByValue(newPosition);
		}
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
				question = SysData.getInstance().getQuestionLevel("Easy");
			}
			if(index == 1 ) {
				question = SysData.getInstance().getQuestionLevel("Medium");

			}
			if(index == 2 ) {
				question = SysData.getInstance().getQuestionLevel("Hard");

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
	        JTextField questionField = new JTextField(question.getQuestionText());
	        JTextField answer1Field = new JTextField(question.getOptions()[0]);
	        JTextField answer2Field = new JTextField(question.getOptions()[1]);
	        JTextField answer3Field = new JTextField(question.getOptions()[2]);
	        JTextField answer4Field = new JTextField(question.getOptions()[3]);
	        JTextField correctAnswerField = new JTextField(String.valueOf(question.getCorrectOption() + 1)); // Adjusting because your user input is 1-based
	        JTextField difficultyField = new JTextField(String.valueOf(question.getDiffculty()));

	        Object[] fields = {
	            "Question:", questionField,
	            "Answer 1:", answer1Field,
	            "Answer 2:", answer2Field,
	            "Answer 3:", answer3Field,
	            "Answer 4:", answer4Field,
	            "Correct Answer (1-4):", correctAnswerField,
	            "Difficulty:", difficultyField
	        };

	        JOptionPane.showConfirmDialog(frame, fields, "Edit Question", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

	       
	    }

}
