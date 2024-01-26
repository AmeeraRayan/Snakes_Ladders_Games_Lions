package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayerNicknames3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;

	
	public PlayerNicknames3(int numberOfPlayers, String difficultyLevel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 937, 618);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Next = new JButton("Next");
		
		Next.setBounds(695, 506, 143, 41);
		contentPane.add(Next);
		
		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(560, 443, 220, 30);
		contentPane.add(textField3);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(363, 329, 232, 30);
		contentPane.add(textField2);
		
		textField1 = new JTextField();
		textField1.setBounds(115, 212, 208, 30);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Player 3");
		lblNewLabel_3.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(560, 403, 156, 30);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Player 2");
		lblNewLabel_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(363, 289, 156, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Player 1");
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(115, 172, 156, 30);
		contentPane.add(lblNewLabel_1);
		
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] Playersname = new String[numberOfPlayers];
        		Playersname[0]=textField1.getText();
        		Playersname[1]=textField2.getText();
        		Playersname[2]=textField3.getText();
        		 Color[] color = new Color[numberOfPlayers];
                 color[0] = Color.GREEN;
                 color[1] = Color.BLUE;
                 color[2] = Color.RED;
                 PlayerNicknames3.this.setVisible(false);
 				new PlayerTurn(numberOfPlayers ,difficultyLevel , Playersname , color).setVisible(true);
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PlayerNicknames3.class.getResource("/images/Nickname3.png")));
		lblNewLabel.setBounds(-24, 0, 1050, 581);
		contentPane.add(lblNewLabel);
	}

}