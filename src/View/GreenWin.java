package View;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import Model.Game;
import Model.Player;
import Model.WinFrame;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GreenWin extends JFrame  implements WinFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel_2;
	private String plyerNickname;
	private String time;
	private Game game;

	public GreenWin() {
		
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 894, 596);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel playerWin = new JLabel(plyerNickname);
		playerWin.setBounds(415, 321, 215, 36);
		playerWin.setForeground(new Color(255, 255, 255));
		playerWin.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 34));
		contentPane.add(playerWin);
		
		JLabel lblNewLabe2 = new JLabel("Time is : ");
		lblNewLabe2.setBounds(64, 216, 140, 28);
		lblNewLabe2.setForeground(new Color(255, 255, 255));
		lblNewLabe2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		contentPane.add(lblNewLabe2);
		
		JLabel lblNewLabel_3 = new JLabel(time);
		lblNewLabel_3.setBounds(230, 216, 170, 28);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 28));
		contentPane.add(lblNewLabel_3);
		
		
		JButton btnNewButton = new JButton("Play again?");
		btnNewButton.setForeground(new Color(58, 173, 62));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Player player: game.getPlayers())
				{
					player.setPosition(1);
				}
			new BoardEasyViewPlayers(game).setVisible(true);
			GreenWin.this.setVisible(false);
}
		});
		btnNewButton.setBounds(155, 487, 100, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Menu");
		btnNewButton_1.setForeground(new Color(58, 173, 62));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					new DataReception().setVisible(true);
					 GreenWin.this.setVisible(false);

			}
		});
		btnNewButton_1.setBounds(643, 497, 85, 21);
		contentPane.add(btnNewButton_1);
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2 = new JLabel(new ImageIcon(getClass().getResource("/images/GreenWin.png")));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(-43, 0, 960, 560);
		contentPane.add(lblNewLabel_2);
			
	}


	@Override
	public void createWinFrame(String winnerName, String time, Game game) {
		this.plyerNickname=winnerName;
		this.game=game;
		this.time=time;		
	}
}
