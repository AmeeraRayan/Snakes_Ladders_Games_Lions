package View;
import javax.swing.*;
import java.awt.*;

public class MediumBoard extends JFrame {
    
    private static final int GRID_SIZE = 10; // 10x10 grid for the game board

    public MediumBoard() {
        setTitle("Snake and Ladder Game");
        getContentPane().setLayout(new BorderLayout());

        // Create the game board panel
        JPanel gameBoardPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        initializeBoard(gameBoardPanel);

        // Set a preferred size for the game board panel
        gameBoardPanel.setPreferredSize(new Dimension(600, 600));

        // Create the dice panel
        JPanel dicePanel = new JPanel();
        JLabel diceLabel = new JLabel("Dice: [Value]");
        dicePanel.add(diceLabel);

        // Adding panels to the frame
        getContentPane().add(gameBoardPanel, BorderLayout.CENTER);
        getContentPane().add(dicePanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Adjust the frame size based on its content
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private void initializeBoard(JPanel panel) {
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            JPanel cell = new JPanel();
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(String.valueOf(i + 1), SwingConstants.CENTER);
            cell.add(label);
            panel.add(cell);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MediumBoard();
            }
        });
    }
}
