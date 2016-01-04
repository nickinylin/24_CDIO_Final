/**
 * Ownable
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package game;

import desktop_resources.GUI;

public abstract class Ownable extends Field {
    
    public Ownable(String name) {
        super(name);
    }
    
    protected Player fieldowner;
    protected int fieldprice;
    protected boolean fieldowned;
    protected int houses;
    protected int houseprice;
    // evt. array til leje priserne.

    
}
