package View;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import Controller.MangQuestionControl;
import Model.SysData;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class LogIn extends JFrame implements ActionListener{

    private JFrame frame;
    private JTextField txtuser;
	private JButton btnNewButton;
    static  SysData sysData = new SysData();
    static MangQuestionControl mangQuestionControl=new MangQuestionControl();
    private JPasswordField passwordField;

    public LogIn() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 902, 564);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        txtuser = new JTextField();
        txtuser.setBackground(new Color(255, 255, 255));
        txtuser.setBounds(234, 203, 142, 23);
        frame.getContentPane().add(txtuser);
        txtuser.setColumns(10);

        btnNewButton = new JButton("Submit");
        btnNewButton.setSelectedIcon(null);
        btnNewButton.setIcon(new ImageIcon(LogIn.class.getResource("/images/5374040.png")));
        btnNewButton.setFont(new Font("David", Font.BOLD, 20));
        btnNewButton.setBackground(new Color(250, 255, 127));
        btnNewButton.addActionListener(this);
        btnNewButton.setBounds(671, 394, 181, 49);
        frame.getContentPane().add(btnNewButton);
        
        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(255, 255, 255));
        passwordField.setBounds(234, 283, 142, 23);
        frame.getContentPane().add(passwordField);
        
        JLabel lblNewLabel_3 = new JLabel(" ");
        lblNewLabel_3.setBackground(new Color(255, 140, 0));
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_3.setIcon(new ImageIcon(LogIn.class.getResource("/images/Screenshot 2024-01-29 140544.png")));
        lblNewLabel_3.setBounds(0, 0, 888, 527);
        frame.getContentPane().add(lblNewLabel_3);
        
    }
    
    public boolean validateLogin(String enteredUserName, String enteredPassword) {
        if(enteredUserName == null || enteredPassword == null) {
            throw new NullPointerException("Username or password is null.");
        }
         return mangQuestionControl.validateAdminCredentials(enteredUserName, enteredPassword);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnNewButton) {
            String enteredUserName = txtuser.getText();
            String enteredPassword = new String(passwordField.getPassword()); // Use getPassword() for JPasswordField
            try {
                if (validateLogin(enteredUserName, enteredPassword)) {
                    // Login successful
                    JOptionPane.showMessageDialog(null, "Login successful! Redirecting to admin page...");
                    this.frame.setVisible(false);
                    QuestionManagment questionManagement = new QuestionManagment();
                    questionManagement.frame.setVisible(true); 
                    
                } else {
                    // Login failed
                    JOptionPane.showMessageDialog(null, "Invalid email or password. Please try again.");
                }
            } catch(NullPointerException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
            }
        }
    }


    public void showLoginScreen() {
        frame.setVisible(true);
    }
}
