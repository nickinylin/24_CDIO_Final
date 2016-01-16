package jUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import controllers.*;
import fields.Field;
import fields.Territory;
import game.Player;
import setup.Setup;

public class JUnitTerritoryBuy {

	private Player spiller1;

	protected Field[] fields;
	TerritoryController t = new TerritoryController();

	@Before
	public void setUp() {
		Setup setup = new Setup();
		// Create Fields players and card deck
		fields = setup.createFields();

		this.spiller1= new Player("Lars");
		spiller1.setPlayerPosition(1);
	}

	@Test
	public void testSetFieldOwned() { //Tester at man kan sætte en ejer på feltet
		Field currentfield = fields[spiller1.getPlayerPosition()-1];
		((Territory) currentfield).buyField(spiller1, fields);

		assertEquals(true, ((Territory) currentfield).fieldowned);
	} 

	@Test
	public void testSetFieldOwner() { //Tester at vi kan sætte en spiller som ejer af et felt
		Field currentfield = fields[spiller1.getPlayerPosition()-1];
		((Territory) currentfield).buyField(spiller1, fields);

		assertEquals(spiller1, ((Territory) currentfield).fieldowner);
	}

	@Test //tester om huslejen bliver fordoblet ved at eje alle felter i samme gruppe
	public void testBuyFieldGroup() {

		spiller1.setPlayerPositionToField(2);
		Field currentfield = fields[spiller1.getPlayerPosition()-1];
		((Territory) currentfield).buyField(spiller1, fields);

		spiller1.setPlayerPositionToField(4);
		currentfield = fields[spiller1.getPlayerPosition()-1];
		((Territory) currentfield).buyField(spiller1, fields);

		assertEquals(100, ((Territory) currentfield).getRent(spiller1, fields) );
	}
	
}
