/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

import java.util.ArrayList;
import java.util.Iterator;

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

    public void genLoot(String[] args, LootPrefs prefs, LootDB books) {
        this.dM = new LootCalc(books, prefs);

        rollCoins();
        rollGoods();
        rollItems();

        if (this.dM.getPrefs().isNoRepeats()) {
            addToDB(this.getTrove());
            // return this.getTrove();
        } else {
            addToDB(this.getHoard());
            // return this.getHoard();
        }
    }

    public void rollCoins() {
        LootOutListItem coins = new LootOutListItem(this.dM.rollCoins());
        addItem(coins);
    }

    public void rollGoods() {
        LootOutListItem outGoods = new LootOutListItem(this.dM.rollGoods());
        addItem(outGoods);
    }

    public void rollItems() {
        // initializing variables, values unimportant
        // itemGroup: 1 = mundane, 2 = minor, 3 = medium, 4 = major
        Integer itemGroup = 1;
        Integer numDiceItems = 1;
        Integer dieSizeItems = 6;
        // Database Call returns numDice, dieSize, and itemGroup
        this.dM.rollItemGrouping(numDiceItems, dieSizeItems, itemGroup);

        // roll number to items to generate
        Integer numItems = this.dM.rollNumItems(numDiceItems, dieSizeItems);

        // Roll each Item, then add to the ArrayList.
        for (int ii = 0; ii < numItems; ii++) {
            LootOutListItem item = new LootOutListItem(
                    this.dM.rollItem(itemGroup));
            addItem(item);
        }
    }

    public void addToDB(ArrayList<LootOutListItem> lootList) {
        Iterator<LootOutListItem> itr = lootList.iterator();
        while (itr.hasNext()) {
            LootOutListItem item = itr.next();
            this.getdM().getBooks().saveEntry("lootOut", null, item);
        }

    }

    // Getters/Setters
    public void addItem(LootOutListItem item) {
        if (this.dM.getPrefs().isNoRepeats() && this.dM.isValid(item)) {
            addToTrove(item);
        } else if (this.dM.isValid(item)) {
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

    // eclipse Getters and setters
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

    public ArrayList<LootOutListItem> getTrash() {
        return trash;
    }

    public void setTrash(ArrayList<LootOutListItem> trash) {
        this.trash = trash;
    }

    public LootCalc getdM() {
        return dM;
    }

    public void setdM(LootCalc dM) {
        this.dM = dM;
    }
}
