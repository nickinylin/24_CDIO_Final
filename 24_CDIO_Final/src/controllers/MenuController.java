package controllers;

import desktop_resources.GUI;
import fields.*;
import game.Player;

public class MenuController {

	public boolean menuBuild(Player[] players, Player player, Field currentfield, Field[] fields) {

		int count = 0;
		String[] tempmenu = new String[9];
		boolean nextTurn = false;

		final String button1 = "Køb felt";
		final String button2 = "Sælg et felt";
		final String button3 = "Pantsæt";
		final String button4 = "Køb Bygning";
		final String button5 = "Sælg Bygning";
		final String button6 = "Afslut Tur";
		final String button7 = "Gå Bankerot";
		final String button8 = "Sælg Alle Felter";

		if (checkYouCanBuyField(player, currentfield, fields)) {
			tempmenu[count++] = button1;
		}
		if (checkOwnField(player, currentfield, fields)) {
			tempmenu[count++] = button2;
		}
		if (checkPawnField(player, currentfield, fields)|| checkUnPawnField(player, currentfield, fields)) {
			tempmenu[count++] = button3;
		}
		if (checkBuyBuilding(player, currentfield, fields)) {
			tempmenu[count++] = button4;
		}
		if (checkBuildingExists(player, currentfield, fields)){
			tempmenu[count++] = button5;
		}
		if(player.getMoney()>=0) {
			tempmenu[count++] = button6;
		} else {
			tempmenu[count++] = button7;
		}

		String[] menu = new String[count];

		for (int i = 0; i < menu.length; i++) {
			menu[i] = tempmenu[i];
		}

		String button = GUI.getUserButtonPressed(player.getName(), menu);

		switch(button) {

		case button1:

			//			if (player.getMoney() < ((Ownable) currentfield).getPrice()) {
			//				getRaiseMoney(player, currentfield, fields);
			//			} else {
			((Ownable) currentfield).buyField(player, fields);
			GUI.setBalance(player.getName(), player.getMoney());
			((Ownable) currentfield).updateFieldGroup(player, currentfield, fields);
			//			}
			break;

		case button2:
			String[] fieldlist = getPlayerOwnedFields(player, fields);

			String buyfield = GUI.getUserSelection(player.getName(), fieldlist);

			String sellto = GUI.getUserButtonPressed(player.getName(), "Sælg felt til Spillere", "Fortryd");


			if (sellto == "Sælg felt til Spillere") {

				String[] playernames = getAllPlayerNamesExceptPlayer(players, player);

				String spiller = GUI.getUserSelection("Sælg "+buyfield+" til", playernames);
				int g = 0;
				Player[] buyingplayer = new Player[1];
				for (int z = 0; z < players.length ; z++) {
					if (players[z].getName() == spiller) {
						buyingplayer[g++] = players[z];
					}
				}

				for (int i = 0; i < fields.length; i++){
					Field f = fields[i];

					//				for (Field f : fields) {
					if (f instanceof Territory) {
						Territory territory = (Territory) f;

						if (buyfield == territory.getName()) {
							territory.buyField(buyingplayer[0], fields);
							int pris = GUI.getUserInteger("Pris");
							player.giveMoney(pris);
							player.setAssets(-territory.getPrice());
							buyingplayer[0].giveMoney(territory.getPrice());
							buyingplayer[0].payMoney(pris);
							buyingplayer[0].setAssets(buyingplayer[0].getAssets()+territory.getPrice());
							GUI.setBalance(buyingplayer[0].getName(), buyingplayer[0].getMoney());
							GUI.setBalance(player.getName(), player.getMoney());
							territory.updateFieldGroup(buyingplayer[0], territory, fields);
							if (((Territory) f).getPawned()) {
								GUI.setTitleText(i++, f.getName());
								GUI.setSubText(i++, "Pantsat");
							}
						}
					} else if (f instanceof Fleet) {
						Fleet fleet = (Fleet) f;

						if (buyfield == fleet.getName()) {
							fleet.buyField(buyingplayer[0], fields);
							int pris = GUI.getUserInteger("Pris");
							player.giveMoney(pris);
							player.setAssets(-fleet.getPrice());
							buyingplayer[0].giveMoney(fleet.getPrice());
							buyingplayer[0].payMoney(pris);
							GUI.setBalance(buyingplayer[0].getName(), buyingplayer[0].getMoney());
							GUI.setBalance(player.getName(), player.getMoney());
							fleet.updateFieldGroup(buyingplayer[0], fleet, fields);
							if (((Fleet) f).getPawned()) {
								GUI.setTitleText(i++, f.getName());
								GUI.setSubText(i++, "Pantsat");
							}
						}
					} else if (f instanceof Labor) {
						Labor labor = (Labor) f;

						if (buyfield == labor.getName()) {
							labor.buyField(buyingplayer[0], fields);
							int pris = GUI.getUserInteger("Pris");
							player.giveMoney(pris);
							player.setAssets(-labor.getPrice());
							buyingplayer[0].giveMoney(labor.getPrice());
							buyingplayer[0].payMoney(pris);
							GUI.setBalance(buyingplayer[0].getName(), buyingplayer[0].getMoney());
							GUI.setBalance(player.getName(), player.getMoney());
							labor.updateFieldGroup(buyingplayer[0], labor, fields);
							if (((Labor) f).getPawned()) {
								GUI.setTitleText(i++, f.getName());
								GUI.setSubText(i++, "Pantsat");
							}

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

				String[] pawnfieldlist = getPlayerOwnedActiveFields(player, fields);

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

				//				 String[] buyBuildingFieldlist = getBuyBuildingFieldList(player, fields);
				String[] buyBuildingFieldlist = getWhereToBuyBuilding(player,(Territory) currentfield ,fields);

				String buyBuildingField = GUI.getUserSelection(player.getName(), buyBuildingFieldlist);


				Territory[] thisfield = new Territory[3];
				int i = 0;
				int getfieldnumber = 0;

				for (int x = 0; x < fields.length; x++) {
					Field f = fields[x];

					if (f instanceof Territory) {
						Territory t = (Territory) f;

						if (buyBuildingField.equals(t.getName())) {
							thisfield[i++] = t;
							getfieldnumber = x+1;
						}

					}
				}

				int houseCount = thisfield[0].getHouse();
				thisfield[0].buyHouse();
				thisfield[0].updateFieldGroup(player, thisfield[0], fields);
				player.payMoney(thisfield[0].getBuildingPrice());

				player.setAssets(thisfield[0].getBuildingPrice());

				GUI.setBalance(player.getName(), player.getMoney());
				if (thisfield[0].getHouse() > 4) {
					GUI.setHotel(getfieldnumber, true);
				} else {
					GUI.setHouses(getfieldnumber, houseCount+1);
				}

			}
			break;
		case button5: // sælg bygning
			nextTurn = false;

			if (checkBuildingExists(player, currentfield, fields)) {
				String[] sellBuildingFieldlist = getWhereToSellBuilding(player, fields);
				String regretBuildingSale = GUI.getUserButtonPressed(player.getName(), "Sælg bygning", "Fortryd");
				if(regretBuildingSale == "Sælg bygning"){
					String sellBuildingField = GUI.getUserSelection(player.getName(), sellBuildingFieldlist);



					Territory[] thisfield = new Territory[3];
					int i = 0;
					int getfieldnumber = 0;

					for (int x = 0; x < fields.length; x++) {
						Field f = fields[x];

						if (f instanceof Territory) {
							Territory t = (Territory) f;

							if (sellBuildingField.equals(t.getName())) {
								thisfield[i++] = t;
								getfieldnumber = x+1;
							}

						}
					}

					thisfield[0].sellHouse();
					GUI.setHouses(getfieldnumber, thisfield[0].getHouse());
					thisfield[0].updateFieldGroup(player, thisfield[0], fields);
					player.giveMoney(thisfield[0].getBuildingPrice());
					player.setAssets(player.getAssets()-thisfield[0].getBuildingPrice());
					GUI.setBalance(player.getName(), player.getMoney());
				}
			}
			break;
		case button6: // Afslut tur
			if(player.getMoney() < 0) {
				return false;
			} else {
				nextTurn = true; break;
			}

		case button7: // Gå bankerot
			nextTurn = true;
			player.bankrupt(); // Spilleren fjernes
			sellAllFields(player, fields);
			break;

		case button8:
			sellAllFields(player, fields);

		default: break;
		}
		return nextTurn;

	}


	//	private void getRaiseMoney(Player player, Field currentfield, Field[] fields) {
	//		//TODO
	//		int count = 0;
	//		
	//		final String button1 = "Sælg Alle Felter";
	//		final String button2 = "Sælg et felt";
	//		final String button3 = "Pantsæt et felt";
	//		final String button4 = "Sælg Bygning";
	//		final String button5 = "Gå Bankerot";
	//		final String button6 = "Afslut Tur";
	//
	//		String[] tempmenu = new String[9];
	//		
	//		if (checkOwnField(player, currentfield, fields)) {
	//			tempmenu[count++] = button1;
	//			tempmenu[count++] = button2;
	//		}
	//		if (checkPawnField(player, currentfield, fields)) {
	//			tempmenu[count++] = button3;
	//		}
	//		if (checkBuildingExists(player, currentfield, fields)){
	//			tempmenu[count++] = button4;
	//		}
	//		
	//	}


	private boolean checkPawnField(Player player, Field currentfield, Field[] fields) {
		int i = 0;
		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (t.getPawned()==false && player.equals(t.getOwner()) && t.getHouse() < 1) {
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

	private String[] getBuildingFieldList(Player player, Field[] fields) {

		int i = 0;

		Territory[] tempfields = new Territory[28];

		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (t.getPawned() == false && player.equals(t.getOwner()) && t.getHouse() > 0) {
					tempfields[i++] = t;
				}
			} 
		}


		String[] fieldlist = new String[i];

		for (int x = 0; x < fieldlist.length; x++) {
			fieldlist[x] = tempfields[x].getName();
		}
		return fieldlist;
	}

	private String[] getWhereToBuyBuilding(Player player,Territory currentfield, Field[] fields) {

		int i = 0;

		Territory[] tempfields = new Territory[8];

		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f; 
				if (t.getPawned() == false && player.equals(t.getOwner()) && currentfield.getFieldGroup()==t.getFieldGroup()) {
					tempfields[i] = t;
					++i;
				}
			} 
		}

		String[] fieldlist = new String[i];
		int x = 0;
		if (i == 2) {
			if (tempfields[0].getHouse() == 5 && tempfields[1].getHouse() == 5 && tempfields[2].getHouse() == 5) {

			} else if (tempfields[0].getHouse() == tempfields[1].getHouse()) {
				fieldlist[x] = tempfields[0].getName();
				fieldlist[x+1] = tempfields[1].getName();
			} else if (tempfields[0].getHouse() < tempfields[1].getHouse()) {
				fieldlist[x] = tempfields[0].getName();
			} else if (tempfields[0].getHouse() > tempfields[1].getHouse()) {
				fieldlist[x+1] = tempfields[1].getName();
			}
		} else {
			if (tempfields[0].getHouse() == tempfields[1].getHouse() && tempfields[0].getHouse() == tempfields[2].getHouse()) {
				fieldlist[x] = tempfields[0].getName();
				fieldlist[x+1] = tempfields[1].getName();
				fieldlist[x+2] = tempfields[2].getName();
			} else if (tempfields[0].getHouse() == tempfields[1].getHouse() && tempfields[2].getHouse() > tempfields[0].getHouse()) {
				fieldlist[x] = tempfields[0].getName();
				fieldlist[x+1] = tempfields[1].getName();
			} else if (tempfields[0].getHouse() == tempfields[2].getHouse() && tempfields[1].getHouse() > tempfields[0].getHouse()) {
				fieldlist[x] = tempfields[0].getName();
				fieldlist[x+1] = tempfields[2].getName();
			} else if (tempfields[1].getHouse() == tempfields[2].getHouse() && tempfields[0].getHouse() > tempfields[2].getHouse()) {
				fieldlist[x] = tempfields[1].getName();
				fieldlist[x+1] = tempfields[2].getName();
			} else if (tempfields[0].getHouse() == tempfields[1].getHouse() && tempfields[2].getHouse() < tempfields[0].getHouse()) {
				fieldlist[x] = tempfields[2].getName();
			} else if (tempfields[1].getHouse() == tempfields[2].getHouse() && tempfields[0].getHouse() < tempfields[1].getHouse()) {
				fieldlist[x] = tempfields[0].getName();
			} else if (tempfields[0].getHouse() == tempfields[2].getHouse() && tempfields[1].getHouse() < tempfields[0].getHouse()) {
				fieldlist[x] = tempfields[1].getName();
			}



		}

		String string = new String();

		for (int j = 0; j < fieldlist.length; j++) {
			if (fieldlist[j] != null) {
				string+=fieldlist[j]+"Q";
			}
		}

		return string.substring(0,string.length()-1).split("Q");
	}

	private String[] getWhereToSellBuilding(Player player, Field[] fields) {

		int i = 0;

		Territory[] tempfields = new Territory[22];

		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f; 
				if (t.getPawned() == false && player.equals(t.getOwner()) && t.getHouse()>0) {
					tempfields[i] = t;
					++i;
				}
			} 
		}

