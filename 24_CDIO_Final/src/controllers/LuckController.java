package controllers;

import cards.*;
import desktop_resources.GUI;
import fields.*;
import game.Player;

public class LuckController {
	
private CardsDeck deck=new CardsDeck();

	public void landOnLuck(Player player, Field[] fields,Player[] group) {
//		
//		// Draw a luck card
//		
//		// Metode til at flytte spilleren
//		
			Cards card = deck.drawcard();
			GUI.displayChanceCard(card.getText());
			if (card instanceof CardsMoveto) {
	            CardsMoveto move=(CardsMoveto) card;
	            if (move.getExtraMoves()==0){
	    

            	for (int i =0;i<fields.length;i++) {
                    if (fields[i].getName().equals(move.getDestination())){
                    if (fields[i] instanceof Empty){
                    player.setPlayerPosition(i);
                    if(player.getPlayerPosition()>i)
                    	player.payMoney(4000);
                    player.setJail(true);
                    }
                    else if(fields[i] instanceof Refuge){
                    	player.setPlayerPosition(i);
                    }
                    else if (fields[i] instanceof Territory){
                    	player.setPlayerPosition(i);
                    	TerritoryController territorycontroller= new TerritoryController();
                    	Territory territory= (Territory)fields[i];
                    	territorycontroller.landOnTerritory(player, territory, fields);
                    }
                    else if(fields[i] instanceof Fleet){
                    	player.setPlayerPosition(i);
                    	FleetController fleetcontroller= new FleetController();
                    	Fleet fleet= (Fleet)fields[i];
                    	fleetcontroller.landOnFleet(player, fleet, fields);
                    }
                    
	}
                    else if(move.getDestination().equals("fleet")){
                    	FleetController fleetcontroller= new FleetController();
                    	if(player.getPlayerPosition()>35 && player.getPlayerPosition()<=5)
                			{
                			player.setPlayerPosition(5);
                			Fleet ships = (Fleet)fields[5];
                				if(ships.fieldowned)
                				{
                					fleetcontroller.landOnFleet(player,ships,fields);
                					fleetcontroller.landOnFleet(player,ships,fields);
                					
                				}
                				else
                					fleetcontroller.landOnFleet(player,ships,fields);
                			}
                			else if(player.getPlayerPosition()>5 && player.getPlayerPosition()<=15)
                			{
                			player.setPlayerPosition(15);
                			Fleet ships = (Fleet)fields[15];
                				if(ships.fieldowned)
                				{
            					fleetcontroller.landOnFleet(player,ships,fields);
            					fleetcontroller.landOnFleet(player,ships,fields);
                				}
                				else
                					fleetcontroller.landOnFleet(player,ships,fields);
                			}
                			else if(player.getPlayerPosition()>15 && player.getPlayerPosition()<=25)
                			{
                			player.setPlayerPosition(25);
                			Fleet ships = (Fleet)fields[25];
                				if(ships.fieldowned)
                				{
                					fleetcontroller.landOnFleet(player,ships,fields);
                					fleetcontroller.landOnFleet(player,ships,fields);
                				}
                				else
                					fleetcontroller.landOnFleet(player,ships,fields);
                			}
                			else if(player.getPlayerPosition()>25 && player.getPlayerPosition()<=35)
                			{
                			player.setPlayerPosition(35);
                			Fleet ships = (Fleet)fields[35];
                				if(ships.fieldowned)
                				{
                					fleetcontroller.landOnFleet(player,ships,fields);
                					fleetcontroller.landOnFleet(player,ships,fields);
                				}
                				else
                					fleetcontroller.landOnFleet(player,ships,fields);
                			}
	
                    }
            	}
	}
	            else{
	            	player.setPlayerPosition(move.getExtraMoves());
	            	int destination=player.getPlayerPosition();
	            	
	            	if(fields[destination] instanceof Territory){
                    	TerritoryController territorycontroller= new TerritoryController();
                    	Territory territory= (Territory)fields[destination];
                    	territorycontroller.landOnTerritory(player, territory, fields);
	            	}
	            	else if(fields[destination] instanceof Fleet){
                    	FleetController fleetcontroller= new FleetController();
                    	Fleet fleet= (Fleet)fields[destination];
                    	fleetcontroller.landOnFleet(player, fleet, fields);
	            	}
	            	else if (fields[destination] instanceof Tax){
	                 	TaxController taxcontroller= new TaxController();
                    	Tax tax= (Tax)fields[destination];
                    	taxcontroller.payTax(player, tax); 		
	            	}
	            	else if (fields[destination] instanceof Luck){
	            		landOnLuck(player, fields, group);
	            	}
	            	else if(fields[destination] instanceof Jail){
	                 	JailController jailcontroller= new JailController();
                    	jailcontroller.jail(player);
	            	}
	            	
	            	
	            }
}
			if(card instanceof CardsTransaction){
				CardsTransaction transaction= (CardsTransaction) card;
				player.giveMoney(transaction.getvalue());
			}
			if (card instanceof CardsShare){
				CardsShare transaction= (CardsShare) card;
				int moneyPool;
				moneyPool=transaction.getvalue()*group.length;
				for(int i=0; i<=group.length;i++){
					group[i].payMoney(transaction.getvalue());
				}
				player.giveMoney(moneyPool);
			}
	}
}
