package Lions_JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Controller.MangQuestionControl;
import Model.Color;
import Model.Game;
import Model.Player;
import Model.Questions;
import Model.SysData;
import View.BoardEasyViewPlayers;
import static org.junit.Assert.assertEquals;
//myClassTest
public class myClassTest {
	
	/*White box tests*/

	/*ID:WB1,test for red snake in easy game if it return the player to 1*/
	@Test
	public void testRedSnakeEncounter() {
		Player player1 = new Player("TestPlayer1", Color.GREEN);
		Player player2 = new Player("TestPlayer", Color.BLUE);	    
		Game game = new Game("Easy", new ArrayList<>(Arrays.asList(player1,player2)));

		BoardEasyViewPlayers board2= new BoardEasyViewPlayers(game);
		if ((board2.path).equals("board1"))
		{
			player1.setPosition(11);//red snake in 11
		}
		if ((board2.path).equals("board2"))
		{
			player1.setPosition(45);//red snake in 45
		}
		if  ((board2.path).equals("board3"))
		{
			player1.setPosition(22);//red snake in 22
		}
		board2.checkForSnakesAndLadders(player1.getPosition());

		// Assert player position is now 1
		assertEquals(1, player1.getPosition());
	}

	/*ID=WB2,test if the ladder in square 2 leads to square 23 0 In all the easy game changing board 
	 * we have ladder at square number 2 that leads to square 23 */
	@Test
	public void testLadderClimb() {
		Player player1 = new Player("TestPlayer1", Color.GREEN);
		Player player2 = new Player("TestPlayer", Color.BLUE);	    
		Game game = new Game("Easy", new ArrayList<>(Arrays.asList(player1,player2)));

		BoardEasyViewPlayers board2= new BoardEasyViewPlayers(game);
		player1.setPosition(2);//a ladder in 2
		board2.checkForSnakesAndLadders(player1.getPosition());
		assertEquals( 23, player1.getPosition());

	}

	/*ID=WB3 test if the easy game correctly initializes the board size and sets players's first positions at 1*/
	@Test
	public void testEasyDifficultyInitialization() {
		Player player1 = new Player("TestPlayer1", Color.GREEN);
		Player player2 = new Player("TestPlayer", Color.BLUE);	   
		Game game = new Game("Easy", new ArrayList<>(Arrays.asList(player1,player2)));
		assertEquals( 7, game.getBoard().getSize());

		// Assuming a method to check all players are at starting position
		game.getPlayers().forEach(player ->
		assertEquals( 1, player.getPosition())
				);
	}
	
	/*ID=WB4 test the dice roll correctly updates the player's position on the board, 
	 * considering the board's size and avoiding overshooting the last square in easy game.
	 */
	@Test
    public void testDiceRollMovement() {
		Player player1 = new Player("TestPlayer1", Color.GREEN);
		Player player2 = new Player("TestPlayer", Color.BLUE);	   
		Game game = new Game("Easy", new ArrayList<>(Arrays.asList(player1,player2)));
		BoardEasyViewPlayers board2= new BoardEasyViewPlayers(game);
		player1.setPosition(48);

        int diceRoll = 3;
        board2.movePlayer(player1, diceRoll);

        // Expected Output: Player moves to square 49, not beyond it
        int expectedPosition = 49; // Last square on the Easy game board
        assertEquals( expectedPosition, player1.getPosition());
    }

	/*  @Test
	    public void testMediumDifficultyInitialization() {
	    	Player player1 = new Player("TestPlayer1", Color.GREEN);
	    	Player player2 = new Player("TestPlayer", Color.BLUE);	   
		    Game game = new Game("Easy", new ArrayList<>(Arrays.asList(player1,player2)));
	        assertEquals( 10, game.getBoard().getSize());

	        game.getPlayers().forEach(player ->
	            assertEquals( 1, player.getPosition())
	        );
	    }
	 */
	
	
	/*Black box tests*/

	/*ID:BB1 test if the addNewQuestion method in the SysData class successfully add a new question,answers,difficulty
	     to a JSON file.
	 */
	@Test
	public void testAddNewQuestion() {
		MangQuestionControl controller =new MangQuestionControl();
        Questions newQuestion = new Questions("What is a key principle of Agile software development?", new String[]{"1.Extensive planning at the beginning of a project to ensure there are no changes in the requirements."
        		,"2.Strong documentation is more important than working software.",
        		"3.Regular adaptation to changing circumstances and user feedback.",
        		"4.Complete each project phase before starting the next one without any overlap."}, 3, 1);
        int initialSize = controller.getQuestions().size();

        controller.addNewQuestion(newQuestion);

        List<Questions> updatedQuestions = controller.getQuestions();
        int updatedSize = updatedQuestions.size();

        // Assert: The list should now contain the added question
        Assertions.assertTrue(updatedSize == initialSize + 1, "The question list size should be increased by 1.");
        Assertions.assertTrue(updatedQuestions.contains(newQuestion), "The new question should be in the list.");
    }



}
