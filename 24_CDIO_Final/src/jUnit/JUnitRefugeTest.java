package jUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.Player;
import fields.*;
import controllers.*;

public class JUnitRefugeTest {

	private Player player1;
	private Refuge testRefuge;

	@Before
	public void setUp() {
		player1 = new Player("Lars");

		testRefuge = new Refuge("Refuge", "Ugh", 500);

	}

	@Test
	public void landon1owned() {
		int expected = player1.getMoney() + 500;
		testRefuge.getRent();
		player1.giveMoney(testRefuge.getRent());
		assertEquals(expected, player1.getMoney());
	}
}
