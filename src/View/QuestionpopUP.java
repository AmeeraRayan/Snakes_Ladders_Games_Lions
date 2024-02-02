package View;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.Robot;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Model.Game;
import Model.Questions;
import Model.Questions;
import Model.SysData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class QuestionpopUP extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private Questions currQuestion;
    private int OnwhichQuestionland;
    private ButtonGroup optionsGroup;
    private JRadioButton answer1RButton, answer2RButton, answer3RButton, answer4RButton;
    private JButton submitButton;
    private JLabel questionLabel;
    private int selectedAnswer = -1;
    
    
    
    public QuestionpopUP() {
        setTitle("Question");
        setSize(510, 485);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 700);
        contentPane = new JPanel();
        contentPane.setLayout((LayoutManager) new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);

        // Question label
        questionLabel = new JLabel("Select One Type of nonfunctional requirement?");
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(questionLabel);

        // Radio buttons for options
        optionsGroup = new ButtonGroup();
        answer1RButton = new JRadioButton("Option 1");
        answer2RButton = new JRadioButton("Option 2");
        answer3RButton = new JRadioButton("Option 3");
        answer4RButton = new JRadioButton("Option 4");
        optionsGroup.add(answer1RButton);
        optionsGroup.add(answer2RButton);
        optionsGroup.add(answer3RButton);
        optionsGroup.add(answer4RButton);

        // Adding radio buttons to the content pane
        contentPane.add(answer1RButton);
        contentPane.add(answer2RButton);
        contentPane.add(answer3RButton);
        contentPane.add(answer4RButton);

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				// TODO Auto-generated method stub
				// Handle submit action
                if (answer1RButton.isSelected()) selectedAnswer = 1;
                if (answer2RButton.isSelected()) selectedAnswer = 2;
                if (answer3RButton.isSelected()) selectedAnswer = 3;
                if (answer4RButton.isSelected()) selectedAnswer = 4;
                
                // Close the dialog or handle the selected answer
                handleAnswer(selectedAnswer);
				
			}
        	
        });
   
        
        		
        contentPane.add(submitButton);
     // Initialize question and answers
        initialize();
        // Display the window.
        pack();
        setVisible(true);
    }
    public void initialize() {
        // Example question and answers
        currQuestion = new Questions("Select One Type of nonfunctional requirement?", 
                                     new String[]{"user requirements", "product requirements", "Efficiency requirements", "Correct Answer"}, 
                                     3, // Assuming the correct answer is the third option
                                     2); // Difficulty level
        questionLabel.setText(currQuestion.getQuestionText());
        answer1RButton.setText(currQuestion.getOptions()[0]);
        answer2RButton.setText(currQuestion.getOptions()[1]);
        answer3RButton.setText(currQuestion.getOptions()[2]);
        answer4RButton.setText(currQuestion.getOptions()[3]);
    }

    private void handleAnswer() {
        if (answer1RButton.isSelected()) selectedAnswer = 1;
        if (answer2RButton.isSelected()) selectedAnswer = 2;
        if (answer3RButton.isSelected()) selectedAnswer = 3;
        if (answer4RButton.isSelected()) selectedAnswer = 4;
        
        if(selectedAnswer == currQuestion.getCorrectOption()) {
            JOptionPane.showMessageDialog(this, "Correct Answer!");
        } else {
            JOptionPane.showMessageDialog(this, "Wrong Answer!");
        }

        // Close the window
        dispose();
    }
    private void handleAnswer(int selectedAnswer) {
        // Implement your logic to handle the answer
        System.out.println("Answer Selected: " + selectedAnswer);
    }
/*
	public void initialize() {
		OnwhichQuestionland = Game.getInstance().OnwhichQuestionland;
		Random rand = new Random();
		int randIndex = 0;
		switch(OnwhichQuestionland) {
			case 1:
				Set<String> easy = SysData.getInstance().getEasyQuestions().keySet();
				ArrayList<String> easyKeys = new ArrayList<String>(easy);
				randIndex = rand.nextInt(easyKeys.size());
				currQuestion = SysData.getInstance().getEasyQuestions().get(easyKeys.get(randIndex));
				break;
			case 2:
				Set<String> medium = SysData.getInstance().getMediumQuestions().keySet();
				ArrayList<String> mediumKeys = new ArrayList<String>(medium);
				randIndex = rand.nextInt(mediumKeys.size());
				currQuestion = SysData.getInstance().getMediumQuestions().get(mediumKeys.get(randIndex));
				break;
			case 3:
				Set<String> hard = SysData.getInstance().getHardQuestions().keySet();
				ArrayList<String> hardKeys = new ArrayList<String>(hard);
				randIndex = rand.nextInt(hardKeys.size());
				currQuestion = SysData.getInstance().getHardQuestions().get(hardKeys.get(randIndex));
				break;
		}
		questionArea.setWrapText(true);
		questionArea.setText(currQuestion.getQuestionText());
		answer1RButton.setWrapText(true);
		answer1RButton.setText(currQuestion.getOptions()[0]);
		answer2RButton.setWrapText(true);
		answer2RButton.setText(currQuestion.getOptions()[1]);
		answer3RButton.setWrapText(true);
		answer3RButton.setText(currQuestion.getOptions()[2]);
		answer4RButton.setWrapText(true);
		answer4RButton.setText(currQuestion.getOptions()[3]);
	}
	void answer1Action(ActionEvent event) {
    	selectedAnswer = 1;
    }
    void answer2Action(ActionEvent event) {
    	selectedAnswer = 2;
    }

    void answer3Action(ActionEvent event) {
    	selectedAnswer = 3;
    }

    void answer4Action(ActionEvent event) {
    	selectedAnswer = 4;
    }
    
    
	void submitAction(ActionEvent event) throws AWTException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Alert alert = new Alert(AlertType.INFORMATION);    	
    	Optional<ButtonType> result = null;
    	//Checks if the player was right
    	if(selectedAnswer == currQuestion.getCorrectOption()) {
    		switch(OnwhichQuestionland) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
    		}
    	}
    	//Wrong Answer
    	else {
    		switch(OnwhichQuestionland) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
    		}
    	}
    		stage.close();
    		Robot robot = new Robot();
    		robot.keyPress(KeyEvent.VK_ENTER);
    		robot.keyRelease(KeyEvent.VK_ENTER);
    	//}

    }*/
    public static void main(String[] args) {
        // Run the form
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QuestionpopUP().setVisible(true);
            }
        });
    }
	
}