		String[] fieldlist = new String[i];
		int x = 0;
		if (i == 1) {
			fieldlist[x] = tempfields[0].getName();
		} else if (i == 2) {
			if (tempfields[0].getHouse() == tempfields[1].getHouse()) {
				fieldlist[x] = tempfields[0].getName();
				fieldlist[x+1] = tempfields[1].getName();
			} else if (tempfields[0].getHouse() > tempfields[1].getHouse()) {
				fieldlist[x] = tempfields[0].getName();
			} else if (tempfields[0].getHouse() < tempfields[1].getHouse()) {
				fieldlist[x] = tempfields[1].getName();
			}
		} else if (i == 3){
			if (tempfields[0].getHouse() == tempfields[1].getHouse() && tempfields[0].getHouse() == tempfields[2].getHouse()) {
				fieldlist[x] = tempfields[0].getName();
				fieldlist[x+1] = tempfields[1].getName();
				fieldlist[x+2] = tempfields[2].getName();
			} else if (tempfields[0].getHouse() == tempfields[1].getHouse() && tempfields[2].getHouse() > tempfields[0].getHouse()) {
				fieldlist[x] = tempfields[2].getName();
			} else if (tempfields[0].getHouse() == tempfields[2].getHouse() && tempfields[1].getHouse() > tempfields[0].getHouse()) {
				fieldlist[x] = tempfields[1].getName();
			} else if (tempfields[1].getHouse() == tempfields[2].getHouse() && tempfields[0].getHouse() > tempfields[2].getHouse()) {
				fieldlist[x] = tempfields[0].getName();
			} else if (tempfields[0].getHouse() == tempfields[1].getHouse() && tempfields[2].getHouse() < tempfields[0].getHouse()) {
				fieldlist[x] = tempfields[0].getName();
				fieldlist[x+1] = tempfields[1].getName();
			} else if (tempfields[1].getHouse() == tempfields[2].getHouse() && tempfields[0].getHouse() < tempfields[1].getHouse()) {
				fieldlist[x] = tempfields[1].getName();
				fieldlist[x+1] = tempfields[2].getName();
			} else if (tempfields[0].getHouse() == tempfields[2].getHouse() && tempfields[1].getHouse() < tempfields[0].getHouse()) {
				fieldlist[x] = tempfields[0].getName();
				fieldlist[x] = tempfields[2].getName();
			}
		}

