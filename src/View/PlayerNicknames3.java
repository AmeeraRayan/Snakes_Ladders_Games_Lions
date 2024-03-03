package View;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.GameController;
import Model.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class PlayerNicknames3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
    private GameController gameController = new GameController(null);

	
	public PlayerNicknames3(int numberOfPlayers, String difficultyLevel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 984, 641);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		 // Make the frame undecorated (no title bar, no minimize/maximize/close buttons)
        setUndecorated(true);
		JButton Next = new JButton("Next");
        Next = new JButton("Next");
        Next.setIcon(new ImageIcon(DataReception.class.getResource("/View/images/NextButton.png")));
		Next.setBounds(813, 583, 150, 47);
		contentPane.add(Next);
		
		JButton Back = new JButton("Back");
		 Back.setIcon(new ImageIcon(DataReception.class.getResource("/View/images/BackButton.png")));
         Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	 PlayerNicknames3.this.setVisible(false);
 				new DataReception().setVisible(true);
                gameController.buttonClick();
             }
         });
		Back.setBounds(23, 581, 150, 50);
		contentPane.add(Back);
    
		
		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(603, 464, 177, 30);
		contentPane.add(textField3);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(451, 379, 186, 30);
		contentPane.add(textField2);
		
		textField1 = new JTextField();
		textField1.setBounds(263, 291, 177, 30);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Player 3");
		lblNewLabel_3.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(682, 403, 156, 30);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Player 2");
		lblNewLabel_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(497, 316, 156, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Player 1");
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(266, 233, 156, 30);
		contentPane.add(lblNewLabel_1);
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
		
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                gameController.buttonClick();
				String[] Playersname = new String[numberOfPlayers];
        		Playersname[0]=textField1.getText().trim();
        		Playersname[1]=textField2.getText().trim();
        		Playersname[2]=textField3.getText().trim();
        		if(isValidString(Playersname[0]) && isValidString(Playersname[1]) && isValidString(Playersname[2])&& !(Playersname[0].equals(Playersname[1]))&&!(Playersname[0].equals(Playersname[2]))&&!(Playersname[2].equals(Playersname[1])))  {
        		 Color[] color = new Color[numberOfPlayers];
                 color[0] = Color.GREEN;
                 color[1] = Color.BLUE;
                 color[2] = Color.RED;
                 PlayerNicknames3.this.setVisible(false);
 				new PlayerTurn(numberOfPlayers ,difficultyLevel , Playersname , color).setVisible(true);
			}else {
				showMessage("Please enter a valid name in Player 2");
                return;
			}
				
			
		}});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PlayerNicknames3.class.getResource("/View/images/pickplayer3.jpg")));
		lblNewLabel.setBounds(-51, -37, 1050, 678);
		contentPane.add(lblNewLabel);
	}
	private boolean isValidString(String str) {// the checks if the name is valid - not null or empty value
        return str != null && !str.isEmpty();
    }
	 private void showMessage(String message) {//show message if the name is unvalid
	        JOptionPane.showMessageDialog(PlayerNicknames3.this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
	    }

}
