package View;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import Model.SysData;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

public class LogIn {

    private JFrame frame;
    private JTextField txtId;
    private JTextField textField;
    static  SysData sysData = new SysData();


    public LogIn() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        txtId = new JTextField();
        txtId.setBounds(146, 60, 79, 19);
        frame.getContentPane().add(txtId);
        txtId.setColumns(10);

        JButton btnNewButton = new JButton("Submit");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnNewButton.addActionListener(new ActionListener() {
        		    public void actionPerformed(ActionEvent e) {
        		        // Hide the current window
        		        frame.setVisible(false);

        		        // Show the QuestionManagement screen
        		        QuestionManagment questionManagement = new QuestionManagment();
        		        questionManagement.frame.setVisible(true);
        		    }
        		});
        	}
        });
        btnNewButton.setBounds(136, 156, 89, 23);
        frame.getContentPane().add(btnNewButton);

        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setBounds(93, 62, 29, 17);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Password");
        lblNewLabel_1.setBounds(80, 103, 49, 14);
        frame.getContentPane().add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(146, 100, 79, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Login for Admin");
        lblNewLabel_2.setBounds(146, 28, 79, 14);
        frame.getContentPane().add(lblNewLabel_2);
    }
    
    private void validateLogin() {
        String enteredEmail = txtId.getText();
        String enteredPassword = textField.getText();

        if (sysData.validateAdminCredentials(enteredEmail, enteredPassword)) {
            JOptionPane.showMessageDialog(null, "Login successful! Redirecting to admin page...");
            frame.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid email or password. Please try again.");
        }
    }

    public void showLoginScreen() {
        frame.setVisible(true);
    }
}
