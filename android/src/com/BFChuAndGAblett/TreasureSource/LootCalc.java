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
        Integer dRoll = rollPercent();

        Integer numDice = getNumDice(tableIndex, dRoll);
        Integer dieSize = getDieSize(tableIndex, dRoll);
        Integer coinType = getCoinType(tableIndex, dRoll); // 1 = cp, 2 = sp, 3
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
        Integer dRoll = rollPercent();

        // Roll on goods chart to determine if and what kind of goods
        Integer numDiceGoods = books.getNumDice(dRoll, tableIndex);
        Integer dieSizeGoods = books.getDieSize(dRoll, tableIndex);
        goods.setGoodsType(books.getGoodsType(dRoll, tableIndex));
        // Roll number of goods
        goods.setQuantity(rollNumGoods(numDiceGoods, dieSizeGoods));

        // Roll value per goods
        goods.setgValue(rollGoodsVal(goods.getGoodsType())); // TODO: decide if
                                                             // this is the
                                                             // place to
                                                             // multiply for
                                                             // treasure size

        return goods;
    }

    public Integer rollNumGoods(Integer numDice, Integer dieSize) {
        return dice.roll(numDice, dieSize);
    }

    public Integer rollNumItems(Integer numDice, Integer dieSize) {
        return dice.roll(numDice, dieSize);
    }

    public Double rollGoodsVal(Integer goodsType) {
        Double val = 1.0;
        int numDice = 1;
        int dieSize = 6;
        int coinVal = 10;
        int dRoll = rollPercent();

        // Hard-coded table for Gems and Art value ranges
        if (goodsType != 2) { // Gems table
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
        } else { // Art table
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

    public void rollItemGrouping(Integer numDice, Integer dieSize,
            Integer itemGroup) {
        String tableIndex = "APL" + prefs.getaPL() + "_Items";
        Integer dRoll = rollPercent();

        // Roll on goods chart to determine if and what kind of goods
        numDice = books.getNumDice(dRoll, tableIndex);
        dieSize = books.getDieSize(dRoll, tableIndex);
        itemGroup = books.getItemGroup(dRoll, tableIndex);
    }

    public Integer getNumDice(String tableIndex, Integer numRolled) {
        return books.getNumDice(numRolled, tableIndex);
    }

    public Integer getDieSize(String tableIndex, Integer numRolled) {
        return books.getDieSize(numRolled, tableIndex);
    }

    public LootItem rollItem(Integer rarityLevel) {

        // catch mundane item calls first
        if (rarityLevel == 1) {
            LootItem mundane = rollMundaneItem();
            return mundane;
        }

        LootItem item = new LootItem();
        item.setrPower(rarityLevel);
        // Roll to determine item type (ie. armor, weapon, scroll...)
        if (rarityLevel == 2) {
            item.setItemType(rollMinorItemType());
        } else if (rarityLevel == 3) {
            item.setItemType(rollMediumItemType());
        } else if (rarityLevel == 4) {
            item.setItemType(rollMajorItemType());
        }

        // TODO:
        // Roll qualities, and special abilities.
        if (item.getItemType() == 3) {
            item = rollMagicArmor(rarityLevel);
        } else if (item.getItemType() == 4) {
            // item = rollMagicWeapon(rarityLevel);
        } else if (item.getItemType() == 5) {
            item = rollSpecificItem(rarityLevel, "Potions");
        } else if (item.getItemType() == 6) {
            item = rollSpecificItem(rarityLevel, "Rings");
        } else if (item.getItemType() == 7) {
            item = rollSpecificItem(rarityLevel, "Rods");
        } else if (item.getItemType() == 8) {
            // item = rollScrolls(rarityLevel);
        } else if (item.getItemType() == 9) {
            item = rollSpecificItem(rarityLevel, "Staves");
        } else if (item.getItemType() == 10) {
            // item = rollWand();
        } else if (item.getItemType() == 11) {
            // item = rollWondrousItem(rarityLevel);
        }

        return item;
    }

    private LootItem rollSpecificItem(Integer rarityLevel, String itemType) {
        LootItem item = new LootItem();
        Integer dRoll = rollPercent();
        boolean isGreaterItem = true;
        if (dice.roll(1, 2) != 2) {
            isGreaterItem = false;
        }
        String name = null;
        double gVal = 1.0;

        books.getSpecificItem(dRoll, itemType, isGreaterItem, rarityLevel,
                name, gVal);

        item.setName(name);
        item.setgValue(gVal);
        item.setNumRolled(dRoll);

        return item;
    }

    private LootItem rollRing(Integer rarityLevel) {
        LootItem ring = new LootItem();
        Integer dRoll = rollPercent();
        boolean isGreaterItem = true;
        if (dice.roll(1, 2) != 2) {
            isGreaterItem = false;
        }
        String name = "Bellowing Dragoncrest Ring";
        double gVal = 1.0;

        books.getSpecificItem(dRoll, "Rings", isGreaterItem, rarityLevel, name,
                gVal);

        ring.setName(name);
        ring.setgValue(gVal);
        ring.setNumRolled(dRoll);

        return ring;
    }

    private LootItem rollMagicArmor(Integer rarityLevel) {
        LootItem item = new LootItem();

        // Is it made of special stuff?
        if (rollPercent() > 95) {
            item.setName(rollSpecialMaterial() + " ");
        }
        // is it armor or shield?
        Integer armorOrShield = dice.roll(1, 2);// 1 = armor, 2 = shield;
        // What kind of armor or shield?
        String armorType = rollArmorType(armorOrShield);

        Integer isSpecific = 0;
        // Does it have special abilities?
        String specialAbs = rollArmorSpecials(armorOrShield, rarityLevel,
                isSpecific);

        if (isSpecific > 0) {
            item.setName(specialAbs);
        } else {
            item.setName(item.getName() + specialAbs + " " + armorType);
        }

        return item;
    }

    public String rollArmorSpecials(int armorOrShield, Integer rarityLevel,
            Integer isSpecific) {
        String abilities = "+1";
        Integer enhancement = 1;
        Integer numAbilities = 0;
        Integer abilityLevel = 1;
        Integer dRoll = rollPercent();
        // is it a Greater or Lesser Item?
        boolean isGreaterItem = true;
        if (dice.roll(1, 2) != 2) {
            isGreaterItem = false;
        }

        // Get Abilities
        if (armorOrShield != 2) {
            books.getArmorSpecs(dRoll, isGreaterItem, rarityLevel, enhancement,
                    numAbilities, abilityLevel, isSpecific);
            if (isSpecific > 0) {
                // TODO: get specific item built and return it,
            } else {
                abilities = rollArmorAbilities();
            }
        } else {
            books.getArmorSpecs(dRoll, isGreaterItem, rarityLevel, enhancement,
                    numAbilities, abilityLevel, isSpecific);
            if (isSpecific > 0) {
                // TODO: get specific item built and return it,
            } else {
                // TODO: abilities = ;
            }
        }

        return abilities;
    }

    private String rollArmorType(int armorOrShield) {
        String type = "full plate";
        Integer dRoll = rollPercent();

        if (armorOrShield != 2) {
            type = books.getArmorType(dRoll);
        } else {
            type = books.getShieldType(dRoll);
        }

        return type;
    }

    private String rollSpecialMaterial() {
        String material = "Adamantine"; // placeholder
        // TODO: find out how special materials are rolled, then put it here.
        return material;
    }

    public LootItem rollMundaneItem() {
        LootItem item = new LootItem();
        // TODO: make database table containing all possible mundane items, then
        // make this function create a LootItem object from that table
        return item;
    }

    public Integer rollMinorItemType() {
        // Hard-coded static table
        Integer roll = rollPercent();
        if (roll < 5) {
            return 3; // Armor and Shields
        } else if (roll < 10) {
            return 4; // Weapons
        } else if (roll < 45) {
            return 5; // Potions
        } else if (roll < 47) {
            return 6; // Rings
        } else if (roll < 82) {
            return 8; // Scrolls
        } else if (roll < 92) {
            return 10; // Wands
        } else {
            return 11; // Wondrous Items
        }
    }

    public Integer rollMediumItemType() {
        // Hard-coded static table
        Integer roll = rollPercent();
        if (roll < 11) {
            return 3; // Armor and Shields
        } else if (roll < 21) {
            return 4; // Weapons
        } else if (roll < 31) {
            return 5; // Potions
        } else if (roll < 41) {
            return 6; // Rings
        } else if (roll < 51) {
            return 7; // Rods
        } else if (roll < 66) {
            return 8; // Scrolls
        } else if (roll < 69) {
            return 9; // Staves
        } else if (roll < 84) {
            return 10; // Wands
        } else {
            return 11; // Wondrous Items
        }
    }

    public Integer rollMajorItemType() {
        // Hard-Coded static table
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
