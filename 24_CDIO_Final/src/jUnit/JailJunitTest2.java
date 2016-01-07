package jUnit;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import game.Dice;
import game.Player;
import setup.Setup;
import fields.Jail;

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
		
	}

}