		String string = new String();

		for (int j = 0; j < fieldlist.length; j++) {
			if (fieldlist[j] != null) {
				string+=fieldlist[j]+"Q";
			}
		}

		return string.substring(0,string.length()-1).split("Q");
	}



	private String[] getPlayerOwnedActiveFields(Player player, Field[] fields) {

		int i = 0;

		Field[] tempownedfields = new Field[28];

		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (player.equals(t.getOwner()) && t.getPawned() == false && t.getHouse() < 1) {
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

	private String[] getPlayerOwnedFields(Player player, Field[] fields) {

		int i = 0;

		Field[] tempownedfields = new Field[28];

		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (player.equals(t.getOwner()) && t.getHouse() < 1 && t.isSellAble()) {
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
				if (player.equals(t.getOwner())&& t.getHouse() < 1 && t.isSellAble()) {
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
			// Brugeren ejer ikke alle felter
			if (!(numberofgroupfields == numberofownedfields && numberofgroupfields != 0)) {
				return false;
			}

			for (int x = 0; x < ownedfields.length; x++) {
				if (ownedfields[x] != null && ownedfields[x].getHouse() < 5) {
					return true;
				}
			}
		}

		return false;
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

			}  else if (currentfield.getName() == "De fængsles") {
				JailController jailcontroller = new JailController();
				jailcontroller.jail(player, fields);

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

	public void sellAllFields(Player player, Field[] fields) {

		for (int i = 0; i < fields.length; i++) {

			if (fields[i] instanceof Territory) {
				Territory territory = (Territory) fields[i];
				if (territory.fieldowned && territory.fieldowner.equals(player)) {
					territory.fieldowned = false;
					territory.fieldowner = null;
					GUI.removeOwner(i+1);
				}
			}

			if (fields[i] instanceof Labor) {
				Labor labor = (Labor) fields[i];
				if (labor.fieldowned && labor.fieldowner.equals(player)) {
					labor.fieldowned = false;
					labor.fieldowner = null;
					GUI.removeOwner(i+1);
				}
			}

			if (fields[i] instanceof Fleet) {
				Fleet fleet = (Fleet) fields[i];
				if (fleet.fieldowned && fleet.fieldowner.equals(player)) {
					fleet.fieldowned = false;
					fleet.fieldowner = null;
					GUI.removeOwner(i+1);
				}
			}

		}
	}


	public void showMenu(Player[] players, Player player, Field currentfield, Field[] fields) {
		boolean res;
		do{
			res = menuBuild(players, player, currentfield, fields);
		}while(!res);
	}

}
