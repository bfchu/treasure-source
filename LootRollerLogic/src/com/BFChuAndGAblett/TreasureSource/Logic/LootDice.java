/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource.Logic;

import java.util.Random;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 * Handles the acquisition of random numbers based on dice size and number.  
 * Is not responsible for remembering the numbers it rolls
 *
 */
public class LootDice {
    public static final Integer LOOT_TABLE_RANGE = 100;

    private Integer numDice;
    private Integer dieSize;
    private Random unRander;

    // Constructors
    public LootDice() {
        super();
        this.numDice = 1;
        this.dieSize = 10;
        this.unRander = new Random();
    }

    /**
     * @param numDice
     * @param dieSize
     */
    public LootDice(Integer numDice, Integer dieSize) {
        super();
        this.numDice = numDice;
        this.dieSize = dieSize;
        this.unRander = new Random();
    }

    // Dice-related Methods
    public Integer roll() {
        Integer numRolled = 0;
        for (int ii = 0; ii < numDice; ii++) {
            numRolled += ((Math.abs(unRand()) % dieSize) + 1);
        }
        return numRolled;
    }

    public Integer unRand() {
        return unRander.nextInt();
    }

    // Getters Setters field

    /**
     * Master setter; input integers in the format: XdY
     * example: 2d6 is LootDice.set(2,6);
     * 
     * @param numDice
     * @param dieSize
     */
    public void set(Integer numDice, Integer dieSize) {
        this.numDice = numDice;
        this.dieSize = dieSize;
    }

    /**
     * @return the numDice
     */
    public Integer getNumDice() {
        return numDice;
    }

    /**
     * @param numDice the numDice to set
     */
    public void setNumDice(Integer numDice) {
        this.numDice = numDice;
    }

    /**
     * @return the dieSize
     */
    public Integer getDieSize() {
        return dieSize;
    }

    /**
     * @param dieSize the dieSize to set
     */
    public void setDieSize(Integer dieSize) {
        this.dieSize = dieSize;
    }

}
