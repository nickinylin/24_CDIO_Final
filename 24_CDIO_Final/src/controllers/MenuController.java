package controllers;

import fields.Ownable;
import fields.Refuge;
import desktop_resources.GUI;
import fields.*;
import fields.Fleet;
import fields.Labor;
import fields.Luck;
import fields.Jail;
import fields.Territory;
import game.Dice;
import game.Player;

public class MenuController {

	public boolean menuBuild(Player[] players, Player player, Field currentfield, Field[] fields) {

		int count = 0;
		String[] tempmenu = new String[7];
		boolean nextTurn = false;

		final String button1 = "Køb felt";
		final String button2 = "Sælg et felt";
		final String button3 = "Pantsæt";
		final String button4 = "Køb Bygning";
		final String button5 = "Sælg Bygning";
		final String button6 = "Afslut Tur";

		if (checkYouCanBuyField(player, currentfield, fields)) {
			tempmenu[count++] = button1;
		}
		if (checkOwnField(player, currentfield, fields)) {
			tempmenu[count++] = button2;
			tempmenu[count++] = button3;
		} else if (checkPawnField(player, currentfield, fields)) {
			tempmenu[count++] = button3;
		}
		if (checkBuyBuilding(player, currentfield, fields)) {
			tempmenu[count++] = button4;
		}
		if (checkBuildingExists(player, currentfield, fields)){
			tempmenu[count++] = button5;
		}

		tempmenu[count++] = button6;

		String[] menu = new String[count];

		for (int i = 0; i < menu.length; i++) {
			menu[i] = tempmenu[i];
		}

		String button = GUI.getUserButtonPressed(player.getName(), menu);

		switch(button) {

		case button1:
			nextTurn = false;
			((Ownable) currentfield).buyField(player, fields);
			GUI.setBalance(player.getName(), player.getMoney());
			((Ownable) currentfield).updateFieldGroup(player, currentfield, fields);
			break;

		case button2:
			nextTurn = false;
			String[] fieldlist = getPlayerOwnedFields(player, fields);

			String buyfield = GUI.getUserSelection(player.getName(), fieldlist);

			String sellto = GUI.getUserButtonPressed(player.getName(), "Sælg felt til Spillere", "Fortryd");

			if (sellto == "Sælg felt til Spillere") {

				String[] playernames = getAllPlayerNamesExceptPlayer(players, player);

				String spiller = GUI.getUserSelection("Sælg "+buyfield+" til", playernames);
				int g = 0;
				Player[] buyplayer = new Player[1];
				for (int z = 0; z < players.length ; z++) {
					if (players[z].getName() == spiller) {
						buyplayer[g++] = players[z];
					}
				}

				int k = 0;
				for (Field f : fields) {
					if (f instanceof Territory) {
						Territory territory = (Territory) f;

						if (buyfield == territory.getName()) {
							// TODO lav metode
							territory.buyField(buyplayer[0], fields);
							int pris = GUI.getUserInteger("Pris");
							player.giveMoney(pris);
							player.setAssets(-territory.getPrice());
							buyplayer[0].giveMoney(territory.getPrice());
							buyplayer[0].payMoney(pris);
							GUI.setBalance(buyplayer[0].getName(), buyplayer[0].getMoney());
							GUI.setBalance(player.getName(), player.getMoney());
							territory.updateFieldGroup(buyplayer[0], territory, fields);
						}
					} else if (f instanceof Fleet) {
						Fleet fleet = (Fleet) f;

						if (buyfield == fleet.getName()) {
							// TODO lav metode
							fleet.buyField(buyplayer[0], fields);
							int pris = GUI.getUserInteger("Pris");
							player.giveMoney(pris);
							player.setAssets(-fleet.getPrice());
							buyplayer[0].giveMoney(fleet.getPrice());
							buyplayer[0].payMoney(pris);
							GUI.setBalance(buyplayer[0].getName(), buyplayer[0].getMoney());
							GUI.setBalance(player.getName(), player.getMoney());
							fleet.updateFieldGroup(buyplayer[0], fleet, fields);
						}
					} else if (f instanceof Labor) {
						Labor labor = (Labor) f;

						if (buyfield == labor.getName()) {
							// TODO lav metode
							labor.buyField(buyplayer[0], fields);
							int pris = GUI.getUserInteger("Pris");
							player.giveMoney(pris);
							player.setAssets(-labor.getPrice());
							buyplayer[0].giveMoney(labor.getPrice());
							buyplayer[0].payMoney(pris);
							GUI.setBalance(buyplayer[0].getName(), buyplayer[0].getMoney());
							GUI.setBalance(player.getName(), player.getMoney());
							labor.updateFieldGroup(buyplayer[0], labor, fields);
						}
					}
				}

			}
			break;

		case button3:
			nextTurn = false;
			String pantsæt = null;

			if (checkPawnField(player, currentfield, fields) && checkUnPawnField(player, currentfield, fields)) {
				pantsæt = GUI.getUserButtonPressed(player.getName(), "Pantsæt Felt", "Tilbagekøb af Felt", "Fortryd");
			} else if (checkPawnField(player, currentfield, fields)){
				pantsæt = GUI.getUserButtonPressed(player.getName(), "Pantsæt Felt", "Fortryd");
			} else if (checkUnPawnField(player, currentfield, fields)) {
				pantsæt = GUI.getUserButtonPressed(player.getName(), "Tilbagekøb af Felt", "Fortryd");
			}

			if (pantsæt == "Pantsæt Felt") {

				String[] pawnfieldlist = getPlayerOwnedFields(player, fields);

				String pawnfield = GUI.getUserSelection(player.getName(), pawnfieldlist);

				Ownable[] thisfield = new Ownable[1];
				int x = 0;
				int getfieldnumber = 0;

				for (int w = 0; w < fields.length; w++) {
					Field f = fields[w];

					if (f instanceof Territory) {
						Territory territory = (Territory) f;

						if (pawnfield == territory.getName()) {
							thisfield[x++] = territory;
							getfieldnumber = w+1;
						}

					} else if (f instanceof Fleet) {
						Fleet fleet = (Fleet) f;

						if (pawnfield == fleet.getName()) {
							thisfield[x++] = fleet;
							getfieldnumber = w+1;
						}

					} else if (f instanceof Labor) {
						Labor labor = (Labor) f;

						if (pawnfield == labor.getName()) {
							thisfield[x++] = labor;
							getfieldnumber = w+1;
						}

					}
				}

				thisfield[0].setOwnedToFalse();
				thisfield[0].setPawned(true);

				player.giveMoney((int) (thisfield[0].getPawnPrice()));
				GUI.setBalance(player.getName(), player.getMoney());

				GUI.setTitleText(getfieldnumber, thisfield[0].getName());
				GUI.setSubText(getfieldnumber, "Pantsat");

			} else if (pantsæt == "Tilbagekøb af Felt") {

				String[] pawnfieldlist = getPlayerPawnedFields(player, fields);

				String pawnfield = GUI.getUserSelection(player.getName(), pawnfieldlist);

				Ownable[] thisfield = new Ownable[1];
				int x = 0;
				int getfieldnumber = 0;

				for (int w = 0; w < fields.length; w++) {
					Field f = fields[w];

					if (f instanceof Territory) {
						Territory territory = (Territory) f;

						if (pawnfield == territory.getName()) {
							thisfield[x++] = territory;
							getfieldnumber = w+1;
						}

					} else if (f instanceof Fleet) {
						Fleet fleet = (Fleet) f;

						if (pawnfield == fleet.getName()) {
							thisfield[x++] = fleet;
							getfieldnumber = w+1;
						}

					} else if (f instanceof Labor) {
						Labor labor = (Labor) f;

						if (pawnfield == labor.getName()) {
							thisfield[x++] = labor;
							getfieldnumber = w+1;
						}

					}
				}

				thisfield[0].setOwned();
				thisfield[0].setPawned(false);

				player.payMoney((int) (thisfield[0].getPawnPrice()));
				GUI.setBalance(player.getName(), player.getMoney());

				GUI.setTitleText(getfieldnumber, thisfield[0].getName());
				GUI.setSubText(getfieldnumber, "Leje: "+thisfield[0].getRent(player, fields));
			}
			break;
		case button4: // køb bygning
			nextTurn = false;

			if (checkBuyBuilding(player, currentfield, fields)) {
				// TODO get fields where group is owned
				// buy house for selected field
				// house can only be bought if other fields have houses

				 String[] buyBuildingFieldlist = getBuyBuildingFieldList(player, fields);
//				String[] buyBuildingFieldlist = getWhereToBuyBuilding(player, fields);

				String buyBuildingField = GUI.getUserSelection(player.getName(), buyBuildingFieldlist);

				Territory[] thisfield = new Territory[3];
				int i = 0;
				int getfieldnumber = 0;

				for (int x = 0; x < fields.length; x++) {
					Field f = fields[x];

					if (f instanceof Territory) {
						Territory t = (Territory) f;

						if (buyBuildingField == t.getName()) {
							thisfield[i++] = t;
							getfieldnumber = x+1;
						}

					}
				}

				int houseCount = thisfield[0].getHouse();
				thisfield[0].buyHouse();
				GUI.setHouses(getfieldnumber, houseCount+1);
				GUI.setDescriptionText(getfieldnumber, "Leje: "+thisfield[0].getRent(player, fields));
			}
			break;
		case button5: // sælg bygning
			nextTurn = false;

			if (checkBuildingExists(player, currentfield, fields)) {
				// Sælg bygning
			}
			break;
		case button6:
			nextTurn = true; break;
		default: break;
		}
		return nextTurn;

	}


