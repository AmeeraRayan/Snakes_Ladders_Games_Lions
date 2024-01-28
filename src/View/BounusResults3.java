package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Player;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class BounusResults3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	public BounusResults3( List<Player> playersSortedByOrder) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Player3 = new JLabel(playersSortedByOrder.get(2).getName());
		Player3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		Player3.setBounds(207, 392, 197, 72);
		contentPane.add(Player3);
		
		JLabel Player2 = new JLabel(playersSortedByOrder.get(1).getName());
		Player2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		Player2.setBounds(207, 260, 197, 72);
		contentPane.add(Player2);
		
		JLabel Player1 = new JLabel(playersSortedByOrder.get(0).getName());
		Player1.setBounds(207, 119, 197, 72);
		contentPane.add(Player1);
		Player1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BounusResults3.class.getResource("/images/Bounus 11 (1).png")));
		lblNewLabel.setBounds(-231, 0, 1106, 640);
		contentPane.add(lblNewLabel);
	}
}
