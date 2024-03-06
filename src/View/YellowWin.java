package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import Controller.GameController;
import Model.Game;
import Model.Player;
import Model.WinFrame;

import javax.swing.JLabel;
import java.awt.Color;

public class YellowWin extends JFrame  implements WinFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JLabel lblNewLabel_2;
	private String plyerNickname;
	private String time;
	private Game game;
    private GameController gameController = new GameController(game);


	/**
	 * Create the frame.
	 */
	public YellowWin(String winnerName, String time,Game game) {
		this.plyerNickname=winnerName;
		this.time=time;
		this.game=game;
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 // Make the frame undecorated (no title bar, no minimize/maximize/close buttons)
        setUndecorated(true);
		setBounds(0, 0, 1037, 596);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel playerWin = new JLabel(plyerNickname);
		playerWin.setBounds(316, 279, 262, 36);
		playerWin.setForeground(new Color(255, 255, 255));
		playerWin.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 36));
		contentPane.add(playerWin);
		
		JLabel lblNewLabel_3 = new JLabel(time);
		lblNewLabel_3.setBounds(316, 396, 222, 49);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 36));
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1 = new JLabel(new ImageIcon(YellowWin.class.getResource("/images/Win_yellow.jpg")));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(-64, -20, 1171, 636);
		contentPane.add(lblNewLabel_1);
		
		JLabel restart = new JLabel("");
		restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	for(Player player: game.getPlayers())
				{
					player.setPosition(1);
				}
				YellowWin.this.setVisible(false);
				if(game.getDifficulty() == "Easy") {
				new BoardEasyViewPlayers(game).setVisible(true);
				}
				else if(game.getDifficulty().equals("Medium")) {
					new MediumGameBoard(game).setVisible(true);
				}
				else if(game.getDifficulty().equals("Hard")) {
					new HardGameBoard(game).setVisible(true);
				}
				gameController.buttonClick();
			}
        });
		restart.setBounds(72, 518, 163, 32);
		contentPane.add(restart);
		
		JLabel menu = new JLabel("");
		menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	YellowWin.this.setVisible(false);
				new MainScreen().setVisible(true);
				gameController.buttonClick();
			}
        });
		menu.setBounds(845, 518, 163, 32);
		contentPane.add(menu);
		
		
	}
	@Override
	public void createWinFrame(String winnerName, String time, Game game) {
		this.plyerNickname=winnerName;
		this.game=game;
		this.time=time;	
		new YellowWin(winnerName,time,game).setVisible(true);

	}
}