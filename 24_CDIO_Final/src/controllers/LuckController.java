package controllers;

import cards.*;
import desktop_resources.GUI;
import fields.*;
import game.Player;

public class LuckController {
	
private CardsDeck deck=new CardsDeck();
private PlayerController playercontroller= new PlayerController();

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
                    	int x;
						if(player.getPlayerPosition()>i)
                    x = fields.length-player.getPlayerPosition();
						else
							x = i-player.getPlayerPosition();
                    	playercontroller.movePlayer(player, x, fields);
                    if(player.getPlayerPosition()>i)
                    	player.payMoney(4000);
                    player.setIsInJail(true);
                    }
                    else if(fields[i] instanceof Refuge){
                    	int x;
						if(player.getPlayerPosition()>i)
                    x = fields.length-player.getPlayerPosition();
						else
							x = i-player.getPlayerPosition();
                    	playercontroller.movePlayer(player, x, fields);
                    }
                    else if (fields[i] instanceof Territory){
                    	int x;
						if(player.getPlayerPosition()>i)
                    x = fields.length-player.getPlayerPosition();
						else
							x = i-player.getPlayerPosition();
                    	playercontroller.movePlayer(player, x, fields);
                    	TerritoryController territorycontroller= new TerritoryController();
                    	Territory territory= (Territory)fields[i];
                    	territorycontroller.landOnTerritory(player, territory, fields);
                    }
                    else if(fields[i] instanceof Fleet){
                    	int x;
						if(player.getPlayerPosition()>i)
                    x = fields.length-player.getPlayerPosition();
						else
							x = i-player.getPlayerPosition();
                    	playercontroller.movePlayer(player, x, fields);
                    	FleetController fleetcontroller= new FleetController();
                    	Fleet fleet= (Fleet)fields[i];
                    	fleetcontroller.landOnFleet(player, fleet, fields);
                    }
                    
	}
                    else if(move.getDestination().equals("fleet")){
                    	FleetController fleetcontroller= new FleetController();
                    	if(player.getPlayerPosition()>35 && player.getPlayerPosition()<=5)
                			{
                        	int x;
    						if(player.getPlayerPosition()>5)
                        x = fields.length-player.getPlayerPosition();
    						else
    							x = 5-player.getPlayerPosition();
                        	playercontroller.movePlayer(player, x, fields);
                			Fleet ships = (Fleet)fields[5];
                				if(ships.fieldowned)
                				{
                					player.payMoney(ships.getRent(player, fields)*2);
                					
                				}
                				else
                					fleetcontroller.landOnFleet(player,ships,fields);
                			}
                			else if(player.getPlayerPosition()>5 && player.getPlayerPosition()<=15)
                			{
                            	int x;
        						if(player.getPlayerPosition()>15)
                            x = fields.length-player.getPlayerPosition();
        						else
        							x = 15-player.getPlayerPosition();
                            	playercontroller.movePlayer(player, x, fields);
                			Fleet ships = (Fleet)fields[15];
                				if(ships.fieldowned)
                				{
                					player.payMoney(ships.getRent(player, fields)*2);
                				}
                				else
                					fleetcontroller.landOnFleet(player,ships,fields);
                			}
                			else if(player.getPlayerPosition()>15 && player.getPlayerPosition()<=25)
                			{
                            	int x;
        						if(player.getPlayerPosition()>25)
                            x = fields.length-player.getPlayerPosition();
        						else
        							x = 25-player.getPlayerPosition();
                            	playercontroller.movePlayer(player, x, fields);
                			Fleet ships = (Fleet)fields[25];
                				if(ships.fieldowned)
                				{
                					player.payMoney(ships.getRent(player, fields)*2);
                				}
                				else
                					fleetcontroller.landOnFleet(player,ships,fields);
                			}
                			else if(player.getPlayerPosition()>25 && player.getPlayerPosition()<=35)
                			{
                            	int x;
        						if(player.getPlayerPosition()>35)
                            x = fields.length-player.getPlayerPosition();
        						else
        							x = 35-player.getPlayerPosition();
                            	playercontroller.movePlayer(player, x, fields);
                			Fleet ships = (Fleet)fields[35];
                				if(ships.fieldowned)
                				{
                					player.payMoney(ships.getRent(player, fields)*2);
                				}
                				else
                					fleetcontroller.landOnFleet(player,ships,fields);
                			}
	
                    }
            	}
	}
	            else{
	            	playercontroller.movePlayer(player,move.getExtraMoves(),fields);
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
                    	jailcontroller.jail(player, fields);
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
