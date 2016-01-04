/**
 * Territory
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package game;

public class Territory extends Ownable {
    
    protected String fieldname;
    private int fieldrent;

    public Territory(String name, int fieldrent, int fieldvalue) {
        super(name);
        this.fieldrent = fieldrent;
        super.fieldprice = fieldvalue;
        super.fieldowned=false;
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
}