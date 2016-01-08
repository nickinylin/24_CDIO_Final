package jUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controllers.*;
import controllers.PlayerController;
import controllers.TerritoryController;
import desktop_resources.GUI;
import fields.Field;
import fields.Territory;
import game.Dice;
import game.Player;
import setup.Setup;



public class JUnitJailDices {

	


		private Player spiller1;
		private Player spiller2;

		private Player[] players;
		protected Field[] fields;
		TerritoryController t = new TerritoryController();
		Dice dice = new Dice();
		Game game = new Game();
		JailController jail = new JailController();

		@Before
		public void setUp(){
			Setup setup = new Setup();
			// Create Fields players and card deck
			fields = setup.createFields();

			this.spiller1= new Player("Lars");
			this.spiller2= new Player("Svend");
//			spiller1.setPlayerPosition(1);
		}


		@Test
		public void testAf3AfSammeSlag() {
			spiller1.setNumberOfExtraTurns(3);
			Dice.testRollDiceSame();
			if (Dice.issame()) {
				if (spiller1.getNumberOfExtraTurns() < 3) {

				} else {
					jail.jail(spiller1, fields);
				}
			}

			assertEquals(true, spiller1.isInJail());
		}

	}

