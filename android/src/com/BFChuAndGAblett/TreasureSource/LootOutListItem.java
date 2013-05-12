/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

/**
 * @author Brian
 * 
 */
public class LootOutListItem extends LootItem {
    private int id;
    private String specials;
    private boolean dispGold;
    private boolean dispRoll;

    /**
     * @param id
     * @param dLow
     * @param dHigh
     */
    public LootOutListItem(int id, int numRolled, int quantity) {
        super();
        this.id = id;
        this.setNumRolled(numRolled);
        super.setQuantity(quantity);
        this.dispGold = true;
        this.dispRoll = false;
    }

    public LootOutListItem(LootItem item) {
        super();
        this.setNumRolled(item.getNumRolled());
        this.specials = "";
        super.setQuantity(item.getQuantity());
        this.setName(item.getName());
        this.setgValue(item.getgValue());
        this.dispGold = true;
        this.dispRoll = false;
    }

    public LootOutListItem(LootItemGoods item) {
        super();
        this.setNumRolled(item.getNumRolled());
        this.specials = "";
        super.setQuantity(item.getQuantity());
        this.setName(item.getName());
        this.setgValue(item.getgValue());
        this.dispGold = true;
        this.dispRoll = false;
    }

    public LootOutListItem(LootItemMundane item) {
        super();

        this.setNumRolled(item.getNumRolled());
        this.specials = findSpecialMatByID(item.getSpecialMat());
        super.setQuantity(item.getQuantity());
        this.setName(item.getName());
        this.setgValue(item.getgValue());
        this.setmLevel(0);
        this.setrPower(1);
        this.dispGold = true;
        this.dispRoll = false;
    }

    public LootOutListItem(LootItemMagic item) {
        super();
        this.setNumRolled(item.getNumRolled());
        this.specials = findSpecialMatByID(item.getSpecialMat());
        super.setQuantity(item.getQuantity());
        this.setName(item.getName());
        this.setgValue(item.getgValue());
        this.setmLevel(item.getmLevel());
        this.setrPower(item.getrPower());
        this.dispGold = true;
        this.dispRoll = false;
    }

    public LootOutListItem(int id, int numRolled, int quantity,
            String specials, String name, double gValue) {
        super();
        this.id = id;
        this.setNumRolled(numRolled);
        this.specials = specials;
        super.setQuantity(quantity);
        this.setName(name);
        this.setgValue(gValue);
        this.dispGold = true;
        this.dispRoll = false;
    }

    public LootOutListItem(int id, int numRolled, int quantity, String name,
            double gValue, boolean dispGold, boolean dispRoll) {
        super();
        this.id = id;
        this.setNumRolled(numRolled);
        this.specials = "";
        super.setQuantity(quantity);
        this.setName(name);
        this.setgValue(gValue);
        this.dispGold = dispGold;
        this.dispRoll = dispRoll;
    }

    public LootOutListItem(int id, int numRolled, int quantity,
            String specials, String name, double gValue, boolean dispGold,
            boolean dispRoll) {
        super();
        this.id = id;
        this.setNumRolled(numRolled);
        this.specials = specials;
        super.setQuantity(quantity);
        this.setName(name);
        this.setgValue(gValue);
        this.dispGold = dispGold;
        this.dispRoll = dispRoll;
    }

    public String findSpecialMatByID(Integer id) {
        switch (id) {
        case 1:
            return "Iron";
        case 2:
            return "Steel";
        case 3:
            return "Adamantine";
        case 4:
            return "Dragon hide";
        case 5:
            break;
        }
        return null;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        String string = "";

        if (this.getQuantity() > 1) {
            string += (this.getQuantity() + "x ");
        }
        if (this.getSpecials() != null) {
            string += this.getSpecials() + " ";
        }
        string += this.getName();

        if (dispGold) {
            string += ", worth: " + this.getgValue() + "gp";
        }
        if (dispRoll) {
            string += " (rolled " + this.getNumRolled() + "%)";
        }

        return string;
    }

    /**
     * @return the specials
     */
    public String getSpecials() {
        return specials;
    }

    /**
     * @param specials
     *            the specials to set
     */
    public void setSpecials(String specials) {
        this.specials = specials;
    }

    public boolean isDispGold() {
        return dispGold;
    }

    public void setDispGold(boolean dispGold) {
        this.dispGold = dispGold;
    }

    public boolean isDispRoll() {
        return dispRoll;
    }

    public void setDispRoll(boolean dispRoll) {
        this.dispRoll = dispRoll;
    }
}
