package View;

import java.awt.EventQueue;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.FlowLayout;

public class MainScreen extends JFrame{

    private MainScreen frame ;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainScreen window = new MainScreen();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainScreen() {
    	frame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 845, 486);

        JButton btnNewButton = new JButton("Start Game");

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				MainScreen.this.setVisible(false);
				new DataReception().setVisible(true);
            }
        });
        getContentPane().setLayout(null);
        btnNewButton.setBounds(587, 93, 178, 54);
        frame.getContentPane().add(btnNewButton);


        JButton btnNewButton_1 = new JButton("Management Question");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showQuestionDialog();
            }
        });
        btnNewButton_1.setBounds(587, 303, 178, 54);
        frame.getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Game History");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add your logic for "Game History" button
            }
        });
        btnNewButton_2.setBounds(587, 199, 178, 54); // Reduced width and height
        frame.getContentPane().add(btnNewButton_2);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/Start.png")));
        lblNewLabel.setBounds(-188, -21, 1184, 481);
        getContentPane().add(lblNewLabel);
    }



    private void showQuestionDialog() {
        boolean isAdmin = true;

        if (isAdmin) {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to proceed as an admin?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                // Open the admin login screen
                LogIn logIn = new LogIn();
                logIn.showLoginScreen();
            }
        } else {
            JOptionPane.showMessageDialog(null, "You do not have permission to access this feature.");
        }
    }
}
