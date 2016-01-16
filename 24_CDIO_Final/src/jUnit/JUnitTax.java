package jUnit;

import static org.junit.Assert.*;

import game.*;
import org.junit.Before;
import org.junit.Test;


public class JUnitTax {

	private Player player1;
	
	@Before
	public void setUp(){
		player1 = new Player("Hans");
	}
	
	@Test
	public void pay10percent() {
		int payamount = player1.getAssets()/10;
		player1.payMoney(payamount);
		assertEquals(27000, player1.getAssets());
	}
	
	@Test
	public void pay4000() {
		player1.payMoney(4000);
		assertEquals(26000, player1.getAssets());
	}

}
