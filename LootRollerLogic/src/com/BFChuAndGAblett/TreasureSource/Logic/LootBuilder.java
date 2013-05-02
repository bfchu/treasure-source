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

    private LootList hoard;
    private LootListNoDuplicates trove;

    public LootBuilder() {
        super();
        this.hoard = new LootList();
        this.trove = new LootListNoDuplicates();
    }

    /**
     * @param hoard
     * @param trove
     */
    public LootBuilder(LootList hoard, LootListNoDuplicates trove) {
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
        addItem(dM, dM.rollCoins());

    }

    public void rollGoods(LootCalc dM) {
        LootItemGoods goods = new LootItemGoods();

        // Roll on goods chart to determine if and what kind of goods
        Integer numDiceGoods = 1;
        Integer dieSizeGoods = 6;
        String goodsType = "gems";
        dM.rollGoodsType(numDiceGoods, dieSizeGoods, goodsType);

        goods.setGoodsType(goodsType);
        goods.setQuantity(dM.rollNumGoods(numDiceGoods, dieSizeGoods));

        // Roll value range
        Integer valRange;
        valRange = dM.rollPercent();

        // Roll value per goods
        goods.setgValue(dM.rollGoodsVal());

        addItem(dM, goods);

    }

    public void rollItems(LootCalc dM) {

        String itemGroup = "mundane";
        Integer numDiceItems = 1;
        Integer dieSizeItems = 6;
        dM.rollItemGrouping(numDiceItems, dieSizeItems, itemGroup);

        Integer numItems = dM.getDice().roll(numDiceItems, dieSizeItems);
        for (int ii = 0; ii < numItems; ii++) {
            LootItem item = dM.rollItem(itemGroup);
            addItem(dM, item);
        }

    }

    // Getters/Setters
    public void addItem(LootCalc dM, LootItem item) {
        if (dM.getPrefs().isNoRepeats() && dM.isValid(item)) {
            addToTrove(item);
        } else if (dM.isValid(item)) {
            addToHoard(item);
        } else {
            // increment some throw-out counter
        }
    }

    public void addToHoard(LootItem item) {
        this.hoard.getLoot().put(this.hoard.getLoot().size(), item);
    }

    public void addToTrove(LootItem item) {
        this.trove.getLoot().put(this.trove.getLoot().size(), item);
    }

    public LootList getHoard() {
        return hoard;
    }

    public void setHoard(LootList hoard) {
        this.hoard = hoard;
    }

    public LootListNoDuplicates getTrove() {
        return trove;
    }

    public void setTrove(LootListNoDuplicates trove) {
        this.trove = trove;
    }
}
