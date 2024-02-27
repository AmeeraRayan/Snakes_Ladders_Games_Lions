package View;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Game;
import Model.Player;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import java.awt.Color;

public class BounusResults2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public BounusResults2(String difficultyLevel,List<Player>  playersSortedByOrder) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Player's Turn: ");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel_1.setBounds(138, 60, 354, 52);
		contentPane.add(lblNewLabel_1);
		
		JLabel PlayerTurnOne = new JLabel(" "+playersSortedByOrder.get(0).getName());
		PlayerTurnOne.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 34));
		PlayerTurnOne.setBounds(232, 162, 243, 58);
		contentPane.add(PlayerTurnOne);
		
		JLabel PlayerTurnTwo = new JLabel(" "+playersSortedByOrder.get(1).getName());
		PlayerTurnTwo.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 34));
		PlayerTurnTwo.setBounds(232, 306, 243, 58);
		contentPane.add(PlayerTurnTwo);
        JButton btnNewButton_1 = new JButton("Next");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game game = new Game(difficultyLevel, playersSortedByOrder);
                if(difficultyLevel.equals("Easy")) {
                new BoardEasyViewPlayers(game).setVisible(true);
                BounusResults2.this.setVisible(false); 
                }
                if(difficultyLevel.equals("Medium")) {
                    new MediumGameBoard(game).setVisible(true);
                    BounusResults2.this.setVisible(false); 
                    }
                if(difficultyLevel.equals("Hard")) {
                    new HardGameBoard(game).setVisible(true);
                    BounusResults2.this.setVisible(false); 
                    }
            }
        });


        btnNewButton_1.setBounds(556, 434, 105, 34);
        contentPane.add(btnNewButton_1);
		 
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BounusResults2.class.getResource("/images/2Results.png")));
		lblNewLabel.setBounds(-108, -38, 1043, 669);
		contentPane.add(lblNewLabel);
		 

	}
}