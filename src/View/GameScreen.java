package View;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class GameScreen extends JFrame {
	private static final int GRID_SIZE = 10;
	private static final int Board_SIZE = 10;
	private static final Color[] COLORS = new Color[]{Color.BLUE, Color.WHITE, Color.YELLOW, Color.RED, Color.GREEN};
	private Color[][] boardColors = new Color[GRID_SIZE][GRID_SIZE];


    public GameScreen() {
            setTitle("Game Board");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(749, 652);

            JPanel outerPanel = new JPanel();
            outerPanel.setLayout(null);

            JPanel innerPanel = new JPanel();
            innerPanel.setBounds(120, 65, 500, 500);
            initializeBoard(innerPanel);
            innerPanel.setBackground(Color.WHITE);

            outerPanel.add(innerPanel);
            innerPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

            // Adding the snake image above the board
            ImageIcon snakeImageIcon = new ImageIcon("/images/Snakeforgame.png");
            JLabel snakeLabel = new JLabel(snakeImageIcon);
            snakeLabel.setBounds(127, 10, 50, 100);
            outerPanel.add(snakeLabel);

            getContentPane().add(outerPanel);
            
            JLabel lblNewLabel = new JLabel("");
            lblNewLabel.setBounds(6, 205, 104, 50);
            outerPanel.add(lblNewLabel);
            lblNewLabel.setIcon(new ImageIcon(GameScreen.class.getResource("/images/Vector 208.png")));
            setVisible(true);
        
    }
    private void initializeBoard(JPanel panel) {
        Random rand = new Random();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int cellNumber = i * GRID_SIZE + (i % 2 == 0 ? j : GRID_SIZE - 1 - j);
                cellNumber = GRID_SIZE * GRID_SIZE - cellNumber;

                JPanel cell = new JPanel(new BorderLayout());
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JLabel label = new JLabel(String.valueOf(cellNumber), SwingConstants.CENTER);
                cell.add(label, BorderLayout.CENTER);

                // Get a color for the cell that is not equal to the adjacent cells
                cell.setBackground(getUniqueColor(i, j));

                // Update the board colors array with the new color for reference
                boardColors[i][j] = cell.getBackground();

                // Make the text color contrast with the background
                label.setForeground(getContrastColor(cell.getBackground()));

                panel.add(cell);
            }
        }
    }

    // Get a color that is different from the adjacent cells
    private Color getUniqueColor(int row, int col) {
        List<Color> availableColors = new ArrayList<>(Arrays.asList(COLORS));
        // Remove colors from the list that are the same as adjacent cells
        if (row > 0) { availableColors.remove(boardColors[row - 1][col]); } // Check cell above
        if (col > 0) { availableColors.remove(boardColors[row][col - 1]); } // Check cell to the left
        if (row > 0 && col > 0) { availableColors.remove(boardColors[row - 1][col - 1]); } // Check upper left diagonal
        if (row > 0 && col < GRID_SIZE - 1) { availableColors.remove(boardColors[row - 1][col + 1]); } // Check upper right diagonal

        // Randomly select from the remaining colors
        return availableColors.get(new Random().nextInt(availableColors.size()));
    }

    // Method to get a contrasting color (black or white) based on the background color for readability
    private Color getContrastColor(Color color) {
        double luminance = (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255;
        return luminance > 0.5 ? Color.BLACK : Color.WHITE;  // Higher luminance means lighter color, so return black for contrast and vice versa
    }
    
    

    public static void main(String[] args) {
        // Running the application
        SwingUtilities.invokeLater(() -> new GameScreen());
    }
}

