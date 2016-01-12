package jUnit;

import static org.junit.Assert.*;

import controllers.*;
import game.*;
import setup.*;
import fields.*;
import cards.*;

import org.junit.Before;
import org.junit.Test;


public class TaxJunit {

	private Player player1;
	private Bank b;
	private TaxController t;
	private Field f;
	private int payamount = player1.getAssets()*10/100;
	
	@Before
	public void setUp(){
		player1 = new Player("Hans");
		b = new Bank();
	}
	
	@Test
	public void pay10percent() {
		player1.setAssets(30000);
		player1.payMoney(payamount);
		assertEquals(27000, player1.getAssets());
	}
	
	@Test
	public void pay4000() {
		player1.setAssets(50000);
		player1.payMoney(4000);
		assertEquals(46000, player1.getAssets());
	}

}
