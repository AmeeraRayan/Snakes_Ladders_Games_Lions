package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExitConfirmationDialog extends JDialog {
    private boolean confirmed = false;

    public ExitConfirmationDialog(Frame parent) {
        super(parent, "Confirmation", true);
        JPanel panel = new JPanel();
        // Make the frame undecorated (no title bar, no minimize/maximize/close buttons)
        setUndecorated(true);
        JLabel label = new JLabel("<html>Are you sure you want to exit to the main screen?<br/><br/>If you exit, the game won't be saved in the history!</html>");
        label.setFont(new Font("Arial", Font.BOLD, 18)); // Change the font to bold and size 16
        panel.add(label);
        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                dispose();
            }
        });
        panel.add(yesButton);
        JButton noButton = new JButton("No");
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                dispose();
            }
        });
        panel.add(noButton);
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(parent);
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
