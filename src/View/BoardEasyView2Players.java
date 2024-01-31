package View;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Game;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class BoardEasyView2Players extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Game game;
	public BoardEasyView2Players(Game game) {
		this.game=game;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("images/bardeasy2.png"));
		lblNewLabel.setBounds(-24, -94, 1063, 752);
		contentPane.add(lblNewLabel);
	}
}
