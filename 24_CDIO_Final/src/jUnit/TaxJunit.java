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
	
	@Before
	public void setUp(){
		player1 = new Player("Hans");
		b = new Bank();
	}
	
	@Test
	public void pay10percent() {
		player1.setAssets(30000);
//		t.payTax(player1, payamount);
		
			
	}

}
