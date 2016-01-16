package jUnit;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import game.Player;
import setup.Setup;

public class JUnitBankrupt {

	private Player spiller1;
	private Player spiller2;
	
	@Before
	public void setUp()	{
		Setup setup = new Setup();
		setup.createFields();
		
		this.spiller1 = new Player("Lars");
		this.spiller2 = new Player("Svend");
		spiller1.setPlayerPosition(1);
		spiller2.setPlayerPosition(1);
		
	}
	
	@Test
	public void testBankrupt() {
		
		spiller1.setBankrupt();
		
		assertEquals(true, spiller1.getBankrupt());
	}
	
}
