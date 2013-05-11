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

    public void genMain(String[] args, LootPrefs prefs) {
        LootCalc dM = new LootCalc();
        dM.setPrefs(prefs);

        rollCoins(dM);
        // rollGoods(dM);
        // rollItems(dM);
    }

    public void rollCoins(LootCalc dM) {
        LootOutListItem coins = new LootOutListItem(dM.rollCoins());
        addItem(dM, coins);

    }

    public void rollGoods(LootCalc dM) {
        LootItemGoods goods = new LootItemGoods();

        // Roll on goods chart to determine if and what kind of goods
        Integer numDiceGoods = 1;
        Integer dieSizeGoods = 6;
        int goodsType = 1;
        dM.rollGoodsType(numDiceGoods, dieSizeGoods, goodsType);

        goods.setGoodsType(goodsType);
        goods.setQuantity(dM.rollNumGoods(numDiceGoods, dieSizeGoods));

        // Roll value range
        Integer valRange;
        valRange = dM.rollPercent();

        // Roll value per goods
        goods.setgValue(dM.rollGoodsVal());

        LootOutListItem outGoods = new LootOutListItem(goods);
        addItem(dM, outGoods);

    }

    public void rollItems(LootCalc dM) {

        // initializing variables, values unimportant
        Integer itemGroup = 1; // 1 = mundane, 2 = minor, 3 = medium, 4 = major
        Integer numDiceItems = 1;
        Integer dieSizeItems = 6;

        // Database Call returns numDice, dieSize, and itemGroup
        dM.rollItemGrouping(dM.getPrefs().getaPL(), numDiceItems, dieSizeItems,
                itemGroup);

        Integer numItems = dM.getDice().roll(numDiceItems, dieSizeItems);
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
