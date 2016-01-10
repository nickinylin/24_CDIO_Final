package controllers;

import cards.*;
import desktop_resources.GUI;
import fields.*;
import game.Player;

public class LuckController {
	
private CardsDeck deck=new CardsDeck();
private PlayerController playercontroller= new PlayerController();

	public void landOnLuck(Player player, Field[] fields,Player[] group) {
		
		// Draw a luck card
		
			Cards card = deck.drawcard();
			GUI.displayChanceCard("=======Prøv lykken=======<br> <br>"+card.getText());
			GUI.getUserButtonPressed(player.getName() +" Du har trukket et prøv lykken kort", "Fortsæt");
			if (card instanceof CardsMoveto) {
	            CardsMoveto move=(CardsMoveto) card;
	            if (move.getExtraMoves()==0){
	    

            	for (int i =0;i<fields.length;i++) {
                    if (fields[i].getName().equals(move.getDestination())){
                    if(fields[i] instanceof Refuge){
                    	int x;
						if(player.getPlayerPosition()>i)
				             x = fields.length-player.getPlayerPosition()+i;
						else
							x = i-player.getPlayerPosition();
                    	playercontroller.movePlayer(player, ++x, fields);
                    }
                    else if (fields[i] instanceof Territory){
                    	int x;
						if(player.getPlayerPosition()>i)
				             x = fields.length-player.getPlayerPosition()+i;
						else
							x = i-player.getPlayerPosition();
                    	playercontroller.movePlayer(player, ++x, fields);
                    	TerritoryController territorycontroller= new TerritoryController();
                    	Territory territory= (Territory)fields[i];
                    	territorycontroller.landOnTerritory(group, player, territory, fields);
                    }
                    else if(fields[i] instanceof Fleet){
                    	int x;
						if(player.getPlayerPosition()>i)
				             x = fields.length-player.getPlayerPosition()+i+1;
						else
							x = i-player.getPlayerPosition();
                    	playercontroller.movePlayer(player, ++x, fields);
                    	FleetController fleetcontroller= new FleetController();
                    	Fleet fleet= (Fleet)fields[i];
                    	fleetcontroller.landOnFleet(player, fleet, fields);
                    }
                    
	}
            	}
                    if(move.getDestination().equals("fleet")){
                    	FleetController fleetcontroller= new FleetController();
      Fleet fleet=null;
      int i;
      for(i=player.getPlayerPosition()-1; i<fields.length;i++){
    	  if (fields[i] instanceof Fleet){
    		  fleet=(Fleet) fields[i];
    	  break;
    	  }
    	  else if (i>=fields.length)
    		  i=0;
      }
      if(player.getPlayerPosition()>i)
      playercontroller.movePlayer(player, i-(fields.length-player.getPlayerPosition()), fields);
      else
      playercontroller.movePlayer(player, i-player.getPlayerPosition()+1, fields);
      fleetcontroller.landOnFleet(player, fleet, fields);
      
                    	
//                    	if(player.getPlayerPosition()>35 && player.getPlayerPosition()<=5)
//                			{
//                        	int x;
//                        	if(player.getPlayerPosition()>=0)
//    							x = 5-player.getPlayerPosition();
//                        	else
//                        		x=5+fields.length-player.getPlayerPosition();
//                        	
//                        	playercontroller.movePlayer(player, ++x, fields);
//                			Fleet ships = (Fleet)fields[5];
//                				if(ships.fieldowned)
//                				{
//                					player.payMoney(ships.getRent(player, fields)*2);
//                					
//                				}
//                				else
//                					fleetcontroller.landOnFleet(player,ships,fields);
//                			}
//                			else if(player.getPlayerPosition()>5 && player.getPlayerPosition()<=15)
//                			{
//                            	int x;
//        						if(player.getPlayerPosition()>15)
//                            x = fields.length-player.getPlayerPosition();
//        						else
//        							x = 15-player.getPlayerPosition();
//                            	playercontroller.movePlayer(player, ++x, fields);
//                			Fleet ships = (Fleet)fields[15];
//                				if(ships.fieldowned)
//                				{
//                					player.payMoney(ships.getRent(player, fields)*2);
//                				}
//                				else
//                					fleetcontroller.landOnFleet(player,ships,fields);
//                			}
//                			else if(player.getPlayerPosition()>15 && player.getPlayerPosition()<=25)
//                			{
//                            	int x;
//        						if(player.getPlayerPosition()>25)
//                            x = fields.length-player.getPlayerPosition();
//        						else
//        							x = 25-player.getPlayerPosition();
//                            	playercontroller.movePlayer(player, ++x, fields);
//                			Fleet ships = (Fleet)fields[25];
//                				if(ships.fieldowned)
//                				{
//                					player.payMoney(ships.getRent(player, fields)*2);
//                				}
//                				else
//                					fleetcontroller.landOnFleet(player,ships,fields);
//                			}
//                			else if(player.getPlayerPosition()>25 && player.getPlayerPosition()<=35)
//                			{
//                            	int x;
//        						if(player.getPlayerPosition()>35)
//                            x = fields.length-player.getPlayerPosition();
//        						else
//        							x = 35-player.getPlayerPosition();
//                            	playercontroller.movePlayer(player, ++x, fields);
//                			Fleet ships = (Fleet)fields[35];
//                				if(ships.fieldowned)
//                				{
//                					player.payMoney(ships.getRent(player, fields)*2);
//                				}
//                				else
//                					fleetcontroller.landOnFleet(player,ships,fields);
//                			}
	
                    }
                    if (move.getDestination().equals("De fænglses")){
                    	JailController jailcontroller= new JailController();
                    	jailcontroller.jail(player, fields);
							
							
                    }
            	
	}
	            //kort der bevæger en et predefineret antal skridt.
	            else{
	            	// om kort rykker en bagud
	            	if(move.getExtraMoves()<0){
	            	if(player.getPlayerPosition()+move.getExtraMoves()<player.getPlayerPosition()){
	            		if(player.getPlayerPosition()+move.getExtraMoves()<1){
		            		player.setPlayerPosition(move.getExtraMoves()+fields.length-1);
			            	int destination=player.getPlayerPosition()-1;
		            		GUI.removeAllCars(player.getName());
		            		GUI.setCar(player.getPlayerPosition()+1, player.getName());
	            		}	            		
	            		else{
	            		player.setPlayerPosition(move.getExtraMoves());
		            	int destination=player.getPlayerPosition()-1;
	            		GUI.removeAllCars(player.getName());
	            		GUI.setCar(player.getPlayerPosition(), player.getName());
	            		}
	            		}
	            		
	            	}
	            	//kort der rykker en fremad
	            	else
	            	playercontroller.movePlayer(player,move.getExtraMoves(),fields);
	            	int destination=player.getPlayerPosition()-1;
	            	//felt aktivering.
	            	if(fields[destination] instanceof Territory){
                    	TerritoryController territorycontroller= new TerritoryController();
                    	Territory territory= (Territory)fields[destination];
                    	territorycontroller.landOnTerritory(group, player, territory, fields);
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
			//hvis det er et simpelt penge overførelses kort
			if(card instanceof CardsTransaction){
				CardsTransaction transaction= (CardsTransaction) card;
				player.giveMoney(transaction.getvalue());
				GUI.setBalance(player.getName(), player.getMoney());
			}
			//hvis samtlige spillere er involverede.
			if (card instanceof CardsShare){
				CardsShare transaction= (CardsShare) card;
				int moneyPool;
				moneyPool=transaction.getvalue()*group.length;
				for(int i=0; i<group.length;i++){
					group[i].payMoney(transaction.getvalue());
					GUI.setBalance(group[i].getName(), group[i].getMoney());
				}
				player.giveMoney(moneyPool);
				GUI.setBalance(player.getName(), player.getMoney());
				
			}
			if(card instanceof CardsLegat){
				CardsLegat legat= (CardsLegat) card;
				if(player.getAssets()<15000){
					player.giveMoney(legat.getValue());
				GUI.showMessage("De modtager matador legatet for værdigt trængende");
				GUI.setBalance(player.getName(), player.getMoney());
				}
				else{
					GUI.showMessage("De anses ikke for værdigt trængende og modtager derfor ikke matador legatet");
				}
			}
	}
}
