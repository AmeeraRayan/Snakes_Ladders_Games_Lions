package View;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import Controller.GameController;
import Controller.MangQuestionControl;
import Model.SysData;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.Font;

public class LogIn extends JFrame implements ActionListener{

    private JFrame frame;
    private JTextField txtuser;
    private GameController gameController = new GameController(null);

	public JTextField getTxtuser() {
		return txtuser;
	}

	public void setTxtuser(JTextField txtuser) {
		this.txtuser = txtuser;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}


	private JButton btnNewButton;
    static  SysData sysData = new SysData();
    static MangQuestionControl mangQuestionControl=new MangQuestionControl();
    public JPasswordField passwordField;

    public LogIn() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 899, 528);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        // Make the frame undecorated (no title bar, no minimize/maximize/close buttons)
        frame.setUndecorated(true);

        txtuser = new JTextField();
        txtuser.setBackground(new Color(255, 255, 255));
        txtuser.setBounds(234, 209, 142, 23);
        frame.getContentPane().add(txtuser);
        txtuser.setColumns(10);

        btnNewButton = new JButton("Submit");
        btnNewButton.setSelectedIcon(null);
        btnNewButton.setIcon(new ImageIcon(LogIn.class.getResource("/View/images/5374040.png")));
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
        lblNewLabel_3.setIcon(new ImageIcon(LogIn.class.getResource("/View/images/Screenshot 2024-01-29 140544.png")));
        lblNewLabel_3.setBounds(0, 0, 921, 527);
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
        	gameController.buttonClick();
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
