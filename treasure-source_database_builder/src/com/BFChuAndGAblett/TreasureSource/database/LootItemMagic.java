/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource.database;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 */
@Deprecated
public class LootItemMagic extends LootItem {

    private Integer specialMat;

    public LootItemMagic() {
        super();
    }

    public LootItemMagic(String name, Integer quantity, Double gValue,
            Integer mLevel, Integer rPower, Integer numRolled, Integer itemType) {
        super(name, quantity, gValue, mLevel, rPower, numRolled, itemType);
    }

    public LootItemMagic(Integer specialMat, String name) {
        super();
        this.specialMat = specialMat;
        this.setName(name);
    }

    public LootItemMagic(String name, Integer quantity, Double gValue,
            Integer mLevel, Integer rPower, Integer numRolled,
            Integer specialMat, Integer itemType) {
        super(name, quantity, gValue, mLevel, rPower, numRolled, itemType);
        this.specialMat = specialMat;
    }

    // Getters setters
    @Override
    public Integer getSpecialMat() {
        return specialMat;
    }

    @Override
    public void setSpecialMat(Integer specialMat) {
        this.specialMat = specialMat;
    }

}
