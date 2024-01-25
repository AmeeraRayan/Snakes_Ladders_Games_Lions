package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayersNacknames extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField[] textFields;
    private JButton btnNewButton;
    private JButton btnNewButton_1;

    public PlayersNacknames(int numberOfPlayers, String difficultyLevel) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 446);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Players Nicknames");
        lblNewLabel.setBounds(251, 23, 150, 25);
        contentPane.add(lblNewLabel);

        // Dynamically create and initialize an array of JTextFields
        textFields = new JTextField[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            JLabel label = new JLabel("Player " + (i + 1));
            label.setBounds(50, 80 + i * 50, 60, 13);
            contentPane.add(label);

            textFields[i] = new JTextField();
            textFields[i].setBounds(120, 77 + i * 50, 96, 19);
            contentPane.add(textFields[i]);
            textFields[i].setColumns(10);
        }

        btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayersNacknames.this.setVisible(false);
                new DataReception().setVisible(true);
            }
        });
        btnNewButton.setBounds(79, 77 + numberOfPlayers * 50, 121, 53);
        contentPane.add(btnNewButton);

        btnNewButton_1 = new JButton("Next");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayersNacknames.this.setVisible(false);
                // Pass the array of player nicknames to the next screen
                new PlayerTurn(numberOfPlayers, difficultyLevel, getPlayerNicknames()).setVisible(true);
            }
        });
        btnNewButton_1.setBounds(250, 77 + numberOfPlayers * 50, 112, 53);
        contentPane.add(btnNewButton_1);
    }

    // Helper method to get player nicknames from text fields
    private String[] getPlayerNicknames() {
        String[] playerNicknames = new String[textFields.length];
        for (int i = 0; i < textFields.length; i++) {
            playerNicknames[i] = textFields[i].getText();
        }
        return playerNicknames;
    }
}
