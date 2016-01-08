package jUnit;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import controllers.PlayerController;
import desktop_resources.GUI;
import fields.Field;
import game.*;
import setup.Setup;

public class SellField {
    private Player spiller1;
    private Player spiller2;
    
	private Player[] players;
	protected Field[] fields;
	
    @Before
    public void setUp(){
		Setup setup = new Setup();
		// Create Fields players and card deck
		fields = setup.createFields();
		players = setup.createPlayers();
		
        spiller1= new Player("Lars");
        spiller2= new Player("Svend");
        
        PlayerController.movePlayer(spiller1, 1, fields);
        String string = GUI.getUserSelection("s", "l");
    }
    
    @Test
    public void landon1owned() {

    }
}
