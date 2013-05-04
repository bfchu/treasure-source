/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 */
public class LootItemGoods extends LootItem {
    // 1 = gems, 2 = art
    private int goodsType;

    public LootItemGoods() {
        super("goods", 1, 10.00, 0, 0, 0, 2);
        this.goodsType = 1;
    }

    public LootItemGoods(Integer quantity, Double gValue, Integer numRolled,
            int goodsType) {
        super("goods", quantity, gValue, 0, 0, numRolled, 2);
        this.goodsType = goodsType;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    @Override
    public String toString() {
        return "LootItemGoods [goodsType=" + goodsType + ", getQuantity()="
                + getQuantity() + ", getgValue()=" + getgValue()
                + ", getNumRolled()=" + getNumRolled() + "]";
    }

}
