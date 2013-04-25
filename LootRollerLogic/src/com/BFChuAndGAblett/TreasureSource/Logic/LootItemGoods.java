/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource.Logic;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 */
public class LootItemGoods extends LootItem {

    private Integer goodsType; // 0 = nothing, 1 = Art, 2 = Jewelry, 3 =...

    public LootItemGoods() {
        super();
        this.goodsType = 2;
    }

    public LootItemGoods(Integer quantity, Double gValue, Integer numRolled,
            Integer goodsType) {
        super(quantity, gValue, 0, 0, numRolled, 2);
        this.goodsType = goodsType;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    @Override
    public String toString() {
        return "LootItemGoods [goodsType=" + goodsType + ", getQuantity()="
                + getQuantity() + ", getgValue()=" + getgValue()
                + ", getNumRolled()=" + getNumRolled() + "]";
    }

}
