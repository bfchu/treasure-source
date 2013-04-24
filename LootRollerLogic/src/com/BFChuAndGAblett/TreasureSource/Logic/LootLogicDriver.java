/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource.Logic;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 *         LootLogicDriver is a testing program to test the methods of the
 *         LootRollerLogic project.
 * 
 */
public class LootLogicDriver {

    /**
     * @param args
     */
    public static void main(String[] args) {

        //
        LootBuilder gen = new LootBuilder();
        gen.genMain(args);

        // LootCalc tests

        // LootDice tests
        LootDice a = new LootDice(3, 20);
        for (int ii = 0; ii < 20; ii++) {
            System.out.println(a.roll());
        }

        // LootIO Tests

    }

}
