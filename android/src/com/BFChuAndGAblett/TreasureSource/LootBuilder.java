/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

import java.util.ArrayList;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 *         LootBuilder is a middle-driver. It handles bringing together most of
 *         the necessary functions for creating a hoard of loot and hooking it
 *         up to either a Java driver class or an Android GUI.
 * 
 */
public class LootBuilder {

    private ArrayList<LootOutListItem> hoard;
    private ArrayList<LootOutListItem> trove; // use for no duplicates
    private ArrayList<LootOutListItem> trash;
    private LootCalc dM;

    public LootBuilder() {
        super();
        this.hoard = new ArrayList<LootOutListItem>();
        this.trove = new ArrayList<LootOutListItem>();
    }

    /**
     * @param hoard
     * @param trove
     */
    public LootBuilder(ArrayList<LootOutListItem> hoard,
            ArrayList<LootOutListItem> trove) {
        super();
        this.hoard = hoard;
        this.trove = trove;
    }

    public ArrayList<LootOutListItem> genLoot(String[] args, LootPrefs prefs,
            LootDB books) {
        this.dM = new LootCalc(books, prefs);

        rollCoins(dM);
        rollGoods(dM);
        rollItems(dM);

        if (dM.getPrefs().isNoRepeats()) {
            return this.getTrove();
        } else {
            return this.getHoard();
        }
    }

    public void rollCoins(LootCalc dM) {
        LootOutListItem coins = new LootOutListItem(dM.rollCoins());
        addItem(dM, coins);
    }

    public void rollGoods(LootCalc dM) {
        LootOutListItem outGoods = new LootOutListItem(dM.rollGoods());
        addItem(dM, outGoods);
    }

    public void rollItems(LootCalc dM) {
        // initializing variables, values unimportant
        // itemGroup: 1 = mundane, 2 = minor, 3 = medium, 4 = major
        Integer itemGroup = 1;
        Integer numDiceItems = 1;
        Integer dieSizeItems = 6;
        // Database Call returns numDice, dieSize, and itemGroup
        dM.rollItemGrouping(numDiceItems, dieSizeItems, itemGroup);

        // roll number to items to generate
        Integer numItems = dM.rollNumItems(numDiceItems, dieSizeItems);

        // Roll each Item, then add to the ArrayList.
        for (int ii = 0; ii < numItems; ii++) {
            LootOutListItem item = new LootOutListItem(dM.rollItem(itemGroup));
            addItem(dM, item);
        }
    }

    // Getters/Setters
    public void addItem(LootCalc dM, LootOutListItem item) {
        if (dM.getPrefs().isNoRepeats() && dM.isValid(item)) {
            addToTrove(item);
        } else if (dM.isValid(item)) {
            addToHoard(item);
        } else {
            // increment some throw-out counter
        }
    }

    public void addToHoard(LootOutListItem item) {
        this.hoard.add(item);
    }

    public void addToTrove(LootOutListItem item) {
        this.trove.add(item);
    }

    public ArrayList<LootOutListItem> getHoard() {
        return hoard;
    }

    public void setHoard(ArrayList<LootOutListItem> hoard) {
        this.hoard = hoard;
    }

    public ArrayList<LootOutListItem> getTrove() {
        return trove;
    }

    public void setTrove(ArrayList<LootOutListItem> trove) {
        this.trove = trove;
    }
}
