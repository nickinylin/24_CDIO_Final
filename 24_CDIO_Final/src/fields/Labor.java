/**
 * Labor
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package fields;

import game.Dice;

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
    	int count=0;
    	for (Field f : fields) {
            if (f instanceof Labor) {
                Labor labor = (Labor) f;
                if (labor.fieldowned && labor.fieldowner.equals(fieldowner)) {
                    count++;
                }
            }
        }
        if (count == 2){
        	int fieldrent = 200 * Dice.getSum();	
        	return fieldrent;
        }
        else{
        	int fieldrent = 100* Dice.getSum();
        	return fieldrent;
        }
    }

    @Override
    public String getName() {
        return fieldname;
    }

    public int getPrice() {
        return super.fieldprice;
    }

}