	private boolean checkPawnField(Player player, Field currentfield, Field[] fields) {
		int i = 0;
		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (t.getPawned() == false && player.equals(t.getOwner())) {
					i++;
				}
			}
		}
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkUnPawnField(Player player, Field currentfield, Field[] fields) {

		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			if (f instanceof Territory) {
				Territory territory = (Territory) f;

				if (territory.getPawned() && player.equals(territory.getOwner())) {
					return true;
				}

			} else if (f instanceof Fleet) {
				Fleet fleet = (Fleet) f;

				if (fleet.getPawned() && player.equals(fleet.getOwner())) {
					return true;
				}

			} else if (f instanceof Labor) {
				Labor labor = (Labor) f;

				if (labor.getPawned() && player.equals(labor.getOwner())) {
					return true;
				}
			}
		}
		return false;
	}


	private String[] getAllPlayerNamesExceptPlayer(Player[] players, Player player) {
		String[] playernames = new String[players.length-1];
		int j = 0;

		for (int s = 0; s < players.length ; s++) {
			if (player.getName().equals(players[s].getName())) {

			} else {
				playernames[j] = players[s].getName();
				j++;
			}
		}
		return playernames;
	}


	private String[] getPlayerPawnedFields(Player player, Field[] fields) {

		int i = 0;

		Field[] temppawnedfields = new Field[28];

		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (t.getPawned() && player.equals(t.getOwner())) {
					temppawnedfields[i++] = t;
				}
			} else if (f instanceof Fleet) {
				Fleet fleet = (Fleet) f;

				if (fleet.getPawned() && player.equals(fleet.getOwner())) {
					temppawnedfields[i++] = fleet;
				}
			} else if (f instanceof Labor) {
				Labor labor = (Labor) f;

				if (labor.getPawned() && player.equals(labor.getOwner())) {
					temppawnedfields[i++] = labor;
				}
			}
		}

		String[] fieldlist = new String[i];

		for (int x = 0; x < fieldlist.length; x++) {
			fieldlist[x] = temppawnedfields[x].getName();
		}
		return fieldlist;
	}

	private String[] getBuyBuildingFieldList(Player player, Field[] fields) {

		int i = 0;

		Territory[] tempfields = new Territory[3];

		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (t.getPawned() == false && player.equals(t.getOwner())) {
					tempfields[i++] = t;
				}
			} 
		}


		String[] fieldlist = new String[i];
		// TODO denne metode mangler at udregne hvor man kan bygge huse
		
