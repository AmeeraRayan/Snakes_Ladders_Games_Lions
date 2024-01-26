package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayerNicknames2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField2;
	private JTextField textField1;
	private final JLabel lblNewLabel_1 = new JLabel("Player 1");


	/**
	 * Create the frame.
	 */
	public PlayerNicknames2(int numberOfPlayers, String difficultyLevel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1019, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Next = new JButton("Next");
		
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
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] Playersname = new String[numberOfPlayers];
        		Playersname[0]=textField1.getText();
        		Playersname[1]=textField2.getText();
        		 Color[] color = new Color[numberOfPlayers];
                 color[0] = Color.GREEN;
                 color[1] = Color.BLUE;
                 PlayerNicknames2.this.setVisible(false);
 				new PlayerTurn(numberOfPlayers ,difficultyLevel , Playersname , color).setVisible(true);
        		
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PlayerNicknames2.class.getResource("/images/Nicknames2.png")));
		lblNewLabel.setBounds(0, -76, 1005, 737);
		contentPane.add(lblNewLabel);
	}

}
