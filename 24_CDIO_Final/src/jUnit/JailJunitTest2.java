package jUnit;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import game.*;
import setup.*;
import fields.*;
import controllers.*;
import cards.*;

public class JailJunitTest2 
{

	private Player player1;
	private Dice dice1;
	private Dice dice2;
	
	@Before
	public void setup()
	{
		player1 = new Player ("Lars");
		dice1 = new Dice();
		dice2 = new Dice();
	}
	
	
	@Test
	public void testAfToEns() 
	{
		boolean expected=true;
		player1.setIsInJail(true);
		Dice.getDice1();
		Dice.getDice2();
		Dice.getDice1() = Dice.getDice2();
		assertEquals(expected, player1.setIsInJail(false));
		
		
		
//		if(dice1 == dice2)
//		{
//			player1.setJail(false);
//		}
		
		
	}


	private void assertEquals(boolean expected, Object setJail) 
	{
	
		
	}

}
