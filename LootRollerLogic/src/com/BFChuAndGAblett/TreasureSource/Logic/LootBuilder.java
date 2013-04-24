/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource.Logic;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 *         LootBuilder is a middle-driver. It handles bringing together most of
 *         the necessary functions for creating a hoard of loot and hooking it
 *         up to either a Java driver class or an Android GUI.
 * 
 */
public class LootBuilder {

    public void genMain(String[] args) {
        LootCalc dM = new LootCalc();
        LootPrefs prefs = new LootPrefs(3, 63, 4, 4, 2, .54, true, true, false,
                false, new boolean[9], new boolean[3]);
        dM.setPrefs(prefs);

    }

    public void rollCoins(LootCalc dM) {
        dM.rollCoins();
    }

    public void rollGoods(LootCalc dM) {

    }

    public void rollItems(LootCalc dM) {

    }
}
