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
                    player.setMove(i);
                    if(player.getMove()>i)
                    	player.payMoney(4000);
                    player.setJail(true);
                    }
                    else if(fields[i] instanceof Refuge){
                    	player.setMove(i);
                    }
                    else if (fields[i] instanceof Territory){
                    	player.setMove(i);
                    	TerritoryController territorycontroller= new TerritoryController();
                    	Territory territory= (Territory)fields[i];
                    	territorycontroller.landOnTerritory(player, territory, fields);
                    }
                    else if(fields[i] instanceof Fleet){
                    	player.setMove(i);
                    	FleetController fleetcontroller= new FleetController();
                    	Fleet fleet= (Fleet)fields[i];
                    	fleetcontroller.landOnFleet(player, fleet, fields);
                    }
                    
	}
                    else if(move.getDestination().equals("fleet")){
                    	FleetController fleetcontroller= new FleetController();
                    	if(player.getPlayerPosition()>0 && player.getPlayerPosition()<10)
                			{
                			player.setMove(6);
                			Fleet ships = (Fleet)fields[6];
                				if(ships.fieldowned)
                				{
                					fleetcontroller.landOnFleet(player,ships,fields);
                					fleetcontroller.landOnFleet(player,ships,fields);
                					
                				}
                				else
                					fleetcontroller.landOnFleet(player,ships,fields);
                			}
                			else if(player.getPlayerPosition()>=11 && player.getPlayerPosition()<=20)
                			{
                			player.setMove(16);
                			Fleet ships = (Fleet)fields[16];
                				if(ships.fieldowned)
                				{
            					fleetcontroller.landOnFleet(player,ships,fields);
            					fleetcontroller.landOnFleet(player,ships,fields);
                				}
                				else
                					fleetcontroller.landOnFleet(player,ships,fields);
                			}
                			else if(player.getPlayerPosition()>=21 && player.getPlayerPosition()<=30)
                			{
                			player.setMove(26);
                			Fleet ships = (Fleet)fields[26];
                				if(ships.fieldowned)
                				{
                					fleetcontroller.landOnFleet(player,ships,fields);
                					fleetcontroller.landOnFleet(player,ships,fields);
                				}
                				else
                					fleetcontroller.landOnFleet(player,ships,fields);
                			}
                			else if(player.getPlayerPosition()>=31 && player.getPlayerPosition()<=40)
                			{
                			player.setMove(36);
                			Fleet ships = (Fleet)fields[36];
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
	            	player.setPlayerPosistion(move.getExtraMoves());
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
