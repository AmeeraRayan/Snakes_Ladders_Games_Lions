package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.GameController;
import Model.Game;
import Model.Player;
import java.awt.Color;

public class BounusResults4 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private GameController gameController = new GameController(null);

	public BounusResults4( String difficultyLevel, List<Player> playersSortedByOrder) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 859, 673);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		// Make the frame undecorated (no title bar, no minimize/maximize/close buttons)
        setUndecorated(true);
		JLabel Player4 = new JLabel(" "+playersSortedByOrder.get(3).getName());
		Player4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		Player4.setBounds(347, 492, 251, 72);
		contentPane.add(Player4);
		
		JLabel Player3 = new JLabel(" "+playersSortedByOrder.get(2).getName());
		Player3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		Player3.setBounds(343, 381, 227, 72);
		contentPane.add(Player3);
		
		JLabel Player2 = new JLabel(" "+playersSortedByOrder.get(1).getName());
		Player2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		Player2.setBounds(343, 279, 227, 72);
		contentPane.add(Player2);
		
		JLabel Player1 = new JLabel(" "+playersSortedByOrder.get(0).getName());
		Player1.setBounds(345, 167, 295, 72);
		contentPane.add(Player1);
		Player1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		JButton btnNewButton_1 = new JButton("Next");
		 btnNewButton_1.setIcon(new ImageIcon(BounusResults2.class.getResource("/View/images/NextButton.png")));
	        btnNewButton_1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	gameController.buttonClick();
            	 Game game = new Game(difficultyLevel, playersSortedByOrder);

          	   if(difficultyLevel.equals("Easy")) {   
                     new BoardEasyViewPlayers(game).setVisible(true);
                     BounusResults4.this.setVisible(false); 
                     }
                     if(difficultyLevel.equals("Medium")) {
                         new MediumGameBoard(game).setVisible(true);
                         BounusResults4.this.setVisible(false); 
                         }
                     if(difficultyLevel.equals("Hard")) {
                         new HardGameBoard(game).setVisible(true);
                         BounusResults4.this.setVisible(false); 
                         }
            }
        });
        btnNewButton_1.setBounds(713, 601, 136, 42);
        contentPane.add(btnNewButton_1);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BounusResults4.class.getResource("/View/images/bonus4.jpg")));
		lblNewLabel.setBounds(-55, -23, 1242, 746);
		contentPane.add(lblNewLabel);
	}

}
