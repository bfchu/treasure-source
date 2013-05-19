/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 */
@Deprecated
public class LootItemMundane extends LootItem {

    private Integer quality;
    private Integer specialMat;

    public LootItemMundane(Integer quality, Integer specialMat, String name) {
        super();
        this.quality = quality;
        this.specialMat = specialMat;
        this.setName(name);
    }

    public LootItemMundane() {
        super();
        // TODO Auto-generated constructor stub
    }

    public LootItemMundane(String name, Integer quantity, Double gValue,
            Integer mLevel, Integer rPower, Integer numRolled, Integer itemType) {
        super(name, quantity, gValue, mLevel, rPower, numRolled, itemType);
        // TODO Auto-generated constructor stub
    }

    public LootItemMundane(String name, Integer quality, Integer specialMat,
            Integer quantity, Double gValue, Integer mLevel, Integer rPower,
            Integer numRolled, Integer itemType) {
        super(name, quantity, gValue, mLevel, rPower, numRolled, itemType);
        this.quality = quality;
        this.specialMat = specialMat;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    @Override
    public Integer getSpecialMat() {
        return specialMat;
    }

    @Override
    public void setSpecialMat(Integer specialMat) {
        this.specialMat = specialMat;
    }

}
