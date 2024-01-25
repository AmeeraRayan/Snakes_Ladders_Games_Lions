package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class MainScreen {

    private JFrame frame;

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
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnNewButton = new JButton("Start Game");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 // Open the DifficultySelectionScreen when "Start Game" is clicked
                DataReception.createAndShowGUI();
                // Close the current frame if needed
                frame.dispose();            }
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
            	LogIn logIn=new LogIn();
                logIn.showLoginScreen();
            }
        } else {
            JOptionPane.showMessageDialog(null, "You do not have permission to access this feature.");
        }
    }


}