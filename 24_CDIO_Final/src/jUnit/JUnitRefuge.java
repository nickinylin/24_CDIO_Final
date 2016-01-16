package jUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.Player;
import setup.Setup;
import fields.*;
import controllers.*;

public class JUnitRefuge {

	private Player player1;
	private Refuge testRefuge;
	protected Field[] fields;
	
	@Before
	public void setUp() {
		Setup setup = new Setup();
		fields = setup.createFields();
		
		player1 = new Player("Lars");
		player1.setPlayerPosition(1);
		testRefuge = new Refuge("Refuge", "Ugh", 500);

	}
	
	@Test
	public void landonrefuge() {
		int expected = player1.getMoney() + 500;
		testRefuge.getRent();
		player1.giveMoney(testRefuge.getRent());
		assertEquals(expected, player1.getMoney());
	}
	
	@Test
	public void pengeStartFelt() {
		player1.getAssets(); // Her bør assets ligge på 30000
		PlayerController.movePlayer(player1, 40, fields);
		int expected = player1.getAssets(); // Efter han har krydset start, bør hans assets ligge på 34000
	
		assertEquals(expected , 34000);
	}
}
