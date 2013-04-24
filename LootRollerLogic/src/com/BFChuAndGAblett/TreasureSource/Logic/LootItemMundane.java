/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource.Logic;


/**
 * @author Brian Chu and Garrick Ablett
 * 
 */
public class LootItemMundane extends LootItem {

    private Integer quality;
    private Integer specialMat;
    private String name;

    public LootItemMundane(Integer quality, Integer specialMat, String name) {
        super();
        this.quality = quality;
        this.specialMat = specialMat;
        this.name = name;
    }

    public LootItemMundane() {
        super();
        // TODO Auto-generated constructor stub
    }

    public LootItemMundane(Integer quantity, Double gValue, Integer mLevel,
            Integer rPower, Integer numRolled, Integer itemType) {
        super(quantity, gValue, mLevel, rPower, numRolled, itemType);
        // TODO Auto-generated constructor stub
    }

    public LootItemMundane(Integer quality, Integer specialMat, String name,
            Integer quantity, Double gValue, Integer mLevel, Integer rPower,
            Integer numRolled, Integer itemType) {
        super(quantity, gValue, mLevel, rPower, numRolled, itemType);
        this.quality = quality;
        this.specialMat = specialMat;
        this.name = name;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

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
