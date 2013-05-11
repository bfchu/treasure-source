/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 */
public class LootItemGold extends LootItem {

    private int coinType;

    /**
     * 1 = cp
     * 2 = sp
     * 3 = gp
     * 4 = pp
     *  */

    // changing coinType automatically changes the gVal to an appropriate level.
    // Use this.setQuantity() to set the amount of coins.

    public LootItemGold() {
        super();
        this.coinType = 3;
    }

    public LootItemGold(Integer quantity, Integer numRolled) {
        super("gold", quantity, 1.00, 0, 0, numRolled, 1);
        this.coinType = 3;
    }

    public LootItemGold(Integer quantity, Integer numRolled, int coinType) {
        super("gold", quantity, 1.00, 0, 0, numRolled, 1);
        this.coinType = coinType;
    }

    @Override
    public String toString() {
        return "LootItemGold " + "getgValue()=" + getgValue();
    }

    /**
     * @return the coinType
     */
    public int getCoinType() {
        return coinType;
    }

    /**
     * @param coinType the coinType to set
     */
    public void setCoinType(int coinType) {
        this.coinType = coinType;
        switch (coinType) {
        case 1:
            this.setgValue(0.01);
            break;
        case 2:
            this.setgValue(0.10);
            break;
        case 3:
            this.setgValue(1.00);
            break;
        case 4:
            this.setgValue(10.00);
            break;
        }
    }

}
