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
import java.awt.Color;

public class BounusResults4 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public BounusResults4( List<Player> playersSortedByOrder) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 673);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Player Turn's ");
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_1.setBounds(207, 26, 354, 52);
		contentPane.add(lblNewLabel_1);
		JLabel Player4 = new JLabel(" "+playersSortedByOrder.get(3).getName());
		Player4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		Player4.setBounds(282, 465, 251, 72);
		contentPane.add(Player4);
		
		JLabel Player3 = new JLabel(" "+playersSortedByOrder.get(2).getName());
		Player3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		Player3.setBounds(282, 357, 227, 72);
		contentPane.add(Player3);
		
		JLabel Player2 = new JLabel(" "+playersSortedByOrder.get(1).getName());
		Player2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		Player2.setBounds(282, 230, 227, 72);
		contentPane.add(Player2);
		
		JLabel Player1 = new JLabel(" "+playersSortedByOrder.get(0).getName());
		Player1.setBounds(282, 114, 295, 72);
		contentPane.add(Player1);
		Player1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BounusResults4.class.getResource("/images/Bounus 12.png")));
		lblNewLabel.setBounds(-130, -11, 1141, 746);
		contentPane.add(lblNewLabel);
	}

}
