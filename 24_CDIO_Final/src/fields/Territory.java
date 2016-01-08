/**
 * Territory
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package fields;

import desktop_resources.GUI;
import game.Player;

public class Territory extends Ownable {

	private int fieldPriceBuilding;
	private int fieldrent;
	private int houses = 0;
	private int fieldrenthouse1;
	private int fieldrenthouse2;
	private int fieldrenthouse3;
	private int fieldrenthouse4;
	private int fieldrenthotel;
	private final int fieldgroup;
	private int buildingNumbers = 0;
	private boolean pawned;

	public Territory(String name, int fieldgroup, int fieldvalue, int fieldPriceBuilding, int fieldrent, int fieldrenthouse1, int fieldrenthouse2,int fieldrenthouse3, int fieldrenthouse4, int fieldrenthotel) {
		super(name);
		super.fieldowned=false;
		super.fieldprice = fieldvalue;
		this.fieldPriceBuilding = fieldPriceBuilding;
		this.fieldrent = fieldrent;
		this.fieldrenthouse1 = fieldrenthouse1;
		this.fieldrenthouse2 = fieldrenthouse2;
		this.fieldrenthouse3 = fieldrenthouse3;
		this.fieldrenthouse4 = fieldrenthouse4;
		this.fieldrenthotel = fieldrenthotel;
		this.fieldgroup = fieldgroup;
	}



	@Override
	public int getRent(Player player, Field[] fields) {
		// TODO her mangler vi at den udregner hvad lejen er med antal huse
		// Send field med?
		int numberingroup = 0;
		int numberofowned = 0;
		
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			
			if (f instanceof Territory) {
				Territory territory = (Territory) f;

				if (fieldgroup == territory.getFieldGroup()) {
					numberingroup++;
					if (player.equals(territory.fieldowner)) {
						numberofowned++;
					}
				}
				
			}
		}
		
		if (numberingroup == numberofowned) {
			return fieldrent * 2;
		} else {
			return fieldrent;
		}
		
	}

	public void setPawned(boolean pawn) {
		this.pawned = pawn;
	}

	public boolean getPawned() {
		return pawned;
	}

	public double getPawnPrice() {
		double pawnprice = ((int) fieldprice * 0.5);
		return pawnprice;
	}


	public int getPrice() {
		return fieldprice;
	}

	public int buyHouse() {
		houses = houses + 1;
		return houses;
	}

	public int sellHouse() {
		houses = houses - 1;
		return houses;
	}

	public int getHouse() {
		return houses;
	}

	public int getFieldrenthouse1() {
		return fieldrenthouse1;
	}

	public int getFieldrenthouse2() {
		return fieldrenthouse2;
	}

	public int getFieldrenthouse3() {
		return fieldrenthouse3;
	}

	public int getFieldrenthouse4() {
		return fieldrenthouse4;
	}

	public int getFieldrenthotel() {
		return fieldrenthotel;
	}

	public int getFieldPriceBuilding() {
		return fieldPriceBuilding;
	}

	public int getFieldGroup() {
		return fieldgroup;
	}



	public int getBuildingNumbers() {
		return buildingNumbers;
	}



	public void setBuildingNumbers(int buildingNumbers) {
		this.buildingNumbers = buildingNumbers;
	}



	@Override
	public void buyField(Player player, Field[] fields) {
		player.payMoney(fieldprice);
		player.setAssets(fieldprice);
		fieldowned = true;
		fieldowner = player;
	}



	@Override
	public void updateFieldGroup(Player player, Field currentField, Field[] fields) {
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			if (f instanceof Territory) {
				Territory territory = (Territory) f;

				if (territory.getFieldGroup() == ((Territory) currentField).getFieldGroup() && player.equals(territory.fieldowner)) {
					GUI.setOwner(i+1, player.getName());
					GUI.setSubText(i+1, "Leje: "+territory.getRent(player, fields)+"");
				}
			} 
		}

	}



	public int getBaseRent() {
		return fieldrent;
	}


	public void sellFieldToPlayer(Player[] players, Player currentplayer, Territory currentfield, Field[] fields) {
		
		int pris = GUI.getUserInteger("Pris");
		
		String[] playernames = new String[players.length-1];
		int j = 0;
		
		for (int i = 0; i < players.length ; i++) {
			if (currentplayer.getName().equals(players[i].getName())) {
				
			} else {
				playernames[j] = players[i].getName();
				j++;
			}
		}
		
		String spiller = GUI.getUserSelection("Sælg til", playernames);
		currentplayer.giveMoney(pris);
		
		for (int i = 0; i < players.length ; i++) {
			if (players[i].getName() == spiller) {
				players[i].payMoney(pris);
				GUI.setBalance(players[i].getName(), players[i].getMoney());
			}
		}

		
	}

	public void sellFieldToBank(Player player, Territory currentfield, Field[] fields) {
		
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			if (f instanceof Territory) {
				Territory territory = (Territory) f;

				if (territory.getFieldGroup() == currentfield.getFieldGroup() && player.equals(territory.fieldowner)) {
					int price = (int) (currentfield.fieldprice);
					player.giveMoney(price);
					GUI.removeOwner(i);
				}
			} 
		}
	}
}