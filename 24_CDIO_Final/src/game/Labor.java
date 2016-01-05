/**
 * Labor
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package game;

public class Labor extends Ownable {
    
    protected String fieldname;
    Dice dice = new Dice();
     
    public Labor(String name) {
        super(name);
        super.fieldprice=3000;
        super.fieldowned=false;
    }

    @Override
    public int getRent() {
        int fieldrent = 100 * Dice.getSum();
        return fieldrent;
    }

    @Override
    public String getName() {
        return fieldname;
    }

    public int getPrice() {
        return super.fieldprice;
    }

}
