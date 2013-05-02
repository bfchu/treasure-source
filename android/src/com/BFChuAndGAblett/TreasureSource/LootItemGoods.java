/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 */
public class LootItemGoods extends LootItem {

    private String goodsType; // Expected values are "art", "gems", "jewelry"s

    public LootItemGoods() {
        super();
        this.goodsType = "gems";
    }

    public LootItemGoods(Integer quantity, Double gValue, Integer numRolled,
            String goodsType) {
        super("", quantity, gValue, 0, 0, numRolled, 2);
        this.goodsType = goodsType;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    @Override
    public String toString() {
        return "LootItemGoods [goodsType=" + goodsType + ", getQuantity()="
                + getQuantity() + ", getgValue()=" + getgValue()
                + ", getNumRolled()=" + getNumRolled() + "]";
    }

}
