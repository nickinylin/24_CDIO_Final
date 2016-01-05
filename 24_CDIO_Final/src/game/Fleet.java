/**
 * Fleet
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package game;

public class Fleet extends Ownable {
    
    protected String fieldname;
    
    private int RENT_1 = 500;
    private int RENT_2 = 1000;
    private int RENT_3 = 2000;
    private int RENT_4 = 4000;
    
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
            case 1: fieldrent = RENT_1; break;
            case 2: fieldrent = RENT_2; break;
            case 3: fieldrent = RENT_3; break;
            case 4: fieldrent = RENT_4; break;       
        }
        
        return fieldrent;
        
    }
    
    @Override
    public String getName() {
        return fieldname;
    }

    public int getPrice() {
        return fieldprice;
    }
    
    
    
}