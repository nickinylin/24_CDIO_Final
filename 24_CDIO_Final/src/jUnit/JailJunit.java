package jUnit;

import static org.junit.Assert.*;

import controllers.*;
import game.*;
import setup.*;
import fields.*;
import cards.*;

import org.junit.Before;
import org.junit.Test;

public class JailJunit {
	
		private Player player1;
		
		@Before
	    public void setUp(){
	        
	        player1 = new Player("Lars");
        }
	
	@Test
	public void pay1000() {
		player1.setIsInJail(true);	
		player1.payMoney(1000);
		assertEquals(false,player1.isInJail());
	}

}
