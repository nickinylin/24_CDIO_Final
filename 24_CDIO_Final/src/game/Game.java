/**
 * Game
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package game;

import desktop_resources.GUI;

public class Game {
    
    private static Player[] players;
    private static Field[] fields;   
    
    public static void main(String[] args) {
        new Game();
    }
    
    public Game() {
        
        // Create Fields
        fields = Field.createFields();
        
        
        // How many Players?
        String NumberofPlayers = GUI.getUserSelection("", "2 Players", "3 Players", "4 Players", "5 Players", "6 Players");
        
        // Create Player 1,2,3,4,5,6
        switch (NumberofPlayers) {
            case "6 Players": players = Player.addPlayer(6); break;
            case "5 Players": players = Player.addPlayer(5); break;
            case "4 Players": players = Player.addPlayer(4); break;
            case "3 Players": players = Player.addPlayer(3); break;
            case "2 Players": players = Player.addPlayer(2); break;
            default: ;
        }
        
        
        
        while (true) {
        	
            for (int i = 0; i < players.length; i++) {
                
                int count = 1;
                
                for (int x = 0; x < players.length; x++) {
                    
                    if (players[x].bankrupt()) {
                        GUI.removeAllCars(players[x].getName());
                        count++;
                    }
                }
                
                // If there is only one player left
                if (count == players.length) {
                    
                    for (int x = 0; x < players.length; x++) {
                        
                        if (players[x].bankrupt() == false) {
                            GUI.displayChanceCard("<center>"+players[x].getName()+" have won the game with a total of <br><br> "+players[x].getAssets()+"<br>assets.");
                            GUI.showMessage("");
                            return;
                        }
                    }
                    
                }
                
                // If a player is bankrupt
                if (players[i].getAssets() < 0) {
                    continue;
                }
                
                GUI.displayChanceCard(fields[i].landOnField(players[i]));
                
                
                // Roll Dices
                GUI.getUserButtonPressed("", players[i].getName()+": Roll Dices");
                Dice.roll();
                GUI.setDice(Dice.getDice1(), Dice.getDice2());
                
                // Move Player
                players[i].movePlayer(players[i], Dice.getSum());
                
                // This determains what action will accure depending on the field type.
                fields[players[i].getPlayerPosition()-1].landOnField(players[i]);
                players[i].bankrupt();
                
            }
        }
        
    }
    
    
}
