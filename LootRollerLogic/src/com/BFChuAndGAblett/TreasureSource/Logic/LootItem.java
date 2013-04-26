/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource.Logic;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 *         A container class that holds the information about an Item that was
 *         rolled.
 * 
 *         TODO: determine all of the properties of an item, and add them to the
 *         LootItem class as private data members.
 * 
 */
public class LootItem {

    private Integer quantity; // for ammunition and some wondrous items. gValue
                              // is multiplied by quantity for final result.
    private Double gValue; // value in gold pieces per quantity. 9.99 = 9g, 9s,
                           // 9c.
    private Integer mLevel; // magic level, or enhancement level.
    private Integer rPower; // relative power level of item.
    private Integer numRolled; // % value that was rolled for the item on the
                               // most relevant chart.
    private Integer itemType;

    /**
     * 0 = null,
     * 1 = gold, 
     * 2 = goods, 
     * 3 = Armor/Shields, 
     * 4 = Weapons,
     * 5 = Potions,
     * 6 = Rings,
     * 7 = Rods
     * 8 = Scrolls
     * 9 = Staves
     * 10 = Wands
     * 11 = Wondrous
     * 
     */

    public LootItem() {
        super();
        this.quantity = 1;
        this.gValue = 0.0;
        this.mLevel = 0;
        this.rPower = 0;
        this.numRolled = 0;
        this.itemType = 0;
    }

    public LootItem(Integer quantity, Double gValue, Integer mLevel,
            Integer rPower, Integer numRolled, Integer itemType) {
        super();
        this.quantity = quantity;
        this.gValue = gValue;
        this.mLevel = mLevel;
        this.rPower = rPower;
        this.numRolled = numRolled;
        this.itemType = itemType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getgValue() {
        return gValue;
    }

    public void setgValue(Double gValue) {
        this.gValue = gValue;
    }

    public Integer getmLevel() {
        return mLevel;
    }

    public void setmLevel(Integer mLevel) {
        this.mLevel = mLevel;
    }

    public Integer getrPower() {
        return rPower;
    }

    public void setrPower(Integer rPower) {
        this.rPower = rPower;
    }

    public Integer getNumRolled() {
        return numRolled;
    }

    public void setNumRolled(Integer numRolled) {
        this.numRolled = numRolled;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

}