//		mangler bare at finde det felt/felter hvor der står mindst huse på
//		og lave dem til en string
//		så vi kan få dem vist i en liste
		
//		Templiste skal vi se hvilke der har mindst huse
//		

		
		for (int z = 0; z < tempfields.length ; z++) {
			Territory f = null;
			Territory territory = (Territory) f;
			

			}
		
		
		int count0 = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		
		Field[] thisfield = new Field[3];

		for (int x = 0; x < fieldlist.length; x++) {
			fieldlist[x] = tempfields[x].getName();
		}
		return fieldlist;
	}

	private String[] getWhereToBuyBuilding(Player player, Field[] fields) {
//TODO
		int i = 0;

		Territory[] tempfields = new Territory[3];

		for (Field f : fields) {
			if (f.getFieldType() == "Territory") {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (t.getPawned() == false && player.equals(t.getOwner())) {
					tempfields[i++] = t;
				}
			} 
		}
		}

		String[] fieldlist = new String[i];

		if (tempfields.length > 2) {
			// Der er flere end 2 grunde
			if (tempfields[0].buyHouse() == 0) {
				// Hvis den første grund ikke har huse
				fieldlist[0] = tempfields[0].getName();

				if (tempfields[1].buyHouse() == 0) {
					// Hvis den anden grund ikke har huse
					fieldlist[1] = tempfields[1].getName();

				} else if (tempfields[2].buyHouse() == 0) {
					// Hvis den 3 grund ikke har huse
					fieldlist[2] = tempfields[2].getName();
				}

			} else if (tempfields[0].buyHouse() == 1) {
				fieldlist[0] = tempfields[0].getName();

				if (tempfields[1].buyHouse() == 1) {
					fieldlist[1] = tempfields[1].getName();

				} else if (tempfields[2].buyHouse() == 1) {
					fieldlist[2] = tempfields[2].getName();
				}

			} else if (tempfields[0].buyHouse() == 2) {
				// Hvis den første grund ikke har huse
				fieldlist[0] = tempfields[0].getName();

				if (tempfields[1].buyHouse() == 2) {
					// Hvis den anden grund ikke har huse
					fieldlist[1] = tempfields[1].getName();

				} else if (tempfields[2].buyHouse() == 2) {
					// Hvis den 3 grund ikke har huse
					fieldlist[2] = tempfields[2].getName();
				}
			} else if (tempfields[0].buyHouse() == 3) {
				// Hvis den første grund ikke har huse
				fieldlist[0] = tempfields[0].getName();

				if (tempfields[1].buyHouse() == 3) {
					// Hvis den anden grund ikke har huse
					fieldlist[1] = tempfields[1].getName();

				} else if (tempfields[2].buyHouse() == 3) {
					// Hvis den 3 grund ikke har huse
					fieldlist[2] = tempfields[2].getName();
				}

			} else if (tempfields[0].buyHouse() == 4) {
				// Hvis den første grund ikke har huse
				fieldlist[0] = tempfields[0].getName();

				if (tempfields[1].buyHouse() == 4) {
					// Hvis den anden grund ikke har huse
					fieldlist[1] = tempfields[1].getName();

				} else if (tempfields[2].buyHouse() == 4) {
					// Hvis den 3 grund ikke har huse
					fieldlist[2] = tempfields[2].getName();
				}
			}
		}

		return fieldlist;
	}

	private String[] getPlayerOwnedFields(Player player, Field[] fields) {

		int i = 0;

		Field[] tempownedfields = new Field[28];

		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (player.equals(t.getOwner()) && t.getPawned() == false) {
					tempownedfields[i++] = t;
				}
			} else if (f instanceof Fleet) {
				Fleet fleet = (Fleet) f;

				if (player.equals(fleet.getOwner()) && fleet.getPawned() == false) {
					tempownedfields[i++] = fleet;
				}
			} else if (f instanceof Labor) {
				Labor labor = (Labor) f;

				if (player.equals(labor.getOwner()) && labor.getPawned() == false) {
					tempownedfields[i++] = labor;
				}
			}
		}

		String[] fieldlist = new String[i];

		for (int x = 0; x < fieldlist.length; x++) {
			fieldlist[x] = tempownedfields[x].getName();
		}
		return fieldlist;
	}


	private boolean checkOwnField(Player player, Field currentfield, Field[] fields) {

		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;
				if (player.equals(t.getOwner())) {
					return true;
				}
			} else if (f instanceof Fleet) {
				Fleet fleet = (Fleet) f;
				if (player.equals(fleet.getOwner())) {
					return true;
				}
			} else  if (f instanceof Labor) {
				Labor labor = (Labor) f;
				if (player.equals(labor.getOwner())) {
					return true;
				}
			}
		}
		return false;
	}


	public boolean checkBuyBuilding(Player player, Field currentfield, Field[] fields) {


		if (currentfield.getFieldType() == "Territory") {
		


		}

			int numberofgroupfields = 0;
			int numberofownedfields = 0;
			int i = 0;


			Territory[] ownedfields = new Territory[3];

			for (Field f : fields) {
				if (f instanceof Territory) {
					Territory t = (Territory) f;

					if (t.getFieldGroup() == ((Territory) currentfield).getFieldGroup()) {
						numberofgroupfields++;
						if (player.equals(t.getOwner()) && t.getPawned() == false) {
							ownedfields[i++] = t;
							numberofownedfields++;
						}
					}
				}
			}

			if (numberofgroupfields == numberofownedfields && numberofgroupfields != 0) {
				return true;
			} else {
				return false;
			}
	}

	private boolean checkBuildingExists(Player player, Field currentfield, Field[] fields) {

		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (player.equals(t.getOwner()) && t.getHouse() > 0) {
					return true;
				}
			}
		}

		return false;
	}


	private boolean checkYouCanBuyField(Player player, Field currentfield, Field[] fields) {
		
		if (currentfield instanceof Tax){
			return false;
		}
		if (currentfield instanceof Refuge){
			return false;
		}
		if(currentfield instanceof Luck){
			return false;
		}
		
		

		for (Field f : fields) {

			if (currentfield.getName() == "Prøv lykken") {

			} else if (f instanceof Territory) {
				Territory territory = (Territory) f;

				if (territory.getName().equals(((Ownable) currentfield).getName())) {
					if (((Ownable) currentfield).fieldowned == false && ((Ownable) currentfield).getPawned() == false) {
						return true;
					}
				}
			} else if (f instanceof Fleet) {
				Fleet fleet = (Fleet) f;

				if (fleet.getName().equals(((Ownable) currentfield).getName())) {
					if (((Ownable) currentfield).fieldowned == false && ((Ownable) currentfield).getPawned() == false) {
						return true;
					}
				}
			} else if (f instanceof Labor) {
				Labor labor = (Labor) f;

				if (labor.getName().equals(((Ownable) currentfield).getName())) {
					if (((Ownable) currentfield).fieldowned == false && ((Ownable) currentfield).getPawned() == false) {
						return true;
					}
				}

			} else {
				// The field cannot be owned
			}

		}
		return false;

	}

	public void showMenu(Player[] players, Player player, Field currentfield, Field[] fields) {
		boolean res;
		do{
			res = menuBuild(players, player, currentfield, fields);
		}while(!res);
	}

}
