package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExitConfirmationDialog extends JDialog {
    private boolean confirmed = false;

    public ExitConfirmationDialog(Frame parent) {
        super(parent, "Confirmation", true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some padding
        
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS)); // Vertical layout
        
        JLabel label = new JLabel("<html>Are you sure you want to exit to the main screen?<br/><br/>");
        label.setFont(new Font("Arial", Font.BOLD, 18)); // Change the font to bold and size 18
        label.setForeground(Color.BLACK); // Change the text color
        textPanel.add(label);
        
        JLabel label_1 = new JLabel("<html>If you exit, the game won't be saved in the history!<br/><br/>");
        label_1.setFont(new Font("Arial", Font.BOLD, 18)); // Change the font to bold and size 18
        label_1.setForeground(Color.RED); // Change the text color
        textPanel.add(label_1);
        
        panel.add(textPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // Use GridLayout for buttons with some spacing
        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                dispose();
            }
        });
        buttonPanel.add(yesButton);
        JButton noButton = new JButton("No");
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                dispose();
            }
        });
        buttonPanel.add(noButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
        setSize(400, 300); // Increase the size of the dialog
        setLocationRelativeTo(parent);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Enable the X button
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
