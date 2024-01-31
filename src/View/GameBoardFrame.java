package View;

import javax.swing.*;

import Model.Board;
import Model.Ladder;
import Model.Snake;
import Model.Square;

import java.awt.*;

public class GameBoardFrame extends JFrame {

    private final int boardSize;
    private final Square[][] cells;
    private final Snake[] snakes;
    private final Ladder[] ladders;

    public GameBoardFrame(Board board) {
        this.boardSize = board.getSize();
        this.cells = board.getCells();
        this.snakes = board.getSnakes();
        this.ladders = board.getLadders();

        setTitle("Snakes and Ladders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(new BoardPanel(), BorderLayout.CENTER);

        pack(); // Set window size based on the preferred sizes of its components
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private class BoardPanel extends JPanel {
        private static final int SQUARE_SIZE = 50; // Size of each square

        public BoardPanel() {
            setPreferredSize(new Dimension(boardSize * SQUARE_SIZE, boardSize * SQUARE_SIZE));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the board
            for (int row = 0; row < boardSize; row++) {
                for (int col = 0; col < boardSize; col++) {
                    // Alternate colors
                    if ((row + col) % 2 == 0) {
                        g.setColor(Color.YELLOW);
                    } else {
                        g.setColor(Color.ORANGE);
                    }
                    g.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                }
            }

            // Draw snakes and ladders
            g.setColor(Color.BLACK);
            for (Snake snake : snakes) {
                // This is a placeholder, draw your snakes based on the start and end positions
                g.drawLine(snake.getStartRow(), snake.getStartCol(), snake.getEndCol(), snake.getEndCol());
            }
            for (Ladder ladder : ladders) {
                // This is a placeholder, draw your ladders based on the start and end positions
                g.drawLine(ladder.getStartRow(), ladder.getStartCol(), ladder.getEndCol(), ladder.getEndCol());
            }
        }
    }

    // Main method to start the game
    public static void main(String[] args) {
        // Initialize your board here with the size and difficulty
        Board board = new Board(7); // Example initialization
        new GameBoardFrame(board);
    }
}
