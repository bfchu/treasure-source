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

    public void genMain(String[] args) {
        LootCalc dM = new LootCalc();
        LootPrefs prefs = new LootPrefs(3, 63, 4, 4, 2, .54, true, true, false,
                false, new boolean[9], new boolean[3]);
        dM.setPrefs(prefs);

        rollCoins(dM);
        // rollGoods(dM);
        // rollItems(dM);
    }

    public void rollCoins(LootCalc dM) {
        if (dM.getPrefs().isNoRepeats()) {
            addToTrove(dM.rollCoins());
        } else {
            addToHoard(dM.rollCoins());
        }

    }

    public void rollGoods(LootCalc dM) {
        LootItemGoods goods = new LootItemGoods();

        // Roll on goods chart to determine if and what kind of goods
        Integer rGoods;
        rGoods = dM.rollGoodsType();

        // Roll quantity (how many times to roll on next chart)
        goods.setQuantity(dM.rollNumGoods(numDice, dieSize));

        // Roll value range
        Integer valRange;
        valRange = dM.rollPercent();

        // Roll value per goods
        goods.setgValue(dM.rollGoodsVal());

        if (dM.getPrefs().isNoRepeats() && dM.isValid(goods)) {
            addToTrove(goods);
        } else if (dM.isValid(goods)) {
            addToHoard(goods);
        }

    }

    public void rollItems(LootCalc dM) {
        // Roll to determine what grouping of items to roll next
        Integer itemGroup = dM.rollItemGroup();
        // Roll to find quantity (how many times to roll next) based on result
        // from previous chart
        Integer numDice = dM.getNumDice(itemGroup);
        Integer dieSize = dM.getDieSize(itemGroup);
        Integer numItems = dM.getDice().roll(numDice, dieSize);

        for (int ii = 0; ii < numItems; ii++) {
            LootItem item = dM.rollItem(itemGroup);
            if (dM.getPrefs().isNoRepeats() && dM.isValid(item)) {
                addToTrove(item);
            } else if (dM.isValid(item)) {
                addToHoard(item);
            }
        }

    }

    // Getters/Setters
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
