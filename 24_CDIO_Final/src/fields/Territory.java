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
	public int getRent(Field[] fields) {
		// TODO her mangler vi at den udregner hvad lejen er med antal huse
		return fieldrent;
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

	public int getFieldrent() {
		return fieldrent;
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
					GUI.setSubText(i+1, "Leje: "+territory.getRent(fields)+"");
				}
			} 
		}

	}
}