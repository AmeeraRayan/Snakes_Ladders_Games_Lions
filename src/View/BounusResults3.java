package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Game;
import Model.Player;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;

public class BounusResults3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	public BounusResults3( String difficultyLevel,List<Player>  playersSortedByOrder) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Player's Turn: ");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_1.setBounds(101, 31, 354, 52);
		contentPane.add(lblNewLabel_1);
		
		JLabel Player3 = new JLabel(" "+playersSortedByOrder.get(2).getName());
		Player3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		Player3.setBounds(228, 402, 274, 72);
		contentPane.add(Player3);
		
		JLabel Player2 = new JLabel(" "+playersSortedByOrder.get(1).getName());
		Player2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		Player2.setBounds(235, 263, 246, 72);
		contentPane.add(Player2);
		
		JLabel Player1 = new JLabel(" " + playersSortedByOrder.get(0).getName());
		Player1.setBounds(240, 118, 262, 72);
		contentPane.add(Player1);
		Player1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
       
		JButton btnNewButton_1 = new JButton("Next");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game game = new Game(difficultyLevel, playersSortedByOrder);

            	   if(difficultyLevel.equals("Easy")) {   
                       new BoardEasyViewPlayers(game).setVisible(true);
                       BounusResults3.this.setVisible(false); 
                       }
                       if(difficultyLevel.equals("Medium")) {
                           new MediumGameBoard(game).setVisible(true);
                           BounusResults3.this.setVisible(false); 
                           }
                       if(difficultyLevel.equals("Hard")) {
                           new HardGameBoard(game).setVisible(true);
                           BounusResults3.this.setVisible(false); 
                           }
            }
        });
        btnNewButton_1.setBounds(556, 434, 105, 34);
        contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BounusResults3.class.getResource("/images/Bounus 11 (1).png")));
		lblNewLabel.setBounds(-159, 0, 1106, 640);
		contentPane.add(lblNewLabel);
	}
}
