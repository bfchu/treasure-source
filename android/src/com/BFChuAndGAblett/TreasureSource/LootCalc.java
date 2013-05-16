/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource;

import android.util.Log;

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

    private static final String TAG = "LootCalc";
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
    /**
     * COINS
     * */
    public LootItemGold rollCoins() {
        LootItemGold coins = new LootItemGold();

        String tableIndex = "APL" + prefs.getAPL() + "_Coins";
        Integer dRoll = rollPercent();

        Integer numDice = 1;
        Integer dieSize = 6;
        Integer coinQuantity = 10;
        Integer coinType = 3; // 1 = cp, 2 = sp, 3 = gp, 4 = pp.

        books.getCoinsByAPL(dRoll, tableIndex, prefs.getAPL(), numDice,
                dieSize, coinQuantity, coinType);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Roll: " + dRoll + ", got from DB: numDice: " + numDice
                    + ", dieSize: " + dieSize + ", quantity: " + coinQuantity
                    + ", coinType: " + coinType);
        }

        coins.setCoinType(coinType);
        coins.setQuantity((int) (coinQuantity * dice.roll(numDice, dieSize) * getTreasureMultiplier()));
        return coins;
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

    /**
     * GOODS
     * */
    public LootItemGoods rollGoods() {
        LootItemGoods goods = new LootItemGoods();

        String tableIndex = "APL" + prefs.getAPL() + "_Goods";
        Integer dRoll = rollPercent();

        // Roll on goods chart to determine if and what kind of goods
        Integer numDice = 1;
        Integer dieSize = 6;
        Integer goodsType = 1;

        books.getGoodsByAPL(dRoll, tableIndex, prefs.getAPL(), numDice,
                dieSize, goodsType);

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Roll: " + dRoll + ", got from DB: numDice: " + numDice
                    + ", dieSize: " + dieSize + ", goodsType: " + goodsType);
        }

        goods.setGoodsType(goodsType);
        // Roll number of goods
        goods.setQuantity(dice.roll(numDice, dieSize));

        // Roll value per goods
        double goodsMultiplier = getTreasureMultiplier();
        goods.setgValue(rollGoodsVal(goods.getGoodsType()) * goodsMultiplier);

        return goods;
    }

    public Double rollGoodsVal(Integer goodsType) {
        Double val = 1.0;
        int numDice = 1;
        int dieSize = 6;
        int coinVal = 10;
        int dRoll = rollPercent();

        // TODO: replace with database calls
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

    /**
     * ITEMS
     * */
    public void rollItemGrouping(Integer numDice, Integer dieSize,
            Integer itemGroup) {
        String tableIndex = "APL" + prefs.getAPL() + "_Items";
        Integer dRoll = rollPercent();

        books.getItemsByAPL(dRoll, tableIndex, numDice, dieSize, itemGroup);
    }

    public Integer rollNumItems(Integer numDice, Integer dieSize) {
        return dice.roll(numDice, dieSize);
    }

    @Deprecated
    public Integer getNumDice(String tableIndex, Integer numRolled) {
        return books.getNumDice(numRolled, tableIndex);
    }

    @Deprecated
    public Integer getDieSize(String tableIndex, Integer numRolled) {
        return books.getDieSize(numRolled, tableIndex);
    }

    public LootItem rollItem(Integer rarityLevel) {
        LootItem item = new LootItem();
        // catch mundane item calls first
        if (rarityLevel == 1) {
            LootItem mundane = rollMundaneItem();
            return mundane;
        }

        do {
            // Roll to determine item type (ie. armor, weapon, scroll...)
            if (rarityLevel == 2) {
                item.setItemType(rollMinorItemType());
                while (!isValid(item)) {
                    item.setItemType(rollMinorItemType());
                }
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
                item = rollMagicWeapon(rarityLevel);
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
                item = rollWondrousItem(rarityLevel);
            }
            item.setrPower(rarityLevel + item.getmLevel());

        } while (isValid(item));
        return item;
    }

    /**
     * MUNDANE ITEMS
     * */
    public LootItem rollMundaneItem() {

        LootItem item = new LootItem();
        String mundaneType = rollMundaneType();
        Integer dRoll = rollPercent();

        if (mundaneType == "Alchemical_item") {
            item = books.getMundaneItem(dRoll, mundaneType);
        } else if (mundaneType == "Armor") {
            item = books.getMundaneItem(dRoll, mundaneType);
        } else if (mundaneType == "Weapon") {
            if (dRoll < 71) {
                item = new LootItem(dRoll, "Masterwork " + rollWeaponType(1),
                        300);
            } else {
                item = new LootItem(dRoll, "Masterwork " + rollWeaponType(4),
                        300);
            }
        } else if (mundaneType == "Tools_and_gear") {
            item = books.getMundaneItem(dRoll, mundaneType);
        }

        return item;
    }

    private String rollMundaneType() {
        String type = "Weapon";
        Integer dRoll = rollPercent();

        type = books.getMundaneType(dRoll);

        return type;
    }

    /** WONDROUS ITEMS */
    private LootItem rollWondrousItem(Integer rarityLevel) {
        String wondrousType = rollWondrousType();

        LootItem item = rollSpecificItem(rarityLevel, wondrousType);

        item.setItemType(11);
        return item;
    }

    public String rollWondrousType() {
        String type = "Wondrous_";
        Integer dRoll = rollPercent();

        type += books.getWondrousType(dRoll);

        return type;
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

    /** MAGIC WEAPONS */
    private LootItem rollMagicWeapon(Integer rarityLevel) {
        LootItem item = new LootItem();

        // Is it made of special stuff?
        if (rollPercent() > 95) {
            item.setName(rollSpecialMaterial() + " ");
        }
        // is it a Weapon, Ranged Weapon, or Ammunition?
        // 1,2,3 = weapon. 4,5 = ranged weapon. 6 = ammo;
        Integer weaponRangedOrAmmo = dice.roll(1, 6);

        // What kind of weapon?
        String weaponType = rollWeaponType(weaponRangedOrAmmo);

        Integer isSpecific = 0;
        // Does it have special abilities?
        String specialAbs = rollWeaponSpecials(weaponRangedOrAmmo, rarityLevel,
                isSpecific);

        if (isSpecific > 0) {
            item.setName(specialAbs);
        } else {
            item.setName(item.getName() + specialAbs + weaponType);
        }

        item.setItemType(4);
        return item;
    }

    private String rollWeaponType(int weaponRangedOrAmmo) {
        String type = "longsword";
        Integer dRoll = rollPercent();

        if (weaponRangedOrAmmo < 4) {
            type = books.getMeleeWeaponType(dRoll);
        } else if (weaponRangedOrAmmo < 6) {
            type = books.getRangedWeaponType(dRoll);
        } else {
            type = books.getAmmoType(dRoll);
        }

        return type;
    }

    public String rollWeaponSpecials(int weaponRangedOrAmmo,
            Integer rarityLevel, Integer isSpecific) {
        String abilities = "+1";
        Integer enhancement = 1;
        Integer numAbilities = 0;
        Integer abilityLevel = 1;
        double priceAdjust = 0.0;
        Integer dRoll = rollPercent();
        // is it a Greater or Lesser Item?
        boolean isGreaterItem = true;
        if (dice.roll(1, 2) != 2) {
            isGreaterItem = false;
        }

        // Get Abilities
        if (weaponRangedOrAmmo < 4) {
            books.getWeaponSpecs(dRoll, isGreaterItem, rarityLevel,
                    enhancement, numAbilities, abilityLevel, isSpecific);
            if (isSpecific > 0) {
                LootItem item = rollSpecificItem(rarityLevel, "Weapon");
                abilities = item.getName();
            } else {
                abilities = rollAbilities("Weapon", numAbilities, abilityLevel,
                        priceAdjust);
            }
        } else if (weaponRangedOrAmmo < 6) {
            books.getWeaponSpecs(dRoll, isGreaterItem, rarityLevel,
                    enhancement, numAbilities, abilityLevel, isSpecific);
            if (isSpecific > 0) {
                LootItem item = rollSpecificItem(rarityLevel, "Weapon");
                abilities = item.getName();
            } else {
                abilities = rollAbilities("Ranged_Weapons", numAbilities,
                        abilityLevel, priceAdjust);
            }
        } else {
            if (isSpecific > 0) {
                LootItem item = rollSpecificItem(rarityLevel, "Weapon");
                abilities = item.getName();
            } else {
                abilities = rollAbilities("Ammunition", numAbilities,
                        abilityLevel, priceAdjust);
            }
        }

        return abilities;
    }

    /** MAGIC ARMOR */
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
            item.setName(item.getName() + specialAbs + armorType);
        }

        item.setItemType(3);
        return item;
    }

    public String rollArmorSpecials(int armorOrShield, Integer rarityLevel,
            Integer isSpecific) {
        String abilities = "+1";
        Integer enhancement = 1;
        Integer numAbilities = 0;
        Integer abilityLevel = 1;
        double priceAdjust = 0.0;
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
                LootItem item = rollSpecificItem(rarityLevel, "Armor");
                abilities = item.getName();
            } else {
                abilities = rollAbilities("Armor", numAbilities, abilityLevel,
                        priceAdjust);
            }
        } else {
            books.getArmorSpecs(dRoll, isGreaterItem, rarityLevel, enhancement,
                    numAbilities, abilityLevel, isSpecific);
            if (isSpecific > 0) {
                LootItem item = rollSpecificItem(rarityLevel, "Shields");
                abilities = item.getName();
            } else {
                abilities = rollAbilities("Armor", numAbilities, abilityLevel,
                        priceAdjust);
            }
        }

        return abilities;
    }

    private String rollAbilities(String itemType, Integer numAbilities,
            Integer abilityLevel, double priceAdjust) {
        String abilities = null;
        Integer dRoll = rollPercent();

        for (int ii = 0; ii < numAbilities; ii++) {
            abilities += books.getAbilities(dRoll, itemType, abilityLevel,
                    priceAdjust) + " ";
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
        boolean validity = true;
        Integer campaignSpeed = 2; // normal speed
        double goldAmmount = books.getEncounterValue(prefs.getAPL(),
                campaignSpeed);
        Integer magicLevel = prefs.getAPL() * prefs.getMagicLv();

        if ((item.getgValue() > goldAmmount) && prefs.isLimitValByCR()) {
            validity = false;
        }

        if (item.getmLevel() > magicLevel) {
            validity = false;
        }

        if (prefs.getItemRestrictions()[item.getItemType()]) {
            validity = false;
        }

        return validity;
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
