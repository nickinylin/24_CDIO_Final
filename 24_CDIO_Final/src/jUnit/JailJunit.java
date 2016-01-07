package jUnit;

import static org.junit.Assert.*;
import fields.Field;
import game.*;
import setup.*;
import fields.*;
import org.junit.Before;
import org.junit.Test;

public class JailJunit {

		private Player player1;
		private Player player2;
		
		@Before
	    public void setUp(){
	        
	        player1= new Player("Lars");
	        player2= new Player("Svend");
	        player1.movePlayer(player1, 1);
	        player2.movePlayer(player2, 1);
		}
	
	@Test
	public void pay1000() {
		player1.setJail(true);
		int expected=player1.getMoney()-1000;
		
		
		
		fail("Not yet implemented");
	}

}
