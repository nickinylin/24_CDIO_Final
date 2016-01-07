/**
 * Fleet
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package fields;

public class Fleet extends Ownable {
    
    public Fleet(String name) {
        super(name);
        super.fieldprice = 4000;
        super.fieldowned = false;
    }
    /**
     * Justere prisen for at lande paa et Fleet felt efter hvor mange man har, b�r opdateres ved k�b og salg af Fleet felt.
     * @param nr
     * @return
     */
    @Override
    public int getRent() {
        int fieldrent = 0;
        int count = 0;
        
        for (Field f : fields) {
            if (f instanceof Fleet) {
                Fleet fleet = (Fleet) f;
                if (fleet.fieldowned && fleet.fieldowner.equals(fieldowner)) {
                    count++;
                }
            }
        }
        
        switch (count) {
            case 1: fieldrent = 500; break;
            case 2: fieldrent = 1000; break;
            case 3: fieldrent = 2000; break;
            case 4: fieldrent = 4000; break;       
        }
        
        return fieldrent;
        
    }
    

    public int getPrice() {
        return fieldprice;
    }
    public boolean getFieldOwned()
    {
    	return fieldowned;
    }
    
    
}