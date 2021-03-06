package controllers;

import cards.*;
import desktop_resources.GUI;
import fields.*;
import game.Player;

public class LuckController {

	private CardsDeck deck=new CardsDeck();

	public void landOnLuck(Player[] group, Player player, Luck currentfield, Field[] fields) {

		// Draw a luck card
		Cards card = deck.drawcard();
		GUI.displayChanceCard(Language.luck_titel+card.getText());
		GUI.getUserButtonPressed(player.getName() + Language.luck_draw, Language.continues);
		resolveCard(group, player, fields, card);
	}

	
	
	public void resolveCard(Player[] group, Player player, Field[] fields, Cards card) {
		if (card instanceof CardsMoveto) {
			doMoveTo(player, fields, group, card);
			
			//hvis det er et simpelt penge overførelses kort
		} else if (card instanceof CardsTransaction) {
			CardsTransaction transaction = (CardsTransaction) card;
			player.giveMoney(transaction.getvalue());
			GUI.setBalance(player.getName(), player.getMoney());
			
			//hvis samtlige spillere er involverede.		
		} else if (card instanceof CardsShare) {
			CardsShare transaction = (CardsShare) card;
			int moneyPool=0;

			for(int i=0; i<group.length;i++){
				if(!group[i].getBankrupt())
				group[i].payMoney(transaction.getvalue());
				moneyPool=moneyPool+transaction.getvalue();
				GUI.setBalance(group[i].getName(), group[i].getMoney());
			}

			player.giveMoney(moneyPool);
			GUI.setBalance(player.getName(), player.getMoney());

		} else if(card instanceof CardsLegat) {
			CardsLegat legat = (CardsLegat) card;

			if(player.getAssets() < 15000){
				GUI.showMessage(Language.luck_matadorlegat_draw);
				player.giveMoney(legat.getValue());
				GUI.setBalance(player.getName(), player.getMoney());
				
			} else {
				GUI.showMessage(Language.luck_matadorlegat_fail);
			}
		}
	}

	private void doMoveTo(Player player, Field[] fields, Player[] group, Cards card) {
		
		CardsMoveto move = (CardsMoveto) card;
		
		if (move.getExtraMoves() == 0) {
			
			for (int i =0; i < fields.length; i++) {
				
				if (fields[i].getName().equals(move.getDestination())) {

					if(fields[i] instanceof Refuge){
						int x;
						if(player.getPlayerPosition() > i) {
							x = fields.length-player.getPlayerPosition()+i;
						} else {
							x = i-player.getPlayerPosition();
							}
							PlayerController.movePlayer(player, ++x, fields);
						
					} else if (fields[i] instanceof Territory) {
						int x;
						if(player.getPlayerPosition() > i) {
							x = fields.length-player.getPlayerPosition()+i;
						} else {
							x = i-player.getPlayerPosition();
							}
							PlayerController.movePlayer(player, ++x, fields);
							TerritoryController territorycontroller = new TerritoryController();
							Territory territory = (Territory)fields[i];
							territorycontroller.landOnTerritory(group, player, territory, fields);
						
					} else if(fields[i] instanceof Fleet) {
						int x;
						if(player.getPlayerPosition() > i) {
							x = fields.length-player.getPlayerPosition()+i;
						} else {
							x = i-player.getPlayerPosition();
							}
							PlayerController.movePlayer(player, ++x, fields);
							FleetController fleetcontroller = new FleetController();
							Fleet fleet = (Fleet)fields[i];
							fleetcontroller.landOnFleet(group, player, fleet, fields);
						
					}

				}

			}


			if(move.getDestination().equals("fleet")) {
				FleetController fleetcontroller= new FleetController();
				Fleet fleet=null;
				int i;

				for(i = player.getPlayerPosition()-1; i < fields.length; i++) {
					if (fields[i] instanceof Fleet){
						fleet=(Fleet) fields[i];
						break;
					} else if (i >= fields.length) {
						i=0;
					}
					
				}

				if(player.getPlayerPosition() > i) {
					PlayerController.movePlayer(player, i+fields.length-player.getPlayerPosition(), fields);
				} else {
					PlayerController.movePlayer(player, i-player.getPlayerPosition()+1, fields);
				}
				if (fleet.fieldowned){
				if (!player.getName().equals(fleet.getOwner().getName())){
					player.payMoney(fleet.getRent(fleet.getOwner(), fields)*2);
					fleet.getOwner().giveMoney(fleet.getRent(fleet.getOwner(), fields)*2);
					GUI.setBalance(player.getName(), player.getMoney());
					GUI.setBalance(fleet.fieldowner.getName(), fleet.fieldowner.getMoney());
				}
				}else{
				fleetcontroller.landOnFleet(group, player, fleet, fields);
				}
			}

			if (move.getDestination().equals(Language.field_VisitJail)){
				JailController jailcontroller= new JailController();
				jailcontroller.jail(player, fields);
			}


			//kort der bevæger en et predefineret antal skridt.
		} else {
			// om kort rykker en bagud
			if (move.getExtraMoves() < 0) {
				if(player.getPlayerPosition()+move.getExtraMoves() < player.getPlayerPosition()) {
					if(player.getPlayerPosition()+move.getExtraMoves() < 1) {
						player.setPlayerPosition(move.getExtraMoves()+fields.length);

						GUI.removeAllCars(player.getName());
						GUI.setCar(player.getPlayerPosition(), player.getName());

					} else {
						player.setPlayerPosition(move.getExtraMoves());

						GUI.removeAllCars(player.getName());
						GUI.setCar(player.getPlayerPosition(), player.getName());
					}
				}
				//kort der rykker en fremad
			} else {
				PlayerController.movePlayer(player,move.getExtraMoves(),fields);
			}
				int destination = player.getPlayerPosition()-1;
				//felt aktivering.
				if(fields[destination] instanceof Territory) {
					TerritoryController territorycontroller = new TerritoryController();
					Territory territory= (Territory)fields[destination];
					territorycontroller.landOnTerritory(group, player, territory, fields);

				} else if(fields[destination] instanceof Fleet) {
					FleetController fleetcontroller = new FleetController();
					Fleet fleet= (Fleet)fields[destination];
					fleetcontroller.landOnFleet(group, player, fleet, fields);

				} else if (fields[destination] instanceof Tax) {
					TaxController taxcontroller = new TaxController();
					Tax tax= (Tax)fields[destination];
					taxcontroller.payTax(group, player, tax, fields);

				} else if (fields[destination] instanceof Luck) {
					landOnLuck(group, player, ((Luck) fields[destination]), fields);

				} else if(fields[destination] instanceof Jail) {
					JailController jailcontroller = new JailController();
					jailcontroller.jail(player, fields);
				}
				else if(fields[destination] instanceof Refuge){
					RefugeController refugecontroller=new RefugeController();
					refugecontroller.landOnRefuge(group, player, (Refuge) fields[destination], fields);
				}
		
		}
		
	}
	
}
