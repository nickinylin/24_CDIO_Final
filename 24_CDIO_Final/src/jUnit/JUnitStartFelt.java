package jUnit;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

//import controllers.Game;
import controllers.PlayerController;
//import controllers.TerritoryController;
//import desktop_resources.GUI;
import fields.Field;
//import fields.Territory;
//import game.Bank;
//import game.Dice;
import game.Player;
import setup.Setup;

public class JUnitStartFelt 
{
	private Player spiller1;
//	private Player[] players;
	protected Field[] fields;
	
	@Before
	public void setUp()
	{
		Setup setup = new Setup();
		fields = setup.createFields();
		
		this.spiller1= new Player("Lars");
		spiller1.setPlayerPosition(1);
		spiller1.getAssets();
	}

	@Test
	public void pengeStartFelt() {
		spiller1.getAssets(); // Her bør assets ligge på 30000
		PlayerController.movePlayer(spiller1, 40, fields);
		int expected = spiller1.getAssets(); // Efter han har krydset start, bør hans assets ligge på 34000
	
		assertEquals(expected , 34000);
	}
}
