package View;

import java.awt.EventQueue;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import Controller.MangQuestionControl;
import Model.SysData;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;

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
        frame.setBounds(100, 100, 805, 405);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        txtuser = new JTextField();
        txtuser.setBounds(366, 71, 79, 19);
        frame.getContentPane().add(txtuser);
        txtuser.setColumns(10);

        btnNewButton = new JButton("Submit");
        btnNewButton.addActionListener(this);
     
        btnNewButton.setBounds(340, 196, 89, 23);
        frame.getContentPane().add(btnNewButton);

        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setBounds(298, 73, 29, 17);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Password");
        lblNewLabel_1.setBounds(264, 124, 63, 19);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Login for Admin");
        lblNewLabel_2.setForeground(Color.BLACK);
        lblNewLabel_2.setBounds(324, 25, 200, 21);
        frame.getContentPane().add(lblNewLabel_2);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(366, 123, 79, 20);
        frame.getContentPane().add(passwordField);
    }
    
    @Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton) {
		// TODO Auto-generated method stub
		try {		
        String entereduserName = txtuser.getText();
        String enteredPassword = passwordField.getText();
        if(entereduserName.equals("") || enteredPassword.equals("")) {
			throw new NullPointerException("return value is null!");
		}
        if (mangQuestionControl.validateAdminCredentials(entereduserName, enteredPassword)) {
            JOptionPane.showMessageDialog(null, "Login successful! Redirecting to admin page...");
            frame.setVisible(false);
         // Create an instance of QuestionManagment and display its frame
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        QuestionManagment questionManagement = new QuestionManagment();
                        questionManagement.frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Invalid email or password. Please try again.");
        }
		} catch(NullPointerException e1 ){
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
        
    }
 }

    public void showLoginScreen() {
        frame.setVisible(true);
    }
}
