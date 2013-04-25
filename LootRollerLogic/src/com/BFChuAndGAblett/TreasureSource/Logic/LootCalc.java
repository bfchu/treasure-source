/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource.Logic;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 *         LootCalc class handles most of math. It gathers the loot tables and
 *         dice and outputs loot Items. Contains math methods for determining
 *         how many rolls happen at various stages.
 * 
 * 
 */
public class LootCalc {

    private LootDice dice;
    private LootIO books;
    private LootPrefs prefs;

    public LootCalc() {
        super();
    }

    /**
     * @param dice
     * @param table
     */
    public LootCalc(LootDice dice, LootIO table, LootPrefs prefs) {
        super();
        this.dice = dice;
        this.books = table;
        this.prefs = prefs;
    }

    // Paizo math
    public LootItem rollCoins() {
        LootItemGold coins = new LootItemGold();

        return coins;
    }

    public Integer rollGoodsType() {
        Integer type = dice.roll(1, 4);
        return type;
    }

    public Integer rollNumGoods(Integer numDice, Integer dieSize) {
        Integer numGoods = dice.roll(1, 100);
        // needs input form table
        return numGoods;
    }

    public Double rollGoodsVal() {
        Double val = (double) dice.roll(1, 100);
        // needs input from table
        // database reminder: there are approximately 60 base treasure values
        // determined by level of party, cr, and game pace
        return val;
    }

    public Integer rollItemGroup() {
        Integer group = dice.roll(1, 4);

        // get string from table
        return group;
    }

    public Integer getNumDice(Integer tableIndex) {
        Integer numDice = 0;
        // numDice = (method that pulls numDice from database at tableIndex);

        return numDice;
    }

    public Integer getDieSize(Integer tableIndex) {
        Integer dieSize = 0;
        // dieSize = (method that pulls dieSize from database at tableIndex);

        return dieSize;
    }

    public Integer rollNumItems(Integer numDice, Integer dieSize) {
        Integer numItems = dice.roll(numDice, dieSize);
        // needs input from table
        return numItems;
    }

    public Integer getRarity(Integer tableIndex) {
        Integer rarity = dice.roll(1, 4);
        // rarity = (method that pulls rarity from database at tableIndex);
        return rarity;
    }

    public LootItem rollItem(Integer rarityLevel) {
        // Roll to determine item type
        // Roll qualities
        // if special abilities: Roll on ability chart
        switch (rarityLevel) {
        case 1:
            LootItemMundane item1 = null;
            return item1;
        case 2:
            LootItemMagic item2 = null;
            return item2;
        case 3:
            LootItemMagic item3 = null;
            return item3;
        case 4:
            LootItemMagic item4 = null;
            return item4;
        }

        return null;
    }

    public Integer rollPercent() {
        return dice.roll(1, 100);
    }

    public boolean isValid(LootItem item) {

        return true;
    }

    // Getter/setters
    /**
     * @return the dice
     */
    public LootDice getDice() {
        return dice;
    }

    /**
     * @param dice
     *            the dice to set
     */
    public void setDice(LootDice dice) {
        this.dice = dice;
    }

    /**
     * @return the books
     */
    public LootIO getBooks() {
        return books;
    }

    /**
     * @param books
     *            the books to set
     */
    public void setBooks(LootIO books) {
        this.books = books;
    }

    /**
     * @return the prefs
     */
    public LootPrefs getPrefs() {
        return prefs;
    }

    /**
     * @param prefs
     *            the prefs to set
     */
    public void setPrefs(LootPrefs prefs) {
        this.prefs = prefs;
    }

}
