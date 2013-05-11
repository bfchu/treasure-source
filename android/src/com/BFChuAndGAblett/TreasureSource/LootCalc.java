/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

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
    private LootDB books;
    private LootPrefs prefs;

    public LootCalc() {
        super();
    }

    /**
     * @param dice
     * @param table
     */
    public LootCalc(LootDice dice, LootDB table, LootPrefs prefs) {
        super();
        this.dice = dice;
        this.books = table;
        this.prefs = prefs;
    }

    public LootCalc(LootDB books, LootPrefs prefs) {
        super();
        this.dice = new LootDice();
        this.books = books;
        this.prefs = prefs;
    }

    // Paizo math
    public LootItem rollCoins() {
        LootItemGold coins = new LootItemGold();

        String tableIndex = "APL" + prefs.getaPL() + "_Coins";
        Integer rolled = rollPercent();

        Integer numDice = getNumDice(tableIndex, rolled);
        Integer dieSize = getDieSize(tableIndex, rolled);
        Integer coinType = getCoinType(tableIndex, rolled); // 1 = cp, 2 = sp, 3
                                                            // // = gp, 4 = pp.
        Integer coinQuantity = rollCoinsQuanitity(numDice, dieSize);

        coins.setCoinType(coinType);
        coins.setQuantity(coinQuantity);
        return coins;
    }

    public Integer getCoinType(String tableIndex, Integer numRolled) {
        return books.getCoinType(numRolled, tableIndex);
    }

    public Integer rollCoinsQuanitity(Integer numDice, Integer dieSize) {
        double coinMultiplier = getTreasureMultiplier();
        return (int) (dice.roll(numDice, dieSize) * coinMultiplier);
    }

    public double getTreasureMultiplier() {
        // Detecting for size of treasure hoard.
        switch (prefs.getLootSize()) {
        case 1:
            return 0.25;
        case 2:
            return 0.5;
        case 3:
            return 1.0;
        case 4:
            return 2.0;
        case 5:
            return 3.0;
        }
        return 1.0;
    }

    public LootItemGoods rollGoods() {
        LootItemGoods goods = new LootItemGoods();

        String tableIndex = "APL" + prefs.getaPL() + "_Goods";
        Integer rolled = rollPercent();

        // Roll on goods chart to determine if and what kind of goods
        Integer numDiceGoods = books.getNumDice(rolled, tableIndex);
        Integer dieSizeGoods = books.getDieSize(rolled, tableIndex);
        goods.setGoodsType(books.getGoodsType(rolled, tableIndex));
        // Roll number of goods
        goods.setQuantity(rollNumGoods(numDiceGoods, dieSizeGoods));

        // Roll value per goods
        goods.setgValue(rollGoodsVal(goods.getGoodsType()));

        return goods;
    }

    public Integer rollNumGoods(Integer numDice, Integer dieSize) {
        return dice.roll(numDice, dieSize);
    }

    public Double rollGoodsVal(Integer goodsType) {
        Double val = 1.0;
        int numDice = 1;
        int dieSize = 6;
        int coinVal = 10;
        int dRoll = rollPercent();

        // Hard-coded table for Gems and Art value ranges
        if (goodsType != 2) {
            if (rollIsBetween(dRoll, 1, 25)) {
                numDice = 4;
                dieSize = 4;
                coinVal = 1;
            } else if (rollIsBetween(dRoll, 26, 50)) {
                numDice = 2;
                dieSize = 4;
                coinVal = 10;
            } else if (rollIsBetween(dRoll, 51, 70)) {
                numDice = 4;
                dieSize = 4;
                coinVal = 10;
            } else if (rollIsBetween(dRoll, 71, 90)) {
                numDice = 2;
                dieSize = 4;
                coinVal = 100;
            } else if (rollIsBetween(dRoll, 91, 99)) {
                numDice = 4;
                dieSize = 4;
                coinVal = 100;
            } else if (rollIsBetween(dRoll, 100, 100)) {
                numDice = 2;
                dieSize = 4;
                coinVal = 1000;
            }
        } else {
            if (rollIsBetween(dRoll, 1, 10)) {
                numDice = 1;
                dieSize = 10;
                coinVal = 10;
            } else if (rollIsBetween(dRoll, 11, 25)) {
                numDice = 3;
                dieSize = 6;
                coinVal = 10;
            } else if (rollIsBetween(dRoll, 26, 40)) {
                numDice = 1;
                dieSize = 6;
                coinVal = 100;
            } else if (rollIsBetween(dRoll, 41, 50)) {
                numDice = 1;
                dieSize = 10;
                coinVal = 100;
            } else if (rollIsBetween(dRoll, 51, 60)) {
                numDice = 2;
                dieSize = 6;
                coinVal = 100;
            } else if (rollIsBetween(dRoll, 61, 70)) {
                numDice = 3;
                dieSize = 6;
                coinVal = 100;
            } else if (rollIsBetween(dRoll, 71, 80)) {
                numDice = 4;
                dieSize = 6;
                coinVal = 100;

            } else if (rollIsBetween(dRoll, 81, 85)) {
                numDice = 5;
                dieSize = 6;
                coinVal = 100;
            } else if (rollIsBetween(dRoll, 86, 90)) {
                numDice = 1;
                dieSize = 4;
                coinVal = 1000;
            } else if (rollIsBetween(dRoll, 91, 99)) {
                numDice = 1;
                dieSize = 6;
                coinVal = 1000;
            } else if (rollIsBetween(dRoll, 100, 100)) {
                numDice = 2;
                dieSize = 6;
                coinVal = 1000;
            }
        }

        val = (double) (dice.roll(numDice, dieSize) * coinVal);
        return val;
    }

    public boolean rollIsBetween(int dRoll, int low, int high) {
        if (!((dRoll < low) && (dRoll > high))) {
            return true;
        }
        return false;
    }

    public void rollItemGrouping(Integer APL, Integer numDice, Integer dieSize,
            Integer itemGroup) {
        // rollPercent();
        // TODO: create database calls to return the proper item group and dice

    }

    public Integer getNumDice(String tableIndex, Integer numRolled) {
        return books.getNumDice(numRolled, tableIndex);
    }

    public Integer getDieSize(String tableIndex, Integer numRolled) {
        return books.getDieSize(numRolled, tableIndex);
    }

    public Integer rollNumItems(Integer numDice, Integer dieSize) {
        Integer numItems = dice.roll(numDice, dieSize);
        // TODO:needs input from table
        return numItems;
    }

    public Integer getRarity(Integer tableIndex) {
        Integer rarity = dice.roll(1, 4);
        // TODO: rarity = (method that pulls rarity from database at
        // tableIndex);
        return rarity;
    }

    public LootItem rollItem(Integer rarityLevel) {
        // TODO:Roll to determine item type
        // Roll qualities
        // if special abilities: Roll on ability chart

        return null;
    }

    public Integer rollItemType(String rarityLevel) {
        // TODO:
        return null;
    }

    public Integer rollMinorItemType() {
        // TODO: replace with databse calls
        Integer roll = rollPercent();
        if (roll < 5) {
            return 3;
        } else if (roll < 10) {
            return 4;
        } else if (roll < 45) {
            return 5;
        } else if (roll < 47) {
            return 6;
        } else if (roll < 82) {
            return 8;
        } else if (roll < 92) {
            return 10;
        } else {
            return 11;
        }
    }

    public Integer rollMediumItemType() {
        // TODO: replace with databse calls
        Integer roll = rollPercent();
        if (roll < 11) {
            return 3;
        } else if (roll < 21) {
            return 4;
        } else if (roll < 31) {
            return 5;
        } else if (roll < 41) {
            return 6;
        } else if (roll < 51) {
            return 7;
        } else if (roll < 66) {
            return 8;
        } else if (roll < 69) {
            return 9;
        } else if (roll < 84) {
            return 10;
        } else {
            return 11;
        }
    }

    public Integer rollMajorItemType() {
        // TODO: replace with databse calls
        Integer roll = rollPercent();
        if (roll < 11) {
            return 3;
        } else if (roll < 21) {
            return 4;
        } else if (roll < 26) {
            return 5;
        } else if (roll < 36) {
            return 6;
        } else if (roll < 46) {
            return 7;
        } else if (roll < 56) {
            return 8;
        } else if (roll < 76) {
            return 9;
        } else if (roll < 81) {
            return 10;
        } else {
            return 11;
        }
    }

    public Integer rollPercent() {
        return dice.roll(1, 100);
    }

    public boolean isValid(LootItem item) {
        // TODO: create validity tests based on APL, CR, encounter value,
        // allowed item types, and other prefs
        // lol...

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
    public LootDB getBooks() {
        return books;
    }

    /**
     * @param books
     *            the books to set
     */
    public void setBooks(LootDB books) {
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
