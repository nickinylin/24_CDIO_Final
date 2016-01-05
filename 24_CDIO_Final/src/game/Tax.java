/**
 * Tax
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package game;

import desktop_resources.GUI;

public class Tax extends Field {
    protected String fieldname;
    private int taxAmount;
    private String special;
    
    public Tax(String name,int tax, String special){
        super(name);
        taxAmount=tax;
        this.special = special;
    }
    
    @Override
    public void landOnField(Player player){
        
        if (this.special == "special") {
            GUI.displayChanceCard("<center>"+player.getName()+" have landed on a TAX field");
            boolean boo = GUI.getUserLeftButtonPressed(""+player.getName()+"", "Pay 10%", "Pay "+taxAmount+"");
            
            if (boo) {
                int payamount = player.getAssets()*10/100;
                
                if (player.getMoney() > payamount) {
                    player.payMoney(payamount);
                    GUI.setBalance(player.getName(), player.getMoney());
                } else {
                    
                    boolean foo = GUI.getUserLeftButtonPressed(""+player.getName()+" you need to pay "+payamount+"", "Sell all fields", "Go Bankrupt");
                    if (foo) {
                        sellAllFields(player);
                        player.payMoney(payamount);
                        GUI.setBalance(player.getName(), player.getMoney());
                    } else {
                        sellAllFields(player);
                        player.payMoney(payamount);
                        player.payMoney(player.getMoney()+1);
                        GUI.setBalance(player.getName(), player.getMoney());
                    }
                    
                }
                
                GUI.displayChanceCard("<center>"+player.getName()+" have landed on a TAX field<br><br>You paid "+payamount+".");
                
            } else {
                if (player.getMoney() > taxAmount) {
                    player.payMoney(taxAmount);
                    GUI.setBalance(player.getName(), player.getMoney());
                } else {
                    boolean foo = GUI.getUserLeftButtonPressed(""+player.getName()+" you need to pay "+taxAmount+"", "Sell all fields", "Go Bankrupt");
                    if (foo) {
                        sellAllFields(player);
                        player.payMoney(taxAmount);
                        GUI.setBalance(player.getName(), player.getMoney());
                    } else {
                        sellAllFields(player);
                        player.payMoney(taxAmount);
                        player.payMoney(player.getMoney()+1);
                        GUI.setBalance(player.getName(), player.getMoney());
                    }
                }
                GUI.displayChanceCard("<center>"+player.getName()+" have landed on a TAX field<br><br>You paid "+taxAmount+".");
            }
            GUI.setBalance(player.getName(), player.getMoney());
        } else {
            
            if (player.getMoney() < taxAmount) {
                boolean foo = GUI.getUserLeftButtonPressed(""+player.getName()+" you need to pay "+taxAmount+"", "Sell all fields", "Go Bankrupt");
                if (foo) {
                    sellAllFields(player);
                    player.payMoney(taxAmount);
                    GUI.setBalance(player.getName(), player.getMoney());
                } else {
                    sellAllFields(player);
                    player.payMoney(player.getMoney()+1);
                    GUI.setBalance(player.getName(), player.getMoney());
                }
            } else {
                player.payMoney(taxAmount);
                GUI.setBalance(player.getName(), player.getMoney());
                GUI.displayChanceCard("<center>"+player.getName()+" have landed on a TAX field<br><br>You paid "+taxAmount+".");
            }
            
        }
        
        
    }
    
    public int getRent() {
        return taxAmount;
    }
    
    @Override
    public String getName() {
        return this.fieldname;
    }
    
    
}
