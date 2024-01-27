package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//ActionListener for player buttons
public class PlayerButtonListener implements ActionListener {
 private final int numOfPlayers;

 public PlayerButtonListener(int numOfPlayers) {
     this.numOfPlayers = numOfPlayers;
 }

 @Override
 public void actionPerformed(ActionEvent e) {
     // Handle button click (you can add your logic here)
 }


}
