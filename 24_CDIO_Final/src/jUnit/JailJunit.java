package jUnit;

import static org.junit.Assert.*;
import fields.Field;
import controllers.*;
import game.*;
import setup.*;
import fields.*;
import org.junit.Before;
import org.junit.Test;

public class JailJunit {
	
		private Player player1;
		
		@Before
	    public void setUp(){
	        
	        player1= new Player("Lars");
        }
	
	@Test
	public void pay1000() {
		player1.setIsInJail(true);
		
		
		int expected=player1.getMoney()-1000;
		if (player1.setIsInJail(true)){
			System.out.println("Er i jail");
		}
		
		
		fail("Not yet implemented");
	}

}
