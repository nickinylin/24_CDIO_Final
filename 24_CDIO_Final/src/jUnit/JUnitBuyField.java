package jUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controllers.Game;
import controllers.PlayerController;
import controllers.TerritoryController;
import desktop_resources.GUI;
import fields.Field;
import fields.Territory;
import game.Player;
import setup.Setup;

public class JUnitBuyField {

	private Player spiller1;
	private Player spiller2;

	private Player[] players;
	protected Field[] fields;
	TerritoryController t = new TerritoryController();

	@Before
	public void setUp(){
		Setup setup = new Setup();
		// Create Fields players and card deck
		fields = setup.createFields();

		this.spiller1= new Player("Lars");
		this.spiller2= new Player("Svend");
		spiller1.setPlayerPosition(1);
	}

	@Test
	public void testSetFieldOwned() {
		Field currentfield = fields[spiller1.getPlayerPosition()-1];
		((Territory) currentfield).buyField(spiller1, fields);
		//		t.landOnTerritory(players, spiller1,(Territory) currentfield,fields);

		assertEquals(true, ((Territory) currentfield).fieldowned);
	} 

	@Test
	public void testSetFieldOwner() {
		Field currentfield = fields[spiller1.getPlayerPosition()-1];
		((Territory) currentfield).buyField(spiller1, fields);

		assertEquals(spiller1, ((Territory) currentfield).fieldowner);
	}

	@Test //tester om huslejen bliver fordoblet ved at eje alle felter i samme gruppe
	public void testBuyFieldGroup() {

		Field currentfield = fields[spiller1.getPlayerPosition()-1];
		((Territory) currentfield).buyField(spiller1, fields);

		spiller1.setPlayerPositionToField(4);
		currentfield = fields[spiller1.getPlayerPosition()-1];
		((Territory) currentfield).buyField(spiller1, fields);

		assertEquals(100, ((Territory) currentfield).getRent(spiller1, fields) );
	}
	
	@Test
	public void testThreeOfTheSameDices() {
		Field currentfield = fields[spiller1.getPlayerPosition()-1];
		((Territory) currentfield).buyField(spiller1, fields);

		assertEquals(spiller1, ((Territory) currentfield).fieldowner);
	}
	
	@Test
	public void payLaborRent() {
		

		assertEquals(spiller1, ((Territory) currentfield).fieldowner);

}
