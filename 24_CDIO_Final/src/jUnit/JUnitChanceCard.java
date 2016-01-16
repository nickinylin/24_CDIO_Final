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
import game.Bank;
import game.Dice;
import game.Player;
import setup.Setup;
import cards.Cards;
import cards.CardsDeck;
import cards.CardsTransaction;
import controllers.LuckController;

public class JUnitChanceCard {
	private Player spiller1;
	private Player[] players;
	protected Field[] fields;
	
	@Before
	public void setUp()	{
		Setup setup = new Setup();
		fields = setup.createFields();
		
		this.spiller1= new Player("Lars");
		spiller1.setPlayerPosition(1);
		spiller1.getAssets();
		
		
	}

	@Test
	public void drawChanceCard() {
		CardsDeck deck = new CardsDeck();
//		PlayerController.movePlayer(spiller1, 3, fields);
		deck.shuffledeck();
		Cards card = deck.drawcard();
//		currentdecksize = 42;

	}
	@Test
	public void drawSpecificCard() {
		CardsDeck deck = new CardsDeck();
		Cards card = deck.drawcard();
//		Cards card = CardsTransaction;
		CardsTransaction transaction= (CardsTransaction) card;
		spiller1.giveMoney(transaction.getvalue());
		int expected = spiller1.getAssets();
		assertEquals(expected , 31000);
	}

}
