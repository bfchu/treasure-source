/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource.Logic;

/**
 * @author Brian Chu and Garrick Ablett
 *
 *  LootCalc class handles most of math.  
 *  It gathers the loot tables and dice and outputs loot Items.
 *  
 *
 */
public class LootCalc {

    private LootDice dice;
    private LootIO table;

    public LootCalc() {
        super();
    }

    /**
     * @param dice
     * @param table
     */
    public LootCalc(LootDice dice, LootIO table) {
        super();
        this.dice = dice;
        this.table = table;
    }

    /**
     * @return the dice
     */
    public LootDice getDice() {
        return dice;
    }

    /**
     * @param dice the dice to set
     */
    public void setDice(LootDice dice) {
        this.dice = dice;
    }

    /**
     * @return the table
     */
    public LootIO getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(LootIO table) {
        this.table = table;
    }

}
