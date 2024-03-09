package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.JScrollPane;
import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Controller.GameController;
import Controller.MangQuestionControl;
import Model.Questions;
import Model.SysData;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Container;
import java.awt.Font;


public class QuestionManagment extends JFrame   {

    JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
	private JTextField searchField;
    private TableRowSorter<DefaultTableModel> sorter;
    static  SysData sysData = new SysData();
    static MangQuestionControl mangQuestionControl=new MangQuestionControl();
    boolean validInput = false;
    private GameController gameController = new GameController(null);


    public QuestionManagment() {
        initialize();
        sysData = SysData.getInstance();
        sysData.LoadQuestions(); // Load questions from the JSON file
        populateTableWithData();

    }

    public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 20, 1550, 903);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);


        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        tableModel.addColumn("Question");
        tableModel.addColumn("Correct Answer");
        tableModel.addColumn("Difficulty");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(283, 251, 1020, 454);
        frame.getContentPane().add(scrollPane);
        
        JLabel label = new JLabel("");
        label.setBounds(20, 803, 188, 80);
        frame.getContentPane().add(label);
        label.addMouseListener(new MouseAdapter() {
        	  @Override
              public void mouseClicked(MouseEvent e) {
                // Code to go back to the main screen
                // For example, you might close the current frame and open the main screen frame
                frame.dispose(); // Close the current frame
                MainScreen mainScreen = new MainScreen(); // Assuming MainScreen is your main screen class
                mainScreen.show(); // Show the main screen frame
                gameController.buttonClick();
            }
        });
        
        searchField = new JTextField();
        searchField.setColumns(20);
        searchField.setBounds(283, 131, 766, 38);
        frame.getContentPane().add(searchField);
        
        JButton btnAdd = new JButton("Add Question");
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAdd.setBounds(348, 187, 147, 53);
        btnAdd.addActionListener(e -> showAddQuestionPopup());
        frame.getContentPane().add(btnAdd);
        
        JButton btnEdit = new JButton("Edit Question");
        btnEdit.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnEdit.setBounds(589, 187, 155, 53);
        btnEdit.addActionListener(e -> editQuestion());
        frame.getContentPane().add(btnEdit);

        JButton btnDelete = new JButton("Delete Question");
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnDelete.setBounds(837, 187, 157, 53);
        btnDelete.addActionListener(e -> deleteQuestion());
        frame.getContentPane().add(btnDelete);

        JButton btnShow = new JButton("Show Question");
        btnShow.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnShow.setBounds(1082, 187, 155, 53);
        btnShow.addActionListener(e -> showQuestion());
        frame.getContentPane().add(btnShow);

        // Create a button for performing the search
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        searchButton.setBounds(1082, 129, 176, 38);
        searchButton.addActionListener(new ActionListener() {
          
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				performSearch();
			}
        });
        frame.getContentPane().add(searchButton);

        // Set up a sorter for the table
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter((RowSorter<? extends TableModel>) sorter);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(QuestionManagment.class.getResource("/images/QuestionManagment.jpeg")));
        lblNewLabel.setBounds(-16, -33, 1891, 972);
        frame.getContentPane().add(lblNewLabel);
    }

    private void performSearch() {
        String query = searchField.getText().toLowerCase();
        ((DefaultRowSorter<DefaultTableModel, Integer>) sorter).setRowFilter(RowFilter.regexFilter(query));    
    }
    
    private void populateTableWithData() {
       
        for (Questions question : SysData.getInstance().getQuestions()) {
            Object[] rowData = {question.getQuestionText(), question.getCorrectOption(), question.getDiffculty()};
            tableModel.addRow(rowData);
        }
    }
    private void showAddQuestionPopup() {
        JTextField questionField = new JTextField();
        JTextField answer1Field = new JTextField();
        JTextField answer2Field = new JTextField();
        JTextField answer3Field = new JTextField();
        JTextField answer4Field = new JTextField();
        JTextField correctAnswerField = new JTextField();
        JTextField difficultyField = new JTextField();

        while (true) {
            Object[] fields = {
                "Question:", questionField,
                "Answer 1:", answer1Field,
                "Answer 2:", answer2Field,
                "Answer 3:", answer3Field,
                "Answer 4:", answer4Field,
                "Correct Answer (1-4):", correctAnswerField,
                "Difficulty:", difficultyField
            };

            int result = JOptionPane.showConfirmDialog(null, fields, "Add New Question", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) {
                break; // Exit if user cancels or closes the dialog
            }

            try {
                int correctAnswerIndex = Integer.parseInt(correctAnswerField.getText());
                int difficulty = Integer.parseInt(difficultyField.getText());
                String[] answers = {answer1Field.getText(), answer2Field.getText(), answer3Field.getText(), answer4Field.getText()};

                if (!isValidDifficulty(difficulty)) {
                    JOptionPane.showMessageDialog(null, "Invalid difficulty. Please enter a value between 1 and 3.");
                    difficultyField.setText(""); // Clear only the difficulty field
                    continue;
                }
                if (!isValidCorrectAnswer(correctAnswerIndex)) {
                    JOptionPane.showMessageDialog(null, "Invalid correct answer. Please enter a value between 1 and 4.");
                    correctAnswerField.setText(""); // Clear only the correct answer field
                    continue;
                }
                if (!isValidAnswersFormat(answers)) {
                    JOptionPane.showMessageDialog(null, "Invalid answers format. Please provide a non-empty string for each answer.");
                    continue;
                }

                Questions newQuestion = new Questions(questionField.getText(), answers, correctAnswerIndex, difficulty, sysData.getQuestions().size());
                mangQuestionControl.addNewQuestion(newQuestion);
                tableModel.addRow(new Object[]{newQuestion.getQuestionText(), newQuestion.getCorrectOption(), newQuestion.getDiffculty()});
                
                // Show success message
                JOptionPane.showMessageDialog(null, "Question added successfully!");

                break;

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid number format for Correct Answer and/or Difficulty.");
                correctAnswerField.setText(""); // Clear the correct answer field
                difficultyField.setText(""); // Clear the difficulty field
            }
        }
    }

    private void editQuestion() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            // Get the selected question
        	sysData.LoadQuestions();
            Questions selectedQuestion = SysData.getInstance().getQuestions().get(selectedRow);

            // Display a dialog for editing
            showEditQuestionDialog(selectedQuestion);

            // Refresh the table after editing
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a question to edit.");
        }
    }

    private void showEditQuestionDialog(Questions question) {
        JTextField questionField = new JTextField(question.getQuestionText());
        JTextField answer1Field = new JTextField(question.getOptions()[0]);
        JTextField answer2Field = new JTextField(question.getOptions()[1]);
        JTextField answer3Field = new JTextField(question.getOptions()[2]);
        JTextField answer4Field = new JTextField(question.getOptions()[3]);
        JTextField correctAnswerField = new JTextField(String.valueOf(question.getCorrectOption() )); 
        JTextField difficultyField = new JTextField(String.valueOf(question.getDiffculty()));

        while (true) {
            Object[] fields = {
                "Question:", questionField,
                "Answer 1:", answer1Field,
                "Answer 2:", answer2Field,
                "Answer 3:", answer3Field,
                "Answer 4:", answer4Field,
                "Correct Answer (1-4):", correctAnswerField,
                "Difficulty:", difficultyField
            };

            int result = JOptionPane.showConfirmDialog(null, fields, "Edit Question", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) {
                break; // Exit if user cancels or closes the dialog
            }

            try {
                int correctAnswerIndex = Integer.parseInt(correctAnswerField.getText());
                int difficulty = Integer.parseInt(difficultyField.getText());
                String[] answers = {answer1Field.getText(), answer2Field.getText(), answer3Field.getText(), answer4Field.getText()};

                if (!isValidDifficulty(difficulty)) {
                    JOptionPane.showMessageDialog(null, "Invalid difficulty. Please enter a value between 1 and 3.");
                    difficultyField.setText(""); // Clear only the difficulty field
                    continue;
                }
                if (!isValidCorrectAnswer(correctAnswerIndex)) {
                    JOptionPane.showMessageDialog(null, "Invalid correct answer. Please enter a value between 1 and 4.");
                    correctAnswerField.setText(""); // Clear only the correct answer field
                    continue;
                }
                if (!isValidAnswersFormat(answers)) {
                    JOptionPane.showMessageDialog(null, "Invalid answers format. Please provide a non-empty string for each answer.");
                    continue;
                }

                // Update the question object with new values
                question.setQuestionText(questionField.getText());
                question.setOptions(answers);
                question.setCorrectOption(correctAnswerIndex);
                question.setDiffculty(difficulty);

                // Call the SysData method to edit the question in the JSON file
                mangQuestionControl.editQuestion(question.getid(), question);

                // Refresh the table after editing
                refreshTable();
                JOptionPane.showMessageDialog(null, "Question updated successfully!");

                break;

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid number format for Correct Answer and/or Difficulty.");
                correctAnswerField.setText(""); // Clear the correct answer field
                difficultyField.setText(""); // Clear the difficulty field
            }
        }
    }


    private void refreshTable() {
        // Clear the current table data
        tableModel.setRowCount(0);

        // Repopulate the table with updated data
        populateTableWithData();
    }


    private void deleteQuestion() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int questionId = mangQuestionControl.getQuestions().get(selectedRow).getid();
            // Call the SysData method to remove the question
            mangQuestionControl.removeQuestionLocaly(questionId);
            // Update the table
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(null, "Question removed successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Please select a question to delete.");
        }
    }



    private void showQuestion() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String question = (String) table.getValueAt(selectedRow, 0);
            Integer correctAnswerIndex = (Integer) table.getValueAt(selectedRow, 1);
            Integer difficulty = (Integer) table.getValueAt(selectedRow, 2);

            // Get the question object from SysData
            List<Questions> questionsList = SysData.getInstance().getQuestions();
            Questions selectedQuestion = questionsList.get(selectedRow);

            // Extract answers from the selected question
            String[] answers = selectedQuestion.getOptions();

            // Build the message including all answers and highlight the correct one
            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append("Question: ").append(question).append("\n");
            for (int i = 0; i < answers.length; i++) {
                messageBuilder.append(answers[i]).append("\n");
            }
            messageBuilder.append("Correct Answer: ").append(correctAnswerIndex).append("\n");
            messageBuilder.append("Difficulty: ").append(difficulty);

            // Show the message dialog
            JOptionPane.showMessageDialog(null, messageBuilder.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Please select a question to show.");
        }
    }

	private boolean isValidDifficulty(int difficulty) {
	    return difficulty >= 1 && difficulty <= 3;
	}
	
	private boolean isValidCorrectAnswer(int correctAnswer) {
	    return correctAnswer >= 0 && correctAnswer < 5;
	}
	
	private boolean isValidAnswersFormat(String[] answers) {
	    // Add more specific validation logic if needed
	    return answers.length == 4 && !Arrays.stream(answers).anyMatch(String::isEmpty);
	}
}
