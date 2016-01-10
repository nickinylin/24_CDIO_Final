package controllers;

import desktop_fields.Ownable;
import desktop_resources.GUI;
import fields.Field;
import fields.Fleet;
import fields.Labor;
import fields.Territory;
import game.Dice;
import game.Player;

public class MenuController {

	public boolean menuBuild(Player[] players, Player player, Territory currentfield, Field[] fields) {

		int count = 0;
		String[] tempmenu = new String[7];
		boolean nextTurn = false;

		final String button1 = "Køb feltet";
		final String button2 = "Sælg felt";
		final String button3 = "Pantsæt";
		final String button4 = "Køb Bygning";
		final String button5 = "Sælg Bygning";
		final String button6 = "Afslut";

		if (checkBuyField(player, currentfield, fields)) {
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
			currentfield.buyField(player, fields);
			GUI.setBalance(player.getName(), player.getMoney());
			currentfield.updateFieldGroup(player, currentfield, fields);
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
			String pantsæt;

			if (checkPawnField(player, currentfield, fields)) {
				pantsæt = GUI.getUserButtonPressed(player.getName(), "Pantsæt Felt", "Tilbagekøb af Felt", "Fortryd");
			} else {
				pantsæt = GUI.getUserButtonPressed(player.getName(), "Pantsæt Felt", "Fortryd");
			}

			if (pantsæt == "Pantsæt Felt") {

				String[] pawnfieldlist = getPlayerOwnedFields(player, fields);

				String pawnfield = GUI.getUserSelection(player.getName(), pawnfieldlist);

				Territory[] thisfield = new Territory[1];
				int x = 0;
				int getfieldnumber = 0;

				for (int w = 0; w < fields.length; w++) {
					Field f = fields[w];

					if (f instanceof Territory) {
						Territory t = (Territory) f;

						if (pawnfield == t.getName()) {
							thisfield[x++] = t;
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

				Territory[] thisfield = new Territory[1];
				int x = 0;
				int getfieldnumber = 0;

				for (int w = 0; w < fields.length; w++) {
					Field f = fields[w];

					if (f instanceof Territory) {
						Territory t = (Territory) f;

						if (pawnfield == t.getName()) {
							thisfield[x++] = t;
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
				// get fields where group is owned
				// buy house for selected field
				// house can only be bought if other fields have houses
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


	private boolean checkPawnField(Player player, Territory currentfield, Field[] fields) {
		int i = 0;
		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (t.getPawned() && player.equals(t.getOwner())) {
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

		Territory[] temppawnedfields = new Territory[22];

		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (t.getPawned() && player.equals(t.getOwner())) {
					temppawnedfields[i++] = t;
				}
			}
		}

		String[] fieldlist = new String[i];

		for (int x = 0; x < fieldlist.length; x++) {
			fieldlist[x] = temppawnedfields[x].getName();
		}
		return fieldlist;
	}

	private String[] getPlayerOwnedFields(Player player, Field[] fields) {

		int i = 0;

		Field[] tempownedfields = new Field[28];

		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (player.equals(t.getOwner())) {
					tempownedfields[i++] = t;
				}
			} else if (f instanceof Fleet) {
				Fleet fleet = (Fleet) f;

				if (player.equals(fleet.getOwner())) {
					tempownedfields[i++] = fleet;
				}
			} else if (f instanceof Labor) {
				Labor labor = (Labor) f;

				if (player.equals(labor.getOwner())) {
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


	public boolean checkBuyBuilding(Player player, Territory currentfield, Field[] fields) {

		int numberofgroupfields = 0;
		int numberofownedfields = 0;
		int i = 0;

		Territory[] ownedfields = new Territory[3];

		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (t.getFieldGroup() == currentfield.getFieldGroup()) {
					numberofgroupfields++;
					if (player.equals(t.getOwner()) && t.getPawned() == false) {
						ownedfields[i++] = t;
						numberofownedfields++;
					}
				}
			}
		}

		if (numberofgroupfields == numberofownedfields) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkBuildingExists(Player player, Territory currentfield, Field[] fields) {

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

	private boolean checkBuyField(Player player, Territory currentfield, Field[] fields) {

		if (currentfield.fieldowned == false && currentfield.getPawned()) {
			return false;
		} else {
			return true;
		}

	}

}
