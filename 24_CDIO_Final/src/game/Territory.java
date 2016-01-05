/**
 * Territory
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package game;

public class Territory extends Ownable {
    
    protected String fieldname;
    private int fieldPriceBuilding;
    private int fieldrent;
    private int fieldrenthouse1;
    private int fieldrenthouse2;
    private int fieldrenthouse3;
    private int fieldrenthouse4;
    private int fieldrenthotel;
    private final int fieldgroup;

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
    public int getRent() {
        return fieldrent;
    }
    
    public String getName() {
        return fieldname;
    }

    @Override
    public int getPrice() {
        return fieldprice;
    }

	public String getFieldname() {
		return fieldname;
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
}