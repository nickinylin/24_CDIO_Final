package jUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import controllers.*;
import game.*;
import setup.*;
import fields.*;
import cards.*;

import org.junit.Before;


public class JUnitFleetTest {
	
	private Player player1;
	private Player player2;
	protected Field[] fields;
	
	protected 
	Field currentfield = fields[player1.getPlayerPosition()+40];
	
	@Before
	public void setUp(){
		player1 = new Player("Børge");
        player2 = new Player("Henning");
        
        Field currentfield = fields[player1.getPlayerPosition()+10];

	}

	@Test
	public void test1owned() {
		int expected = player1.getMoney()-500;
		int count = 0;
		
		while(count < 1){
			for (Field f : fields){
				if (f instanceof Fleet){
					Fleet fleet = (Fleet) f;
					if (fleet.getStatus() == false){
						fleet.buyField(player2, fields);
						count++;
						break;
					}
				}
			}
		}
		for (Field f : fields){
			if (f instanceof Fleet){
				Fleet fleet = (Fleet) f;
				if (fleet.getOwner() == player2){
					fleet.payRent(player1, fields);
					break;
					
				}
			}
		}
		assertEquals(expected, player1.getMoney());
	}

}
