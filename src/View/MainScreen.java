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
        btnNewButton.setBounds(134, 40, 154, 30);
        frame.getContentPane().add(btnNewButton);


        JButton btnNewButton_1 = new JButton("Management Question");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showQuestionDialog();
            }
        });
        btnNewButton_1.setBounds(134, 101, 154, 30);
        frame.getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Game History");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add your logic for "Game History" button
            }
        });
        btnNewButton_2.setBounds(134, 173, 154, 30);
        frame.getContentPane().add(btnNewButton_2);
    }



    private void showQuestionDialog() {
        boolean isAdmin = true; // Replace this with your admin check logic

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
