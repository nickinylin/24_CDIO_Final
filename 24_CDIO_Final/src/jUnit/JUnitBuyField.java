package jUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controllers.Game;
import controllers.PlayerController;
import controllers.TerritoryController;
import desktop_resources.GUI;
import fields.Field;
import game.Player;
import setup.Setup;

public class JUnitBuyField {
	
	private Player spiller1;
    private Player spiller2;
    
	private Player[] players;
	protected Field[] fields;
	TerritoryController t = new TerritoryController();
	
    @Before
    public void setUp(){
		Setup setup = new Setup();
		// Create Fields players and card deck
		fields = setup.createFields();
		
		
        this.spiller1= new Player("Lars");
        this.spiller2= new Player("Svend");
    }
		
	@Test
	public void testSetFieldOwner() {
		t.landOnTerritory(players, spiller1, fields[1], fields);
		
		
	}

}
