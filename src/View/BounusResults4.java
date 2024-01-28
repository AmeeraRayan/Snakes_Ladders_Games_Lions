package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Player;

public class BounusResults4 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public BounusResults4( List<Player> playersSortedByOrder) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 832, 644);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel Player4 = new JLabel(playersSortedByOrder.get(3).getName());
		Player4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		Player4.setBounds(177, 439, 251, 72);
		contentPane.add(Player4);
		
		JLabel Player3 = new JLabel(playersSortedByOrder.get(2).getName());
		Player3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		Player3.setBounds(177, 312, 227, 72);
		contentPane.add(Player3);
		
		JLabel Player2 = new JLabel(playersSortedByOrder.get(1).getName());
		Player2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		Player2.setBounds(177, 209, 227, 72);
		contentPane.add(Player2);
		
		JLabel Player1 = new JLabel(playersSortedByOrder.get(0).getName());
		Player1.setBounds(177, 102, 295, 72);
		contentPane.add(Player1);
		Player1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BounusResults4.class.getResource("/images/Bounus 12.png")));
		lblNewLabel.setBounds(-185, 0, 1106, 640);
		contentPane.add(lblNewLabel);
	}

}
