package controllers;

import desktop_resources.GUI;
import fields.Tax;
import game.Player;

public class TaxController {

	public void payTax(Player player, Tax field) {
		
		if (field.getSpecial().equals("special")) {

            GUI.displayChanceCard("<center>"+player.getName()+" have landed on a TAX field");
            
            boolean boo = GUI.getUserLeftButtonPressed(""+player.getName()+"", "Pay 10%", "Pay "+ field.getRent() +"");
            
            if (boo) {
                int payamount = player.getAssets()*10/100;
                
                if (player.getMoney() > payamount) {
                    player.payMoney(payamount);
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
                
                GUI.displayChanceCard("<center>"+player.getName()+" have landed on a TAX field<br><br>You paid "+payamount+".");
                
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
                GUI.displayChanceCard("<center>"+player.getName()+" have landed on a TAX field<br><br>You paid "+field.getRent()+".");
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
                GUI.displayChanceCard("<center>"+player.getName()+" have landed on a TAX field<br><br>You paid "+field.getRent()+".");
                
            }
            
        }
	}
}
