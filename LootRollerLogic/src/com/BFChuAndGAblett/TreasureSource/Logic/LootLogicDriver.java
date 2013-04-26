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
        LootBuilder gen1 = new LootBuilder();
        LootPrefs prefs1 = new LootPrefs(3, 63, 4, 4, 2, .54, true, true,
                false, false, new boolean[9], new boolean[3]);
        gen1.genMain(args, prefs1);

        // LootCalc tests

        // LootDice tests
        LootDice a = new LootDice(3, 20);
        for (int ii = 0; ii < 20; ii++) {
            System.out.println(a.roll());
        }

        // LootIO Tests

    }
}
