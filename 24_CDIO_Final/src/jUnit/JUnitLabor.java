package jUnit;

import static org.junit.Assert.*;

import org.junit.Test;
import game.*;
import setup.*;
import fields.*;

import org.junit.Before;


public class JUnitLabor {
	
	private Player player1;
	private Player player2;
	protected Field[] fields;
	
	
	
	@Before
	public void setUp(){
		player1 = new Player("Henrik");
        player2 = new Player("Niels");
        Setup setup = new Setup();
        fields = setup.createFields();
	}

	@Test
	public void testLaborOwned() {
		int expected = player1.getMoney()-1200;
		int count = 0;

		while(count < 1){
			for (Field f : fields){
				if (f instanceof Labor) {
					Labor labor = (Labor) f;
					if (!labor.getStatus()){
						labor.buyField(player2, fields);
						count++;
						break;
					}
				}
			}
		}
		
		for (Field f : fields){
			if (f instanceof Labor){
				Labor labor = (Labor) f;
				if (labor.getOwner().equals(player2)){
					Dice.testRollDiceSame();
					labor.payRent(player1, fields);
					break;
				}
			}
		}
		assertEquals(expected, player1.getMoney());
	}

	@Test
	public void testLabor2Owned() {
		int expected = player1.getMoney()-2400;
		int count = 0;

		while(count < 2){
			for (Field f : fields){
				if (f instanceof Labor) {
					Labor labor = (Labor) f;
					if (!labor.getStatus()){
						labor.buyField(player2, fields);
						count++;
						break;
					}
				}
			}
		}
		
		for (Field f : fields){
			if (f instanceof Labor){
				Labor labor = (Labor) f;
				if (labor.getOwner().equals(player2)){
					Dice.testRollDiceSame();
					labor.payRent(player1, fields);
					break;
				}
			}
		}
		assertEquals(expected, player1.getMoney());
	}
}
