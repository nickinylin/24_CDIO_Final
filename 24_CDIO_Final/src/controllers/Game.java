/**
 * Game
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package controllers;

import cards.Cards;
import cards.CardsDeck;
import cards.CardsMoveto;
import desktop_resources.GUI;
import fields.Empty;
import fields.Field;
import fields.Fleet;
import fields.Jail;
import fields.Labor;
import fields.Luck;
import fields.Ownable;
import fields.Refuge;
import fields.Tax;
import fields.Territory;
import game.Dice;
import game.Player;
import setup.Setup;


public class Game {
	private TaxController taxController = new TaxController();
	private LuckController luckController = new LuckController();
	private TerritoryController territoryController = new TerritoryController();
	private FleetController FleetController = new FleetController();
	private LaborController LaborController = new LaborController();
	private JailController jailController = new JailController();
	private static Player[] player;
	protected static Field[] fields;

	public static void main(String[] args) {
		new Game();
	}

	public Game() {

		Setup setup = new Setup();
		// Create Fields players and card deck
		fields = setup.createFields();
		player = setup.createPlayers();
		data.deck = new CardsDeck();
		
		String test = GUI.getUserButtonPressed("Vælg en knap", "1","2","3","4","5");

		boolean noWinner = true;
		
		while (noWinner) {
			
			for (int i = 0; i < player.length; i++) {
				
				if (player[i].bankrupt()) {
					checkWinner();
				} 
				
				if (player[i].isInJail()) {
					doJailTurn(player[i]);
				} else {
					doNormalTurn(player[i]);
				}
				
			}
			
		}
		


	}

	
	
	public void doNormalTurn(Player player) {
		
        // Roll Dices
        GUI.getUserButtonPressed("", player.getName()+": Roll Dices");
        Dice.roll();
        GUI.setDice(Dice.getDice1(), Dice.getDice2());
		
        // Move the Player
        player.movePlayer(player, Dice.getSum());
        
        // Where is the Player?
		Field currentfield = fields[player.getPlayerPosition()-1];

		// Which action should be taken?
		if (currentfield instanceof Ownable) {

			landOnOwnable(player, currentfield);

		} else if (currentfield instanceof Luck) {
			
			//landOnLuck(player, currentfield);

		} else if (currentfield instanceof Jail) {
			
			landOnJail(player, currentfield);

		} else if (currentfield instanceof Refuge) {
			
			landOnJail(player, currentfield);

		} else if (currentfield instanceof Tax) {
			
			landOnTax(player, currentfield);

		} else if (currentfield instanceof Empty) {
			
			landOnEmpty(player, currentfield);

		}
		
		if (Dice.issame()) {
			
			if (player.getNumberOfExtraTurns() < 3) {
				
				player.setNumberOfExtraTurns(1);
				doNormalTurn(player);
				
			} else {
				
				// Move play to jail
				
			}
			
		}
		
	}

	private void landOnEmpty(Player player, Field currentfield) {
		
		// Nothing should happen here
		
	}
	
	private void landOnTax(Player player, Field currentfield) {
		
		if (((Tax) currentfield).getSpecial() == "special") {

            GUI.displayChanceCard("<center>"+player.getName()+" have landed on a TAX field");
            
            boolean boo = GUI.getUserLeftButtonPressed(""+player.getName()+"", "Pay 10%", "Pay "+ ((Tax) currentfield).getRent() +"");
            
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
            	
                if (player.getMoney() > ((Tax) currentfield).getRent()) {
                	
                    player.payMoney(((Tax) currentfield).getRent());
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
                GUI.displayChanceCard("<center>"+player.getName()+" have landed on a TAX field<br><br>You paid "+((Tax) currentfield).getRent()+".");
            }
            GUI.setBalance(player.getName(), player.getMoney());
        
		} else { // There was no special tax option
            
            if (player.getMoney() < ((Tax) currentfield).getRent()) {
            	
        		String paymethod = GUI.getUserButtonPressed("Hvordan vil du betale?", "Sælg felt", "Sælg bygning", "Bankrupt");
        		
        		if (paymethod == "Sælg felt") {
        			//Sælg felt metode
        		} else if (paymethod == "Sælg bygning") {
        			
        		} else if (paymethod == "Bankrupt") {
        			player.bankrupt();
        		}
        		
            } else {
            	
                player.payMoney(((Tax) currentfield).getRent());
                GUI.setBalance(player.getName(), player.getMoney());
                GUI.displayChanceCard("<center>"+player.getName()+" have landed on a TAX field<br><br>You paid "+((Tax) currentfield).getRent()+".");
                
            }
            
        }
		
	}

	private void landOnJail(Player player, Field currentfield) {
		
		// Move player to Jail
		
	}

	private void landOnLuck(Player player, Field currentfield) {
		
		// Draw a luck card
		

			Cards card = data.deck.drawcard();
			if (card instanceof CardsMoveto) {
	            CardsMoveto move=(CardsMoveto) card;
	            if (move.getExtraMoves()==0){
	            	
	            }
	            	
	            
	            }

			if(player.getPlayerPosition()>0 && player.getPlayerPosition()<10)
			{
			player.setPlayerPosistion(player, 6);
			Fleet ships = (Fleet)fields[6];
				if(ships.fieldowned)
				{
					player.setPlayerPosistion(player, 6);
					landOnOwnable(player,fields[6]);
				}
				else
					player.setPlayerPosistion(player, 6);
					landOnOwnable(player,fields[6]);
			}
			else if(player.getPlayerPosition()>=11 && player.getPlayerPosition()<=20)
			{
			player.setPlayerPosistion(player, 16);
			Fleet ships = (Fleet)fields[16];
				if(ships.fieldowned)
				{
				player.setPlayerPosistion(player, 16);
				landOnOwnable(player,fields[16]);
				}
				else
					player.setPlayerPosistion(player, 16);
					landOnOwnable(player,fields[16]);
			}
			else if(player.getPlayerPosition()>=21 && player.getPlayerPosition()<=30)
			{
			player.setPlayerPosistion(player, 26);
			Fleet ships = (Fleet)fields[26];
				if(ships.fieldowned)
				{
				player.setPlayerPosistion(player, 26);
				landOnOwnable(player,fields[26]);
				}
				else
					player.setPlayerPosistion(player, 26);
					landOnOwnable(player,fields[26]);
			}
			else if(player.getPlayerPosition()>=31 && player.getPlayerPosition()<=40)
			{
			player.setPlayerPosistion(player, 36);
			Fleet ships = (Fleet)fields[36];
				if(ships.fieldowned)
				{
				player.setPlayerPosistion(player, 36);
				landOnOwnable(player,fields[36]);
				}
				else
					player.setPlayerPosistion(player, 36);
					landOnOwnable(player,fields[36]);
			}
			
	            
		
		
	}

	private void landOnOwnable(Player player, Field currentfield) {
		if (currentfield instanceof Territory) {
			
			if (((Ownable) currentfield).fieldowned) {
				
				// Hvem ejer feltet?
				if (((Ownable) currentfield).fieldowner.equals(player)) {
					
					// Player ejer det selv
					int numberofgroupfields = 0;
					int numberofownedfields = 0;
					int i = 0;
					
					Territory[] ownedfields = new Territory[3];
					
					for (Field f : fields) {
						if (f instanceof Territory) {
							Territory t = (Territory) f;

							if (t.getFieldGroup() == ((Territory) currentfield).getFieldGroup()) {
								numberofgroupfields++;
								if (player.equals(t.getOwner())) {
									ownedfields[i++] = t;
									numberofownedfields++;
								}
							}
						}
					}
					
					if (numberofgroupfields == numberofownedfields) {
						
						boolean buybuilding = GUI.getUserLeftButtonPressed(""+player.getName()+" vil du købe huse eller hoteller på dine grunde?", "Ja", "Nej");

						if (buybuilding) {
							String buy = GUI.getUserSelection(""+player.getName()+" vælg hvilke grunde du vil købe hus på", ""+ownedfields[0].getName()+"", ""+ownedfields[1].getName()+"", ""+ownedfields[2].getName()+"");
						}
						
					}

				} else {

					// What is the rent?
					((Ownable) currentfield).payRent(player);

				}

			} else { // The Territory field was not owned
				
				boolean buyfield = GUI.getUserLeftButtonPressed(""+player.getName()+" du er landet på "+((Territory) currentfield).getName()+", vil du købe grunden?", "Ja", "Nej");

				if (buyfield) {
					// opdater alle felters leje
					((Territory) currentfield).buyField(player);
				}
				
			}

		} else if (currentfield instanceof Fleet) {
			
			if (((Ownable) currentfield).fieldowned) {
				
				if (((Ownable) currentfield).fieldowner.equals(player) == false) {

					((Ownable) currentfield).payRent(player);
					
				}
				
			} else {
				
				boolean buyfleet = GUI.getUserLeftButtonPressed(""+player.getName()+" du er landet på "+((Fleet) currentfield).getName()+", vil du købe flåden?", "Ja", "Nej");
				
				if (buyfleet) {
					((Fleet) currentfield).buyField(player);
				}
				
			}

		} else if (currentfield instanceof Labor) {

			if (((Ownable) currentfield).fieldowned) {
				
				if (((Ownable) currentfield).fieldowner.equals(player) == false) {

					((Ownable) currentfield).payRent(player);
					
				}
				
			} else {
				
				boolean buylabor = GUI.getUserLeftButtonPressed(""+player.getName()+" du er landet på "+((Labor) currentfield).getName()+", vil du købe denne fabrik?", "Ja", "Nej");
				
				if (buylabor) {
					((Labor) currentfield).buyField(player);
				}
				
			}
			
		}
	}

	public void doJailTurn(Player player) {

		boolean boo = GUI.getUserLeftButtonPressed(""+player.getName()+"", "Betal 1000 kr", "Slå med terningerne");

		if (boo) {
			player.payMoney(1000);
			player.setJail(false);
			player.setJailTurn(0);
			GUI.setBalance(player.getName(), player.getMoney());
			doNormalTurn(player);
			
		} else {
			
			// Kontrollerer om han har været i fængsel i 3 omgange
			if (player.getJailTurn() < 3){
				GUI.getUserButtonPressed("", player.getName()+": Roll Dices");
				Dice.roll();
				GUI.setDice(Dice.getDice1(), Dice.getDice2());
				//Hvis han slår to ens kommer han ud af fængslet og får en ekstra tur
				if (Dice.issame()){
					player.movePlayer(player, Dice.getSum());
					GUI.setCar(player.getPlayerPosition(), player.getName());
					player.setJailTurn(0);
					player.setJail(false);
					
					
					// MISSING A LOT
					
					


				} else {

					player.setJailTurn(player.getJailTurn()+1);

				}
				/**
				 * Hvis han har været i fængsel 3 omgange, betaler man automatisk 1000 for at komme ud
				 * og får en normal tur herefter
				 */

			} else{
				player.payMoney(1000);
				GUI.setBalance(player.getName(), player.getMoney());
				GUI.displayChanceCard("Du har været i fængsel i 3 omgange og betaler automatisk 1000 kr for at komme ud");
				player.setJail(false);
				player.setJailTurn(0);
			}


		}


	}


	public void checkWinner() {
		
		int numberofplayers = player.length;
		int count = 0;
		
		for (int i = 0; i < player.length; i++) {
			
			if (player[i].bankrupt()) {
				count++;
			}
			
			if (numberofplayers == count) {
				GUI.displayChanceCard("<center>"+ player[i].getName() +" have won the game with a total of <br><br> "+player[i].getAssets()+"<br>assets.");
			}
			
		}
		
	}

}
