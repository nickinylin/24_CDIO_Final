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
	private boolean sellAble = true;

	public Territory(String name, int fieldgroup, int fieldvalue, int fieldPriceBuilding, int fieldrent, int fieldrenthouse1, int fieldrenthouse2,int fieldrenthouse3, int fieldrenthouse4, int fieldrenthotel) {
		super(name);
		super.fieldowned = false;
		super.fieldprice = fieldvalue;
		this.fieldPriceBuilding = fieldPriceBuilding;
		this.fieldrent = fieldrent;
		this.fieldrenthouse1 = fieldrenthouse1;
		this.fieldrenthouse2 = fieldrenthouse2;
		this.fieldrenthouse3 = fieldrenthouse3;
		this.fieldrenthouse4 = fieldrenthouse4;
		this.fieldrenthotel = fieldrenthotel;
		this.fieldgroup = fieldgroup;
		this.sellAble = true;
	}



	@Override
	public int getRent(Player playerOwner, Field[] fields) {
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
					if (playerOwner.equals(territory.fieldowner)) {
						numberofowned++;
					}
				}

			}
		}

		int rent = 0;
		if (numberingroup == numberofowned) {

			if (houses > 0) {
				if (houses == 1) {
					rent = this.fieldrenthouse1;
				} else if (houses == 2) {
					rent = this.fieldrenthouse2;
				} else if (houses == 3) {
					rent = this.fieldrenthouse3;
				} else if (houses == 4) {
					rent = this.fieldrenthouse4;
				} else if (houses == 5) {
					rent = this.fieldrenthotel;
				}
			} else {
				rent = fieldrent * 2;
			}

		} else {
			rent =  fieldrent;
		}
		return rent;
	}

	@Override
	public void setPawned(boolean pawn) {
		this.pawned = pawn;
	}

	@Override
	public boolean getPawned() {
		return pawned;
	}

	@Override
	public int getPawnPrice() {
		int pawnprice = (int) (fieldprice * 0.5);
		return pawnprice;
	}


	public int getPrice() {
		return fieldprice;
	}

	public int buyHouse(Field currentfield, Field[] fields) {
		houses = houses + 1;
		int fieldgroup = ((Territory) currentfield).getFieldGroup();
		
		for (Field f : fields) {
			if (f instanceof Territory) {
				Territory t = (Territory) f;

				if (t.getFieldGroup() == fieldgroup) {
					t.setSellAble(false);
				}
				
			}
		}


		return houses;
	}

	public int sellHouse() {

		if (getHouse() == 1){
			setSellAble(true);
		}
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

	public int getBuildingPrice() {
		return fieldPriceBuilding;
	}

	public int getFieldGroup() {
		return fieldgroup;
	}

	public void setBuildingNumbers(int buildingNumbers) {
		this.houses = buildingNumbers;
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


	public Player sellFieldToPlayer(Player[] players, Player currentplayer, Territory currentfield, Field[] fields) {

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

		for (int i = 0; i < players.length ; i++) {
			if (players[i].getName() == spiller) {
				return players[i];
			}
		}
		return currentplayer;
	}

	//	public void sellFieldToBank(Player player, Territory currentfield, Field[] fields) {
	//
	//		for (int i = 0; i < fields.length; i++) {
	//			Field f = fields[i];
	//			if (f instanceof Territory) {
	//				Territory territory = (Territory) f;
	//
	//				if (territory.getFieldGroup() == currentfield.getFieldGroup() && player.equals(territory.fieldowner)) {
	//					int price = (int) (currentfield.fieldprice);
	//					player.giveMoney(price);
	//					GUI.removeOwner(i);
	//				}
	//			} 
	//		}
	//	}

	@Override
	public String getFieldType() {
		return "Territory";
	}



	public boolean isSellAble() {
		return sellAble;
	}



	public void setSellAble(boolean sellAble) {
		this.sellAble = sellAble;
	}
	
	public int getMaxHousesGroup (Territory[] fields, int group) {
		int max = 0;
		for (int i = 0; i < fields.length; i++){
			if(fields[i] != null){
			if (fields[i].getHouse() >= max && fields[i].getFieldGroup() == group){
					max = fields[i].getHouse();	
			} else {
				System.out.println("NFEJL....");
			}
			}
		}
		return max;
	}


	public boolean groupissellable(Territory[] fields, int group) {
		boolean sellable=false;
		int fieldsingroup=0;
		int y=0;
		for (int i = 0; i < fields.length; i++){
			if(fields[i] != null){
			if (fields[i].getFieldGroup() == group){
				fieldsingroup++;
			}
			}
		}
			Territory[] tempfields =new Territory[fieldsingroup];
			for (int x = 0; x < fields.length; x++){
				if(fields[x] != null){
				if (fields[x].getFieldGroup() == group){
					tempfields[y++]=fields[x];
				}
				}
			}
				switch(fieldsingroup){
				case 3:
					if(tempfields[0].fieldowned && tempfields[1].fieldowned && tempfields[2].fieldowned)
					if(tempfields[0].isSellAble() && tempfields[1].isSellAble() &&tempfields[2].isSellAble()){
					sellable=true;
					break;					
					}
				case 2:
					if(tempfields[0].fieldowned && tempfields[1].fieldowned)
					if(tempfields[0].isSellAble() && tempfields[1].isSellAble()){
					sellable=true;
					break;
					}
					default:
						sellable=true;
						System.out.println("Uejet");
				
				}
		
		return sellable;
	}
}