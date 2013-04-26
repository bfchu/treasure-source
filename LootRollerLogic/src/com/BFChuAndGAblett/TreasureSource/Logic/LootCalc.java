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
        // pass in a rollPercent() to a table look-up, get back numDice,
        // dieSize, and
        String coinType = "gp";
        Integer coinQuantity = rollCoinsQuanitity(1, 6, 1000);

        coins.setCoinType(coinType);
        // roll quantity
        coins.setQuantity(coinQuantity);
        return coins;
    }

    public Integer rollCoinsQuanitity(Integer numDice, Integer dieSize,
            Integer coinQuantityCoefficient) {

        Integer quantity = (dice.roll(numDice, dieSize) * coinQuantityCoefficient);
        return quantity;
    }

    public void rollGoodsType(Integer numDice, Integer dieSize, String goodsType) {

        // rollPercent();
        // lookup on table
        // get back values for numDie, dieSize, goodsType.

        // placeholders
        numDice = dice.roll(1, 20); // getNumDice();
        dieSize = dice.roll(1, 20); // getDieSize();

        // placeholder for table calls
        Integer roll = dice.roll(1, 3);
        switch (roll) {
        case 1:
            goodsType = "";
            break;
        case 2:
            goodsType = "gems";
            break;
        case 3:
            goodsType = "art";
            break;
        }

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

    public void rollItemGrouping(Integer numDice, Integer dieSize,
            String itemGroup) {
        // rollPercent();

        // place holders
        numDice = dice.roll(1, 20); // getNumDice();
        dieSize = dice.roll(1, 20); // getDieSize();

        Integer roll = dice.roll(1, 5);
        switch (roll) {
        case 1:
            itemGroup = "";
            break;
        case 2:
            itemGroup = "mundane";
            break;
        case 3:
            itemGroup = "minor";
            break;
        case 4:
            itemGroup = "medium";
            break;
        case 5:
            itemGroup = "major";
            break;
        }
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

    public LootItem rollItem(String rarityLevel) {
        // Roll to determine item type
        // Roll qualities
        // if special abilities: Roll on ability chart

        switch (rarityLevel) {
        case "mundane":
            LootItemMundane item1 = new LootItemMundane();
            item1.setItemType(rollItemType(rarityLevel));
            return item1;
        case "minor":
            LootItemMagic item2 = new LootItemMagic();
            item2.setItemType(rollItemType(rarityLevel));
            return item2;
        case "medium":
            LootItemMagic item3 = new LootItemMagic();
            item3.setItemType(rollItemType(rarityLevel));
            return item3;
        case "major":
            LootItemMagic item4 = new LootItemMagic();
            item4.setItemType(rollItemType(rarityLevel));
            return item4;
        }

        return null;
    }

    public Integer rollItemType(String rarityLevel) {
        switch (rarityLevel) {
        case "mundane":
            Integer type1 = 0;
            return type1;
        case "minor":
            Integer type2 = rollMinorItemType();
            return type2;
        case "medium":
            Integer type3 = rollMediumItemType();
            return type3;
        case "major":
            Integer type4 = rollMajorItemType();
            return type4;
        }
        return null;
    }

    public Integer rollMinorItemType() {
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
