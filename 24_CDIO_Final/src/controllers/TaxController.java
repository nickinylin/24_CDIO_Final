package controllers;

import desktop_resources.GUI;
import fields.Tax;
import game.Player;

public class TaxController {

	public void payTax(Player player, Tax field) {
		
		if (field.getSpecial().equals("special")) {

            GUI.displayChanceCard("<center>"+player.getName() + Language.tax_landon1);
            
            boolean boo = GUI.getUserLeftButtonPressed(""+player.getName()+"", Language.tax_10pct, Language.tax_pay + field.getRent());
            
            if (boo) {
                int payamount = player.getAssets()*10/100;
                
                if (player.getMoney() > payamount) {
                    player.payMoney(payamount);
                    GUI.setBalance(player.getName(), player.getMoney());
                    
                } else {
                    
            		String paymethod = GUI.getUserButtonPressed(Language.tax_missing$, "Sælg felt", "Sælg bygning", "Bankrupt");
            		
            		if (paymethod == "Sælg felt") {
            			//Sælg felt metode
            		} else if (paymethod == "Sælg bygning") {
            			
            		} else if (paymethod == "Bankrupt") {
            			player.bankrupt();
            		}
                    
                }
                
                GUI.displayChanceCard("<center>"+player.getName()+" har landet på et TAX felt<br><br>Du betalte "+payamount+".");
                
            } else {
            	
                if (player.getMoney() > field.getRent()) {
                	
                    player.payMoney(field.getRent());
                    GUI.setBalance(player.getName(), player.getMoney());
                    
                } else {
                	
            		String paymethod = GUI.getUserButtonPressed("Hvordan vil du betale?", "Sælg felt", "Sælg bygning", "Bankrupt");
            		
            		if (paymethod == "Sælg felt") {
            			//Sælg felt metode
            		} else if (paymethod == "Sælg bygning") {
            			
            		} else if (paymethod == "Bankrupt") {
            			player.bankrupt();
            		}
            		
                }
                GUI.displayChanceCard("<center>"+player.getName()+" har landet på et TAX felt<br><br>Du betalte "+field.getRent()+".");
            }
            GUI.setBalance(player.getName(), player.getMoney());
        
		} else { // There was no special tax option
            
            if (player.getMoney() < field.getRent()) {
            	
        		String paymethod = GUI.getUserButtonPressed("Hvordan vil du betale?", "Sælg felt", "Sælg bygning", "Bankrupt");
        		
        		if (paymethod == "Sælg felt") {
        			//Sælg felt metode
        		} else if (paymethod == "Sælg bygning") {
        			
        		} else if (paymethod == "Bankrupt") {
        			player.bankrupt();
        		}
        		
            } else {
            	
                player.payMoney(field.getRent());
                GUI.setBalance(player.getName(), player.getMoney());
                GUI.displayChanceCard("<center>"+player.getName()+" har landet på et TAX felt<br><br>Du betalte "+field.getRent()+".");
                
            }
            
        }
	}
}
