package jUnit;

import org.junit.Before;
import org.junit.Test;

import controllers.PlayerController;
import fields.Field;
import game.*;
import setup.Setup;

public class JUnitSellField {
    private Player spiller1;
    
	protected Field[] fields;
	
    @Before
    public void setUp(){
		Setup setup = new Setup();
		// Create Fields players and card deck
		fields = setup.createFields();
		
        spiller1= new Player("Lars");
        
        PlayerController.movePlayer(spiller1, 1, fields);
    }
    
    @Test
    public void landon1owned() {

    }
}
