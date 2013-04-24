/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource.Logic;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 */
public class LootItemMagic extends LootItem {

    private Integer specialMat;
    private String name;

    public LootItemMagic() {
        super();
    }

    public LootItemMagic(Integer quantity, Double gValue, Integer mLevel,
            Integer rPower, Integer numRolled, Integer itemType) {
        super(quantity, gValue, mLevel, rPower, numRolled, itemType);
    }

    public LootItemMagic(Integer specialMat, String name) {
        super();
        this.specialMat = specialMat;
        this.name = name;
    }

    public LootItemMagic(Integer quantity, Double gValue, Integer mLevel,
            Integer rPower, Integer numRolled, Integer specialMat, String name,
            Integer itemType) {
        super(quantity, gValue, mLevel, rPower, numRolled, itemType);
        this.specialMat = specialMat;
        this.name = name;
    }

    // Getters setters
    public Integer getSpecialMat() {
        return specialMat;
    }

    public void setSpecialMat(Integer specialMat) {
        this.specialMat = specialMat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
