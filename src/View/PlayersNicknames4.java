package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Model.Color;
import Model.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class PlayersNicknames4 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField[] textFields;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JButton button;
    private JButton Next;

    public PlayersNicknames4(int numberOfPlayers, String difficultyLevel) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 938, 693);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        Next = new JButton("Next");
      
        Next.setBounds(712, 518, 135, 52);
        contentPane.add(Next);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(524, 258, 200, 32);
        contentPane.add(textField_3);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(524, 403, 200, 32);
        contentPane.add(textField_2);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(188, 403, 200, 32);
        contentPane.add(textField_1);
        
        textField = new JTextField();
        textField.setBounds(188, 258, 200, 32);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JLabel lblPlayer_2_2_1 = new JLabel("Player 4");
        lblPlayer_2_2_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
        lblPlayer_2_2_1.setBounds(524, 346, 311, 64);
        contentPane.add(lblPlayer_2_2_1);
        
        JLabel lblPlayer_2_2 = new JLabel("Player 3");
        lblPlayer_2_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
        lblPlayer_2_2.setBounds(188, 356, 311, 64);
        contentPane.add(lblPlayer_2_2);
        
        JLabel lblPlayer_2 = new JLabel("Player 2");
        lblPlayer_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
        lblPlayer_2.setBounds(524, 207, 311, 64);
        contentPane.add(lblPlayer_2);
        
        JLabel lblPlayer = new JLabel("Player 1");
        lblPlayer.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
        lblPlayer.setBounds(188, 207, 311, 64);
        contentPane.add(lblPlayer);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(PlayersNicknames4.class.getResource("/images/Nicknames (1).png")));
        lblNewLabel.setBounds(0, 0, 938, 656);
        contentPane.add(lblNewLabel);
        
        button = new JButton("New button");
        button.setBounds(750, 518, 85, 21);
        contentPane.add(button);
        
        Next.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String[] Playersname = new String[numberOfPlayers];
        		Playersname[0]=textField.getText();
        		Playersname[1]=textField_1.getText();
        		Playersname[2]=textField_2.getText();
        		Playersname[3]=textField_3.getText();
                Color[] color = new Color[numberOfPlayers];
                color[0] = Color.GREEN;
                color[1] = Color.BLUE;
                color[2] = Color.RED;
                color[3] = Color.YELLOW;
                
                PlayersNicknames4.this.setVisible(false);
				new PlayerTurn(numberOfPlayers ,difficultyLevel , Playersname , color).setVisible(true);
        		
        	}
        });
        }
}
