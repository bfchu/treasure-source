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
    private LootCalc dCalc;

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
        this.dCalc = new LootCalc(books, prefs);

        rollCoins();
        rollGoods();

        if (dCalc.getTreasureMultiplier() < 1) {
            rollItems(); // for fractions
        } else {
            for (int ii = 0; ii < dCalc.getTreasureMultiplier(); ii++) {
                rollItems(); // for whole loots
            }
        }

        if (this.dCalc.getPrefs().isNoRepeats()) {
            addToDB(this.getTrove());
            // return this.getTrove();
        } else {
            addToDB(this.getHoard());
            // return this.getHoard();
        }
    }

    public void rollCoins() {
        LootOutListItem coins = new LootOutListItem(this.dCalc.rollCoins());
        addItem(coins);
    }

    public void rollGoods() {
        LootOutListItem outGoods = new LootOutListItem(this.dCalc.rollGoods());
        addItem(outGoods);
    }

    public void rollItems() {
        // initializing variables, values unimportant
        // itemGroup: 1 = mundane, 2 = minor, 3 = medium, 4 = major
        Integer dRoll = dCalc.rollPercent();
        Integer itemGroup = dCalc.getItemGrouping(dRoll);
        Integer numItems = 0;
        if (dCalc.getPrefs().isNoRepeats()) {
            numItems = (int) (dCalc.getNumItems(dRoll) * dCalc
                    .getTreasureMultiplier()) + trove.size();
        } else {
            numItems = (int) (dCalc.getNumItems(dRoll) * dCalc
                    .getTreasureMultiplier()) + hoard.size();
        }

        // If you aren't rolling mundane items, tell the user how many
        if ((itemGroup == 1) && !dCalc.getPrefs().isRollMundane()) {
            LootOutListItem mundanes = new LootOutListItem();
            mundanes.setQuantity(dCalc.getNumItems(dRoll));
            mundanes.setName("Mundane Items");
            addItem(mundanes);
        } else {

            // Roll each Item, then add to the ArrayList.
            while ((hoard.size() < numItems) && (trove.size() < numItems)) {
                LootOutListItem item = new LootOutListItem(
                        this.dCalc.rollItem(itemGroup));

                addItem(item);

            }
        }
    }

    public void addToDB(ArrayList<LootOutListItem> lootList) {
        Iterator<LootOutListItem> itr = lootList.iterator();
        while (itr.hasNext()) {
            LootOutListItem item = itr.next();
            this.getdCalc().getBooks().saveEntry("lootOut", null, item);
        }

    }

    // Getters/Setters
    public void addItem(LootOutListItem item) {
        if (this.dCalc.getPrefs().isNoRepeats() && this.dCalc.isValid(item)) {
            if (item.getName() != "") {
                addToTrove(item);
            }
        } else if (this.dCalc.isValid(item)) {
            if (item.getName() != "") {
                addToHoard(item);
            }
        } else {
            // TODO: add to trash
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

    public LootCalc getdCalc() {
        return dCalc;
    }

    public void setdCalc(LootCalc dCalc) {
        this.dCalc = dCalc;
    }
}
