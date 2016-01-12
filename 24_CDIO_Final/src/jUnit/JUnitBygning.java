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

public class JUnitBygning 
{

	private Player spiller1;
	private Player spiller2;
	protected Field[] fields;
	
	@Before
	public void setUp()
	{
		Setup setup = new Setup();
		fields = setup.createFields();
		
		this.spiller1 = new Player("Lars");
		this.spiller2 = new Player("Svend");
		spiller1.setPlayerPosition(1);
		spiller1.getAssets();
		spiller2.setPlayerPosition(1);
		spiller2.getAssets();
		
	}
	
	@Test
	public void test() 
	{
		
	}

}
