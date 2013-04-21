/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource.Logic;

/**
 * @author Brian Chu and Garrick Ablett
 *
 */
public class LootLogicDriver {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // LootCalc tests

        // LootDice tests
        LootDice a = new LootDice(3, 20);
        for (int ii = 0; ii < 20; ii++) {
            System.out.println(a.roll());
        }

        // LootIO Tests

    }

}
