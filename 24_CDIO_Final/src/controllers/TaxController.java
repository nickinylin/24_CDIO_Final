package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Tax;
import game.Player;

public class TaxController {

	private MenuController menuController = new MenuController();
	
	public void payTax(Player[] players, Player player, Tax field, Field[] fields) {
		
		if (field.getSpecial().equals("special")) {

            GUI.displayChanceCard("<center>"+player.getName() + Language.tax_landon1);
            
            boolean boo = GUI.getUserLeftButtonPressed(player.getName(), Language.tax_10pct, Language.tax_pay + field.getRent());
            
            if (boo) {
                int payamount = player.getAssets()*10/100;
                
                if (player.getMoney() > payamount) {
                    player.payMoney(payamount);
                    GUI.setBalance(player.getName(), player.getMoney());
                    
                } else {
                    
            		menuController.showMenu(players, player, field, fields);
                    
                }
                
                GUI.displayChanceCard("<center>"+player.getName()+ Language.tax_landon1 + "<br><br>" + Language.ownable_rent1 + payamount);
                
            } else {
            	
                if (player.getMoney() > field.getRent()) {
                	
                    player.payMoney(field.getRent());
                    GUI.setBalance(player.getName(), player.getMoney());
                    
                } else {
                	
             		String paymethod = GUI.getUserButtonPressed(Language.tax_missing$, Language.sellfield, Language.sellbuilding, Language.bankrupt);
            		
            		if (paymethod == Language.sellfield) {
            			//Sælg felt metode
            		} else if (paymethod == Language.sellbuilding) {
            			
            		} else if (paymethod == Language.bankrupt) {
            			player.bankrupt();
            		}
            		
                }
                GUI.displayChanceCard("<center>"+player.getName()+ Language.tax_landon1 + "<br><br>" + Language.ownable_rent1 + field.getRent());
            }
            GUI.setBalance(player.getName(), player.getMoney());
        
		} else { // There was no special tax option
            
            if (player.getMoney() < field.getRent()) {
            	
            	String paymethod = GUI.getUserButtonPressed(Language.tax_missing$, Language.sellfield, Language.sellbuilding, Language.bankrupt);
        		
        		if (paymethod == Language.sellfield) {
        			//Sælg felt metode
        		} else if (paymethod == Language.sellbuilding) {
        			
        		} else if (paymethod == Language.bankrupt) {
        			player.bankrupt();
        		}
        		
            } else {
            	
                player.payMoney(field.getRent());
                GUI.setBalance(player.getName(), player.getMoney());
                GUI.displayChanceCard("<center>"+player.getName()+ Language.tax_landon1 + "<br><br>" + Language.ownable_rent1 + field.getRent());
                
            }
            
        }
	}
}
