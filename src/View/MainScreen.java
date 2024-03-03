package View;


import java.awt.EventQueue;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Controller.GameController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
// ... other necessary imports



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.JLabel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;
//Add this import to handle JSON parsing
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;


//Import for file handling
import java.io.FileReader;

//Import for GUI components
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import Model.GameDetails;
import Model.Sound;
public class MainScreen extends JFrame{

    private MainScreen frame ;
    public JButton btnNewButton_1;
    private GameController gameController = new GameController(null);

    public MainScreen() {
    	frame=this;
 		//Sound sound = new Sound("src/Sound/start.wav");
 		Sound sound = new Sound("Sound/start.wav");
 		sound.play();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1166, 687);
        getContentPane().setLayout(null);
        
        // Make the frame undecorated (no title bar, no minimize/maximize/close buttons)
        setUndecorated(true);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(1073, 21, 55, 59);
        getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setBounds(471, 249, 200, 30);
        getContentPane().add(lblNewLabel_2);
        lblNewLabel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	MainScreen.this.setVisible(false);
				new DataReception().setVisible(true);
				gameController.buttonClick();
            }
        });
        
        
        lblNewLabel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Instructions instructionsFrame = new Instructions();
                instructionsFrame.setVisible(true);
                MainScreen.this.setVisible(false); //
				gameController.buttonClick();

            }
        });
       
        JLabel lblNewLabel_2_1 = new JLabel("");
        lblNewLabel_2_1.setBounds(471, 385, 200, 35);
        getContentPane().add(lblNewLabel_2_1);
        lblNewLabel_2_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showQuestionDialog();
				gameController.buttonClick();

            }
        });
        
        
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setBounds(471, 514, 200, 40);
        getContentPane().add(lblNewLabel_3);
        lblNewLabel_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	MainScreen.this.setVisible(false);
                Game_History gameHistoryScreen = new Game_History();
                gameHistoryScreen.setVisible(true); 
				gameController.buttonClick();

            }
        });
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/MainScreen.jpg")));
        lblNewLabel.setBounds(0,-41, 1470, 769);
        getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setBounds(960, 11, 68, 69);
        getContentPane().add(lblNewLabel_4);
        lblNewLabel_4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	MainScreen.this.setVisible(false); 
				gameController.buttonClick();
            }
        });
          
    }
 


    private void showQuestionDialog() {
        boolean isAdmin = true;

        if (isAdmin) {
            int choice = JOptionPane.showConfirmDialog(null, "To enter the Mangment Question you have to be an admin with a LogIn",
                    "Confirmation", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                // Open the admin login screen
                LogIn logIn = new LogIn();
                logIn.showLoginScreen();
                this.setVisible(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "You do not have permission to access this feature.");
        }
    }
}
