package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Model.GameDetails;

public class Game_History extends JFrame {
	private JTable table;
    private DefaultTableModel tableModel;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButtonBack;
	public Game_History() {
		 setTitle("Game History");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());

		setContentPane(contentPane);
		
		 // Define the columns for the JTable
        String[] columnNames = {"Time", "Difficulty", "Winner"};

        // Use Gson to read the JSON file and convert it to a List of GameDetails objects
        Gson gson = new Gson();
	    java.lang.reflect.Type gameListType = new TypeToken<ArrayList<GameDetails>>(){}.getType();
        List<GameDetails> gameList = null;
        try (FileReader reader = new FileReader("src/game_history.json")) {
            gameList = gson.fromJson(reader, gameListType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Convert the List of GameDetails to a 2D Object array for the JTable data
        Object[][] data = new Object[gameList.size()][3];

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column)
            {	
                return false; // Make all cells non-editable
            }
        };
        
        // Create the JTable with the data and column names
        tableModel.addColumn("Winner");
        tableModel.addColumn("Difficulty");
        tableModel.addColumn("Time");
        table = new JTable(tableModel);
        contentPane.add(table);
     // Center text in table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.getTableHeader().setReorderingAllowed(false); // Prevent column reordering
        table.setFont(new Font("Serif", Font.BOLD, 20)); // Set the table font size

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER); // Center text
        headerRenderer.setForeground(Color.BLACK);
     // Apply the renderer to each column header
        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        // Custom renderer for table cells
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER); // Center text
        cellRenderer.setBackground(Color.blue);
        cellRenderer.setFont(new Font("Serif", Font.BOLD, 30)); // Set font


        // Apply the renderer to each column
        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        // Set row height and remove grid lines for a cleaner look
        table.setRowHeight(30); // Adjust row height as needed
        table.setShowGrid(false);
           table.setShowHorizontalLines(false);
           table.setShowVerticalLines(false);
        // Add the JScrollPane to the JFrame and center it
           setLayout(new BorderLayout());
           JScrollPane scrollPane = new JScrollPane(table);
           contentPane.add(scrollPane, BorderLayout.CENTER);
           table.setFillsViewportHeight(true);
           table.setForeground(Color.WHITE);
         
        // Add rows to the table model
           if (gameList != null) {
               for (GameDetails details : gameList) {
                   Object[] row = new Object[] {details.winnerName , details.difficulty,details.time };
                   tableModel.addRow(row);
               }
           }
            btnNewButtonBack = new JButton("Back");
           btnNewButtonBack.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   Game_History.this.setVisible(false); // Close the current GameHistory window
                   new MainScreen().setVisible(true); // Open the MainScreen window
               }
           });
        // Calculate the position for the "Back" button
           int buttonWidth = 100;
           int buttonHeight = 50;
           int buttonX = 800 - buttonWidth - 30; // 30 pixels padding from the right
           int buttonY = 500 - buttonHeight - 30; // 30 pixels padding from the bottom

           btnNewButtonBack.setBounds(buttonX, buttonY, buttonWidth, buttonHeight); // Set the position and size

           JPanel buttonPanel = new JPanel();
           buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Right-align the button
           buttonPanel.add(btnNewButtonBack); // Add the button to the panel

           // Add the button panel to the bottom (SOUTH) of the contentPane
           contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
	}
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}
}
