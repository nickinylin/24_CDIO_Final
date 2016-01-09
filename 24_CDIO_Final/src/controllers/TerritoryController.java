package controllers;

import desktop_resources.GUI;
import fields.Field;
import fields.Ownable;
import fields.Territory;
import game.Player;

public class TerritoryController {

	public void landOnTerritory(Player[] players, Player player, Territory territory, Field[] fields) {

		if (territory.fieldowned) {

			if (player.equals(territory.fieldowner)) {

				checkBuyBuilding(player, territory, fields);
			}
		} else {

			checkBuyField(player, territory, fields);
			menuBuild(players, player, territory, fields);
		}
	}


	private void menuBuild(Player[] players, Player player, Territory currentfield, Field[] fields) {

		int count = 0;
		String[] tempmenu = new String[7];
		boolean ownsfields = false;

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
		}
		if (checkPawnField(player, currentfield, fields)) {
			tempmenu[count++] = button3;
		}

		if (checkBuyBuilding(player, currentfield, fields)) {
			tempmenu[count++] = button4;
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
			currentfield.buyField(player, fields);
			GUI.setBalance(player.getName(), player.getMoney());
			currentfield.updateFieldGroup(player, currentfield, fields);
			break;

		case button2:
			String[] fieldlist = getPlayerOwnedFields(player, fields);

			String buyfield = GUI.getUserSelection(player.getName(), fieldlist);

			String sellto = GUI.getUserButtonPressed(player.getName(), "Sælg felt til Spillere", "Sælg felt til Bank", "Fortryd");

			if (sellto == "Sælg felt til Spillere") {

				Territory[] thisfield = new Territory[1];
				int k = 0;
				for (Field f : fields) {
					if (f instanceof Territory) {
						Territory t = (Territory) f;

						if (buyfield == t.getName()) {
							thisfield[k++] = t;
						}
					}
				}

				String[] playernames = getAllPlayerNamesExceptPlayer(players, player);

				String spiller = GUI.getUserSelection("Sælg "+buyfield+" til", playernames);
				int g = 0;
				Player[] buyplayer = new Player[1];
				for (int z = 0; z < players.length ; z++) {
					if (players[z].getName() == spiller) {
						buyplayer[g++] = players[z];
					}
				}

				thisfield[0].buyField(buyplayer[0], fields);
				int pris = GUI.getUserInteger("Pris");
				player.giveMoney(pris);
				buyplayer[0].giveMoney(thisfield[0].getPrice());
				buyplayer[0].payMoney(pris);
				GUI.setBalance(buyplayer[0].getName(), buyplayer[0].getMoney());
				GUI.setBalance(player.getName(), player.getMoney());

				thisfield[0].updateFieldGroup(buyplayer[0], thisfield[0], fields);

			} else if (sellto == "Sælg felt til Bank"){

				Territory[] thisfield = new Territory[1];
				int x = 0;
				int getfieldnumber = 0;

				for (int w = 0; w < fields.length; w++) {
					Field f = fields[w];

					if (f instanceof Territory) {
						Territory t = (Territory) f;

						if (buyfield == t.getName()) {
							thisfield[x++] = t;
							getfieldnumber = w+1;
						}

					}
				}

				thisfield[0].setOwner(null);
				thisfield[0].setOwnedToFalse();

				player.giveMoney(thisfield[0].getPrice());
				GUI.setBalance(player.getName(), player.getMoney());

				GUI.removeOwner(getfieldnumber);
				GUI.setSubText(getfieldnumber, "Pris: "+thisfield[0].getPrice());
			}
			break;

		case button3:

			String pantsæt = GUI.getUserButtonPressed(player.getName(), "Pantsæt Felt", "Tilbagekøb af Felt", "Fortryd");

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

				thisfield[0].setOwner(null);
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
				
				thisfield[0].setOwner(player);
				thisfield[0].setOwned();
				thisfield[0].setPawned(false);
				
				player.payMoney((int) (thisfield[0].getPawnPrice()));
				GUI.setBalance(player.getName(), player.getMoney());

				GUI.setTitleText(getfieldnumber, thisfield[0].getName());
				GUI.setSubText(getfieldnumber, "Leje: "+thisfield[0].getRent(player, fields));
			}
			break;
		default:
		}

	}


	private boolean checkPawnField(Player player, Territory currentfield, Field[] fields) {
		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (t.getPawned() && player.equals(t.getOwner())) {
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

		int numberofpawnedfields = 0;
		int i = 0;

		Territory[] temppawnedfields = new Territory[22];

		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (t.getPawned() && player.equals(t.getOwner())) {
					numberofpawnedfields++;
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

		int numberofownedfields = 0;
		int i = 0;

		Territory[] tempownedfields = new Territory[22];

		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (player.equals(t.getOwner())) {
					numberofownedfields++;
					tempownedfields[i++] = t;
				}
			}
		}

		String[] fieldlist = new String[i];

		for (int x = 0; x < fieldlist.length; x++) {
			fieldlist[x] = tempownedfields[x].getName();
		}
		return fieldlist;
	}


	private boolean checkOwnField(Player player, Territory currentfield, Field[] fields) {

		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (player.equals(t.getOwner())) {
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
					if (player.equals(t.getOwner())) {
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

	private boolean checkBuyField(Player player, Territory currentfield, Field[] fields) {

		if (currentfield.fieldowned) {
			return false;
		} else {
			return true;
		}

	}

}
