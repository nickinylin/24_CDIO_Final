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
		private JailController myJail;
		
		@Before
	    public void setUp(){
	        player1 = new Player("Morten");
	        myJail = new JailController();
        }
	
	@Test
	public void pay1000() {
		int expected = player1.getMoney() - 1000;
		player1.setIsInJail(true);
		myJail.payJail(player1);
		assertEquals(false,player1.isInJail());
		assertEquals(expected, player1.getMoney());
	}

}
