package jUnit;

import static org.junit.Assert.*;

import controllers.*;
import game.*;
import fields.*;

import org.junit.Before;
import org.junit.Test;

public class JUnitJail {

	private Player player1;
	private JailController myJail;
	protected Field[] fields;

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
	
	@Test
	public void rolloutofjailfail() {
		player1.setIsInJail(true);

		Dice.testRollDice();
		if (Dice.issame()){
			player1.setIsInJail(false);
		}
		assertEquals(true, player1.isInJail());
	}
	
	@Test
	public void rolloutofjail() {
		player1.setIsInJail(true);

		Dice.testRollDiceSame();
		if (Dice.issame()){
			player1.setIsInJail(false);
		}
		assertEquals(false, player1.isInJail());
	}
	
	@Test
	public void test3AfSammeSlag() {
		
		player1.setNumberOfExtraTurns(3);
		Dice.testRollDiceSame();
		if (Dice.issame()) {
			if (player1.getNumberOfExtraTurns() < 3) {
				
			} else {
				myJail.jail(player1, fields);
			}
		}
		assertEquals(true, player1.isInJail());
	}

}
