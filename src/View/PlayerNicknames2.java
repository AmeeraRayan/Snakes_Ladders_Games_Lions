package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class PlayerNicknames2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textField2;
	public JTextField textField1;
	private final JLabel lblNewLabel_1 = new JLabel("Player 1");
	private JButton Next;
	private int numberOfPlayers;
	private String difficultyLevel;
	/**
	 * Create the frame.
	 */
	public PlayerNicknames2(int numberOfPlayers, String difficultyLevel) {
		this.numberOfPlayers=numberOfPlayers;
		this.difficultyLevel=difficultyLevel;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1019, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		 // Make the frame undecorated (no title bar, no minimize/maximize/close buttons)
        setUndecorated(true);
		
		 Next = new JButton("Next");
		
		Next.setBounds(680, 493, 183, 47);
		contentPane.add(Next);
		JButton Back = new JButton("Back");
		
		Back = new JButton("Back");
		Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           	 PlayerNicknames2.this.setVisible(false);
				new DataReception().setVisible(true);
            }
        });
		Back.setBounds(100, 493, 183, 47);
		contentPane.add(Back);
		
		Next.setBounds(680, 493, 183, 47);
		contentPane.add(Next);
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Player 2");
		lblNewLabel_1_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(374, 323, 126, 36);
		contentPane.add(lblNewLabel_1_1);
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(374, 178, 126, 36);
		contentPane.add(lblNewLabel_1);
		
		textField1 = new JTextField();
		textField1.setColumns(10);
		textField1.setBounds(374, 221, 173, 36);
		contentPane.add(textField1);
		
		textField2 = new JTextField();
		textField2.setBounds(374, 363, 173, 36);
		contentPane.add(textField2);
		textField2.setColumns(10);
		textField1.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
		            e.consume();  // ignore the event if it's not a letter or a control character
		        }
		    }
		});

		textField2.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		    	if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
		            e.consume();  // ignore the event if it's not a letter or a control character
		        }}
		});
        initializeNextButtonAction(); 

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PlayerNicknames2.class.getResource("/images/Nicknames2.png")));
		lblNewLabel.setBounds(0, -76, 1005, 737);
		contentPane.add(lblNewLabel);
	}
	private boolean isValidString(String str) {// the checks if the name is valid - not null or empty value
        return str != null && !str.isEmpty();
    }
	 private void showMessage(String message) {//show message if the name is unvalid
	        JOptionPane.showMessageDialog(PlayerNicknames2.this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
	    }
	 private void initializeNextButtonAction() {
	        Next.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                handleNextAction();
	            }
	        });
	    }

	    public boolean handleNextAction() {
	        String[] playerNames = getPlayerNames();
	        if (validatePlayerNames(playerNames)) {
	            Color[] colors = assignColorsToPlayers(playerNames.length);
	            navigateToPlayerTurnView(playerNames, colors);
	        } else {
	            showMessage("Please enter valid and unique nicknames.");
				return false;
	        }
			return true;
	    }

	    private String[] getPlayerNames() {
	        String[] playerNames = new String[numberOfPlayers];
	        playerNames[0] = textField1.getText().trim();
	        playerNames[1] = textField2.getText().trim();
	        return playerNames;
	    }

	    private boolean validatePlayerNames(String[] playerNames) {
	        for (String name : playerNames) {
	            if (!isValidString(name) || name.isEmpty()) return false;
	        }
	        return !playerNames[0].equals(playerNames[1]);
	    }

	    private Color[] assignColorsToPlayers(int numberOfPlayers) {
	        Color[] colors = new Color[numberOfPlayers];
	        colors[0] = Color.GREEN;
	        colors[1] = Color.BLUE;
	        return colors;
	    }

	    private void navigateToPlayerTurnView(String[] playerNames, Color[] colors) {
	        this.setVisible(false);
	        new PlayerTurn(numberOfPlayers, difficultyLevel, playerNames, colors).setVisible(true);
	    }

	


}
