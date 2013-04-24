/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource.Logic;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 */
public class LootItemGold extends LootItem {

    public LootItemGold() {
        super();
    }

    public LootItemGold(Double gValue, Integer numRolled) {
        super(1, gValue, 0, 0, numRolled, 1);
    }

    @Override
    public String toString() {
        return "LootItemGold " + "getgValue()=" + getgValue();
    }

}
