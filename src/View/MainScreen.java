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
		setBounds(100, 100, 845, 486);

        JButton btnNewButton = new JButton("Start Game");

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				MainScreen.this.setVisible(false);
				new DataReception().setVisible(true);
            }
        });
        getContentPane().setLayout(null);
        btnNewButton.setBounds(587, 113, 178, 54);
        frame.getContentPane().add(btnNewButton);


         btnNewButton_1 = new JButton("Management Question");
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
            	MainScreen.this.setVisible(false);
                GameHistory gameHistoryScreen = new GameHistory();
                gameHistoryScreen.setVisible(true);           	
            }
        });
        btnNewButton_2.setBounds(587, 211, 178, 54); // Reduced width and height
        frame.getContentPane().add(btnNewButton_2);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/images/Start.png")));
        lblNewLabel.setBounds(-188, -21, 1184, 481);
        getContentPane().add(lblNewLabel);
    }
 // Method to parse JSON file and return a list of GameDetails
    public List<GameDetails> getGameHistory() {
        Gson gson = new Gson();
        java.lang.reflect.Type gameListType = new TypeToken<ArrayList<GameDetails>>(){}.getType();
        List<GameDetails> gameList = new ArrayList<GameDetails>();

        // Try to read from the file
        try (FileReader reader = new FileReader("src/game_history.json")) {
            gameList = gson.fromJson(reader, gameListType);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error or return an empty list
        }
        return gameList;
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
