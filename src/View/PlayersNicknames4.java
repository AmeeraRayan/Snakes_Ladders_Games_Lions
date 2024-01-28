package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Model.Color;
import Model.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class PlayersNicknames4 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField[] textFields;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField2;
    private JButton button;
    private JButton Next;

    public PlayersNicknames4(int numberOfPlayers, String difficultyLevel) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 938, 693);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        JButton Back = new JButton("Back");
		
 		Back = new JButton("Back");
 		Back.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 PlayersNicknames4.this.setVisible(false);
 				new DataReception().setVisible(true);
             }
         });
 		Back.setBounds(120, 518, 135, 52);
 		contentPane.add(Back);
        Next = new JButton("Next");
      
        Next.setBounds(712, 518, 135, 52);
        contentPane.add(Next);
        
        textField2 = new JTextField();
        textField2.setColumns(10);
        textField2.setBounds(524, 258, 200, 32);
        contentPane.add(textField2);
        
        textField4 = new JTextField();
        textField4.setColumns(10);
        textField4.setBounds(524, 403, 200, 32);
        contentPane.add(textField4);
        
        textField3 = new JTextField();
        textField3.setColumns(10);
        textField3.setBounds(188, 403, 200, 32);
        contentPane.add(textField3);
        
        textField1 = new JTextField();
        textField1.setBounds(188, 258, 200, 32);
        contentPane.add(textField1);
        textField1.setColumns(10);
        
        JLabel lblPlayer_2_2_1 = new JLabel("Player 4");
        lblPlayer_2_2_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
        lblPlayer_2_2_1.setBounds(524, 346, 311, 64);
        contentPane.add(lblPlayer_2_2_1);
        
        JLabel lblPlayer_2_2 = new JLabel("Player 3");
        lblPlayer_2_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
        lblPlayer_2_2.setBounds(188, 356, 311, 64);
        contentPane.add(lblPlayer_2_2);
        
        JLabel lblPlayer_2 = new JLabel("Player 2");
        lblPlayer_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
        lblPlayer_2.setBounds(524, 207, 311, 64);
        contentPane.add(lblPlayer_2);
        
        JLabel lblPlayer = new JLabel("Player 1");
        lblPlayer.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
        lblPlayer.setBounds(188, 207, 311, 64);
        contentPane.add(lblPlayer);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(PlayersNicknames4.class.getResource("/images/Nicknames (1).png")));
        lblNewLabel.setBounds(0, 0, 938, 656);
        contentPane.add(lblNewLabel);
        
        
        Next.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String[] Playersname = new String[numberOfPlayers];
        		Playersname[0]=textField1.getText().trim();
        		Playersname[1]=textField2.getText().trim();
        		Playersname[2]=textField3.getText().trim();
        		Playersname[3]=textField4.getText().trim();
        		if(isValidString(Playersname[0]) && isValidString(Playersname[1]) && isValidString(Playersname[2])&& isValidString(Playersname[3])) {
                Color[] color = new Color[numberOfPlayers];
                color[0] = Color.GREEN;
                color[1] = Color.BLUE;
                color[2] = Color.RED;
                color[3] = Color.YELLOW;
                
                PlayersNicknames4.this.setVisible(false);
				new PlayerTurn(numberOfPlayers ,difficultyLevel , Playersname , color).setVisible(true);
        		
        	}else {
        		showMessage("Please enter a valid name in Player 2");
                return;
        	}}
        });
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
		textField3.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		    	if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
		            e.consume();  // ignore the event if it's not a letter or a control character
		        }}
		});
		textField4.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		    	if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
		            e.consume();  // ignore the event if it's not a letter or a control character
		        }}
		});
		
        
        }
	private boolean isValidString(String str) { // the checks if the name is valid - not null or empty value
        return str != null && !str.isEmpty();
    }
	 private void showMessage(String message) { //show message if the name is unvalid
	        JOptionPane.showMessageDialog(PlayersNicknames4.this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
	    }

}
