package View;


import java.awt.EventQueue;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
public class MainScreen extends JFrame{

    private MainScreen frame ;
    public JButton btnNewButton_1;

    public MainScreen() {
    	frame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 486);
        getContentPane().setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(755, 25, 25, 15);
        getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setBounds(547, 131, 200, 30);
        getContentPane().add(lblNewLabel_2);
        lblNewLabel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	MainScreen.this.setVisible(false);
				new DataReception().setVisible(true);
            }
        });
        
        
        lblNewLabel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Instructions instructionsFrame = new Instructions();
                instructionsFrame.setVisible(true);
                MainScreen.this.setVisible(false); //
            }
        });
       
        JLabel lblNewLabel_2_1 = new JLabel("");
        lblNewLabel_2_1.setBounds(547, 222, 200, 35);
        getContentPane().add(lblNewLabel_2_1);
        lblNewLabel_2_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showQuestionDialog();

            }
        });
        
        
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setBounds(545, 309, 200, 40);
        getContentPane().add(lblNewLabel_3);
        lblNewLabel_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	MainScreen.this.setVisible(false);
                Game_History gameHistoryScreen = new Game_History();
                gameHistoryScreen.setVisible(true);  
            }
        });
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/MainScreen.png")));
        lblNewLabel.setBounds(0,0, 856, 465);
        getContentPane().add(lblNewLabel);
          
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
