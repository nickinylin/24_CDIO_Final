package jUnit;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import fields.Field;
import fields.Territory;
import game.Player;
import setup.Setup;

public class JUnitBygning {

	private Player spiller1;
	private Player spiller2;
	protected Field[] fields;
	
	@Before
	public void setUp()	{
		Setup setup = new Setup();
		fields = setup.createFields();
		
		this.spiller1 = new Player("Lars");
		this.spiller2 = new Player("Svend");
		spiller1.setPlayerPosition(1);
		spiller1.getAssets();
		spiller2.setPlayerPosition(1);
		spiller2.getAssets();
		
	}
	
	@Test //tester om huslejen bliver fordoblet ved at eje alle felter i samme gruppe
	public void testBuyBuilding() {

		spiller1.setPlayerPositionToField(2);
		Field currentfield = fields[spiller1.getPlayerPosition()-1];
		((Territory) currentfield).buyField(spiller1, fields);

		spiller1.setPlayerPositionToField(4);
		currentfield = fields[spiller1.getPlayerPosition()-1];
		((Territory) currentfield).buyField(spiller1, fields);
		
		((Territory) currentfield).setBuildingNumbers(5);
		
		assertEquals(6000, ((Territory) currentfield).getRent(spiller1, fields) );
	}
	
}
