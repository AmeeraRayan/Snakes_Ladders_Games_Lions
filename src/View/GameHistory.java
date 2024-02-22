package View;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Model.GameDetails;
import Model.Questions;
import Model.SysData;

import java.lang.reflect.Type;
import javax.swing.table.DefaultTableCellRenderer;

public class GameHistory extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    public GameHistory() {
        setTitle("Game History");
        setBounds(100, 100, 800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
        add(scrollPane, BorderLayout.CENTER);
        table.setFillsViewportHeight(true);
        table.setForeground(Color.WHITE);
      
     // Add rows to the table model
        if (gameList != null) {
            for (GameDetails details : gameList) {
                Object[] row = new Object[] {details.winnerName , details.difficulty,details.time };
                tableModel.addRow(row);
            }
        }
        
  
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
