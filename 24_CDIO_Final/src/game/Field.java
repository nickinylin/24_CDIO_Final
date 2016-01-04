/**
 * Field
 * @author Gruppe 24
 * @version 04/01-2016
 **/

package game;

public abstract class Field {
    
    protected String fieldname;
    protected static Field[] field;
    
    /**
     * creates the fields for both the GUI and gamelogic to interact with.
     * @return Field[]
     */
    public static Field[] createFields() {
        
        field = new Field[] { 
            new Territory ("Tribe Encampment", 100, 1000),
            new Territory ("Crater", 300, 1500),
            new Territory ("Mountain", 500, 2000),
            new Territory ("Cold Desert", 700, 3000),
            new Territory ("Black Cave", 1000, 4000),
            new Territory ("The WereWall", 1300, 4300),
            new Territory ("Mountain Village", 1600, 4750),
            new Territory ("South Citadel", 2000, 5000),
            new Territory ("Palace Gates", 2600, 5500),
            new Territory ("Tower", 3200, 6000),
            new Territory ("Castle", 4000, 8000),
            new Refuge ("Walled City", 5000),
            new Refuge ("Monastery", 500),
            new Labor ("Huts"),
            new Labor ("The Pit"),
            new Tax ("Goldmine", 2000, "false"),
            new Tax ("Caravan", 4000, "special"),
            new Fleet ("Second Sail"),
            new Fleet ("Sea Grover"),
            new Fleet ("Buccaneers"),
            new Fleet ("Armade")
        };
        
        for (int i = 0; i < field.length; i++) {
            Field fields = field[i];
            if (fields instanceof Territory) {
                list[i] = new Street.Builder()
                    .setTitle(field[i].fieldname)
                    .setSubText("Costs: "+field[i].getPrice()+"")
                    .setDescription("Rent: "+field[i].getRent()+"")
                    .setBgColor(Color.DARK_GRAY)
                    .setFgColor(Color.WHITE)
                    .build();
                
            } else if (fields instanceof Refuge) {
                list[i] = new desktop_fields.Refuge.Builder()
                    .setTitle(field[i].fieldname)
                    .setDescription("You collect "+field[i].getRent()+"")
                    .setSubText("Bonus")
                    .setPicture("GUI/desktop_resources/pics/money.png")
                    .setBgColor(Color.GREEN)
                    .setFgColor(Color.BLACK)
                    .build();
                
            } else if (fields instanceof Labor) {
                list[i] = new Brewery.Builder()
                    .setTitle(field[i].fieldname)
                    .setDescription("Rent: 100xDices")
                    .setSubText("")
                    .setBgColor(Color.YELLOW)
                    .setFgColor(Color.BLACK)
                    .setRent("100xDices")
                    .setPicture(field[i].getName())
                    .build(); 
                
            } else if (fields instanceof Tax) {
                list[i] = new desktop_fields.Tax.Builder()
                    .setTitle(field[i].fieldname)
                    .setDescription("TAX: "+field[i].getRent()+"")
                    .setSubText("Pay "+field[i].getRent()+" in TAXES")
                    .setBgColor(Color.RED)
                    .build(); 
                
            } else if (fields instanceof Fleet) {
                list[i] = new Shipping.Builder()
                    .setTitle(field[i].fieldname)
                    .setDescription("Rent: 500-4000")
                    .setSubText("")
                    .setBgColor(Color.BLUE)
                    .setFgColor(Color.WHITE)
                    .setRent("500 per owned Fleet")
                    .build();
            }
        }
        GUI.create(list);
        return field;
    }
    
    /**
     * This method is used when a player lands on a field. landOnField defines what happens when a player lands on a field.
     * @param
     */
    public abstract void landOnField(Player player);  
    
    /*
     * This method will sell all player assets
     */
    public void sellAllFields(Player player) {
        
        for (int i = 0; i < field.length; i++) {
            
            if (field[i] instanceof Territory) {
                Territory territory = (Territory) field[i];
                if (territory.fieldowned && territory.fieldowner.equals(player)) {
                    player.giveMoney(territory.fieldprice);
                    territory.fieldowned = false;
                    territory.fieldowner = null;
                    player.setAssets(-territory.fieldprice);
                    GUI.removeOwner(i+1);
                    GUI.setBalance(player.getName(), player.getMoney());
                }
            }
            
            if (field[i] instanceof Labor) {
                Labor labor = (Labor) field[i];
                if (labor.fieldowned && labor.fieldowner.equals(player)) {
                    player.giveMoney(labor.fieldprice);
                    labor.fieldowned = false;
                    labor.fieldowner = null;
                    player.setAssets(-labor.fieldprice);
                    GUI.removeOwner(i+1);
                    GUI.setBalance(player.getName(), player.getMoney());
                }
            }
            
            if (field[i] instanceof Fleet) {
                Fleet fleet = (Fleet) field[i];
                if (fleet.fieldowned && fleet.fieldowner.equals(player)) {
                    player.giveMoney(fleet.fieldprice);
                    fleet.fieldowned = false;
                    fleet.fieldowner = null;
                    player.setAssets(-fleet.fieldprice);
                    GUI.removeOwner(i+1);
                    GUI.setBalance(player.getName(), player.getMoney());
                }
            }
            
            
        }
        
    } 
    /**
     * Method returns the name of the field.
     * @return fieldName
     */
    public String getName() {
        return fieldname;
    }
    
    public Field(String name) {
        this.fieldname = name;
    }
    
    public static int getNumberOfFields() {
        return field.length;
    }
    /**
     * Method gets the rent of the field.
     * @return rentoffield
     */
    public abstract int getRent();
    
    /**
     * Method gets the price of the field.
     * @return Priceoffield
     */
    public abstract int getPrice();
    
    
}