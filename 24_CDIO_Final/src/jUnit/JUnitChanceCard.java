package jUnit;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import fields.*;
import game.*;
import setup.Setup;
import cards.*;
import controllers.Language;
import controllers.LuckController;

public class JUnitChanceCard {
	private Player[] player;
	private Field[] fields;
	CardsDeck deck;
	private LuckController luckcontroller=new LuckController();

	@Before
	public void setUp()	{
		Setup setup = new Setup();
		fields = setup.createFields();
		deck= new CardsDeck();
		player=new Player[3];
		player[0]= new Player("Lars");
		player[1]= new Player("Bente");
		player[2]=new Player("Kebab");

	}

	//	@Test
	//	public void drawChanceCard() {
	//		CardsDeck deck = new CardsDeck();
	//		Cards card = deck.drawcard();
	//		currentdecksize = 42;

	//	}
	//	@Test
	//	public void drawSpecificCard() {
	//		CardsDeck deck = new CardsDeck();
	//		Cards card = deck.drawcard();
	////		Cards card = CardsTransaction;
	//		CardsTransaction transaction= (CardsTransaction) card;
	//		player[0].giveMoney(transaction.getvalue());
	//		int expected = player[0].getAssets();
	//		assertEquals(expected , 31000);
	//	}
	@Test
	public void drawtransactioncard(){
		Cards card=deck.drawspecifik(1);
		int expected=player[0].getMoney()+ ((CardsTransaction)card).getvalue();
		luckcontroller.resolveCard(player, player[0], fields, card);
		assertEquals(expected , player[0].getMoney());
	}
	@Test
	public void drawsharecard(){
		Cards card=deck.drawspecifik(2);
		int expectedloss=player[1].getMoney()-((CardsShare)card).getvalue();
		int expected=player[0].getMoney()+ ((CardsShare)card).getvalue()*(player.length-1);
		luckcontroller.resolveCard(player, player[0], fields, card);
		assertEquals(expected , player[0].getMoney());
		assertEquals(expectedloss,player[1].getMoney());
	}
	@Test
	public void drawlegatcard(){
		Cards card=deck.drawspecifik(3);
		player[0].payMoney(player[0].getMoney());
		int expected=player[0].getMoney()+ ((CardsLegat)card).getValue();
		luckcontroller.resolveCard(player, player[0], fields, card);
		assertEquals(expected , player[0].getMoney());
	}	
	@Test
	public void drawuværdigcard(){
		Cards card=deck.drawspecifik(3);
		int expected=player[0].getMoney();
		luckcontroller.resolveCard(player, player[0], fields, card);
		assertEquals(expected , player[0].getMoney());
	}
	@Test
	public void drawmovetodestinationcard(){
		Cards card=deck.drawspecifik(4);
		CardsMoveto tempcard=(CardsMoveto)card;
		for(int i=0; i<fields.length;i++){
			if(fields[i].getName().equals(tempcard.getDestination())){
				player[0].setPlayerPosition(i);
				break;
			}
		}
		int expected=player[0].getPlayerPosition();
		luckcontroller.resolveCard(player, player[1], fields, card);
		assertEquals(expected , player[1].getPlayerPosition());
	}
	@Test
	public void drawmovetonearestfleetcard(){
		Cards card=deck.drawspecifik(6);
		int i=0;
		for(i=0; i<fields.length;i++){
			if(fields[i] instanceof Fleet){
				player[0].setPlayerPosition(i);
				break;
			}
		}
		int expected=player[0].getPlayerPosition();
		luckcontroller.resolveCard(player, player[1], fields, card);
		assertEquals(expected , player[1].getPlayerPosition());
	}
	@Test
	public void drawmovetojailcard(){
		Cards card=deck.drawspecifik(7);
		int i=0;
		for(i=0; i<fields.length;i++){
			if(fields[i] instanceof Refuge){
				if(fields[i].getName().equals(Language.field_VisitJail)){
					player[0].setPlayerPosition(i);
					break;
				}
			}
		}
		int expected=player[0].getPlayerPosition();
		boolean expectedstatus=true;
		luckcontroller.resolveCard(player, player[1], fields, card);
		assertEquals(expected , player[1].getPlayerPosition());
		assertEquals(expectedstatus,player[1].isInJail());
	}
}
