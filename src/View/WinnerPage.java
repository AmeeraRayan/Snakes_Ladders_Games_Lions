package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Color;
import Model.Game;
import Model.Player;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class WinnerPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public WinnerPage(int index ,Game g) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 782);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		Game game = g ; 
		int i = index ; 
		Player winner = g.getPlayers().get(i);
		
		JLabel PlayerName = new JLabel("");
		PlayerName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 35));
		PlayerName.setForeground(new java.awt.Color(153, 0, 102));
		PlayerName.setBounds(66, 414, 845, 170);
		contentPane.add(PlayerName);
		
		
		JLabel PlayerObject = new JLabel("");
		PlayerObject.setIcon(new ImageIcon(WinnerPage.class.getResource("/images/BigBlue.png")));
		PlayerObject.setBounds(319, 106, 294, 417);
		contentPane.add(PlayerObject);
		WinnerPlayer( winner ,PlayerObject , PlayerName );
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(WinnerPage.class.getResource("/images/EndGAMEWinner.png")));
		lblNewLabel.setBounds(-266, -45, 1150, 742);
		contentPane.add(lblNewLabel);
	}
	 private void WinnerPlayer(Player winner  , JLabel PlayerObject , JLabel PlayerName   ) {
		 String path = "";
		 if(winner.getColor() == Color.BLUE) {
			 path =  "/images/BigBlue.png" ; 
			PlayerName.setForeground(new java.awt.Color(0, 0, 255));

		 }
		 if(winner.getColor() == Color.RED) {
			 path =  "/images/BigRed.png" ; 
				PlayerName.setForeground(new java.awt.Color(255, 0, 0));

			
		 }
		 if(winner.getColor() == Color.YELLOW) {
			 path =  "/images/BigYellow.png" ; 
			 PlayerName.setForeground(new java.awt.Color(255,255, 0));

		 }
		 if(winner.getColor() == Color.GREEN) {
			 path =  "/images/BigGreen.png" ; 
			 PlayerName.setForeground(new java.awt.Color(0,204, 0));


		 }
		 
		PlayerObject.setIcon(new ImageIcon(WinnerPage.class.getResource(path)));
		PlayerName.setText("The Winner of the game is : " + winner.getName());


	 }

}
 