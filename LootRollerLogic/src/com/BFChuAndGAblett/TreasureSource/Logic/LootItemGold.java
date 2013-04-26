/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource.Logic;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 */
public class LootItemGold extends LootItem {

    private String coinType;

    // changing coinType automatically changes the gVal to an appropriate level.
    // Use this.setQuantity() to set the amount of coins.

    public LootItemGold() {
        super();
        this.coinType = "gp";
    }

    public LootItemGold(Double gValue, Integer numRolled) {
        super(1, gValue, 0, 0, numRolled, 1);
        this.coinType = "gp";
    }

    @Override
    public String toString() {
        return "LootItemGold " + "getgValue()=" + getgValue();
    }

    /**
     * @return the coinType
     */
    public String getCoinType() {
        return coinType;
    }

    /**
     * @param coinType the coinType to set
     */
    public void setCoinType(String coinType) {
        this.coinType = coinType;
        switch (coinType) {
        case "cp":
            this.setgValue(0.01);
            break;
        case "sp":
            this.setgValue(0.1);
            break;
        case "gp":
            this.setgValue(1.0);
            break;
        case "pp":
            this.setgValue(10.0);
            break;
        }
    }

}
