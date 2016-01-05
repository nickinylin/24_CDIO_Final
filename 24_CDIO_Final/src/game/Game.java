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
    
        }
        
    }
    
    
}
