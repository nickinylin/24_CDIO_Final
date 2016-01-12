package controllers;

import cards.*;
import desktop_resources.GUI;
import fields.*;
import game.Player;

public class LuckController {

	private CardsDeck deck=new CardsDeck();
//	private PlayerController playercontroller= new PlayerController();
	private MenuController menuController = new MenuController();


	public void landOnLuck(Player[] group, Player player, Luck currentfield, Field[] fields) {

		// Draw a luck card

		Cards card = deck.drawcard();
		GUI.displayChanceCard("====== Prøv lykken ======<br> <br>"+card.getText());
		GUI.getUserButtonPressed(player.getName() +" Du har trukket et prøv lykken kort", "Fortsæt");

		if (card instanceof CardsMoveto) {
			doMoveTo(player, fields, group, card);
			
			//hvis det er et simpelt penge overførelses kort
		} else if (card instanceof CardsTransaction) {
			CardsTransaction transaction = (CardsTransaction) card;
			player.giveMoney(transaction.getvalue());
			GUI.setBalance(player.getName(), player.getMoney());
			showMenu(group, player, currentfield, fields);
			
			//hvis samtlige spillere er involverede.		
		} else if (card instanceof CardsShare) {
			CardsShare transaction = (CardsShare) card;
			int moneyPool;
			moneyPool = transaction.getvalue()*group.length;

			for(int i=0; i<group.length;i++){
				group[i].payMoney(transaction.getvalue());
				GUI.setBalance(group[i].getName(), group[i].getMoney());
			}

			player.giveMoney(moneyPool);
			GUI.setBalance(player.getName(), player.getMoney());
			showMenu(group, player, currentfield, fields);

		} else if(card instanceof CardsLegat) {
			CardsLegat legat = (CardsLegat) card;

			if(player.getAssets() < 15000){
				GUI.showMessage("De modtager matador legatet for værdigt trængende");
				player.giveMoney(legat.getValue());
				GUI.setBalance(player.getName(), player.getMoney());
				showMenu(group, player, currentfield, fields);
			} else {
				GUI.showMessage("De anses ikke for værdigt trængende og modtager derfor ikke matador legatet");
				showMenu(group, player, currentfield, fields);
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
							PlayerController.movePlayer(player, ++x, fields);
						}
					} else if (fields[i] instanceof Territory) {
						int x;
						if(player.getPlayerPosition() > i) {
							x = fields.length-player.getPlayerPosition()+i;
						} else {
							x = i-player.getPlayerPosition();
							PlayerController.movePlayer(player, ++x, fields);
							TerritoryController territorycontroller = new TerritoryController();
							Territory territory = (Territory)fields[i];
							territorycontroller.landOnTerritory(group, player, territory, fields);
						}
					} else if(fields[i] instanceof Fleet) {
						int x;
						if(player.getPlayerPosition() > i) {
							x = fields.length-player.getPlayerPosition()+i;
						} else {
							x = i-player.getPlayerPosition();
							PlayerController.movePlayer(player, ++x, fields);
							FleetController fleetcontroller = new FleetController();
							Fleet fleet = (Fleet)fields[i];
							fleetcontroller.landOnFleet(group, player, fleet, fields);
						}
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
					PlayerController.movePlayer(player, i-(fields.length-player.getPlayerPosition()), fields);
				} else {
					PlayerController.movePlayer(player, i-player.getPlayerPosition()+1, fields);
					fleetcontroller.landOnFleet(group, player, fleet, fields);
				}

			}

			if (move.getDestination().equals("De fængsles")){
				JailController jailcontroller= new JailController();
				jailcontroller.jail(player, fields);
			}


			//kort der bevæger en et predefineret antal skridt.
		} else {
			// om kort rykker en bagud
			if (move.getExtraMoves() < 0) {
				if(player.getPlayerPosition()+move.getExtraMoves() < player.getPlayerPosition()) {
					if(player.getPlayerPosition()+move.getExtraMoves() < 1) {
						player.setPlayerPosition(move.getExtraMoves()+fields.length-1);

						GUI.removeAllCars(player.getName());
						GUI.setCar(player.getPlayerPosition()+1, player.getName());
						showMenu(group, player, ((Field) fields[player.getPlayerPosition()]), fields);
					} else {
						player.setPlayerPosition(move.getExtraMoves()-1);

						GUI.removeAllCars(player.getName());
						GUI.setCar(player.getPlayerPosition(), player.getName());
						showMenu(group, player, ((Field) fields[player.getPlayerPosition()]), fields);
					}
				}
				//kort der rykker en fremad
			} else {
				PlayerController.movePlayer(player,move.getExtraMoves(),fields);
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
					taxcontroller.payTax(player, tax);

				} else if (fields[destination] instanceof Luck) {
					landOnLuck(group, player, ((Luck) fields[destination]), fields);

				} else if(fields[destination] instanceof Jail) {
					JailController jailcontroller = new JailController();
					jailcontroller.jail(player, fields);
				}
			}
		}
	}
	
	private void showMenu(Player[] players, Player player, Field luck, Field[] fields) {
		boolean res;
		do{
			res = menuController.menuBuild(players, player, luck, fields);
		}while(!res);
	}
}